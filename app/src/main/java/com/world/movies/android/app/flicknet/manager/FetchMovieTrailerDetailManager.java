package com.world.movies.android.app.flicknet.manager;

import android.os.AsyncTask;
import android.util.Log;

import com.world.movies.android.app.flicknet.business.FetchTrailerBusiness;
import com.world.movies.android.app.flicknet.model.TrailerItem;

import java.util.List;

import static com.world.movies.android.app.flicknet.infraestructure.ErrorCodeConstants.ERROR_FETCH_REMOTE_DATA;

/**
 * Created by root on 8/28/16.
 */
public class FetchMovieTrailerDetailManager extends AsyncTask<String, String, List<TrailerItem>> {

    private static final String LOG_TAG = FetchMovieTrailerDetailManager.class.getSimpleName();

    private OnLoadMovieListener mListener;

    public void setOnLoadMovieListener(OnLoadMovieListener listener) {
        this.mListener = listener;
    }

    @Override
    protected List<TrailerItem> doInBackground(String... args) {
        try {
            String movieId = args[0];
            return FetchTrailerBusiness.newInstance().obtainTrailersByMovieId(movieId);
        } catch (Exception e) {
            Log.e(LOG_TAG, ERROR_FETCH_REMOTE_DATA, e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<TrailerItem> trailerItems) {
        if (trailerItems != null) {
            mListener.onLoadMovieTrailerDetail(trailerItems);
        }
    }
}
