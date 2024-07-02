package com.amoln.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MovieDTO {
	
	private Long id;	
	
	private String title;	
	
	private String director;	
	
	private String description;	
	
	private String genre;	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;	
	
	private String location;	
	
	private Integer totalSeat;	
	
	private Integer availableSeat;	
	
	private Double price;
	
	private Integer bookedTickets;
    
    private Double totalPrice;
}
