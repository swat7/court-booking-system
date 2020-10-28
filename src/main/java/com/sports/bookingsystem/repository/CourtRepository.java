package com.sports.bookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sports.bookingsystem.entity.Court;

@Repository
public interface CourtRepository extends BaseRepository<Court, Long> {
	
	@Query("select c from Court c, SportsClub s where s.city.id = ?1")
	public List<Court> getCourtsByCityId(Long cityId);
	
	@Query("select c from Court c, SportsClub s where c.sport = ?2 and s.city.id = ?1")
	public List<Court> getCourtsByCityAndSport(Long cityId, String type);
}
