package org.model.SystemManage;

import java.util.ArrayList;

import org.model.Query;

public class ModifyDao {
	public static boolean modify(User user)
	{	
		ArrayList<String[]> ret = new ArrayList<String[]>();
		if(user.getUsername()!=null) {
			ret = Query.runSql(2,"select police_id,password from user where police_id=\'"+user.getUsername()+"\' ");
			if(ret.size()==0)
			{
				return false;
			}
			else {
				System.out.println(ret.get(0)[2]);
				return user.getPassword().equals(ret.get(0)[2]);
			}
		}
		else
			return false;
	}
}
