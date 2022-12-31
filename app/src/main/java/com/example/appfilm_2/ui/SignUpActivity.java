package com.example.appfilm_2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfilm_2.R;
import com.example.appfilm_2.model.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText edtUserNameorPassword;
    EditText edtPasswordCreate;
    ImageView btnRegister;
    ProgressDialog progressDialog;
    EditText edtConfirmPassword;
    ImageView imagView;
    TextView tvError4;
    private String passInput = "";
    private String passInputAgain = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtPasswordCreate = findViewById(R.id.edtPasswordCreate);
        edtUserNameorPassword = findViewById(R.id.edtUserNameorPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvError4 = findViewById(R.id.tvError4);
        progressDialog = new ProgressDialog(this);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtUserNameorPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = s.toString().trim();
                if (name.length() < 6) {
                    edtUserNameorPassword.setError("Account minimum 6 characters");
                } else {
                    edtUserNameorPassword.setError(null);
                }
            }
        });

        edtPasswordCreate.addTextChangedListener(new TextWatcher() {
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
                    edtPasswordCreate.setError("Password at least 8 characters");
                } else {
                    edtPasswordCreate.setError(null);
                }
                passInput = s.toString().trim();
            }
        });
        edtConfirmPassword.addTextChangedListener(new TextWatcher() {
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
                    edtConfirmPassword.setError("Password at least 8 characters");
                } else {
                    edtConfirmPassword.setError(null);
                }
                passInputAgain = s.toString().trim();

                if (passInput.equals(passInputAgain)) {
                    tvError4.setText(null);
                } else {
                    tvError4.setText("Password does not match");
                }
            }
        });
        initListener();
    }











    private void initListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUp();
            }
            private void onClickSignUp() {
                String strEmail = edtUserNameorPassword.getText().toString().trim();
                String strPassword = edtPasswordCreate.getText().toString().trim();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(strEmail,strPassword)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                FirebaseDatabase.getInstance().getReference("account/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new Account(edtUserNameorPassword.getText().toString(),""));
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {

                                    Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUpActivity.this, "Authentication failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

        });
    }
})
    ;}
}