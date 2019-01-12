package com.example.plabon.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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
    private Button btnSignOut;

    protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_auth);
            //declare buttons and edit texts in oncreate
            mEmail = (EditText) findViewById(R.id.email);
            mPassword = (EditText) findViewById(R.id.password);
            btnSignIn = (Button) findViewById(R.id.email_sign_in_button);
            btnSignOut = (Button) findViewById(R.id.email_sign_out_button);
            //btnAddItems = (Button) findViewById(R.id.add_item_screen);

            mAuth = FirebaseAuth.getInstance();

            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {
                        // User is signed in
                        Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                        toastMessage("Successfully signed in with: " + user.getEmail());
                    } else {
                        // User is signed out
                        Log.d(TAG, "onAuthStateChanged:signed_out");
                        toastMessage("Successfully signed out.");
                    }
                    // ...
                }
            };

            btnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = mEmail.getText().toString();
                    String pass = mPassword.getText().toString();
                    if (!email.equals("") && !pass.equals("")) {
                        mAuth.signInWithEmailAndPassword(email, pass);
                    } else {
                        // toastMessage("You didn't fill in all the fields.");
                    }
                    Intent intent = new Intent(AuthActivity.this, Register.class);
                    startActivity(intent);
                }
            });

            btnSignOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mAuth.signOut();
                    //toastMessage("Signing Out...");
                }
            });


        }

        @Override
        public void onStart () {
            super.onStart();
            mAuth.addAuthStateListener(mAuthListener);
        }

        @Override
        public void onStop () {
            super.onStop();
            if (mAuthListener != null) {
                mAuth.removeAuthStateListener(mAuthListener);
            }
        }

        private void toastMessage(String message){
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        }

    }
