package com.example.appfilm_2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.appfilm_2.R;
import com.example.appfilm_2.frament.MenuFragment;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditAvatarActivity extends AppCompatActivity {
    private CircleImageView vAvatar;
    private Button btn_save;
    private Button btn_close;

    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    private Uri imageUri;
    private String myUri = "";
    private StorageTask uploadTask;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_avatar);
        vAvatar = findViewById(R.id.vAvatar);
        btn_save = findViewById(R.id.btn_save);
        btn_close = findViewById(R.id.btn_close);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Account1");
        storageReference = FirebaseStorage.getInstance().getReference().child("Profile Pic");
        getUserInfo();
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
        vAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 3);

            }


        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfileImage();
            }

            private void uploadProfileImage() {
                final ProgressDialog progressDialog = new ProgressDialog(EditAvatarActivity.this);
                progressDialog.setTitle("Set your profile");
                progressDialog.setMessage("Please wait, while we are setting your data");
                progressDialog.show();
                if (imageUri != null) {
                    final StorageReference fileRef = storageReference.child(mAuth.getCurrentUser().getUid() + ".jpg");
                    uploadTask = fileRef.putFile(imageUri);
                    uploadTask.continueWithTask(new Continuation() {
                        @Override
                        public Object then(@NonNull Task task) throws Exception {
                            if (!task.isSuccessful()) {

                            }
                            return fileRef.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri uri = task.getResult();
                                myUri = uri.toString();
                                HashMap<String, Object> userMap = new HashMap<>();
                                userMap.put("image", myUri);
                                databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);
                                progressDialog.dismiss();
                                Toast.makeText(EditAvatarActivity.this,"Successful!",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(EditAvatarActivity.this, "Image not selected", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    public void getUserInfo() {
        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
          if(snapshot.exists() && snapshot.getChildrenCount() > 0){
              if(snapshot.hasChild("image")){
                  String image = snapshot.child("image").getValue().toString();
                  Picasso.get().load(image).into(vAvatar);
              }

          }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            vAvatar.setImageURI(imageUri);
        }
    }
}