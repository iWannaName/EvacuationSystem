package org.model.SchemeMake.PathApi.result;

import java.util.ArrayList;
import java.util.TreeSet;

import org.model.settings;
import org.model.SchemeMake.Units.*;
public class step {
	private int leg_index;
	private int direction;
	private float distance;
	private String road_name;
	private int road_type;
	private int toll;
	private float toll_distance;
	private String toll_gate_name;
	private vertex toll_gate_location;
	private vertex start_location;
	private vertex end_location;
	private Condition[] traffic_condition;
	public Path path;
	
	public step(String json) {
		if(settings.DEBUG_MODE) {
			System.out.print("[step debug]");
			System.out.println(json);
		}
		String condition = settings.findAttribute(json, "traffic_condition");
		int len = settings.getJSONLen(condition);
		traffic_condition = new Condition[len+1];
		for(int i=1;i<=len;i++) {
			traffic_condition[i]=new Condition(settings.getByIndex(condition, i));
		}
		leg_index = Integer.parseInt(settings.findAttribute(json, "leg_index"));
		direction = Integer.parseInt(settings.findAttribute(json, "direction"));
		distance = Float.parseFloat(settings.findAttribute(json, "distance"));
		road_type = Integer.parseInt(settings.findAttribute(json, "road_type"));
		toll = Integer.parseInt(settings.findAttribute(json, "toll"));
		toll_distance = Float.parseFloat(settings.findAttribute(json, "toll_distance"));
		start_location = new vertex(settings.findAttribute(json, "start_location"));
		end_location = new vertex(settings.findAttribute(json, "start_location"));
		String strPath = settings.findAttribute(json, "path");
		road_name = settings.findAttribute(json, "road_name");
		path = new Path(strPath);
	}
	public int pointNum() {
		return path.pointNum();
	}
	public Path getPath() {
		return path;
	}
	public String getRoadName() {
		return road_name;
	}
	public int getRoadType() {
		return road_type;
	}
	public float getDitance() {
		return distance;
	}
	public vertex[] getPoint() {
		vertex[] ret = path.getPoints();
		if(settings.DEBUG_MODE) {
			for(int i=1;i<ret.length;i++) {
				System.out.print("[point test in step]");
				System.out.println(ret[i].toString());
			}
		}
		return ret;
	}
	public edge[] getTrajectory() {
		ArrayList<edge> ts = new ArrayList<edge>();
		vertex [] ps = path.getPoints();
		for(int i=2;i<ps.length;i++) {
			double x=ps[i-1].x;
			double y=ps[i-1].y;
			ps[i-1].x=Math.min(x,y);
			ps[i-1].y=Math.max(x,y);
			x=ps[i].x;
			y=ps[i].y;
			ps[i].x=Math.min(x,y);
			ps[i].y=Math.max(x,y);
			edge e = new edge(ps[i-1],ps[i],this.road_type);
			ts.add(e);
		}
		return ts.toArray(new edge[ts.size()]);
	}
	
}
