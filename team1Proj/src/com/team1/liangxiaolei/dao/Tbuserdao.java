package com.team1.liangxiaolei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import com.team1.liangxiaolei.bean.Tbownerdetail;
import com.team1.liangxiaolei.bean.Tbuser;
import com.team1.liangxiaolei.bean.TbuserCost;
import com.team1.util.DBManage;
import com.team1.util.PageUtil;


public class Tbuserdao {
	public boolean add(Tbuser tbuser){
		boolean flag = false;
		String sql = "insert into tb_user(userName,userPwd,realName,roleId)  values(?,?,?,?)";
		Connection conn = DBManage.getConn();
		PreparedStatement sta = null;
		try {
			// 操作对象
		
			sta = conn.prepareStatement(sql);
		   sta.setString(1, tbuser.getUserName());
			sta.setString(2, tbuser.getUserPwd());
			sta.setString(3, tbuser.getRealName());	
			sta.setString(4, tbuser.getRoleId());
			
			
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
		 * 用户登录（根据用户名和密码查出用户的信息）
		 * 
		 * @param 用户名
		 * @param 密码
		 * @return
		 */
		public Tbuser login(String userName, String userPwd) {
			// 初始化为null
			Tbuser user = null;
			String sql = "select  * from tb_user where userName=? and userPwd=?";
			// 获取连接对象
			Connection conn = DBManage.getConn();
			// 操作对象
			// Statement statement = null;
			// PreparedStatement可以执行含有?的SQL语句
			PreparedStatement sta = null;
			// 结果集对象
			ResultSet rst = null;
			try {
				// statement = conn.createStatement();
				sta = conn.prepareStatement(sql);
				// 给?赋值
				sta.setString(1, userName);
				sta.setString(2, userPwd);

				rst = sta.executeQuery();
				if (rst.next()) {
					// 实例化
					user = new Tbuser();
					user.setUserId(rst.getInt(1));
					user.setUserName(rst.getString("userName"));
					user.setUserPwd(rst.getString(3));
					user.setRealName(rst.getString("realName"));
					user.setRoleId(rst.getString("roleId"));
					// System.out.println(rst.getString(1)+"--"+rst.getString(2));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBManage.closeAll(rst, sta, conn);
			}

			return user;
		}
	
	public boolean add(TbuserCost tbusercost){
		boolean flag = false;
		String sql = "insert into tb_userCost(userId,feedId, haveCost,reveived,sendDate,remark)  values(?,?,?,?,?,?)";
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
	
	public PageUtil getUserList(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = "select  count(*) as totalCount from tb_user";
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		// 结果集对象
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// 总记录数
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
			}

			page = new PageUtil(pageSize, totalCount);
			// 设置页码
			page.setPageNo(pageNo);
			// 构造查询当前页数据的sql语句

			sql = "select userName,realName,roleId from tb_user ORDER BY userId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// 创建一个List集合
			List<Tbuser> users = new ArrayList<Tbuser>();
			
			while (rst.next()) {

				// user.setRoleId(rst.getString(columnIndex))
				Tbuser user = new Tbuser();
				user.setUserName(rst.getString(1));
				user.setRealName(rst.getString(2));
				user.setRoleId(rst.getString(3));

				// 添加到集合中
			users.add(user);
			    
			}
			// 设置数据的集合
			page.setData(users);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}
		
	public PageUtil getUserLista(int pageSize, int pageNo) {
		PageUtil page = null;
		// 查询用户表，获取总条数
		String sql = "select  count(*) as totalCount from tb_user";
		Connection conn = DBManage.getConn();
		// 操作对象
		PreparedStatement sta = null;
		// 结果集对象
		ResultSet rst = null;
		try {
			sta = conn.prepareStatement(sql);

			rst = sta.executeQuery();
			// 总记录数
			int totalCount = 0;
			if (rst.next()) {
				totalCount = rst.getInt("totalCount");
			}

			page = new PageUtil(pageSize, totalCount);
			// 设置页码
			page.setPageNo(pageNo);
			// 构造查询当前页数据的sql语句

			sql = "select userName,realName,roleId from tb_user where roleId='r002' ORDER BY userId limit  "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			sta = conn.prepareStatement(sql);
			rst = sta.executeQuery();
			// 创建一个List集合
			List<Tbuser> users = new ArrayList<Tbuser>();
			
			while (rst.next()) {

				// user.setRoleId(rst.getString(columnIndex))
				Tbuser user = new Tbuser();
				user.setUserName(rst.getString(1));
				user.setRealName(rst.getString(2));
				user.setRoleId(rst.getString(3));

				// 添加到集合中
			users.add(user);
			    
			}
			// 设置数据的集合
			page.setData(users);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}
		return page;
	}	
	
	public List<Object[]> getAll( String realName){
		// 初始化为null
		Tbuser user = null;
		String sql = "select realName,buildingName,houseNumber,cardId,telephone,enterDate,ownerId from tb_ownerdetail s  inner join tb_building c on s.buildingId=c.buildingId inner join tb_user t on s.userId=t.userId where t.realName=?";
		// 获取连接对象
		Connection conn = DBManage.getConn();
		  List<Object[] >  list=new ArrayList<Object[] >();
		PreparedStatement sta = null;
		// 结果集对象
		ResultSet rst = null;
		try {
			
			sta = conn.prepareStatement(sql);
			
			
			sta.setString(1,realName);
			rst = sta.executeQuery();
			
			while (rst.next()) {

	        Object[]  obj=new  Object[7];
	        
	            obj[0]=rst.getString(1);
	            obj[1]=rst.getString(2);
	            obj[2]=rst.getString(3);
	    		   obj[3]=rst.getString(4);
	    		   obj[4]=rst.getString(5);
	    		   obj[5]=rst.getString(6);
	    		   obj[6]=rst.getInt(7);
	       list.add(obj);
	       }
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			DBManage.closeAll(rst, sta, conn);
		}

		return  list;
	}
		

	}
