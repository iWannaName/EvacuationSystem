package org.model.SchemeMake.PathApi;

import org.model.settings;
import org.model.SchemeMake.PathApi.result.Route;
import org.model.SchemeMake.Units.*;
public class PathReturn {
	public PathReturn(String JSON) {
		if(settings.DEBUG_MODE) {
			System.out.println("[ApiReturn debug]");
			//System.out.println(JSON);
			
		}
		status = Integer.parseInt(settings.findAttribute(JSON, "status"));

		message = settings.findAttribute(JSON, "message");		
		if(status!=0) {
			System.out.print("Wrong status:");
			System.out.print(status);
			System.out.print(":");
			System.out.println(message);
			return;
		}
		type = Integer.parseInt(settings.findAttribute(JSON, "type"));
		String result = settings.findAttribute(JSON, "result");
		total = Integer.parseInt(settings.findAttribute(result, "total"));
		restriction = settings.findAttribute(result, "restriction");
		String routes_ret = settings.findAttribute(result, "routes");
		int len = settings.getJSONLen(routes_ret);
		routes = new Route[len+1];
		for(int i=1;i<=len;i++) {
			routes[i]=new Route(settings.getByIndex(routes_ret, i));
		}
	}

	private int status;
	private String message;
	private int type;

	private Route[] routes;
	private String restriction;
	private int total;

	public vertex[] Points() {
		return null;
		
	}
	public Route[] getRoutes() {
		return routes;
		
	}
	
}
