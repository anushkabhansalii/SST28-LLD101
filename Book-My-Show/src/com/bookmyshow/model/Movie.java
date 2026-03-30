package com.bookmyshow.model;

/**
 * Represents a movie entity.
 */
public class Movie {

    private final String movieId;
    private final String title;
    private final String language;
    private final int durationMinutes;

    public Movie(String movieId, String title, String language, int durationMinutes) {
        this.movieId = movieId;
        this.title = title;
        this.language = language;
        this.durationMinutes = durationMinutes;
    }

    public String getMovieId()        { return movieId; }
    public String getTitle()          { return title; }
    public String getLanguage()       { return language; }
    public int getDurationMinutes()   { return durationMinutes; }

    @Override
    public String toString() {
        return "Movie{id='" + movieId + "', title='" + title + "', lang='" + language + "', duration=" + durationMinutes + "min}";
    }
}