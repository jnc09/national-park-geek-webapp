package com.techelevator;


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

import com.techelevator.com.pojo.Weather;
import com.techelevator.npgeek.dao.JdbcSurveyDao;
import com.techelevator.npgeek.dao.JdbcWeatherDao;

public class JdbcWeatherDaoIntegrationTest extends DAOIntegrationTest {

	private JdbcWeatherDao dao;
	
	@Before
	public void setup() {
		this.dao = new JdbcWeatherDao(getDataSource());

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
		
		String weather1Insert = "INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('TST1',2,31,43,'snow');";
		jdbcTemplate.update(weather1Insert);
		
		String weather2Insert = "INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('TST2',2,31,43,'sunny');";
		jdbcTemplate.update(weather2Insert);
	}
	
	@Test
	public void getWeatherForParkTest() {
		
		List<Weather> weather = dao.getWeatherForPark("TST1");
		List<Weather> weather2 = dao.getWeatherForPark("TST2");
		
		boolean weather1Correct = false;
		boolean weather2Correct = false;
		

		for(Weather park1 : weather) {
			if(park1.getForecast().equals("snow")) {
				weather1Correct = true;
			}
		}

		for(Weather park2 : weather2) {
			if(park2.getForecast().equals("sunny")) {
				weather2Correct = true;
			}
		}
		
		assertTrue(weather1Correct);
		assertTrue(weather2Correct);
		
	}

}
