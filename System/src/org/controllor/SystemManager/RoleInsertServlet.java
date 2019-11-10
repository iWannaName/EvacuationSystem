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

import org.model.SystemManage.RegisterDao;
import org.model.SystemManage.RoleManageDao;
import org.model.SystemManage.User;

/**
 * Servlet implementation class RoleInsertServlet
 */
@WebServlet("/RoleInsertServlet")
public class RoleInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");	
        
        String username   = request.getParameter("username");
        String realname   = request.getParameter("realname");
        String unit       = request.getParameter("unit");
        String position   = request.getParameter("position");
        String birthdate  = request.getParameter("birthdate");
        String jointime   = request.getParameter("jointime");
        String role_name  = request.getParameter("role_name");
        String status     = request.getParameter("status");
        
    	if(!Pattern.matches("\\d{6}", username)){
    		PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('用户名(警号)有误!'); window.location='RoleManageServlet'</script>");
        	out.flush();
        	out.close();
        	return;
    	}
    	else if(!Pattern.matches("^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",birthdate)){
    		PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('出生日期格式有误!'); window.location='RoleManageServlet'</script>");
        	out.flush();
        	out.close();
        	return;
    	}
    	else if(!Pattern.matches("^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",jointime)){
    		PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('入队日期格式有误!'); window.location='RoleManageServlet'</script>");
        	out.flush();
        	out.close();
        	return;
    	}
        User temp = new User(username,birthdate,jointime,status);
        User user = new User(username,realname,unit,position,birthdate,jointime);
    	boolean flagU = RegisterDao.verifyU(temp);
    	if(flagU) {
    		RoleManageDao.roleinsert(user, role_name, status);
    		PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('增加成功!'); window.location='RoleManageServlet'</script>");
        	out.flush();
        	out.close();
        	return;
    	}
    	else {
    		PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('用户名（警号）已存在!'); window.location='RoleManageServlet.jsp'</script>");
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
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		doGet(request, response);
	}

}
