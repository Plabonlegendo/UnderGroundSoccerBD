package com.example.plabon.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Showuser extends Activity {

    private FirebaseUser currentUser;
    private FirebaseAuth showSingleUserAuth;
    private DatabaseReference showSingleUserDatabaseRef,myref;
    private DatabaseReference userQuestionDatabaseRef;

    TextView name_value;
    TextView email_value;
    TextView position_value;
    TextView jerseynumber_value;
    TextView phoneNumber_value;
    TextView location_value;
    TextView bio_value;
    ImageView profilePicture;
    ImageButton edit_profilePhoto_button;
    ImageButton toUserQuestion;

    String  userEmail,nowUser;
    User user;

    ArrayList<String> userFiltered = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showuser);

        name_value = findViewById(R.id.name_value);
        email_value = findViewById(R.id.email_value);
        position_value = findViewById(R.id.batch_value);
        jerseynumber_value = findViewById(R.id.roll_value);
        phoneNumber_value = findViewById(R.id.phone_value);
        location_value = findViewById(R.id.location_value);
        bio_value = findViewById(R.id.bio_value);
        profilePicture = findViewById(R.id.singleUser_profile_image);
        edit_profilePhoto_button = findViewById(R.id.Edit_photo_button);
        toUserQuestion = findViewById(R.id.toUserQuestions);

        showSingleUserAuth = FirebaseAuth.getInstance();
        currentUser = showSingleUserAuth.getCurrentUser();
        userEmail = currentUser.getEmail();
        //nowUser = getIntent().getStringExtra("showUser");
        System.out.println("users/"+userEmail.replace('.','&'));
        showSingleUserDatabaseRef = FirebaseDatabase.getInstance().getReference("users");
        myref = showSingleUserDatabaseRef.child(userEmail.replace('.','&'));
        System.out.println();
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);

                name_value.setText(user.getName());
                email_value.setText(user.getEmail());
                position_value.setText(user.getPosition());
                jerseynumber_value.setText(user.getJerseynumber());
                phoneNumber_value.setText(user.getPhoneNumber());
                location_value.setText(user.getLocation());
                bio_value.setText(user.getBio());
                System.out.println("@@@@@@@@@" + user.getDpURL());
                Glide.with(Showuser.this)
                        .load(user.getDpURL())
                        .into(profilePicture);

                if(userEmail.equals(user.getEmail())){
                    edit_profilePhoto_button.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        edit_profilePhoto_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toEditProfilePicture();
            }
        });

    }

    public void toEditProfilePicture(){
       /* Intent intent = new Intent(this,editUserPage.class);
        intent.putExtra("showUser","users/"+nowUser.replace('.','&'));
        startActivity(intent);*/
    }


}