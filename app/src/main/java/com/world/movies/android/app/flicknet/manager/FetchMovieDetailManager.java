package com.world.movies.android.app.flicknet.manager;

import android.os.AsyncTask;
import android.util.Log;

import com.world.movies.android.app.flicknet.business.FetchMoviesBusiness;
import com.world.movies.android.app.flicknet.model.MovieDetailView;

import org.json.JSONException;

import static com.world.movies.android.app.flicknet.infraestructure.ErrorCodeConstants.ERROR_FETCH_REMOTE_DATA;

/**
 * Created by root on 8/28/16.
 */
public class FetchMovieDetailManager extends AsyncTask<Integer, Void, MovieDetailView> {

    private static final String LOG_TAG = FetchMovieDetailManager.class.getSimpleName();

    private OnLoadMovieListener mListener;

    public void setOnLoadMovieListener(OnLoadMovieListener listener) {
        this.mListener = listener;
    }

    @Override
    protected MovieDetailView doInBackground(Integer... args) {
        // get movie id param
        int id = args[0];
        try {
            return FetchMoviesBusiness.newInstance().obtainMovieDetail(id);
        } catch (JSONException e) {
            Log.e(LOG_TAG, ERROR_FETCH_REMOTE_DATA, e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(MovieDetailView movieDetailView) {
        if (movieDetailView != null) {
            mListener.onLoadMovieDetail(movieDetailView);
        }
    }
}
