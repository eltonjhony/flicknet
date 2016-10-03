package com.world.movies.android.app.flicknet.manager;

import com.world.movies.android.app.flicknet.model.MovieMainInfo;

import java.util.List;

/**
 * Created by root on 9/5/16.
 */
public interface OnLoadMoviesMainPosterListener {
    void onLoadMoviesMainPoster(List<MovieMainInfo> movieMainInfoList);
}
