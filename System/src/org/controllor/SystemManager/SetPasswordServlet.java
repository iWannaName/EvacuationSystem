package org.controllor.SystemManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.SystemManage.SetPasswordDao;

/**
 * Servlet implementation class SetPasswordServlet
 */
@WebServlet("/SetPasswordServlet")
public class SetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String password   = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String username   = request.getParameter("username");
        String message=null;
        System.out.println(username);
        if(password!="") {
	        if(!password.equals(repassword))
	        {	
	        	PrintWriter out = response.getWriter();
	        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('两次密码不一致!'); window.location='SetPassword.jsp'</script>");
	        	out.flush();
	        	out.close();
	        }
	        else
	        {	
	        	SetPasswordDao.setpassword(password,username);
	        	PrintWriter out = response.getWriter();
	        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('密码设置成功!'); window.location='Login.jsp'</script>");
	        	out.flush();
	        	out.close();
	        }
        }
        else{
        	PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('请输入密码!'); window.location='SetPassword.jsp'</script>");
        	out.flush();
        	out.close();
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
