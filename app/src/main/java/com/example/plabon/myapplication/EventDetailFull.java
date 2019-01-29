package com.example.plabon.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;



public class EventDetailFull extends Activity {

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail_full);

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

        TextView bestAttacker = findViewById(R.id.attacker);
        bestAttacker.setText(intent.getStringExtra("bestAttacker"));

        TextView bestDefender = findViewById(R.id.defender);
        bestDefender.setText(intent.getStringExtra("bestDefender"));

        TextView bestGoalkeeper = findViewById(R.id.goalkeeper);
        bestGoalkeeper.setText(intent.getStringExtra("bestGoalkeeper"));

        TextView bestMidfielder = findViewById(R.id.midfielder);
        bestMidfielder.setText(intent.getStringExtra("bestMidfielder"));

        TextView bestPlayer = findViewById(R.id.player);
        bestPlayer.setText(intent.getStringExtra("bestPlayer"));

    }

}