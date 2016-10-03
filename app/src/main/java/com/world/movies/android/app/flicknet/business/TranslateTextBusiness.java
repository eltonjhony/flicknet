package com.world.movies.android.app.flicknet.business;

import android.net.Uri;

import com.world.movies.android.app.flicknet.BuildConfig;
import com.world.movies.android.app.flicknet.infraestructure.ApiDataParser;
import com.world.movies.android.app.flicknet.network.RemoteHttpCallNetwork;

import static com.world.movies.android.app.flicknet.infraestructure.APIFieldMapper.FIELD_LOCATOR;
import static com.world.movies.android.app.flicknet.infraestructure.APIFieldMapper.KEY;
import static com.world.movies.android.app.flicknet.infraestructure.APIFieldMapper.LANG;
import static com.world.movies.android.app.flicknet.infraestructure.APIFieldMapper.TEXT;

/**
 * Created by eltonjhony on 02/10/16.
 */

public class TranslateTextBusiness {

    private static TranslateTextBusiness sInstance = null;

    public static synchronized TranslateTextBusiness newInstance() {
        if (sInstance == null) {
            sInstance = new TranslateTextBusiness();
        }
        return sInstance;
    }

    public String translate(String sourceText, String lang) {
        Uri uri = Uri.parse(BuildConfig.TRANSLATE_API_URL_BASE)
                .buildUpon()
                .appendQueryParameter(FIELD_LOCATOR.get(KEY), BuildConfig.TRANSLATE_APPID)
                .appendQueryParameter(FIELD_LOCATOR.get(TEXT), sourceText)
                .appendQueryParameter(FIELD_LOCATOR.get(LANG), lang)
                .build();

        String rawData = RemoteHttpCallNetwork.fetchRawData(uri.toString());
        return ApiDataParser.getTranslatedText(rawData);
    }

}
