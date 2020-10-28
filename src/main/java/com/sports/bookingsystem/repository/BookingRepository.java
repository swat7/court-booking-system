package com.sports.bookingsystem.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sports.bookingsystem.entity.Booking;

@Repository
public interface BookingRepository extends BaseRepository<Booking, Long> {
	public List<Booking> getBookingByUserId(Long userId);
}
