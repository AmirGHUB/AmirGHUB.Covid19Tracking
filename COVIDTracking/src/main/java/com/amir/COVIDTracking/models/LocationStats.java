package com.amir.COVIDTracking.models;

public class LocationStats {
	
	private String state;
	private String country;
	private int newTotalCases;
	private int differenceFromPreviousDay;
	
	
	
	
	public int getDifferenceFromPreviousDay() {
		return differenceFromPreviousDay;
	}
	public void setDifferenceFromPreviousDay(int differenceFromPreviousDay) {
		this.differenceFromPreviousDay = differenceFromPreviousDay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getNewTotalCases() {
		return newTotalCases;
	}
	public void setNewTotalCases(int newTotalCases) {
		this.newTotalCases = newTotalCases;
	}
	
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", newTotalCases=" + newTotalCases + "]";
	}

	
}
