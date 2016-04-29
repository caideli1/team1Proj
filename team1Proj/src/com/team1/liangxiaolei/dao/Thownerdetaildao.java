package com.team1.liangxiaolei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team1.liangxiaolei.bean.TbfeeItem;
import com.team1.liangxiaolei.bean.Tbownerdetail;
import com.team1.liangxiaolei.bean.Tbuser;
import com.team1.util.DBManage;

public class Thownerdetaildao {
	public List<Object[]> getAll(int userId){
		// 初始化为null
		
		String sql = "select s.userId,t.realName,c.buildingName,s.houseNumber,s.cardId,s.telephone,s.enterDate,s.ownerId ,s.buildingId,s.houseUint,s.houseFloor from tb_ownerdetail s  inner join tb_building c on s.buildingId=c.buildingId inner join tb_user t on s.userId=t.userId where t.userId=?";
		// 获取连接对象
	
		Connection conn = DBManage.getConn();
		  List<Object[] >  list=new ArrayList<Object[] >();
		PreparedStatement sta = null;
		// 结果集对象
		ResultSet rst = null;
		try {
			
			sta = conn.prepareStatement(sql);sta.setInt(1, userId);
			rst = sta.executeQuery();
		
			while (rst.next()) {
				
	        Object[]  obj=new  Object[11];
	    	
	       obj[0]=rst.getInt(1);
	            obj[1]=rst.getString(2);
	            obj[2]=rst.getString(3);
	    		   obj[3]=rst.getString(4);
	    		   obj[4]=rst.getString(5);
	    		   obj[5]=rst.getString(6);
	    		   obj[6]=rst.getString(7);
	    		   obj[7]=rst.getInt(8);
	    		   obj[8]=rst.getInt(9);
	    		   obj[9]=rst.getInt(10);
	    		   obj[10]=rst.getInt(11);
	       list.add(obj);
	       }
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}

		return  list;
	}
	
	public boolean update(Tbownerdetail tbownerdetail){
		boolean flag = false;
		String sql = "update  tb_ownerDetail set cardId=?,telephone=?,enterDate=?where ownerId=? ";
		Connection conn = DBManage.getConn();
		PreparedStatement sta = null;
		try {
			// 操作对象
		
			sta = conn.prepareStatement(sql);
		 
			sta.setString(1, tbownerdetail.getCardId());
			sta.setString(2, tbownerdetail.getTelephone());
			sta.setString(3, tbownerdetail.getEnterDate());
			sta.setInt(4, tbownerdetail.getOwnerId());
			// 影响的行数
			int row = sta.executeUpdate();
			if (row > 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据更新失败！");
		} finally {
			DBManage.closeAll(null, sta, conn);
		
		}
		return flag;



	}
	
	
	
}
