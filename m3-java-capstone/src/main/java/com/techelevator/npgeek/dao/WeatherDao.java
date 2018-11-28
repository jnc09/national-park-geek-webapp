package com.techelevator.npgeek.dao;

import java.util.List;

import com.techelevator.com.pojo.Weather;

public interface WeatherDao {

	public List<Weather> getWeatherForPark(String parkcode);
	
	
}
