package org.model.SystemManage;

import java.util.ArrayList;

import org.model.Query;

public class ForgetDao {
	public static String verify(User user)
	{	
		String message=null;
		ArrayList<String[]> ret = Query.runSql(4,"select police_id,id,tel,email from user"
				+ " where police_id=\'"+user.getUsername()+"\'");
		if(ret.size()==0)
		{	
			message="用户名不存在!";
			System.out.println(message);
			return message;
		}
		else if(!ret.get(0)[2].equals(user.getId())){
			message="身份证号错误!";
			System.out.println(message);
			return message;
		}
		else if(!ret.get(0)[3].equals(user.getTel())){
			message="手机号码错误!";
			System.out.println(message);
			return message;
		}
		else if(!ret.get(0)[4].equals(user.getEmail())){
			message="邮箱错误!";
			System.out.println(message);
			return message;
		}
		else {
			message="2";
			System.out.println(message);
			return message;
		}
	}
}
