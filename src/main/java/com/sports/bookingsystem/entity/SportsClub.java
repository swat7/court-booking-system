package com.sports.bookingsystem.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SPORTS_CLUB")
public class SportsClub extends AbstractEntity {
	
	@Id
	@Column(name = "club_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	public String name;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	public City city;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "club")
	public List<Court> courts;

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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Court> getCourts() {
		return courts;
	}

	public void setCourts(List<Court> courts) {
		this.courts = courts;
	}

}
