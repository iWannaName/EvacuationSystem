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

import org.model.SystemManage.AccountManageDao;
import org.model.SystemManage.RegisterDao;
import org.model.SystemManage.RoleManageDao;
import org.model.SystemManage.User;

/**
 * Servlet implementation class AccountInsertServlet
 */
@WebServlet("/AccountInsertServlet")
public class AccountInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        String username  = request.getParameter("username");
        String password  = request.getParameter("password");
        String id        = request.getParameter("id");
        String tel       = request.getParameter("tel");
        String email     = request.getParameter("email");
        
    	if(!Pattern.matches("\\d{6}", username)){
    		PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('用户名(警号)有误!'); window.location='AccountManageServlet'</script>");
        	out.flush();
        	out.close();
        	return;
    	}else if(!Pattern.matches("^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$",id)){
    		PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('身份证号有误!'); window.location='AccountManageServlet'</script>");
        	out.flush();
        	out.close();
            return;
    	}else if(!Pattern.matches("^1[3-9]\\d{9}$",tel)){
    		PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('手机号有误!'); window.location='AccountManageServlet'</script>");
        	out.flush();
        	out.close();
            return;
    	}
    	else if(!Pattern.matches(".*[a-zA-Z0-9_-]@(qq|126|163|sina|.*edu.*)(\\.com|\\.cn).*",email)){
    		PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('邮箱有误!'); window.location='AccountManageServlet'</script>");
        	out.flush();
        	out.close();
            return;
    	}
        User user = new User(username,password,id,tel,email);
    	int flag = AccountManageDao.accountinsert(user);
    	if(flag==1) {
   			PrintWriter out = response.getWriter();
           	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('增加成功!'); window.location='AccountManageServlet'</script>");
           	out.flush();
           	out.close();
            return;
   		}else if(flag==0) {
    		PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('账户已存在!'); window.location='AccountManageServlet.jsp'</script>");
        	out.flush();
        	out.close();
        	return;
    	}
   		else if(flag==-1) {
    		PrintWriter out = response.getWriter();
        	out.print("<%@ page language = \"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"utf-8\" %><script>alert('用户名（警号）不存在!'); window.location='AccountManageServlet.jsp'</script>");
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
