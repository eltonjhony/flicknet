package com.world.movies.android.app.flicknet.model;

import java.io.Serializable;

/**
 * Created by root on 8/28/16.
 */
public class MovieMainInfo implements Serializable {

    private static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/";
    private static final String IMG_SIZE_185 = "w185";

    private int id;
    private String posterPath;

    public MovieMainInfo(int id, String posterPath) {
        this.id = id;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void buildPosterPathUrl() {
        StringBuilder builder = new StringBuilder(BASE_POSTER_URL);
        if (posterPath != null) {
            builder.append(IMG_SIZE_185).append("/").append(posterPath);
            setPosterPath(builder.toString());
        }
    }


}
