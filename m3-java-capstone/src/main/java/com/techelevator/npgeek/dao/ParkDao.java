package com.techelevator.npgeek.dao;

import java.util.List;

import com.techelevator.com.pojo.Park;


public interface ParkDao {

	public List<Park> getAllParks();
	
	public Park getParkByCode(String parkCode);
}
