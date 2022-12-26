package com.example.appfilm_2.click;

import android.widget.ImageView;

import com.example.appfilm_2.model.FilmModel;
import com.example.appfilm_2.model.Trailer;

public interface TrailerItemClickListener {
    void onTrailerClick(Trailer trailer, ImageView movieImageView);
}
