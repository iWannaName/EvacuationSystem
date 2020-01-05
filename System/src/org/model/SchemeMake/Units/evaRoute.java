package org.model.SchemeMake.Units;

import org.model.Query;

public class evaRoute {
	public int evaRoute_id;
	public int Rid1;
	public int Rid2;
	public int capacity;
	public int Event_id;
	
	public int getEvent_id() {
		return Event_id;
	}
	public void setEvent_id(int event_id) {
		Event_id = event_id;
	}
	public evaRoute(int evaRoute_id, int Rid1, int Rid2, int capacity) {
		this.evaRoute_id=evaRoute_id;
		this.Rid1=Rid1;
		this.Rid2=Rid2;
		this.capacity=capacity;
	}
	public evaRoute(int evaRoute_id, int Rid1, int Rid2, int capacity,int Event_id) {
		this.evaRoute_id=evaRoute_id;
		this.Rid1=Rid1;
		this.Rid2=Rid2;
		this.capacity=capacity;
		this.Event_id=Event_id;
	}
	public String getSaverSQL() {
		String ans = "insert into evaRoute (evaRoute_id,Rid1,Rid2,capacity,event_id) values (";
		ans+=Integer.toString(evaRoute_id)+",";
		ans+=Integer.toString(Rid1)+",";
		ans+=Integer.toString(Rid2)+",";
		ans+=Integer.toString(capacity)+",";
		ans+=Integer.toString(Event_id)+")";
		return ans;
	}
	public void save() {
		Query.addSql(getSaverSQL());
	}
	
	public int compareTo(Object o) {
		if(this.evaRoute_id==((evaRoute)o).evaRoute_id)
			return 0;
		if(this.Rid1==((evaRoute)o).Rid1&&this.Rid2==((evaRoute)o).Rid2)
			return 0;
		if(this.Rid1==((evaRoute)o).Rid1)
			return this.Rid2-((evaRoute)o).Rid2;
		return this.Rid1-((evaRoute)o).Rid1;
	}

}
