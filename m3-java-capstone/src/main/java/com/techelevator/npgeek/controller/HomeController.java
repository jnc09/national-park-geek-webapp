package com.techelevator.npgeek.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.com.pojo.Park;
import com.techelevator.com.pojo.Weather;
import com.techelevator.npgeek.dao.JdbcWeatherDao;
import com.techelevator.npgeek.dao.ParkDao;
import com.techelevator.npgeek.dao.WeatherDao;

@Controller
public class HomeController {

	@Autowired
	WeatherDao weatherDao;
	
	@Autowired
	ParkDao parkDao;
		
	@RequestMapping("/")
	public String showHomePage(ModelMap map) {
		
		List<Park> parkList = parkDao.getAllParks();
		
		map.put("parks", parkList);
		
		return "homePage";
	}
	
	@RequestMapping(path="/parkDetail", method=RequestMethod.GET)
	public String showDetailPage(HttpSession session,
								ModelMap map, 
								@RequestParam String parkCode) {
		
		String code = parkCode.toUpperCase();
		
		List<Weather> parkWeather = weatherDao.getWeatherForPark(code);
		Park park = parkDao.getParkByCode(code);
		
		map.put("weather", parkWeather);
		map.put("park", park);
		
		return "parkDetail";
	}
	
	@RequestMapping(path="/parkDetail", method=RequestMethod.POST)
	public String processScaleSelction(HttpSession session,
										@RequestParam String parkCode,
										@RequestParam String scale) {
		session.setAttribute("scale", scale);
		return "redirect:/parkDetail?parkCode=" + parkCode;
	}
	
	
	
}
