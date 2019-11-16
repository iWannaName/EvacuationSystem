package org.controllor.SystemManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.SystemManage.RegisterDao;
import org.model.SystemManage.RoleManageDao;
import org.model.SystemManage.User;

/**
 * Servlet implementation class RoleModifyServlet
 */
@WebServlet("/RoleModifyServlet")
public class RoleModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");	
        
        String username   = request.getParameter("usernameM");
        String realname   = request.getParameter("realnameM");
        String unit       = request.getParameter("unitM");
        String position   = request.getParameter("positionM");
        String birthdate  = request.getParameter("birthdateM");
        String jointime   = request.getParameter("jointimeM");
        String role_name  = request.getParameter("role_nameM");
        
        User temp = new User(username,birthdate,jointime,unit);
        boolean flagU = RegisterDao.verifyU(temp);
        if(flagU)
        {
        	PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('用户名（警号）不存在!'); window.location='RoleManageServlet'</script>");
        	out.flush();
        	out.close();
        	return;
        }
        else {
	        User user=new User(username,realname,unit,position,birthdate,jointime);
	        RoleManageDao.rolemodify(user, role_name);
	        PrintWriter out = response.getWriter();
	    	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('修改成功!'); window.location='RoleManageServlet'</script>");
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
