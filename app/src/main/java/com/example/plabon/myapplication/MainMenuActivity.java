package com.example.plabon.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;

public class MainMenuActivity extends Activity {

    Button EventHostMenu;
    Button Playerprofile;
    Button Teamhostmenu;
    Button EventFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        EventHostMenu = findViewById(R.id.EventHostButton);
        Playerprofile = findViewById(R.id.profileButton);
        Teamhostmenu = findViewById(R.id.TeamButton);
        EventFeed = findViewById(R.id.EventButton);

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
        Teamhostmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this,ShowTeam.class));
            }
        });
        EventFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this,EventFeed.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            

        }

        return true;
    }
}
