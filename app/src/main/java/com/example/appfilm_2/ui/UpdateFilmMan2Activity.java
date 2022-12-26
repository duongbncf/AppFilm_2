package com.example.appfilm_2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appfilm_2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateFilmMan2Activity extends AppCompatActivity {
    EditText edtAvatar;
    EditText edtCover_Image;
    EditText edt_depcription;
    EditText edtName;
    EditText edtUrl;
    EditText edtId;
    Button updateBtn;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_film_man2);
        edtAvatar = findViewById(R.id.edtAvatar);
        edtCover_Image = findViewById(R.id.edtCover_Image);
        edt_depcription = findViewById(R.id.edt_depcription);
        edtName = findViewById(R.id.edtName);
        edtUrl = findViewById(R.id.edtUrl);
        edtId = findViewById(R.id.edtId);
        updateBtn = findViewById(R.id.updateBtn);
        String id1 = getIntent().getExtras().getString("id");
        edtId.setText(id1);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String avatar = edtAvatar.getText().toString();
                String cover_Image = edtCover_Image.getText().toString();
                String depcription = edt_depcription.getText().toString();
                String name = edtName.getText().toString();
                String url = edtUrl.getText().toString();

                String id = edtId.getText().toString();

                updateData(avatar,cover_Image,depcription,name,url,id);
            }

            private void updateData(String avatar, String cover_image, String depcription, String name, String url,String id) {

                HashMap User = new HashMap();
                User.put("avatar",avatar);
                User.put("depcription",depcription);
                User.put("url",url);
                User.put("cover_image",cover_image);
                User.put("name",name);
                databaseReference = FirebaseDatabase.getInstance().getReference("Film");
                databaseReference.child(id).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                   if(task.isSuccessful()){
                       edtAvatar.setText("");
                       edtCover_Image.setText("");
                       edt_depcription.setText("");
                       edtName.setText("");
                       edtUrl.setText("1");
                       edtName.setText("1");
                       Toast.makeText(UpdateFilmMan2Activity.this,"OKE",Toast.LENGTH_SHORT).show();

                   }else {
                       Toast.makeText(UpdateFilmMan2Activity.this,"Failed",Toast.LENGTH_SHORT).show();
                   }
                    }
                });
            }
        });
    }
}