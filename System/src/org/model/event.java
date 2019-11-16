package org.model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import org.model.SchemeMake.Units.vertex;

public class event {

	// insert into e_event
	// (event_id,e_name,eventType,evacuationPoint,refugePoint,evacuatedNum,e_status,recorder,DrillTime)
	public int Event_ID;
	String EventName = null;
	String EventType = null;
	public String origin = null;
	public String destination = null;
	String Crowd = null;
	String EventStatus = null;
	String policeId = null;
	String Time = null;

	vertex src = null;
	vertex dst = null;

	public event(String EventName, String EventType, String origin, String destination, String Crowd,
			String EventStatus, String policeId, String Time, Semaphore semEvent) {
		this.EventName = EventName;
		this.EventType = EventType;
		this.origin = origin;
		this.destination = destination;
		this.Crowd = Crowd;
		this.EventStatus = EventStatus;
		this.policeId = policeId;
		this.Time = Time;

		// 在Event表中占位
		semEvent.acquireUninterruptibly();
		Event_ID = 1;
		ArrayList<String[]> ret;
		ret = Query.runSql(1, "Select max(event_id) from event");
		if (ret != null && ret.size() == 1) {
			String currPIDmax = ret.get(0)[1];
			if(currPIDmax!=null&&!currPIDmax.equals("null")) {
				Event_ID = Integer.parseInt(currPIDmax) + 1;
			}
		}
		String sql = "insert into event (event_id) values";
		sql += "('" + Integer.toString(Event_ID) + "')";

		Query.addSql(sql);
		// 在project表中占位
		sql = "insert into project (project_id) values";
		sql += "('" + Integer.toString(Event_ID) + "')";
		Query.addSql(sql);
		semEvent.release();
	}

	public event(int Event_id) {

		String sql ="select * from event where event_id="+Integer.toString(Event_id);
		ArrayList<String[]> ret = Query.runSql(9, sql);
		if(ret==null||ret.size()==0) {
			System.out.println("[Event] Event id does not exist");
			return;
		}
		String[]ans = ret.get(0);
		this.EventName = ans[2];
		this.EventType = ans[3];
		this.origin = ans[4];
		this.destination = ans[5];
		this.Crowd = ans[6];
		this.EventStatus = ans[7];
		this.policeId = ans[8];
		this.Time = ans[9];
	}

	public void setStart(vertex src) {
		this.src = src;
	}

	public vertex getStart() {
		return this.src;
	}

	public void setEnd(vertex dst) {
		this.dst = dst;
	}

	public vertex getEnd() {
		return this.dst;
	}

	public int getEvaluted() {
		return Integer.parseInt(EventStatus);
	}

	public void setEvaluted(int status) {
		this.EventStatus = Integer.toString(status);
	}

	public int getAttackerNum() {
		return Integer.parseInt(Crowd);
	}

	public void setAttackerNum(int status) {
		this.Crowd = Integer.toString(status);
	}

	public void save() {
		if (src == null || dst == null) {
			System.out.println("[Event] Src and Dst is needed");
			return;
		}
		String sql = "update event set ";
		sql += "e_name ='" + EventName + "'" + ",";
		sql += "eventType='" + EventType + "'" + ",";
		sql += "evacuationPoint='" + src.getID() + "'" + ",";
		sql += "refugePoint='" + dst.getID() + "'" + ",";
		sql += "evacuatedNum='" + Crowd + "'" + ",";
		sql += "e_status='0'" + ",";
		sql += "recorder='" + policeId + "'" + ",";
		sql += "DrillTime='" + Time + "' ";
		sql += " where event_id=" + Integer.toString(this.Event_ID);
		System.out.println("[Event] New Event Amlified");
		//System.out.println(sql);
		Query.addSql(sql);
		sql = "update project set project_name=";
		sql += "'"+EventName+"'";
		sql +=" where project_id="+Integer.toString(this.Event_ID);
		
		//System.out.println(sql);
		Query.addSql(sql);
	}

	public static int getPosition(Semaphore semEvent) {
		int Event_ID = 1;
		// 在Event表中占位
		semEvent.acquireUninterruptibly();
		ArrayList<String[]> ret;
		ret = Query.runSql(1, "Select max(event_id) from event");
		if (ret != null && ret.size() == 1) {
			String currPIDmax = ret.get(0)[0];
			Event_ID = Integer.parseInt(currPIDmax) + 1;
		}
		String sql = "insert into event (event_id) values";
		sql += "('" + Integer.toString(Event_ID) + "')";
		System.out.println(sql);

		// 在project表中占位
		sql = "insert into project (project_id) values";
		sql += "('" + Integer.toString(Event_ID) + "')";
		Query.addSql(sql);

		semEvent.release();
		return Event_ID;
	}

	public void viewEvent() {
		System.out.print("[Event] ");
		System.out.print(Event_ID);
		System.out.print(":");
		System.out.print(EventName);
		System.out.print(" ");
		System.out.print(EventType);
		System.out.print(" ");
		System.out.print(origin);
		System.out.print(" ");
		System.out.print(destination);
		System.out.print(" ");
		System.out.print(Crowd);
		System.out.print(" ");
		System.out.print(EventStatus);
		System.out.print(" ");
		System.out.print(policeId);
		System.out.print(" ");
		System.out.println(Time);
		System.out.print(" ");
	}
}
