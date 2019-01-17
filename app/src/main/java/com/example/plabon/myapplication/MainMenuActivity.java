package com.example.plabon.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;

public class MainMenuActivity extends Activity {

    Button EventHostMenu;
    Button Playerprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        EventHostMenu = findViewById(R.id.EventHostButton);
        Playerprofile = findViewById(R.id.profileButton);

        EventHostMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this,Frontpage.class));
            }
        });
        Playerprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this,Showuser.class));
            }
        });

    }

}
