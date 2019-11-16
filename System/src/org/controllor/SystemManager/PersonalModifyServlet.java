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
				
				request.setCharacterEncoding("utf-8");
		        response.setContentType("text/html;charset=utf-8");
		        
		        String username = null;
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("username")) {
						username = cookie.getValue();
					}
				}
				
		        String password   =request.getParameter("password");
		        System.out.println("测试获取password："+password);
		        String tel        = request.getParameter("tel");
		        String email      = request.getParameter("email");
		       
		        User user=new User(username,password,tel,email);

	        	
		       	PersonalModifyDao.personalmodify(user);
		       	ArrayList<String[]> ret=PersonalAreaDao.personal_inf(username);
		       	
		       	Cookie cookie2 =new Cookie("password",ret.get(0)[2]);
		       	cookie2.setMaxAge(300);//设置cookie的生命周期
		       	response.addCookie(cookie2);//添加到response中
		       	System.out.println(cookie2.getValue());
		       	
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
