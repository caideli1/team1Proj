package com.team1.liuzhifeng.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.team1.liuzhifeng.bean.CarStallBean;
import com.team1.liuzhifeng.dao.CarStallDao;
import com.team1.liuzhifeng.dao.ComplainDao;
import com.team1.util.PageUtil;

/**
 * Servlet implementation class CarStallServlet
 */
public class CarStallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pageSize =5;
	private int changeSize;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarStallServlet() {
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
		// TODO Auto-generated method stub
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
	 * 得到车位信息
	 */
	protected void getCarStallInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//得到改变的页面的记录数
		String size = request.getParameter("pageSize");	
		//实例化pageUtil对象
		PageUtil page = null;
		// 得到页码
		int pageNo = Integer.parseInt(request.getParameter("carpageNo")) ;
		//实例化ComplainDao对象
		CarStallDao complaindao = new CarStallDao();
		if (size!=null){
	
			int pageSize = Integer.parseInt(size);
			this.changeSize = pageSize; 
			this.pageSize  = this.changeSize;			
			page = complaindao.getCarstallList(pageSize, pageNo);
			
		}else{			
			page = complaindao.getCarstallList(pageSize, pageNo);
		}
		
		request.setAttribute("carpage", page);
		
		//重定向
		RequestDispatcher dis = request.getRequestDispatcher("/carStall/carStallList.jsp");
		dis.forward(request, response);		
		//response.sendRedirect("carStall/carStallList.jsp");	
	}

}
