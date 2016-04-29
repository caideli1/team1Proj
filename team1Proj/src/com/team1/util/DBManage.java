package com.team1.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DBManage {
	//1.定义数据库驱动
	private static String driver = "com.mysql.jdbc.Driver";
	//2.定义数据库的链接地址
	private static String url = "jdbc:mysql://localhost:3306/team1db";
	//3.用户名
	private static String user = "root";
	//4.密码
	private static String password = "123";
	
	/**
	 * 得到链接对象
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
	 * 释放资源
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
