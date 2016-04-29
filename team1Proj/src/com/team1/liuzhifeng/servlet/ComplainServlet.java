package com.team1.liuzhifeng.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.liuzhifeng.bean.ComplainBean;
import com.team1.liuzhifeng.dao.CarStallDao;
import com.team1.liuzhifeng.dao.ComplainDao;
import com.team1.util.PageUtil;

/**
 * Servlet implementation class ComplainServlet
 */
public class ComplainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pageSize =5;
	private int changeSize;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplainServlet() {
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
	 * 处理所有的投诉
	 */
	protected void doAllComplain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到改变的页面的记录数
		String size = request.getParameter("pageSize");	
		//实例化pageUtil对象
		PageUtil page = null;
		// 得到页码
		int pageNo = Integer.parseInt(request.getParameter("complainpageNo")) ;
		//实例化ComplainDao对象
		ComplainDao complaindao = new ComplainDao();
		if (size!=null){
	
			int pageSize = Integer.parseInt(size);
			this.changeSize = pageSize; 
			this.pageSize  = this.changeSize;			
			page = complaindao.getComplainlList(pageSize, pageNo);
			
		}else{
			
			page = complaindao.getComplainlList(pageSize, pageNo);
		}
		//实例化session对象
		HttpSession session = request.getSession();
		request.setAttribute("complainpage", page);
		request.getRequestDispatcher("/complain/complainList.jsp").forward(request, response);
		//response.sendRedirect("complain/complainList.jsp");		
	}
		/**
		 * 转发到分配处理人员那界面
		 */
	
	protected void openAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//得到jsp页面参数、
		//int doWithId = request.getParameter("");
		
		int complainId = Integer.parseInt(request.getParameter("complainId"));
		//实例化ComplainDao对象
		ComplainDao complaindao = new ComplainDao();
		
		Object[] obj = complaindao.serachcom(complainId);
		//实例化session对象
		HttpSession session = request.getSession();
		request.setAttribute("obj", obj);
		session.setAttribute("complainId", complainId);
		request.getRequestDispatcher("/complain/allotComplain.jsp").forward(request, response);
		//response.sendRedirect("complain/allotComplain.jsp");			
	}
	
		/**
		 * 分配处理人员
		 */
	protected void addAllotSer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//处理人的ID,
		int doWithId = 2;
		int complainId = Integer.parseInt(request.getParameter("tb_complain.complainId"));
		//实例化ComplainDao对象
		ComplainDao complaindao = new ComplainDao();
		
		
		boolean flag = complaindao.addAllot(doWithId, 1,complainId);
		
		if (flag){
			//request.getRequestDispatcher("/public/index.jsp").forward(request, response);
			response.sendRedirect("public/notice.html");
		}else{
			System.out.println("操作失败！");
		}
				
	}
	/**
	 * 已受理投诉
	 */
	protected void opendoWith(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		int complainId = Integer.parseInt(request.getParameter("complainId"));
		//实例化ComplainDao对象
		ComplainDao complaindao = new ComplainDao();
		
		Object[] obj = complaindao.serachcom(complainId);
		//实例化session对象
		HttpSession session = request.getSession();
		request.setAttribute("obj", obj);
		session.setAttribute("complainId", complainId);
		request.getRequestDispatcher("/complain/updateComplain.jsp").forward(request, response);		
	}
	/**
	 * 录入处理结果
	 */
	protected void addDoWith(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//处理人的ID,
		int doWithId = 2;
		int complainId = Integer.parseInt(request.getParameter("tb_complain.complainId"));
		String result = request.getParameter("tb_complain.ComplainResult");
		String date = request.getParameter("tb_complain.endDate");
		//实例化ComplainDao对象
		ComplainDao complaindao = new ComplainDao();
		
		boolean flag = complaindao.addDowithServ(doWithId,2 ,result , date, complainId);
		
		if (flag){
			//request.getRequestDispatcher("/public/index.jsp").forward(request, response);
			response.sendRedirect("public/notice.html");
		}else{
			System.out.println("操作失败！");
		}				
	}
	/**
	 * 未受理
	 */
	
	protected void doUnDoWith(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//得到改变的页面的记录数
		String size = request.getParameter("pageSize");		
		// 得到页码
		int pageNo = Integer.parseInt(request.getParameter("complainpageNo"));		
		//实例化ComplainDao对象
		ComplainDao complaindao = new ComplainDao();
		//实例化pageUtil对象
		PageUtil page = null;
		if (size!=null){
			
			int pageSize = Integer.parseInt(size);
			this.changeSize = pageSize; 
			this.pageSize  = this.changeSize;			
			page = complaindao.getUnDoWithlList(pageSize, pageNo);
			
		}else{
			
			page = complaindao.getUnDoWithlList(pageSize, pageNo);
		}
				
		//实例化session对象
		HttpSession session = request.getSession();
		session.setAttribute("complainpage", page);
		
		response.sendRedirect("complain/unDoWithComplain.jsp");		
	}
	/**
	 * 已受理
	 */
	protected void doDoWithCom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//得到改变的页面的记录数
		String size = request.getParameter("pageSize");		
		// 得到页码
		int pageNo = Integer.parseInt(request.getParameter("complainpageNo"));		
		//实例化ComplainDao对象
		ComplainDao complaindao = new ComplainDao();
		//实例化pageUtil对象
		PageUtil page = null;
		if (size!=null){
			
			int pageSize = Integer.parseInt(size);
			this.changeSize = pageSize; 
			this.pageSize  = this.changeSize;			
			page = complaindao.getDoWithList(pageSize, pageNo);
			
		}else{
			
			page = complaindao.getDoWithList(pageSize, pageNo);
		}
		HttpSession session = request.getSession();
		session.setAttribute("complainpage", page);
		
		response.sendRedirect("complain/dowithComplain.jsp");		
	}
	/**
	 * 已处理
	 */
	protected void doFinishCom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//得到改变的页面的记录数
		String size = request.getParameter("pageSize");		
		// 得到页码
		int pageNo = Integer.parseInt(request.getParameter("complainpageNo"));		
		//实例化ComplainDao对象
		ComplainDao complaindao = new ComplainDao();
		//实例化pageUtil对象
		PageUtil page = null;
		if (size!=null){
			
			int pageSize = Integer.parseInt(size);
			this.changeSize = pageSize; 
			this.pageSize  = this.changeSize;			
			page = complaindao.getFinishComList(pageSize, pageNo);
			
		}else{
			
			page = complaindao.getFinishComList(pageSize, pageNo);
		}
		//实例化session对象
		HttpSession session = request.getSession();
		session.setAttribute("complainpage", page);
		
		response.sendRedirect("complain/finishComplainList.jsp");		
	}
	/**
	 * 处理详情
	 */
	protected void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//实例化ComplainDao对象
		ComplainDao complaindao = new ComplainDao();
		//得到从jsp页面传过来的id
		int  complainId = Integer.parseInt(request.getParameter("complainId"));
			
		//实例化Object数组
		Object[] obj = complaindao.getDetail_one(complainId);
		//得到处理人员的姓名
		String name = complaindao.getDetail_two(complainId);
		request.setAttribute("obj", obj);
		
		request.setAttribute("name",name);
		request.getRequestDispatcher("/complain/seeDetails.jsp").forward(request, response);	
		//response.sendRedirect("complain/seeDetails.jsp");		
	}
	
	
}
