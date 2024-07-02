package com.amoln.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amoln.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
