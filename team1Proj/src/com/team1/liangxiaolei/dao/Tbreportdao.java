package com.team1.liangxiaolei.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.team1.liangxiaolei.bean.Tbreport;
import com.team1.util.DBManage;

public class Tbreportdao {

	public boolean seletbreport(Tbreport tbreport){
		String sql="insert into Tb_report(userId,reportName,question,reamark,reportDate,handlerId,reportState) values(?,?,?,?,?,?,?)";
		boolean flag = false;
		Connection conn = (Connection) DBManage.getConn();
		PreparedStatement sta = null;
		
		
		try {
			sta = conn.prepareStatement(sql);
			
			   sta.setInt(1, tbreport.getUserId());
				sta.setString(2, tbreport.getReportName());
				sta.setString(3, tbreport.getQuestion());	
				sta.setString(4, tbreport.getReamark());
				sta.setString(5, tbreport.getReportDate());
				sta.setInt(6, tbreport.getHandlerId());
				sta.setInt(7, tbreport.getReportState());
				int row = sta.executeUpdate();
				if (row > 0) {
					flag = true;
				}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();System.out.println(" ˝æ›≤Â»Î ß∞‹£°");
		}
		finally {
			DBManage.closeAll(null, sta, conn);
		
		}
		return flag;
		
	}
	
	
}
