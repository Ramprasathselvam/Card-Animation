package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class ExoPlayerActivity extends AppCompatActivity {

    private SimpleExoPlayer player;
    private PlayerView playerView;

    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);

        playerView = findViewById(R.id.video_view);
    }

    private void initializePlayer() {

//    player = new SimpleExoPlayer.Builder(this).build();
        if (player == null) {
            DefaultTrackSelector trackSelector = new DefaultTrackSelector(this);
            trackSelector.setParameters(
                    trackSelector.buildUponParameters().setMaxVideoSizeSd());
            player = new SimpleExoPlayer.Builder(this)
                    .setTrackSelector(trackSelector)
                    .build();
        }

        playerView.setPlayer(player);

        Uri uri = Uri.parse(getString(R.string.media_url_mp4));   //mp4
//        Uri uri = Uri.parse(getString(R.string.media_url_dash));   //dash
        MediaSource mediaSource = buildMediaSource(uri);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, false, false);
    }

    private MediaSource buildMediaSource(Uri uri) {

        //Single Video

    DataSource.Factory dataSourceFactory =
            new DefaultDataSourceFactory(this, "exoplayer-codelab");
    return new ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri);

//    //stream
//    DataSource.Factory dataSourceFactory =
//            new DefaultDataSourceFactory(this, "exoplayer-codelab");
//    ProgressiveMediaSource.Factory mediaSourceFactory =
//            new ProgressiveMediaSource.Factory(dataSourceFactory);
//
//    // Create a media source using the supplied URI
//    MediaSource mediaSource1 = mediaSourceFactory.createMediaSource(uri);
//
//    // Additionally create a media source using an MP3
//    Uri audioUri = Uri.parse(getString(R.string.media_url_mp3));
//    MediaSource mediaSource2 = mediaSourceFactory.createMediaSource(audioUri);
//
//    return new ConcatenatingMediaSource(mediaSource1, mediaSource2);

        //Dash
//        DataSource.Factory dataSourceFactory =
//                new DefaultDataSourceFactory(this, "exoplayer-codelab");
//        DashMediaSource.Factory mediaSourceFactory = new DashMediaSource.Factory(dataSourceFactory);
//        return mediaSourceFactory.createMediaSource(uri);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

//    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }


}