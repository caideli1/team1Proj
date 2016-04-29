package com.team1.liangxiaolei.bean;

public class Tbuser implements java.io.Serializable {
private int userId;
private String userName;
private String userPwd;
private String realName;
private String roleId;
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserPwd() {
	return userPwd;
}
public void setUserPwd(String userPwd) {
	this.userPwd = userPwd;
}
public String getRealName() {
	return realName;
}
public void setRealName(String realName) {
	this.realName = realName;
}
public String getRoleId() {
	return roleId;
}
public void setRoleId(String i) {
	this.roleId = i;
}

	
	
}
