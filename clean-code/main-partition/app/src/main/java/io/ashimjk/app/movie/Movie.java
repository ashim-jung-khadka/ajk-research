package io.ashimjk.app.movie;

public abstract class Movie {

    private String title;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract double determineAmount(int daysRented);

    public abstract int determineFrequentRenterPoints(int daysRented);

}