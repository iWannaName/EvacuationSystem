
package org.model;

import java.sql.*;
import java.util.ArrayList;

public class Query {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/evacuation?serverTimezone=UTC";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
    /***************************************************************************/
    /***************************************************************************/
    //读取数据库限定列的表信息
    public static ArrayList<String[]> runSql(int sql1,String sql)  {
		// TODO Auto-generated method stub
		int columnCount = sql1;
		ArrayList<String[]> ret = new ArrayList<String[]>();
		// TODO Auto-generated method stub
		try{
			
			Connection conn = null;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //调用Class.forName()方法加载驱动程序 
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");
            ResultSet rs = stmt.executeQuery(sql);//创建数据对象
                while (rs.next()){
                	String[] line = new String [columnCount+1];
	                for(int i=1;i<line.length;i++){
	                	if(rs.getString(i)==null) {
	                		line[i]="null";
	                	}
	                	else {
		                    line[i]=new String(rs.getString(i));
	                	}
	                 }
	                ret.add(line);
                }
                rs.close();
                stmt.close();
                conn.close();
                return ret;
            }catch(Exception e)
            {
                e.printStackTrace();
            }
		return null;
	}
    //读取数据库任意列整表信息
	public static ArrayList<String[]> runSql(String sql1,String sql)  {
		int columnCount = 0;
		ArrayList<String[]> ret = new ArrayList<String[]>();
		try{
			Connection conn = null;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //调用Class.forName()方法加载驱动程序 
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");            
            ResultSet rs = stmt.executeQuery(sql1);//创建数据对象
            /*****************************************************/
                while (rs.next()){
                	columnCount+=rs.getInt(1);
                }
                rs.close();
                stmt.close();
                conn.close();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
		try{
			Connection conn = null;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //调用Class.forName()方法加载驱动程序 
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");
            
            ResultSet rs = stmt.executeQuery(sql);//创建数据对象
            /*****************************************************/
            /*****************************************************/
                while (rs.next()){
                	String[] line = new String [columnCount];
	                for(int i=1;i<line.length;i++){
	                    line[i]=new String(rs.getString(i));
	                 }
	                ret.add(line);
                }
                rs.close();
                stmt.close();
                conn.close();
                return ret;
            }catch(Exception e)
            {
            	//if(settings.DEBUG_MODE)
            		e.printStackTrace();
            	//else
            		System.out.println("[MySQL] Warning or Wrong");
            }
		return null;
	}

public static void addSql(String sql)  {
	try{
		System.out.println("测试是否执行*********************");
		ArrayList<String[]> ret = new ArrayList<String[]>();
		Connection conn = null;
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
        //调用Class.forName()方法加载驱动程序 
        Statement stmt = conn.createStatement(); //创建Statement对象
        System.out.println("成功连接到数据库！");
        stmt.execute(sql);
        }catch(Exception e)
        {
        	//if(settings.DEBUG_MODE)
        		e.printStackTrace();
        	//else
        		System.out.println("[MySQL] Warning or Wrong");
        }
	return;
}

public static void addSql(ArrayList<String> sql)  {//批量存储：速度好慢啊
	try{
		ArrayList<String[]> ret = new ArrayList<String[]>();
		Connection conn = null;
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
        //调用Class.forName()方法加载驱动程序 
        Statement stmt = conn.createStatement(); //创建Statement对象
        System.out.println("[Saver] success to connect with database");
        int total = sql.size();
        System.out.println("[saver] total:"+Integer.toString(total));
        for(int i=0;i<total;i++) {
        	if(i%100==0) {
                System.out.print("[saver] ");
        		System.out.print(i);
        		System.out.print('\\');
        		System.out.println(total);
        	}
        	String sql1 = sql.get(i);

        	//System.out.println(sql1);
        	if(sql1!=null) {
    			stmt.execute(sql1);
        	}
        }
        System.out.println("[Saver] finished");
        }catch(Exception e)
        {
        	//if(settings.DEBUG_MODE)
        		e.printStackTrace();
        	//else
        		System.out.println("[MySQL] Warning or Wrong");
        }
	return;
}
}