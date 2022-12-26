package com.example.appfilm_2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appfilm_2.R;
import com.example.appfilm_2.model.FilmModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InsertFilmActivity extends AppCompatActivity {
    private DatabaseReference myRef;
    private ArrayList<FilmModel> filmModelList;
    EditText edit_filmName,edit_depcription,edit_linkimageavatar,edit_linkcover,edit_linkfilm;
    Button txtDienVien,txtDanhMuc;
    Button btnThem;
//    public static List<Integer> idCast = new ArrayList<>();
    public static int idCast = 0;
    public static String nameCast = "Choose Cast";
    public static int idCategories = 1;
    public static String nameCategories = "Choose Category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_film);
        edit_filmName = findViewById(R.id.edit_filmName);
        edit_depcription = findViewById(R.id.edit_depcription);
        edit_linkimageavatar = findViewById(R.id.edit_linkimageavatar);
        edit_linkcover = findViewById(R.id.edit_linkcover);
        edit_linkfilm = findViewById(R.id.edit_linkfilm);
        btnThem = findViewById(R.id.btnThem);
        txtDienVien = findViewById(R.id.txtDienVien);
        txtDanhMuc = findViewById(R.id.txtDanhMuc);
        filmModelList = new ArrayList<>();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewPost();

            }
        });
        getAllFilm();
        txtDienVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertFilmActivity.this,SelectCastActivity.class);
                startActivity(intent);
            }
        });

        txtDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertFilmActivity.this, SelectCategoriesActivity.class);
                startActivity(intent);
                txtDanhMuc.setText("Choose Category");
            }
        });
    }


    void getAllFilm() {
        myRef = FirebaseDatabase.getInstance().getReference("Film");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    FilmModel filmModel = data.getValue(FilmModel.class);
                    filmModelList.add(filmModel);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void writeNewPost() {
        String filmname = edit_filmName.getText().toString();
        String depcription = edit_depcription.getText().toString();
        String linkimageavatar = edit_linkimageavatar.getText().toString();
        String linkcover = edit_linkcover.getText().toString();
        String linkfilm = edit_linkfilm.getText().toString();
        myRef = FirebaseDatabase.getInstance().getReference("Film");
        FilmModel filmModel = new FilmModel();
        filmModel.setName(filmname);
        filmModel.setAvatar(linkimageavatar);
        filmModel.setCover_image(linkcover);
        filmModel.setDepcription(depcription);
        filmModel.setUrl(linkfilm);
        filmModel.setIdAuthor(idCast);
        filmModel.setIdCategories(idCategories);
//        long time = Calendar.getInstance().getTime().getTime();
        myRef.child(String.valueOf(filmModelList.size())).setValue(filmModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        getAllFilm();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...
                    }
                });

    }

    @Override
    protected void onResume() {
        txtDienVien.setText(nameCast);
        txtDanhMuc.setText(nameCategories);
        super.onResume();
    }
}