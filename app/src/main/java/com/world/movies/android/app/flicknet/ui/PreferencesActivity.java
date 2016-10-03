package com.world.movies.android.app.flicknet.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.world.movies.android.app.flicknet.R;

/**
 * Created by root on 8/28/16.
 */
public class PreferencesActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container_preferences, new PreferencesFragment())
                    .commit();
        }
    }
}
