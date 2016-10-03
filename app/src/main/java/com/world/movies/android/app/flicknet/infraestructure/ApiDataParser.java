package com.world.movies.android.app.flicknet.infraestructure;

import android.util.Log;

import static com.world.movies.android.app.flicknet.infraestructure.APIFieldMapper.*;
import static com.world.movies.android.app.flicknet.infraestructure.ErrorCodeConstants.ERROR_PARSE_DATA;

import com.world.movies.android.app.flicknet.model.MovieDetailView;
import com.world.movies.android.app.flicknet.model.MovieMainInfo;
import com.world.movies.android.app.flicknet.model.TrailerItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 8/28/16.
 */
public class ApiDataParser {

    private static final String TAG = ApiDataParser.class.getSimpleName();

    private ApiDataParser() {
    }

    public static List<MovieMainInfo> getPosterMainViewList(String moviesJsonRaw)
            throws JSONException {

        JSONArray results;
        try {
            JSONObject moviesData = new JSONObject(moviesJsonRaw);
            results = moviesData.getJSONArray(FIELD_LOCATOR.get(RESULTS_KEY));
        } catch (Exception ex) {
            Log.e(TAG, ERROR_PARSE_DATA, ex);
            return null;
        }

        List<MovieMainInfo> movieMainInfoList = new ArrayList<>();
        for (int i =0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            MovieMainInfo mainInfo = new MovieMainInfo(
                    result.getInt(FIELD_LOCATOR.get(ID_KEY)),
                    result.getString(FIELD_LOCATOR.get(POSTER_KEY)));
            mainInfo.buildPosterPathUrl();
            movieMainInfoList.add(mainInfo);
        }

        return movieMainInfoList;
    }

    public static MovieDetailView recoverMovieDetailConverted(String movieDataRaw)
            throws JSONException {

        JSONObject movieDetail;
        try {
            movieDetail = new JSONObject(movieDataRaw);
        } catch (Exception ex) {
            Log.e(TAG, ERROR_PARSE_DATA, ex);
            return null;
        }

        MovieDetailView movieDetailView = new MovieDetailView();
        movieDetailView.setOriginalTitle(movieDetail.getString(FIELD_LOCATOR.get(TITLE_KEY)));
        movieDetailView.setBackdropPath(movieDetail.getString(FIELD_LOCATOR.get(BACKDROP_KEY)));
        movieDetailView.setOverview(movieDetail.getString(FIELD_LOCATOR.get(OVERVIEW_KEY)));
        movieDetailView.setPopularity(movieDetail.getString(FIELD_LOCATOR.get(POPULARITY_KEY)));
        movieDetailView.setReleaseDate(movieDetail.getString(FIELD_LOCATOR.get(REL_DATE_KEY)));
        movieDetailView.setVoteCount(movieDetail.getString(FIELD_LOCATOR.get(VOTE_COUNT_KEY)));
        movieDetailView.setVoteAverage(movieDetail.getString(FIELD_LOCATOR.get(VOTE_AV_KEY)));
        movieDetailView.buildBackdropPath();

        return movieDetailView;
    }

    public static List<TrailerItem> getTrailerItems(String jsonRawData) {
        List<TrailerItem> trailerItems = new ArrayList<>();
        try {
            JSONObject result = new JSONObject(jsonRawData);
            JSONArray trailers = result.getJSONArray(FIELD_LOCATOR.get(RESULTS_KEY));
            for (int i = 0; i < trailers.length(); i++) {
                JSONObject jsonObject = trailers.getJSONObject(i);
                TrailerItem trailerItem = new TrailerItem();
                trailerItem.setName(jsonObject.getString(FIELD_LOCATOR.get(NAME)));
                trailerItem.setKey(jsonObject.getString(FIELD_LOCATOR.get(KEY)));
                trailerItem.buildThumbnail();
                trailerItems.add(trailerItem);
            }
        } catch (Exception ex) {
            Log.e(TAG, ERROR_PARSE_DATA, ex);
            return null;
        }
        return trailerItems;
    }

    public static String getTranslatedText(String rawData) {
        try {
            JSONObject jsonObject = new JSONObject(rawData);
            String translatedText = jsonObject.getString(FIELD_LOCATOR.get(TEXT));
            if (translatedText != null && !translatedText.isEmpty()) {
                return translatedText.replace("\\", "")
                        .replace("[", "")
                        .replace("]", "")
                        .replace("\"", "");
            }
        } catch (Exception ex) {
            Log.e(TAG, ERROR_PARSE_DATA, ex);
        }
        return null;
    }
}
