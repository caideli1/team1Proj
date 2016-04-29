package com.team1.liangxiaolei.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import com.team1.liangxiaolei.bean.TbfeeItem;
import com.team1.liangxiaolei.bean.Tbuser;
import com.team1.liangxiaolei.bean.TbuserCost;
import com.team1.util.DBManage;

public class TbfeeItemdao {
public List<TbfeeItem> getAll(){
		
		String sql="select * from tb_feeItem ";
		Connection conn = DBManage.getConn();
		PreparedStatement sta = null;
		
		try {
			// 操作对象
			List<TbfeeItem> tbfeeItems =new ArrayList<TbfeeItem>();
			sta = conn.prepareStatement(sql);
			ResultSet rst=sta.executeQuery();
			while(rst.next()&&rst!=null){	
				TbfeeItem tbfeeItem=new TbfeeItem();
				tbfeeItem.setFeeId(rst.getInt(1));
			
				tbfeeItem.setFeeName(rst.getString(2));
				
				tbfeeItem.setFee(rst.getFloat(3));
				
				tbfeeItem.setFeeReceDate(rst.getInt(4));
				tbfeeItem.setCreateDate(rst.getString(5));
			    
				tbfeeItems.add(tbfeeItem);
			
			}
			
			return tbfeeItems;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 	
}




public boolean add(TbfeeItem tbfeeItem){
	boolean flag = false;
	String sql = "insert into tb_feeItem(feeName,fee,feeReceDate,createDate)  values(?,?,?,?)";
	Connection conn = DBManage.getConn();
	PreparedStatement sta = null;
	try {
		// 操作对象
	
		sta = conn.prepareStatement(sql);
	   sta.setString(1, tbfeeItem.getFeeName());
		sta.setFloat(2, tbfeeItem.getFee());
		sta.setInt(3, tbfeeItem.getFeeReceDate());	
		sta.setString(4, tbfeeItem.getCreateDate());
	
		
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


public boolean update(TbfeeItem tbfeeItem){
	boolean flag = false;
	String sql = "update  tb_feeItem set feeName=?,fee=?,feeReceDate=?,createDate=? where feeId=?";
	Connection conn = DBManage.getConn();
	PreparedStatement sta = null;
	try {
		// 操作对象
	
		sta = conn.prepareStatement(sql);
	   sta.setString(1, tbfeeItem.getFeeName());
		sta.setFloat(2, tbfeeItem.getFee());
		sta.setInt(3, tbfeeItem.getFeeReceDate());	
		sta.setString(4, tbfeeItem.getCreateDate());
		sta.setInt(5, tbfeeItem.getFeeId());
		
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
public boolean deleAll(Integer[] items_int){
	boolean flag = false;
	
	Connection conn = DBManage.getConn();
	PreparedStatement sta = null;
	try {
		// 操作对象
		
			String sql="delete  from tb_feeitem where feeId in";
			
			 String str = Arrays.toString(items_int);
			 str = str.replace('[', '(').replace(']', ')');
			  sql = sql + str;
			sta = conn.prepareStatement(sql);
			
			int row = sta.executeUpdate();
			if (row > 0) {
				flag = true;
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据删除失败！");
		} finally {
			DBManage.closeAll(null, sta, conn);
		
		}
		return flag;
	}

public TbfeeItem getOne(String feeName){
	TbfeeItem tbfeeItem=new TbfeeItem();
	String sql="select * from tb_feeItem where feeName=?";
	Connection conn = DBManage.getConn();
	PreparedStatement sta = null;
	try {
		// 操作对象
		sta.setString(1, feeName);
		sta = conn.prepareStatement(sql);
		ResultSet rst=sta.executeQuery();
		while(rst.next()&&rst!=null){	
			
			tbfeeItem.setFeeId(rst.getInt(1));
		
			tbfeeItem.setFeeName(rst.getString(2));
			
			tbfeeItem.setFee(rst.getFloat(3));
			
			tbfeeItem.setFeeReceDate(rst.getInt(4));
			tbfeeItem.setCreateDate(rst.getString(5));
				
		}
		
		
		return tbfeeItem ;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} 
}
//根据收费项目id获取收费项目对象
public TbfeeItem getItemById(int id){
	List<TbfeeItem>  items=this.getAll();
	for(TbfeeItem  item : items){
		 if(item.getFeeId()==id){
			 return item;
		 }
	}
	return null;
	
}
public TbfeeItem getItemsById(int id){
	List<TbfeeItem>  items=this.getAll();
	for(TbfeeItem  item : items){
		 if(item.getFeeId()==id){
			 return item;
		 }
	}
	return null;
	
}
public boolean checkUserName(String feeName) {
	// 1.定义sql语句
	String sql = "select feeName from tb_feeItem where feeName=?";
	// 2.然后得到链接
	Connection conn = DBManage.getConn();
	// 3.得到预操作对象
	PreparedStatement sta = null;
	// 结果集
	ResultSet rst = null;
	try {
		sta = conn.prepareStatement(sql);
		sta.setString(1, feeName);
		rst = sta.executeQuery();
		if (rst.next()) {
			return true;
		}
	} catch (Exception e) {
		// TODO: handle exception
	} finally {
		DBManage.closeAll(rst, sta, conn);
	}
	return false;
}

}
