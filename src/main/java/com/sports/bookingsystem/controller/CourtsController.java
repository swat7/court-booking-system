package com.sports.bookingsystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sports.bookingsystem.dto.ClubDTO;
import com.sports.bookingsystem.dto.CourtDTO;
import com.sports.bookingsystem.service.CourtService;
import com.sports.bookingsystem.util.ResponseEntityBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sports/booking-system")
@Slf4j
public class CourtsController {
	
	@Autowired
	private CourtService service;
	
	@RequestMapping(value = "/clubs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity addClub(@RequestBody ClubDTO clubDTO,
			@RequestHeader Map<String, String> headers) {
		service.addClub(clubDTO);
		return ResponseEntityBuilder.buildSuccessResponse();
	}
	
	/*
	 * API for getting all courts
	 * Can add pagination if required, sorting & filtering
	 */
	@RequestMapping(value = "/courts", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@GetMapping
	public ResponseEntity getAllCourts(
			@RequestHeader Map<String, String> headers) {
		List<CourtDTO> courts = service.getAllCourts();
		return ResponseEntityBuilder.buildSuccessResponse(courts);
	}
	
	/*
	 * API for getting all courts of a city
	 * Can add pagination if required, sorting & filtering
	 */
	@RequestMapping(value = "/courts/{city-id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@GetMapping
	public ResponseEntity getCourtsForCity(@PathVariable("city-id") String cityName,
			@RequestHeader Map<String, String> headers) {
		List<CourtDTO> courts = service.getCourtsByCity(cityName);
		return ResponseEntityBuilder.buildSuccessResponse(courts);
	}
	
	/*
	 *  API for getting all courts of a city and a sport
	 *  Can add pagination if required, sorting & filtering
	 */
	@RequestMapping(value = "/courts/{city-id}/sports/{sport-id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@GetMapping
	public ResponseEntity getCourtsForSports(@PathVariable("city-id") String cityName, @PathVariable("sport-id") String sportType,
			@RequestHeader Map<String, String> headers) {
		List<CourtDTO> courts = service.getCourtsByCityAndSports(cityName, sportType);
		return ResponseEntityBuilder.buildSuccessResponse(courts);
	}
}
