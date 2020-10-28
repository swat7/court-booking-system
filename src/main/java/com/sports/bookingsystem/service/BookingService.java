package com.sports.bookingsystem.service;

import java.util.Date;
import java.util.List;

import com.sports.bookingsystem.dto.BookingDTO;
import com.sports.bookingsystem.dto.SlotDTO;

public interface BookingService {
	
	public BookingDTO createBooking(BookingDTO booking) throws Exception;
	public void cancelBooking(Long bookingId);
	public List<BookingDTO> getBookingsForUser(Long userId);
	
	public List<SlotDTO> getAvailableSlotsForCourt(Long court, Date startDay, Date endDay) throws Exception;
	public List<SlotDTO> getBookedSlotsForCourt(Long court, Date startDay, Date endDay);

}
