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
import com.example.appfilm_2.adapter.CastAdapter;
import com.example.appfilm_2.model.Author;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CastActivity extends AppCompatActivity{
    private ArrayList<Author> castModels;
    private CastAdapter castAdapter;
    private RecyclerView rcvCast;
    private DatabaseReference myRef;
    private ImageView ivAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        rcvCast = findViewById(R.id.rcvCast);
        ivAdd = findViewById(R.id.ivAdd);
        castAdapter = new CastAdapter(CastActivity.this);
        castModels = new ArrayList<Author>();
        rcvCast.setAdapter(castAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        rcvCast.setLayoutManager(mLayoutManager);
//        rcvFilm.setLayoutManager(new LinearLayoutManager(FilmActivity.this, LinearLayoutManager.VERTICAL, false));
        rcvCast.setHasFixedSize(true);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CastActivity.this,InsertCastActivity.class);
                startActivity(intent);
            }
        });
        getAllFilm();
    }


    void getAllFilm() {
        castModels.clear();
        myRef = FirebaseDatabase.getInstance().getReference("Author");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                castModels.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Author author = data.getValue(Author.class);
//                    GenericTypeIndicator<List<Integer>> t = new GenericTypeIndicator<List<Integer>>() {};
//                    List<Integer> categories = data.child("id_categories").getValue(t);
//                    filmModel.setIdCategories(categories);
//                    List<Integer> authors = data.child("id_author").getValue(t);
//                    filmModel.setIdAuthor(authors);
                    castModels.add(author);

                }
                castAdapter.setCastModel(castModels);
                castAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}