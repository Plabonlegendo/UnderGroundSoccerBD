package com.example.plabon.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;

public class EventFeed extends Activity {

    Button buttonMyParticipation;
    Button buttonOngoingEvents;
    Button buttonUpcomingEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_feed);

        buttonMyParticipation = findViewById(R.id.buttonMyParticipation);
        buttonOngoingEvents = findViewById(R.id.buttonOngoingEvents);
        buttonUpcomingEvents = findViewById(R.id.buttonUpcomingEvents);

        buttonMyParticipation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventFeed.this,MyParticipation.class));
            }
        });
        buttonOngoingEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventFeed.this,OngoingEvents.class));
            }
        });
        buttonUpcomingEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventFeed.this,UpcomingEvents.class));
            }
        });

    }

}