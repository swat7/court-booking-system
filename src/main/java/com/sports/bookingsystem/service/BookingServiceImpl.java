package com.sports.bookingsystem.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.sports.bookingsystem.dto.BookingDTO;
import com.sports.bookingsystem.dto.BookingStatus;
import com.sports.bookingsystem.dto.SlotDTO;
import com.sports.bookingsystem.entity.BookedSlot;
import com.sports.bookingsystem.entity.Booking;
import com.sports.bookingsystem.entity.Court;
import com.sports.bookingsystem.entity.User;
import com.sports.bookingsystem.repository.BookedSlotRepository;
import com.sports.bookingsystem.repository.BookingRepository;
import com.sports.bookingsystem.repository.CourtRepository;
import com.sports.bookingsystem.repository.UserRepository;
import com.sports.bookingsystem.util.ConverterUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository repository;

	@Autowired
	private BookedSlotRepository slotRepository;

	@Autowired
	private CourtRepository courtRepository;

	@Autowired
	private UserRepository userRepository;

	private Object lockObj;

	/**
	 * Creates a booking.
	 * 
	 * @param BookingDTO
	 *            transfer object describing the booking.
	 * @throws Exception
	 *             if court is null or is not found.
	 * @throws Exception
	 *             if multiple bookings at same day and time for a court
	 *
	 */
	@Transactional
	@Override
	public BookingDTO createBooking(BookingDTO booking) throws Exception {
		Long courtId = booking.getCourtId();

		BookedSlot slot = new BookedSlot();
		Optional<Court> court = courtRepository.findById(courtId);
		Optional<User> user = userRepository.findById(booking.getUserId());

		// If User Not present - Create Autogenerated User mapping Mobile Number
		synchronized (lockObj) {
			if (!user.isPresent()) {
				User guestUser = new User();
				// Set the mobile Number
				user = Optional.ofNullable(userRepository.insert(guestUser));
			}
			if (court.isPresent()) {
				slot.setCourt(court.get());
				slot.setStartTime(booking.getStartTime());
				slot.setEndTime(booking.getEndTime());
				slot = slotRepository.insert(slot);
				List<BookedSlot> slots = new ArrayList<>();
				slots.add(slot);
				Booking bookingEntity = new Booking();
				bookingEntity.setUser(user.get());
				bookingEntity.setSlots(slots);
				bookingEntity.setStatus(BookingStatus.RESERVED.toString());
				Booking bookedEntity = repository.insert(bookingEntity);
				return ConverterUtil.convertBookingEntityToDTO(bookedEntity);
			} else {
				// No Court found
				throw new Exception("No court found");
			}
		}
	}

	/**
	 * Get all slots for a court within a date range
	 * 
	 * @param courtId
	 * @param startDay
	 *            and endDay startDay should be current day. Assumption: endDay
	 *            should be maximum two days Each slot is of 1 hour duration
	 * @throws Exception
	 *             if court is null or is not found.
	 * @throws DataAccessException
	 *             in case of error on a persistence layer.
	 */
	@Override
	public List<SlotDTO> getAvailableSlotsForCourt(Long courtId, Date startDay, Date endDay) throws Exception {
		Court court = courtRepository.findById(courtId).get();
		List<BookedSlot> slots = slotRepository.findByCourtId(courtId, startDay, endDay);
		String dailyStartTime = court.getDailyStartTime();
		String dailyEndTime = court.getDailyEndTime();

		// Parse Daily start and end times
		Date newStartSlot = parseDateWithStartTime(startDay, dailyStartTime);
		List<Date> bookedDates = slots.stream().map(s -> s.getStartTime()).collect(Collectors.toList());
		List<SlotDTO> availableSlots = new ArrayList<>();

		for (Date date = newStartSlot; date.before(endDay); date = addHoursToJavaUtilDate(date, 1)) {
			Date endSlotTime = parseDateWithStartTime(date, dailyEndTime);
			Date startSlotTime = parseDateWithStartTime(date, dailyStartTime);
			if (!date.before(endSlotTime) || date.before(startSlotTime))
				continue;

			if (!bookedDates.contains(date)) {
				SlotDTO slotDTO = new SlotDTO();
				slotDTO.setStartTime(date);
				slotDTO.setEndTime(addHoursToJavaUtilDate(date, 1));
				slotDTO.setCourtId(courtId);
				slotDTO.setPrice(court.getPrice());
				availableSlots.add(slotDTO);
			}
		}
		return availableSlots;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sports.bookingsystem.service.BookingService#getBookedSlotsForCourt(java.
	 * lang.Long)
	 */
	@Override
	public List<SlotDTO> getBookedSlotsForCourt(Long courtId, Date startDay, Date endDay) {
		List<BookedSlot> slots = slotRepository.findByCourtId(courtId, startDay, endDay);
		return ConverterUtil.convertSlotsEntityToDTO(slots);
	}

	@Override
	public void cancelBooking(Long bookingId) {

	}

	@Override
	public List<BookingDTO> getBookingsForUser(Long userId) {
		List<Booking> bookings = repository.getBookingByUserId(userId);
		return ConverterUtil.convertBookingEntitiesToDTO(bookings);
	}

	private Date addHoursToJavaUtilDate(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}

	private Date addDaysToJavaUtilDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	private Date parseDateWithStartTime(Date startDay, String dailyTime) {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat df = new SimpleDateFormat();
		df.setTimeZone(TimeZone.getTimeZone("GMT"));

		String gmtStr1 = df.format(startDay);
		String[] arr = gmtStr1.split(" ");
		sb.append(arr[0]);
		sb.append(" ");
		String[] dailyTimeStr = dailyTime.split(" ");
		sb.append(dailyTimeStr[0]).append(" ");
		sb.append(dailyTimeStr[1]);
		try {
			return df.parse(sb.toString());
		} catch (ParseException e) {
			log.error("");
		}
		return startDay;
	}

}
