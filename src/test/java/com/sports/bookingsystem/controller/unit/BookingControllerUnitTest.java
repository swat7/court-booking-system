package com.sports.bookingsystem.controller.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.sports.bookingsystem.controller.BookingController;
import com.sports.bookingsystem.dto.BookingDTO;
import com.sports.bookingsystem.dto.SlotDTO;
import com.sports.bookingsystem.service.BookingService;

@RunWith(MockitoJUnitRunner.class)
public class BookingControllerUnitTest {

	@Mock
	BookingService bookingService;

	@InjectMocks
	private BookingController classToTest;

	Map<String, String> headers;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		headers = new HashMap<String, String>();
		headers.put("user_id", "23");
	}

	@Test
	public void createBookingTest() throws Exception {
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setCourtId(1L);
		bookingDTO.setStartTime(new Date());
		bookingDTO.setEndTime(new Date());
		bookingDTO.setUserId(Long.parseLong(headers.get("user_id")));
		when(bookingService.createBooking(any())).thenReturn(bookingDTO);
		ResponseEntity response = classToTest.createBooking(bookingDTO, headers);
		assertEquals(200, response.getStatusCode().value());
	}

	@Test
	public void getAvailableSlotsTest() throws Exception {
		Long courtId = 1L;
		Date startDay = new Date();
		Date endDay = new Date();
		List<SlotDTO> slots = new ArrayList<>();
		when(bookingService.getAvailableSlotsForCourt(any(), any(), any())).thenReturn(slots);
		ResponseEntity response = classToTest.getAvailableSlotsForCourt(courtId, startDay, endDay, headers);
		assertEquals(200, response.getStatusCode().value());
	}

	@Test
	public void getBookedSlotsTest() throws Exception {
		Long courtId = 1L;
		Date startDay = new Date();
		Date endDay = new Date();
		List<SlotDTO> slots = new ArrayList<>();
		when(bookingService.getBookedSlotsForCourt(any(), any(), any())).thenReturn(slots);
		ResponseEntity response = classToTest.getBookedSlotsForCourt(courtId, startDay, endDay, headers);
		assertEquals(200, response.getStatusCode().value());
	}

}
