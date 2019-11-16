package org.model.SchemeMake.PathApi.result;
import org.model.settings;
import org.model.SchemeMake.PathApi.*;
public class Condition {
	private int status;
	private int geo_cnt;
	private float distance;
	public Condition(String json) {
		if(settings.DEBUG_MODE) {
			System.out.print("[condition Debug]");
			System.out.println(json);
		}
		status = Integer.parseInt(settings.findAttribute(json, "status"));
		geo_cnt = Integer.parseInt(settings.findAttribute(json, "geo_cnt"));
		distance = Float.parseFloat(settings.findAttribute(json, "distance"));
	}
}
