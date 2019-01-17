package com.example.plabon.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Team extends Activity {
    private static final String TAG = "Team";

    //add Firebase Database stuff
    private FirebaseAuth userAuthentication;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private  String userID;
    private TextView text;
    private String finalTeamName="";


    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d(TAG,"Dhuksi\n");

        mAuth = FirebaseAuth.getInstance();
        userAuthentication = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("user_team");
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    //toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        setContentView(R.layout.activity_team_name);

    }

    private void showData(DataSnapshot dataSnapshot) {

        final FirebaseUser hmmuser = userAuthentication.getCurrentUser();
        Log.d(TAG, "showData: user: " + hmmuser);
        String eemail = "";
        if(hmmuser != null)
        {
            eemail= (String)hmmuser.getEmail();
        }
        Log.d(TAG, "showData: eemail: " + eemail);
        for(int i=0; i<eemail.length();i+=1)
        {
            if(eemail.charAt(i)=='.') {
                char[] chars = eemail.toCharArray();
                chars[i] = '&';
                eemail="";
                eemail = String.valueOf(chars);
            }
        }
        Log.d(TAG, "showData: eemail:*" + eemail+"*");

        for(DataSnapshot ds : dataSnapshot.getChildren()){

            String str = (String)ds.child("teamname").getValue(); //set the name

            //display all the information
            Log.d(TAG, "showData: name:*" + str+"*");
            String achi = (String)ds.getKey();
            Log.d(TAG, "showData: achi:*" + achi+"*");

            text=findViewById(R.id.textViewTeamName);
            if(achi.equals(eemail)){
                text.setText(str);
                finalTeamName=str;
            }

        }
    }

    public void showTeamPlayers(View view){
        Intent i=new Intent(this,ShowTeam.class);
        i.putExtra("haha",finalTeamName);
        startActivity(i);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}