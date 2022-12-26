package com.example.appfilm_2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.appfilm_2.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;

public class FilmDetailActivity extends AppCompatActivity {

    PlayerView playerView;
    ExoPlayer player;
    MediaItem mediaItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        player = new ExoPlayer.Builder(FilmDetailActivity.this).build();
        setContentView(R.layout.activity_film_detail);
        playerView  = findViewById(R.id.playerView);
        String movieUrl = getIntent().getExtras().getString("video");
        mediaItem = MediaItem.fromUri(movieUrl);
        intinplayer();

    }

    private void intinplayer() {
        playerView.setPlayer(player);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    @Override
    protected void onStart() {
        super.onStart();
        intinplayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(player == null){
            intinplayer();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(player.isPlaying()){
            player.stop();
        }
    }
}