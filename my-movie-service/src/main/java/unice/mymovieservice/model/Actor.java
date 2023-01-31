package unice.mymovieservice.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Actor {
    public String first_name, second_name;
    public LocalDate born_date;
    public ArrayList<Movie> filmography = new ArrayList<>();

    public Actor(String first_name, String second_name, LocalDate born_date) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.born_date = born_date;
    }

    // Alternative constructor
    public Actor(String first_name, String second_name, LocalDate born_date, ArrayList<Movie> filmography) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.born_date = born_date;
        this.filmography = filmography;
    }

    public void add_movie(Movie movie) {
        this.filmography.add(movie);
    }
    
}
