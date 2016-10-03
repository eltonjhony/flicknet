package com.world.movies.android.app.flicknet.manager;

import android.os.AsyncTask;
import android.util.Log;
import com.world.movies.android.app.flicknet.business.FetchMoviesBusiness;
import com.world.movies.android.app.flicknet.model.MovieMainInfo;

import org.json.JSONException;
import java.util.List;

import static com.world.movies.android.app.flicknet.infraestructure.ErrorCodeConstants.ERROR_FETCH_REMOTE_DATA;

/**
 * Created by root on 8/28/16.
 */
public class FetchMoviesManager extends AsyncTask<String, Void, List<MovieMainInfo>> {

    private static final String LOG_TAG = FetchMoviesManager.class.getSimpleName();

    private OnLoadMoviesMainPosterListener mListener;

    public void setOnLoadMoviesMainPosterListener(OnLoadMoviesMainPosterListener listener) {
        this.mListener = listener;
    }

    @Override
    protected List<MovieMainInfo> doInBackground(String... args) {
        // get order param
        String order = args[0];

        //get page param
        String offset = args[1];

        try {
            return FetchMoviesBusiness.newInstance().retrieveMoviesOrderedBy(order, offset);
        } catch (JSONException e) {
            Log.e(LOG_TAG, ERROR_FETCH_REMOTE_DATA, e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<MovieMainInfo> movieMainInfoList) {
        if (movieMainInfoList != null && !movieMainInfoList.isEmpty()) {
            mListener.onLoadMoviesMainPoster(movieMainInfoList);
        }
    }
}
