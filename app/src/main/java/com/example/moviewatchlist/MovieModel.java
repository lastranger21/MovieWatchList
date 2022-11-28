package com.example.moviewatchlist;

public class MovieModel {
    String judul;
    Double Rating;
    Boolean isFavorite;
    String pathBanner;

    public MovieModel(String judul, Double rating, Boolean isFavorite, String pathBanner) {
        this.judul = judul;
        this.Rating = rating;
        this.isFavorite = isFavorite;
        this.pathBanner = pathBanner;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public Double getRating() {
        return Rating;
    }

    public void setRating(Double rating) {
        Rating = rating;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getPathBanner() {
        return pathBanner;
    }

    public void setPathBanner(String pathBanner) {
        this.pathBanner = pathBanner;
    }
}
