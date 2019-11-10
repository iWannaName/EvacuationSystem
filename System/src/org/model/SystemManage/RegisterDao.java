package org.model.SystemManage;

import java.util.ArrayList;

import org.model.Query;

public class RegisterDao {
	public static boolean verifyU(User user)
	{
		ArrayList<String[]> retU = Query.runSql(1,"select police_id from user"
				+ " where police_id=\'"+user.getUsername()+"\'");
		if(retU.size()==0)
		{
			return true;
		}
		return false;
	}
	public static boolean verifyI(User user)
	{
		ArrayList<String[]> retI = Query.runSql(1,"select id from user"
				+ " where id=\'"+user.getId()+"\'");
		if(retI.size()==0)
		{
			return true;
		}
		return false;
	}
	public static boolean verifyT(User user)
	{
		ArrayList<String[]> retT = Query.runSql(1,"select tel from user"
				+ " where tel=\'"+user.getTel()+"\'");
		if(retT.size()==0)
		{
			return true;
		}
		return false;
	}
	public static boolean verifyE(User user)
	{
		ArrayList<String[]> retE = Query.runSql(1,"select email from user"
				+ " where email=\'"+user.getEmail()+"\'");
		if(retE.size()==0)
		{
			return true;
		}
		return false;
	}
	public static void register(User user)
	{
		String sql = "insert into user(police_id,password,name,unit,position,id,birthdate,jointime,tel,email) "
				+ "values(\'"+user.getUsername()+"\',\'"+user.getPassword()+"\',\'"+user.getRealname()+"\',\'"+user.getUnit()+"\',\'"
				+user.getPosition()+"\',\'"+user.getId()+"\',\'"+user.getBirthdate()+"\',\'"+user.getJointime()
				+"\',\'"+user.getTel()+"\',\'"+user.getEmail()+"\')";
		System.out.println(sql);
		Query.addSql(sql);
	}
}
