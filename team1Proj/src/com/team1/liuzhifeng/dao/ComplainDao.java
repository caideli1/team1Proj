package com.team1.liuzhifeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.team1.liuzhifeng.bean.ComplainBean;
import com.team1.util.DBManage;
import com.team1.util.PageUtil;

public class ComplainDao {
	
	//所有投诉记录
	public PageUtil getComplainlList(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = " SELECT count(complainId) as totalCount from tb_complain";
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		
		// 结果集对象
		ResultSet rt = null;
		Object[] obj = null;
		try {
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			// 总记录数
			int totalCount = 0;
			if (rt.next()) {
				totalCount = rt.getInt("totalCount");
			}

			page = new PageUtil(pageSize, totalCount);
			// 设置页码
			page.setPageNo(pageNo);
			// 构造查询当前页数据的sql语句

			sql = "SELECT c.complainId,c.complainText,u.realName,c.complainDate,c.complainState,c.reamark " +
					"FROM tb_complain AS c ,tb_user AS u " +
						"WHERE c.userId = u.userId order by c.complainId limit "
							+ " "+(pageNo - 1) * pageSize + "," + pageSize;
			
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			
			//定义并实例化一个集合
			List<Object[]> list = new ArrayList<Object[]>();
			while ( rt.next()){
				obj = new Object[6];
				obj[0] = rt.getInt(1);
				obj[1] = rt.getString(2);
				obj[2] = rt.getString(3);
				obj[3] = rt.getString(4);
				obj[4] = rt.getString(5);	
				obj[5] = rt.getString(6);
				list.add(obj);
			}
			// 设置数据的集合
			page.setData(list);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rt, sta, conn);
		}
		return page;
	}

	/**
	 * 根据complianId查询
	 */
	public Object[] serachcom(int complainId){
		Object[] obj = null;
		
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		//定义结果集
		 ResultSet rt = null;
		//定义sql语句
		String sql = "select u.realName,c.complainText,c.complainDate,c.reamark from tb_complain as c,tb_user as u where c.userId = u.userId and c.complainId='"+complainId+"'";
		try {
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
					
			while (rt!=null &&rt.next()){
				obj = new Object[4];
				obj[0] = rt.getString(1);
				obj[1] = rt.getString(2);
				obj[2] = rt.getString(3);
				obj[3] = rt.getString(4);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManage.closeAll(rt, sta, conn);
		}		
		return obj;
	}
	
	
	
	/**
	 * 分配处理人员
	 * 也就是 向数据库表更新记录
	 */	
	public  boolean addAllot(int dowithId,int complainState, int complainId ){
		
		boolean flag = false;		
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;		
		//定义sql语句
		String sql = "update tb_complain set doWithId=?,complainState=? where complainId=?";				
		try {
			sta = conn.prepareStatement(sql);
			sta.setInt(1, dowithId);
			sta.setInt(2, complainState);
			sta.setInt(3, complainId);
			sta.executeUpdate();
			
			flag= true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManage.closeAll(null, sta, conn);
		}
		return flag;
	}
	/**
	 * 录入处理结果
	 */
	public  boolean addDowithServ(int dowithId,int complainState,String ComplainResult,String endDate, int complainId ){
		
		boolean flag = false;
		
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		
		//定义sql语句
		String sql = "update tb_complain set doWithId=?,complainState=?,ComplainResult=?,endDate=? where complainId=?";
				
		try {
			sta = conn.prepareStatement(sql);
			sta.setInt(1, dowithId);
			sta.setInt(2, complainState);
			sta.setString(3,ComplainResult);
			sta.setString(4,endDate);
			sta.setInt(5, complainId);
			sta.executeUpdate();
		
			flag= true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManage.closeAll(null, sta, conn);
		}
		return flag;
	}
	/**
	 * 处理详情
	 */
	public Object[] getDetail_one(int complainId){
		
		Object[] obj = null;
		
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		//定义结果集
		 ResultSet rt = null;
		//定义sql语句
		String sql = "select u.realName,c.complainText,c.complainDate,c.reamark,(SELECT realName from tb_user where userId = doWithId),c.endDate from tb_complain as c,tb_user as u where c.userId = u.userId and c.complainId='"+complainId+"'";
		System.out.println(sql);
		try {
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			
			
			while (rt!=null &&rt.next()){
				obj = new Object[6];
				obj[0] = rt.getString(1);
				obj[1] = rt.getString(2);
				obj[2] = rt.getString(3);
				obj[3] = rt.getString(4);
				obj[4] = rt.getString(5);
				obj[5] = rt.getString(6);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManage.closeAll(rt, sta, conn);
		}		
		return obj;
	}
	
	/**
	 * 处理详情2
	 */
	public String getDetail_two(int complainId){
		Object[] obj = null;
		
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		//定义结果集
		 ResultSet rt = null;
		 //定义一个字符串
		 String str = null;
		//定义sql语句
		String sql = "SELECT  t.realName from tb_user as t ,tb_complain as d WHERE t.userId=d.doWithId and d.complainId='"+complainId+"'";
		try {
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			
			while (rt!=null &&rt.next()){
				str = rt.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManage.closeAll(rt, sta, conn);
		}		
		return str;
	}
	
	
	
	/**
	 * 未受理投诉
	 */

	public PageUtil getUnDoWithlList(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = " SELECT count(complainId) as totalCount from tb_complain where complainState=0";
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		
		// 结果集对象
		ResultSet rt = null;
		Object[] obj = null;
		try {
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			// 总记录数
			int totalCount = 0;
			if (rt.next()) {
				totalCount = rt.getInt("totalCount");
			}

			page = new PageUtil(pageSize, totalCount);
			// 设置页码
			page.setPageNo(pageNo);
			// 构造查询当前页数据的sql语句

			sql = "SELECT c.complainId,c.complainText,u.userName,c.complainDate,c.complainState,c.reamark " +
					"FROM tb_complain AS c ,tb_user AS u " +
						"WHERE c.userId = u.userId and complainState=0 order by c.complainId limit "
							+ " "+(pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			
			//定义并实例化一个集合
			List<Object[]> list = new ArrayList<Object[]>();
			while ( rt.next()){
				obj = new Object[6];
				obj[0] = rt.getInt(1);
				obj[1] = rt.getString(2);
				obj[2] = rt.getString(3);
				obj[3] = rt.getString(4);
				obj[4] = rt.getString(5);	
				obj[5] = rt.getString(6);
				list.add(obj);
			}
			// 设置数据的集合
			page.setData(list);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rt, sta, conn);
		}
		return page;
	}
	
	/**
	 *已受理投诉
	 */
	public PageUtil getDoWithList(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = " SELECT count(complainId) as totalCount from tb_complain where complainState=1";
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		
		// 结果集对象
		ResultSet rt = null;
		Object[] obj = null;
		try {
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			// 总记录数
			int totalCount = 0;
			if (rt.next()) {
				totalCount = rt.getInt("totalCount");
			}

			page = new PageUtil(pageSize, totalCount);
			// 设置页码
			page.setPageNo(pageNo);
			// 构造查询当前页数据的sql语句

			sql = "SELECT c.complainId,c.complainText,u.userName,c.complainDate,c.complainState,c.reamark " +
					"FROM tb_complain AS c ,tb_user AS u " +
						"WHERE c.userId = u.userId and complainState=1 order by c.complainId limit "
							+ " "+(pageNo - 1) * pageSize + "," + pageSize;
			System.out.println(sql);
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			
			//定义并实例化一个集合
			List<Object[]> list = new ArrayList<Object[]>();
			while ( rt.next()){
				obj = new Object[6];
				obj[0] = rt.getInt(1);
				obj[1] = rt.getString(2);
				obj[2] = rt.getString(3);
				obj[3] = rt.getString(4);
				obj[4] = rt.getString(5);	
				obj[5] = rt.getString(6);
				list.add(obj);
			}
			// 设置数据的集合
			page.setData(list);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rt, sta, conn);
		}
		return page;
	}
	/**
	 * 已处理投诉
	 */
	public PageUtil getFinishComList(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = " SELECT count(complainId) as totalCount from tb_complain where complainState=2";
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		
		// 结果集对象
		ResultSet rt = null;
		Object[] obj = null;
		try {
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			// 总记录数
			int totalCount = 0;
			if (rt.next()) {
				totalCount = rt.getInt("totalCount");
			}

			page = new PageUtil(pageSize, totalCount);
			// 设置页码
			page.setPageNo(pageNo);
			// 构造查询当前页数据的sql语句

			sql = "SELECT c.complainId,c.complainText,u.realName,c.complainDate,c.complainState,c.reamark " +
					"FROM tb_complain AS c ,tb_user AS u " +
						"WHERE c.userId = u.userId and complainState=2 order by c.complainId limit "
							+ " "+(pageNo - 1) * pageSize + "," + pageSize;
			
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			
			//定义并实例化一个集合
			List<Object[]> list = new ArrayList<Object[]>();
			while ( rt.next()){
				obj = new Object[6];
				obj[0] = rt.getInt(1);
				obj[1] = rt.getString(2);
				obj[2] = rt.getString(3);
				obj[3] = rt.getString(4);
				obj[4] = rt.getString(5);	
				obj[5] = rt.getString(6);
				list.add(obj);
			}
			// 设置数据的集合
			page.setData(list);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rt, sta, conn);
		}
		return page;
	}
	
	
	
}
