package com.techelevator;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.com.pojo.Survey;
import com.techelevator.npgeek.dao.JdbcSurveyDao;

import junit.framework.Assert;

public class JdbcSurveyDaoIntegrationTest extends DAOIntegrationTest {


	private JdbcSurveyDao dao;

	@Before
	public void setup() {
		this.dao = new JdbcSurveyDao(getDataSource());

		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

		String park1Insert = "INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, "
				+ "numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, "
				+ "parkDescription, entryFee, numberOfAnimalSpecies) "
				+ "VALUES ('TST1', 'Test Park 1', 'Ohio', 32832, 696, 125, 0, 'Woodland', "
				+ "2000, 2189849, 'Of all the paths you take in life, make sure a few of them are dirt.', 'John Muir', 'Test Description 1', 0, 390);";
		jdbcTemplate.update(park1Insert);

		String park2Insert = "INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, "
				+ "numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, "
				+ "parkDescription, entryFee, numberOfAnimalSpecies) "
				+ "VALUES ('TST2', 'Test Park 2', 'Ohio', 32832, 696, 125, 0, 'Woodland', "
				+ "2000, 2189849, 'Of all the paths you take in life, make sure a few of them are dirt.', 'John Muir', 'Test Description 2', 0, 390);";
		jdbcTemplate.update(park2Insert);
		
		String survey1Insert = "INSERT INTO survey_result(parkcode, emailaddress, state, activitylevel) VALUES ('TST1', 'testing@testing.org', 'MI', 'active');";
		jdbcTemplate.update(survey1Insert);

		String survey2Insert = "INSERT INTO survey_result(parkcode, emailaddress, state, activitylevel) VALUES ('TST1', 'testing@testing.co', 'AL', 'sedentary');";
		jdbcTemplate.update(survey2Insert);
		
		String survey3Insert = "INSERT INTO survey_result(parkcode, emailaddress, state, activitylevel) VALUES ('TST1', 'testing@testing.com', 'MA', 'extremely active');";
		jdbcTemplate.update(survey3Insert);

		String survey4Insert = "INSERT INTO survey_result(parkcode, emailaddress, state, activitylevel) VALUES ('TST2', 'testing@testing.net', 'OH', 'sedentary');";
		jdbcTemplate.update(survey4Insert);
	}


	@Test
	public void getAllSurveysTest() {
		List<Survey> allSurveys = dao.getAllSurveys();
		
		boolean survey1Exists = false;
		boolean survey2Exists = false;
		
		for(Survey survey : allSurveys) {
			if(survey.getPark().getParkCode().equals("TST1")) {
				survey1Exists = true;
			} else if(survey.getPark().getParkCode().equals("TST2")) {
				survey2Exists = true;
			}
		}
		
		assertTrue(survey1Exists);
		assertTrue(survey2Exists);
	}
	
	@Test
	public void getGroupedSurveysTest() {
		
		List<Survey> groupedList = dao.getGroupedSurveys();
		
		boolean correctCount1 = false;
		boolean correctCount2 = false;
		
		for(Survey survey : groupedList) {
			if(survey.getPark().getParkCode().equals("TST1")) {
				int count = survey.getSurveyCount();
				if(count == 3) {
					correctCount1 = true;
				}
			} else if(survey.getPark().getParkCode().equals("TST2")) {
				int count = survey.getSurveyCount();
				if(count == 1) {
					correctCount2 = true;
				}
			}
		}
		
		assertTrue(correctCount1);
		assertTrue(correctCount2);
	}
	
	@Test
	public void getAllStates() {
		List<String> states = dao.getAllStates();
		boolean ohExists = false;
		boolean miExists = false;
		boolean sdExists = false;
		
		for(String state : states) {
			if(state.equals("OH")) {
				ohExists = true;
			} else if(state.equals("MI")) {
				miExists = true;
			} else if(state.equals("SD")) {
				sdExists = true;
			}
		}
		
		assertTrue(ohExists);
		assertTrue(miExists);
		assertTrue(sdExists);
	}
	
	@Test
	public void getActivityLevelFromNumTest() {
		String active = dao.getActivityLevelFromNum(3);
		String inactive = dao.getActivityLevelFromNum(1);
		String sedentary = dao.getActivityLevelFromNum(2);
		String extremelyActive = dao.getActivityLevelFromNum(4);
		
		assertEquals("active", active);
		assertEquals("inactive", inactive);
		assertEquals("sedentary", sedentary);
		assertEquals("extremely active", extremelyActive);
	}
	
	@Test
	public void isValidEmailAddressTest() {
		String email1 = "testing@testing.com";
		String email2 = "testing@.testing.com";
		String email3 = "testingtesting.com";
		
		boolean email1Valid = dao.isValidEmailAddress(email1);
		boolean email2Valid = dao.isValidEmailAddress(email2);
		boolean email3Valid = dao.isValidEmailAddress(email3);
		
		assertTrue(email1Valid);
		assertFalse(email2Valid);
		assertFalse(email3Valid);
	}
}
