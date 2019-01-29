package com.example.plabon.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;



public class OngoingEventDetail extends Activity {

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_event_detail);

        bundle = getIntent().getExtras();
        Intent intent = getIntent();

        TextView eventName = findViewById(R.id.ename);
        eventName.setText(intent.getStringExtra("eventName"));

        TextView eventEmail = findViewById(R.id.eemail);
        eventEmail.setText(intent.getStringExtra("eventEmail"));

        TextView eventPhone = findViewById(R.id.ephone);
        eventPhone.setText(intent.getStringExtra("phoneNumber"));

        TextView eventLocation = findViewById(R.id.elocation);
        eventLocation.setText(intent.getStringExtra("eventLocation"));

        TextView eventSTime = findViewById(R.id.estart);
        eventSTime.setText(intent.getStringExtra("startingDate")+" "+intent.getStringExtra("startingTime"));

        TextView eventETime = findViewById(R.id.eend);
        eventETime.setText(intent.getStringExtra("endingDate")+" "+intent.getStringExtra("endingTime"));

    }

}