package com.world.movies.android.app.flicknet.manager;

import com.world.movies.android.app.flicknet.model.MovieDetailView;
import com.world.movies.android.app.flicknet.model.MovieMainInfo;
import com.world.movies.android.app.flicknet.model.TrailerItem;

import java.util.List;

/**
 * Created by root on 9/5/16.
 */
public interface OnLoadMovieListener {
    void onLoadMovieDetail(MovieDetailView movieDetailView);
    void onLoadMovieTrailerDetail(List<TrailerItem> trailerItems);
}
