package com.team1.liuzhifeng.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.team1.liuzhifeng.bean.BuildingBean;
import com.team1.util.DBManage;
import com.team1.util.PageUtil;

public class BuildingDao {
	
	/**
	 * 查询tb_building表中的所有信息
	 */
	
//	public Vector<BuildingBean> getInfo(){
//		//定义并实例化一个集合
//		Vector<BuildingBean> vc = new Vector<BuildingBean>();
//		//
//		BuildingBean buildbean = null;
//		//定义数据库连接
//		Connection conn = null;
//		//定义预操作对象
//		PreparedStatement sta = null;
//		//定义结果集
//		ResultSet rt = null;
//		//sql语句
//		String sql = "select * from tb_building";
//		//得到数据库连接
//		conn = DBManage.getConn();
//		try {
//			sta = conn.prepareStatement(sql);
//			rt = sta.executeQuery();
//			
//			while (rt!=null && rt.next()){
//				buildbean = new BuildingBean();
//				buildbean.setBuildingId(rt.getInt(1));
//				buildbean.setBuildingName(rt.getString(2));
//				buildbean.setFloorNumber(rt.getInt(3));
//				buildbean.setUnitNumber(rt.getInt(4));
//				buildbean.setHouseSeries(rt.getInt(5));
//				buildbean.setLivingNumber(rt.getInt(6));
//				buildbean.setDeveloper(rt.getString(7));
//				//把buildbean对象添加到vc集合中
//				vc.add(buildbean);			
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally{
//			//关闭连接
//			DBManage.closeAll(rt, sta, conn);
//		}		
//		return vc;
//	}
	
	
	
	public PageUtil getBuildList(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = "select  count(buildingId) as totalCount from tb_building";
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
			sql = "SELECT  t.buildingId,t.buildingName,t.floorNumber,t.unitNumber,t.houseSeries,t.livingNumber,t.developer FROM tb_building AS t limit"+" "+(pageNo - 1) * pageSize + "," + pageSize;
			
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			//定义并实例化一个集合
			List<Object[]> list = new ArrayList<Object[]>();
			while ( rt.next()){
				//实例化Object[]数组
				obj = new Object[7];
					
				obj[0] = rt.getInt(1);
				obj[1] = rt.getString(2);
				obj[2] = rt.getInt(3);
				obj[3] = rt.getInt(4);
				obj[4] = rt.getInt(5);
				obj[5] = rt.getInt(6);
				obj[6] = rt.getString(7);
				
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
