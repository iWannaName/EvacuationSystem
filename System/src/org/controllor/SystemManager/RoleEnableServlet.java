package org.controllor.SystemManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.SystemManage.RoleManageDao;

/**
 * Servlet implementation class RoleEnableServlet
 */
@WebServlet("/RoleEnableServlet")
public class RoleEnableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				request.setCharacterEncoding("utf-8");
		        response.setContentType("text/html;charset=utf-8");	
		        String username =request.getParameter("usernameC");
		        String status   =request.getParameter("statusC");
		        System.out.println(status);
		        boolean flag = RoleManageDao.roleenable(username, status);
		        if(flag) {
		        	PrintWriter out = response.getWriter();
		        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('操作成功!'); window.location='RoleManageServlet'</script>");
		        	out.flush();
		        	out.close();
		        	return;
		        }
		        else {
		        	PrintWriter out = response.getWriter();
		        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('用户名（警号）不存在!'); window.location='RoleManageServlet'</script>");
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
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");	
		doGet(request, response);
	}

}
