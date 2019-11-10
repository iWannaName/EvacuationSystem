package org.model.SchemeMake.Units;

import java.text.DecimalFormat;

import org.model.Query;
import org.model.settings;


public class vertex implements Comparable{
	public double x, y;//x经度 y纬度
	private int ID;
	private String Road_name;
	public int getProject_id() {
		return Project_id;
	}
	public void setProject_id(int project_id) {
		Project_id = project_id;
	}
	private int Project_id;
	public String getRoad_name() {
		return Road_name;
	}
	public void setRoad_name(String road_name) {
		Road_name = road_name;
	}
	public void setID(int ID) {
		this.ID=ID;
	}
	public int getID() {
		return ID;
	}

	
	public vertex(double x, double y) {
		this.x = 0;
		this.y = 0;
		this.x = x;
		this.y = y;
	}
	


	vertex() {
		this.x = 0;
		this.y = 0;
	}

	vertex(vertex a) {
		this.x = a.x;
		this.y = a.y;
	}

	public vertex(String json) {
		if (settings.DEBUG_MODE) {
			System.out.print("[point debug - json]");
			System.out.print(json);
		}
		String s1;
		String s2;
		if (json.charAt(0) == '{') {
			s1 = settings.findAttribute(json, "lng");
			s2 = settings.findAttribute(json, "lat");
		} else {
			int pos = json.indexOf(',');
			s1 = json.substring(0, pos);
			s2 = json.substring(pos + 1, json.length());
		}
		x = Float.parseFloat(s1);
		y = Float.parseFloat(s2);
		if (settings.DEBUG_MODE) {
			System.out.print(x);
			System.out.print(',');
			System.out.println(y);
		}
	}

	public String toString() {
		DecimalFormat df = new DecimalFormat("0.000000");
		String ans = df.format(x) + "," + df.format(y);
		return ans;
	}
	public String toString2() {
		DecimalFormat df = new DecimalFormat("0.000000");
		String ans = df.format(y) + "," + df.format(x);
		return ans;
	}
	public vertex clone(vertex s) {
		vertex p = new vertex(s.x,s.y);
		return p;
	}
	public vertex clone() {
		vertex p = new vertex(this.x,this.y);
		return p;
	}

	@Override
	public int compareTo(Object o) {
		double x1 = this.x;
		double y1 = this.y;
		double x2 = ((vertex)o).x;
		double y2 = ((vertex)o).y;
		
		double distance = getDistance(this.x,this.y,((vertex)o).x,((vertex)o).y);
		if(distance<50) {
			return 0;
		}
		long lx1 = (int)(x1*1000000);
		long ly1 = (int)(y1*1000000);
		long lx2 = (int)(x2*1000000);
		long ly2 = (int)(y2*1000000);
		return (int) (100*(lx1-lx2)+(ly1-ly2));
	}
	
	private static double EARTH_RADIUS = 6378.137;//地球半径
	private static double rad(double d)
	{
	   return d * Math.PI / 180.0;
	}
    public static double getDistance(double lat1, double lng1, double lat2,double lng2) {  
        double radLat1 = rad(lat1);  
        double radLat2 = rad(lat2);  
        double a = radLat1 - radLat2;  
        double b = rad(lng1) - rad(lng2);  
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
                + Math.cos(radLat1) * Math.cos(radLat2)  
                * Math.pow(Math.sin(b / 2), 2)));  
        s = s * EARTH_RADIUS;  
        s = Math.round(s * 10000d) / 10000d;  
        s = s*1000;  
        return s;
    }
    public String getSaverSQL() {
		String ans = "insert into point (p_id,x_coordinate,y_coordinate,event_id) values (";
		ans+=Integer.toString(ID)+",";
		ans+=this.toString()+",";
		ans+=Integer.toString(Project_id)+")";
		return ans;
    }
	public void save() {
		Query.addSql(getSaverSQL());
	}
}

