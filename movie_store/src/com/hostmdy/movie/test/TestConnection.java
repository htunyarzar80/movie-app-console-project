package com.hostmdy.movie.test;

import java.sql.Connection;
import java.util.Optional;

import com.hostmdy.movie.database.DatabaseConnection;

public class TestConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Optional<Connection> connectionOpt = DatabaseConnection
				.getConnection();
		
		if(connectionOpt.isPresent()) {
			System.out.println("Successfully Connected to Database");
		}else {
			System.out.println("Fail to connect to Database");
		}

	}

}
