package com.team1.huangsai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.team1.util.DBManage;

public class Tb_feeitemDao {
	//��ȡ��Ϣ
		public  List<String>  getFeeName(){
			System.out.println("hh");
			List<String> Names = new ArrayList<String>();
			
	     String  sql="select feeName from  tb_feeitem";
	  // �õ�����
	  		Connection conn = DBManage.getConn();
	  	// �õ�Ԥ��������
	  			PreparedStatement sta = null;
	  			ResultSet rst = null;
	  			try {
	  				
	  				sta = conn.prepareStatement(sql); 				
	  				rst = sta.executeQuery();
	  				//System.out.println(sql);
	  				
					while(rst.next()) {
						
						Names.add(rst.getString(1));
					
						System.out.println(rst.getString(1));
							
			}
				
					return  Names;
					
	  			} catch (Exception e) {
	  				e.printStackTrace();
	  				return null;
	  			} finally {
	  				DBManage.closeAll(null, sta, conn);
	  			}
	  			
	  		}
}
