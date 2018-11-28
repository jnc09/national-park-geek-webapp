package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.com.pojo.Weather;

@Component
public class JdbcWeatherDao implements WeatherDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcWeatherDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Weather> getWeatherForPark(String parkcode) {
		
		List<Weather> weatherList = new ArrayList<Weather>();
		
		String sql = "SELECT * FROM weather WHERE parkcode = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkcode);
		
		while(results.next()) {
			Weather weather = mapRowToWeather(results);
			weatherList.add(weather);
		}
		
		return weatherList;
	}
	
	public Weather mapRowToWeather(SqlRowSet results) {
		
		Weather weather = new Weather();
		
		weather.setParkcode(results.getString("parkcode"));
		weather.setDayValue(results.getInt("fivedayforecastvalue"));
		weather.setForecast(results.getString("forecast"));
		weather.setTempLow(results.getInt("low"));
		weather.setTempHigh(results.getInt("high"));
		weather.setImageName(results.getString("forecast"));
		
		return weather;
	}

}
