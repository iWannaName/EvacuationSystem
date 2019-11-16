package org.model.SystemManage;

import java.util.ArrayList;

import org.model.Query;

public class PersonalAreaDao {
	public static ArrayList<String[]> personal_inf(String username){
		ArrayList<String[]> ret = Query.runSql(10,"select * from user where police_id=\'"+username+"\'");
		System.out.println(ret.get(0)[2]);
		return ret;
	}
}
