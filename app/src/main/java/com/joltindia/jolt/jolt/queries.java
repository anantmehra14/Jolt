package com.joltindia.jolt.jolt;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class queries extends AppCompatActivity implements View.OnClickListener {
    //firebase auth object
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;

    private TextView textViewUserEmail;
    Button buttonLogout;
    Button newemail;
    private TextView veri;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries);
        //getting views from layout
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        user = firebaseAuth.getCurrentUser();
        //getting current user
        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.button3);

        newemail = (Button) findViewById(R.id.verif);

        veri = (TextView) findViewById(R.id.verify);
        //adding listener to button
        textViewUserEmail.setText("Welcome " + user.getEmail());

        boolean emailVerified = user.isEmailVerified();
        if (emailVerified == false) {
            veri.setText(veri.getText() + ":  NO");
        } else if (emailVerified == true) {
            veri.setText(veri.getText() + ":  YES");
        }
        buttonLogout.setOnClickListener(this);

        newemail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if (view == newemail) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(queries.this, "email sent", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
            firebaseAuth.signOut();
            //closing activity
            finish();
            Intent login_page=new Intent(queries.this,LoginActivity.class);
            startActivity(login_page);

        }

        if (view == buttonLogout) {
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            Intent login_page=new Intent(queries.this,LoginActivity.class);
            startActivity(login_page);
            //starting login activity
            //Intent to login in page

        }
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
                i= new Intent(this,home.class);
                startActivity(i);
                return true;
            case R.id.item3:
                Intent qrcode=new Intent(queries.this,qrcode.class);
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
                Toast.makeText(getApplicationContext(),"Queries Page", Toast.LENGTH_LONG).show();
                return true;

            case R.id.item7:
                firebaseAuth.signOut();
                finish();
                Intent log=new Intent(queries.this,LoginActivity.class);
                startActivity(log);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}