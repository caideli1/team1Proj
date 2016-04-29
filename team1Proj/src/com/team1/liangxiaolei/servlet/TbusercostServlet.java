package com.team1.liangxiaolei.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.rmi.server.Dispatcher;

import com.team1.liangxiaolei.bean.TbfeeItem;
import com.team1.liangxiaolei.bean.TbuserCost;
import com.team1.liangxiaolei.dao.Tbusercostdao;
import com.team1.liuzhifeng.dao.ComplainDao;
import com.team1.util.PageUtil;

/**
 * Servlet implementation class TbusercostServlet
 */
public class TbusercostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pageSize =5;
	private int feeId = 0;
	private int changeSize;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TbusercostServlet() {
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
	
	//查询用户的缴费信息
	protected void searchUsercost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 得到页码
		int pageNo = Integer.parseInt(request.getParameter("feepageNo")) ;
		//得到缴费项目
		String feeid = request.getParameter("feeItemId");
		//得到jsp页面输入的用户名
		String realName = tonull(request.getParameter("realName"));
	
		//得到缴费的日期
		String beginDate = tonull(request.getParameter("beginDate"));
		String endDate = tonull(request.getParameter("endDate"));
		
		//得到改变的页面的记录数
		String size = request.getParameter("pageSize");	
		
		//实例化pageUtil对象
		PageUtil page = null;
		
		
		//实例化ComplainDao对象
		Tbusercostdao complaindao = new Tbusercostdao();
		if (size!=null){	
			int pageSize = Integer.parseInt(size);		
			this.pageSize  = pageSize;	
			
				
		}
		else if (feeid!=null){	
			int feeId = Integer.parseInt(feeid);
			this.feeId = feeId;
			
			page = complaindao.getUserCostList(pageSize, pageNo,realName,this.feeId,beginDate,endDate);
		}		
		else{
			page = complaindao.getUserCostList(pageSize, pageNo,realName,this.feeId,beginDate,endDate);
		}
		List<TbfeeItem> list = complaindao.getSelectOp();
		request.setAttribute("complainpage", page);
		request.setAttribute("list",list);
		request.getRequestDispatcher("/userCost/feeList.jsp").forward(request, response);	
		
	}
	
	/**
	 * 删除所选的条目
	 */
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//用集合来接收checkbox的值
		String[] list = request.getParameterValues("delid");
		//实例化TbuserCost
		Tbusercostdao tc = new Tbusercostdao();
		
		boolean flag = tc.deleteList(list);
		if (flag){
			response.sendRedirect(request.getContextPath()+"/usercost?method=searchUsercost&feepageNo=1&feeItemId=0");
		}else{
			 System.out.println("失败！");
		}		
	}
	/*
	 * 将空串的返回值return null;
	 */
	private String tonull(Object s) {
		if (s != null) {
			if (s.equals("")) {
				return null;
			} else {
				return (String) s;
			}
		} else {
			return null;
		}

	}

	
}
