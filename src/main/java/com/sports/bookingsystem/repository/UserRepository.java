package com.sports.bookingsystem.repository;

import org.springframework.stereotype.Repository;

import com.sports.bookingsystem.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

}
