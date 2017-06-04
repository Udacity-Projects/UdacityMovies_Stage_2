package com.example.vamshi.udacitymoviesstage1;

/**
 * Created by Vamshi on 5/31/2017.
 */

public class MovieObject {

    private String movieTitle;
    private String movieURL;
    private String movieSynopsis;
    private String movieRating;
    private String movieReleaseDate;
    private String movieOriginalTitle;

    public MovieObject(String movieTitle, String movieURL, String movieSynopsis, String movieRating, String movieReleaseDate, String movieOriginalTitle) {
        this.movieTitle = movieTitle;
        this.movieURL = movieURL;
        this.movieSynopsis = movieSynopsis;
        this.movieRating = movieRating;
        this.movieReleaseDate = movieReleaseDate;
        this.movieOriginalTitle = movieOriginalTitle;
    }


    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieURL() {
        return movieURL;
    }

    public void setMovieURL(String movieURL) {
        this.movieURL = movieURL;
    }

    public String getMovieSynopsis() {
        return movieSynopsis;
    }

    public void setMovieSynopsis(String movieSynopsis) {
        this.movieSynopsis = movieSynopsis;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieOriginalTitle() {
        return movieOriginalTitle;
    }

    public void setMovieOriginalTitle(String movieOriginalTitle) {
        this.movieOriginalTitle = movieOriginalTitle;
    }
}
