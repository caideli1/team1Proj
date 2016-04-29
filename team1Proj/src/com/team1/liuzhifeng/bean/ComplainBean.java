package com.team1.liuzhifeng.bean;

import java.io.Serializable;

public class ComplainBean implements Serializable {

	private int complained; 
	private int userId;
	private int doWithId;
	private String complainText;
	private String complainDate;
	private String reamark;
	private String  complainResult;
	private String endDate;
	private int complainState;
	public int getComplained() {
		return complained;
	}
	public void setComplained(int complained) {
		this.complained = complained;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getDoWithId() {
		return doWithId;
	}
	public void setDoWithId(int doWithId) {
		this.doWithId = doWithId;
	}
	public String getComplainText() {
		return complainText;
	}
	public void setComplainText(String complainText) {
		this.complainText = complainText;
	}
	public String getComplainDate() {
		return complainDate;
	}
	public void setComplainDate(String complainDate) {
		this.complainDate = complainDate;
	}
	public String getReamark() {
		return reamark;
	}
	public void setReamark(String reamark) {
		this.reamark = reamark;
	}
	public String getComplainResult() {
		return complainResult;
	}
	public void setComplainResult(String complainResult) {
		this.complainResult = complainResult;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getComplainState() {
		return complainState;
	}
	public void setComplainState(int complainState) {
		this.complainState = complainState;
	}
}
