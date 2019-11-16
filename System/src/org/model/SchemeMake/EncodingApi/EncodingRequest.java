package org.model.SchemeMake.EncodingApi;

import org.model.settings;
import org.model.SchemeMake.PathApi.PathReturn;
import org.model.SchemeMake.Units.vertex;
import org.model.SchemeMake.httpAPI.HttpRequest;
import org.model.SchemeMake.httpAPI.config;

public class EncodingRequest {
	private static String PathURL="http://api.map.baidu.com/geocoding/v3/";
	private String address;
	private static String output = "json";
	private static String params;
	public EncodingRequest(String address){
		this.address = address;
	}
	public String SendRequest() {
		params = "address="+address+"&"+"ak="+config.AK+"&"+"output="+output;
		return settings.getJSON(HttpRequest.sendGet(PathURL, params));
	}
	public static String SendRequest(String address) {
		params = "address="+address+"&"+"ak="+config.AK+"&"+"output="+output;
		return settings.getJSON(HttpRequest.sendGet(PathURL, params));
	}
}
