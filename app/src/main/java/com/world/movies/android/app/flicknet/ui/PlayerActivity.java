package com.world.movies.android.app.flicknet.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.world.movies.android.app.flicknet.BuildConfig;
import com.world.movies.android.app.flicknet.R;

/**
 * Created by eltonjhony on 21/09/16.
 */

public class PlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView mPlayerView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_player);

        mPlayerView = (YouTubePlayerView) findViewById(R.id.player_view);
        mPlayerView.initialize(BuildConfig.YOUTUBE_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean restored) {
        if (!restored) {
            player.cueVideo(getIntent().getStringExtra(getString(R.string.VIDEO_TAG)));
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show();
    }
}
