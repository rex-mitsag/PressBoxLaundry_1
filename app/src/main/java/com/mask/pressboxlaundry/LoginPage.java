package com.mask.pressboxlaundry;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences sharedpreferences;
    int c;

    ProgressBar ProgB;
    EditText mUser, mPass;
    FirebaseAuth mAuth;

    public static final String flname="User_Pass";
    public static final String User_id="Email";
    public static final String Pass_id="Password";

    String uid,pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        ProgB = (ProgressBar) findViewById(R.id.Prog);
        mUser = (EditText) findViewById(R.id.LoginRegister_Email);
        mPass = (EditText) findViewById(R.id.LoginRegister_Passwd);

        mAuth = FirebaseAuth.getInstance();

        sharedpreferences=getSharedPreferences(flname, Context.MODE_PRIVATE);

        SharedPreferences spref=getSharedPreferences(flname,Context.MODE_PRIVATE);
        uid=spref.getString(User_id,"");
        pid=spref.getString(Pass_id,"");

        mUser.setText(uid);
        mPass.setText(pid);


        findViewById(R.id.LoginRegister_SignupBut).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent registerIntent = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(registerIntent);
            }

        });
        findViewById(R.id.LoginRegister_LoginBut).setOnClickListener(this);
    }


    public void Remember_Func(View view) {
        CheckBox checkBox=(CheckBox) findViewById(R.id.remember_me);

        String cno=mUser.getText().toString();
        String pass=mPass.getText().toString();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        if(checkBox.isChecked())
        {
            c=1;
            editor.putString(User_id,cno);
            editor.putString(Pass_id,pass);
            editor.apply();
        }
        else
            c=0;
    }

    private void userLogin(){

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

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                ProgB.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginPage.this, FirstPage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.LoginRegister_LoginBut:
                userLogin();
                break;
        }
    }
}
