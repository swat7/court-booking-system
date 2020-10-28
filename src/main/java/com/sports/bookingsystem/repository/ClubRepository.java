package com.sports.bookingsystem.repository;

import org.springframework.stereotype.Repository;

import com.sports.bookingsystem.entity.SportsClub;

@Repository
public interface ClubRepository extends BaseRepository<SportsClub, Long> {

}
