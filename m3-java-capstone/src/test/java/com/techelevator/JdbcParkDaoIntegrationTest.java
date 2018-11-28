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

import com.techelevator.com.pojo.Park;
import com.techelevator.npgeek.dao.JdbcParkDao;

public class JdbcParkDaoIntegrationTest extends DAOIntegrationTest {


	private JdbcParkDao dao;

	@Before
	public void setup() {
		this.dao = new JdbcParkDao(getDataSource());

		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

		String sqlInsert = "INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, "
				+ "numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, "
				+ "parkDescription, entryFee, numberOfAnimalSpecies) "
				+ "VALUES ('TST1', 'Test Park 1', 'Ohio', 32832, 696, 125, 0, 'Woodland', "
				+ "2000, 2189849, 'Of all the paths you take in life, make sure a few of them are dirt.', 'John Muir', 'Test Description 1', 0, 390);";
		jdbcTemplate.update(sqlInsert);

		String sqlInsertPark2 = "INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, "
				+ "numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, "
				+ "parkDescription, entryFee, numberOfAnimalSpecies) "
				+ "VALUES ('TST2', 'Test Park 2', 'Ohio', 32832, 696, 125, 0, 'Woodland', "
				+ "2000, 2189849, 'Of all the paths you take in life, make sure a few of them are dirt.', 'John Muir', 'Test Description 2', 0, 390);";
		jdbcTemplate.update(sqlInsertPark2);
	}


	@Test
	public void getAllParksTest() {

		List<Park> allParks = dao.getAllParks();

		boolean park1Exists = false;
		boolean park2Exists = false;

		for(Park park : allParks) {
			if(park.getParkCode().equals("TST1")) {
				park1Exists = true;
			} else if(park.getParkCode().equals("TST2")) {
				park2Exists = true;
			}
		}

		assertTrue(park1Exists);
		assertTrue(park2Exists);
	}
	
	@Test
	public void getParkByCodeTest() {
		
		Park park1 = dao.getParkByCode("TST1");
		Park park2 = dao.getParkByCode("TST2");
		boolean park1Exists = false;
		boolean park2Exists = false;
		
		if(park1.getParkName().equals("Test Park 1")) {
			System.out.println(park1.getParkName());
		}
		
		if(park2.getParkName().equals("Test Park 2")) {
			park2Exists = true;
		}
		
		assertTrue(park1Exists);
		assertTrue(park2Exists);
	}
}
