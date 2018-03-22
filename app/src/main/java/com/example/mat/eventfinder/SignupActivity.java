package com.example.mat.eventfinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabaseRef;
    private EditText username, password, rePassword;
    private Button btnSignup;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        username = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        rePassword = (EditText)findViewById(R.id.re_password);
        btnSignup = (Button)findViewById(R.id.btnSignUp);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        firebaseDatabaseRef = FirebaseDatabase.getInstance().getReference("users");

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser(){

        String email = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String repass = rePassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Enter a email id.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass) && TextUtils.isEmpty(repass)){
            Toast.makeText(getApplicationContext(), "Enter the Passwords.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.length() < 6){
            Toast.makeText(getApplicationContext(), "Password is too short.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.equals(repass)){

            progressBar.setVisibility(View.VISIBLE);

            firebaseAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()){


                                Toast.makeText(SignupActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(SignupActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(getApplicationContext(), "Passwords doesn't match.", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void openLogin(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}