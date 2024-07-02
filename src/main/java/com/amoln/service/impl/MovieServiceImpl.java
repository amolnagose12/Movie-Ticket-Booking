package com.amoln.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amoln.dto.MovieDTO;
import com.amoln.entity.Movie;
import com.amoln.repository.MovieRepository;
import com.amoln.service.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<MovieDTO> filterMovies(String title, LocalDate date, String Location, String genre) {
		List<Movie> movieList = movieRepository.findAll();
		List<MovieDTO> filteredMovieDTO = new ArrayList<>();

		for (Movie movie : movieList) {
			boolean match = true;

			if (title != null && !movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
				match = false;
			}
			if (date != null && !movie.getDate().isEqual(date)) {
				match = false;
			}
			if (Location != null && !movie.getLocation().contains(Location)) {
				match = false;
			}
			if (genre != null && !movie.getGenre().contains(genre)) {
				match = false;
			}
			if (match = true) {
				MovieDTO movieDTO = MovieDTO.builder().title(movie.getTitle()).date(movie.getDate())
						.location(movie.getLocation()).genre(movie.getGenre()).build();
				filteredMovieDTO.add(movieDTO);
			}
		}
		return filteredMovieDTO;
	}

	@Override
	public List<MovieDTO> getAllMovies(String title, LocalDate date, String location, String genre) {

		List<Movie> movieList = movieRepository.findAll();
		List<MovieDTO> movieDTOList = new ArrayList<>();

		for (Movie movie : movieList) {
			if (title == null && date == null && location == null && genre == null) {
				MovieDTO movieDTO = MovieDTO.builder().title(movie.getTitle()).date(movie.getDate())
						.location(movie.getLocation()).genre(movie.getGenre())
						.build();
				movieDTOList.add(movieDTO);
			} else {
				movieDTOList = filterMovies(title, date, location, genre);
			}
		}
		return movieDTOList;
	}

	@Override
	public Movie getMovieById(Long id) {
		return movieRepository.findById(id)
				.orElseThrow(
						() -> new IllegalArgumentException("movie is not available with given id " + id));
	}

	@Override
	public List<MovieDTO> getBookingHistory() {
		List<Movie> movieList = movieRepository.findAll();

		List<MovieDTO> movieDTOList = new ArrayList<>();

		for (Movie movie : movieList) {
			Integer bookedTicket = movie.getTotalSeat() - movie.getAvailableSeat();

			if (bookedTicket > 0) {
				Double totalPrice = bookedTicket * movie.getPrice();

				MovieDTO movieDTO = MovieDTO.builder().id(movie.getId()).title(movie.getTitle()).date(movie.getDate())
						.description(movie.getDescription()).location(movie.getLocation()).genre(movie.getGenre())
						.bookedTickets(bookedTicket).totalPrice(totalPrice).build();
				movieDTOList.add(movieDTO);
			}
		}
		return movieDTOList;
	}

	@Override
	public void bookTickets(Long id, Integer ticket, Integer payment) {
		Movie movie = getMovieById(id);

		Integer availableSeat = movie.getAvailableSeat();
		if (ticket > availableSeat)
			throw new IllegalArgumentException("No seats available at this time");

		Double calculatedTotalPrice = ticket * movie.getPrice();

		if (!payment.equals(calculatedTotalPrice))
			throw new IllegalArgumentException("Invalid total price");

		availableSeat -= ticket;

		movie.setAvailableSeat(availableSeat);
		movieRepository.save(movie);

	}

	@Override
	public Movie addMovie(MovieDTO movieDTO) {
		Movie movie = Movie.builder().title(movieDTO.getTitle()).director(movieDTO.getDirector())
				.description(movieDTO.getDescription()).date(movieDTO.getDate())
				.availableSeat(movieDTO.getAvailableSeat()).location(movieDTO.getLocation()).genre(movieDTO.getGenre())
				.price(movieDTO.getPrice()).totalSeat(movieDTO.getTotalSeat()).build();

		return movieRepository.save(movie);

	}

	@Override
	public void deleteMovie(Long id) {
//		Movie movie = getMovieById(id);
		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid movie id " + id));
		movieRepository.delete(movie);
	}

	@Override
	public void updateMovie(MovieDTO movieDTO) {
		Movie movie = getMovieById(movieDTO.getId());

		movie = movie.builder().title(movieDTO.getTitle()).director(movieDTO.getDirector())
				.description(movieDTO.getDescription()).location(movieDTO.getLocation()).date(movieDTO.getDate())
				.genre(movieDTO.getGenre()).price(movieDTO.getPrice()).totalSeat(movieDTO.getTotalSeat()).build();

		movieRepository.save(movie);
	}

}
