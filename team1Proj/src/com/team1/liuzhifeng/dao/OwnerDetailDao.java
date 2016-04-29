package com.team1.liuzhifeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.team1.liuzhifeng.bean.OwnerDetailBean;
import com.team1.util.DBManage;
import com.team1.util.PageUtil;

public class OwnerDetailDao {

	
	/**
	 * 获取每页的业主列表
	 */
	public PageUtil getOwnerList(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = "select  count(ownerId) as totalCount from tb_ownerdetail";
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

			sql = "select us.realName,build.buildingName,o.houseFloor,o.houseUint,o.houseNumber,o.cardId,o.telephone,o.enterDate " +
					"from tb_ownerdetail as o join tb_building as build on o.buildingId=build.buildingId " +
						"join tb_user as us on o.userId=us.userId where us.roleId='r002' ORDER BY o.ownerId limit"
					+ " "+(pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			//定义并实例化一个集合
			List<Object[]> list = new ArrayList<Object[]>();
			while ( rt.next()){
				//实例化Object[]数组
				obj = new Object[8];
				obj[0] = rt.getString(1);
				obj[1] = rt.getString(2);
				obj[2] = rt.getInt(3);
				obj[3] = rt.getInt(4);
				obj[4] = rt.getString(5);
				obj[5] = rt.getString(6);
				obj[6] = rt.getString(7);
				obj[7] = rt.getString(8);				
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
