package com.team1.huangsai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.team1.huangsai.bean.Tb_repair;

import com.team1.util.DBManage;

public class Tb_repairDao {
//  添加修信息
	public  boolean   addRepair(Tb_repair tb_repair){
		boolean  flag=false;
     String  sql="insert into tb_repair (reportId,userId,repairResult,endDate,totalCost) values(?,?,?,?,?)";
  // 得到链接
  		Connection conn = DBManage.getConn();
  	// 得到预操作对象
  			PreparedStatement sta = null;
  			try {
  				sta = conn.prepareStatement(sql);
  				sta.setInt(1, tb_repair.getReportId());
  				sta.setInt(2, tb_repair.getUserId());
  				sta.setString(3, tb_repair.getRepairResult());
  				sta.setString(4, tb_repair.getEndDate());
  				sta.setFloat(5, tb_repair.getTotalCost());

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
	//获取信息
	public  Tb_repair  getRepair(int reportId){
		
		Tb_repair  repair=new Tb_repair();
     String  sql="select * from  tb_repair where reportId=?";
  // 得到链接
  		Connection conn = DBManage.getConn();
  	// 得到预操作对象
  			PreparedStatement sta = null;
  			ResultSet rst = null;
  			try {
  				
  				sta = conn.prepareStatement(sql);
  				sta.setInt(1, reportId);

  				rst = sta.executeQuery();
  				//System.out.println(sql);
  				
				if(rst.next()) {
					
					repair.setRepareId(rst.getInt(1));
					repair.setReportId(rst.getInt(2));
					
					repair.setUserId(rst.getInt(3));
					repair.setRepairResult(rst.getString(4));
					repair.setEndDate(rst.getString(5));
					repair.setTotalCost(rst.getFloat(6));
					
					
						
						

					
						
		}
				return  repair;
  			} catch (Exception e) {
  				e.printStackTrace();
  				return null;
  			} finally {
  				DBManage.closeAll(null, sta, conn);
  			}
  			
  		}
	
	
}
