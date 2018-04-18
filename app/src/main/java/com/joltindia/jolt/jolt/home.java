package com.joltindia.jolt.jolt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class home extends AppCompatActivity {
    Intent i;
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;

    TextView book_pass;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String step1,step2,step3,jill;
    int flag=0;

    Button book,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Initialising");
        progressDialog.show();
        setContentView(R.layout.activity_home);

        if(!isConnected(home.this))
            buildDialog(home.this).show();
        else {

            setContentView(R.layout.activity_home);
        }

        progressDialog.setMessage("Please Wait");
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        user = firebaseAuth.getCurrentUser();

        jill=user.getUid();

        book_pass=(TextView)findViewById(R.id.book_pass);

        book_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qr=new Intent(home.this,qrcode.class);
                startActivity(qr);
            }
        });




        final DatabaseReference myRef = database.getReference("USERS/"+jill);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                step1 = dataSnapshot.child("A").getValue().toString();
                step2 = dataSnapshot.child("B").getValue().toString();
                step3 = dataSnapshot.child("C").getValue().toString();
                flag=1;
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Please Check Network Settings", Toast.LENGTH_LONG).show();
            }
        });






        book = (Button) findViewById(R.id.button_book);
        cancel = (Button) findViewById(R.id.button_cancel);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean emailVerified = user.isEmailVerified();
                if (emailVerified == false) {
                    Toast.makeText(getApplicationContext(),"Please Confirm email.Go to Queries.", Toast.LENGTH_LONG).show();
                }
                else if (emailVerified == true&&flag==1) {
                    if(step1.equals("N")){
                        Toast.makeText(getApplicationContext(),"Please finish registration", Toast.LENGTH_LONG).show();
                        Intent j= new Intent(home.this,step1.class);
                        startActivity(j);
                    }
                 /*  else if (step2.equals("N")){
                        Toast.makeText(getApplicationContext(),"Please finish registration", Toast.LENGTH_LONG).show();
                        Intent j= new Intent(home.this,step3.class);
                        startActivity(j);
                    }
                    else if (step3.equals("N")){
                        Toast.makeText(getApplicationContext(),"Please finish registration", Toast.LENGTH_LONG).show();
                        Intent j= new Intent(home.this,step4.class);
                        startActivity(j);
                    } */


                    else if(step1.equals("Y")){
                        Intent timings=new Intent(home.this, timings.class);
                        startActivity(timings);

                }}


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    new AlertDialog.Builder(home.this)
                            .setMessage("Are you sure you want to cancel?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    DatabaseReference cancel = database.getReference("Wait/List/"+jill);
                                    cancel.removeValue();
                                    Toast.makeText(getApplicationContext(),"    Booking Cancelled   ", Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();


            }
        });



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"Home", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Intent qrcode=new Intent(home.this,qrcode.class);
                Toast.makeText(getApplicationContext(),"Click on Retrieve First", Toast.LENGTH_LONG).show();
                startActivity(qrcode);
                //Anchit
                return true;
            case R.id.item4:
                i= new Intent(this,terms.class);
                startActivity(i);
                return true;
            case R.id.item5:

                i= new Intent(this,faq.class);
                startActivity(i);
                return true;
            case R.id.item6:

                i= new Intent(this,queries.class);
                startActivity(i);
                return true;
            case R.id.item7:
                firebaseAuth.signOut();
                finish();
                Intent log=new Intent(home.this,LoginActivity.class);
                startActivity(log);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to continue. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }



}