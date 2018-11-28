package com.techelevator.com.pojo;

import java.util.HashMap;
import java.util.Map;

public class Weather {
	
	private String parkcode;
	private int dayValue;
	private int tempLow;
	private int tempHigh;
	private String forecast;
	private String imageName;
	
	public String getParkcode() {
		return parkcode;
	}
	public void setParkcode(String parkcode) {
		this.parkcode = parkcode;
	}
	public int getDayValue() {
		return dayValue;
	}
	public void setDayValue(int dayValue) {
		this.dayValue = dayValue;
	}
	public int getTempLow() {
		return tempLow;
	}
	public void setTempLow(int tempLow) {
		this.tempLow = tempLow;
	}
	public int getTempHigh() {
		return tempHigh;
	}
	public void setTempHigh(int tempHigh) {
		this.tempHigh = tempHigh;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	public void setImageName(String forecast) {
		
		Map<String, String> weatherImage = new HashMap<String, String>();
		weatherImage.put("cloudy", "cloudy.png");
		weatherImage.put("snow", "snow.png");
		weatherImage.put("rain", "rain.png");
		weatherImage.put("sunny", "sunny.png");
		weatherImage.put("thunderstorms", "thunderstorms.png");
		weatherImage.put("partly cloudy", "partlyCloudy.png");
		
		this.imageName = weatherImage.get(forecast);
	}
	
	public String getImageName() {
		return imageName;
	}
	
	

}
