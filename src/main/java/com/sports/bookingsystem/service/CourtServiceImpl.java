package com.sports.bookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sports.bookingsystem.dto.ClubDTO;
import com.sports.bookingsystem.dto.CourtDTO;
import com.sports.bookingsystem.entity.City;
import com.sports.bookingsystem.entity.Court;
import com.sports.bookingsystem.entity.SportsClub;
import com.sports.bookingsystem.repository.CityRepository;
import com.sports.bookingsystem.repository.ClubRepository;
import com.sports.bookingsystem.repository.CourtRepository;
import com.sports.bookingsystem.util.ConverterUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourtServiceImpl implements CourtService {

	@Autowired
	private CourtRepository repository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ClubRepository clubRepository;

	/**
	 * Add a club with courts for a city with multiple sports.
	 * 
	 * @param ClubDTO
	 *            transfer object describing club and courts to create.
	 */

	@Override
	public void addClub(ClubDTO clubDTO) {
		SportsClub sportsClub = ConverterUtil.convertClubDTOToEntity(clubDTO);
		City city = cityRepository.getByName(clubDTO.getCity());
		sportsClub.setCity(city);
		sportsClub = clubRepository.insert(sportsClub);
		List<CourtDTO> courtDtos = clubDTO.getCourts();
		if (!CollectionUtils.isEmpty(courtDtos)) {
			for (CourtDTO courtDto : courtDtos) {
				repository.insert(ConverterUtil.convertCourtDTOToEntity(courtDto, sportsClub));
			}
		}
	}

	/**
	 * Fetches all courts for all cities with multiple sports.
	 * TODO Pagination & Sorting/Filtering
	 * 
	 */

	@Override
	public List<CourtDTO> getAllCourts() {
		List<Court> courts = repository.findAll();
		return ConverterUtil.convertCourtEntitiesToDTO(courts);
	}

	@Override
	public List<CourtDTO> getCourtsByCity(String cityName) {
		City city = cityRepository.getByName(cityName);
		List<Court> courts = repository.getCourtsByCityId(city.getId());
		return ConverterUtil.convertCourtEntitiesToDTO(courts);
	}

	@Override
	public List<CourtDTO> getCourtsByCityAndSports(String cityName, String sportType) {
		City city = cityRepository.getByName(cityName);
		List<Court> courts = repository.getCourtsByCityAndSport(city.getId(), sportType);
		return ConverterUtil.convertCourtEntitiesToDTO(courts);
	}

	@Override
	public List<CourtDTO> getCourtsBySports(String sportType) {
		List<Court> courts = null;// repository.getCourtsBySport(sportType);
		return ConverterUtil.convertCourtEntitiesToDTO(courts);
	}

	public Court getCourtEntityById(Long courtId) {
		return repository.findById(courtId).get();
	}

	@Override
	public CourtDTO getCourtById(Long courtId) {
		Court courtEntity = getCourtEntityById(courtId);
		return ConverterUtil.convertCourtEntityToDTO(courtEntity);
	}

}
