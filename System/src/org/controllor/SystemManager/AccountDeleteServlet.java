package org.controllor.SystemManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.model.SystemManage.AccountManageDao;



/**
 * Servlet implementation class TaskDelete
 */
@WebServlet("/AccountDeleteServlet")
public class AccountDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取要删除的事件id
    	String userid=request.getParameter("userid");
    	 HttpSession session=request.getSession();
    	if(userid==null||userid=="") {
    		session.setAttribute("message", "用户名不能为空");
    		response.sendRedirect(request.getContextPath()+"/AccountManageServlet");
    	}else {
    		//删除数据库中的数据
    		AccountManageDao dao=new AccountManageDao();
    		int flag=dao.accountDelete(Integer.parseInt(userid));
    		//判断有没有删除成功
    		if(flag==1) {
    			//跳转到AccountManageServlet的页面
    			session.setAttribute("message", "删除成功！");
    			response.sendRedirect(request.getContextPath()+"/AccountManageServlet");
    		}else {
    			session.setAttribute("message", "删除失败！");
    			response.sendRedirect(request.getContextPath()+"/AccountManageServlet");
    		}
    		//跳转到AccountManageServlet的页面
    		//request.setAttribute("message","<script type='text/javascript' >alert('删除成功');</script>");
    	}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
