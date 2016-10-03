package com.world.movies.android.app.flicknet.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;

import com.world.movies.android.app.flicknet.R;
import com.world.movies.android.app.flicknet.manager.FetchMoviesManager;
import com.world.movies.android.app.flicknet.manager.OnLoadMoviesMainPosterListener;
import com.world.movies.android.app.flicknet.model.MovieMainInfo;
import com.world.movies.android.app.flicknet.ui.adapter.CustomGridMovieViewAdapter;

import java.util.List;

/**
 * Created by root on 8/27/16.
 */
public class MoviesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        OnLoadMoviesMainPosterListener {

    private static final String MOVIE_CAT_TAG =
            "com.world.movies.android.app.flicknet.MOVIE.CAT.TAG";

    private CustomGridMovieViewAdapter mGridMovieViewAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private int mOffset = 0;

    public static MoviesFragment newInstance(String movieCategory) {
        MoviesFragment f = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString(MOVIE_CAT_TAG, movieCategory);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGridMovieViewAdapter = new CustomGridMovieViewAdapter(getActivity(),
                R.layout.list_item_movies);
    }

    @Override
    public void onLoadMoviesMainPoster(List<MovieMainInfo> movieMainInfoList) {
        mGridMovieViewAdapter.addNewItems(movieMainInfoList);
    }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        updateMovieData();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GridView gridViewMovies = (GridView) rootView.findViewById(R.id.grid_view_movies);

        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_orange_dark));

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            updateMovieData();
            mSwipeRefreshLayout.setRefreshing(false);
        });

        gridViewMovies.setAdapter(mGridMovieViewAdapter);

        gridViewMovies.setOnItemClickListener((adapter, rootView1, position, l) -> {
            final MovieMainInfo movieDetailViewClicked = (MovieMainInfo) adapter.getItemAtPosition(position);

            Intent intentToDetail = new Intent(getActivity(), DetailActivity.class);
            intentToDetail.putExtra(Intent.EXTRA_TEXT, movieDetailViewClicked);
            getContext().startActivity(intentToDetail);
        });

        gridViewMovies.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int firstVisibleItem, visibleItemCount,totalItemCount;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                final int lastItem = firstVisibleItem + visibleItemCount;
                if (lastItem == totalItemCount && scrollState == SCROLL_STATE_IDLE) {
                    onRefresh();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                this.firstVisibleItem = firstVisibleItem;
                this.visibleItemCount = visibleItemCount;
                this.totalItemCount = totalItemCount;
            }
        });

        return rootView;
    }

    private void updateMovieData() {
        this.mOffset = this.mOffset + 1;
        String movieCategory = getArguments().getString(MOVIE_CAT_TAG);
        FetchMoviesManager fetchMoviesManager = new FetchMoviesManager();
        fetchMoviesManager.setOnLoadMoviesMainPosterListener(this);
        fetchMoviesManager.execute(movieCategory, String.valueOf(mOffset));
    }

}
