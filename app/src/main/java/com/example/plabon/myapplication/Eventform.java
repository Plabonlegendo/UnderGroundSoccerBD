package com.example.plabon.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Eventform extends Activity {

    EditText Eventname;
    EditText Hostname;
    EditText HostEmail;
    EditText Eventlocation;
    EditText phonenumber;
    TimePicker Startingtime;
    DatePicker Startingdate;
    TimePicker Endingtime;
    DatePicker Endingdate;
    EditText passfield;

    Button submit;
    Button cancel;

    private DatabaseReference databaseReference;
    private FirebaseAuth userAuthentication;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);

        databaseReference = FirebaseDatabase.getInstance().getReference("events");
        userAuthentication = FirebaseAuth.getInstance();


        Eventname = findViewById(R.id.EventNameEditText);
       // Hostname = findViewById(R.id.hostNameEditText);
        HostEmail = findViewById(R.id.EventEmailEditText);
        Eventlocation = findViewById(R.id.EventLocationEditText);
        phonenumber = findViewById(R.id.EventPhoneEditText);
        Startingtime = findViewById(R.id.StartTimeEditText);
        Startingdate = findViewById(R.id.StartDateEditText);
        Endingdate = findViewById(R.id.EndDateEditText);
        Endingtime = findViewById(R.id.EndTimeEditText);
        passfield = findViewById(R.id.eventPasswordEditText);
        submit = findViewById(R.id.submitButton);
        cancel = findViewById(R.id.cancelButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addevent();
            }
        });
    }
    public void addevent(){
        final FirebaseUser hmmuser = userAuthentication.getCurrentUser();

        if(hmmuser != null)
        {
            String EMAIL= userAuthentication.getCurrentUser().getEmail();
        }

        final String seventname = Eventname.getText().toString().trim();
       // final String shostname = Hostname.getText().toString().trim();
        final String shostemail = HostEmail.getText().toString().trim();
        final String seventlocation = Eventlocation.getText().toString().trim();
        final String sphonenumber ;

        final String spassfield = passfield.getText().toString().trim();

        if(TextUtils.isEmpty(phonenumber.getText().toString().trim())) sphonenumber = "null";
        else sphonenumber = phonenumber.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(shostemail).matches()){
            //Toast.makeText(this,"Please input Email Correctly",Toast.LENGTH_LONG).show();
            HostEmail.setError("Please input Email Correctly.");
            HostEmail.requestFocus();
            return;
        }
         /*Pattern.compile(DATE_PATTERN).matcher(Birthday);

//Birthday validator
    else if (! Pattern.compile(DATE_PATTERN).matcher(Birthday).matches()) {
            Toast.makeText(getApplicationContext(), "Invalid Birthday!", Toast.LENGTH_SHORt).show();
        }*/
        if(spassfield.length() < 8){
            //Toast.makeText(this,"Password has to be at least 8 chars",Toast.LENGTH_LONG).show();
            passfield.setError("Password has to be at least 8 chars");
            passfield.requestFocus();
            return;
        }


        else
        {
            userAuthentication.createUserWithEmailAndPassword(shostemail,spassfield).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful())
                    {
                        try
                        {
                            throw task.getException();
                        }
                        catch (FirebaseAuthUserCollisionException existEmail)
                        {
                            //Log.d(TAG, "onComplete: exist_email");

                            // TODO: Take your action
                        }
                        catch (Exception e)
                        {
                            //Log.d(TAG, "onComplete: " + e.getMessage());
                        }
                    }

                }
            });
        }

    }
}
