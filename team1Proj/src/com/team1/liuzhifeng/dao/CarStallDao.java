package com.team1.liuzhifeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team1.liuzhifeng.bean.CarStallBean;
import com.team1.util.DBManage;
import com.team1.util.PageUtil;

public class CarStallDao {
	
//	public List<CarStallBean> getInfo(){
//		
//		//实例化集合对象
//		List<CarStallBean> list = new ArrayList<CarStallBean>();
//		//实例化CarStallBean对象
//		CarStallBean carstallbean = null;
//		//定义数据库连接
//		Connection conn = null;
//		//定义预操作对象
//		PreparedStatement sta = null;
//		//定义结果集
//		ResultSet rt = null;
//		//定义sql语句
//		String sql = "SELECT * from tb_carstall";
//		//得到数据库连接
//		conn = DBManage.getConn();
//		try {
//			sta = conn.prepareStatement(sql);
//			rt = sta.executeQuery();
//			
//			while (rt!=null && rt.next()){
//				carstallbean = new CarStallBean();
//				carstallbean.setStallNo(rt.getString(1));
//				carstallbean.setStallAdress(rt.getString(2));
//				carstallbean.setUserId(rt.getInt(3));
//				carstallbean.setStallState(rt.getInt(4));
//				carstallbean.setStartDate(rt.getString(5));
//				carstallbean.setEndDate(rt.getString(6));
//				System.out.println(rt.getString(1));
//				list.add(carstallbean);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  finally{
//			DBManage.closeAll(rt, sta, conn);
//		}
//		//返回一个List集合
//		return list;
//	}
	
	
	
	/**
	 * 获取每页的业主列表
	 */
	public PageUtil getCarstallList(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = " SELECT count(stallNo) as totalCount from tb_carstall";
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		
		// 结果集对象
		ResultSet rt = null;
		CarStallBean carbean = null;
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

			sql = "SELECT * from tb_carstall order by stallNo limit"
					+ " "+(pageNo - 1) * pageSize + "," + pageSize;
			
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			
			//定义并实例化一个集合
			List<CarStallBean> list = new ArrayList<CarStallBean>();
			while ( rt.next()){
				
				carbean = new CarStallBean();
				
				carbean.setStallNo(rt.getString(1));
				carbean.setStallAdress(rt.getString(2)) ;
				carbean.setUserId(rt.getInt(3)) ;
				carbean.setStallState(rt.getInt(4)) ;
				carbean.setStartDate(rt.getString(5));
				carbean.setEndDate(rt.getString(6));	
				list.add(carbean);
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
