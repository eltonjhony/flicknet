package com.world.movies.android.app.flicknet.infraestructure;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 9/5/16.
 */
public class APIFieldMapper {
    public static final int RESULTS_KEY = 0;
    public static final int ID_KEY = 1;
    public static final int TITLE_KEY = 2;
    public static final int POPULARITY_KEY = 3;
    public static final int POSTER_KEY = 4;
    public static final int BACKDROP_KEY = 5;
    public static final int REL_DATE_KEY = 6;
    public static final int OVERVIEW_KEY = 7;
    public static final int VOTE_COUNT_KEY = 8;
    public static final int VOTE_AV_KEY = 9;
    public static final int KEY = 10;
    public static final int NAME = 11;
    public static final int API_KEY = 12;
    public static final int PAGE = 13;
    public static final int VIDEOS = 14;
    public static final int TEXT = 15;
    public static final int LANG = 16;

    public static final Map<Integer, String> FIELD_LOCATOR = new HashMap<Integer, String>() {{
        put(RESULTS_KEY, "results");
        put(ID_KEY, "id");
        put(TITLE_KEY, "title");
        put(POPULARITY_KEY, "popularity");
        put(POSTER_KEY, "poster_path");
        put(BACKDROP_KEY, "backdrop_path");
        put(REL_DATE_KEY, "release_date");
        put(OVERVIEW_KEY, "overview");
        put(VOTE_COUNT_KEY, "vote_count");
        put(VOTE_AV_KEY, "vote_average");
        put(KEY, "key");
        put(NAME, "name");
        put(API_KEY, "api_key");
        put(PAGE, "page");
        put(VIDEOS, "videos");
        put(TEXT, "text");
        put(LANG, "lang");
    }};
}
