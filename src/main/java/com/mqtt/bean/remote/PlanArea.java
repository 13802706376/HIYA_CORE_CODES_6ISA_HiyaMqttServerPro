package com.mqtt.bean.remote;

import java.util.List;


public class PlanArea {
	
	private String areaCode;
	
	private List<SystemUpgradeOrder> sysUpgradeOrderList;
	
	private List<AppUpgradeOrder> appUpgradeOrderList;

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public List<SystemUpgradeOrder> getSysUpgradeOrderList() {
		return sysUpgradeOrderList;
	}

	public void setSysUpgradeOrderList(List<SystemUpgradeOrder> sysUpgradeOrderList) {
		this.sysUpgradeOrderList = sysUpgradeOrderList;
	}

	public List<AppUpgradeOrder> getAppUpgradeOrderList() {
		return appUpgradeOrderList;
	}

	public void setAppUpgradeOrderList(List<AppUpgradeOrder> appUpgradeOrderList) {
		this.appUpgradeOrderList = appUpgradeOrderList;
	}
	
	
}
