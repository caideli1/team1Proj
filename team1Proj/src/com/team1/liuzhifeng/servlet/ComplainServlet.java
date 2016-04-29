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
	 * �������е�Ͷ��
	 */
	protected void doAllComplain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�õ��ı��ҳ��ļ�¼��
		String size = request.getParameter("pageSize");	
		//ʵ����pageUtil����
		PageUtil page = null;
		// �õ�ҳ��
		int pageNo = Integer.parseInt(request.getParameter("complainpageNo")) ;
		//ʵ����ComplainDao����
		ComplainDao complaindao = new ComplainDao();
		if (size!=null){
	
			int pageSize = Integer.parseInt(size);
			this.changeSize = pageSize; 
			this.pageSize  = this.changeSize;			
			page = complaindao.getComplainlList(pageSize, pageNo);
			
		}else{
			
			page = complaindao.getComplainlList(pageSize, pageNo);
		}
		//ʵ����session����
		HttpSession session = request.getSession();
		request.setAttribute("complainpage", page);
		request.getRequestDispatcher("/complain/complainList.jsp").forward(request, response);
		//response.sendRedirect("complain/complainList.jsp");		
	}
		/**
		 * ת�������䴦����Ա�ǽ���
		 */
	
	protected void openAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�õ�jspҳ�������
		//int doWithId = request.getParameter("");
		
		int complainId = Integer.parseInt(request.getParameter("complainId"));
		//ʵ����ComplainDao����
		ComplainDao complaindao = new ComplainDao();
		
		Object[] obj = complaindao.serachcom(complainId);
		//ʵ����session����
		HttpSession session = request.getSession();
		request.setAttribute("obj", obj);
		session.setAttribute("complainId", complainId);
		request.getRequestDispatcher("/complain/allotComplain.jsp").forward(request, response);
		//response.sendRedirect("complain/allotComplain.jsp");			
	}
	
		/**
		 * ���䴦����Ա
		 */
	protected void addAllotSer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�����˵�ID,
		int doWithId = 2;
		int complainId = Integer.parseInt(request.getParameter("tb_complain.complainId"));
		//ʵ����ComplainDao����
		ComplainDao complaindao = new ComplainDao();
		
		
		boolean flag = complaindao.addAllot(doWithId, 1,complainId);
		
		if (flag){
			//request.getRequestDispatcher("/public/index.jsp").forward(request, response);
			response.sendRedirect("public/notice.html");
		}else{
			System.out.println("����ʧ�ܣ�");
		}
				
	}
	/**
	 * ������Ͷ��
	 */
	protected void opendoWith(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		int complainId = Integer.parseInt(request.getParameter("complainId"));
		//ʵ����ComplainDao����
		ComplainDao complaindao = new ComplainDao();
		
		Object[] obj = complaindao.serachcom(complainId);
		//ʵ����session����
		HttpSession session = request.getSession();
		request.setAttribute("obj", obj);
		session.setAttribute("complainId", complainId);
		request.getRequestDispatcher("/complain/updateComplain.jsp").forward(request, response);		
	}
	/**
	 * ¼�봦����
	 */
	protected void addDoWith(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�����˵�ID,
		int doWithId = 2;
		int complainId = Integer.parseInt(request.getParameter("tb_complain.complainId"));
		String result = request.getParameter("tb_complain.ComplainResult");
		String date = request.getParameter("tb_complain.endDate");
		//ʵ����ComplainDao����
		ComplainDao complaindao = new ComplainDao();
		
		boolean flag = complaindao.addDowithServ(doWithId,2 ,result , date, complainId);
		
		if (flag){
			//request.getRequestDispatcher("/public/index.jsp").forward(request, response);
			response.sendRedirect("public/notice.html");
		}else{
			System.out.println("����ʧ�ܣ�");
		}				
	}
	/**
	 * δ����
	 */
	
	protected void doUnDoWith(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�õ��ı��ҳ��ļ�¼��
		String size = request.getParameter("pageSize");		
		// �õ�ҳ��
		int pageNo = Integer.parseInt(request.getParameter("complainpageNo"));		
		//ʵ����ComplainDao����
		ComplainDao complaindao = new ComplainDao();
		//ʵ����pageUtil����
		PageUtil page = null;
		if (size!=null){
			
			int pageSize = Integer.parseInt(size);
			this.changeSize = pageSize; 
			this.pageSize  = this.changeSize;			
			page = complaindao.getUnDoWithlList(pageSize, pageNo);
			
		}else{
			
			page = complaindao.getUnDoWithlList(pageSize, pageNo);
		}
				
		//ʵ����session����
		HttpSession session = request.getSession();
		session.setAttribute("complainpage", page);
		
		response.sendRedirect("complain/unDoWithComplain.jsp");		
	}
	/**
	 * ������
	 */
	protected void doDoWithCom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�õ��ı��ҳ��ļ�¼��
		String size = request.getParameter("pageSize");		
		// �õ�ҳ��
		int pageNo = Integer.parseInt(request.getParameter("complainpageNo"));		
		//ʵ����ComplainDao����
		ComplainDao complaindao = new ComplainDao();
		//ʵ����pageUtil����
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
	 * �Ѵ���
	 */
	protected void doFinishCom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�õ��ı��ҳ��ļ�¼��
		String size = request.getParameter("pageSize");		
		// �õ�ҳ��
		int pageNo = Integer.parseInt(request.getParameter("complainpageNo"));		
		//ʵ����ComplainDao����
		ComplainDao complaindao = new ComplainDao();
		//ʵ����pageUtil����
		PageUtil page = null;
		if (size!=null){
			
			int pageSize = Integer.parseInt(size);
			this.changeSize = pageSize; 
			this.pageSize  = this.changeSize;			
			page = complaindao.getFinishComList(pageSize, pageNo);
			
		}else{
			
			page = complaindao.getFinishComList(pageSize, pageNo);
		}
		//ʵ����session����
		HttpSession session = request.getSession();
		session.setAttribute("complainpage", page);
		
		response.sendRedirect("complain/finishComplainList.jsp");		
	}
	/**
	 * ��������
	 */
	protected void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//ʵ����ComplainDao����
		ComplainDao complaindao = new ComplainDao();
		//�õ���jspҳ�洫������id
		int  complainId = Integer.parseInt(request.getParameter("complainId"));
			
		//ʵ����Object����
		Object[] obj = complaindao.getDetail_one(complainId);
		//�õ�������Ա������
		String name = complaindao.getDetail_two(complainId);
		request.setAttribute("obj", obj);
		
		request.setAttribute("name",name);
		request.getRequestDispatcher("/complain/seeDetails.jsp").forward(request, response);	
		//response.sendRedirect("complain/seeDetails.jsp");		
	}
	
	
}
