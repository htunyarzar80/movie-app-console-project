package com.hostmdy.movie;

import java.util.Scanner;
import com.hostmdy.movie.view.MovieView;

public class Main {

	public static void main(String[] args) {
		MovieView movieView = new MovieView();
		System.out.println("### Movie Store App ###");
		Scanner userInput = new Scanner(System.in);
		
		while (true) {
			System.out.println("1.View All 2.Search 3.Create New 4.Update 5.Delete");
			int operations = userInput.nextInt();
			switch (operations) {
			case 1 -> movieView.showAllMovie();
			case 2 -> movieView.showSearchedMovie(userInput);
			case 3 -> movieView.showCreateForm(userInput);
			case 4 -> movieView.showUpdateForm(userInput);
			case 5 -> movieView.showDeleteForm(userInput);
			default -> System.out.println("Invalid Operations");
			}
			
			System.out.println("continue?yes-y or no-n");
			char decision = userInput.next().charAt(0);
			if(decision == 'n') break;
		}
		userInput.close();
	}

}
