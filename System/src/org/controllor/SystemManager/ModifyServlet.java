package org.controllor.SystemManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.SystemManage.ModifyDao;
import org.model.SystemManage.User;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User user = new User(username,password);
		boolean flag;
        
		if(username!=""){
        	flag=ModifyDao.modify(user);
	        if(flag){
	        	Cookie cookie = new Cookie("username", username);//创建一个键值对的cookie对象
	        	cookie.setMaxAge(9000);//设置cookie的生命周期
	        	response.addCookie(cookie);//添加到response中
	        	System.out.println(cookie.getValue());
	        	response.sendRedirect("SetPassword.jsp");
	            return;
	        }
	        else{
	        	PrintWriter out = response.getWriter();
	        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('用户名或密码错误!'); window.location='Modify.jsp'</script>");
	        	out.flush();
	        	out.close();
	        }
		}
	    else{
	    	PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('请输入用户名（警号）'); window.location='Modify.jsp'</script>");
        	out.flush();
        	out.close();
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		doGet(request, response);
	}

}
