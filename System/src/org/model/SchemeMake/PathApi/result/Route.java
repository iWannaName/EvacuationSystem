package org.model.SchemeMake.PathApi.result;
import org.model.SchemeMake.Units.edge;
import java.util.TreeSet;

import org.model.BaiduMap;
import org.model.settings;
import org.model.SchemeMake.Units.*;
import org.model.SchemeMake.Units.*;
public class Route {
	private vertex src;
	private vertex dst;
	private String tag;
	private Condition[] traffic_condition;
	private int distance = 0;
	private int duration = 0;
	private int taxi_fee = 0;
	private int toll = 0;
	private int toll_distance = 0;
	private step[] steps;

	public Route(String json) {
		if (settings.DEBUG_MODE) {
			System.out.print("[Route Debug]");
			System.out.println(json);
		}
		String origin = settings.findAttribute(json, "origin");
		String destination = settings.findAttribute(json, "destination");
		tag = settings.findAttribute(json, "tag");
		String Steps = settings.findAttribute(json, "steps");
		int len = settings.getJSONLen(Steps);
		steps = new step[len + 1];
		for (int i = 1; i <= len; i++) {
			steps[i] = new step(settings.getByIndex(Steps, i));
		}
		distance = Integer.parseInt(settings.findAttribute(json, "distance"));
		duration = Integer.parseInt(settings.findAttribute(json, "duration"));
		taxi_fee = Integer.parseInt(settings.findAttribute(json, "taxi_fee"));
		toll = Integer.parseInt(settings.findAttribute(json, "toll"));
		toll_distance = Integer.parseInt(settings.findAttribute(json, "toll_distance"));
	}

	public int pointNum() {
		int ret = 0;
		for (int i = 1; i < steps.length; i++) {
			ret += steps[i].pointNum();
		}
		return ret;
	}

	public vertex[] getPoint() {
		int len = 1;
		vertex[] ret = null;
		for (int i = 1; i < steps.length; i++) {
			len += steps[i].getPoint().length;
		}
		ret = new vertex[len];
		int index=1;
		for (int i = 1; i < steps.length; i++) {
			vertex[] tmp = steps[i].getPoint();
			for(int j = 1;j<tmp.length;j++) {
				if(tmp[j]!=null)
					ret[index++]=tmp[j];
			}
		}
		vertex[] tmp = new vertex[index];
		for(int i=1;i<index;i++) {
			tmp[i]=ret[i].clone();
		}
		return tmp;
	}

	public step[] getSteps() {
		return steps;
	}

	public vertex[] getJunctions() {
		int len = steps.length;
		vertex[] ret = new vertex[len - 1];
		for (int i = 1; i < len - 1; i++) {
			if (settings.DEBUG_MODE) {
				System.out.println(steps[i].getRoadName());
			}
			int len1 = steps[i].getPath().getPoints().length;
			vertex tmp = steps[i].getPath().getPoints()[len1 - 1];
			ret[i] = new vertex(tmp.x, tmp.y);
		}
		return ret;
	}
	public vertex[] getJunctions(TreeSet<edge> es) {
		int len = steps.length;
		vertex[] ret = new vertex[len - 1];
		for (int i = 1; i < len - 1; i++) {
			if (settings.DEBUG_MODE) {
				System.out.println(steps[i].getRoadName());
			}
			int len1 = steps[i].getPath().getPoints().length;
			vertex tmp = steps[i].getPath().getPoints()[len1 - 1];
			tmp.setRoad_name(steps[i].getRoadName());
			ret[i] = new vertex(tmp.x, tmp.y);
			ret[i].setRoad_name(steps[i].getRoadName());
			if(i>1) {
				if(ret[i-1]==null||ret[i]==null) {
					continue;
				}
				int road_type = steps[i-1].getRoadType();
				String road_name = steps[i-1].getRoadName();
				edge e = new edge(ret[i-1],ret[i],road_type);
				e.setRoad_name(road_name);
				e.setDistance(steps[i].getDitance());
				es.add(e);
			}
		}
		return ret;
	}
}
