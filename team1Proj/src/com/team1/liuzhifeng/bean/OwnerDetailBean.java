package com.team1.liuzhifeng.bean;

import java.io.Serializable;

public class OwnerDetailBean implements  Serializable {

	private int ownerId;
	private int buildingId;
	private int houseUint;
	private int houseFloor;
	private String houseNumber;
	private String cardId;
	private String telephone;
	private String enterDate;
	private int userId;
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}
	public int getHouseUint() {
		return houseUint;
	}
	public void setHouseUint(int houseUnit) {
		this.houseUint = houseUint;
	}
	public int getHouseFloor() {
		return houseFloor;
	}
	public void setHouseFloor(int houseFloor) {
		this.houseFloor = houseFloor;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
