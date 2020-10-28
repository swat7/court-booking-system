package com.sports.bookingsystem.service;

import java.util.List;

import com.sports.bookingsystem.dto.ClubDTO;
import com.sports.bookingsystem.dto.CourtDTO;

public interface CourtService {
	
	public void addClub(ClubDTO clubDTO);
	public CourtDTO getCourtById(Long courtId);
	
	public List<CourtDTO> getAllCourts();
	public List<CourtDTO> getCourtsByCity(String cityName);
	public List<CourtDTO> getCourtsByCityAndSports(String cityName, String sportType);
	public List<CourtDTO> getCourtsBySports(String sportType);
	
	
}
