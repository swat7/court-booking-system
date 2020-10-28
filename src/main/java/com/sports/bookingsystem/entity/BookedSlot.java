package com.sports.bookingsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "BOOKED_COURT_SLOT", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"court_id", "start_time", "end_time"})
	})
public class BookedSlot extends AbstractEntity {
	
	@Id
	@Column(name = "slot_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "court_id")
	public Court court;
	
	@Column(name = "start_time")
	public Date startTime;
	
	@Column(name = "end_time")
	public Date endTime;
	
	public Double price;
}
