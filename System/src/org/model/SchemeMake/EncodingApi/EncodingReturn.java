package org.model.SchemeMake.EncodingApi;

import org.model.settings;
import org.model.SchemeMake.Units.vertex;

public class EncodingReturn {
	private String status;
	private vertex location;
	private String precise;
	private String confidence;
	private String comprehension;
	private String level;
	public EncodingReturn(String JSON) {
		status=settings.findAttribute(JSON, "status");
		precise=settings.findAttribute(settings.findAttribute(JSON,"result"), "precise");
		confidence=settings.findAttribute(settings.findAttribute(JSON,"result"), "confidence");
		comprehension=settings.findAttribute(settings.findAttribute(JSON,"result"), "comprehension");
		level=settings.findAttribute(settings.findAttribute(JSON,"result"), "level");
		location=new vertex(settings.findAttribute(settings.findAttribute(JSON,"result"), "location"));
	}
	public vertex getLocation() {
		return location;
	}
}
