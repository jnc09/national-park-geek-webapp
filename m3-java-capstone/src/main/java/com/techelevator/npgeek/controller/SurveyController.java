package com.techelevator.npgeek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.com.pojo.Park;
import com.techelevator.com.pojo.Survey;
import com.techelevator.npgeek.dao.ParkDao;
import com.techelevator.npgeek.dao.SurveyDao;
import com.techelevator.npgeek.dao.WeatherDao;

@Controller
public class SurveyController {
	
	@Autowired
	WeatherDao weatherDao;
	
	@Autowired
	ParkDao parkDao;
	
	@Autowired
	SurveyDao surveyDao;
	
	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String showSurvey(ModelMap map) {
		
		List<Park> parks = parkDao.getAllParks();
		List<String> states = surveyDao.getAllStates();
		List<String> activeLevels = surveyDao.getActivityOptions();
		
		map.put("states", states);
		map.put("activeLevels", activeLevels);
		map.put("parks", parks);
		
		return "survey";
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.POST)
	public String submitSurvey(String parkCode,
								String email,
								String state,
								String activeLevel) {
		
		try {
            Survey survey = new Survey();
            String level = surveyDao.getActivityLevelFromNum(Integer.parseInt(activeLevel));
            survey.setActivityLevel(level);

            boolean validEmail = surveyDao.isValidEmailAddress(email);
            
            if (validEmail) {
                survey.setEmail(email);
            }

            Park park = parkDao.getParkByCode(parkCode);
            survey.setState(state);
            survey.setPark(park);
            
            surveyDao.save(survey);
            return "redirect:/favoriteParks";
		} catch (Exception e){
			
			return "survey";

		}
	}
	
	@RequestMapping(path="/favoriteParks", method=RequestMethod.GET)
	public String showFavoriteParks(ModelMap map) {
		
		List<Survey> surveyList = surveyDao.getGroupedSurveys();
		
		map.put("surveys", surveyList);
		
		return "favoriteParks";
	}
	
}
