package com.team1.liangxiaolei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.team1.liangxiaolei.bean.Tbcomplain;
import com.team1.util.DBManage;


public class Tbcomplaindao {
	
	public boolean add(Tbcomplain tbcomplain){
		boolean flag = false;
		String sql = "insert into tb_complain(userId,complainText,reamark,complainDate,complainState,doWithId,ComplainResult,endDate)  values(?,?,?,?,?,?,?,?)";
		Connection conn = DBManage.getConn();
		PreparedStatement sta = null;
		try {
			// 操作对象
		
			sta = conn.prepareStatement(sql);
		   sta.setInt(1, tbcomplain.getUserId());
			sta.setString(2, tbcomplain.getComplainText());
			sta.setString(3, tbcomplain.getReamark());	
			sta.setString(4, tbcomplain.getComplainDate());
			sta.setInt(5, tbcomplain.getComplainState());
			sta.setInt(6,tbcomplain.getDoWithId());
			sta.setString(7, tbcomplain.getComplainResult());
			sta.setString(8, tbcomplain.getEndDate());
			// 影响的行数
			int row = sta.executeUpdate();
			if (row > 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据插入失败！");
		} finally {
			DBManage.closeAll(null, sta, conn);
		
		}
		return flag;
	}
	

}
