package com.world.movies.android.app.flicknet.ui;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.world.movies.android.app.flicknet.R;
import com.world.movies.android.app.flicknet.manager.FetchMovieTrailerDetailManager;
import com.world.movies.android.app.flicknet.manager.OnLoadMovieListener;
import com.world.movies.android.app.flicknet.model.MovieDetailView;
import com.world.movies.android.app.flicknet.model.TrailerItem;
import com.world.movies.android.app.flicknet.ui.adapter.CustomGridTrailerViewAdapter;

import java.util.List;

/**
 * Created by root on 9/18/16.
 */
public class TrailerFragment extends Fragment implements OnLoadMovieListener {

    private static final String MOVIE_ID_TAG =
            "com.world.movies.android.app.flicknet.ui.MOVIE.ID.TAG";

    private CustomGridTrailerViewAdapter mTrailerAdapter;

    public static TrailerFragment newInstance(int id) {
        TrailerFragment f = new TrailerFragment();
        Bundle args = new Bundle();
        args.putString(MOVIE_ID_TAG, String.valueOf(id));
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FetchMovieTrailerDetailManager movieTrailerDetailManager =
                new FetchMovieTrailerDetailManager();
        movieTrailerDetailManager.setOnLoadMovieListener(this);
        movieTrailerDetailManager.execute(getArguments().getString(MOVIE_ID_TAG));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trailer, container, false);

        ListView listViewTrailers = (ListView) rootView.findViewById(R.id.grid_view_trailers);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listViewTrailers.setNestedScrollingEnabled(true);
        }
        mTrailerAdapter = new CustomGridTrailerViewAdapter(getActivity(), R.layout.list_item_trailers);
        listViewTrailers.setAdapter(mTrailerAdapter);

        return rootView;
    }

    @Override
    public void onLoadMovieDetail(MovieDetailView movieDetailView) {}

    @Override
    public void onLoadMovieTrailerDetail(List<TrailerItem> trailerItems) {
        mTrailerAdapter.addAll(trailerItems);
    }
}
