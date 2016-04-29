package com.team1.liangxiaolei.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


import com.team1.liangxiaolei.bean.Tbcarstall;
import com.team1.liangxiaolei.bean.Tbcomplain;
import com.team1.liangxiaolei.bean.TbfeeItem;
import com.team1.liangxiaolei.bean.Tbownerdetail;
import com.team1.liangxiaolei.bean.Tbreport;
import com.team1.liangxiaolei.bean.Tbuser;
import com.team1.liangxiaolei.bean.TbuserCost;
import com.team1.liangxiaolei.dao.Tbcarstalldao;
import com.team1.liangxiaolei.dao.Tbcomplaindao;
import com.team1.liangxiaolei.dao.TbfeeItemdao;
import com.team1.liangxiaolei.dao.Tbreportdao;
import com.team1.liangxiaolei.dao.Tbusercostdao;
import com.team1.liangxiaolei.dao.Tbuserdao;
import com.team1.liangxiaolei.dao.Thownerdetaildao;
import com.team1.util.*;
/**
 * Servlet implementation class Userservlet
 */
public class Userservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Tbuserdao tbuserdao=new Tbuserdao();
       Tbcarstalldao tbcarstalldao=new Tbcarstalldao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Userservlet() {
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
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// �õ�method��ֵ
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
	//��½
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		Tbuser user = tbuserdao.login(userName, userPwd);

		if (user != null) {
			// ���û���Ϣ��װ�����ŵ�session��
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			// �ض���index.jspҳ�� (�ض����ʱ��ʹ�����·�����ļ���ǰ����Ҫ��/)
			response.sendRedirect("public/index.jsp");

		} else {
			// �������ص�¼ҳ��
			response.sendRedirect("login.jsp");
		}
	}
	
	//..��������Ƿ����Ҫ��
	protected void checkName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ӡ��
		boolean flag=true;
				PrintWriter   pw=response.getWriter();
				//��ȡurl���ύ�Ĳ���
			   String userName=request.getParameter("feeNames");
			   String createDates=request.getParameter("createDates");
			   String fees=request.getParameter("fees");
			   String feeDates=request.getParameter("feedates");
			    //�����û���jack��mike���Ѿ�ע�����
			  
			    if(userName==""){
			              //��ӡ�ַ��������ظ��ͻ���
			    	 pw.print("<font color='red'>�ף�*�շ���Ŀ������дŶ��</font>");
			    	 
			    }
			    if(feeDates==""){
		              //��ӡ�ַ��������ظ��ͻ���
		    	 pw.print("<font color='red'>�ף�*�շ�������д�ұ���������Ŷ��</font>");
		    	 
		    }
			    if(createDates==""){
			    	 pw.print("<font color='red'>�ף�*�������ڱ�����дŶ��</font>");
			    	
			    }
			    //Pattern pattern = Pattern.compile("[0-9]*");  
			   //flag=  pattern.matcher(fees).matches(); 
			    if(fees==""){
			    	 pw.print("<font color='red'>�ף�*�շѱ�׼������д����Ŷ��</font>");
			    	
			    }
			    
			    
			    pw.flush();
			    pw.close();
	}
	
	
	
	
	//����ϵͳ��������Ա�������û��б�
	protected void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int pageSize = 5;
	//int pageSize=	Integer.parseInt(request.getParameter("pageSize"));
		
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageNo2 = Integer.parseInt(request.getParameter("pageNo"));
		PageUtil page = tbuserdao.getUserList(pageSize, pageNo);
		PageUtil page2 = tbuserdao.getUserLista(pageSize, pageNo2);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("page", page);
		
   HttpSession session2 = request.getSession();
		
		session2.setAttribute("pagea", page2);
		
		request.getRequestDispatcher("/user/userList.jsp").forward(request,
				response);
	}
	
protected void listUsera(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int pageSize = 5;
	//int pageSize=	Integer.parseInt(request.getParameter("pageSize"));
		
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		PageUtil page = tbuserdao.getUserList(pageSize, pageNo);
        
		
		HttpSession session = request.getSession();
		
		session.setAttribute("pagea", page);
		
		request.getRequestDispatcher("/user/userList.jsp").forward(request,
				response);
	}
	
	
	
	//����ϵͳ���������û��б����û��༭
	
	protected void bianItems(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		TbfeeItemdao tbfeeitemdao=new TbfeeItemdao();
		int feeId=Integer.parseInt(request.getParameter("feeId"));
		
		TbfeeItem tbfeeItem=new TbfeeItem();
		tbfeeItem=tbfeeitemdao.getItemById(feeId);
		request.setAttribute("tbfeeItem", tbfeeItem);
		request.getRequestDispatcher("/feeItem/editItem.jsp").forward(request,
				response);
		
		
	}
	//..ϵͳ�������û��б����û��༭�����û�����
	protected void baocenItems(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		TbfeeItemdao tbfeeItemdao=new TbfeeItemdao();
		TbfeeItem tbfeeitem=new TbfeeItem();
		int feeId=Integer.parseInt(request.getParameter("feeId"));
		String feeName=request.getParameter("feeItem");
		Float fee=Float.parseFloat(request.getParameter("cost"));
		int feeReceDate=Integer.parseInt(request.getParameter("receDate"));
		String createDate=request.getParameter("createDate");
		
		tbfeeitem.setFeeId(feeId);
		tbfeeitem.setFeeName(feeName);
		tbfeeitem.setFee(fee);
		tbfeeitem.setFeeReceDate(feeReceDate);
		tbfeeitem.setCreateDate(createDate);
		
		tbfeeItemdao.update(tbfeeitem);
		
		RequestDispatcher resp=request.getRequestDispatcher("/userservlet?method=feeItems");
		
		
		resp.forward(request, response);
		
		
		
	}
	
	
	//��Ա������----������Ա���
	protected  void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	Tbuser tbuser1=new Tbuser();
	
	String userName=request.getParameter("tb_user.userName");
	String userPwd=request.getParameter("tb_user.userPwd");
	String realName=request.getParameter("tb_user.realName");
	String roleId=request.getParameter("tb_user.roleId");
	
	tbuser1.setUserName(userName);
	tbuser1.setUserPwd(userPwd);
	tbuser1.setRealName(realName);
	tbuser1.setRoleId(roleId);
	
	Tbuserdao tbuserdao=new Tbuserdao();
	tbuserdao.add(tbuser1);
	
	request.setAttribute("sf", tbuser1);
	RequestDispatcher resp=request.getRequestDispatcher("/public/notice.html");
	
	
	resp.forward(request, response);
	
	}
	//������ҵ���ɷѲ�ѯ
	protected void feeList(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		List<TbuserCost> tbusercost=new ArrayList<TbuserCost>();
		Tbusercostdao tbusercostdao =new Tbusercostdao();
		tbusercost=tbusercostdao.getAll();
		
		request.setAttribute("tbusercost", tbusercost);
		RequestDispatcher resp=request.getRequestDispatcher("/userCost/feeList.jsp");
		resp.forward(request, response);
		
		
	}
	
	//..�ɷ�
	//����ϵͳ�����շ���Ŀ����...�б�
	
	protected void feeItems(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		TbfeeItemdao tbfeeItemdao=new TbfeeItemdao();
		List<TbfeeItem> tbfeeItems= new ArrayList<TbfeeItem>();
		
				tbfeeItems= tbfeeItemdao.getAll();
				HttpSession session = request.getSession();
				
				session.setAttribute("tbfeeItems", tbfeeItems);
				RequestDispatcher resp=request.getRequestDispatcher("/feeItem/feeItem.jsp");
		resp.forward(request, response);
		
	}
	
	
	
	
	
	
	//�����ɷѷѱ���

	
	protected void baocenaddsendfee(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Tbusercostdao tbusercostdao=new Tbusercostdao();
		TbuserCost tbusercost=new TbuserCost();
		int userId=Integer.parseInt(request.getParameter("tb_userCost.userId"));
		int feeId=Integer.parseInt(request.getParameter("tb_userCost.feeId"));
		 Float havaCost=Float.parseFloat(request.getParameter("tbhavaCost"));
		int  reveived=Integer.parseInt(request.getParameter("tb_userCost.reveived"));
		String sendDate=request.getParameter("tb_userCost.sendDate");
		String remark=request.getParameter("tb_userCost.remark");
		tbusercost.setFeedId(feeId);
		tbusercost.setUserId(userId);
		tbusercost.setReveiveId(reveived);
		tbusercost.setHavaCost(havaCost);
		tbusercost.setSendDate(sendDate);
		tbusercost.setRemark(remark);
		
		tbusercostdao.add(tbusercost);
		RequestDispatcher resp=request.getRequestDispatcher("/public/notice.html");
		
		
		resp.forward(request, response);
		
	}	
	
	
	
	//����ϵͳ�������շ���Ŀ���á���������շ���Ŀ
	
	protected void feeItemsadd(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String feeName=request.getParameter("feeNames");
		System.out.println(feeName);
		Float fee=Float.parseFloat(request.getParameter("fees"));
		int feerecedate=Integer.parseInt(request.getParameter("feedates"));
		System.out.println(fee);
		System.out.println(feerecedate);
		String createdate=request.getParameter("createDates");
		System.out.println(createdate);
		TbfeeItem tbfeeItem=new TbfeeItem();
		tbfeeItem.setFeeName(feeName);
		tbfeeItem.setFee(fee);
		tbfeeItem.setFeeReceDate(feerecedate);
		tbfeeItem.setCreateDate(createdate);
		TbfeeItemdao tbfeeItemdao=new TbfeeItemdao();
		tbfeeItemdao.add(tbfeeItem);
		
		request.setAttribute("tbfeeItem", tbfeeItem);
		RequestDispatcher resp=request.getRequestDispatcher("/userservlet?method=feeItems");
		resp.forward(request, response);
	}
	
	//..��Ŀ�б�...ɾ����ѡ��
	
	protected void feeItemsdele(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
         
      String[] items=request.getParameterValues("delid");
      Integer[] items_int=new Integer[items.length];
		TbfeeItemdao tbfeeItemdao=new TbfeeItemdao();
		for(int i=0;i<items.length;i++){
			items_int[i]=Integer.parseInt(items[i]);
		  }
		
	
			boolean is=tbfeeItemdao.deleAll(items_int);
		    if(is){
		    	RequestDispatcher resp=request.getRequestDispatcher("/userservlet?method=feeItems");
				resp.forward(request, response);
		    }else{
		    	request.setAttribute("error", "ɾ��ʧ�ܣ�ԭ����������ã�");
				request.setAttribute("link","/userservlet?method=feeItems");
				RequestDispatcher resp=request.getRequestDispatcher("/WEB-INF/error.jsp");
				resp.forward(request, response);
		    }
			

	
		
		
	}
	
	
	//.....�������ģ���������
	
	protected void personEdit(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		List<Object[]>  tbodetail=new ArrayList<Object[]>();
		int userId=Integer.parseInt(request.getParameter("realId"));
		System.out.println(userId);
		Thownerdetaildao thownerdetaildao =new Thownerdetaildao();
		tbodetail=thownerdetaildao.getAll(userId);	
		HttpSession session = request.getSession();
	
		session.setAttribute("obj", tbodetail);
		RequestDispatcher resp=request.getRequestDispatcher("/user/personEdit.jsp");
resp.forward(request, response);
		
	}
	//�����������ϸ���
	protected void updateUser(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		Thownerdetaildao ownerdetaildao=new Thownerdetaildao();
		Tbownerdetail tbownerdetail=new Tbownerdetail();
		int ownerId=Integer.parseInt(request.getParameter("tb_ownerDetail.ownerId"));
		
		
		String cardId=request.getParameter("tb_ownerDetail.cardId");
		String telephone=request.getParameter("tb_ownerDetail.telephone");
		String enterDate=request.getParameter("tb_ownerDetail.enterDate");
		
		
		tbownerdetail.setOwnerId(ownerId);
		tbownerdetail.setCardId(cardId);
		tbownerdetail.setTelephone(telephone);
		tbownerdetail.setEnterDate(enterDate);
		
		
		
		ownerdetaildao.update(tbownerdetail);
		
		RequestDispatcher resp=request.getRequestDispatcher("/public/notice.html");
		resp.forward(request, response);
		
	}	
	
	
	
	//.....�������ġ�����Ҫ�⳵λ
	protected void restCar(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		
		int pageSize = 5;
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
			
			PageUtil page2 = tbcarstalldao.getRestcarList(pageSize, pageNo);
	        
			request.setAttribute("pages", page2);

			request.getRequestDispatcher("/carStall/restCarPosition.jsp").forward(request,
					response);
		
		
	}
	
	//.....�������ġ�����Ҫ����
	
	protected void addNewreport(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Tbreport tbreport=new Tbreport();
		int userId=Integer.parseInt(request.getParameter("tb_report.userId"));
		String reportName=request.getParameter("tb_report.reportName");
		String question=request.getParameter("tb_report.question");
		String reamark=request.getParameter("tb_report.reamark");
		String reportdate=request.getParameter("tb_report.reportDate");
		int handlerId=Integer.parseInt(request.getParameter("tb_report.handlerId"));
		 int reportState=Integer.parseInt(request.getParameter("tb_report.reportState"));
		tbreport.setUserId(userId);
		tbreport.setReportName(reportName);
		tbreport.setQuestion(question);
		tbreport.setReamark(reamark);
		tbreport.setReportDate(reportdate);
		tbreport.setHandlerId(handlerId);
		tbreport.setReportState(reportState);
		
		Tbreportdao tbreportdao=new Tbreportdao();
		tbreportdao.seletbreport(tbreport);
		
		RequestDispatcher resp=request.getRequestDispatcher("/public/notice.html");
		resp.forward(request, response);
		
	}
	
	//.....�������ġ�����ҪͶ��
	protected void addcomplain(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Tbcomplain tbcomplain=new Tbcomplain();
		int userId=Integer.parseInt(request.getParameter("tb_complain.userId"));
		String complainText=request.getParameter("tb_complain.complainText");
		String reamark=request.getParameter("tb_complain.reamark");
		String complainDate=request.getParameter("tb_complain.complainDate");
		int tbcomplainstate=Integer.parseInt(request.getParameter("complainState"));
		String complainResult=request.getParameter("ComplainResult");
		String endDate=request.getParameter("the_complainendDate");
		int doWithId=Integer.parseInt(request.getParameter("doWithId"));
		
		tbcomplain.setUserId(userId);
		tbcomplain.setComplainText(complainText);
		tbcomplain.setReamark(reamark);
		tbcomplain.setComplainDate(complainDate);
		tbcomplain.setComplainState(tbcomplainstate);
		tbcomplain.setComplainResult(complainResult);
		tbcomplain.setEndDate(endDate);
		tbcomplain.setDoWithId(doWithId);
		
		Tbcomplaindao tbcomplaindao=new Tbcomplaindao();
		tbcomplaindao.add(tbcomplain);
		
		
		
		RequestDispatcher resp=request.getRequestDispatcher("/public/notice.html");
		resp.forward(request, response);
		
	}
	
	
	
	

	
}
