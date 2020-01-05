package org.model.SchemeMake;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import org.model.BaiduMap;
import org.model.SchemeMake.EncodingApi.EncodingRequest;
import org.model.SchemeMake.EncodingApi.EncodingReturn;
import org.model.SchemeMake.Units.capacity;
import org.model.SchemeMake.Units.edge;
import org.model.SchemeMake.Units.evaRoute;
import org.model.SchemeMake.Units.vertex;
import org.springframework.jdbc.core.JdbcTemplate;

import system.utils.JDBCUtils;

import org.model.Query;
import org.model.event;
public class SchemeMake extends Thread{
	event e_event;
	String mode;
	final static Semaphore semDB = new Semaphore(100);
	//private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	public SchemeMake(
			String mode,
			event e_event) {
		this.e_event=e_event;
		this.mode=mode;
	}
	@Override
	public void run() {
		//将vertex类型转换为String
		String origin=e_event.origin;
		String destination=e_event.destination;
		if(mode.equals("地址模式")) {
			origin=new EncodingReturn(EncodingRequest.SendRequest(origin)).getLocation().toString2();
			destination=new EncodingReturn(EncodingRequest.SendRequest(destination)).getLocation().toString2();
		}
		float lng = Float.parseFloat(origin.substring(0,origin.indexOf(',')));
		float lat = Float.parseFloat(origin.substring(origin.indexOf(',')+1,origin.length()));
		float lng2 = Float.parseFloat(destination.substring(0,destination.indexOf(',')));
		float lat2 = Float.parseFloat(destination.substring(destination.indexOf(',')+1,destination.length()));
		vertex src = new vertex(lng,lat);
		vertex dst = new vertex(lng2,lat2);
		
		vertex src1 = new vertex(lng,lat);
		vertex dst1 = new vertex(lng2,lat2);
		
		System.out.println(src);
		System.out.println(dst);
		System.out.println("src1: "+src1);
		System.out.println("dst1: "+dst1);
		ArrayList<String> sqls = new ArrayList<String>();
		//获得锁
		SchemeMake.semDB.acquireUninterruptibly();
		//获得目前数据库中最大的点ID和边ID
		//如果数据库为空则最大ID为1 否则查询最大ID 本样例中的0ID就是数据库中最大ID+1,point表，point表和event表，project表
		int PID_0 = 1;
		int POID_0=1;
		int EID_0 = 1;
		int ProID_0 = 0;
		ArrayList<String[]>  ret;
		ret = Query.runSql(1, "Select max(p_id) from point");
		if(ret!=null&&ret.size()==1) {
			String currPIDmax = ret.get(0)[1];
			if(currPIDmax!=null&&!currPIDmax.equals("null"))
			PID_0 = Integer.parseInt(currPIDmax)+1;
		}
		ret = Query.runSql(1, "Select max(e_id) from capacity");
		if(ret!=null&&ret.size()==1) {
			String currEIDmax = ret.get(0)[1];
			if(currEIDmax!=null&&!currEIDmax.equals("null"))
			EID_0 = Integer.parseInt(currEIDmax)+1;
		}
		ret = Query.runSql(1, "Select max(po_id) from point1");
		if(ret!=null&&ret.size()==1) {
			String currPOIDmax = ret.get(0)[1];
			if(currPOIDmax!=null&&!currPOIDmax.equals("null"))
			POID_0 = Integer.parseInt(currPOIDmax)+1;
		}
		//获取点集合,从历史信息页面点进去，获取工程ID
		
		src.setID(PID_0++);
		dst.setID(PID_0++);
		
		src1.setID(POID_0++);
		dst1.setID(POID_0++);
		
		e_event.setStart(src);
		e_event.setEnd(dst);
		
		System.out.println("[SchemeMake]"+e_event.getStart());
		System.out.println("[SchemeMake]"+e_event.getEnd());
		src.setProject_id(e_event.Event_ID);
		dst.setProject_id(e_event.Event_ID);
		
		src1.setEvent_id(e_event.Event_ID);
		dst1.setEvent_id(e_event.Event_ID);
		
		
		//保存事件
		src.save();
		dst.save();
		
		src1.save1();
		dst1.save1();
		
		e_event.save();
		//获取十字交叉口
		vertex [] ps = BaiduMap.getAllJunction(src, dst,e_event.Event_ID);
		for(int i=0;i<ps.length;i++) {
			ps[i].setID(PID_0+i);
			ps[i].setProject_id(e_event.Event_ID);
			sqls.add(ps[i].getSaverSQL());
		}
		//获取百度地图的三条边的点*************
		ArrayList<String> sqls1 = new ArrayList<String>();
		vertex [] p = BaiduMap.getJunctions(src1, dst1);
		for(int i=0;i<p.length;i++) {
//			String p1=p[i].toString();
//			String sql="insert into path(path_id,e_id,event_id) values(null,'p1',e_event.Event_ID)";
//			Query.addSql(sql);
			p[i].setID(POID_0+i);
			p[i].setEvent_id(e_event.Event_ID);
			sqls1.add(p[i].getSaverSQL1());
			
			System.out.println("***********************");
			System.out.println("测试数据库插入语句是否执行");
			System.out.println("***********************");
		}
		//建立点边映射集合（匹配起点和终点在数据库中对应的ID），获取SQLs
		edge [] es = BaiduMap.es.toArray(new edge[BaiduMap.es.size()]);
		
		capacity [] c = new capacity[es.length];
		evaRoute[] r=new evaRoute[es.length];
		
		for(int i=0;i<es.length;i++) {
			int ID = EID_0+i;
			int evaRoute_id=EID_0+i;
			int id1 = 0;
			int id2 = 0;
			int Rid1=0;
			int Rid2=0;
			//道路容量=道路类型与距离得乘积。道路容量系数1000增加精度
			int capacity = (int) (es[i].getRode_type()*es[i].getDistance()*1000);
			boolean isfind1=false;
			boolean isfind2=false;
			boolean find1=false;
			boolean find2=false;
			for(int j=0;j<ps.length;j++) {
				if(es[i].getSrc().compareTo(ps[j])==0) {//匹配起点
					id1=ps[j].getID();
					isfind1=true;
				}
				if(es[i].getDst().compareTo(ps[j])==0) {//匹配终点
					id2=ps[j].getID();
					isfind2=true;
				}
				if(isfind1&&isfind2) {
					break;
				}
			}
			if(id1!=0&&id2!=0) {
				c[i]=new capacity(ID,id1,id2,capacity);
				c[i].setProject_id(e_event.Event_ID);
				sqls.add(c[i].getSaverSQL());
			}
		//另加
		for(int j=0;j<p.length;j++) {
			if(es[i].getSrc().compareTo(p[j])==0) {//匹配起点
				Rid1=p[j].getID();
				find1=true;
			}
			if(es[i].getDst().compareTo(p[j])==0) {//匹配终点
				Rid2=p[j].getID();
				find2=true;
			}
			if(find1&&find2) {
				break;
			}
		}
		if(Rid1!=0&&Rid2!=0) {
			r[i]=new evaRoute(evaRoute_id,Rid1,Rid2,capacity);
			r[i].setEvent_id(e_event.Event_ID);
			sqls1.add(r[i].getSaverSQL());
		}
		}
		//存储
		Query.addSql(sqls);
		Query.addSql(sqls1);
		Query.addSql("update project set percent = 100 where project_id="+Integer.toString(e_event.Event_ID));
		SchemeMake.semDB.release();//释放锁
	}
}
