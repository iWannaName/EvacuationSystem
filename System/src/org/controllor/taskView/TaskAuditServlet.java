package org.controllor.taskView;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import org.model.*;
import org.model.audit.TaskAuditDao;


/**
 * Servlet implementation class TaskModify
 */
@WebServlet("/TaskAuditServlet")
public class TaskAuditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskAuditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 设置编码
		request.setCharacterEncoding("utf-8");
		//2. 获取参数
		
		Map<String, String[]> map = request.getParameterMap();
		//3. 封装对象
		Audit audit=new Audit();
		try {
            BeanUtils.populate(audit,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
		
		//4. 调用dao保存
		    TaskAuditDao dao = new TaskAuditDao();
	        dao.audit(audit);
	    //5. 页面跳转
	       response.sendRedirect(request.getContextPath()+"/TaskViewServlet");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
