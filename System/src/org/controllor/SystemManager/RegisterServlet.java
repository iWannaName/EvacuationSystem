package org.controllor.SystemManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.SystemManage.*;

//控制器层，接收view请求，并转发给model
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
     static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理注册
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        String username   = request.getParameter("username");
        String password   = request.getParameter("password");
        String realname   = request.getParameter("realname");
        String unit       = request.getParameter("unit");
        String position   = request.getParameter("position");
        String id         = request.getParameter("id");
        String birthdate  = request.getParameter("birthdate");
        String jointime   = request.getParameter("jointime");
        String tel        = request.getParameter("tel");
        String email      = request.getParameter("email");
        	String message=null;
        	User temp = new User(username,id,tel,email);
        	User user = new User(username,password,realname,unit,position,id,birthdate,jointime,tel,email);
        	boolean flagU = RegisterDao.verifyU(temp);
        	boolean flagI = RegisterDao.verifyI(temp);
        	boolean flagT = RegisterDao.verifyT(temp);
        	boolean flagE = RegisterDao.verifyE(temp);
        	if(!Pattern.matches("\\d{6}", username)){
        		message = "用户名（警号）有误";
	        	request.getSession().setAttribute("message", message);
	            request.getRequestDispatcher("Register.jsp").forward(request, response);
	            System.out.println(message);
	            return;
        	}
        	else if(!Pattern.matches("[1-7][1-3][0]\\d{3}([1][9]\\d{2}|[2][0][0-1][0-9])([0][1-9]|[1][0-2])([0-1][0-9]|[2][0-8]|[3][0-1])\\d{4}",id)){
        		message = "身份证号有误";
	        	request.getSession().setAttribute("message", message);
	            request.getRequestDispatcher("Register.jsp").forward(request, response);
	            System.out.println(message);
	            return;
        	}
        	else if(!Pattern.matches("^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",birthdate)){
        		message = "出生日期格式有误";
	        	request.getSession().setAttribute("message", message);
	            request.getRequestDispatcher("Register.jsp").forward(request, response);
	            System.out.println(message);
	            return;
        	}
        	else if(!Pattern.matches("^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",jointime)){
        		message = "入队时间格式有误";
	        	request.getSession().setAttribute("message", message);
	            request.getRequestDispatcher("Register.jsp").forward(request, response);
	            System.out.println(message);
	            return;
        	}
        	else if(!Pattern.matches("^1[3-9]\\d{9}$",tel)){
        		message = "手机号有误";
	        	request.getSession().setAttribute("message", message);
	            request.getRequestDispatcher("Register.jsp").forward(request, response);
	            System.out.println(message);
	            return;
        	}
        	else if(!Pattern.matches(".*[a-zA-Z0-9_-]@(qq|126|163|sina|.*edu.*)(\\.com|\\.cn).*",email)){
        		message = "邮箱有误";
	        	request.getSession().setAttribute("message", message);
	            request.getRequestDispatcher("Register.jsp").forward(request, response);
	            System.out.println(message);
	            return;
        	}
        	else{
	        	if(flagU) {
	        		if(flagI)
		       		{
		       			if(flagT)
		       			{
		       				if(flagE)
		       				{
		       					RegisterDao.register(user);
		       					message="注册成功!";
		       					PowerDao.power(user);
		       					RoleDao.Role(user);
		       					request.getSession().setAttribute("message", message);
		        	            request.getRequestDispatcher("Login.jsp").forward(request, response);
		        			}
		        			else
		       				{
		       					message = "邮箱已被注册";
		       		        	request.getSession().setAttribute("message", message);
		       		            request.getRequestDispatcher("Register.jsp").forward(request, response);
		       		            System.out.println(message);
		       		            return;
		       				}
		        		}
		       			else
		   				{
		   					message = "手机号码已被注册";
		    	        	request.getSession().setAttribute("message", message);
		    	            request.getRequestDispatcher("Register.jsp").forward(request, response);
		   		            System.out.println(message);
		   		            return;
		    			}
		       		}
		        	else
					{
						message = "身份证号已被注册";				   
						request.getSession().setAttribute("message", message);
			            request.getRequestDispatcher("Register.jsp").forward(request, response);
			            System.out.println(message);
			            return;
					}
		       	}
		       	else
		        {
					message = "用户名（警号）已被注册";
			       	request.getSession().setAttribute("message", message);
			        request.getRequestDispatcher("Register.jsp").forward(request, response);
			        System.out.println(message);
			        return;
				}
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