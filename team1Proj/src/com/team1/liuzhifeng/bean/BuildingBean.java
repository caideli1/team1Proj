package com.team1.liuzhifeng.bean;

import java.io.Serializable;

public class BuildingBean implements  Serializable {
	
	private int buildingId;
	private String buildingName;
	private int floorNumber;
	private int houseSeries;
	private int  unitNumber;
	private int livingNumber;
	private String developer;
	public int getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public int getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	public int getHouseSeries() {
		return houseSeries;
	}
	public void setHouseSeries(int houseSeries) {
		this.houseSeries = houseSeries;
	}
	public int getUnitNumber() {
		return unitNumber;
	}
	public void setUnitNumber(int unitNumber) {
		this.unitNumber = unitNumber;
	}
	public int getLivingNumber() {
		return livingNumber;
	}
	public void setLivingNumber(int livingNumber) {
		this.livingNumber = livingNumber;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
}
