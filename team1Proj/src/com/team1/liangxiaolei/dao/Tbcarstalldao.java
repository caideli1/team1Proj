package com.team1.liangxiaolei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team1.liangxiaolei.bean.Tbcarstall;
import com.team1.liangxiaolei.bean.Tbuser;
import com.team1.util.DBManage;
import com.team1.util.PageUtil;

public class Tbcarstalldao {
	
	
	public PageUtil getRestcarList(int pageSize, int pageNo) {
		PageUtil page2 = null;
		// ��ѯ�û�����ȡ������
		String sql = "select  count(*) as totalCount from tb_carstall";
		Connection conn = DBManage.getConn();
		// ��������
		PreparedStatement sta = null;
		// ���������
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// �ܼ�¼��
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
			}

			page2 = new PageUtil(pageSize, totalCount);
			// ����ҳ��
			page2.setPageNo(pageNo);
			// �����ѯ��ǰҳ���ݵ�sql���

			sql = "select  stallNo,stallAdress,stallState from tb_carstall ORDER BY stallNo limit "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			
			rst = sta.executeQuery();
			// ����һ��List����
			List<Tbcarstall> tbcarStalls = new ArrayList<Tbcarstall>();
			
			while (rst.next()) {

			
				Tbcarstall  tbcarStall= new Tbcarstall();
				 tbcarStall.setStallNo(rst.getString(1));
				 tbcarStall.setStallAdress(rst.getString(2));
			
				 tbcarStall.setStallState(rst.getInt(3));
				
				

				// ��ӵ�������
				 tbcarStalls.add(tbcarStall);
			    
			}
			// �������ݵļ���
			page2.setData(tbcarStalls);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page2;
	}

}
