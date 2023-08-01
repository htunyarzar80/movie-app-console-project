package com.hostmdy.movie.model;

import java.time.LocalDate;

public class Movie {
	
	private Long id;
	private String title;
	private Genre genre;
	private Integer durationByMinute;
	private Double imdbRating;
	private Boolean pg13;
	private LocalDate releasedDate;
	private String review;
	
	public Movie() {}

	public Movie(String title, Genre genre, Integer durationByMinute, Double imdbRating, Boolean pg13,
			LocalDate releasedDate, String review) {
		super();
		this.title = title;
		this.genre = genre;
		this.durationByMinute = durationByMinute;
		this.imdbRating = imdbRating;
		this.pg13 = pg13;
		this.releasedDate = releasedDate;
		this.review = review;
	}

	public Movie(Long id, String title, Genre genre, Integer durationByMinute, Double imdbRating, Boolean pg13,
			LocalDate releasedDate, String review) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.durationByMinute = durationByMinute;
		this.imdbRating = imdbRating;
		this.pg13 = pg13;
		this.releasedDate = releasedDate;
		this.review = review;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Integer getDurationByMinute() {
		return durationByMinute;
	}

	public void setDurationByMinute(Integer durationByMinute) {
		this.durationByMinute = durationByMinute;
	}

	public Double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(Double imdbRating) {
		this.imdbRating = imdbRating;
	}

	public Boolean getPg13() {
		return pg13;
	}

	public void setPg13(Boolean pg13) {
		this.pg13 = pg13;
	}

	public LocalDate getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(LocalDate releasedDate) {
		this.releasedDate = releasedDate;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", genre=" + genre + ", durationByMinute=" + durationByMinute
				+ ", imdbRating=" + imdbRating + ", pg13=" + pg13 + ", releasedDate=" + releasedDate + ", review="
				+ review + "]";
	}
	
	

}
