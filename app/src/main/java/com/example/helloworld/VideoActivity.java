package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PixelFormat;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    MediaPlayer mediaPlayer;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean pausing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Button buttonPlayVideo = (Button)findViewById(R.id.playvideoplayer);
        Button buttonPauseVideo = (Button)findViewById(R.id.pausevideoplayer);
        Button buttonStopeVideo = (Button)findViewById(R.id.stopevideoplayer);
        Button buttonResumeVideo = (Button)findViewById(R.id.playvideoplayer);

        getWindow().setFormat(PixelFormat.UNKNOWN);


        buttonPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VideoView mVideoView = (VideoView)findViewById(R.id.videoview);

//                String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.samplevideo;
                String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.fileaudio;
                Uri uri = Uri.parse(uriPath);
                mVideoView.setVideoURI(uri);
                mVideoView.requestFocus();
                mVideoView.start();
            }
        });

        buttonPauseVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VideoView mVideoView = (VideoView)findViewById(R.id.videoview);
                mVideoView.pause();
                mVideoView.resume();
            }
        });


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}