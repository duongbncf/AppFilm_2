package com.example.appfilm_2.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfilm_2.R;
import com.example.appfilm_2.adapter.AdapterDeleteFilm;
import com.example.appfilm_2.adapter.AdapterUpdateFilm;
import com.example.appfilm_2.click.DeleteItemClickListener;
import com.example.appfilm_2.click.UpdateItemClickListener;
import com.example.appfilm_2.model.FilmModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpdateFilmActivity extends AppCompatActivity {
//    EditText edit_name, edit_image;
    //    Button btnThem;
    private ArrayList<FilmModel> filmModels;
    private AdapterUpdateFilm adapterUpdateFilm;
    private RecyclerView rcvCast;
    private DatabaseReference myRef;
    private ImageView ivAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_film);
//        edit_image = findViewById(R.id.edit_image);
//        edit_name = findViewById(R.id.edit_name);
//        btnThem = findViewById(R.id.btnThem);
        rcvCast = findViewById(R.id.rcvCast);
        filmModels = new ArrayList<>();
        ivAdd = findViewById(R.id.ivAdd);
        adapterUpdateFilm = new AdapterUpdateFilm(UpdateFilmActivity.this, new UpdateItemClickListener() {
            @Override
            public void onUpdateClick(FilmModel filmModel, ImageView movieImageView, int position) {
                String id = String.valueOf(position + 1);
                Intent intent = new Intent(UpdateFilmActivity.this, UpdateFilmMan2Activity.class);
                intent.putExtra("id", id);
                startActivity(intent);


            }
        });
        rcvCast.setAdapter(adapterUpdateFilm);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        rcvCast.setLayoutManager(mLayoutManager);
//        rcvFilm.setLayoutManager(new LinearLayoutManager(FilmActivity.this, LinearLayoutManager.VERTICAL, false));
        rcvCast.setHasFixedSize(true);
        getAllFilm();
    }

    void getAllFilm() {
        filmModels.clear();
        myRef = FirebaseDatabase.getInstance().getReference("Film");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    FilmModel filmModel = data.getValue(FilmModel.class);
                    filmModels.add(filmModel);
                }
                adapterUpdateFilm.setCastModel(filmModels);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    private void writeNewPost() {
//        String name = edit_name.getText().toString();
//        String linkAvatar = edit_image.getText().toString();
//        myRef = FirebaseDatabase.getInstance().getReference("Author");
//        Author author = new Author();
//        author.setName(name);
//        author.setAvatar(linkAvatar);
//        author.setIdAuthor(castModels.size());
//        myRef.child(String.valueOf(castModels.size())).setValue(author)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        int a =1;
//                        getAllFilm();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        int a =1;
//
//                        // Write failed
//                        // ...
//                    }
//                });

    //   }
}