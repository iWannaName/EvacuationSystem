package org.controllor.SystemManager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.SystemManage.PersonalAreaDao;

/**
 * Servlet implementation class PersonalAreaServlet
 */
@WebServlet("/PersonalAreaServlet")
public class PersonalAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        String username = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username")) {
				username = cookie.getValue();
			}
		}
        System.out.println(username);
        ArrayList<String[]> ret=PersonalAreaDao.personal_inf(username);
       	
        Cookie cookie2 =new Cookie("password",ret.get(0)[2]);
        cookie2.setMaxAge(300);//设置cookie的生命周期
        response.addCookie(cookie2);//添加到response中
       	System.out.println(cookie2.getValue());
        
       	Cookie cookie3 =new Cookie("realname",ret.get(0)[3]);
        cookie3.setMaxAge(300);//设置cookie的生命周期
        response.addCookie(cookie3);//添加到response中
       	System.out.println(cookie3.getValue());
       	
       	Cookie cookie4 =new Cookie("unit",ret.get(0)[4]);
        cookie4.setMaxAge(300);//设置cookie的生命周期
        response.addCookie(cookie4);//添加到response中
       	System.out.println(cookie4.getValue());
       	
       	Cookie cookie5 =new Cookie("position",ret.get(0)[5]);
        cookie5.setMaxAge(300);//设置cookie的生命周期
        response.addCookie(cookie5);//添加到response中
       	System.out.println(cookie5.getValue());
       	
       	Cookie cookie6 =new Cookie("id",ret.get(0)[6]);
        cookie6.setMaxAge(300);//设置cookie的生命周期
        response.addCookie(cookie6);//添加到response中
       	System.out.println(cookie6.getValue());
        
       	Cookie cookie7 =new Cookie("birthdate",ret.get(0)[7]);
        cookie7.setMaxAge(300);//设置cookie的生命周期
        response.addCookie(cookie7);//添加到response中
       	System.out.println(cookie7.getValue());
       	
       	Cookie cookie8 =new Cookie("jointime",ret.get(0)[8]);
        cookie8.setMaxAge(300);//设置cookie的生命周期
        response.addCookie(cookie8);//添加到response中
       	System.out.println(cookie8.getValue());
       	
       	Cookie cookie9 =new Cookie("tel",ret.get(0)[9]);
        cookie9.setMaxAge(300);//设置cookie的生命周期
        response.addCookie(cookie9);//添加到response中
       	System.out.println(cookie9.getValue());
       	
       	Cookie cookie10 =new Cookie("email",ret.get(0)[10]);
        cookie10.setMaxAge(300);//设置cookie的生命周期
        response.addCookie(cookie10);//添加到response中
       	System.out.println(cookie10.getValue());
       	
    	response.sendRedirect("PersonalArea.jsp");
        return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
