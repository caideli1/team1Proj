package com.team1.liuzhifeng.bean;

import java.io.Serializable;

public class CarStallBean implements  Serializable {
	
	private String stallNo;
	private String stallAdress;
	private int userId;
	private int stallState;
	private String startDate;
	private String endDate;
	public String getStallNo() {
		return stallNo;
	}
	public void setStallNo(String stallNo) {
		this.stallNo = stallNo;
	}
	public String getStallAdress() {
		return stallAdress;
	}
	public void setStallAdress(String stallAdress) {
		this.stallAdress = stallAdress;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStallState() {
		return stallState;
	}
	public void setStallState(int stallState) {
		this.stallState = stallState;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
	
}
