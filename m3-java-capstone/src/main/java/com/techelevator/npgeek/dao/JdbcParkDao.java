package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.com.pojo.Park;

@Component
public class JdbcParkDao implements ParkDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcParkDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> allParks = new ArrayList<Park>();
		String sqlSelectAllParks = "SELECT * FROM park;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParks);
		while(results.next()) {
			Park park = mapRowToPark(results);
			allParks.add(park);
		}
		return allParks;
	}
	

	@Override
	public Park getParkByCode(String parkCode) {
		Park park = null; 
		String query = "SELECT * FROM park WHERE parkcode = '" + parkCode + "';";
		SqlRowSet result = jdbcTemplate.queryForRowSet(query);
		while(result.next()) {
			
			park = mapRowToPark(result);
		}
		
		
		return park;
	}
		
	
	
	private Park mapRowToPark(SqlRowSet results) {
		
		Park park = new Park();
		park.setParkCode(results.getString("parkcode"));
		park.setParkName(results.getString("parkname"));
		park.setState(results.getString("state"));
		park.setAcreage(results.getInt("acreage"));
		park.setElevationInFeet(results.getInt("elevationinfeet"));
		park.setMilesOfTrail(results.getFloat("milesoftrail"));
		park.setNumberOfCampsites(results.getInt("numberofcampsites"));
		park.setClimate(results.getString("climate"));
		park.setYearFounded(results.getInt("yearfounded"));
		park.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		park.setInspirationalQuote(results.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(results.getString("inspirationalquotesource"));
		park.setParkDescription(results.getString("parkdescription"));
		park.setEntryFee(results.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));
		
		
		return park;
	}

	

}
