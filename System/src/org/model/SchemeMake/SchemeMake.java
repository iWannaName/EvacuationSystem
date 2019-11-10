package org.model.SchemeMake;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import org.model.BaiduMap;
import org.model.SchemeMake.EncodingApi.EncodingRequest;
import org.model.SchemeMake.EncodingApi.EncodingReturn;
import org.model.SchemeMake.Units.capacity;
import org.model.SchemeMake.Units.edge;
import org.model.SchemeMake.Units.vertex;
import org.model.Query;
import org.model.event;
public class SchemeMake extends Thread{
	event e_event;
	String mode;
	final static Semaphore semDB = new Semaphore(100);
	public SchemeMake(
			String mode,
			event e_event) {
		this.e_event=e_event;
		this.mode=mode;
	}
	@Override
	public void run() {
		//将String转换为vertex类型
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
		System.out.println(src);
		System.out.println(dst);
		ArrayList<String> sqls = new ArrayList<String>();
		//获得锁
		SchemeMake.semDB.acquireUninterruptibly();
		//获得目前数据库中最大的点ID和边ID
		//如果数据库为空则最大ID为1 否则查询最大ID 本样例中的0ID就是数据库中最大ID+1
		int PID_0 = 1;
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
		//获取点集合
		src.setID(PID_0++);
		dst.setID(PID_0++);
		e_event.setStart(src);
		e_event.setEnd(dst);
		System.out.println("[SchemeMake]"+e_event.getStart());
		System.out.println("[SchemeMake]"+e_event.getEnd());
		src.setProject_id(e_event.Event_ID);
		dst.setProject_id(e_event.Event_ID);
		//保存事件
		src.save();
		dst.save();
		e_event.save();
		//获取十字交叉口
		vertex [] ps = BaiduMap.getAllJunction(src, dst,e_event.Event_ID);
		for(int i=0;i<ps.length;i++) {
			ps[i].setID(PID_0+i);
			ps[i].setProject_id(e_event.Event_ID);
			sqls.add(ps[i].getSaverSQL());
		}
		//建立点边映射集合（匹配起点和重点在数据库中对应的ID），获取SQLs
		edge [] es = BaiduMap.es.toArray(new edge[BaiduMap.es.size()]);
		capacity [] c = new capacity[es.length];
		for(int i=0;i<es.length;i++) {
			int ID = EID_0+i;
			int id1 = 0;
			int id2 = 0;
			//道路容量=道路类型与距离得乘积。道路容量系数1000增加精度
			int capacity = (int) (es[i].getRode_type()*es[i].getDistance()*1000);
			boolean isfind1=false;
			boolean isfind2=false;
			for(int j=0;j<ps.length;j++) {
				if(es[i].getSrc().compareTo(ps[j])==0) {
					id1=ps[j].getID();
					isfind1=true;
				}
				if(es[i].getDst().compareTo(ps[j])==0) {
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
		}
		//存储
		Query.addSql(sqls);
		Query.addSql("update project set percent = 100 where project_id="+Integer.toString(e_event.Event_ID));
		SchemeMake.semDB.release();//释放锁
	}
}
