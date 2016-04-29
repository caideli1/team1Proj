package com.team1.liangxiaolei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team1.liangxiaolei.bean.TbfeeItem;
import com.team1.liangxiaolei.bean.Tbuser;
import com.team1.liangxiaolei.bean.TbuserCost;
import com.team1.util.DBManage;
import com.team1.util.PageUtil;



public class Tbusercostdao {
public List<TbuserCost> getAll(){
		
		String sql="select * from tb_userCost ";
		Connection conn = DBManage.getConn();
		PreparedStatement sta = null;
		try {
			// 操作对象
			List<TbuserCost> stuu =new ArrayList<TbuserCost>();
			sta = conn.prepareStatement(sql);
			ResultSet rst=sta.executeQuery();
			while(rst.next()&&rst!=null){	
				TbuserCost student=new TbuserCost();
				student.setUserCostId(rst.getInt(1));
			
				student.setUserId(rst.getInt(2));
				
				student.setFeedId(rst.getInt(3));
				student.setHavaCost(rst.getFloat(4));
			    student.setReveiveId(rst.getInt(5));
			    student.setSendDate(rst.getString(6));
			    student.setRemark(rst.getNString(7));
				stuu.add(student);
			
			}
			
			return stuu;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		
		
	
		}

//public PageUtil getUsercostList(int pageSize, int pageNo) {
//	PageUtil page = null;
//	// 查询用户表，获取总条数
//	String sql = "select  count(userCostId) as totalCount from tb_userCost";
//	Connection conn = DBManage.getConn();
//	// 操作对象
//	PreparedStatement sta = null;
//	// 结果集对象
//	ResultSet rst = null;
//	try {
//		sta = conn.prepareStatement(sql);
//
//		rst = sta.executeQuery();
//		// 总记录数
//		int totalCount = 0;
//		if (rst.next()) {
//			totalCount = rst.getInt("totalCount");
//		}
//
//		page = new PageUtil(pageSize, totalCount);
//		// 设置页码
//		page.setPageNo(pageNo);
//		// 构造查询当前页数据的sql语句
//
//		sql = "select * from tb_userCost s left join tb_user c on s.uerId=c.userId left join tb_feeItem  r on s.feeId=r.feeId   ORDER BY userCostId limit  "
//				+ (pageNo - 1) * pageSize + "," + pageSize;
//		sta = conn.prepareStatement(sql);
//		rst = sta.executeQuery();
//		// 创建一个List集合
//		List<Tbuser> users = new ArrayList<Tbuser>();
//		
//		while (rst.next()) {
//
//			// user.setRoleId(rst.getString(columnIndex))
//			Tbuser user = new Tbuser();
//			user.setUserName(rst.getString(1));
//			user.setRealName(rst.getString(2));
//			user.setRoleId(rst.getString(3));
//
//			// 添加到集合中
//		users.add(user);
//		    
//		}
//		// 设置数据的集合
//		page.setData(users);
//
//	} catch (Exception e) {
//		// TODO: handle exception
//	} finally {
//		DBManage.closeAll(rst, sta, conn);
//	}
//	return page;
//}



public boolean add(TbuserCost tbusercost){
	boolean flag = false;
	String sql = "insert into tb_userCost(userId,feeId,havaCost,reveived,sendDate,remark)  values(?,?,?,?,?,?)";
	Connection conn = DBManage.getConn();
	PreparedStatement sta = null;
	try {
		// 操作对象
	
		sta = conn.prepareStatement(sql);
	   sta.setInt(1, tbusercost.getUserId());
		sta.setInt(2, tbusercost.getFeedId());
		sta.setFloat(3, tbusercost.getHavaCost());	
		sta.setInt(4, tbusercost.getReveived());
		sta.setString(5,tbusercost.getSendDate());
		sta.setString(6, tbusercost.getRemark());
		
		
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


/**
 * 获取缴费项目的下拉列表		
 */
public List<TbfeeItem> getSelectOp(){
	//得到连接
	Connection conn = DBManage.getConn();
	// 操作对象
	PreparedStatement sta = null;
	
	// 结果集对象
	ResultSet rt = null;
	TbfeeItem ti = null;
	List<TbfeeItem> list = new ArrayList<TbfeeItem>();
	
	String sql = "SELECT feeName,feeId from tb_feeitem  ";
	try {
		sta = conn.prepareStatement(sql);
		rt = sta.executeQuery();
		
		while (rt!=null && rt.next()){	
			ti = new TbfeeItem();
			ti.setFeeName(rt.getString(1));
			ti.setFeeId(rt.getInt(2));
			list.add(ti);
		}
		
		
	} catch (Exception e) {
		
		e.printStackTrace();
	} finally{				
		DBManage.closeAll(rt, sta, conn);				
	}
	return list;			
}

		//根据姓名查找用户
	public PageUtil getUserCostList(int pageSize, int pageNo,String realName,int feeId,String beginDate,String endDate) {
		PageUtil page = null;
		String sql = null;
		
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		
		// 结果集对象
		ResultSet rt = null;
		Object[] obj = null;
				
		try {
		
		
		String sql1 = "select us.realName,build.buildingName,o.telephone,f.feeName as '缴费项目',ut.havaCost as '缴费金额',(SELECT realName from tb_user where userId=reveived) as '收费人',ut.sendDate as '收费日期',ut.remark as 备注,ut.userConstId " +
				"			from tb_ownerdetail as o join tb_building as build on o.buildingId=build.buildingId join tb_user as us on o.userId=us.userId join tb_usercost as ut on ut.userId = us.userId join tb_feeitem as f on f.feeId=ut.feeId " +
				"				where us.roleId='r002' ";
		String sql2 = "ORDER BY o.ownerId limit"+" "+(pageNo - 1) * pageSize + "," + pageSize;
		
		if (feeId==0 ){
			sql = sql1;			
		}									
		else{					
			sql =  sql1+"and f.feeId='"+feeId+"'";
			
		}
		
		if (realName!=null && beginDate==null && endDate==null){
			sql = sql + "and us.realName like '%"+realName+"%'";				
		}
		else if (realName==null && beginDate!=null && endDate!=null){
			sql = sql + "and  sendDate BETWEEN '"+beginDate+"' and '"+endDate+"'";
		
		}else if (realName!=null && beginDate!=null && endDate!=null){
			sql = sql+"and us.realName like '%"+realName+"%' and sendDate BETWEEN '"+beginDate+"' and '"+endDate+"'";
		}
		
		sta = conn.prepareStatement(sql);		
		rt = sta.executeQuery();
		int totalCount = 0;
		while (rt.next()){
			totalCount++;
		}
		page = new PageUtil(pageSize, totalCount);
		System.out.println("pageSize:"+pageSize+"totalCount:"+totalCount);
		// 设置页码
		page.setPageNo(pageNo);
		//定义并实例化一个集合
		sql = sql + sql2;
		sta = conn.prepareStatement(sql);		
		rt = sta.executeQuery();
		List<Object[]> list = new ArrayList<Object[]>();
		while ( rt.next()){
		   
			//实例化Object[]数组
			obj = new Object[9];
			obj[0] = rt.getString(1);
			obj[1] = rt.getString(2);
			obj[2] = rt.getString(3);
			obj[3] = rt.getString(4);
			obj[4] = rt.getInt(5);
			obj[5] = rt.getString(6);
			obj[6] = rt.getString(7);
			obj[7] = rt.getString(8);
			obj[8] = rt.getInt(9);
			list.add(obj);
		}
		
		// 设置数据的集合
			page.setData(list);
		
		} catch (Exception e) {
			
		} finally {
			DBManage.closeAll(rt, sta, conn);
		}
		return page;
}
/*
* 删除所选的项
*/
public boolean deleteList(String[] str){
boolean flag = false;
//得到连接
Connection conn = DBManage.getConn();
// 操作对象
PreparedStatement sta = null;

for (String li:str){
	int ll = Integer.parseInt(li);
	String sql = "delete from tb_usercost WHERE userConstId ="+ll;
	System.out.println(sql);
	try {
		sta = conn.prepareStatement(sql);
		sta.executeUpdate();
		flag = true;
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		DBManage.closeAll(null, sta, conn);
	}
	
}		
return flag;
}
	
}
