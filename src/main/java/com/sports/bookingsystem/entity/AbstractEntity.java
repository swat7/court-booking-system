package com.sports.bookingsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractEntity implements Entity {
	
	@Column(name = "RECORD_CREATE_TIME", length = 6)
	@Temporal(TemporalType.TIMESTAMP)
	private Date recordCreateTime;

	@Column(name = "RECORD_UPDATE_TIME", length = 6)
	@Temporal(TemporalType.TIMESTAMP)
	private Date recordUpdateTime;
}
