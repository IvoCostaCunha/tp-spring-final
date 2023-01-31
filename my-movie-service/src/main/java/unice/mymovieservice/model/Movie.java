package unice.mymovieservice.model;

import java.time.LocalDate;

public class Movie {
    public String title, director;
    public LocalDate release_date;
    public Actor principal_actor;
    
    public Movie(String title, String director, Actor principal_actor, LocalDate release_date) {
        this.title = title;
        this.director = director;
        this.principal_actor = principal_actor;
        this.release_date = release_date;
    }
}
