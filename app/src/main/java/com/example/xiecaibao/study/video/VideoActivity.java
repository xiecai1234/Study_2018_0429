package com.example.xiecaibao.study.video;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.xiecaibao.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {
    @BindView(R.id.videoView)
    VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        String localPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "xxxxx.mp4";
        //local video
        videoView.setVideoPath(localPath);
        //remote video
        videoView.setVideoURI(Uri.parse(""));
        //使用MediaController控制视频播放
        MediaController mediaController = new MediaController(this);
        //建立关联
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
    }
}
