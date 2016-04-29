package com.team1.liangxiaolei.bean;

public class TbuserCost {
private int userCostId;
private int userId;
private int feedId;
private float havaCost;
private int reveived;
	private String sendDate;
	private String remark;
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
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}

	public float getHavaCost() {
		return havaCost;
	}
	public void setHavaCost(float havaCost) {
		this.havaCost = havaCost;
	}
	public int getReveived() {
		return reveived;
	}
	public void setReveiveId(int reveived) {
		this.reveived = reveived;
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
	
	
}