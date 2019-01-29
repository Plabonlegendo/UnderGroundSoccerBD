package com.example.plabon.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class OngoingEvents extends Activity {
    private static final String TAG = "OngoingEvents";

    //add Firebase Database stuff
    private FirebaseAuth userAuthentication;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private DatabaseReference myRef2;
    private  String userID;
    private TextView textView;
    public String currTime;
    ArrayList<EventInfo> array  = new ArrayList<>();
    ListView listView;

    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_events);
        calculateCurrentTime();

        bundle = getIntent().getExtras();

        Log.d(TAG,"Dhuksi\n");

        mAuth = FirebaseAuth.getInstance();
        userAuthentication = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("myevent");
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


    }



    private void showData(DataSnapshot dataSnapshot) {

        Log.d(TAG, "show data te dhuksi");


        for(DataSnapshot ds : dataSnapshot.getChildren()){

            String startingDate = (String)ds.child("startingdate").getValue();
            String startingTime = (String)ds.child("startingtime").getValue();
            String endingDate = (String)ds.child("endingdate").getValue();
            String endingTime = (String)ds.child("endingtime").getValue();
            String bestAttacker;
            String bestDefender;
            String bestGoalkeeper;
            String bestMidfielder;
            String bestPlayer;
            String eventEmail;
            String eventLocation;
            String eventName;
            String phoneNumber;

            if(dateChecker(startingDate, startingTime, endingDate, endingTime)==1){
                Log.d(TAG,"milse");
                bestAttacker = (String)ds.child("bestattacker").getValue();
                bestDefender = (String)ds.child("bestdefender").getValue();
                bestGoalkeeper = (String)ds.child("bestgoalkeeper").getValue();
                bestMidfielder = (String)ds.child("bestmidfielder").getValue();
                bestPlayer = (String)ds.child("bestplayer").getValue();
                eventEmail = (String)ds.child("eventEmail").getValue();
                eventLocation = (String)ds.child("eventlocation").getValue();
                eventName = (String)ds.child("eventname").getValue();
                phoneNumber = (String)ds.child("phonenumber").getValue();

                EventInfo eventInfo = new EventInfo(bestAttacker, bestDefender, bestGoalkeeper, bestMidfielder, bestPlayer,
                        endingDate, endingTime, eventEmail, eventLocation, eventName, phoneNumber, startingDate, startingTime);

                array.add(eventInfo);

            }

        }


        listView=(ListView) findViewById(R.id.listview);

        MyAdapter adapter = new MyAdapter(this, array);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(OngoingEvents.this, "Hello", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(OngoingEvents.this, OngoingEventDetail.class);
                intent.putExtra("bestAttacker", array.get(position).getBestAttacker());
                intent.putExtra("bestDefender", array.get(position).getBestDefender());
                intent.putExtra("bestGoalkeeper", array.get(position).getBestGoalkeeper());
                intent.putExtra("bestMidfielder", array.get(position).getBestMidfielder());
                intent.putExtra("bestPlayer", array.get(position).getBestPlayer());
                intent.putExtra("endingDate", array.get(position).getEndingDate());
                intent.putExtra("endingTime", array.get(position).getEndingTime());
                intent.putExtra("eventEmail", array.get(position).getEventEmail());
                intent.putExtra("eventLocation", array.get(position).getEventLocation());
                intent.putExtra("eventName", array.get(position).getEventName());
                intent.putExtra("phoneNumber", array.get(position).getPhoneNumber());
                intent.putExtra("startingDate", array.get(position).getStartingDate());
                intent.putExtra("startingTime", array.get(position).getStartingTime());

                startActivity(intent);

            }
        });

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

    public int dateChecker(String startingDate, String startingTime, String endingDate, String endingTime){

        int value = 0;
        int firstSlash=-1, secondSlash=-1, firstColon=-1;
        String myTime="";
        String myTime2="";
        for(int i=0;i<startingDate.length();i++){
            if(startingDate.charAt(i)=='/'){
                if(firstSlash==-1) firstSlash=i;
                else secondSlash=i;
            }
        }
        for(int i=secondSlash+3;i<startingDate.length();i++){
            myTime+=startingDate.charAt(i);
        }
        if(firstSlash==1){
            myTime+='0';
            myTime+=startingDate.charAt(firstSlash-1);
        }
        else{
            myTime+=startingDate.charAt(firstSlash-2);
            myTime+=startingDate.charAt(firstSlash-1);
        }
        if(secondSlash-firstSlash==2){
            myTime+='0';
            myTime+=startingDate.charAt(secondSlash-1);
        }
        else{
            myTime+=startingDate.charAt(secondSlash-2);
            myTime+=startingDate.charAt(secondSlash-1);
        }

        for(int i=0;i<startingTime.length();i++){
            if(startingTime.charAt(i)==':'){
                if(firstColon==-1) firstColon=i;
                break;
            }
        }
        for(int i=0;i<firstColon;i++) myTime+=startingTime.charAt(i);
        for(int i=firstColon+1;i<startingTime.length();i++) myTime+=startingTime.charAt(i);
        if(startingTime.length()-firstColon==2) myTime+="0";
        myTime+="00";

        firstSlash=-1;
        secondSlash=-1;
        firstColon=-1;
        for(int i=0;i<endingDate.length();i++){
            if(endingDate.charAt(i)=='/'){
                if(firstSlash==-1) firstSlash=i;
                else secondSlash=i;
            }
        }
        for(int i=secondSlash+3;i<endingDate.length();i++){
            myTime2+=endingDate.charAt(i);
        }
        if(firstSlash==1){
            myTime2+='0';
            myTime2+=endingDate.charAt(firstSlash-1);
        }
        else{
            myTime2+=endingDate.charAt(firstSlash-2);
            myTime2+=endingDate.charAt(firstSlash-1);
        }
        if(secondSlash-firstSlash==2){
            myTime2+='0';
            myTime2+=endingDate.charAt(secondSlash-1);
        }
        else{
            myTime2+=endingDate.charAt(secondSlash-2);
            myTime2+=endingDate.charAt(secondSlash-1);
        }

        for(int i=0;i<endingTime.length();i++){
            if(endingTime.charAt(i)==':'){
                if(firstColon==-1) firstColon=i;
                break;
            }
        }
        for(int i=0;i<firstColon;i++) myTime2+=endingTime.charAt(i);
        for(int i=firstColon+1;i<endingTime.length();i++) myTime2+=endingTime.charAt(i);
        if(endingTime.length()-firstColon==2) myTime2+="0";
        myTime2+="00";

        int compare = myTime.compareTo(currTime);
        int compare2 = myTime2.compareTo(currTime);
        Log.d(TAG,"startingTime: "+myTime+ " endingTime: "+myTime2+ " "+ compare + " "+ compare2);

        //if(myTime.compareTo(curTime) <0 && myTime2.compareTo(curTime)>0) value=1;
        //else value = 2;
        if(compare<0 && compare2>0) value=1;
        else value=4;
        return value;
    }

    public void calculateCurrentTime(){

        Calendar cal = Calendar. getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        currTime=dateFormat.format(date);
        Log.d(TAG, "Current time of the day using Calendar - 24 hour format: "+ currTime);
    }



    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}