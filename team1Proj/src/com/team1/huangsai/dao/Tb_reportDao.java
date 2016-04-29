package com.team1.huangsai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team1.huangsai.bean.Tb_report;
import com.team1.util.DBManage;
import com.team1.util.PageUtil;

public class Tb_reportDao {
       //��ȡ���б�����Ϣ
	/**
	 * ��ȡÿҳ�ı�����Ϣ
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	//ͨ�����ű��ȡ���б�����Ϣ
	public PageUtil getReportListTwo(int pageSize, int pageNo) {
		PageUtil page = null;
		// ��ѯ�û�����ȡ������
		String sql = "select  count(reportId) as totalCount from tb_report";
		Connection conn = DBManage.getConn();
		// ��������
		PreparedStatement sta = null;
		// ���������
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// �ܼ�¼��
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
				//System.out.println(totalCount);
			}

			page = new PageUtil(pageSize, totalCount);
			// ����ҳ��
			page.setPageNo(pageNo);
			// �����ѯ��ǰҳ���ݵ�sql���

			sql = "SELECT  reportId,reportName,question,realName,reportState from  tb_report   INNER JOIN   tb_user  ON  tb_report.userId=tb_user.userId  ORDER BY reportId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// ����һ��List����
			List<Object[]> reports = new ArrayList<Object[]>();
			
		while (rst.next()) {
				 Object[]  obj=new Object[5];
				 obj[0]=rst.getInt(1);
				 obj[1]=rst.getString(2);
				 obj[2]=rst.getString(3);
				 obj[3]=rst.getString(4);
				 obj[4]=rst.getInt(5);
				// ��ӵ�������
				reports.add(obj);
			    
			}
			// �������ݵļ���
			page.setData(reports);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}
	//һ�ű�
	public PageUtil getReportList(int pageSize, int pageNo) {
		PageUtil page = null;
		// ��ѯ�û�����ȡ������
		String sql = "select  count(reportId) as totalCount from tb_report";
		Connection conn = DBManage.getConn();
		// ��������
		PreparedStatement sta = null;
		// ���������
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// �ܼ�¼��
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
				//System.out.println(totalCount);
			}

			page = new PageUtil(pageSize, totalCount);
			// ����ҳ��
			page.setPageNo(pageNo);
			// �����ѯ��ǰҳ���ݵ�sql���

			sql = "SELECT  * from  tb_report    ORDER BY reportId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// ����һ��List����
			List<Tb_report> reports = new ArrayList<Tb_report>();
			
		while (rst.next()) {
// user.setRoleId(rst.getString(columnIndex))
				Tb_report report = new Tb_report();
				report.setReportId(rst.getInt(1));
				report.setReportName(rst.getString(2));
				report.setQuestion(rst.getString(3));
				report.setUserId(rst.getInt(4));
				report.setHandlerId(rst.getInt(5));
				report.setReportState(rst.getInt(6));
				report.setReportDate(rst.getString(7));
				report.setReamark(rst.getString(8));
				
				// ��ӵ�������
				reports.add(report);
			    
			}
			// �������ݵļ���
			page.setData(reports);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}
	
	//���±�����Ϣ
	public  boolean   UpReport(int handlerId,int  reportId) {
		 boolean   flag=false;
		  String  sql="update tb_report set handlerId=?,reportState=1 where  reportId=?";
		  Connection conn = DBManage.getConn();
			// ��������
			PreparedStatement sta = null;
			
			try {
  				sta = conn.prepareStatement(sql);
  				sta.setInt(1, handlerId);
  				sta.setInt(2, reportId);
  			

  				int row = sta.executeUpdate();
  				//System.out.println(sql);
  				if (row > 0) {
  					flag = true;
  				}

  			} catch (Exception e) {
  				e.printStackTrace();
  			} finally {
  				DBManage.closeAll(null, sta, conn);
  			}
  			return flag;
	}
	//ͨ��id�ҵ�report��Ϣ
	public  Tb_report   FindReport(int  reportId) {
		
		  String  sql="select * from tb_report where reportId=?";
		  Connection conn = DBManage.getConn();
			// ��������
			PreparedStatement sta = null;
			Tb_report   report=new Tb_report();
			// �����
			ResultSet rst = null;
			try {
				sta = conn.prepareStatement(sql);
				sta.setInt(1, reportId);
				rst = sta.executeQuery();
				if (rst.next()) {
					report.setReportId(rst.getInt(1));
					report.setReportName(rst.getString(2));
					report.setQuestion(rst.getString(3));
					report.setUserId(rst.getInt(4));
					report.setHandlerId(rst.getInt(5));
					report.setReportState(rst.getInt(6));
					report.setReportDate(rst.getString(7));
					report.setReamark(rst.getString(8));

				}
				return   report;
 			} catch (Exception e) {
 				e.printStackTrace();
 				return   null;
 			} finally {
 				DBManage.closeAll(null, sta, conn);
 			}
 			
	}

//   ͨ�����ű��������δ����ı��޵�
	public PageUtil getUnacceptList(int pageSize, int pageNo) {
		PageUtil page = null;
		// ��ѯ�û�����ȡ������
		String sql = "select  count(reportId) as totalCount from tb_report  where  reportState=0";
		Connection conn = DBManage.getConn();
		// ��������
		PreparedStatement sta = null;
		// ���������
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// �ܼ�¼��
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
				//System.out.println(totalCount);
			}

			page = new PageUtil(pageSize, totalCount);
			// ����ҳ��
			page.setPageNo(pageNo);
			// �����ѯ��ǰҳ���ݵ�sql���

			sql = "SELECT  reportId,reportName,question,realName,reportState from  tb_report   INNER JOIN   tb_user  ON  tb_report.userId=tb_user.userId and reportState=0 ORDER BY reportId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// ����һ��List����
			List<Object[]> reports = new ArrayList<Object[]>();
			
		while (rst.next()) {
				 Object[]  obj=new Object[5];
				 obj[0]=rst.getInt(1);
				 obj[1]=rst.getString(2);
				 obj[2]=rst.getString(3);
				 obj[3]=rst.getString(4);
				 obj[4]=rst.getInt(5);
				// ��ӵ�������
				reports.add(obj);
			
	

				// user.setRoleId(rst.getString(columnIndex))
				/*Tb_report report = new Tb_report();
				report.setReportId(rst.getInt(1));
				report.setReportName(rst.getString(2));
				report.setQuestion(rst.getString(3));
				report.setUserId(rst.getInt(4));
				report.setHandlerId(rst.getInt(5));
				report.setReportState(rst.getInt(6));
				report.setReportDate(rst.getString(7));
				report.setReamark(rst.getString(8));
*/
				
			    
			}
			// �������ݵļ���
			page.setData(reports);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}
	
//  ��������������ı��޵�
	public PageUtil getAcceptList(int pageSize, int pageNo) {
		PageUtil page = null;
		// ��ѯ�û�����ȡ������
		String sql = "select  count(reportId) as totalCount from tb_report  where  reportState=1";
		Connection conn = DBManage.getConn();
		// ��������
		PreparedStatement sta = null;
		// ���������
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// �ܼ�¼��
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
				//System.out.println(totalCount);
			}

			page = new PageUtil(pageSize, totalCount);
			// ����ҳ��
			page.setPageNo(pageNo);
			// �����ѯ��ǰҳ���ݵ�sql���

			sql = "SELECT  reportId,reportName,question,realName,reportState from  tb_report   INNER JOIN   tb_user  ON  tb_report.userId=tb_user.userId and reportState=1 ORDER BY reportId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// ����һ��List����
						List<Object[]> reports = new ArrayList<Object[]>();
						
					while (rst.next()) {
							 Object[]  obj=new Object[5];
							 obj[0]=rst.getInt(1);
							 obj[1]=rst.getString(2);
							 obj[2]=rst.getString(3);
							 obj[3]=rst.getString(4);
							 obj[4]=rst.getInt(5);
							// ��ӵ�������
							reports.add(obj);
						
				

							// user.setRoleId(rst.getString(columnIndex))
							/*Tb_report report = new Tb_report();
							report.setReportId(rst.getInt(1));
							report.setReportName(rst.getString(2));
							report.setQuestion(rst.getString(3));
							report.setUserId(rst.getInt(4));
							report.setHandlerId(rst.getInt(5));
							report.setReportState(rst.getInt(6));
							report.setReportDate(rst.getString(7));
							report.setReamark(rst.getString(8));
			*/
							
						    
						
			    
			}
			// �������ݵļ���
			page.setData(reports);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}
//  ���������Ѵ���ı��޵�
	public PageUtil getFinishList(int pageSize, int pageNo) {
		PageUtil page = null;
		// ��ѯ�û�����ȡ������
		String sql = "select  count(reportId) as totalCount from tb_report  where  reportState=2";
		Connection conn = DBManage.getConn();
		// ��������
		PreparedStatement sta = null;
		// ���������
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// �ܼ�¼��
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
				//System.out.println(totalCount);
			}

			page = new PageUtil(pageSize, totalCount);
			// ����ҳ��
			page.setPageNo(pageNo);
			// �����ѯ��ǰҳ���ݵ�sql���

			sql = "SELECT  reportId,reportName,question,realName,reportState from  tb_report   INNER JOIN   tb_user  ON  tb_report.userId=tb_user.userId and reportState=2 ORDER BY reportId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// ����һ��List����
						List<Object[]> reports = new ArrayList<Object[]>();
						
					while (rst.next()) {
							 Object[]  obj=new Object[5];
							 obj[0]=rst.getInt(1);
							 obj[1]=rst.getString(2);
							 obj[2]=rst.getString(3);
							 obj[3]=rst.getString(4);
							 obj[4]=rst.getInt(5);
							// ��ӵ�������
							reports.add(obj);
						
				

							// user.setRoleId(rst.getString(columnIndex))
							/*Tb_report report = new Tb_report();
							report.setReportId(rst.getInt(1));
							report.setReportName(rst.getString(2));
							report.setQuestion(rst.getString(3));
							report.setUserId(rst.getInt(4));
							report.setHandlerId(rst.getInt(5));
							report.setReportState(rst.getInt(6));
							report.setReportDate(rst.getString(7));
							report.setReamark(rst.getString(8));
			*/
							
						    
					
			    
			}
			// �������ݵļ���
			page.setData(reports);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}
	//һ�ű��ȡ�Ѵ�����Ϣ
			public PageUtil getFinishListTwo(int pageSize, int pageNo) {
				PageUtil page = null;
				// ��ѯ�û�����ȡ������
				String sql = "select  count(reportId) as totalCount from tb_report";
				Connection conn = DBManage.getConn();
				// ��������
				PreparedStatement sta = null;
				// ���������
				ResultSet rst = null;
				try {
					sta = conn.prepareStatement(sql);

					rst = sta.executeQuery();
					// �ܼ�¼��
					int totalCount = 0;
					if (rst.next()) {
						totalCount = rst.getInt("totalCount");
						//System.out.println(totalCount);
					}

					page = new PageUtil(pageSize, totalCount);
					// ����ҳ��
					page.setPageNo(pageNo);
					// �����ѯ��ǰҳ���ݵ�sql���

					sql = "SELECT  * from  tb_report where reportState=2  ORDER BY reportId limit  "
							+ (pageNo - 1) * pageSize + "," + pageSize;
					sta = conn.prepareStatement(sql);
					rst = sta.executeQuery();
					// ����һ��List����
					List<Tb_report> reports = new ArrayList<Tb_report>();
					
				while (rst.next()) {
		// user.setRoleId(rst.getString(columnIndex))
						Tb_report report = new Tb_report();
						report.setReportId(rst.getInt(1));
						report.setReportName(rst.getString(2));
						report.setQuestion(rst.getString(3));
						report.setUserId(rst.getInt(4));
						report.setHandlerId(rst.getInt(5));
						report.setReportState(rst.getInt(6));
						report.setReportDate(rst.getString(7));
						report.setReamark(rst.getString(8));
						
						// ��ӵ�������
						reports.add(report);
					    
					}
					// �������ݵļ���
					page.setData(reports);

				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					DBManage.closeAll(rst, sta, conn);
				}
				return page;
			}
	
	
	
	
	//���ű�¼�뱣����Ϣҳ��
	
	public PageUtil getAddRepList(int pageSize, int pageNo) {
		PageUtil page = null;
		// ��ѯ�û�����ȡ������
		String sql = "select  count(reportId) as totalCount from tb_report  where  reportState=1";
		Connection conn = DBManage.getConn();
		// ��������
		PreparedStatement sta = null;
		// ���������
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// �ܼ�¼��
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
				//System.out.println(totalCount);
			}

			page = new PageUtil(pageSize, totalCount);
			// ����ҳ��
			page.setPageNo(pageNo);
			// �����ѯ��ǰҳ���ݵ�sql���

			sql = "SELECT  reportId,reportName,question,handlerId,reportState,reportDate,reamark,realName from  tb_report   INNER JOIN   tb_user  ON  tb_report.userId=tb_user.userId and reportState=1 ORDER BY reportId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// ����һ��List����
						List<Object[]> reports = new ArrayList<Object[]>();
						
					while (rst.next()) {
							 Object[]  obj=new Object[8];
							 obj[0]=rst.getInt(1);
							 obj[1]=rst.getString(2);
							 obj[2]=rst.getString(3);
							 obj[3]=rst.getInt(4);
							 obj[4]=rst.getInt(5);
							 obj[5]=rst.getString(6);
							 obj[6]=rst.getString(7);
							 obj[7]=rst.getString(8);
							
							// ��ӵ�������
							reports.add(obj);

						
							
						    
					
			    
			}
			// �������ݵļ���
			page.setData(reports);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}
	
	//һ�ű��ȡ������Ϣ
		public PageUtil getAddRepListTwo(int pageSize, int pageNo) {
			PageUtil page = null;
			// ��ѯ�û�����ȡ������
			String sql = "select  count(reportId) as totalCount from tb_report";
			Connection conn = DBManage.getConn();
			// ��������
			PreparedStatement sta = null;
			// ���������
			ResultSet rst = null;
			try {
				sta = conn.prepareStatement(sql);

				rst = sta.executeQuery();
				// �ܼ�¼��
				int totalCount = 0;
				if (rst.next()) {
					totalCount = rst.getInt("totalCount");
					//System.out.println(totalCount);
				}

				page = new PageUtil(pageSize, totalCount);
				// ����ҳ��
				page.setPageNo(pageNo);
				// �����ѯ��ǰҳ���ݵ�sql���

				sql = "SELECT  * from  tb_report where reportState=1   ORDER BY reportId limit  "
						+ (pageNo - 1) * pageSize + "," + pageSize;
				sta = conn.prepareStatement(sql);
				rst = sta.executeQuery();
				// ����һ��List����
				List<Tb_report> reports = new ArrayList<Tb_report>();
				
			while (rst.next()) {
	// user.setRoleId(rst.getString(columnIndex))
					Tb_report report = new Tb_report();
					report.setReportId(rst.getInt(1));
					report.setReportName(rst.getString(2));
					report.setQuestion(rst.getString(3));
					report.setUserId(rst.getInt(4));
					report.setHandlerId(rst.getInt(5));
					report.setReportState(rst.getInt(6));
					report.setReportDate(rst.getString(7));
					report.setReamark(rst.getString(8));
					
					// ��ӵ�������
					reports.add(report);
				    
				}
				// �������ݵļ���
				page.setData(reports);

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				DBManage.closeAll(rst, sta, conn);
			}
			return page;
		}
	
	
	//¼�뱨����Ϣ�������Ϣ
		public  boolean   UpRepairt(int  reportId) {
			 boolean   flag=false;
			  String  sql="update tb_report set reportState=2 where  reportId=?";
			  Connection conn = DBManage.getConn();
				// ��������
				PreparedStatement sta = null;
				
				try {
	  				sta = conn.prepareStatement(sql);
	  				sta.setInt(1, reportId);
	  			
	  			

	  				int row = sta.executeUpdate();
	  				//System.out.println(sql);
	  				if (row > 0) {
	  					flag = true;
	  				}

	  			} catch (Exception e) {
	  				e.printStackTrace();
	  			} finally {
	  				DBManage.closeAll(null, sta, conn);
	  			}
	  			return flag;
		}
	
	//ά������/ά�޵���Ϣ
		public PageUtil getReportDetailes(int pageSize, int pageNo) {
			PageUtil page = null;
			// ��ѯ�û�����ȡ������
			String sql = "select  count(reportId) as totalCount from tb_report  where  reportState=2";
			Connection conn = DBManage.getConn();
			// ��������
			PreparedStatement sta = null;
			// ���������
			ResultSet rst = null;
			try {
				sta = conn.prepareStatement(sql);

				rst = sta.executeQuery();
				// �ܼ�¼��
				int totalCount = 0;
				if (rst.next()) {
					totalCount = rst.getInt("totalCount");
					//System.out.println(totalCount);
				}

				page = new PageUtil(pageSize, totalCount);
				// ����ҳ��
				page.setPageNo(pageNo);
				// �����ѯ��ǰҳ���ݵ�sql���

				sql = "SELECT r1.*,r2.totalCost,r2.repairResult,r2.endDate,u.realName as handlerName FROM tb_report  as r1 INNER JOIN tb_repair as r2 ON r1.reportId=r2.reportId  and reportState=2 INNER JOIN tb_user  as u  ON  r1.handlerId=u.userId  and reportState=2 ORDER BY reportId limit  "
						+ (pageNo - 1) * pageSize + "," + pageSize;
				sta = conn.prepareStatement(sql);
				rst = sta.executeQuery();
				// ����һ��List����
							List<Object[]> reports = new ArrayList<Object[]>();
							
						while (rst.next()) {
								 Object[]  obj=new Object[12];
								 obj[0]=rst.getInt(1);
								 obj[1]=rst.getString(2);
								 obj[2]=rst.getString(3);
								 obj[3]=rst.getInt(4);
								 obj[4]=rst.getInt(5);
								 obj[5]=rst.getInt(6);
								 obj[6]=rst.getString(7);
								 obj[7]=rst.getString(8);
								 obj[8]=rst.getFloat(9);
								 obj[9]=rst.getString(10);
								 obj[10]=rst.getString(11);
								 obj[11]=rst.getString(12);
                        System.out.println(obj[10]);
								
								// ��ӵ�������
								reports.add(obj);

							
								
							    
						
				    
				}
				// �������ݵļ���
				page.setData(reports);

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				DBManage.closeAll(rst, sta, conn);
			}
			return page;
		}	
	
		
		/*//ά������/���޵���Ϣ
				public PageUtil getReportDetailes(int pageSize, int pageNo) {
					PageUtil page = null;
					// ��ѯ�û�����ȡ������
					String sql = "select  count(reportId) as totalCount from tb_report  where  reportState=2";
					Connection conn = DBManage.getConn();
					// ��������
					PreparedStatement sta = null;
					// ���������
					ResultSet rst = null;
					try {
						sta = conn.prepareStatement(sql);

						rst = sta.executeQuery();
						// �ܼ�¼��
						int totalCount = 0;
						if (rst.next()) {
							totalCount = rst.getInt("totalCount");
							//System.out.println(totalCount);
						}

						page = new PageUtil(pageSize, totalCount);
						// ����ҳ��
						page.setPageNo(pageNo);
						// �����ѯ��ǰҳ���ݵ�sql���

						sql = "SELECT r1.*,r2.totalCost,r2.repairResult,r2.endDate,u.realName as handlerName FROM tb_report  as r1 INNER JOIN tb_repair as r2 ON r1.reportId=r2.reportId  and reportState=2 INNER JOIN tb_user  as u  ON  r1.handlerId=u.userId  and reportState=2 ORDER BY reportId limit  "
								+ (pageNo - 1) * pageSize + "," + pageSize;
						sta = conn.prepareStatement(sql);
						rst = sta.executeQuery();
						// ����һ��List����
									List<Object[]> reports = new ArrayList<Object[]>();
									
								while (rst.next()) {
										 Object[]  obj=new Object[12];
										 obj[0]=rst.getInt(1);
										 obj[1]=rst.getString(2);
										 obj[2]=rst.getString(3);
										 obj[3]=rst.getInt(4);
										 obj[4]=rst.getInt(5);
										 obj[5]=rst.getInt(6);
										 obj[6]=rst.getString(7);
										 obj[7]=rst.getString(8);
										 obj[8]=rst.getFloat(9);
										 obj[9]=rst.getString(10);
										 obj[10]=rst.getString(11);
										 obj[11]=rst.getString(12);

										
										// ��ӵ�������
										reports.add(obj);

									
										
									    
								
						    
						}
						// �������ݵļ���
						page.setData(reports);

					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						DBManage.closeAll(rst, sta, conn);
					}
					return page;
				}	*/
			
		
	
}
