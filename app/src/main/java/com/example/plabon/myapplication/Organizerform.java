package com.example.plabon.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Organizerform extends Activity {
    EditText mEmail;
    EditText mPassword;
    Button signin;
    Button signup;


    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organize_menu);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();


        mEmail = (EditText) findViewById(R.id.emailEditText);
        mPassword = (EditText) findViewById(R.id.passwordEditText);

        signin = findViewById(R.id.EventSignInButtton);
        signup = findViewById(R.id.HostEventButton);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Organizerform.this, Eventform.class));
            }
        });




    }

    public void login(){
        Log.d("TAG","Attempting Login");
        //progbar.setVisibility(View.VISIBLE);

        String user = mEmail.getText().toString().trim();
        String pass = mPassword.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(user).matches()){
            //Toast.makeText(this,"Please input Email Correctly",Toast.LENGTH_LONG).show();
            mEmail.setError("Please input Email Correctly.");
            mEmail.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(user,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //progbar.setVisibility(View.GONE);
                        if(task.isSuccessful()){

                            Toast.makeText(Organizerform.this,"Chinsi Bro!",Toast.LENGTH_LONG).show();

                            final FirebaseUser currentUser = mAuth.getCurrentUser();
                            if(currentUser.isEmailVerified()){

                                Intent intent = new Intent(Organizerform.this,AuthActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.putExtra("current", currentUser.getEmail());
                                startActivity(intent);

                            }else{
                                Toast.makeText(Organizerform.this,"Verify the Email First",Toast.LENGTH_LONG).show();
                            }

                            // pass information to homepage
                        }else{
                            Toast.makeText(Organizerform.this,"Wrong password" + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}

