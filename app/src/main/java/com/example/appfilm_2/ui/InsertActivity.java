package com.example.appfilm_2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appfilm_2.R;

public class InsertActivity extends AppCompatActivity {
    Button btn_Film;
    Button btn_Danhmuc;
    Button btn_SignOut;
    Button btn_DeleteFilm;
    Button btn_editFilm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        btn_Film = findViewById(R.id.btn_Film);
        btn_Danhmuc = findViewById(R.id.btn_Danhmuc);
        btn_SignOut = findViewById(R.id.btn_SignOut);
        btn_DeleteFilm = findViewById(R.id.btn_DeleteFilm);
        btn_editFilm = findViewById(R.id.btn_editFilm);
        btn_editFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertActivity.this,UpdateFilmActivity.class);
                startActivity(intent);
            }
        });
        btn_DeleteFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertActivity.this, DeleteFilmActivity.class);
                startActivity(intent);
            }
        });
        btn_Film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertActivity.this,FilmActivity.class);
                startActivity(intent);
            }
        });
        btn_Danhmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertActivity.this, CastActivity.class);
                startActivity(intent);
            }
        });
        btn_SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(InsertActivity.this,SignInAcitivity.class);
            startActivity(intent);
            }
        });
    }
}