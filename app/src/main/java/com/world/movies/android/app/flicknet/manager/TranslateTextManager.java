package com.world.movies.android.app.flicknet.manager;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.world.movies.android.app.flicknet.R;
import com.world.movies.android.app.flicknet.business.TranslateTextBusiness;

/**
 * Created by eltonjhony on 02/10/16.
 */

public class TranslateTextManager extends AsyncTask<String, String, String>  {

    private Context mContext;
    private ProgressDialog mProgressDialog;
    private OnLoadTranslateTextListener mListener;

    public void setListener(OnLoadTranslateTextListener listener) {
        this.mListener = listener;
    }

    public TranslateTextManager(Context context) {
        mContext = context;
        this.mProgressDialog = new ProgressDialog(mContext);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog.setTitle(mContext.getString(R.string.translate_loading));
        mProgressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        String sourceText = params[0];
        String lang = params[1];

        return TranslateTextBusiness.newInstance().translate(sourceText, lang);
    }

    @Override
    protected void onPostExecute(String translatedText) {
        super.onPostExecute(translatedText);
        mProgressDialog.dismiss();
        if (translatedText != null && !translatedText.isEmpty()) {
            this.mListener.onTranslateTextListener(translatedText);
        }
    }
}
