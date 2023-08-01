package com.hostmdy.movie.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.hostmdy.movie.controller.MovieController;
import com.hostmdy.movie.model.Genre;
import com.hostmdy.movie.model.Movie;

public class MovieView {
	
	private final MovieController movieController = new MovieController();
	
	private void showMovieDetails(Movie movie) {
		System.out.println("Movie Title : "+movie.getTitle());
		System.out.println("Movie Duration : "+movie.getDurationByMinute()+" min");
		System.out.println("Movie Genre : "+movie.getGenre());
		System.out.println("Movie ImdbRating : "+movie.getImdbRating()+"/10");
		System.out.println("Is Movie PG-13 : "+(movie.getPg13() ? "Yes" : "No"));
		System.out.println("Movie Released Date : "+movie.getReleasedDate());
		System.out.println();
		System.out.println("Movie Review : ");
		System.out.println(movie.getReview());
	}
	
	public void showAllMovie() {
		List<Movie> movieList = movieController.getAllMovie();
		
		int i = 1;
		System.out.println("All Movies are as follows : ");
		System.out.println("Total : "+movieList.size());
		for(final Movie movie : movieList) {
			System.out.println("Movie No : "+i);
			showMovieDetails(movie);
			System.out.println("\r\n");
			i++;
		}
	}
	
	public void showSearchedMovie(Scanner userInput) {
		
		System.out.println("Enter Search Category : no space");
		String columnName = userInput.next();
		System.out.println("Enter Search Query : ");
		userInput.nextLine();
		String value = userInput.nextLine();
		
		List<Movie> movieList = movieController.searchMovie(columnName, value);
		
		int i = 1;
		System.out.println("All Searched Movies are as follows : ");
		System.out.println("Total : "+movieList.size());
		for(final Movie movie : movieList) {
			System.out.println("Movie No : "+i);
			showMovieDetails(movie);
			System.out.println("\r\n");
			i++;
		}
	}
	
	public void showCreateForm(Scanner userInput) {
		System.out.println("Enter Movie Title : ");
		userInput.nextLine();
		String title = userInput.nextLine();
		System.out.println("Choose Genre : 1.Drama 2.Action 3.Melo Drama 4.Fiction 5.Science Fiction 6.Kid 7.Horror 8.Fantasy 9.Biography");
		int rawGenre = userInput.nextInt();
		Genre genre = switch (rawGenre) {
		case 1 -> Genre.DRAMA;
		case 2 -> Genre.ACTION;
		case 3 -> Genre.MELO_DRAMA;
		case 4 -> Genre.FICTION;
		case 5 -> Genre.SCIENCE_FICTION;
		case 6 -> Genre.Kid;
		case 7 -> Genre.HORROR;
		case 8 -> Genre.FANTASY;
		case 9 -> Genre.BIOGRAPHY;
		default -> Genre.DRAMA;
		};
		System.out.println("Enter Movie Duration : min");
		Integer durationByMinute = userInput.nextInt();
		System.out.println("Enter Movie Imdb Rating");
		Double imdbRating = userInput.nextDouble();
		System.out.println("Is PG-13?yes-y or no-n");
		Boolean pg13 = userInput.next().toLowerCase().charAt(0) == 'y'? true : false;
		System.out.println("Enter Movie Released Date : yyyy-MM-dd");
		LocalDate releasedDate = LocalDate.parse(userInput.next());
		System.out.println("Enter Movie Review : maximum - 1000 charactors");
		userInput.nextLine();
		String review = userInput.nextLine();
		
		Movie movie = new Movie(title, genre, durationByMinute, imdbRating, pg13, releasedDate, review);
		if(movieController.createMovie(movie)) {
			System.out.println("created movie!");
		}else {
			System.out.println("Fail to create movie");
		}
	}
	
	public void showUpdateForm(Scanner userInput) {
		String title = "";
		Genre genre = Genre.NOT_SPECIFIED;
		Integer durationByMinute = 0;
		Double imdbRating = 0.0;
		Boolean pg13 = null;
		LocalDate releasedDate = null;
		String review = "";
		
		System.out.println("Enter Movie ID : ");
		Long movieId = userInput.nextLong();
		
		Optional<Movie> movieOpt = movieController.getMovieById(movieId);
		if(movieOpt.isEmpty()) {
			return;
		}
		
		Movie dbMovie = movieOpt.get();
		
		showMovieDetails(dbMovie);
		
		
		while (true) {
			System.out.println("Choose Input : 1.title 2.genre 3.duration 4.imdbRating 5.pg13 6.releasedDate 7.review");
			int inputType = userInput.nextInt();
			switch (inputType) {
			case 1 -> {
				System.out.println("Enter Movie Title : ");
				userInput.nextLine();
				title = userInput.nextLine();
			}
			case 2 -> {
				System.out.println("Choose Genre : 1.Drama 2.Action 3.Melo Drama 4.Fiction 5.Science Fiction 6.Kid 7.Horror 8.Fantasy 9.Biography");
				int rawGenre = userInput.nextInt();
				genre = switch (rawGenre) {
				case 1 -> Genre.DRAMA;
				case 2 -> Genre.ACTION;
				case 3 -> Genre.MELO_DRAMA;
				case 4 -> Genre.FICTION;
				case 5 -> Genre.SCIENCE_FICTION;
				case 6 -> Genre.Kid;
				case 7 -> Genre.HORROR;
				case 8 -> Genre.FANTASY;
				case 9 -> Genre.BIOGRAPHY;
				default -> Genre.DRAMA;
				};
			}
			
			case 3 -> {
				System.out.println("Enter Movie Duration : min");
				durationByMinute = userInput.nextInt();
			}
			
			case 4 -> {
				System.out.println("Enter Movie Imdb Rating");
				imdbRating = userInput.nextDouble();
			}
			
			case 5 ->{
				System.out.println("Is PG-13?yes-y or no-n");
				pg13 = userInput.next().toLowerCase().charAt(0) == 'y'? true : false;
			}
			
			case 6->{
				System.out.println("Enter Movie Released Date : yyyy-MM-dd");
				releasedDate = LocalDate.parse(userInput.next());
			}
			
			case 7->{
				System.out.println("Enter Movie Review : maximum - 1000 charactors");
				userInput.nextLine();
				review = userInput.nextLine();
			}
			default -> System.out.println("Invalid Input Type");
			}/* end switch */
			System.out.println("finshed?yes-y or no-n");
			char decision = userInput.next().charAt(0);
			if(decision == 'y') break;
		}/* end while loop */
		Movie movie = new Movie(movieId, title, genre, durationByMinute, imdbRating, pg13, releasedDate, review);
		if(movieController.updateMovie(movie)) {
			System.out.println("movie with id = "+movieId+" is updated!");
		}else {
			System.out.println("something wrong update fail.check you input data or id");
		}
	}/*end method */
	
	public void showDeleteForm(Scanner userInput) {
		System.out.println("Enter Movie ID : ");
		Long id = userInput.nextLong();
		if(movieController.deleteMovie(id)) {
			System.out.println("movie with id = "+id+" is deleted");
		}else {
			System.out.println("movie is failed to delete");
		}
	}

}
