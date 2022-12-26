package com.example.appfilm_2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appfilm_2.R;

public class SelectCategoriesActivity extends AppCompatActivity {
    Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_categories);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertFilmActivity.idCategories = 1;
                InsertFilmActivity.nameCategories = "Best";
                finish();
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertFilmActivity.idCategories = 2;
                InsertFilmActivity.nameCategories = "Trend";
                finish();
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertFilmActivity.idCategories = 3;
                InsertFilmActivity.nameCategories = "ACTION";
                finish();
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertFilmActivity.idCategories = 4;
                InsertFilmActivity.nameCategories = "Adventure";
                finish();
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertFilmActivity.idCategories = 5;
                InsertFilmActivity.nameCategories = "Comedy";
                finish();
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertFilmActivity.idCategories = 6;
                InsertFilmActivity.nameCategories = "Sport";
                finish();
            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertFilmActivity.idCategories = 7;
                InsertFilmActivity.nameCategories = "Drama";
                finish();
            }

        });
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertFilmActivity.idCategories = 8;
                InsertFilmActivity.nameCategories = "Scifi";
                finish();
            }
        });
    }
}