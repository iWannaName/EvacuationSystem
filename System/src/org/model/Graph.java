package org.model;

import java.util.ArrayList;
import java.util.TreeSet;

import org.model.SchemeMake.Units.capacity;

public class Graph {
	public  TreeSet<capacity> capacity;//边集合
	public  TreeSet<Integer> vertex_id;//点ID集合
	public int vertexNum;
	public int edgeLength;
	
	public void addEdge(capacity c) {
		capacity.add(c);
		vertex_id.add(c.id1);
		vertex_id.add(c.id2);
		vertexNum=vertex_id.size();
		edgeLength=capacity.size();
	}

	public Graph() {
		capacity = new TreeSet<capacity>();
		vertex_id = new TreeSet<Integer>();
		vertexNum=vertex_id.size();
		edgeLength=capacity.size();
	}
	
	public void initialGrap(int Project_id) {
		Graph g = this;
		ArrayList<String[]> ret = new ArrayList<String[]>();
		String sql = "select e_id,id1,id2,capacity from capacity where Event_id=" + Integer.toString(Project_id);
		ret = Query.runSql(4, sql);
		if (ret == null || ret.size() == 0) {
			System.out.println("[displayer] Wrong request : Event_ID=" + Integer.toString(Project_id));
		} else {
			for (int i = 1; i < ret.size(); i++) {
				String[] tmp = ret.get(i);
				int e_id = Integer.parseInt(tmp[1]);
				int id1 = Integer.parseInt(tmp[2]);
				int id2 = Integer.parseInt(tmp[3]);
				int capacity = Integer.parseInt(tmp[4]);
				capacity c = new capacity(e_id, id1, id2, capacity);
				g.addEdge(c);
			}
		}
	}
	
	public static Graph getGrap(int Project_id) {
		Graph g = new Graph();
		ArrayList<String[]> ret = new ArrayList<String[]>();
		String sql = "select e_id,id1,id2,capacity from capacity where Event_id=" + Integer.toString(Project_id);
		ret = Query.runSql(4, sql);
		if (ret == null || ret.size() == 0) {
			System.out.println("[displayer] Wrong request : Event_ID=" + Integer.toString(Project_id));
		} else {
			for (int i = 1; i < ret.size(); i++) {
				String[] tmp = ret.get(i);
				int e_id = Integer.parseInt(tmp[1]);
				int id1 = Integer.parseInt(tmp[2]);
				int id2 = Integer.parseInt(tmp[3]);
				int capacity = Integer.parseInt(tmp[4]);
				capacity c = new capacity(e_id, id1, id2, capacity);
				g.addEdge(c);
			}
		}
		return g;
	}
	
}
