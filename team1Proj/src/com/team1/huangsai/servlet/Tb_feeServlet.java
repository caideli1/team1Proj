package com.team1.huangsai.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.huangsai.dao.Tb_feeitemDao;
import com.team1.huangsai.dao.Tb_userCostDao;

/**
 * Servlet implementation class Tb_feeServlet
 */
public class Tb_feeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Tb_userCostDao   userCostDao=new  Tb_userCostDao();
	Tb_feeitemDao    feeitemDao=new Tb_feeitemDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tb_feeServlet() {
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
		// 生成的响应数据没有乱码
		response.setContentType("text/html;charset=UTF-8");
		// 防止请求参数的值出现乱码
		request.setCharacterEncoding("UTF-8");
		// 得到method的值
		String methodName = request.getParameter("method");
		try {
			Method method = this.getClass().getDeclaredMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//按项目统计
	protected void getFee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  startDate=request.getParameter("beginDate");
		String  endDate=request.getParameter("endDate");
		if(startDate==null||startDate==""||endDate==null||endDate==""){
		List<Object[]>  fees = new ArrayList<Object[]>();
		fees=userCostDao.getFee();
		//System.out.println(fees.size());
		
		//System.out.println(fees.get(0)[1]);
		
		request.setAttribute("fees", fees);
		
		request.getRequestDispatcher("/userCost/totalReport.jsp").forward(request, response);
	}if(startDate!=null&&endDate!=null&&startDate!=""&&endDate!=""){
		//System.out.println(startDate);
		//System.out.println(endDate);
		List<Object[]>  fees = new ArrayList<Object[]>();
		fees=userCostDao.getFee(startDate,endDate);
		//System.out.println(fees.size());
		
		//System.out.println(fees.get(0)[1]);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("fees", fees);
		
		request.getRequestDispatcher("/userCost/totalReport.jsp").forward(request, response);	
			
			
		}
		
	}

}
