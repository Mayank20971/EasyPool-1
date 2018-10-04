package com.example.anubhav.easypool;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mauthlistener;
    private EditText getemail;
    private EditText getpass;
    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mauthlistener);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.signup).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);

        getemail = (EditText) findViewById(R.id.email);
        getpass = (EditText) findViewById(R.id.pass);

        mAuth = FirebaseAuth.getInstance();
        mauthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            }
        };
    }




    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signup:

                startActivity(new Intent(this, signup.class));

                break
           
            case R.id.login:
                Signin();
                break;

        }

    }

        private void Signin() {
            String email = getemail.getText().toString();
            String pass = getpass.getText().toString();
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(MainActivity.this, "Empty Fields ", Toast.LENGTH_LONG).show();

            } else {
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(MainActivity.this ,dashboard.class));
                        }else {
                                Toast.makeText(MainActivity.this, "Authentication Error ", Toast.LENGTH_LONG).show();
                            }
                        }

                });
            }

        }


}
