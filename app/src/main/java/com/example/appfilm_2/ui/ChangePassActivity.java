package com.example.appfilm_2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfilm_2.R;
import com.example.appfilm_2.frament.MenuFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassActivity extends AppCompatActivity {
    private EditText edt_old_password;
    private EditText edt_new_password;
    private EditText edt_new_password_again;
    private Button btnThem;
    private TextView tv_back;
    private TextView tvError4;
    private String passInput = "";
    private String passInputAgain = "";
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        edt_new_password = findViewById(R.id.edt_new_password_again);
        edt_old_password = findViewById(R.id.edt_old_password);
        edt_new_password_again = findViewById(R.id.edt_new_password_again);
        tvError4 = findViewById(R.id.tvError4);
        btnThem = findViewById(R.id.btnThem);
        edt_old_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass = s.toString().trim();
                if (pass.length() < 8) {
                    edt_old_password.setError("Password at least 8 characters");
                } else {
                    edt_old_password.setError(null);
                }
            }
        });
        edt_new_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass = s.toString().trim();
                if (pass.length() < 8) {
                    edt_new_password.setError("Password at least 8 characters");
                } else {
                    edt_new_password.setError(null);
                }
                passInput = s.toString().trim();
            }
        });
        edt_new_password_again.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass = s.toString().trim();
                if (pass.length() < 8) {
                    edt_new_password_again.setError("Password at least 8 characters");
                } else {
                    edt_new_password_again.setError(null);
                }
                passInputAgain = s.toString().trim();

                if (passInput.equals(passInputAgain)) {
                    tvError4.setText(null);
                } else {
                    tvError4.setText("Password does not match");
                }
            }
        });
        tv_back = findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              onBackPressed();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newpassword = edt_old_password.getText().toString().trim();
                String newpassword_again = edt_new_password.getText().toString().trim();
                updatepassword(newpassword, newpassword_again);
            }

            private void updatepassword(String newpassword, String newpassword_again) {
                AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), newpassword);
                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(newpassword_again).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(ChangePassActivity.this,"Something went wrong. Please try again later",Toast.LENGTH_SHORT).show();

                                    } else {
                                        finish();
                                        Toast.makeText(ChangePassActivity.this,"Password Successfully Modified",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}