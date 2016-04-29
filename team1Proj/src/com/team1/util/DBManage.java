package com.team1.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DBManage {
	//1.�������ݿ�����
	private static String driver = "com.mysql.jdbc.Driver";
	//2.�������ݿ�����ӵ�ַ
	private static String url = "jdbc:mysql://localhost:3306/team1db";
	//3.�û���
	private static String user = "root";
	//4.����
	private static String password = "123";
	
	/**
	 * �õ����Ӷ���
	 */
	public static Connection getConn(){
		Connection conn = null;		
		try {			
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
		
	/**
	 * �ͷ���Դ
	 */
	public static void closeAll(ResultSet rst, PreparedStatement sta, Connection conn) {
		try {
			if (rst != null) {
				rst.close();
				rst = null;
			}
			if (sta != null) {
				sta.close();
				sta = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
