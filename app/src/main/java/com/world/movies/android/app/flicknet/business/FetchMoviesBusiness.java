package com.world.movies.android.app.flicknet.business;

import android.net.Uri;

import com.world.movies.android.app.flicknet.BuildConfig;
import com.world.movies.android.app.flicknet.infraestructure.ApiDataParser;
import com.world.movies.android.app.flicknet.model.MovieDetailView;
import com.world.movies.android.app.flicknet.model.MovieMainInfo;
import com.world.movies.android.app.flicknet.network.RemoteHttpCallNetwork;

import org.json.JSONException;

import java.util.List;

import static com.world.movies.android.app.flicknet.infraestructure.APIFieldMapper.API_KEY;
import static com.world.movies.android.app.flicknet.infraestructure.APIFieldMapper.FIELD_LOCATOR;
import static com.world.movies.android.app.flicknet.infraestructure.APIFieldMapper.PAGE;

/**
 * Created by root on 8/28/16.
 */
public class FetchMoviesBusiness {

    private static FetchMoviesBusiness sInstance;

    public static synchronized FetchMoviesBusiness newInstance() {
        if (sInstance == null) {
            sInstance = new FetchMoviesBusiness();
        }
        return sInstance;
    }

    public List<MovieMainInfo> retrieveMoviesOrderedBy(String order, String offset) throws JSONException {

        Uri builtUri = Uri.parse(BuildConfig.MOVIES_API_URL_BASE)
                .buildUpon()
                .appendPath(order)
                .appendQueryParameter(FIELD_LOCATOR.get(PAGE), offset)
                .appendQueryParameter(FIELD_LOCATOR.get(API_KEY), BuildConfig.APPID)
                .build();

        String movieDataRaw = RemoteHttpCallNetwork.fetchRawData(builtUri.toString());

        List<MovieMainInfo> movieMainInfoList = ApiDataParser
                .getPosterMainViewList(movieDataRaw);
        
        return movieMainInfoList;
    }

    public MovieDetailView obtainMovieDetail(int id) throws JSONException {

        Uri builtUri = Uri.parse(BuildConfig.MOVIES_API_URL_BASE)
                .buildUpon()
                .appendPath(String.valueOf(id))
                .appendQueryParameter(FIELD_LOCATOR.get(API_KEY), BuildConfig.APPID)
                .build();

        String movieDataRaw = RemoteHttpCallNetwork.fetchRawData(builtUri.toString());

        MovieDetailView movieDetailView = ApiDataParser
                .recoverMovieDetailConverted(movieDataRaw);

        return movieDetailView;
    }
}
