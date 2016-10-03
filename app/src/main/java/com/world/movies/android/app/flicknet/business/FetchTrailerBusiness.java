package com.world.movies.android.app.flicknet.business;

import android.net.Uri;

import com.world.movies.android.app.flicknet.BuildConfig;
import com.world.movies.android.app.flicknet.infraestructure.ApiDataParser;
import com.world.movies.android.app.flicknet.model.TrailerItem;
import com.world.movies.android.app.flicknet.network.RemoteHttpCallNetwork;

import java.util.List;

import static com.world.movies.android.app.flicknet.infraestructure.APIFieldMapper.*;

/**
 * Created by eltonjhony on 25/09/16.
 */

public class FetchTrailerBusiness {

    private static FetchTrailerBusiness sInstance;

    public static synchronized FetchTrailerBusiness newInstance() {
        if (sInstance == null) {
            sInstance = new FetchTrailerBusiness();
        }
        return sInstance;
    }

    public List<TrailerItem> obtainTrailersByMovieId(String movieId) {
        Uri uriBuilt = Uri.parse(BuildConfig.MOVIES_API_URL_BASE)
                .buildUpon()
                .appendPath(movieId)
                .appendPath(FIELD_LOCATOR.get(VIDEOS))
                .appendQueryParameter(FIELD_LOCATOR.get(API_KEY), BuildConfig.APPID)
                .build();

        String jsonRawData = RemoteHttpCallNetwork
                .fetchRawData(uriBuilt.toString());

        return ApiDataParser.getTrailerItems(jsonRawData);
    }
}
