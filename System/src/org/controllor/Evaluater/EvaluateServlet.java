package org.controllor.Evaluater;

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
@WebServlet("/EvaluateServlet")
public class EvaluateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		String EventName = null;
		String EventID = null;
		String AttackerNum = null;
		String DefenderNum = null;
		String Scores = null;
		String Result = null;
		EventName = request.getParameter("EventName");
		EventID = (String) request.getParameter("EventID");
		AttackerNum = (String) request.getParameter("AttackerNum");
		DefenderNum = (String) request.getParameter("DefenderNum");
		Scores = (String) request.getParameter("Scores");
		Result = (String) request.getParameter("Result");
		
		boolean illeagal = false;
		illeagal |= Checks.checkNULL(EventName);
		illeagal |= Checks.checkNULL(EventID);
		illeagal |= Checks.checkNULL(AttackerNum);
		illeagal |= Checks.checkNULL(DefenderNum);
		illeagal |= Checks.checkNULL(Scores);
		illeagal |= Checks.checkNULL(Result);
		
		request.getSession().setAttribute("isIniting", "0");
		
		if (illeagal) {
			PrintWriter out = response.getWriter();
        	out.print("<script>alert('参数错误'); window.location='Evacuator.jsp'</script>");
        	out.flush();
        	out.close();
			return;
		}
		illeagal |= Checks.checkEmpty(EventName);
		illeagal |= Checks.checkEmpty(EventID);
		illeagal |= Checks.checkEmpty(AttackerNum);
		illeagal |= Checks.checkEmpty(DefenderNum);
		illeagal |= Checks.checkEmpty(Scores);
		illeagal |= Checks.checkEmpty(Result);
		
		if (illeagal) {
			PrintWriter out = response.getWriter();
        	out.print("<script>alert('参数错误'); window.location='Evaluator.jsp'</script>");
        	out.flush();
        	out.close();
        	return;
		}
		System.out.println(EventName);
		System.out.println(EventID);
		System.out.println(AttackerNum);
		System.out.println(DefenderNum);
		System.out.println(Scores);
		PrintWriter out = response.getWriter();
    	out.print("<script>alert('正确'); window.location='Evaluator.jsp'</script>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		doGet(request, response);
	}

}