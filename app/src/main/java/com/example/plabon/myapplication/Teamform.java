package com.example.plabon.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Teamform extends AppCompatActivity {
    private TextView teamname;
    private TextView contact;
    private TextView Location;

    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_team_form);

        teamname = findViewById(R.id.TeamNameEditText);
        contact = findViewById(R.id.TeamPhoneEditText);
        Location = findViewById(R.id.TeamLocationEditText);

        databaseReference = FirebaseDatabase.getInstance().getReference("Teams");
        final String steam = teamname.getText().toString();
        final String scontact = contact.getText().toString();
        final String slocation = Location.getText().toString();

        if(TextUtils.isEmpty(steam) || TextUtils.isEmpty(scontact) || TextUtils.isEmpty(slocation))
            Toast.makeText(this, "Please fill out all the sections",Toast.LENGTH_LONG).show();
        else
        {
           // Teamuserform teamuserform = new Teamuserform(steam,scontact,slocation);
            databaseReference.push().setValue(teamname);
        }




    }
}
