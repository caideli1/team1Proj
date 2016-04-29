package com.team1.huangsai.bean;

public class Tb_userCost {
      private  int  userCostId;
      private  int  userId;
      private  int  feeId;
      private  float  havaCost;
      private  int  reveiveId;
      private  String  sendDate;
      public int getUserCostId() {
		return userCostId;
	}
	public void setUserCostId(int userCostId) {
		this.userCostId = userCostId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFeeId() {
		return feeId;
	}
	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}
	public float getHavaCost() {
		return havaCost;
	}
	public void setHavaCost(float havaCost) {
		this.havaCost = havaCost;
	}
	public int getReveiveId() {
		return reveiveId;
	}
	public void setReveiveId(int reveiveId) {
		this.reveiveId = reveiveId;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	private  String  remark;
      
      
}
