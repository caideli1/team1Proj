package com.team1.liuzhifeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.team1.liuzhifeng.bean.OwnerDetailBean;
import com.team1.util.DBManage;
import com.team1.util.PageUtil;

public class OwnerDetailDao {

	
	/**
	 * ��ȡÿҳ��ҵ���б�
	 */
	public PageUtil getOwnerList(int pageSize, int pageNo) {
		PageUtil page = null;
		// ��ѯ�û�����ȡ������
		String sql = "select  count(ownerId) as totalCount from tb_ownerdetail";
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

			sql = "select us.realName,build.buildingName,o.houseFloor,o.houseUint,o.houseNumber,o.cardId,o.telephone,o.enterDate " +
					"from tb_ownerdetail as o join tb_building as build on o.buildingId=build.buildingId " +
						"join tb_user as us on o.userId=us.userId where us.roleId='r002' ORDER BY o.ownerId limit"
					+ " "+(pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rt = sta.executeQuery();
			//���岢ʵ����һ������
			List<Object[]> list = new ArrayList<Object[]>();
			while ( rt.next()){
				//ʵ����Object[]����
				obj = new Object[8];
				obj[0] = rt.getString(1);
				obj[1] = rt.getString(2);
				obj[2] = rt.getInt(3);
				obj[3] = rt.getInt(4);
				obj[4] = rt.getString(5);
				obj[5] = rt.getString(6);
				obj[6] = rt.getString(7);
				obj[7] = rt.getString(8);				
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
