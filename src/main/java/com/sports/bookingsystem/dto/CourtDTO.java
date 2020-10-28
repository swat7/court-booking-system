package com.sports.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourtDTO {
	public String courtId;
	public String clubName;
	public String courtName;
	public String sport;
	public String cityName;
	public Double price;
	public String dailyStartTime;
	public String dailyEndTime;
	public String weeklyClosedDay;
}
