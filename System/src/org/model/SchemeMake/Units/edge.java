package org.model.SchemeMake.Units;

import java.util.TreeSet;

import org.model.Query;
import org.model.SchemeMake.PathApi.PathRequest;
import org.model.SchemeMake.PathApi.PathReturn;
import org.model.SchemeMake.PathApi.result.Route;
import org.model.SchemeMake.PathApi.result.step;

public class edge implements Comparable{
	private int ID;
	private String Road_name;
	private vertex src;
	private vertex dst;
	private int rode_type;
	private double distance;
	public String getRoad_name() {
		return Road_name;
	}
	public void setRoad_name(String road_name) {
		Road_name = road_name;
	}
	public void setID(int ID) {
		this.ID=ID;
	}
	public vertex getSrc() {
		return src;
	}
	public void setSrc(vertex src) {
		this.src = src;
	}
	public vertex getDst() {
		return dst;
	}
	public void setDst(vertex dst) {
		this.dst = dst;
	}
	public int getRode_type() {
		return rode_type;
	}
	public void setRode_type(int rode_type) {
		this.rode_type = rode_type;
	}
	public int getID() {
		return ID;
	}
	public edge(vertex src,vertex dst,int rode_type) {
		this.src=src;
		this.dst=dst;
		this.rode_type=rode_type;
	}
	@Override
	public int compareTo(Object o) {
		int com1 = src.compareTo(((edge)o).src);
		int com2 = dst.compareTo(((edge)o).dst);
		if(com1==0&&com2==0) {
			return 0;
		}
		
		String name1,name2;
		name1 = this.Road_name;
		name2 = ((edge)o).getRoad_name();
		if(name1==null||name2==null) {
			return src.compareTo(dst);
		}
		if(name1.equals(name2)) {
			if(com1==0) {
				return com2;
			}
			return com2;
		}
		else return(name1.compareTo(name2));
	}
	public double getLen() {
		return vertex.getDistance(src.x, src.y, dst.x, dst.y);
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public edge[] getTrajectory() {
		PathRequest pr = new PathRequest(src,dst);
		String JSON = pr.CulculatePath_Drive();
		PathReturn ret= new PathReturn(JSON);
		Route [] rts = ret.getRoutes();
		TreeSet<edge> ts = new TreeSet<edge>();
		for(int i=1;i<rts.length;i++) {
			step[] s = rts[i].getSteps();
			for(int j=1;j<s.length;j++) {
				edge [] es = s[i].getTrajectory();
				for(int i1 =0;i1<es.length;i1++) {		
					ts.add(es[i1]);
				}
			}
		}
		return ts.toArray(new edge[ts.size()]);
	}

	


	
}
