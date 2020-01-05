<!DOCTYPE html>
<html>
<head>
<meta >
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=rvgrn6yT8jA11suotGclTuLe9Aj3AivK"></script>
	<title>地图显示</title>
</head>
<body>
	<div id="allmap"></div>
</body>

    <%
	String username = null;
	Cookie[] cookies = request.getCookies();
	for (Cookie cookie : cookies) {
		if (cookie.getName().equals("username")) {
			username = cookie.getValue();
		}
	}
	if(username==null){
		out.println("<script>alert('没有权限 ')</script>");
		return;
	}
	%>
		<%!
			String power;
			String status;
			String subtitle;
		%>
		<%
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("power")) {
					power = cookie.getValue();
				}
				if (cookie.getName().equals("status")) {
					status = cookie.getValue();
				}
			}
		%>

<script type="text/javascript">

</script>
<%@page import="org.model.SchemeMake.PathApi.PathRequest"%>
<%@page import="org.model.BaiduMap"%>
<%@page import="org.model.settings"%>
<%@page import="org.model.SchemeMake.PathApi.PathReturn"%>
<%@page import="org.model.SchemeMake.Units.vertex"%>
<%@page import="org.model.SchemeMake.Units.edge"%>
<%@page import="org.model.SchemeMake.Units.capacity"%>
<%@page import="org.model.SchemeMake.Units.evaRoute"%>
<%@page import="org.model.SchemeMake.PathApi.result.Route"%>
<%@page import="org.model.Query"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Iterator"%>

<script type="text/javascript">
	
	var points=[
		<%
		String project_id = null;
		String Event_id = null;
		project_id = request.getParameter("Project_id");
		Event_id   = request.getParameter("Event_id");
		if(project_id!=null)
		System.out.println("[get project]"+project_id);
		if(Event_id!=null)
		System.out.println("[get eventID]"+Event_id);
		TreeSet<vertex> ts = new TreeSet<vertex>();
		vertex origin=null;
		boolean flg = false;
		if(project_id!=null){
			ArrayList<String[]>ret = new ArrayList<String[]>();
			String sql = "select p_id,x_coordinate,y_coordinate from point where Event_id="+project_id;
			ret  = Query.runSql(3, sql);
			if(ret==null||ret.size()==0){
				System.out.println("[displayer] Wrong request : Event_ID="+project_id);
				out.print("<script>alert(\"wrong parameters\");</script>");
				out.flush();
				return;
			}
			else{
				for(int i=1;i<ret.size();i++){
					String [] tmp = ret.get(i);
					int p_id = Integer.parseInt(tmp[1]);
					double x = Double.parseDouble(tmp[2]);
					double y = Double.parseDouble(tmp[3]);
					vertex v = new vertex(x,y);
					v.setID(p_id);
					ts.add(v);
					if(!flg){
						origin = v ;
						flg = true;
					}
					out.print("new BMap.Point(");
					out.print(v.toString2());
					out.println("),");
				}
			}
		}
		if(Event_id!=null){
			ArrayList<String[]>ret = new ArrayList<String[]>();
			String sql = "select po_id,x,y from point1 where event_id="+Event_id;
			ret  = Query.runSql(3, sql);
			if(ret==null||ret.size()==0){
				System.out.println("[displayer] Wrong request : Event_ID="+Event_id);
			}
			else{
				for(int i=1;i<ret.size();i++){
					String [] tmp = ret.get(i);
					int po_id = Integer.parseInt(tmp[1]);
					double x = Double.parseDouble(tmp[2]);
					double y = Double.parseDouble(tmp[3]);
					vertex v = new vertex(x,y);
					v.setID(po_id);
					ts.add(v);
					if(!flg){
						origin = v ;
						flg = true;
					}
				}
			}
		}
		
		%>
	];
	var map = new BMap.Map("allmap");    // åå»ºMapå®ä¾
	map.centerAndZoom(new BMap.Point(<%if(origin!=null){
		out.print(origin.toString2());
	}
	else{
		out.print(((vertex)ts.iterator().next()).toString2());
	}%>), 14);  // åå§åå°å¾,è®¾ç½®ä¸­å¿ç¹åæ åå°å¾çº§å«
	//æ·»å å°å¾ç±»åæ§ä»¶
	map.addControl(new BMap.MapTypeControl({
		mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));	  
	map.setCurrentCity("å¤§è¿");          // è®¾ç½®å°å¾æ¾ç¤ºçåå¸ æ­¤é¡¹æ¯å¿é¡»è®¾ç½®ç
	map.enableScrollWheelZoom(true);    
    var plPoints = [
    	<%
		System.out.println(project_id);
		if(project_id!=null){
			ArrayList<String[]>ret = new ArrayList<String[]>();
			String sql = "select e_id,id1,id2,capacity from capacity where Event_id="+project_id;
			ret  = Query.runSql(4, sql);
			if(ret==null||ret.size()==0){
				System.out.println("[displayer] Wrong request : Event_ID="+project_id);
			}
			else{
				for(int i=1;i<ret.size();i++){
					String [] tmp = ret.get(i);
					int e_id = Integer.parseInt(tmp[1]);
					int id1 = Integer.parseInt(tmp[2]);
					int id2 = Integer.parseInt(tmp[3]);
					int capacity = Integer.parseInt(tmp[4]);
					capacity c = new capacity(e_id,id1,id2,capacity);
					
					vertex src=null;
					vertex dst=null;
					
					Iterator iter = ts.iterator();
					while(iter.hasNext()){
						vertex v = (vertex)iter.next();
						if(v.getID()==c.id1){
							src = v;
						}
						else if(v.getID()==c.id2){
							dst = v;
						}
					}
					if(src==null||dst==null){
						continue;
					}
					edge e = new edge(src,dst,capacity);
					out.print("{weight:"+"5"+",color:\"blue\",opacity:0.6,points:[");
					out.print("\""+e.getSrc().toString2()+"\",\""+e.getDst().toString2()+"\"");
					out.print("]},");
					out.println();
				}
			}
		}
		if(Event_id!=null){
			ArrayList<String[]>ret = new ArrayList<String[]>();
			String sql = "select evaRoute_id,Rid1,Rid2,capacity from evaRoute where event_id="+Event_id;
			ret  = Query.runSql(4, sql);
			if(ret==null||ret.size()==0){
				System.out.println("[displayer] Wrong request : Event_ID="+Event_id);
			}
			else{
				for(int i=1;i<ret.size();i++){
					String [] tmp = ret.get(i);
					int evaRoute_id = Integer.parseInt(tmp[1]);
					int Rid1 = Integer.parseInt(tmp[2]);
					int Rid2 = Integer.parseInt(tmp[3]);
					int capacity = Integer.parseInt(tmp[4]);
					evaRoute r = new evaRoute(evaRoute_id,Rid1,Rid2,capacity);
					
					vertex src=null;
					vertex dst=null;
					
					Iterator iter = ts.iterator();
					while(iter.hasNext()){
						vertex v = (vertex)iter.next();
						if(v.getID()==r.Rid1){
							src = v;
						}
						else if(v.getID()==r.Rid2){
							dst = v;
						}
					}
					if(src==null||dst==null){
						continue;
					}
					edge e = new edge(src,dst,capacity);
					out.print("{weight:"+"5"+",color:\"blue\",opacity:0.6,points:[");
					out.print("\""+e.getSrc().toString2()+"\",\""+e.getDst().toString2()+"\"");
					out.print("]},");
					out.println();
				}
			}
		}
		%>
    	
    	
    ];
    //画折线
    function addPolyline(){
        for(var i=0;i<plPoints.length;i++){
            var json = plPoints[i];
            var points = [];
            for(var j=0;j<json.points.length;j++){
                var p1 = json.points[j].split(",")[0];
                var p2 = json.points[j].split(",")[1];
                points.push(new BMap.Point(p1,p2));
            }
            var line = new BMap.Polyline(points,{strokeWeight:json.weight,strokeColor:json.color,strokeOpacity:json.opacity});
            map.addOverlay(line);
 
        }
    }

    
    points.forEach(function(value) {  
    	var locMarkers;
    	locMarkers = new BMap.Marker(value);
     
    	map.addOverlay(locMarkers);		
    	
    	locMarkers.disableMassClear();	
    	
    	;  
    });
    addPolyline();
//起点终点加marker
 var start = new BMap.Point(121.826775,39.106663);
 var end = new BMap.Point(121.639267,38.928051);
 var marker_sta=new BMap.Marker(start);
 var marker_end=new BMap.Marker(end);
 map.addOverlay(marker_sta);
 map.addOverlay(marker_end);


</script>

</html>
