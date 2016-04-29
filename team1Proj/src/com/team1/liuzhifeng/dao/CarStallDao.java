package com.team1.liuzhifeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team1.liuzhifeng.bean.CarStallBean;
import com.team1.util.DBManage;
import com.team1.util.PageUtil;

public class CarStallDao {
	
//	public List<CarStallBean> getInfo(){
//		
//		//ʵ�������϶���
//		List<CarStallBean> list = new ArrayList<CarStallBean>();
//		//ʵ����CarStallBean����
//		CarStallBean carstallbean = null;
//		//�������ݿ�����
//		Connection conn = null;
//		//����Ԥ��������
//		PreparedStatement sta = null;
//		//��������
//		ResultSet rt = null;
//		//����sql���
//		String sql = "SELECT * from tb_carstall";
//		//�õ����ݿ�����
//		conn = DBManage.getConn();
//		try {
//			sta = conn.prepareStatement(sql);
//			rt = sta.executeQuery();
//			
//			while (rt!=null && rt.next()){
//				carstallbean = new CarStallBean();
//				carstallbean.setStallNo(rt.getString(1));
//				carstallbean.setStallAdress(rt.getString(2));
//				carstallbean.setUserId(rt.getInt(3));
//				carstallbean.setStallState(rt.getInt(4));
//				carstallbean.setStartDate(rt.getString(5));
//				carstallbean.setEndDate(rt.getString(6));
//				System.out.println(rt.getString(1));
//				list.add(carstallbean);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  finally{
//			DBManage.closeAll(rt, sta, conn);
//		}
//		//����һ��List����
//		return list;
//	}
	
	
	
	/**
	 * ��ȡÿҳ��ҵ���б�
	 */
	public PageUtil getCarstallList(int pageSize, int pageNo) {
		PageUtil page = null;
		// ��ѯ�û�����ȡ������
		String sql = " SELECT count(stallNo) as totalCount from tb_carstall";
		Connection conn = DBManage.getConn();
		// ��������
		PreparedStatement sta = null;
		
		// ���������
		ResultSet rt = null;
		CarStallBean carbean = null;
		try {
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			// �ܼ�¼��
			int totalCount = 0;
			if (rt.next()) {
				totalCount = rt.getInt("totalCount");
			}

			page = new PageUtil(pageSize, totalCount);
			// ����ҳ��
			page.setPageNo(pageNo);
			// �����ѯ��ǰҳ���ݵ�sql���

			sql = "SELECT * from tb_carstall order by stallNo limit"
					+ " "+(pageNo - 1) * pageSize + "," + pageSize;
			
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			
			//���岢ʵ����һ������
			List<CarStallBean> list = new ArrayList<CarStallBean>();
			while ( rt.next()){
				
				carbean = new CarStallBean();
				
				carbean.setStallNo(rt.getString(1));
				carbean.setStallAdress(rt.getString(2)) ;
				carbean.setUserId(rt.getInt(3)) ;
				carbean.setStallState(rt.getInt(4)) ;
				carbean.setStartDate(rt.getString(5));
				carbean.setEndDate(rt.getString(6));	
				list.add(carbean);
			}

			// �������ݵļ���
			page.setData(list);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rt, sta, conn);
		}
		return page;
	}
	
}
