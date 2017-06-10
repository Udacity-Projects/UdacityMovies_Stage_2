package com.example.vamshi.udacitymoviesstage1;

/**
 * Created by Vamshi on 6/10/2017.
 */

public class FavMovies {

    public int MovieID;
    private String MovieName;

    public FavMovies(int movieID, String movieName) {
        MovieID = movieID;
        MovieName = movieName;
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int movieID) {
        MovieID = movieID;
    }

    public String getMovieName() {
        return MovieName;
    }

    public void setMovieName(String movieName) {
        MovieName = movieName;
    }
}
