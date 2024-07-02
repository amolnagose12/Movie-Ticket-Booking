package com.amoln.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "director")
	private String director;
	
	@Column(name = "descrption")
	private String description;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "total_seats")
	private Integer totalSeat;
	
	@Column(name = "available_seat")
	private Integer availableSeat;	
	
	@Column(name = "pricess")
	private Double price;
	
	
	
	
	
}
