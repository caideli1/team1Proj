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
	 * �õ�¥������Ϣ
	 */
	protected void getBuild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
//		//ʵ����BuildingDao����
//		BuildingDao builddao = new BuildingDao();
//		//ʵ����Vector<BuildBean>����
//		Vector<BuildingBean> vc = builddao.getInfo();
//		//ʵ����session����
//		HttpSession session = request.getSession();
//		session.setAttribute("buildingList", vc);
//		
//		response.sendRedirect("building/buildingList.jsp");
		
		
		//�õ��ı��ҳ��ļ�¼��
		String size = request.getParameter("pageSize");	
		//ʵ����pageUtil����
		PageUtil page = null;
		// �õ�ҳ��
		int pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
		//ʵ����ComplainDao����
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
	 * �õ�ҵ������Ϣ
	 */
	protected void getOwner(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�õ��ı��ҳ��ļ�¼��
		String size = request.getParameter("pageSize");	
		//ʵ����pageUtil����
		PageUtil page = null;
		// �õ�ҳ��
		int pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
		//ʵ����ComplainDao����
		OwnerDetailDao complaindao = new OwnerDetailDao();
		if (size!=null){
	
			int pageSize = Integer.parseInt(size);
			this.changeSize = pageSize; 
			this.pageSize  = this.changeSize;			
			page = complaindao.getOwnerList(pageSize, pageNo);
			
		}else{			
			page = complaindao.getOwnerList(pageSize, pageNo);
		}
		
		//ʵ����session����
		request.setAttribute("ownerpage", page);		
		//�ض���
		RequestDispatcher dis = request.getRequestDispatcher("/ownerDetail/ownerList.jsp");
		dis.forward(request, response);		
		
		
	}
	
}
