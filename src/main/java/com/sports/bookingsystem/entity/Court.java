package com.sports.bookingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "COURT", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"club_id", "sport"})
	})
public class Court extends AbstractEntity {

	@Id
	@Column(name = "court_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	public String name;

	@Column(name = "sport")
	public String sport;

	@Column(name = "price")
	public Double price;

	@Column(name = "daily_start_time")
	public String dailyStartTime;

	@Column(name = "daily_end_time")
	public String dailyEndTime;

	@Column(name = "weekly_closed_Day")
	public String weeklyClosedDay;
	
	@ManyToOne
	@JoinColumn(name = "club_id")
	public SportsClub club;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDailyStartTime() {
		return dailyStartTime;
	}

	public void setDailyStartTime(String dailyStartTime) {
		this.dailyStartTime = dailyStartTime;
	}

	public String getDailyEndTime() {
		return dailyEndTime;
	}

	public void setDailyEndTime(String dailyEndTime) {
		this.dailyEndTime = dailyEndTime;
	}

	public String getWeeklyClosedDay() {
		return weeklyClosedDay;
	}

	public void setWeeklyClosedDay(String weeklyClosedDay) {
		this.weeklyClosedDay = weeklyClosedDay;
	}

	public SportsClub getClub() {
		return club;
	}

	public void setClub(SportsClub club) {
		this.club = club;
	}
}
