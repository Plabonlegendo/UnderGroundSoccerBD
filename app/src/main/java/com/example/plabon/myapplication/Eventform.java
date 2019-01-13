package com.example.plabon.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Eventform extends Activity {

    EditText Eventname;
    EditText Hostname;
    EditText HostEmail;
    EditText Eventlocation;
    EditText phonenumber;
    EditText Startingtime;
    EditText Endingtime;
    EditText passfield;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);

    }
}
