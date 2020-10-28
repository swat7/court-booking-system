package com.sports.bookingsystem.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sports.bookingsystem.dto.BookingDTO;
import com.sports.bookingsystem.dto.SlotDTO;
import com.sports.bookingsystem.service.BookingService;
import com.sports.bookingsystem.util.ResponseEntityBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sports/booking-system")
@Slf4j
public class BookingController {

	@Autowired
	private BookingService service;

	/*
	 * API for booking a court Assumption: Booking day should be within two days
	 * Each slot is of 1 hour duration
	 */
	@RequestMapping(value = "/bookings", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity createBooking(@RequestBody BookingDTO booking, @RequestHeader Map<String, String> headers) {
		try {
			BookingDTO bookingResponse = service.createBooking(booking);
			return ResponseEntityBuilder.buildSuccessResponse(bookingResponse);
		} catch (Exception e) {
			return ResponseEntityBuilder.buildFailureResponse(e);
		}
	}

	/*
	 * API for getting all available slots for a court within startDay and endDay
	 * startDay should be current day. Assumption: endDay should be maximum two days
	 * Each slot is of 1 hour duration
	 * 
	 */
	@RequestMapping(value = "/available-slots/{court-id}", method = RequestMethod.GET)
	public ResponseEntity getAvailableSlotsForCourt(@PathVariable("court-id") Long courtId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDay,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDay,
			@RequestHeader Map<String, String> headers) {
		List<SlotDTO> slots;
		try {
			slots = service.getAvailableSlotsForCourt(courtId, startDay, endDay);
		} catch (Exception e) {
			return ResponseEntityBuilder.buildFailureResponse(e);
		}
		return ResponseEntityBuilder.buildSuccessResponse(slots);
	}

	/*
	 * API for getting all bookings
	 * UserID would be present in the headers.
	 * For Simplicity - Passing as QueryParam
	 */
	@RequestMapping(value = "/bookings", method = RequestMethod.GET)
	public ResponseEntity getBookingsForUser(@RequestParam Long userId, @RequestHeader Map<String, String> headers) {
		List<BookingDTO> bookings = service.getBookingsForUser(userId);
		return ResponseEntityBuilder.buildSuccessResponse(bookings);
	}
	
	@RequestMapping(value = "/booked-slots/{court-id}", method = RequestMethod.GET)
	public ResponseEntity getBookedSlotsForCourt(@PathVariable("court-id") Long courtId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDay,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDay,
			@RequestHeader Map<String, String> headers) {
		List<SlotDTO> slots = service.getBookedSlotsForCourt(courtId, startDay, endDay);
		return ResponseEntityBuilder.buildSuccessResponse(slots);
	}

	/*
	 * API for cancelling a booking
	 * 
	 */
	@RequestMapping(value = "/bookings/{booking-id}/cancel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity createBooking(@PathVariable String bookingId, @RequestHeader Map<String, String> headers) {
		return null;
	}

}
