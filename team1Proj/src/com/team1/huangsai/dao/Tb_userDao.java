package com.team1.huangsai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team1.huangsai.bean.Tb_user;
import com.team1.util.DBManage;






public class Tb_userDao {
     //添加用户方法
	public  boolean   add(Tb_user tb_user){
		boolean  flag=false;
     String  sql="insert into userinfo (userName,userPwd,realName) values(?,?,?)";
  // 得到链接
  		Connection conn = DBManage.getConn();
  	// 得到预操作对象
  			PreparedStatement sta = null;
  			try {
  				sta = conn.prepareStatement(sql);
  				sta.setString(1, tb_user.getUserName());
  				sta.setString(2, tb_user.getUserPwd());
  				sta.setString(3, tb_user.getRealName());

  				int row = sta.executeUpdate();
  				//System.out.println(sql);
  				if (row > 0) {
  					flag = true;
  				}

  			} catch (Exception e) {
  				e.printStackTrace();
  			} finally {
  				DBManage.closeAll(null, sta, conn);
  			}
  			return flag;
  		}
	
	//检查用户名是否存在
	public  boolean   CheckExsit(String userName){
		boolean  flag=false;
				String sql = "select * from tb_user where userName=?";
				// 2.然后得到链接
				Connection conn = DBManage.getConn();
				// 3.得到预操作对象
				PreparedStatement sta = null;
				// 结果集
				ResultSet rst = null;
				try {
					sta = conn.prepareStatement(sql);
					sta.setString(1, userName);
					rst = sta.executeQuery();
					System.out.println(sql);
					if (rst.next()) {
						flag=true;
					}
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					DBManage.closeAll(rst, sta, conn);
				}
				return flag;
			}
	//检查密码是否正确
	public  boolean   CheckPwd(String userPwd,String userName){
		
		boolean  flag=false;
		String sql = "select * from tb_user where userName=? and userPwd=?";
		// 2.然后得到链接
		Connection conn = DBManage.getConn();
		// 3.得到预操作对象
		PreparedStatement sta = null;
		// 结果集
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);
			sta.setString(1, userName);
			sta.setString(2, userPwd);
			rst = sta.executeQuery();
			System.out.println(sql);
			if (rst.next()) {
				flag=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return flag;
	}
	
	//查找用户
public  Tb_user   CheckUser(String userPwd,String userName){
		Tb_user tb_user=new Tb_user();
		
		String sql = "select * from tb_user where userName=? and userPwd=?";
		// 2.然后得到链接
		Connection conn = DBManage.getConn();
		// 3.得到预操作对象
		PreparedStatement sta = null;
		// 结果集
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);
			sta.setString(1, userName);
			sta.setString(2, userPwd);
			rst = sta.executeQuery();
			System.out.println(sql);
			
			if (rst.next()) {
				tb_user.setUserId(rst.getInt(1));
				tb_user.setUserName(rst.getString(2));
				tb_user.setUserPwd(rst.getString(3));
				tb_user.setRealName(rst.getString(4));
				tb_user.setRoleId(rst.getString(5));
				return  tb_user;
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return  tb_user;
	} 
   //  通过userId查找用户
        public  Tb_user  FindUser(int userId){
        	Tb_user tb_user=new Tb_user();
        String 	sql="select * from tb_user where userId=?";
     // 2.然后得到链接
     		Connection conn = DBManage.getConn();
     		// 3.得到预操作对象
     		PreparedStatement sta = null;
     		// 结果集
     		ResultSet rst = null;
     		try {
     			sta = conn.prepareStatement(sql);
     			sta.setInt(1, userId);
     			
     			rst = sta.executeQuery();
     			System.out.println(sql);
     			
     			if (rst.next()) {
     				tb_user.setUserId(rst.getInt(1));
     				tb_user.setUserName(rst.getString(2));
     				tb_user.setUserPwd(rst.getString(3));
     				tb_user.setRealName(rst.getString(4));
     				tb_user.setRoleId(rst.getString(5));
     				return  tb_user;
     			}
     		} catch (Exception e) {
     			// TODO: handle exception
     			
     		} finally {
     			DBManage.closeAll(rst, sta, conn);
     		}
     		return  tb_user;
        }
        //查找工作人员或者业主
        public  List<Tb_user>  FindWorker(String roleId){
        	
        	List<Tb_user>  users=new ArrayList<Tb_user>();
        String 	sql="select * from tb_user where roleId=?";
     // 2.然后得到链接
     		Connection conn = DBManage.getConn();
     		// 3.得到预操作对象
     		PreparedStatement sta = null;
     		// 结果集
     		ResultSet rst = null;
     		try {
     			sta = conn.prepareStatement(sql);
     			sta.setString(1, roleId);
     			
     			rst = sta.executeQuery();
     			System.out.println(sql);
     			
     			while (rst!=null&&rst.next()) {
     				Tb_user tb_user=new Tb_user();
     				tb_user.setUserId(rst.getInt(1));
     				tb_user.setUserName(rst.getString(2));
     				tb_user.setUserPwd(rst.getString(3));
     				tb_user.setRealName(rst.getString(4));
     			
     				tb_user.setRoleId(rst.getString(5));
     				users.add(tb_user);
     				
     			}
     			
     			return  users;
     			
     		} catch (Exception e) {
     			// TODO: handle exception
     			return   null;
     		} finally {
     			DBManage.closeAll(rst, sta, conn);
     		}
     		
        }
     /*   //获取所有用户
 public  List<Tb_user>  getAllUser(String roleId){
        	
        	List<Tb_user>  users=new ArrayList<Tb_user>();
        String 	sql="select * from tb_user where roleId=?";
     // 2.然后得到链接
     		Connection conn = DBManage.getConn();
     		// 3.得到预操作对象
     		PreparedStatement sta = null;
     		// 结果集
     		ResultSet rst = null;
     		try {
     			sta = conn.prepareStatement(sql);
     			sta.setString(1, roleId);
     			
     			rst = sta.executeQuery();
     			System.out.println(sql);
     			
     			while (rst!=null&&rst.next()) {
     				Tb_user tb_user=new Tb_user();
     				tb_user.setUserId(rst.getInt(1));
     				tb_user.setUserName(rst.getString(2));
     				tb_user.setUserPwd(rst.getString(3));
     				tb_user.setRealName(rst.getString(4));
     			
     				tb_user.setRoleId(rst.getString(5));
     				users.add(tb_user);
     				
     			}
     			
     			return  users;
     			
     		} catch (Exception e) {
     			// TODO: handle exception
     			return   null;
     		} finally {
     			DBManage.closeAll(rst, sta, conn);
     		}
     		
        }*/
        
        //重设密码
        public  boolean   updatePassword(String userPwd,int userId){
    		
    		boolean  flag=false;
    		String sql = "update tb_user set userPwd=? where  userId=?";
    		// 2.然后得到链接
    		Connection conn = DBManage.getConn();
    		// 3.得到预操作对象
    		PreparedStatement sta = null;
    	
    		try {
    			sta = conn.prepareStatement(sql);
    			sta.setString(1, userPwd);
    			sta.setInt(2, userId);
    			int row = sta.executeUpdate();
    			System.out.println(sql);
    			if (row>0) {
    				flag=true;
    			}
    		} catch (Exception e) {
    			// TODO: handle exception
    		} finally {
    			DBManage.closeAll(null, sta, conn);
    		}
    		return flag;
    	}
        
	}
	
	
	
	
	
	
	
	
	

