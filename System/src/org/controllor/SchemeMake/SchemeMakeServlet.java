package org.controllor.SchemeMake;

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
@WebServlet("/SchemeMakeServlet")
public class SchemeMakeServlet extends HttpServlet {
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
		//需要修改此处的权限
		Cookie[] cookies = request.getCookies();
		if(!Checks.haveRights(cookies,1)) {
			PrintWriter out = response.getWriter();
        	out.print("<script>alert('没有权限'); window.location='SchemeMake.jsp'</script>");
        	out.flush();
        	out.close();
			return;
		}
		String origin = null;
		String destination = null;
		String mode = null;
		String EventName = null;
		String EventType = null;
		String Mode = null;
		String Crowd = null;
		String policeId = null;
		String Time = null;

		origin = request.getParameter("origin");
		destination = request.getParameter("destination");
		mode = request.getParameter("mode");
		EventName = request.getParameter("EventName");
		EventType = (String) request.getParameter("EventType");
		Mode = (String) request.getParameter("Mode");
		Crowd = (String) request.getParameter("Crowd");
		policeId = (String) request.getParameter("policeId");
		Time = (String) request.getParameter("Time");
		
		boolean illeagal = false;
		illeagal |= Checks.checkNULL(origin);
		illeagal |= Checks.checkNULL(destination);
		illeagal |= Checks.checkNULL(EventName);
		illeagal |= Checks.checkNULL(EventType);
		illeagal |= Checks.checkNULL(Crowd);
		illeagal |= Checks.checkNULL(policeId);
		illeagal |= Checks.checkNULL(Time);
		
		request.getSession().setAttribute("isIniting", "0");
		
		if (illeagal) {
			PrintWriter out = response.getWriter();
        	out.print("<script>alert('参数错误'); window.location='SchemeMake.jsp'</script>");
        	out.flush();
        	out.close();
			return;
		}
		illeagal |= Checks.checkEmpty(origin);
		illeagal |= Checks.checkEmpty(destination);
		illeagal |= Checks.checkEmpty(EventName);
		illeagal |= Checks.checkEmpty(EventType);
		illeagal |= Checks.checkEmpty(Crowd);
		illeagal |= Checks.checkEmpty(policeId);
		illeagal |= Checks.checkEmpty(Time);
		if (illeagal) {
			PrintWriter out = response.getWriter();
        	out.print("<script>alert('参数错误'); window.location='SchemeMake.jsp'</script>");
        	out.flush();
        	out.close();
        	return;
		}

		int status = Checks.SchemeMakeChecker(mode, origin, destination);
		if(status!=0) {
			PrintWriter out = response.getWriter();
        	out.print("<script>alert('参数错误'); window.location='SchemeMake.jsp'</script>");
        	out.flush();
        	out.close();
        	return;
		}
		
		event e_event = new event(EventName,EventType,origin,destination,Crowd,"0",policeId,Time,semEvent);
		e_event.viewEvent();
		

		
		SchemeMake scmk = new SchemeMake(mode,e_event) ;

		PrintWriter out;
		out = response.getWriter();
		out.print("<script>alert('提交成功'); window.location='SchemeMake_display.jsp'</script>");//此处未设置跳转
    	out.flush();
    	out.close();
		scmk.start();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		doGet(request, response);
	}

}