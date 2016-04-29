package com.team1.liangxiaolei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team1.liangxiaolei.bean.Tbcarstall;
import com.team1.liangxiaolei.bean.Tbuser;
import com.team1.util.DBManage;
import com.team1.util.PageUtil;

public class Tbcarstalldao {
	
	
	public PageUtil getRestcarList(int pageSize, int pageNo) {
		PageUtil page2 = null;
		// 查询用户表，获取总条数
		String sql = "select  count(*) as totalCount from tb_carstall";
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
			}

			page2 = new PageUtil(pageSize, totalCount);
			// 设置页码
			page2.setPageNo(pageNo);
			// 构造查询当前页数据的sql语句

			sql = "select  stallNo,stallAdress,stallState from tb_carstall ORDER BY stallNo limit "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			
			rst = sta.executeQuery();
			// 创建一个List集合
			List<Tbcarstall> tbcarStalls = new ArrayList<Tbcarstall>();
			
			while (rst.next()) {

			
				Tbcarstall  tbcarStall= new Tbcarstall();
				 tbcarStall.setStallNo(rst.getString(1));
				 tbcarStall.setStallAdress(rst.getString(2));
			
				 tbcarStall.setStallState(rst.getInt(3));
				
				

				// 添加到集合中
				 tbcarStalls.add(tbcarStall);
			    
			}
			// 设置数据的集合
			page2.setData(tbcarStalls);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page2;
	}

}
