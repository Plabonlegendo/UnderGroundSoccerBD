package com.example.plabon.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ShowTeam extends Activity {
    private static final String TAG = "ShowTeam";

    //add Firebase Database stuff
    private FirebaseAuth userAuthentication;
    private FirebaseUser currentuser;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef,Teamref,addteam;
    private DatabaseReference myRef2;
    private  String userID;
    private TextView textView;
    private Spinner showteamspinner;
    private Button createteam;
    private Button updateteam;
    String finalTeamName="";
    ArrayList<String> array  = new ArrayList<>();
    ListView listView;
    String Useremail;

    private ListView mListView;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_team);
        listView=(ListView) findViewById(R.id.listview);
        bundle = getIntent().getExtras();

        Log.d(TAG,"Dhuksi\n");

        mAuth = FirebaseAuth.getInstance();
        userAuthentication = FirebaseAuth.getInstance();
        currentuser = userAuthentication.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        Useremail = currentuser.getEmail();
        myRef = mFirebaseDatabase.getReference("user_team");
        FirebaseUser user = mAuth.getCurrentUser();
        showteamspinner = findViewById(R.id.showteamspinner);
        createteam = findViewById(R.id.createnewteambutton);
        updateteam = findViewById(R.id.updateteambutton);
        Teamref = FirebaseDatabase.getInstance().getReference("Teams");
        addteam = FirebaseDatabase.getInstance().getReference("user_team").child(Useremail.replace('.','&')).child("teamname");

        userID = user.getUid();


        createteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowTeam.this,Teamform.class));
            }
        });

        updateteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String steam;

                if(showteamspinner.getSelectedItem().toString().equals("none")) steam = "";
                else steam = showteamspinner.getSelectedItem().toString();

                addteam.setValue(steam);
                Intent intent = new Intent(ShowTeam.this,MainMenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


            }
        });

        Teamref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> teams = new ArrayList<String>();
                for (DataSnapshot TeamSnapshot: dataSnapshot.getChildren()) {
                    String teamName = TeamSnapshot.child("teamname").getValue(String.class);

                    teams.add(teamName);

                }
                ArrayAdapter<String> teamsAdapter = new ArrayAdapter<String>(ShowTeam.this, R.layout.teamspinner, teams);
                teamsAdapter.setDropDownViewResource(R.layout.myspinner);
                showteamspinner.setAdapter(teamsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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


    }



    private void showData(DataSnapshot dataSnapshot) {

        Log.d(TAG, "show data te dhuksi");
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

            //text=findViewById(R.id.textViewTeamName);
            if(achi.equals(eemail)){
                //text.setText(str);
                finalTeamName=str;
                break;
            }

        }

        String myTeamName = finalTeamName;
        Log.d(TAG, "showData: name:*" +myTeamName+"*");

        if(myTeamName.length()==0) {
            textView=findViewById(R.id.myText);
            textView.setText("You are not a member of any team");
        }
        else{
            textView=findViewById(R.id.myText);
            textView.setText("Squad of "+myTeamName);

            myRef = mFirebaseDatabase.getReference("user_team");

            for(DataSnapshot ds : dataSnapshot.getChildren()){

                String str = (String)ds.child("teamname").getValue(); //set the name
                if(str.equals(myTeamName)){

                    String achi= (String)ds.child("name").getValue();
                    array.add(achi);
                    Log.d(TAG, "showData: name:*" +achi+"*");
                }

            }

            ArrayAdapter adapter = new ArrayAdapter(this,R.layout.teamadapt,array);
            listView.setAdapter(adapter);
        }

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