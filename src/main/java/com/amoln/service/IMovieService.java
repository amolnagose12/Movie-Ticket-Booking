package com.amoln.service;

import java.time.LocalDate;
import java.util.List;

import com.amoln.dto.MovieDTO;
import com.amoln.entity.Movie;

public interface IMovieService {

	List<MovieDTO> filterMovies(String title,LocalDate date,
			String Location, String genre);
	
	List<MovieDTO> getAllMovies(String title, LocalDate date, String location, String genre);
	
	Movie getMovieById(Long id);
	
	List<MovieDTO> getBookingHistory();
	
	void bookTickets(Long id, Integer ticket, Integer payment);
	
	Movie addMovie(MovieDTO movieDTO);
	
	void deleteMovie(Long id);
	
	void updateMovie(MovieDTO movieDTO);
	
	
}
