package org.controllor.SystemManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.SystemManage.ForgetDao;
import org.model.SystemManage.User;

/**
 * Servlet implementation class ForgetServlet
 */
@WebServlet("/ForgetServlet")
public class ForgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String id       = request.getParameter("id");
        String tel      = request.getParameter("tel");
        String email    = request.getParameter("email");
        
        User user = new User(username,id,tel,email);
        String verify ="";
        System.out.println(username);
        System.out.println(id);
        System.out.println(tel);
        System.out.println(email);
        if(!username.equals("")) {
        	verify=ForgetDao.verify(user);
        }
        else{
        	verify="用户名（警号）有误";
        	request.getSession().setAttribute("verify", verify);
        	request.getRequestDispatcher("Forget.jsp").forward(request, response);
        }
        System.out.println(verify);
        if(verify.equals("2")){
        	Cookie cookie = new Cookie("username", username);//创建一个键值对的cookie对象
        	cookie.setMaxAge(9000);//设置cookie的生命周期
        	response.addCookie(cookie);//添加到response中
        	System.out.println(cookie.getValue());
        	response.sendRedirect("SetPassword.jsp");
            return;
        }
        else {
        	request.getSession().setAttribute("verify", verify);
        	request.getRequestDispatcher("Forget.jsp").forward(request, response);
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
