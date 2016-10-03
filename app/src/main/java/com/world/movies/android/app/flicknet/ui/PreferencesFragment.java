package com.world.movies.android.app.flicknet.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import com.world.movies.android.app.flicknet.R;

/**
 * Created by root on 8/28/16.
 */
public class PreferencesFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.advance_preferences);
    }
}
