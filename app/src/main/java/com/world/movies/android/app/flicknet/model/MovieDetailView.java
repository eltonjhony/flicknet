package com.world.movies.android.app.flicknet.model;

import java.io.Serializable;

/**
 * Created by root on 9/5/16.
 */
public class MovieDetailView implements Serializable {

    private static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/";
    private static final String IMG_SIZE_780 = "w780";

    private String originalTitle;
    private String releaseDate;
    private String popularity;
    private String voteCount;
    private String voteAverage;
    private String overview;
    private String backdropPath;

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPopularityConverted() {
        return String.format("%.1f", Float.parseFloat(getPopularity()));
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public Float getVoteAverageAsFloat() {
        return (Float.parseFloat(getVoteAverage()) / 2);
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void buildBackdropPath() {
        StringBuilder builder = new StringBuilder(BASE_POSTER_URL);
        if (backdropPath != null) {
            builder.append(IMG_SIZE_780).append("/").append(backdropPath);
            setBackdropPath(builder.toString());
        }
    }
}
