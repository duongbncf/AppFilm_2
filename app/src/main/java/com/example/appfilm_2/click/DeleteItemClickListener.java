package com.example.appfilm_2.click;

import android.widget.ImageView;

import com.example.appfilm_2.model.Author;
import com.example.appfilm_2.model.FilmModel;

public interface DeleteItemClickListener {
void onDeleteClick(FilmModel filmModel, ImageView movieImageView, int position);
}
