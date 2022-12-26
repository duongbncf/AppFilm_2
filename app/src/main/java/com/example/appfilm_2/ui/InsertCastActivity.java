package com.example.appfilm_2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appfilm_2.R;
import com.example.appfilm_2.model.Author;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InsertCastActivity extends AppCompatActivity {
    EditText edit_name, edit_image;
    Button btnThem;
    private DatabaseReference myRef;
    private ArrayList<Author> castModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_cast);
        edit_image = findViewById(R.id.edit_image);
        edit_name = findViewById(R.id.edit_name);
        btnThem = findViewById(R.id.btnThem);
        castModels = new ArrayList<Author>();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewPost();
                finish();
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
                for (DataSnapshot data : snapshot.getChildren()) {
                    Author author = data.getValue(Author.class);
                    castModels.add(author);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void writeNewPost() {
        String name = edit_name.getText().toString();
        String linkAvatar = edit_image.getText().toString();
        myRef = FirebaseDatabase.getInstance().getReference("Author");
        Author author = new Author();
        author.setName(name);
        author.setAvatar(linkAvatar);
        author.setIdAuthor(castModels.size());
        myRef.child(String.valueOf(castModels.size())).setValue(author)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        int a =1;
                        getAllFilm();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int a =1;

                        // Write failed
                        // ...
                    }
                });

    }
}