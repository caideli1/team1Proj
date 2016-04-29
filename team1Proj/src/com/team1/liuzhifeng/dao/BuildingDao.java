package com.team1.liuzhifeng.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.team1.liuzhifeng.bean.BuildingBean;
import com.team1.util.DBManage;
import com.team1.util.PageUtil;

public class BuildingDao {
	
	/**
	 * ��ѯtb_building���е�������Ϣ
	 */
	
//	public Vector<BuildingBean> getInfo(){
//		//���岢ʵ����һ������
//		Vector<BuildingBean> vc = new Vector<BuildingBean>();
//		//
//		BuildingBean buildbean = null;
//		//�������ݿ�����
//		Connection conn = null;
//		//����Ԥ��������
//		PreparedStatement sta = null;
//		//��������
//		ResultSet rt = null;
//		//sql���
//		String sql = "select * from tb_building";
//		//�õ����ݿ�����
//		conn = DBManage.getConn();
//		try {
//			sta = conn.prepareStatement(sql);
//			rt = sta.executeQuery();
//			
//			while (rt!=null && rt.next()){
//				buildbean = new BuildingBean();
//				buildbean.setBuildingId(rt.getInt(1));
//				buildbean.setBuildingName(rt.getString(2));
//				buildbean.setFloorNumber(rt.getInt(3));
//				buildbean.setUnitNumber(rt.getInt(4));
//				buildbean.setHouseSeries(rt.getInt(5));
//				buildbean.setLivingNumber(rt.getInt(6));
//				buildbean.setDeveloper(rt.getString(7));
//				//��buildbean������ӵ�vc������
//				vc.add(buildbean);			
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally{
//			//�ر�����
//			DBManage.closeAll(rt, sta, conn);
//		}		
//		return vc;
//	}
	
	
	
	public PageUtil getBuildList(int pageSize, int pageNo) {
		PageUtil page = null;
		// ��ѯ�û�����ȡ������
		String sql = "select  count(buildingId) as totalCount from tb_building";
		Connection conn = DBManage.getConn();
		// ��������
		PreparedStatement sta = null;
		
		// ���������
		ResultSet rt = null;
		Object[] obj = null;
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
			sql = "SELECT  t.buildingId,t.buildingName,t.floorNumber,t.unitNumber,t.houseSeries,t.livingNumber,t.developer FROM tb_building AS t limit"+" "+(pageNo - 1) * pageSize + "," + pageSize;
			
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			//���岢ʵ����һ������
			List<Object[]> list = new ArrayList<Object[]>();
			while ( rt.next()){
				//ʵ����Object[]����
				obj = new Object[7];
					
				obj[0] = rt.getInt(1);
				obj[1] = rt.getString(2);
				obj[2] = rt.getInt(3);
				obj[3] = rt.getInt(4);
				obj[4] = rt.getInt(5);
				obj[5] = rt.getInt(6);
				obj[6] = rt.getString(7);
				
				list.add(obj);
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
