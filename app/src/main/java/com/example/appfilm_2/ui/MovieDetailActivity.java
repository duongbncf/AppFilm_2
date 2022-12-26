package com.example.appfilm_2.ui;

import static com.example.appfilm_2.R.drawable.ic_baseline_fullscreen_24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.appfilm_2.R;
import com.example.appfilm_2.adapter.CastAdapter;
import com.example.appfilm_2.model.Author;
import com.example.appfilm_2.model.CastModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView imageTrailer;
    private ImageView detail_movie_cover;
    private TextView titleTrailer;
    private TextView tvCmt;

    private FloatingActionButton floatingPlay;
    DatabaseReference myRef;
    RecyclerView rcv;
    RecyclerView rcvCast;
    CastAdapter castAdapter;
    ArrayList<CastModel> castModelList;
    ImageView imgCast;
    TextView tvCast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        tvCmt = findViewById(R.id.tvCmt);
        imageTrailer = findViewById(R.id.imageTrailer);
        titleTrailer = findViewById(R.id.trailer_title);
        detail_movie_cover = findViewById(R.id.detail_movie_cover);
        floatingPlay = findViewById(R.id.floatingPlay);
        imgCast = findViewById(R.id.imgCast);
        tvCast = findViewById(R.id.tvCast);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String movieTitle = bundle.getString("title");
        String movieUrl = bundle.getString("url");
        String movieVideo = bundle.getString("video");
        String movieCover = bundle.getString("cover");
        String movieDepcription = bundle.getString("depcription");
        int movieCast = bundle.getInt("author");

        getSupportActionBar().setTitle(movieTitle);
        titleTrailer.setText(movieTitle);
        Glide.with(imageTrailer).load(movieUrl)
                .into((imageTrailer));
        Glide.with(detail_movie_cover).load(movieCover)
                .into((detail_movie_cover));
        tvCmt.setText(movieDepcription);
        detail_movie_cover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        floatingPlay.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        floatingPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, FilmDetailActivity.class);
                intent.putExtra("video", movieVideo);
                startActivity(intent);
            }
        });

        getCast(movieCast);

    }

    void getCast(int idCast) {
        myRef = FirebaseDatabase.getInstance().getReference("Author");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Author author = data.getValue(Author.class);
                  if(idCast == author.getIdAuthor()){
                      Glide.with(getApplicationContext()).load(author.getAvatar()).into(imgCast);
                      tvCast.setText(author.getName());
                  }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}