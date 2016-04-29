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
		//���������Լ���Ӧ���ַ�����
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//���ȵõ�jspҳ���method������ֵ
		String method = request.getParameter("method");
		
		//ʵ����һ��Method��Ķ���
		try {
			Method m = this.getClass().getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
			m.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		} 			
	}
	
	/**
	 * �õ���λ��Ϣ
	 */
	protected void getCarStallInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�õ��ı��ҳ��ļ�¼��
		String size = request.getParameter("pageSize");	
		//ʵ����pageUtil����
		PageUtil page = null;
		// �õ�ҳ��
		int pageNo = Integer.parseInt(request.getParameter("carpageNo")) ;
		//ʵ����ComplainDao����
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
		
		//�ض���
		RequestDispatcher dis = request.getRequestDispatcher("/carStall/carStallList.jsp");
		dis.forward(request, response);		
		//response.sendRedirect("carStall/carStallList.jsp");	
	}

}
