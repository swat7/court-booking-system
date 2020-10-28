package com.sports.bookingsystem.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.sports.bookingsystem.dto.BookingDTO;
import com.sports.bookingsystem.dto.ClubDTO;
import com.sports.bookingsystem.dto.CourtDTO;
import com.sports.bookingsystem.dto.SlotDTO;
import com.sports.bookingsystem.entity.BookedSlot;
import com.sports.bookingsystem.entity.Booking;
import com.sports.bookingsystem.entity.Court;
import com.sports.bookingsystem.entity.SportsClub;

public class ConverterUtil {
	public static CourtDTO convertCourtEntityToDTO(Court courtEntity) {
		CourtDTO courtDTO = new CourtDTO();
		if(courtEntity.getClub() != null) {
			courtDTO.setClubName(courtEntity.getClub().getName());
			courtDTO.setCityName(courtEntity.getClub().getCity().getName());
		}
		courtDTO.setSport(courtEntity.getSport());
		courtDTO.setCourtName(courtEntity.getName());
		courtDTO.setCourtId(courtEntity.getId().toString());
		courtDTO.setPrice(courtEntity.getPrice());
		courtDTO.setDailyStartTime(courtEntity.getDailyStartTime());
		courtDTO.setDailyEndTime(courtEntity.getDailyEndTime());
		return courtDTO;
	}
	
	public static List<CourtDTO> convertCourtEntitiesToDTO(List<Court> courts) {
		List<CourtDTO> courtDTOs = new ArrayList<>();
		if(!CollectionUtils.isEmpty(courts)) {
			for(Court court: courts) {
				courtDTOs.add(convertCourtEntityToDTO(court));
			}
		}
		return courtDTOs;
	}
	
	public static SportsClub convertClubDTOToEntity(ClubDTO clubDto) {
		SportsClub club = new SportsClub();
		club.setName(clubDto.getClubName());
		return club;
	}
	
	public static Court convertCourtDTOToEntity(CourtDTO courtDto, SportsClub clubEntity) {
		Court courtEntity = new Court();
		courtEntity.setName(courtDto.getCourtName());
		courtEntity.setSport(courtDto.getSport());
		courtEntity.setDailyStartTime(courtDto.getDailyStartTime());
		courtEntity.setDailyEndTime(courtDto.getDailyEndTime());
		courtEntity.setPrice(courtDto.getPrice());
		courtEntity.setClub(clubEntity);
		return courtEntity;
	}

	public static List<SlotDTO> convertSlotsEntityToDTO(List<BookedSlot> slots) {
		List<SlotDTO> slotDTOs = new ArrayList<>();
		if(!CollectionUtils.isEmpty(slots)) {
			for(BookedSlot slot: slots) {
				slotDTOs.add(convertSlotEntityToDTO(slot));
			}
		}
		return slotDTOs;
	}

	private static SlotDTO convertSlotEntityToDTO(BookedSlot slot) {
		SlotDTO slotDTO = new SlotDTO();
		slotDTO.setCourtId(slot.getCourt().getId());
		slotDTO.setStartTime(slot.getStartTime());
		slotDTO.setEndTime(slot.getEndTime());
		return slotDTO;
	}
	
	public static List<BookingDTO> convertBookingEntitiesToDTO(List<Booking> bookings) {
		List<BookingDTO> dtos = new ArrayList<>();
		if(!CollectionUtils.isEmpty(bookings)) {
			for(Booking entity: bookings) {
				dtos.add(convertBookingEntityToDTO(entity));
			}
		}
		return dtos;
	}

	public static BookingDTO convertBookingEntityToDTO(Booking booking) {
		BookingDTO bookingDTO = new BookingDTO();
		if(booking != null) {
			bookingDTO.setBookingId(booking.getId().toString());
			List<BookedSlot> slots = booking.getSlots();
			if(!CollectionUtils.isEmpty(slots)) {
				bookingDTO.setCourtId(booking.getSlots().get(0).getCourt().getId());
				bookingDTO.setStartTime(slots.get(0).getStartTime());
				bookingDTO.setEndTime(slots.get(0).getEndTime());
			}	
		}
		return bookingDTO;
	}
	
}
