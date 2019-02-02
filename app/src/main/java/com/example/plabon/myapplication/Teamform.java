package com.example.plabon.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Teamform extends Activity {
    private TextView teamname;
    private TextView contact;
    private TextView Location;
    private Button submit;
    private Button cancel;

    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_team_form);

        teamname = findViewById(R.id.TeamNameEditText);
        contact = findViewById(R.id.TeamPhoneEditText);
        Location = findViewById(R.id.TeamLocationEditText);
        submit = findViewById(R.id.teamsubmitButton);
        cancel = findViewById(R.id.teamcancelButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teamform.this,MainMenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addteam();
            }
        });





    }
    private void addteam()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("Teams");
        final String steam = teamname.getText().toString();
        final String scontact = contact.getText().toString().trim();
        final String slocation = Location.getText().toString();

        if(TextUtils.isEmpty(steam) || TextUtils.isEmpty(scontact) || TextUtils.isEmpty(slocation))
            Toast.makeText(this, "Please fill out all the sections",Toast.LENGTH_LONG).show();
        else
        {
            Teamuserform teamuserform = new Teamuserform(steam);
            databaseReference.push().setValue(teamuserform);
            Intent intent = new Intent(Teamform.this,MainMenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
