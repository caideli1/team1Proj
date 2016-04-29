package com.team1.huangsai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team1.util.DBManage;

public class Tb_userCostDao {
//按项目统计
	
public   List<Object[]>    getFee(){
		
	 List<Object[]>   fees = new ArrayList<Object[]>();
	     String  sql="select tb_userCost.feeId,SUM(havaCost),feeName from tb_userCost  INNER JOIN  tb_feeitem  ON  tb_userCost.feeId=tb_feeitem.feeId GROUP BY  tb_userCost.feeId";
	  // 得到链接
	  		Connection conn = DBManage.getConn();
	  	// 得到预操作对象
	  			PreparedStatement sta = null;
	  			ResultSet rst = null;
	  			try {
	  				
	  				sta = conn.prepareStatement(sql); 
	  				
	  				rst = sta.executeQuery();
	  				//System.out.println(sql);
	  				
					while(rst.next()) {
						Object[]  obj=new Object[3];
						obj[0]=rst.getInt(1);
						obj[1]=rst.getFloat(2);
						obj[2]=rst.getString(3);
						fees.add(obj);
			}
					return  fees;
	  			} catch (Exception e) {
	  				e.printStackTrace();
	  				return null;
	  			} finally {
	  				DBManage.closeAll(null, sta, conn);
	  			}
	  			
	  		}
	
	
	
	public   List<Object[]>    getFee(String startDate,String  endDate){
		
		List<Object[]> fees = new ArrayList<Object[]>();
	     String  sql="select tb_userCost.feeId,SUM(havaCost),feeName from tb_userCost  INNER JOIN  tb_feeitem  ON  tb_userCost.feeId=tb_feeitem.feeId  WHERE tb_userCost.sendDate   BETWEEN  ? and ?  GROUP BY  tb_userCost.feeId";
	  // 得到链接
	  		Connection conn = DBManage.getConn();
	  	// 得到预操作对象
	  			PreparedStatement sta = null;
	  			ResultSet rst = null;
	  			try {
	  				
	  				sta = conn.prepareStatement(sql); 
	  				sta.setString(1, startDate);
	  				sta.setString(2, endDate);
	  				rst = sta.executeQuery();
	  				//System.out.println(sql);
	  				
					while(rst.next()) {
						Object[]  obj=new Object[3];	
						
						
						obj[0]=rst.getInt(1);
						obj[1]=rst.getFloat(2);
						obj[2]=rst.getString(3)		;
						fees.add(obj)	;
			}
					return  fees;
	  			} catch (Exception e) {
	  				e.printStackTrace();
	  				return null;
	  			} finally {
	  				DBManage.closeAll(null, sta, conn);
	  			}
	  			
	  		}
		
		
	}         
	

