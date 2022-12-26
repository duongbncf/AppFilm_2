package com.example.appfilm_2.click;

import android.widget.ImageView;

import com.example.appfilm_2.model.FilmModel;

public interface UpdateItemClickListener {
    void onUpdateClick(FilmModel filmModel, ImageView movieImageView, int position);
}
