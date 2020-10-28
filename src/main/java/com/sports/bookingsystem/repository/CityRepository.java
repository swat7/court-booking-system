package com.sports.bookingsystem.repository;

import org.springframework.stereotype.Repository;

import com.sports.bookingsystem.entity.City;

@Repository
public interface CityRepository extends BaseRepository<City, Long> {
	public City getByName(String cityName);
}
