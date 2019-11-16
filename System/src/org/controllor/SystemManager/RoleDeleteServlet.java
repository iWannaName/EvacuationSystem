package org.controllor.SystemManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.SystemManage.RoleManageDao;

/**
 * Servlet implementation class RoleDeleteServlet
 */
@WebServlet("/RoleDeleteServlet")
public class RoleDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.setCharacterEncoding("utf-8");
	     response.setContentType("text/html;charset=utf-8");
	     
	        String username   = request.getParameter("usernameD");
	        if(!Pattern.matches("\\d{6}", username)){
	    		PrintWriter out = response.getWriter();
	        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('用户名(警号)有误!'); window.location='RoleManageServlet'</script>");
	        	out.flush();
	        	out.close();
	        	return;
	    	}
	        boolean flag =RoleManageDao.roledeleteverify(username);
	        if(flag) {
	        	PrintWriter out = response.getWriter();
	        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('删除成功!'); window.location='RoleManageServlet'</script>");
	        	out.flush();
	        	out.close();
	        	return;
	        }
	        else{
	        	PrintWriter out = response.getWriter();
	        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('用户名(警号)不存在!'); window.location='RoleManageServlet'</script>");
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
		PrintWriter out = response.getWriter();
        doGet(request, response);
	}

}
