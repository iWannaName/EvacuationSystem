package org.model.SystemManage;

import java.util.ArrayList;

import org.model.Query;

public class RoleDao {
	public static void Role(User user)
	{
		ArrayList<String[]> ret = new ArrayList<String[]>();
		String temp="select user_id,role_id from user_role where user_id=\'"+user.getUsername()+"\'";
		System.out.print(temp);
		ret = Query.runSql(2,temp);
		String sql = "insert into role(role_id,role_status) "
				+ "values(\'"+ret.get(0)[2]+"\','1')";
		System.out.println(sql);
		Query.addSql(sql);
	}
}
