package com.amoln.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amoln.dto.MovieDTO;
import com.amoln.dto.ResponseDTO;
import com.amoln.entity.Movie;
import com.amoln.service.IMovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private IMovieService movieService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseDTO> addMovie(@RequestBody MovieDTO movieDTO){
		Movie movie = movieService.addMovie(movieDTO);
		ResponseDTO response = ResponseDTO.builder().message("movie added successfully").data(movie).build();
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> fetchByID(@PathVariable("id") Long id ){
		try {
			Movie movie = movieService.getMovieById(id);
			ResponseDTO response = ResponseDTO.builder().message("movie fetch successfully with id " + id).data(movie).build();
			return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);		
		} catch (Exception e) {
			throw new IllegalArgumentException("No movie found with given id :" +id);
		}
	}

}
