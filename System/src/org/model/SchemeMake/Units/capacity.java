package org.model.SchemeMake.Units;

import org.model.Query;

public class capacity implements Comparable{
	public int id1;
	public int id2;
	public int capacity;
	public int ID;
	public int Project_id;
	public int getProject_id() {
		return Project_id;
	}
	public void setProject_id(int project_id) {
		Project_id = project_id;
	}
	public capacity(int ID, int id1, int id2, int capacity) {
		this.ID=ID;
		this.id1=id1;
		this.id2=id2;
		this.capacity=capacity;
	}
	public capacity(int ID, int id1, int id2, int capacity,int Project_id) {
		this.ID=ID;
		this.id1=id1;
		this.id2=id2;
		this.capacity=capacity;
		this.Project_id=Project_id;
	}
	public String getSaverSQL() {
		String ans = "insert into capacity (e_id,id1,id2,capacity,event_id) values (";
		ans+=Integer.toString(ID)+",";
		ans+=Integer.toString(id1)+",";
		ans+=Integer.toString(id2)+",";
		ans+=Integer.toString(capacity)+",";
		ans+=Integer.toString(Project_id)+")";
		return ans;
	}
	public void save() {
		Query.addSql(getSaverSQL());
	}
	@Override
	public int compareTo(Object o) {
		if(this.ID==((capacity)o).ID)
			return 0;
		if(this.id1==((capacity)o).id1&&this.id2==((capacity)o).id2)
			return 0;
		if(this.id1==((capacity)o).id1)
			return this.id2-((capacity)o).id2;
		return this.id1-((capacity)o).id1;
	}
}
