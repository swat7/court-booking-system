package com.sports.bookingsystem.service.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.sports.bookingsystem.dto.BookingDTO;
import com.sports.bookingsystem.entity.BookedSlot;
import com.sports.bookingsystem.entity.Booking;
import com.sports.bookingsystem.entity.Court;
import com.sports.bookingsystem.entity.User;
import com.sports.bookingsystem.repository.BookedSlotRepository;
import com.sports.bookingsystem.repository.BookingRepository;
import com.sports.bookingsystem.repository.CourtRepository;
import com.sports.bookingsystem.repository.UserRepository;
import com.sports.bookingsystem.service.BookingServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceUnitTest {
	
	@Mock
	private BookingRepository repository;
	
	@Mock
	private BookedSlotRepository slotRepository;
	
	@Mock
	private CourtRepository courtRepository;
	
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	BookingServiceImpl classToTest;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createBookingTest() throws Exception {
		BookedSlot slot = new BookedSlot();
		doReturn(getCourt()).when(courtRepository).findById(any());
		doReturn(getUser()).when(userRepository).findById(any());
		doReturn(slot).when(slotRepository).insert(any());
		doReturn(getBooking()).when(repository).insert(any());
		BookingDTO bookedDTO = classToTest.createBooking(getBookingDTO());
		assertEquals(bookedDTO.getBookingId(), "3");
	}
	
	@Test(expected = Exception.class)
	public void createBookingExceptionTest() throws Exception {
		classToTest.createBooking(getBookingDTO());
	}

	private BookingDTO getBookingDTO() {
		BookingDTO dto = new BookingDTO();
		dto.setCourtId(1L);
		dto.setStartTime(new Date());
		dto.setEndTime(new Date());
		dto.setUserId(2L);
		return dto;
	}
	
	private Booking getBooking() {
		Booking booking = new Booking();
		booking.setId(3L);
		booking.setUser(new User());
		return booking;
	}
	
	private Optional<Court> getCourt() {
		Court courtEntity = new Court();
		Optional<Court> court = Optional.ofNullable(courtEntity);
		return court;
	}
	
	private Optional<User> getUser() {
		User user = new User();
		Optional<User> userOptional = Optional.ofNullable(user);
		return userOptional;
	}
	

}
