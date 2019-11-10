package org.controllor.SystemManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.SystemManage.PersonalAreaDao;
import org.model.SystemManage.PersonalModifyDao;
import org.model.SystemManage.PowerDao;
import org.model.SystemManage.RegisterDao;
import org.model.SystemManage.RoleDao;
import org.model.SystemManage.User;

/**
 * Servlet implementation class PersonalModifyServlet
 */
@WebServlet("/PersonalModifyServlet")
public class PersonalModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.getWriter().append("Served at: ").append(request.getContextPath());
				request.setCharacterEncoding("utf-8");
		        response.setContentType("text/html;charset=utf-8");
		        
		        String username = null;
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("username")) {
						username = cookie.getValue();
					}
				}
		        String realname   = request.getParameter("realname");
		        String unit       = request.getParameter("unit");
		        String position   = request.getParameter("position");
		        String tel        = request.getParameter("tel");
		        String email      = request.getParameter("email");
		        String temp1      = request.getParameter("id");
		        String temp2      = request.getParameter("birthdate");
		        
		        User temp=new User(username,realname,tel,email);
		        User user=new User(username,realname,unit,position,tel,email);

	        	if(temp1.equals(tel)&&temp2.equals(email)) {
	        		PersonalModifyDao.personalmodify(user);
   					ArrayList<String[]> ret=PersonalAreaDao.personal_inf(username);
   			        
   			       	Cookie cookie3 =new Cookie("realname",ret.get(0)[3]);
   			        cookie3.setMaxAge(300);//设置cookie的生命周期
   			        response.addCookie(cookie3);//添加到response中
   			       	System.out.println(cookie3.getValue());
   			       	
   			       	Cookie cookie4 =new Cookie("unit",ret.get(0)[4]);
   			        cookie4.setMaxAge(300);//设置cookie的生命周期
   			        response.addCookie(cookie4);//添加到response中
   			       	System.out.println(cookie4.getValue());
   			       	
   			       	Cookie cookie5 =new Cookie("position",ret.get(0)[5]);
   			        cookie5.setMaxAge(300);//设置cookie的生命周期
   			        response.addCookie(cookie5);//添加到response中
   			       	System.out.println(cookie5.getValue());
   			       	
   			       	Cookie cookie6 =new Cookie("id",ret.get(0)[6]);
   			        cookie6.setMaxAge(300);//设置cookie的生命周期
   			        response.addCookie(cookie6);//添加到response中
   			       	System.out.println(cookie6.getValue());
   			        
   			       	Cookie cookie7 =new Cookie("birthdate",ret.get(0)[7]);
   			        cookie7.setMaxAge(300);//设置cookie的生命周期
   			        response.addCookie(cookie7);//添加到response中
   			       	System.out.println(cookie7.getValue());
   			       	
   			       	Cookie cookie8 =new Cookie("jointime",ret.get(0)[8]);
   			        cookie8.setMaxAge(300);//设置cookie的生命周期
   			        response.addCookie(cookie8);//添加到response中
   			       	System.out.println(cookie8.getValue());
   			       	
   			       	Cookie cookie9 =new Cookie("tel",ret.get(0)[9]);
   			        cookie9.setMaxAge(300);//设置cookie的生命周期
   			        response.addCookie(cookie9);//添加到response中
   			       	System.out.println(cookie9.getValue());
   			       	
   			       	Cookie cookie10 =new Cookie("email",ret.get(0)[10]);
   			        cookie10.setMaxAge(300);//设置cookie的生命周期
   			        response.addCookie(cookie10);//添加到response中
   			       	System.out.println(cookie10.getValue());
   			       	
   			    	response.sendRedirect("PersonalArea.jsp");
   			        return;
	        	}
	        	else if(!temp1.equals(tel)&&temp2.equals(email)) {
	        		if(!Pattern.matches("^1[3-9]\\d{9}$",tel)){
	            		PrintWriter out = response.getWriter();
	           	       	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('手机号码有误!'); window.location='PersonalArea.jsp'</script>");
	           	       	out.flush();
	           	      	out.close();
	       		        return;
	        		}
	        		else {
		        		boolean flagT = RegisterDao.verifyT(temp);
		        		if(flagT) {
		        			PersonalModifyDao.personalmodify(user);
	       					ArrayList<String[]> ret=PersonalAreaDao.personal_inf(username);
	       			        
	       			       	Cookie cookie3 =new Cookie("realname",ret.get(0)[3]);
	       			        cookie3.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie3);//添加到response中
	       			       	System.out.println(cookie3.getValue());
	       			       	
	       			       	Cookie cookie4 =new Cookie("unit",ret.get(0)[4]);
	       			        cookie4.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie4);//添加到response中
	       			       	System.out.println(cookie4.getValue());
	       			       	
	       			       	Cookie cookie5 =new Cookie("position",ret.get(0)[5]);
	       			        cookie5.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie5);//添加到response中
	       			       	System.out.println(cookie5.getValue());
	       			       	
	       			       	Cookie cookie6 =new Cookie("id",ret.get(0)[6]);
	       			        cookie6.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie6);//添加到response中
	       			       	System.out.println(cookie6.getValue());
	       			        
	       			       	Cookie cookie7 =new Cookie("birthdate",ret.get(0)[7]);
	       			        cookie7.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie7);//添加到response中
	       			       	System.out.println(cookie7.getValue());
	       			       	
	       			       	Cookie cookie8 =new Cookie("jointime",ret.get(0)[8]);
	       			        cookie8.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie8);//添加到response中
	       			       	System.out.println(cookie8.getValue());
	       			       	
	       			       	Cookie cookie9 =new Cookie("tel",ret.get(0)[9]);
	       			        cookie9.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie9);//添加到response中
	       			       	System.out.println(cookie9.getValue());
	       			       	
	       			       	Cookie cookie10 =new Cookie("email",ret.get(0)[10]);
	       			        cookie10.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie10);//添加到response中
	       			       	System.out.println(cookie10.getValue());
	       			       	
	       			    	response.sendRedirect("PersonalArea.jsp");
	       			        return;
		        		}
		        		
		        		else {
		        			PrintWriter out = response.getWriter();
		           	       	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('手机号码已被注册!'); window.location='PersonalArea.jsp'</script>");
		           	       	out.flush();
		           	      	out.close();
		       		        return;
		        		}
	        		}
	        	}
	        	else if(!temp2.contentEquals(email)&&temp1.equals(tel)) {
	        		if(!Pattern.matches(".*[a-zA-Z0-9_-]@(qq|126|163|sina|.*edu.*)(\\.com|\\.cn).*",email)){
	            		PrintWriter out = response.getWriter();
	           	       	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('邮箱有误!'); window.location='PersonalArea.jsp'</script>");
	           	       	out.flush();
	           	      	out.close();
	       		        return;
	            	}
	        		else {
		        		boolean flagE = RegisterDao.verifyE(temp);
	    				if(flagE) 
	    				{
	    					PersonalModifyDao.personalmodify(user);
	       					ArrayList<String[]> ret=PersonalAreaDao.personal_inf(username);
	       			        
	       			       	Cookie cookie3 =new Cookie("realname",ret.get(0)[3]);
	       			        cookie3.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie3);//添加到response中
	       			       	System.out.println(cookie3.getValue());
	       			       	
	       			       	Cookie cookie4 =new Cookie("unit",ret.get(0)[4]);
	       			        cookie4.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie4);//添加到response中
	       			       	System.out.println(cookie4.getValue());
	       			       	
	       			       	Cookie cookie5 =new Cookie("position",ret.get(0)[5]);
	       			        cookie5.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie5);//添加到response中
	       			       	System.out.println(cookie5.getValue());
	       			       	
	       			       	Cookie cookie6 =new Cookie("id",ret.get(0)[6]);
	       			        cookie6.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie6);//添加到response中
	       			       	System.out.println(cookie6.getValue());
	       			        
	       			       	Cookie cookie7 =new Cookie("birthdate",ret.get(0)[7]);
	       			        cookie7.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie7);//添加到response中
	       			       	System.out.println(cookie7.getValue());
	       			       	
	       			       	Cookie cookie8 =new Cookie("jointime",ret.get(0)[8]);
	       			        cookie8.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie8);//添加到response中
	       			       	System.out.println(cookie8.getValue());
	       			       	
	       			       	Cookie cookie9 =new Cookie("tel",ret.get(0)[9]);
	       			        cookie9.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie9);//添加到response中
	       			       	System.out.println(cookie9.getValue());
	       			       	
	       			       	Cookie cookie10 =new Cookie("email",ret.get(0)[10]);
	       			        cookie10.setMaxAge(300);//设置cookie的生命周期
	       			        response.addCookie(cookie10);//添加到response中
	       			       	System.out.println(cookie10.getValue());
	       			       	
	       			    	response.sendRedirect("PersonalArea.jsp");
	       			        return;
	    				}
	    				else
	       				{
	        				PrintWriter out = response.getWriter();
		           	       	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('邮箱已被注册!'); window.location='PersonalArea.jsp'</script>");
		           	       	out.flush();
		           	      	out.close();
		       		        return;
	       				}
	        		}
	        	}
	        	else {
	        		if(!Pattern.matches("^1[3-9]\\d{9}$",tel)){
	            		PrintWriter out = response.getWriter();
	           	       	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('手机号码有误!'); window.location='PersonalArea.jsp'</script>");
	           	       	out.flush();
	           	      	out.close();
	       		        return;
	            	}
	            	else if(!Pattern.matches(".*[a-zA-Z0-9_-]@(qq|126|163|sina|.*edu.*)(\\.com|\\.cn).*",email)){
	            		PrintWriter out = response.getWriter();
	           	       	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('邮箱有误!'); window.location='PersonalArea.jsp'</script>");
	           	       	out.flush();
	           	      	out.close();
	       		        return;
	            	}
	            	else {
		        		boolean flagT = RegisterDao.verifyT(temp);
		            	boolean flagE = RegisterDao.verifyE(temp);
		            	if(flagT)
		       			{
		       				if(flagE)
		       				{
		       					PersonalModifyDao.personalmodify(user);
		       					ArrayList<String[]> ret=PersonalAreaDao.personal_inf(username);
		       			        
		       			       	Cookie cookie3 =new Cookie("realname",ret.get(0)[3]);
		       			        cookie3.setMaxAge(300);//设置cookie的生命周期
		       			        response.addCookie(cookie3);//添加到response中
		       			       	System.out.println(cookie3.getValue());
		       			       	
		       			       	Cookie cookie4 =new Cookie("unit",ret.get(0)[4]);
		       			        cookie4.setMaxAge(300);//设置cookie的生命周期
		       			        response.addCookie(cookie4);//添加到response中
		       			       	System.out.println(cookie4.getValue());
		       			       	
		       			       	Cookie cookie5 =new Cookie("position",ret.get(0)[5]);
		       			        cookie5.setMaxAge(300);//设置cookie的生命周期
		       			        response.addCookie(cookie5);//添加到response中
		       			       	System.out.println(cookie5.getValue());
		       			       	
		       			       	Cookie cookie6 =new Cookie("id",ret.get(0)[6]);
		       			        cookie6.setMaxAge(300);//设置cookie的生命周期
		       			        response.addCookie(cookie6);//添加到response中
		       			       	System.out.println(cookie6.getValue());
		       			        
		       			       	Cookie cookie7 =new Cookie("birthdate",ret.get(0)[7]);
		       			        cookie7.setMaxAge(300);//设置cookie的生命周期
		       			        response.addCookie(cookie7);//添加到response中
		       			       	System.out.println(cookie7.getValue());
		       			       	
		       			       	Cookie cookie8 =new Cookie("jointime",ret.get(0)[8]);
		       			        cookie8.setMaxAge(300);//设置cookie的生命周期
		       			        response.addCookie(cookie8);//添加到response中
		       			       	System.out.println(cookie8.getValue());
		       			       	
		       			       	Cookie cookie9 =new Cookie("tel",ret.get(0)[9]);
		       			        cookie9.setMaxAge(300);//设置cookie的生命周期
		       			        response.addCookie(cookie9);//添加到response中
		       			       	System.out.println(cookie9.getValue());
		       			       	
		       			       	Cookie cookie10 =new Cookie("email",ret.get(0)[10]);
		       			        cookie10.setMaxAge(300);//设置cookie的生命周期
		       			        response.addCookie(cookie10);//添加到response中
		       			       	System.out.println(cookie10.getValue());
		       			       	
		       			    	response.sendRedirect("PersonalArea.jsp");
		       			        return;
		        			}
		        			else
		       				{
		        				PrintWriter out = response.getWriter();
			           	       	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('邮箱已被注册!'); window.location='PersonalArea.jsp'</script>");
			           	       	out.flush();
			           	      	out.close();
			       		        return;
		       				}
		        		}
		       			else
		   				{
		       				PrintWriter out = response.getWriter();
		           	       	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('手机号码已被注册!'); window.location='PersonalArea.jsp'</script>");
		           	       	out.flush();
		           	      	out.close();
		       		        return;
		    			}
		            }
		        }
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		doGet(request, response);
	}

}
