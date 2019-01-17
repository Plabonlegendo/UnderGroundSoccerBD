package com.example.plabon.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;

public class MainMenuActivity extends Activity {

    Button EventHostMenu;
    Button MyTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        EventHostMenu = findViewById(R.id.EventHostButton);
        MyTeam = findViewById(R.id.TeamButton);

        /*EventHostMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this,Frontpage.class));
            }
        });

        MyTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this,Team.class));
            }
        });*/
    }
    public void eventHostMenu(View view){

        Intent i=new Intent(this,Frontpage.class);
        startActivity(i);
    }

    public void showTeamName(View view){
        Intent ii=new Intent(this,Team.class);
        startActivity(ii);
    }

}
