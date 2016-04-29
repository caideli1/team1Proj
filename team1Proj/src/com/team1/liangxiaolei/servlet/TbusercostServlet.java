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
	
	//��ѯ�û��Ľɷ���Ϣ
	protected void searchUsercost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// �õ�ҳ��
		int pageNo = Integer.parseInt(request.getParameter("feepageNo")) ;
		//�õ��ɷ���Ŀ
		String feeid = request.getParameter("feeItemId");
		//�õ�jspҳ��������û���
		String realName = tonull(request.getParameter("realName"));
	
		//�õ��ɷѵ�����
		String beginDate = tonull(request.getParameter("beginDate"));
		String endDate = tonull(request.getParameter("endDate"));
		
		//�õ��ı��ҳ��ļ�¼��
		String size = request.getParameter("pageSize");	
		
		//ʵ����pageUtil����
		PageUtil page = null;
		
		
		//ʵ����ComplainDao����
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
	 * ɾ����ѡ����Ŀ
	 */
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�ü���������checkbox��ֵ
		String[] list = request.getParameterValues("delid");
		//ʵ����TbuserCost
		Tbusercostdao tc = new Tbusercostdao();
		
		boolean flag = tc.deleteList(list);
		if (flag){
			response.sendRedirect(request.getContextPath()+"/usercost?method=searchUsercost&feepageNo=1&feeItemId=0");
		}else{
			 System.out.println("ʧ�ܣ�");
		}		
	}
	/*
	 * ���մ��ķ���ֵreturn null;
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
