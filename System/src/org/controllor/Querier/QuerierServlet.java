package org.controllor.Querier;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.Checks;
import org.model.Query;
import org.model.event;
import org.model.SchemeMake.SchemeMake;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/QuerierServlet")
public class QuerierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final static Semaphore semEvent = new Semaphore(101);
	public void init(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		init(request, response);
		Cookie[] cookies = request.getCookies();
		if(!Checks.haveRights(cookies,1)) {
			PrintWriter out = response.getWriter();
        	out.print("<script>alert('没有权限'); window.location='SchemeMake.jsp'</script>");
        	out.flush();
        	out.close();
			return;
		}
		String EventName=null;
		String EventID=null;
		String PoliceId=null;
		String Time=null;
		EventName=request.getParameter("EventName");
		EventID=request.getParameter("EventID");
		PoliceId=request.getParameter("PoliceId");
		Time=request.getParameter("Time");
		
		
		
		boolean NoParameters = true;
		NoParameters&=Checks.checkNULL(EventName);
		NoParameters&=Checks.checkNULL(EventID);
		NoParameters&=Checks.checkNULL(PoliceId);
		NoParameters&=Checks.checkNULL(Time);
		if(NoParameters) {
			NoParameters&=Checks.checkEmpty(EventName);
			NoParameters&=Checks.checkEmpty(EventID);
			NoParameters&=Checks.checkEmpty(PoliceId);
			NoParameters&=Checks.checkEmpty(Time);
			if(NoParameters) {
				
			}
		}
		else {
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		doGet(request, response);
	}

}