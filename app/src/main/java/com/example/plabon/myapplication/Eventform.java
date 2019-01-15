package com.example.plabon.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

public class Eventform extends Activity {

    EditText Eventname;
    EditText Hostname;
    EditText HostEmail;
    EditText Eventlocation;
    EditText phonenumber;
    EditText Startingtime;
    EditText Startingdate;
    EditText Endingtime;
    EditText Endingdate;
    EditText passfield;

    Button submit;
    Button cancel;

    String startdate;
    String enddate;
    String starttime;
    String endtime;


    private TextView eReminderTime,eendReminderTime;
    private TextView mDisplayDate;
    private TextView mendDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mendDateSetListener;

    private DatabaseReference databaseReference;
    private FirebaseAuth userAuthentication;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);

        databaseReference = FirebaseDatabase.getInstance().getReference("events");
        userAuthentication = FirebaseAuth.getInstance();


        Eventname = findViewById(R.id.EventNameEditText);
       // Hostname = findViewById(R.id.hostNameEditText);
        HostEmail = findViewById(R.id.EventEmailEditText);
        Eventlocation = findViewById(R.id.EventLocationEditText);
        phonenumber = findViewById(R.id.EventPhoneEditText);
        eReminderTime = findViewById(R.id.Starttime);
        //Startingdate = findViewById(R.id.EventStartDateEditText);
        //Endingdate = findViewById(R.id.EventEndDateEditText);
        eendReminderTime = findViewById(R.id.endtime);

        passfield = findViewById(R.id.eventPasswordEditText);
        submit = findViewById(R.id.submitButton);
        cancel = findViewById(R.id.cancelButton);

        mendDisplayDate = findViewById(R.id.menddate);
        mDisplayDate =  findViewById(R.id.mstartdate);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Eventform.this, MainMenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Eventform.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                startdate = month + "/" + day + "/" + year;
                mDisplayDate.setText(startdate);
            }
        };

        mendDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Eventform.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mendDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mendDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                enddate = month + "/" + day + "/" + year;
                mendDisplayDate.setText(enddate);
            }
        };

        eReminderTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Eventform.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        eReminderTime.setText( selectedHour + ":" + selectedMinute);
                        starttime = eReminderTime.getText().toString();
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        eendReminderTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Eventform.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        eendReminderTime.setText( selectedHour + ":" + selectedMinute);
                        endtime = eendReminderTime.getText().toString();
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addevent();
            }
        });
    }
    public void addevent(){
        final FirebaseUser hmmuser = userAuthentication.getCurrentUser();

        String Email = "";
        if(hmmuser != null)
        {
            Email= userAuthentication.getCurrentUser().getEmail();
        }

        final String seventname = Eventname.getText().toString().trim();
       // final String shostname = Hostname.getText().toString().trim();
        final String shostemail = HostEmail.getText().toString().trim();
        final String seventlocation = Eventlocation.getText().toString().trim();
        final String sphonenumber ;
        final String sstartingtime = starttime;
        final String sstartingdate = startdate;
        final String sendingdate = enddate;
        final String sendingtime = endtime;
        //final String spassfield = passfield.getText().toString().trim();

        if(TextUtils.isEmpty(phonenumber.getText().toString().trim())) sphonenumber = null;
        else sphonenumber = phonenumber.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(shostemail).matches()) {
            //Toast.makeText(this,"Please input Email Correctly",Toast.LENGTH_LONG).show();
            HostEmail.setError("Please input Email Correctly.");
            HostEmail.requestFocus();
            return;
        }

        if( TextUtils.isEmpty(sendingdate) || TextUtils.isEmpty(sstartingdate) || TextUtils.isEmpty(shostemail) || TextUtils.isEmpty(seventlocation) ||  TextUtils.isEmpty(sstartingtime) || TextUtils.isEmpty(seventname) || TextUtils.isEmpty((shostemail))){
            Toast.makeText(this, "Please fill out all the sections",Toast.LENGTH_LONG).show();
            Log.d(TAG, "onDateSet: mm/dd/yyy: "+"   " + seventlocation+ "    "+"    "+sendingdate+"    "+ sstartingdate +"    "+sstartingtime+"   " + seventname + "/"  +shostemail+ "/" +sendingtime);

        }

        else
        {
            event Event = new event(seventname,shostemail,seventlocation,sphonenumber,sstartingtime,sstartingdate,sendingtime,sendingdate,"nothing yet","nothing yet","nothing yet","nothing yet","nothing yet");
            databaseReference.child(Email.replace('.','&')).push().setValue(Event);

        }

    }
}
