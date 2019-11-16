package org.controllor.SystemManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.SystemManage.AccountManageDao;

/**
 * Servlet implementation class AccountManageServlet
 */
@WebServlet("/AccountManageServlet")
public class AccountManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");	
        
        ArrayList<String[]> ret = new ArrayList<String[]>();
        ret =AccountManageDao.accountselect();
        
        for(int i=0;i<ret.size();i++)
        {
        	for(int j=1;j<7;j++)
			{
				System.out.print(ret.get(i)[j]+"    "); 
			}
			System.out.println();
        }
        
        request.getSession().setAttribute("ret", ret);
        request.getRequestDispatcher("AccountManage.jsp").forward(request, response);
        return;
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
