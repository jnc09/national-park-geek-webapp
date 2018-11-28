package com.techelevator.npgeek.dao;

import java.util.List;

import com.techelevator.com.pojo.Survey;

public interface SurveyDao {

	public List<Survey> getAllSurveys();
	
	public void save(Survey survey);
	
	public List<String> getActivityOptions();
	
	public List<String> getAllStates();
	
	public List<Survey> getGroupedSurveys();
	
	public String getActivityLevelFromNum(int num);

	public boolean isValidEmailAddress(String email); 
	
}
