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
			// ��������
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
//	// ��ѯ�û�����ȡ������
//	String sql = "select  count(userCostId) as totalCount from tb_userCost";
//	Connection conn = DBManage.getConn();
//	// ��������
//	PreparedStatement sta = null;
//	// ���������
//	ResultSet rst = null;
//	try {
//		sta = conn.prepareStatement(sql);
//
//		rst = sta.executeQuery();
//		// �ܼ�¼��
//		int totalCount = 0;
//		if (rst.next()) {
//			totalCount = rst.getInt("totalCount");
//		}
//
//		page = new PageUtil(pageSize, totalCount);
//		// ����ҳ��
//		page.setPageNo(pageNo);
//		// �����ѯ��ǰҳ���ݵ�sql���
//
//		sql = "select * from tb_userCost s left join tb_user c on s.uerId=c.userId left join tb_feeItem  r on s.feeId=r.feeId   ORDER BY userCostId limit  "
//				+ (pageNo - 1) * pageSize + "," + pageSize;
//		sta = conn.prepareStatement(sql);
//		rst = sta.executeQuery();
//		// ����һ��List����
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
//			// ��ӵ�������
//		users.add(user);
//		    
//		}
//		// �������ݵļ���
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
		// ��������
	
		sta = conn.prepareStatement(sql);
	   sta.setInt(1, tbusercost.getUserId());
		sta.setInt(2, tbusercost.getFeedId());
		sta.setFloat(3, tbusercost.getHavaCost());	
		sta.setInt(4, tbusercost.getReveived());
		sta.setString(5,tbusercost.getSendDate());
		sta.setString(6, tbusercost.getRemark());
		
		
		// Ӱ�������
		int row = sta.executeUpdate();
		if (row > 0) {
			flag = true;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("���ݲ���ʧ�ܣ�");
	} finally {
		DBManage.closeAll(null, sta, conn);
	
	}
	return flag;



}


/**
 * ��ȡ�ɷ���Ŀ�������б�		
 */
public List<TbfeeItem> getSelectOp(){
	//�õ�����
	Connection conn = DBManage.getConn();
	// ��������
	PreparedStatement sta = null;
	
	// ���������
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

		//�������������û�
	public PageUtil getUserCostList(int pageSize, int pageNo,String realName,int feeId,String beginDate,String endDate) {
		PageUtil page = null;
		String sql = null;
		
		Connection conn = DBManage.getConn();
		// ��������
		PreparedStatement sta = null;
		
		// ���������
		ResultSet rt = null;
		Object[] obj = null;
				
		try {
		
		
		String sql1 = "select us.realName,build.buildingName,o.telephone,f.feeName as '�ɷ���Ŀ',ut.havaCost as '�ɷѽ��',(SELECT realName from tb_user where userId=reveived) as '�շ���',ut.sendDate as '�շ�����',ut.remark as ��ע,ut.userConstId " +
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
		// ����ҳ��
		page.setPageNo(pageNo);
		//���岢ʵ����һ������
		sql = sql + sql2;
		sta = conn.prepareStatement(sql);		
		rt = sta.executeQuery();
		List<Object[]> list = new ArrayList<Object[]>();
		while ( rt.next()){
		   
			//ʵ����Object[]����
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
		
		// �������ݵļ���
			page.setData(list);
		
		} catch (Exception e) {
			
		} finally {
			DBManage.closeAll(rt, sta, conn);
		}
		return page;
}
/*
* ɾ����ѡ����
*/
public boolean deleteList(String[] str){
boolean flag = false;
//�õ�����
Connection conn = DBManage.getConn();
// ��������
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
