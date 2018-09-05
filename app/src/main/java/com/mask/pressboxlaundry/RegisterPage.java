package com.mask.pressboxlaundry;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener{

    ProgressBar ProgB;
    EditText mUser, mPass;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        mUser = (EditText) findViewById(R.id.Register_Name);
        mPass = (EditText) findViewById(R.id.Register_passwd);
        ProgB = (ProgressBar) findViewById(R.id.Prog);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.Register_RegisterBut).setOnClickListener(this);

    }

    private void registerUser(){
        String email = mUser.getText().toString().trim();
        String pass = mPass.getText().toString().trim();

        if(email.isEmpty()){
            mUser.setError("Email is required");
            mUser.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mUser.setError("Enter valid email");
            mUser.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            mPass.setError("Password is required");
            mPass.requestFocus();
            return;
        }

        if(pass.length()<6){
            mPass.setError("Minimum length should be 6");
            mPass.requestFocus();
            return;
        }

        ProgB.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                ProgB.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "User already registered", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.Register_RegisterBut:
                registerUser();
                break;

        }
    }
}

