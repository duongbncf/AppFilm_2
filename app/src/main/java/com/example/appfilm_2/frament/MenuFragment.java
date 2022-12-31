package com.example.appfilm_2.frament;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appfilm_2.R;
import com.example.appfilm_2.ui.ChangePassActivity;
import com.example.appfilm_2.ui.EditAvatarActivity;
import com.example.appfilm_2.ui.ForgotActivity;
import com.example.appfilm_2.ui.SignInAcitivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuFragment extends Fragment {
    private CircleImageView vAvatar;
    private TextView tvName;
    private TextView tvEmail;
    private TextView vSignOut;
    private Uri selectedImage;
    private ImageView IV_setting;
    private TextView TV_change_password;
    private TextView tv_change_avatar;
    //    private FirebaseStorage mStorage = FirebaseStorage.getInstance();
    private DatabaseReference databaseReference;
    //    private StorageReference storageReference;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_menu_new, container, false);
        vAvatar = view.findViewById(R.id.vAvatar);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        vSignOut = view.findViewById(R.id.vSignOut);
        tv_change_avatar = view.findViewById(R.id.tv_change_avatar);
        TV_change_password = view.findViewById(R.id.TV_change_password);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Account1");
//        storageReference = FirebaseStorage.getInstance().getReference().child("Profile Pic");
        tv_change_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), EditAvatarActivity.class);
                startActivity(intent);
            }
        });
        TV_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ChangePassActivity.class);
                startActivity(intent);

            }
        });
        vAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), EditAvatarActivity.class);
                startActivity(intent);
            }
        });
        getUserInfo();
        showUser();
//    updateAvatar();
        return view;

    }

    public void getUserInfo() {
        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() && snapshot.getChildrenCount() > 0) {
                    if (snapshot.hasChild("image")) {
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

    //    private void updateAvatar() {
//        vAvatar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////               intent.setType("image/*");
////               intent.setAction(Intent.ACTION_GET_CONTENT);
////                startActivityForResult(intent,3);
////                mStorage.getReference("images/" + UUID.randomUUID().toString()).putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
////                    @Override
////                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
////
////                        Toast.makeText(requireContext(),"Upload Thành Công!",Toast.LENGTH_SHORT).show();
////                    }
////                });
////
//
//
//            }
//        });
//    }
//
    private void showUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        if (name == null) {
            tvName.setVisibility(getView().GONE);
        } else {
            tvName.setVisibility(getView().VISIBLE);
            tvName.setText(name);
        }
        tvEmail.setText(email);


        vSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(requireContext(), SignInAcitivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK && data != null){
//                 selectedImage = data.getData();
//            vAvatar.setImageURI(selectedImage);
//        }
//    }
}
