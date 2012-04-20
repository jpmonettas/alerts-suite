package com.androidapps.alerts;

public class Alert {
	protected int alertId;
	protected String name;
	protected String note;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setNote(String note) {
		this.note = note;
	}
	public String getNote() {
		return note;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	public int getAlertId() {
		return alertId;
	}
	
	
}
