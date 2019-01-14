package com.example.plabon.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Frontpage extends Activity {

    Button eventorganize;

    private FirebaseAuth mAuth;
    private DatabaseReference loginPageDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage);

        eventorganize = findViewById(R.id.Eventorganizer);

        mAuth = FirebaseAuth.getInstance();
        loginPageDatabaseRef = FirebaseDatabase.getInstance().getReference("users");

        eventorganize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addevent();
            }
        });
    }
    private void Addevent() {
        startActivity(new Intent(Frontpage.this, Eventform.class));
    }
}
