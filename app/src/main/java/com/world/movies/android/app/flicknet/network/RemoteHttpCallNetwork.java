package com.world.movies.android.app.flicknet.network;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.world.movies.android.app.flicknet.infraestructure.ErrorCodeConstants.ERROR_CLOSING_STREAMS;
import static com.world.movies.android.app.flicknet.infraestructure.ErrorCodeConstants.ERROR_GENERIC;

/**
 * Created by root on 8/28/16.
 */
public class RemoteHttpCallNetwork {

    private static final String LOG_TAG = RemoteHttpCallNetwork.class.getSimpleName();
    private static final String HTTP_METHOD_GET = "GET";

    private RemoteHttpCallNetwork() {
    }

    public static String fetchRawData(String apiUrl) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            urlConnection = getHttpURLConnection(apiUrl, HTTP_METHOD_GET);
            InputStream inputStream = urlConnection.getInputStream();

            if (inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = readContent(reader);
            if (buffer.length() == 0) {
                return null;
            }

            return  buffer.toString();
        } catch (Exception ex) {
            Log.e(LOG_TAG, ERROR_GENERIC, ex);
            return null;
        } finally {
            closeConnection(urlConnection, reader);
        }
    }

    @NonNull
    private static StringBuffer readContent(BufferedReader reader) throws IOException {
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line + "\n");
        }
        return buffer;
    }

    @NonNull
    private static HttpURLConnection getHttpURLConnection(String apiUrl, String httpMethod)
            throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(httpMethod);
        urlConnection.connect();
        return urlConnection;
    }

    private static void closeConnection(HttpURLConnection urlConnection, BufferedReader reader) {
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
        if (reader != null) {
            try {
                reader.close();
            } catch (final IOException e) {
                Log.e(LOG_TAG, ERROR_CLOSING_STREAMS, e);
            }
        }
    }
}
