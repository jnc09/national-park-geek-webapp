package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.com.pojo.Park;
import com.techelevator.com.pojo.Survey;

@Component
public class JdbcSurveyDao implements SurveyDao {

	JdbcTemplate jdbcTemplate;
	JdbcParkDao parkDao;
	
	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		parkDao = new JdbcParkDao(dataSource);
	}
	
	@Override
	public List<Survey> getAllSurveys() {
		// TODO Auto-generated method stub
		
		List<Survey> allPosts = new ArrayList<Survey>();

		String sqlSelectAllPosts = "SELECT surveyid, parkcode, emailaddress, state, activitylevel,  (SELECT COUNT(*) FROM survey_result) as counter FROM survey_result ORDER BY parkcode ASC;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllPosts);
		while(results.next()) {
			Survey post = new Survey();
			post.setActivityLevel(results.getString("activitylevel"));
			post.setEmail(results.getString("emailaddress"));
			
			Park park = parkDao.getParkByCode(results.getString("parkcode").toUpperCase());
			post.setPark(park);

			post.setState(results.getString("state"));
			allPosts.add(post);
		}

		return allPosts;
	}
	
	public List<Survey> getGroupedSurveys() {
		
		List<Survey> posts = new ArrayList<Survey>();
		
		String sql = "SELECT parkcode, COUNT(parkcode) as counter FROM survey_result GROUP BY parkcode ORDER BY counter DESC, parkcode ASC;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			Survey post = new Survey();
			
			post.setSurveyCount(results.getInt("counter"));
			
			Park park = parkDao.getParkByCode(results.getString("parkcode").toUpperCase());
			post.setPark(park);
			
			posts.add(post);

		}

		return posts;
	}

	@Override
	public void save(Survey survey) {
		
		String sql = "INSERT INTO survey_result(parkcode, emailaddress, state, activitylevel) VALUES (?, ?, ?, ?);";
		jdbcTemplate.update(sql, survey.getPark().getParkCode().toUpperCase(), survey.getEmail(), survey.getState(), survey.getActivityLevel().toLowerCase());
	}
	
	public List<String> getActivityOptions() {
		
		List<String> activityOptions = new ArrayList<String>();
		activityOptions.add("Inactive");
		activityOptions.add("Sedentary");
		activityOptions.add("Active");
		activityOptions.add("Extremely Active");
		
		return activityOptions;
	}
	
	public List<String> getAllStates() {

		List<String> states = new ArrayList<String>();

        states.add("AL");
        states.add("AK");
        states.add("AZ");
        states.add("AR");
        states.add("CA");
        states.add("CO");
        states.add("CT");
        states.add("DE");
        states.add("FL");
        states.add("GA");
        states.add("HI");
        states.add("ID");
        states.add("IL");
        states.add("IN");
        states.add("IA");
        states.add("KS");
        states.add("KY");
        states.add("LA");
        states.add("ME");
        states.add("MD");
        states.add("MA");
        states.add("MI");
        states.add("MN");
        states.add("MS");
        states.add("MO");
        states.add("MT");
        states.add("NE");
        states.add("NV");
        states.add("NH");
        states.add("NJ");
        states.add("NM");
        states.add("NY");
        states.add("NC");
        states.add("ND");
        states.add("OH");
        states.add("OK");
        states.add("OR");
        states.add("PA");
        states.add("RI");
        states.add("SC");
        states.add("SD");
        states.add("TN");
        states.add("TX");
        states.add("UT");
        states.add("VT");
        states.add("VA");
        states.add("WA");
        states.add("WV");
        states.add("WI");
        states.add("WY");
		
		return states;
	}
	
	@Override
	public String getActivityLevelFromNum(int num) {
		Map<Integer,String> activeNums = new HashMap<Integer,String>();
		
		activeNums.put(1, "inactive");
		activeNums.put(2, "sedentary");
		activeNums.put(3, "active");
		activeNums.put(4, "extremely active");
		
		String level = activeNums.get(num);
		
		return level;
		
	}
	
	@Override
	public boolean isValidEmailAddress(String email) {
		String emailRegex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*"
				+ "@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
		
		return email.matches(emailRegex);
	}
}
