package com.sports.bookingsystem.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sports.bookingsystem.entity.BookedSlot;

@Repository
public interface BookedSlotRepository extends BaseRepository<BookedSlot, Long> {
	
	/*@Query("select b from BookedSlot b, Court c where b.court.id = ?1 and b.startTime > ?2 and b.endTime < ?3")
	public List<BookedSlot> findByCourtId(Long courtId, Date startDay, Date endDay);*/
	
	@Query("select b from BookedSlot b where b.court.id = ?1 and b.startTime > ?2 and b.endTime < ?3")
	public List<BookedSlot> findByCourtId(Long courtId, Date startDay, Date endDay);
}
