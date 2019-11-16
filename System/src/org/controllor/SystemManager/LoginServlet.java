package org.controllor.SystemManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.SystemManage.LoginDao;
import org.model.SystemManage.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
		boolean flag;
		String power=null;
        if(username!="" && password!="")
        {
            User user = new User(username,password);
        	flag = LoginDao.login(user);
        	System.out.println(flag);
	        if(flag){
	        	ArrayList<String[]> ret = LoginDao.getcookie(user);
	        	Cookie cookie1 = new Cookie("username", username);//创建一个键值对的cookie对象
	        	cookie1.setMaxAge(60*60*24);//设置cookie的生命周期
	        	response.addCookie(cookie1);//添加到response中
	        	System.out.println(cookie1.getValue());
	        	Cookie cookie2 = new Cookie("power", ret.get(0)[2]);//创建一个键值对的cookie对象
	        	cookie2.setMaxAge(60*60*24);//设置cookie的生命周期
	        	response.addCookie(cookie2);//添加到response中
	        	System.out.println(cookie2.getValue());
	        	Cookie cookie3 = new Cookie("status", ret.get(0)[3]);//创建一个键值对的cookie对象
	        	cookie3.setMaxAge(60*60*24);//设置cookie的生命周期
	        	response.addCookie(cookie3);//添加到response中
	        	System.out.println(cookie3.getValue());
	        	response.sendRedirect("welcome.jsp");
	            return;
	        }else{
	        	PrintWriter out = response.getWriter();
	        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('用户名或密码有误!'); window.location='Login.jsp'</script>");
	        	out.flush();
	        	out.close();
	        	return;
	        }
        }
        else {
        	PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('用户名或密码有误!'); window.location='Login.jsp'</script>");
        	out.flush();
        	out.close();
            return;
        }
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		doGet(request, response);
	}

}
