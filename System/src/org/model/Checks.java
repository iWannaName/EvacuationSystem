package org.model;

import javax.servlet.http.Cookie;

public class Checks {
	public static boolean checkNULL(String Attribute) {
		if(Attribute==null){
			return true;
		}
		return false;
	}
	//when illeagal returns true
	public static boolean checkEmpty(String EventName) {
		if(EventName.equals("")){
			return true;
		}
		return false;
	}
	public static int SchemeMakeChecker(String mode, String origin,String Destination) {
		if(mode.equals("坐标模式")) {
			int slide1 = origin.indexOf(',');
			int slide2 = Destination.indexOf(',');
			if(slide1<0||slide2<0)
				return 1;
			float x1,x2,y1,y2;
			try {
				x1 = Float.parseFloat(origin.substring(0,slide1));
				x2 = Float.parseFloat(origin.substring(0,slide2));
				y1 = Float.parseFloat(origin.substring(slide1+1,origin.length()));
				y2 = Float.parseFloat(origin.substring(slide2+1,Destination.length()));
			}
			catch(NumberFormatException e) {
				return 2;
			}
			//中国经度范围73-136  （前者下取整和上取整）
			//中国维度范围3-53
			if(x1<73||x2<73||x1>136||x2>136||y1<3||y1>53||y2<3||y2>53) {
				return 3;
			}
		}
		return 0;
		
	}
	public static boolean haveRights(Cookie[] cookies,int thispower) {
		String power=null;
		String username =null;
		String status =null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username")) {
				username = cookie.getValue();
			}
			if (cookie.getName().equals("power")) {
				power = cookie.getValue();
			}
			if (cookie.getName().equals("status")) {
				status = cookie.getValue();
			}
		}
		if(power==null||username ==null||status ==null) {
			return false;
		}
		int IntPow = Integer.parseInt(power);
		boolean ret  = (thispower&IntPow)>0;
		return ret;
	}
}
