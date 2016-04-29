package com.team1.liuzhifeng.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.liuzhifeng.bean.BuildingBean;
import com.team1.liuzhifeng.dao.BuildingDao;
import com.team1.liuzhifeng.dao.ComplainDao;
import com.team1.liuzhifeng.dao.OwnerDetailDao;
import com.team1.util.PageUtil;

/**
 * Servlet implementation class LzfServlet
 */
public class LzfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pageSize =5;
	private int changeSize;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LzfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//定义请求，以及响应的字符编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//首先得到jsp页面的method方法的值
		String method = request.getParameter("method");
		
		//实例化一个Method类的对象
		try {
			Method m = this.getClass().getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
			m.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}
	/**
	 * 得到楼栋的信息
	 */
	protected void getBuild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
//		//实例化BuildingDao对象
//		BuildingDao builddao = new BuildingDao();
//		//实例化Vector<BuildBean>集合
//		Vector<BuildingBean> vc = builddao.getInfo();
//		//实例化session对象
//		HttpSession session = request.getSession();
//		session.setAttribute("buildingList", vc);
//		
//		response.sendRedirect("building/buildingList.jsp");
		
		
		//得到改变的页面的记录数
		String size = request.getParameter("pageSize");	
		//实例化pageUtil对象
		PageUtil page = null;
		// 得到页码
		int pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
		//实例化ComplainDao对象
		BuildingDao builddao = new BuildingDao();
		if (size!=null){
	
			int pageSize = Integer.parseInt(size);
			this.changeSize = pageSize; 
			this.pageSize  = this.changeSize;			
			page = builddao.getBuildList(pageSize, pageNo);
			
		}else{			
			page = builddao.getBuildList(pageSize, pageNo);
		}
		request.setAttribute("buildPage", page);
		RequestDispatcher dis = request.getRequestDispatcher("building/buildingList.jsp");
		dis.forward(request, response);		
		//response.sendRedirect("building/buildingList.jsp");
	}
	
	/**
	 * 得到业主的信息
	 */
	protected void getOwner(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//得到改变的页面的记录数
		String size = request.getParameter("pageSize");	
		//实例化pageUtil对象
		PageUtil page = null;
		// 得到页码
		int pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
		//实例化ComplainDao对象
		OwnerDetailDao complaindao = new OwnerDetailDao();
		if (size!=null){
	
			int pageSize = Integer.parseInt(size);
			this.changeSize = pageSize; 
			this.pageSize  = this.changeSize;			
			page = complaindao.getOwnerList(pageSize, pageNo);
			
		}else{			
			page = complaindao.getOwnerList(pageSize, pageNo);
		}
		
		//实例化session对象
		request.setAttribute("ownerpage", page);		
		//重定向
		RequestDispatcher dis = request.getRequestDispatcher("/ownerDetail/ownerList.jsp");
		dis.forward(request, response);		
		
		
	}
	
}
