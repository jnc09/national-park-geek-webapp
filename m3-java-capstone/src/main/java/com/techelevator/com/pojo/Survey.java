package com.techelevator.com.pojo;

import java.util.HashMap;
import java.util.Map;

public class Survey {

	private Park park;
	private String email;
	private String state;
	private String activityLevel;
	private int surveyCount;
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	
	public Park getPark() {
		return park;
	}
	public void setPark(Park park) {
		this.park = park;
	}
	public int getSurveyCount() {
		return surveyCount;
	}
	public void setSurveyCount(int surveyCount) {
		this.surveyCount = surveyCount;
	}
	
	
	
}
