package com.hostmdy.movie.controller;

import java.util.List;
import java.util.Optional;

import com.hostmdy.movie.model.Genre;
import com.hostmdy.movie.model.Movie;
import com.hostmdy.movie.model.MovieDAO;

public class MovieController {
	
	private final MovieDAO movieDAO = new MovieDAO();
	
	public List<Movie> getAllMovie() {

		return movieDAO.getAllMovie().stream().sorted((m1,m2)-> m1.getTitle().compareTo(m2.getTitle()))
			.toList();
	}
	
	public boolean createMovie(Movie movie) {
		int rowEffected = movieDAO.createMovie(movie);
		return rowEffected > 0? true : false;
	}
	
	public boolean updateMovie(Movie movie) {
		Optional<Movie> movieOpt = getMovieById(movie.getId());
		if(movieOpt.isEmpty()) {
			return false;
		}
		
		Movie updateMovie = movieOpt.get();
		if(!(movie.getTitle().isEmpty())) {
			updateMovie.setTitle(movie.getTitle());
		}
		
		if(!(movie.getGenre().equals(Genre.NOT_SPECIFIED))) {
			updateMovie.setGenre(movie.getGenre());
		}
		
		if(movie.getDurationByMinute() > 0) {
			updateMovie.setDurationByMinute(movie.getDurationByMinute());
		}
		
		if(movie.getImdbRating() > 0.0) {
			updateMovie.setImdbRating(movie.getImdbRating());
		}
		
		if(movie.getPg13() != null) {
			updateMovie.setPg13(movie.getPg13());
		}
		
		if(movie.getReleasedDate() != null) {
			updateMovie.setReleasedDate(movie.getReleasedDate());
		}
		
		if(!(movie.getReview().isEmpty())) {
			updateMovie.setReview(movie.getReview());
		}
		
		int rowEffected = movieDAO.updateMovie(updateMovie);
		return rowEffected > 0? true : false;
	}
	
	public boolean deleteMovie(Long id) {
		return movieDAO.deleteMovie(id) > 0? true : false;
	}
	
	public Optional<Movie> getMovieById(Long id) {
		return movieDAO.getMovieById(id);
	}
	
	public List<Movie> searchMovie(String columnName,String value) {
		return movieDAO.searchMovie(columnName,value);
	}

}
