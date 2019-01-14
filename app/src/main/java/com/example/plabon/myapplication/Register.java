package com.example.plabon.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends Activity {

    EditText password;
    EditText fullname;
    EditText position;
    EditText jerseynumber;
    EditText email;
    EditText Location;
    EditText phoneNumber;
    EditText Playerfoot;
    Button doneButton;
    Button cancel;
    Button visibilty;
    TextView textjersey, textposition, textfoot;

    private DatabaseReference databaseReference;
    private FirebaseAuth userAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        userAuthentication = FirebaseAuth.getInstance();

        password = findViewById(R.id.PasswordEditText);
        fullname = findViewById(R.id.PlayerNameEditText);
        position = findViewById(R.id.PlayerPositionEditText);
        jerseynumber = findViewById(R.id.PlayerNumberEditText);
        Location = findViewById(R.id.PlayerLocationEditText);
        email = findViewById(R.id.PlayerEmailEditText);
        Playerfoot = findViewById(R.id.PlayerFootEditText);
        phoneNumber = findViewById(R.id.PlayerPhoneEditText);
        doneButton = findViewById(R.id.idsubmitButton);
        cancel = findViewById(R.id.idcancelButton);
        visibilty = findViewById(R.id.Visibility);

        textjersey = findViewById(R.id.textviewPreferredjersey);
        textposition = findViewById(R.id.textviewPreferredpos);
        textfoot = findViewById(R.id.textViewPreferredfoot);

        jerseynumber.setVisibility(View.GONE);
        position.setVisibility(View.GONE);
        Playerfoot.setVisibility(View.GONE);
        textjersey.setVisibility(View.GONE);
        textfoot.setVisibility(View.GONE);
        textposition.setVisibility(View.GONE);

        visibilty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jerseynumber.setVisibility(View.VISIBLE);
                position.setVisibility(View.VISIBLE);
                Playerfoot.setVisibility(View.VISIBLE);
                textjersey.setVisibility(View.VISIBLE);
                textfoot.setVisibility(View.VISIBLE);
                textposition.setVisibility(View.VISIBLE);
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayer();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,AuthActivity.class));
            }
        });
    }

    private void addPlayer() {
        final FirebaseUser hmmuser = userAuthentication.getCurrentUser();
        //System.out.println("asd sfg sfdg sdfg sd " + hmmuser.getEmail());

        final String spassword = password.getText().toString().trim();
        final String sfullname = fullname.getText().toString().trim();
        final String sposition = position.getText().toString().trim();
        final String sjerseynumber = jerseynumber.getText().toString().trim();
        final String semail = email.getText().toString().trim();
        final String sLocation = Location.getText().toString().trim();
        final String sfoot = Playerfoot.getText().toString().trim();

        final String sphoneNumber;

        if(TextUtils.isEmpty(phoneNumber.getText().toString().trim())) sphoneNumber = "null";
        else sphoneNumber = phoneNumber.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(semail).matches()){
            //Toast.makeText(this,"Please input Email Correctly",Toast.LENGTH_LONG).show();
            email.setError("Please input Email Correctly.");
            email.requestFocus();
            return;
        }
        if(spassword.length() < 8){
            //Toast.makeText(this,"Password has to be at least 8 chars",Toast.LENGTH_LONG).show();
            password.setError("Password has to be at least 8 chars");
            password.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(spassword) || TextUtils.isEmpty(sfullname) || TextUtils.isEmpty(sposition) || TextUtils.isEmpty(sjerseynumber) || TextUtils.isEmpty(semail)){
            Toast.makeText(this, "Please fill out all the sections",Toast.LENGTH_LONG).show();
        }else{
            System.out.println("@@@@@@@@@@@" + semail + " " + spassword);
            userAuthentication.createUserWithEmailAndPassword(semail,spassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User user = new User(semail, sfullname,sposition,sjerseynumber,sphoneNumber,sLocation,sfoot,"https://i.kym-cdn.com/entries/icons/original/000/003/619/ForeverAlone.jpg","false","Nothing yet.");
                                //com.example.plabon.myapplication.User user =
                                databaseReference.child(semail.replace('.','&')).setValue(user);
                                FirebaseUser hmmttuser = userAuthentication.getCurrentUser();
                                hmmttuser.sendEmailVerification();


                                Toast.makeText(Register.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();

                                email.setText("");
                                password.setText("");
                                phoneNumber.setText("");
                                fullname.setText("");
                                position.setText("");
                                jerseynumber.setText("");

                                Intent intent = new Intent(Register.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                userAuthentication.signOut();
                                startActivity(intent);
                            }else{
                                if(task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(Register.this, "Email alredy taken.", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(Register.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
        }
    }

}

