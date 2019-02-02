package com.example.plabon.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Register extends Activity {

    EditText password;
    EditText fullname;
    EditText position;
    EditText jerseynumber;
    EditText email;
    EditText Location;
    EditText phoneNumber;
    EditText Playerfoot;
    Button doneButton;
    Button cancel;
    TextView Teamtext;
    Spinner TeamSpinner;
    RadioButton visibilty,invisibility;
    TextView textjersey, textposition, textfoot;
    TextView textlocation;

    private DatabaseReference databaseReference,Teamref,addteam;
    private FirebaseAuth userAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Teamref = FirebaseDatabase.getInstance().getReference("Teams");
        addteam = FirebaseDatabase.getInstance().getReference("user_team");
        userAuthentication = FirebaseAuth.getInstance();

        password = findViewById(R.id.PasswordEditText);
        fullname = findViewById(R.id.PlayerNameEditText);
        position = findViewById(R.id.PlayerPositionEditText);
        jerseynumber = findViewById(R.id.PlayerNumberEditText);
        Location = findViewById(R.id.PlayerLocationEditText);
        email = findViewById(R.id.PlayerEmailEditText);
        Playerfoot = findViewById(R.id.PlayerFootEditText);
        phoneNumber = findViewById(R.id.PlayerPhoneEditText);
        doneButton = findViewById(R.id.idsubmitButton);
        cancel = findViewById(R.id.idcancelButton);
        Teamtext = findViewById(R.id.teamtextview);
        TeamSpinner = findViewById(R.id.teamspinner);
        visibilty =(RadioButton) findViewById(R.id.Visibility);
        invisibility = findViewById(R.id.InVisibility);

        invisibility.setChecked(true);

        textjersey = findViewById(R.id.textviewPreferredjersey);
        textposition = findViewById(R.id.textviewPreferredpos);
        textfoot = findViewById(R.id.textViewPreferredfoot);
        textlocation = findViewById(R.id.textViewlocation);
        Teamref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> teams = new ArrayList<String>();
                for (DataSnapshot TeamSnapshot: dataSnapshot.getChildren()) {
                    String teamName = TeamSnapshot.getValue(String.class);

                    teams.add(teamName);

                }
                ArrayAdapter<String> teamsAdapter = new ArrayAdapter<String>(Register.this, R.layout.myspinner, teams);
                teamsAdapter.setDropDownViewResource(R.layout.myspinner);
                TeamSpinner.setAdapter(teamsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Location.setVisibility(View.GONE);
        jerseynumber.setVisibility(View.GONE);
        position.setVisibility(View.GONE);
        Playerfoot.setVisibility(View.GONE);
        textjersey.setVisibility(View.GONE);
        textfoot.setVisibility(View.GONE);
        textposition.setVisibility(View.GONE);
        textlocation.setVisibility(View.GONE);
        Teamtext.setVisibility(View.GONE);
        TeamSpinner.setVisibility(View.GONE);

        invisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = (RadioButton) v;
                if(radioButton.isChecked())
                {
                    Location.setVisibility(View.GONE);
                    jerseynumber.setVisibility(View.GONE);
                    position.setVisibility(View.GONE);
                    Playerfoot.setVisibility(View.GONE);
                    textjersey.setVisibility(View.GONE);
                    textfoot.setVisibility(View.GONE);
                    textposition.setVisibility(View.GONE);
                    textlocation.setVisibility(View.GONE);
                    Teamtext.setVisibility(View.GONE);
                    TeamSpinner.setVisibility(View.GONE);
                    visibilty.setChecked(false);
                }
            }
        });

        visibilty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = (RadioButton) v;
                if (radioButton.isChecked()){
                    Location.setVisibility(View.VISIBLE);
                jerseynumber.setVisibility(View.VISIBLE);
                position.setVisibility(View.VISIBLE);
                Playerfoot.setVisibility(View.VISIBLE);
                textlocation.setVisibility(View.VISIBLE);
                textjersey.setVisibility(View.VISIBLE);
                textfoot.setVisibility(View.VISIBLE);
                textposition.setVisibility(View.VISIBLE);
                Teamtext.setVisibility(View.VISIBLE);
                TeamSpinner.setVisibility(View.VISIBLE);

                    invisibility.setChecked(false);
            }

            }
        });



        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayer();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,AuthActivity.class));
            }
        });
    }

    private void addPlayer() {
        final FirebaseUser hmmuser = userAuthentication.getCurrentUser();
        //System.out.println("asd sfg sfdg sdfg sd " + hmmuser.getEmail());

        final String spassword = password.getText().toString().trim();
        final String sfullname = fullname.getText().toString().trim();
        final String sposition ;
        final String sjerseynumber ;
        final String semail = email.getText().toString().trim();
        final String sLocation ;
        final String sfoot ;
        final String steam;

        final String sphoneNumber;

        if(TextUtils.isEmpty(phoneNumber.getText().toString().trim())) sphoneNumber = "null";
        else sphoneNumber = phoneNumber.getText().toString().trim();

       if(TeamSpinner.getSelectedItem().toString().equals("none")) steam = "null";
       else steam = TeamSpinner.getSelectedItem().toString();


        if(TextUtils.isEmpty(jerseynumber.getText().toString().trim())) sjerseynumber = "null";
        else sjerseynumber = jerseynumber.getText().toString().trim();

        if(TextUtils.isEmpty(Location.getText().toString().trim())) sLocation = "null";
        else sLocation = Location.getText().toString().trim();

        if(TextUtils.isEmpty(Playerfoot.getText().toString().trim())) sfoot = "null";
        else sfoot = Playerfoot.getText().toString().trim();

        if(TextUtils.isEmpty(position.getText().toString().trim())) sposition = "null";
        else sposition = position.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(semail).matches()){
            //Toast.makeText(this,"Please input Email Correctly",Toast.LENGTH_LONG).show();
            email.setError("Please input Email Correctly.");
            email.requestFocus();
            return;
        }
        if(spassword.length() < 8){
            //Toast.makeText(this,"Password has to be at least 8 chars",Toast.LENGTH_LONG).show();
            password.setError("Password has to be at least 8 chars");
            password.requestFocus();
            return;
        }


        if(TextUtils.isEmpty(spassword) || TextUtils.isEmpty(sfullname) || TextUtils.isEmpty(sposition) || TextUtils.isEmpty(sjerseynumber) || TextUtils.isEmpty(semail)){
            Toast.makeText(this, "Please fill out all the sections",Toast.LENGTH_LONG).show();
        }else{
            System.out.println("@@@@@@@@@@@" + semail + " " + spassword);
            userAuthentication.createUserWithEmailAndPassword(semail,spassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User user = new User(semail, sfullname,sposition,sjerseynumber,sphoneNumber,sLocation,steam,sfoot,"https://i.kym-cdn.com/entries/icons/original/000/003/619/ForeverAlone.jpg","false","Nothing yet.");
                                //com.example.plabon.myapplication.User user =
                                TeamUser teamUser = new TeamUser(sfullname, steam);
                                databaseReference.child(semail.replace('.','&')).setValue(user);
                                addteam.child(semail.replace('.','&')).setValue(teamUser);
                                FirebaseUser hmmttuser = userAuthentication.getCurrentUser();
                                hmmttuser.sendEmailVerification();


                                Toast.makeText(Register.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();

                                email.setText("");
                                password.setText("");
                                phoneNumber.setText("");
                                fullname.setText("");
                                position.setText("");
                                jerseynumber.setText("");

                                Intent intent = new Intent(Register.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                userAuthentication.signOut();
                                startActivity(intent);
                            }else{
                                if(task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(Register.this, "Email alredy taken.", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(Register.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
        }
    }

}

