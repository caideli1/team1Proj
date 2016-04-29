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
       //获取所有保修信息
	/**
	 * 获取每页的保修信息
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	//通过两张表获取所有报修信息
	public PageUtil getReportListTwo(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = "select  count(reportId) as totalCount from tb_report";
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		// 结果集对象
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// 总记录数
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
				//System.out.println(totalCount);
			}

			page = new PageUtil(pageSize, totalCount);
			// 设置页码
			page.setPageNo(pageNo);
			// 构造查询当前页数据的sql语句

			sql = "SELECT  reportId,reportName,question,realName,reportState from  tb_report   INNER JOIN   tb_user  ON  tb_report.userId=tb_user.userId  ORDER BY reportId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// 创建一个List集合
			List<Object[]> reports = new ArrayList<Object[]>();
			
		while (rst.next()) {
				 Object[]  obj=new Object[5];
				 obj[0]=rst.getInt(1);
				 obj[1]=rst.getString(2);
				 obj[2]=rst.getString(3);
				 obj[3]=rst.getString(4);
				 obj[4]=rst.getInt(5);
				// 添加到集合中
				reports.add(obj);
			    
			}
			// 设置数据的集合
			page.setData(reports);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}
	//一张表
	public PageUtil getReportList(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = "select  count(reportId) as totalCount from tb_report";
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		// 结果集对象
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// 总记录数
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
				//System.out.println(totalCount);
			}

			page = new PageUtil(pageSize, totalCount);
			// 设置页码
			page.setPageNo(pageNo);
			// 构造查询当前页数据的sql语句

			sql = "SELECT  * from  tb_report    ORDER BY reportId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// 创建一个List集合
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
				
				// 添加到集合中
				reports.add(report);
			    
			}
			// 设置数据的集合
			page.setData(reports);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}
	
	//更新保修信息
	public  boolean   UpReport(int handlerId,int  reportId) {
		 boolean   flag=false;
		  String  sql="update tb_report set handlerId=?,reportState=1 where  reportId=?";
		  Connection conn = DBManage.getConn();
			// 操作对象
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
	//通过id找到report信息
	public  Tb_report   FindReport(int  reportId) {
		
		  String  sql="select * from tb_report where reportId=?";
		  Connection conn = DBManage.getConn();
			// 操作对象
			PreparedStatement sta = null;
			Tb_report   report=new Tb_report();
			// 结果集
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

//   通过两张表查找所有未受理的报修单
	public PageUtil getUnacceptList(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = "select  count(reportId) as totalCount from tb_report  where  reportState=0";
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		// 结果集对象
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// 总记录数
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
				//System.out.println(totalCount);
			}

			page = new PageUtil(pageSize, totalCount);
			// 设置页码
			page.setPageNo(pageNo);
			// 构造查询当前页数据的sql语句

			sql = "SELECT  reportId,reportName,question,realName,reportState from  tb_report   INNER JOIN   tb_user  ON  tb_report.userId=tb_user.userId and reportState=0 ORDER BY reportId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// 创建一个List集合
			List<Object[]> reports = new ArrayList<Object[]>();
			
		while (rst.next()) {
				 Object[]  obj=new Object[5];
				 obj[0]=rst.getInt(1);
				 obj[1]=rst.getString(2);
				 obj[2]=rst.getString(3);
				 obj[3]=rst.getString(4);
				 obj[4]=rst.getInt(5);
				// 添加到集合中
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
			// 设置数据的集合
			page.setData(reports);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}
	
//  查找所有已受理的报修单
	public PageUtil getAcceptList(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = "select  count(reportId) as totalCount from tb_report  where  reportState=1";
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		// 结果集对象
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// 总记录数
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
				//System.out.println(totalCount);
			}

			page = new PageUtil(pageSize, totalCount);
			// 设置页码
			page.setPageNo(pageNo);
			// 构造查询当前页数据的sql语句

			sql = "SELECT  reportId,reportName,question,realName,reportState from  tb_report   INNER JOIN   tb_user  ON  tb_report.userId=tb_user.userId and reportState=1 ORDER BY reportId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// 创建一个List集合
						List<Object[]> reports = new ArrayList<Object[]>();
						
					while (rst.next()) {
							 Object[]  obj=new Object[5];
							 obj[0]=rst.getInt(1);
							 obj[1]=rst.getString(2);
							 obj[2]=rst.getString(3);
							 obj[3]=rst.getString(4);
							 obj[4]=rst.getInt(5);
							// 添加到集合中
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
			// 设置数据的集合
			page.setData(reports);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}
//  查找所有已处理的报修单
	public PageUtil getFinishList(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = "select  count(reportId) as totalCount from tb_report  where  reportState=2";
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		// 结果集对象
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// 总记录数
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
				//System.out.println(totalCount);
			}

			page = new PageUtil(pageSize, totalCount);
			// 设置页码
			page.setPageNo(pageNo);
			// 构造查询当前页数据的sql语句

			sql = "SELECT  reportId,reportName,question,realName,reportState from  tb_report   INNER JOIN   tb_user  ON  tb_report.userId=tb_user.userId and reportState=2 ORDER BY reportId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// 创建一个List集合
						List<Object[]> reports = new ArrayList<Object[]>();
						
					while (rst.next()) {
							 Object[]  obj=new Object[5];
							 obj[0]=rst.getInt(1);
							 obj[1]=rst.getString(2);
							 obj[2]=rst.getString(3);
							 obj[3]=rst.getString(4);
							 obj[4]=rst.getInt(5);
							// 添加到集合中
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
			// 设置数据的集合
			page.setData(reports);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}
	//一张表获取已处理信息
			public PageUtil getFinishListTwo(int pageSize, int pageNo) {
				PageUtil page = null;
				// 查询用户表，获取总条数
				String sql = "select  count(reportId) as totalCount from tb_report";
				Connection conn = DBManage.getConn();
				// 操作对象
				PreparedStatement sta = null;
				// 结果集对象
				ResultSet rst = null;
				try {
					sta = conn.prepareStatement(sql);

					rst = sta.executeQuery();
					// 总记录数
					int totalCount = 0;
					if (rst.next()) {
						totalCount = rst.getInt("totalCount");
						//System.out.println(totalCount);
					}

					page = new PageUtil(pageSize, totalCount);
					// 设置页码
					page.setPageNo(pageNo);
					// 构造查询当前页数据的sql语句

					sql = "SELECT  * from  tb_report where reportState=2  ORDER BY reportId limit  "
							+ (pageNo - 1) * pageSize + "," + pageSize;
					sta = conn.prepareStatement(sql);
					rst = sta.executeQuery();
					// 创建一个List集合
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
						
						// 添加到集合中
						reports.add(report);
					    
					}
					// 设置数据的集合
					page.setData(reports);

				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					DBManage.closeAll(rst, sta, conn);
				}
				return page;
			}
	
	
	
	
	//两张表录入保修信息页面
	
	public PageUtil getAddRepList(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = "select  count(reportId) as totalCount from tb_report  where  reportState=1";
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		// 结果集对象
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// 总记录数
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
				//System.out.println(totalCount);
			}

			page = new PageUtil(pageSize, totalCount);
			// 设置页码
			page.setPageNo(pageNo);
			// 构造查询当前页数据的sql语句

			sql = "SELECT  reportId,reportName,question,handlerId,reportState,reportDate,reamark,realName from  tb_report   INNER JOIN   tb_user  ON  tb_report.userId=tb_user.userId and reportState=1 ORDER BY reportId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// 创建一个List集合
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
							
							// 添加到集合中
							reports.add(obj);

						
							
						    
					
			    
			}
			// 设置数据的集合
			page.setData(reports);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}
	
	//一张表获取报修信息
		public PageUtil getAddRepListTwo(int pageSize, int pageNo) {
			PageUtil page = null;
			// 查询用户表，获取总条数
			String sql = "select  count(reportId) as totalCount from tb_report";
			Connection conn = DBManage.getConn();
			// 操作对象
			PreparedStatement sta = null;
			// 结果集对象
			ResultSet rst = null;
			try {
				sta = conn.prepareStatement(sql);

				rst = sta.executeQuery();
				// 总记录数
				int totalCount = 0;
				if (rst.next()) {
					totalCount = rst.getInt("totalCount");
					//System.out.println(totalCount);
				}

				page = new PageUtil(pageSize, totalCount);
				// 设置页码
				page.setPageNo(pageNo);
				// 构造查询当前页数据的sql语句

				sql = "SELECT  * from  tb_report where reportState=1   ORDER BY reportId limit  "
						+ (pageNo - 1) * pageSize + "," + pageSize;
				sta = conn.prepareStatement(sql);
				rst = sta.executeQuery();
				// 创建一个List集合
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
					
					// 添加到集合中
					reports.add(report);
				    
				}
				// 设置数据的集合
				page.setData(reports);

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				DBManage.closeAll(rst, sta, conn);
			}
			return page;
		}
	
	
	//录入报修信息后跟新信息
		public  boolean   UpRepairt(int  reportId) {
			 boolean   flag=false;
			  String  sql="update tb_report set reportState=2 where  reportId=?";
			  Connection conn = DBManage.getConn();
				// 操作对象
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
	
	//维修详情/维修单信息
		public PageUtil getReportDetailes(int pageSize, int pageNo) {
			PageUtil page = null;
			// 查询用户表，获取总条数
			String sql = "select  count(reportId) as totalCount from tb_report  where  reportState=2";
			Connection conn = DBManage.getConn();
			// 操作对象
			PreparedStatement sta = null;
			// 结果集对象
			ResultSet rst = null;
			try {
				sta = conn.prepareStatement(sql);

				rst = sta.executeQuery();
				// 总记录数
				int totalCount = 0;
				if (rst.next()) {
					totalCount = rst.getInt("totalCount");
					//System.out.println(totalCount);
				}

				page = new PageUtil(pageSize, totalCount);
				// 设置页码
				page.setPageNo(pageNo);
				// 构造查询当前页数据的sql语句

				sql = "SELECT r1.*,r2.totalCost,r2.repairResult,r2.endDate,u.realName as handlerName FROM tb_report  as r1 INNER JOIN tb_repair as r2 ON r1.reportId=r2.reportId  and reportState=2 INNER JOIN tb_user  as u  ON  r1.handlerId=u.userId  and reportState=2 ORDER BY reportId limit  "
						+ (pageNo - 1) * pageSize + "," + pageSize;
				sta = conn.prepareStatement(sql);
				rst = sta.executeQuery();
				// 创建一个List集合
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
								
								// 添加到集合中
								reports.add(obj);

							
								
							    
						
				    
				}
				// 设置数据的集合
				page.setData(reports);

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				DBManage.closeAll(rst, sta, conn);
			}
			return page;
		}	
	
		
		/*//维修详情/保修单信息
				public PageUtil getReportDetailes(int pageSize, int pageNo) {
					PageUtil page = null;
					// 查询用户表，获取总条数
					String sql = "select  count(reportId) as totalCount from tb_report  where  reportState=2";
					Connection conn = DBManage.getConn();
					// 操作对象
					PreparedStatement sta = null;
					// 结果集对象
					ResultSet rst = null;
					try {
						sta = conn.prepareStatement(sql);

						rst = sta.executeQuery();
						// 总记录数
						int totalCount = 0;
						if (rst.next()) {
							totalCount = rst.getInt("totalCount");
							//System.out.println(totalCount);
						}

						page = new PageUtil(pageSize, totalCount);
						// 设置页码
						page.setPageNo(pageNo);
						// 构造查询当前页数据的sql语句

						sql = "SELECT r1.*,r2.totalCost,r2.repairResult,r2.endDate,u.realName as handlerName FROM tb_report  as r1 INNER JOIN tb_repair as r2 ON r1.reportId=r2.reportId  and reportState=2 INNER JOIN tb_user  as u  ON  r1.handlerId=u.userId  and reportState=2 ORDER BY reportId limit  "
								+ (pageNo - 1) * pageSize + "," + pageSize;
						sta = conn.prepareStatement(sql);
						rst = sta.executeQuery();
						// 创建一个List集合
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

										
										// 添加到集合中
										reports.add(obj);

									
										
									    
								
						    
						}
						// 设置数据的集合
						page.setData(reports);

					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						DBManage.closeAll(rst, sta, conn);
					}
					return page;
				}	*/
			
		
	
}
