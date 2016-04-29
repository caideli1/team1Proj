package com.team1.huangsai.bean;
/**
 * Î¬ÐÞ¼ÇÂ¼±í
 * @author hwq
 *
 */
public class Tb_repair {
  private  int repareId;
  private  int reportId;
  private  int userId;
  private  String repairResult;
  private  String endDate;
  private  Float totalCost;
public int getRepareId() {
	return repareId;
}
public void setRepareId(int repareId) {
	this.repareId = repareId;
}
public int getReportId() {
	return reportId;
}
public void setReportId(int reportId) {
	this.reportId = reportId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getRepairResult() {
	return repairResult;
}
public void setRepairResult(String repairResult) {
	this.repairResult = repairResult;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public Float getTotalCost() {
	return totalCost;
}
public void setTotalCost(Float totalCost) {
	this.totalCost = totalCost;
}
  
}
