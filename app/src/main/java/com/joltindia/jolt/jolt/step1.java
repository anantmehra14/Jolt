package com.joltindia.jolt.jolt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class step1 extends AppCompatActivity {
    String nam,number,adhar,college;
    EditText name,mobile,aadhar,college_id;

    FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("USERS");

    FirebaseAuth firebaseAuth;


    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step1);

        firebaseAuth = FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        uid=user.getUid();

        myRef.push().setValue(uid);
        DatabaseReference pa = database.getReference("USERS/"+uid+"/A");
        pa.setValue("N");

        DatabaseReference pag = database.getReference("USERS/"+uid+"/B");
        pag.setValue("Y");

        DatabaseReference page = database.getReference("USERS/"+uid+"/C");
        page.setValue("Y");


        Button step1=(Button)findViewById(R.id.button3);
        name=(EditText)findViewById(R.id.edit3);
        mobile=(EditText)findViewById(R.id.edit5);
        college_id=(EditText)findViewById(R.id.edit4);
        aadhar=(EditText)findViewById(R.id.edit6);





        step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register(); // to validate before goint to next page






            }
        });


    }
    public void register() {
        initialize();
        if(!validate()) {
            Toast.makeText(this,"Please fill the required fields",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(step1.this,"uploading data",Toast.LENGTH_LONG).show();

            DatabaseReference m = database.getReference("USERS/"+uid+"/NAME");
            m.setValue(nam);
            DatabaseReference my = database.getReference("USERS/"+uid+"/PHONE");
            my.setValue(number);
            DatabaseReference myr =
                    database.getReference("USERS/"+uid+"/CollegeID");
            myr.setValue(college);
            DatabaseReference myre =
                    database.getReference("USERS/"+uid+"/Adhaar");
            myre.setValue(adhar);

            DatabaseReference one =
                    database.getReference("USERS/"+uid+"/A");
            one.setValue("Y");
            Toast.makeText(step1.this,"uploaded data",Toast.LENGTH_LONG).show();

            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(step1.this,"Please confirm email verification and then login in",Toast.LENGTH_LONG).show();
                        firebaseAuth.signOut();
                        finish();
                        Intent log=new Intent(step1.this,LoginActivity.class);
                        startActivity(log);
                    }

                }
            });

        }

    }
    public void initialize() {
        nam=name.getText().toString().trim();
        number=mobile.getText().toString().trim();
        college=college_id.getText().toString().trim();
        adhar=aadhar.getText().toString().trim();

    }

    public boolean validate() {
        boolean valid=true;
        if(nam.isEmpty() || nam.length()>32) {

            name.setError("Please enter a valid name");
            valid = false;
        }


        if(college.isEmpty() || college.length()!=8) {

            college_id.setError("Please enter a valid College id No.");
            valid = false;


        }

        if(number.isEmpty() || number.length()!=10) {

            mobile.setError("Please enter a valid number");
            valid = false;


        }

        if(adhar.isEmpty() || adhar.length()>12) {

            aadhar.setError("Please enter a valid Aadhar no.");
            valid = false;


        }
        return valid;


    }
}