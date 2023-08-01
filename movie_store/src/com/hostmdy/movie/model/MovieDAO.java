package com.hostmdy.movie.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.hostmdy.movie.database.DatabaseConnection;

public class MovieDAO {

	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	private void close() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Read R
	public List<Movie> getAllMovie() {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();
		if (connectionOpt.isEmpty()) {
			System.out.println("### Connection object is not present");
			return Collections.emptyList();
		}

		connection = connectionOpt.get();

		List<Movie> movieList = new ArrayList<>();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from movie;");

			while (rs.next()) {
				movieList.add(new Movie(rs.getLong("id"), rs.getString("title"), Genre.valueOf(rs.getString("genre")),
						rs.getInt("durationByMinute"), rs.getDouble("imdbRating"), rs.getBoolean("pg13"),
						rs.getDate("releasedDate").toLocalDate(), rs.getString("review")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return movieList;
	}

	//Create C
	public int createMovie(Movie movie) {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		int rowEffected = 0;
		if (connectionOpt.isEmpty()) {
			return rowEffected;
		}
		connection = connectionOpt.get();
		try {
			//bad practice
//			stmt = connection.createStatement();
//			rowEffected = stmt.executeUpdate(
//					"insert into movie " + "(title,genre,durationByMinute,imdbRating,pg13,releasedDate,review) "
//							+ "values('" + movie.getTitle() + "','" + movie.getGenre() + "','" + movie.getDurationByMinute()
//							+ "','" + movie.getImdbRating() + "','" + (movie.getPg13() ? 1 : 0) + "','" + movie.getReleasedDate() + "','"
//							+ movie.getReview() + "');");
			pStmt = connection.prepareStatement("insert into movie "
					+ "(title,genre,durationByMinute,imdbRating,pg13,releasedDate,review) "
					+ "values(?,?,?,?,?,?,?);");
			pStmt.setString(1,movie.getTitle());
			pStmt.setString(2,movie.getGenre().toString());
			pStmt.setInt(3, movie.getDurationByMinute());
			pStmt.setDouble(4,movie.getImdbRating());
			pStmt.setBoolean(5, movie.getPg13());
			pStmt.setDate(6,Date.valueOf(movie.getReleasedDate()));
			pStmt.setString(7,movie.getReview());
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return rowEffected;

	}
	
	//Update U
	public int updateMovie(Movie movie) {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		int rowEffected = 0;
		if (connectionOpt.isEmpty()) {
			return rowEffected;
		}
		connection = connectionOpt.get();
		
		try {
			pStmt = connection.prepareStatement("update movie set "
					+ "title=?,"
					+ "genre=?,"
					+ "durationByMinute=?,"
					+ "imdbRating=?,"
					+ "pg13=?,"
					+ "releasedDate=?,"
					+ "review=? where id=?;"
					);
			pStmt.setString(1,movie.getTitle());
			pStmt.setString(2,movie.getGenre().toString());
			pStmt.setInt(3, movie.getDurationByMinute());
			pStmt.setDouble(4,movie.getImdbRating());
			pStmt.setBoolean(5, movie.getPg13());
			pStmt.setDate(6,Date.valueOf(movie.getReleasedDate()));
			pStmt.setString(7,movie.getReview());
			pStmt.setLong(8, movie.getId());
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return rowEffected;
		
	}
	
	//Delete D
	public int deleteMovie(Long id) {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		int rowEffected = 0;
		if (connectionOpt.isEmpty()) {
			return rowEffected;
		}
		connection = connectionOpt.get();
		try {
			pStmt = connection.prepareStatement("delete from movie where id=?;");
			pStmt.setLong(1,id);
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return rowEffected;	
	}
	
	public Optional<Movie> getMovieById(Long id){
		Optional<Movie> movieOpt = Optional.empty();
		
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		if (connectionOpt.isEmpty()) {
			return movieOpt;
		}
		connection = connectionOpt.get();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from movie where id='"+id+"';");
			while (rs.next()) {
				movieOpt = Optional.of(new Movie(rs.getLong("id"), rs.getString("title"), Genre.valueOf(rs.getString("genre")),
						rs.getInt("durationByMinute"), rs.getDouble("imdbRating"), rs.getBoolean("pg13"),
						rs.getDate("releasedDate").toLocalDate(), rs.getString("review")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return movieOpt;
		
	}
	
	//Read R search
	public List<Movie> searchMovie(String columnName,String value){
		List<Movie> movies = new ArrayList<>();
		
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		if (connectionOpt.isEmpty()) {
			return movies;
		}
		connection = connectionOpt.get();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from movie where "+columnName+"='"+value+"';");
			while (rs.next()) {
				movies.add(new Movie(rs.getLong("id"), rs.getString("title"), Genre.valueOf(rs.getString("genre")),
						rs.getInt("durationByMinute"), rs.getDouble("imdbRating"), rs.getBoolean("pg13"),
						rs.getDate("releasedDate").toLocalDate(), rs.getString("review")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return movies;
		
	}

}
