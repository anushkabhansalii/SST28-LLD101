package com.bookmyshow.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Theatre in a City.
 * A Theatre contains multiple Shows.
 */
public class Theatre {

    private final String theatreId;
    private final String name;
    private final City city;
    private final List<Show> shows;

    public Theatre(String theatreId, String name, City city) {
        this.theatreId = theatreId;
        this.name = name;
        this.city = city;
        this.shows = new ArrayList<>();
    }

    public String getTheatreId()    { return theatreId; }
    public String getName()         { return name; }
    public City getCity()           { return city; }
    public List<Show> getShows()    { return shows; }

    public void addShow(Show show)      { shows.add(show); }
    public void removeShow(Show show)   { shows.remove(show); }

    @Override
    public String toString() {
        return "Theatre{id='" + theatreId + "', name='" + name + "', city='" + city.getName() + "'}";
    }
}