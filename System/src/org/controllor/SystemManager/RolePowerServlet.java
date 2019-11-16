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
 * Servlet implementation class RolePowerServlet
 */
@WebServlet("/RolePowerServlet")
public class RolePowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");	
        
        String username   = request.getParameter("usernameX");
        String power1     = request.getParameter("power1");
        String power2     = request.getParameter("power2");
        String power3     = request.getParameter("power3");
        String power4     = request.getParameter("power4");
        
        if(power1==null) {
        	power1="0";
        }
        if(power2==null) {
        	power2="0";
        }
        if(power3==null) {
        	power3="0";
        }
        if(power4==null) {
        	power4="0";
        }
        System.out.println("444444            "+power1+"444444            "+power2+"444444            "+power3+"444444            "+power4);
        int power=(Integer.parseInt(power1))+(Integer.parseInt(power2))+(Integer.parseInt(power3))+(Integer.parseInt(power4));
        power+=17;
        int flag = RoleManageDao.rolepower(username,power);
        if(flag==0){
	        PrintWriter out = response.getWriter();
	    	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('用户名（警号）不存在!'); window.location='RoleManageServlet'</script>");
	    	out.flush();
	    	out.close();
	    	return;
        }
        else if(flag==1){
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
