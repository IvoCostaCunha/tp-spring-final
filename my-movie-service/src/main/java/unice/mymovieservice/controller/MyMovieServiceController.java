package unice.mymovieservice.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;

import unice.mymovieservice.model.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "MyMovieServiceController", description = "Movie static database API")
@RestController
public class MyMovieServiceController {
    private ArrayList<Actor> actors_db = new ArrayList<>();
    private ArrayList<Movie> movies_db = new ArrayList<>();

    public MyMovieServiceController() {
        Actor a1 = new Actor("Jean", "DuJardin", LocalDate.parse("1972-06-12"));
        Actor a2 = new Actor("Scarlett", "Johansson", LocalDate.parse("1984-11-22"));
        Actor a3 = new Actor("Robert", "John Downey Jr", LocalDate.parse("1965-04-04"));
        Actor a4 = new Actor("Brian", "Carnston", LocalDate.parse("1956-03-07"));
        Actor a5 = new Actor("Aaron", "Paul", LocalDate.parse("1979-10-27"));

        Movie m1 = new Movie("The Artist", "Michel Hazanavicius", a1, LocalDate.parse("2011-05-15"));
        a1.add_movie(m1);

        Movie m2 = new Movie("Lost in translation", "Sophia Copolla", a2, LocalDate.parse("2003-09-03"));
        a2.add_movie(m2);

        Movie m3 = new Movie("The Avengers", "Joss Whedon", a3, LocalDate.parse("2012-05-04"));
        a2.add_movie(m3);
        a3.add_movie(m3);

        Movie m4 = new Movie("Breaking Bad", "Vince Gilligan", a4, LocalDate.parse("2008-01-20"));
        a4.add_movie(m4);
        a5.add_movie(m4);

        Movie m5  = new Movie("Need for Speed", "Scott Waugh", a5, LocalDate.parse("2014-03-14"));
        a5.add_movie(m5);

        actors_db.add(a1);
        actors_db.add(a2);
        actors_db.add(a3);
        actors_db.add(a4);
        actors_db.add(a5);

        movies_db.add(m1);
        movies_db.add(m2);
        movies_db.add(m3);
        movies_db.add(m4);
        movies_db.add(m5);
    }

    @ApiOperation(value = "Get the list of actors.", response = ArrayList.class, tags = "getActors")
    @RequestMapping(value = "/actors", method = RequestMethod.GET)
    public ResponseEntity<?> getActors() {
        if(actors_db.size() > 0) {
            return new ResponseEntity<>(actors_db, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not found", HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Get an actor by name.", response = Actor.class, tags = "getActorByName")
    @RequestMapping(value = "/actor/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getActorByName(@PathVariable String name) {
        if(actors_db.size() > 0) {
            for(Actor a : actors_db) {
                if(a.first_name == name) {
                    return new ResponseEntity<>(a, HttpStatus.OK);
                }
                else if(a.second_name == name) {
                    return new ResponseEntity<>(a, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>("Not found", HttpStatus.OK);
    }

    @ApiOperation(value = "Get the list of actors in a movie.", response = ArrayList.class, tags = "getActorsByMovie")
    @RequestMapping(value = "/actors/movie/{movie}", method = RequestMethod.GET)
    public ResponseEntity<?> getActorByMovieDone(@PathVariable String movie) {
        ArrayList<Actor> as = new ArrayList<>();
        if(actors_db.size() > 0) {
            for(Actor a : actors_db) {
                for(Movie m : a.filmography) {
                    if(m.title == movie) {
                        as.add(a);
                    }
                }
            }
            if(as.size() > 0) {
                return new ResponseEntity<>(as, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not found", HttpStatus.OK);
    }

    @ApiOperation(value = "Get a movie by title.", response = Movie.class, tags = "getMovieByTitle")
    @RequestMapping(value = "/movie/title/{title}", method = RequestMethod.GET)
    public ResponseEntity<?> getMovieByTitle(@PathVariable String title) {
        if(movies_db.size() > 0) {
            for(Movie m : movies_db) {
                if(m.title == title) {
                    return new ResponseEntity<>(m, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>("Not found", HttpStatus.OK);
    }

    @ApiOperation(value = "Get the list of movies.", response = ArrayList.class, tags = "getMovies")
    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public ResponseEntity<?> getMovies() {
        if(movies_db.size() > 0) {
            return new ResponseEntity<>(movies_db, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not found", HttpStatus.OK);
        }
    }

    // There is only 1 movie possible per year in this API ..
    @ApiOperation(value = "Get a movie by year.", response = ArrayList.class, tags = "getMovieByYear")
    @RequestMapping(value = "/movie/year/{year}", method = RequestMethod.GET)
    public ResponseEntity<?> getMovieByYear(@PathVariable String year) {
        if(movies_db.size() > 0) {
            for(Movie m : movies_db) {
                if(m.release_date == LocalDate.parse(year)) {
                    return new ResponseEntity<>(m, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>("Not found", HttpStatus.OK);  
    }
}