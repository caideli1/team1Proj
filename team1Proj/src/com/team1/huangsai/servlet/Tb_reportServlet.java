package com.team1.huangsai.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import com.team1.huangsai.bean.Tb_repair;
import com.team1.huangsai.bean.Tb_report;
import com.team1.huangsai.bean.Tb_user;
import com.team1.huangsai.dao.Tb_repairDao;
import com.team1.huangsai.dao.Tb_reportDao;
import com.team1.huangsai.dao.Tb_userDao;
import com.team1.util.PageUtil;

/**
 * Servlet implementation class Tb_reportServlet
 */
public class Tb_reportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ����һ��Dao
			private   Tb_reportDao    reportDao=new  Tb_reportDao(); 
			private   Tb_userDao    userDao=new  Tb_userDao(); 
			private   Tb_repairDao   repairDao=new  Tb_repairDao();
			private  int  pageSize=5;	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tb_reportServlet() {
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
		// ���ɵ���Ӧ����û������
					response.setContentType("text/html;charset=UTF-8");
					// ��ֹ���������ֵ��������
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
	//��ȡ�����б�
	public void showReportList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("�����˴˷���");
		// ����ÿҳ������5
        
		
		if(request.getParameter("pageSize")==null){
			
			 //System.out.println(pageSize);
				// �õ�ҳ��
				int pageNo = Integer.parseInt(request.getParameter("pageNo"));
				// �õ�PageUtil����
				PageUtil page = reportDao.getReportListTwo(pageSize, pageNo);
		         //������request��
				request.setAttribute("page", page);
			/*	int  s=page.getData().size();
				List<String>   names=new ArrayList<String>();
				for(int i=0;i<s;i++){
					Tb_user user=(Tb_user) page.getData().get(i);
					String realName=user.getRealName();
					names.add(realName);
				}
				request.setAttribute("names", names);*/
				//System.out.println(page.getTotalCount());
				// ת����userList.jsp(ת����ʱ���ļ���ǰҪ��/)
				request.getRequestDispatcher("/report/reportList.jsp").forward(request,
						response);
		}else{
			pageSize=Integer.parseInt(request.getParameter("pageSize"));	
			 System.out.println(pageSize);
				// �õ�ҳ��
				int pageNo = Integer.parseInt(request.getParameter("pageNo"));
				
				// �õ�PageUtil����
				PageUtil page = reportDao.getReportListTwo(pageSize, pageNo);
		         //������request��
				request.setAttribute("page", page);
			/*	int  s=page.getData().size();
				List<String>   names=new ArrayList<String>();
				for(int i=0;i<s;i++){
					Tb_user user=(Tb_user) page.getData().get(i);
					String realName=user.getRealName();
					names.add(realName);
				}
				request.setAttribute("names", names);*/
				//System.out.println(page.getTotalCount());
				// ת����userList.jsp(ת����ʱ���ļ���ǰҪ��/)
				request.getRequestDispatcher("/report/reportList.jsp").forward(request,
						response);
		}
	

	}
	//���м�¼ҳ�����ά����Ա
	public void allotPeople(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ǰ����
		int  id=Integer.parseInt(request.getParameter("id"));
		//��ǰҳ
       int pageNo=Integer.parseInt(request.getParameter("pageNo"));
		// �õ�PageUtil����
				PageUtil page = reportDao.getReportList(pageSize, pageNo);
				Tb_report report=new Tb_report();
				report=(Tb_report) page.getData().get(id-1);
			int 	userId=report.getUserId();
		Tb_user	user=userDao.FindUser(userId);
		
		//���ҹ�����Ա
		List<Tb_user> 	workers=userDao.FindWorker("r001");
		

				request.setAttribute("report", report);
				request.setAttribute("user", user);
				request.setAttribute("workers", workers);
		// ת����userList.jsp(ת����ʱ���ļ���ǰҪ��/)
		request.getRequestDispatcher("/report/allot.jsp").forward(request,
				response);

	}
	//���м�¼ҳ��¼�뱨����Ϣ
	
	public void TakNotes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ǰ����
		//System.out.println(pageSize);
		int  id=Integer.parseInt(request.getParameter("id"));
		//��ǰҳ
       int pageNo=Integer.parseInt(request.getParameter("pageNo"));
		// �õ�PageUtil����
				PageUtil page = reportDao.getReportList(pageSize, pageNo);
				Tb_report report=new Tb_report();
				report=(Tb_report) page.getData().get(id-1);
			
		Tb_user	handler=userDao.FindUser(report.getHandlerId());
		
		
		

				request.setAttribute("report", report);
				request.setAttribute("handler", handler);
		
		// ת����userList.jsp(ת����ʱ���ļ���ǰҪ��/)
		request.getRequestDispatcher("/report/addRepair.jsp").forward(request,
				response);

	}
	
	

	//���м�¼ҳ�����ά��������Ϣ
	
	public void RepDetailes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ǰ����
		int  id=Integer.parseInt(request.getParameter("id"));
		
		
		
		//��ǰҳ
       int pageNo=Integer.parseInt(request.getParameter("pageNo"));
       System.out.println(pageNo);
       System.out.println("����˭,,");
       
    // �õ�PageUtil����
		PageUtil page = reportDao.getReportList(pageSize, pageNo);
		Tb_report report=new Tb_report();
		report=(Tb_report) page.getData().get(id-1);
	
		int 	userId=report.getUserId();
		Tb_user	user=userDao.FindUser(userId);
		  int    handlerId=report.getHandlerId();
		  Tb_user	handler=userDao.FindUser(handlerId);

				request.setAttribute("report", report);
				request.setAttribute("user", user);
				request.setAttribute("handler", handler);
				
			Tb_repair	repair=repairDao.getRepair(report.getReportId());
                            report.getHandlerId();
		/*// �õ�PageUtil����
				System.out.println(pageNo);
				PageUtil page1 = reportDao.getReportDetailes(5, pageNo);
				Tb_report report=new Tb_report();
				report=(Tb_report) page.getData().get(id-1);
		Object[]   obj=new Object[12];
	
		obj=(Object[]) page1.getData().get(id-1);
		System.out.println(obj[11]);*/
		

				request.setAttribute("repair", repair);
				
			
		// ת����userList.jsp(ת����ʱ���ļ���ǰҪ��/)
		request.getRequestDispatcher("/report/seeRepair.jsp").forward(request,
				response);

	}
	
	
	
	
	//���±�����Ϣ
	public void updateAllot(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int handlerId=Integer.parseInt(request.getParameter("tb_report.handlerId"));
		int reportId=Integer.parseInt(request.getParameter("reportId"));
//		System.out.println(handlerId);
//		System.out.println(reportId);
//	
		if(reportDao.UpReport(handlerId, reportId)){
			//response.sendRedirect(request.getContextPath()+"/report?method=showReportList&pageNo=1");
			response.sendRedirect(request.getContextPath()+"/public/notice.html");
			
		}
	}
	
	//��ȡδ�������б�
		public void showUnacceptList(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// System.out.println("�����˴˷���");
			// ����ÿҳ������5
			if(request.getParameter("pageSize")==null){
              int pageSize2 = 5;        
				
				// �õ�ҳ��
				int pageNo = Integer.parseInt(request.getParameter("pageNo"));
				System.out.println(pageSize2);
				// �õ�PageUtil����
				PageUtil page = reportDao.getUnacceptList(pageSize2, pageNo);
		         //������request��
				request.setAttribute("page1", page);
			/*	int  s=page.getData().size();
				List<String>   names=new ArrayList<String>();
				for(int i=0;i<s;i++){
					Tb_user user=(Tb_user) page.getData().get(i);
					String realName=user.getRealName();
					names.add(realName);
				}
				request.setAttribute("names", names);*/
				//System.out.println(page.getTotalCount());
				// ת����userList.jsp(ת����ʱ���ļ���ǰҪ��/)
				request.getRequestDispatcher("/report/unaccept.jsp").forward(request,
						response);
					
				
				
			}
			
			else{
				int pageSize2 = Integer.parseInt(request.getParameter("pageSize"));        
				
				// �õ�ҳ��
				int pageNo = Integer.parseInt(request.getParameter("pageNo"));
				// �õ�PageUtil����
				PageUtil page = reportDao.getUnacceptList(pageSize2, pageNo);
		         //������request��
				request.setAttribute("page1", page);
			/*	int  s=page.getData().size();
				List<String>   names=new ArrayList<String>();
				for(int i=0;i<s;i++){
					Tb_user user=(Tb_user) page.getData().get(i);
					String realName=user.getRealName();
					names.add(realName);
				}
				request.setAttribute("names", names);*/
				//System.out.println(page.getTotalCount());
				// ת����userList.jsp(ת����ʱ���ļ���ǰҪ��/)
				request.getRequestDispatcher("/report/unaccept.jsp").forward(request,
						response);
				
				
				
			}
			

		}
	
		//��ȡ���������б�
				public void showAcceptList(HttpServletRequest request,
						HttpServletResponse response) throws ServletException, IOException {
					// System.out.println("�����˴˷���");
					// ����ÿҳ������5
					if(request.getParameter("pageSize")==null){
						//int pageSize = 5;
						
						// �õ�ҳ��
						int pageNo = Integer.parseInt(request.getParameter("pageNo"));
						// �õ�PageUtil����
						PageUtil page = reportDao.getAcceptList(pageSize, pageNo);
				         //������request��
						request.setAttribute("page", page);
					/*	int  s=page.getData().size();
						List<String>   names=new ArrayList<String>();
						for(int i=0;i<s;i++){
							Tb_user user=(Tb_user) page.getData().get(i);
							String realName=user.getRealName();
							names.add(realName);
						}
						request.setAttribute("names", names);*/
						//System.out.println(page.getTotalCount());
						// ת����userList.jsp(ת����ʱ���ļ���ǰҪ��/)
						request.getRequestDispatcher("/report/acceptList.jsp").forward(request,
								response);
						
						
						
						
					}else{
                    pageSize = Integer.parseInt(request.getParameter("pageSize"));
						
						// �õ�ҳ��
						int pageNo = Integer.parseInt(request.getParameter("pageNo"));
						// �õ�PageUtil����
						PageUtil page = reportDao.getAcceptList(pageSize, pageNo);
				         //������request��
						request.setAttribute("page", page);
					/*	int  s=page.getData().size();
						List<String>   names=new ArrayList<String>();
						for(int i=0;i<s;i++){
							Tb_user user=(Tb_user) page.getData().get(i);
							String realName=user.getRealName();
							names.add(realName);
						}
						request.setAttribute("names", names);*/
						//System.out.println(page.getTotalCount());
						// ת����userList.jsp(ת����ʱ���ļ���ǰҪ��/)
						request.getRequestDispatcher("/report/acceptList.jsp").forward(request,
								response);
						
						
					}
					
					

				}

				//��ȡ���������б�
				public void showFinishList(HttpServletRequest request,
						HttpServletResponse response) throws ServletException, IOException {
					// System.out.println("�����˴˷���");
					// ����ÿҳ������5
					if(request.getParameter("pageSize")==null){
						
						//int pageSize = 5;
						
						// �õ�ҳ��
						int pageNo = Integer.parseInt(request.getParameter("pageNo"));
						// �õ�PageUtil����
						PageUtil page = reportDao.getFinishList(pageSize, pageNo);
				         //������request��
						request.setAttribute("page", page);
					/*	int  s=page.getData().size();
						List<String>   names=new ArrayList<String>();
						for(int i=0;i<s;i++){
							Tb_user user=(Tb_user) page.getData().get(i);
							String realName=user.getRealName();
							names.add(realName);
						}
						request.setAttribute("names", names);*/
						//System.out.println(page.getTotalCount());
						// ת����userList.jsp(ת����ʱ���ļ���ǰҪ��/)
						request.getRequestDispatcher("/report/finishList.jsp").forward(request,
								response);
						
					}else{
						  pageSize = Integer.parseInt(request.getParameter("pageSize"));
						// �õ�ҳ��
						int pageNo = Integer.parseInt(request.getParameter("pageNo"));
						// �õ�PageUtil����
						PageUtil page = reportDao.getFinishList(pageSize, pageNo);
				         //������request��
						request.setAttribute("page", page);
					/*	int  s=page.getData().size();
						List<String>   names=new ArrayList<String>();
						for(int i=0;i<s;i++){
							Tb_user user=(Tb_user) page.getData().get(i);
							String realName=user.getRealName();
							names.add(realName);
						}
						request.setAttribute("names", names);*/
						//System.out.println(page.getTotalCount());
						// ת����userList.jsp(ת����ʱ���ļ���ǰҪ��/)
						request.getRequestDispatcher("/report/finishList.jsp").forward(request,
								response);
						
						
						
						
					}
					

				}
				//¼�뱣����Ϣҳ��
				public void TakeNotes(HttpServletRequest request,
						HttpServletResponse response) throws ServletException, IOException {
               
						
						//int pageSize = 5;
					//��ǰ����
					int  id=Integer.parseInt(request.getParameter("id"));
					//��ǰҳ
			       int pageNo=Integer.parseInt(request.getParameter("pageNo"));
			       System.out.println("����˭");
					// �õ�PageUtil����
							PageUtil page = reportDao.getAddRepListTwo(pageSize, pageNo);
							Tb_report report=new Tb_report();
							report=(Tb_report) page.getData().get(id-1);
							Tb_user	handler=userDao.FindUser(report.getHandlerId());
							
							
							

							request.setAttribute("report", report);
							request.setAttribute("handler", handler);

						
							
						
					// ת����userList.jsp(ת����ʱ���ļ���ǰҪ��/)
					request.getRequestDispatcher("/report/addRepair.jsp").forward(request,
							response);
                 

				}
				
				//¼�뱨����Ϣ
				public void updateRepair(HttpServletRequest request,
						HttpServletResponse response) throws ServletException, IOException {
					int  reportId=Integer.parseInt(request.getParameter("reportId"));
					int userId=Integer.parseInt(request.getParameter("userId"));;
					Float totalCost=Float.parseFloat(request.getParameter("tb_repair.totalCost"));
					String  repairResult=request.getParameter("tb_repair.repairResult");
					String    endDate=request.getParameter("tb_repair.endDate");
//					System.out.println(handlerId);
//					System.out.println(reportId);
			       Tb_repair   repair=new  Tb_repair();
					   repair.setReportId(reportId);
					   repair.setUserId(userId);
					   repair.setTotalCost(totalCost);
					   repair.setRepairResult(repairResult);
					   repair.setEndDate(endDate);
					   reportDao.UpRepairt(reportId);
					   if(repairDao.addRepair(repair)){
						   //response.sendRedirect(request.getContextPath()+"/report?method=showReportList&pageNo=1");	   
						   response.sendRedirect(request.getContextPath()+"/public/notice.html"); 
					   }
				}
				//ά������ҳ��
				
				
				public void ReportDetailes(HttpServletRequest request,
						HttpServletResponse response) throws ServletException, IOException {
					//��ǰ����
					int  id=Integer.parseInt(request.getParameter("id"));
					
					
					
					//��ǰҳ
			       int pageNo=Integer.parseInt(request.getParameter("pageNo"));
			       System.out.println(pageNo);
			       System.out.println("����˭,,");
			       
			    // �õ�PageUtil����
					PageUtil page = reportDao.getFinishListTwo(pageSize, pageNo);
					Tb_report report=new Tb_report();
					report=(Tb_report) page.getData().get(id-1);
				
					int 	userId=report.getUserId();
					Tb_user	user=userDao.FindUser(userId);
					  int    handlerId=report.getHandlerId();
					  Tb_user	handler=userDao.FindUser(handlerId);

							request.setAttribute("report", report);
							request.setAttribute("user", user);
							request.setAttribute("handler", handler);
							
						Tb_repair	repair=repairDao.getRepair(report.getReportId());
			                            report.getHandlerId();
					/*// �õ�PageUtil����
							System.out.println(pageNo);
							PageUtil page1 = reportDao.getReportDetailes(5, pageNo);
							Tb_report report=new Tb_report();
							report=(Tb_report) page.getData().get(id-1);
					Object[]   obj=new Object[12];
				
					obj=(Object[]) page1.getData().get(id-1);
					System.out.println(obj[11]);*/
					

							request.setAttribute("repair", repair);
							
						
					// ת����userList.jsp(ת����ʱ���ļ���ǰҪ��/)
					request.getRequestDispatcher("/report/seeRepair.jsp").forward(request,
							response);

				}
				
}
