package org.model.SystemManage;

import org.model.Query;

public class PersonalModifyDao {
	public static void personalmodify(User user){
		String sql = "UPDATE user SET password=\'"+user.getPassword()+"\' ,tel=\'"+user.getTel()+"\' ,  email=\'"+user.getEmail()+"\'"
						+ " where police_id=\'"+user.getUsername()+"\'";
		System.out.println(sql);
		Query.addSql(sql);
	}
}
