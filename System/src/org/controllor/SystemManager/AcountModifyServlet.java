package org.controllor.SystemManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.SystemManage.AccountManageDao;
import org.model.SystemManage.RegisterDao;
import org.model.SystemManage.RoleManageDao;
import org.model.SystemManage.User;

/**
 * Servlet implementation class AcountModifyServlet
 */
@WebServlet("/AccountModifyServlet")
public class AcountModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");	
        
        String username   = request.getParameter("usernameM");
        String realname=request.getParameter("realnameM");
        String unit=request.getParameter("unitM");
        String position=request.getParameter("positionM");
        String id         = request.getParameter("idM");
        String birthdate=request.getParameter("birthdateM");
        String jointime=request.getParameter("jointimeM");
        String tel        = request.getParameter("telM");
        String email      = request.getParameter("emailM");
        
        User user = new User(username,realname,unit,position,id,birthdate,jointime,tel,email);
        int flag  =AccountManageDao.accountmodify(user);
        if(flag==-1)
        {
        	PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('用户名（警号）不存在!'); window.location='AccountManageServlet'</script>");
        	out.flush();
        	out.close();
        	return;
        }
        else if(flag==0){
	        PrintWriter out = response.getWriter();
	    	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('账户不存在!'); window.location='AccountManageServlet'</script>");
	    	out.flush();
	    	out.close();
	    	return;
        }
        else if(flag==1){
	        PrintWriter out = response.getWriter();
	    	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('修改成功!'); window.location='AccountManageServlet'</script>");
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
