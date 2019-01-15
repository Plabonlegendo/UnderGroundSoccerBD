package com.example.plabon.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthActivity extends Activity {

    private static final String TAG = "Auth";

    private Button mAddToDB;

    private EditText mNewFood;

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private EditText mEmail;
    private EditText mPassword;
    private Button btnSignIn;
    private Button btnSignUp;
    private Button btnForgetPassword;
    private Button btnOrganizerMode;

    private DatabaseReference loginPageDatabaseRef;

    protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            //declare buttons and edit texts in oncreate
            mEmail = (EditText) findViewById(R.id.emailEditText);
            mPassword = (EditText) findViewById(R.id.passwordEditText);
            btnSignIn = (Button) findViewById(R.id.signInButton);
            btnSignUp = (Button) findViewById(R.id.signUpButton);
            btnForgetPassword = findViewById(R.id.forgotPasswordButton);

            //btnAddItems = (Button) findViewById(R.id.add_item_screen);

            mAuth = FirebaseAuth.getInstance();
            loginPageDatabaseRef = FirebaseDatabase.getInstance().getReference("users");


            btnForgetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AuthActivity.this,ResetpasswordActivity.class );
                    startActivity(intent);
                }
            });
            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AuthActivity.this, Register.class);
                    startActivity(intent);

                    //toastMessage("Signing Out...");
                }
            });

            btnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
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

                            Toast.makeText(AuthActivity.this,"Chinsi Bro!",Toast.LENGTH_LONG).show();

                            final FirebaseUser currentUser = mAuth.getCurrentUser();
                            if(currentUser.isEmailVerified()){

                                Intent intent = new Intent(AuthActivity.this,Frontpage.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.putExtra("current", currentUser.getEmail());
                                startActivity(intent);

                            }else{
                                Toast.makeText(AuthActivity.this,"Verify the Email First",Toast.LENGTH_LONG).show();
                            }

                            // pass information to homepage
                        }else{
                            Toast.makeText(AuthActivity.this,"Wrong password" + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void toastMessage(String message){
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void onStart() {
        super.onStart();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

           /* if(currentUser.isEmailVerified()){

                Intent intent = new Intent(AuthActivity.this,Register.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("current",currentUser.getEmail());
                startActivity(intent);


            }else{
                Toast.makeText(AuthActivity.this,"Verify the Email And Login",Toast.LENGTH_LONG).show();
            }*/
        }
    }

}
