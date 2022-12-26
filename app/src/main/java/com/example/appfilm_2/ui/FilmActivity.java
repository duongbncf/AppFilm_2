package com.example.appfilm_2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.appfilm_2.R;
import com.example.appfilm_2.adapter.CategoryAdapter;
import com.example.appfilm_2.click.MovieItemClickListener;
import com.example.appfilm_2.model.Author;
import com.example.appfilm_2.model.FilmModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FilmActivity extends AppCompatActivity implements MovieItemClickListener {
    private ArrayList<FilmModel> filmModelList;
    private CategoryAdapter FilmcategoryAdapter;
    private RecyclerView rcvFilm;
    private DatabaseReference myRef;
    private ArrayList<Author> castModels;
    private ImageView ivAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        rcvFilm = findViewById(R.id.rcvFilm);
        ivAdd = findViewById(R.id.ivAdd);

        FilmcategoryAdapter = new CategoryAdapter(FilmActivity.this,this::onMovieClick);
        filmModelList = new ArrayList<>();
        rcvFilm.setAdapter(FilmcategoryAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        rcvFilm.setLayoutManager(mLayoutManager);
//        rcvFilm.setLayoutManager(new LinearLayoutManager(FilmActivity.this, LinearLayoutManager.VERTICAL, false));
      rcvFilm.setHasFixedSize(true);
        getAllFilm();

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilmActivity.this,InsertFilmActivity.class);
                startActivity(intent);
            }
        });
    }


    void getAllFilm() {
        myRef = FirebaseDatabase.getInstance().getReference("Film");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                filmModelList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    FilmModel filmModel = data.getValue(FilmModel.class);
//                    GenericTypeIndicator<List<Integer>> t = new GenericTypeIndicator<List<Integer>>() {};
//                    List<Integer> categories = data.child("id_categories").getValue(t);
//                    filmModel.setIdCategories(categories);
//                    List<Integer> authors = data.child("id_author").getValue(t);
//                    filmModel.setIdAuthor(authors);
                    filmModelList.add(filmModel);

                }
                FilmcategoryAdapter.setDataModels(filmModelList);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
    });
    }
    @Override
    public void onMovieClick(FilmModel filmModel, ImageView movieImageView) {

    }

}

