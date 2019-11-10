package org.controllor.audit;

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
import org.model.audit.audit;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/AuditServlet")
public class AuditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final static Semaphore semEvent = new Semaphore(101);
	public void init(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//需要修改此处的权限
		Cookie[] cookies = request.getCookies();
		if(!Checks.haveRights(cookies,1)) {
			PrintWriter out = response.getWriter();
        	out.print("<script>alert('没有权限'); window.location='SchemeMake.jsp'</script>");
        	out.flush();
        	out.close();
			return;
		}
		
		String EventID=null;
		String EventName=null;
		String isPassed=null;
		String Suggestion=null;
		
		EventID=request.getParameter("EventID");
		EventName=request.getParameter("EventName");
		isPassed=request.getParameter("isPassed");
		Suggestion=request.getParameter("Suggestion");
		
		boolean illeagal = false;
		illeagal|=Checks.checkNULL(EventID);
		illeagal|=Checks.checkNULL(EventName);
		illeagal|=Checks.checkNULL(isPassed);
		illeagal|=Checks.checkNULL(Suggestion);
		
		if(illeagal) {
			PrintWriter out;
			out = response.getWriter();
			out.print("<script>alert('参数错误'); window.location='Audit.jsp'</script>");
	    	out.flush();
	    	out.close();
		}
		

		illeagal|=Checks.checkEmpty(EventID);
		illeagal|=Checks.checkEmpty(EventName);
		illeagal|=Checks.checkEmpty(isPassed);
		illeagal|=Checks.checkEmpty(Suggestion);
		
		if(illeagal) {
			PrintWriter out;
			out = response.getWriter();
			out.print("<script>alert('参数错误'); window.location='Audit.jsp'</script>");
	    	out.flush();
	    	out.close();
		}
		if(new audit(EventID,Suggestion,isPassed).saveAudit()) {
			PrintWriter out;
			out = response.getWriter();
			out.print("<script>alert('提交成功'); window.location='displayer.jsp?Project_id="+EventID+"'</script>");//此处未设置跳转
	    	out.flush();
	    	out.close();
		}
		PrintWriter out;
		out = response.getWriter();
		out.print("<script>alert('事件不存在或已被审核通过'); window.location='Audit.jsp'</script>");
    	out.flush();
    	out.close();

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		doGet(request, response);
	}

}