package com.joltindia.jolt.jolt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    String email;
    EditText email_id;
    EditText passwords;


    ProgressDialog progressDialog;




    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        if(!isConnected(LoginActivity.this))
            buildDialog(LoginActivity.this).show();
        else {

            setContentView(R.layout.activity_login);
        }

        progressDialog = new ProgressDialog(this);

        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the objects getcurrentuser method is not null
        //means user is already logged in
        if (firebaseAuth.getCurrentUser() != null) {
            //close this activity
            finish();
            //opening profile activity
            //Intent start=new Intent(LoginActivity.this,book.class);
            //startActivity(start);
            startActivity(new Intent(getApplicationContext(), home.class));
        }


       // ScrollingImageView scrollingBackground = (ScrollingImageView) findViewById(R.id.scrolling_background);

        //scrollingBackground.start();


        Button get_started = (Button) findViewById(R.id.button2);
        Button login = (Button) findViewById(R.id.button1);
        email_id = (EditText) findViewById(R.id.editText1);
        passwords = (EditText) findViewById(R.id.editText2);
        TextView forgot = (TextView) findViewById(R.id.forgot);


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = email_id.getText().toString();

                if(email.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter your email-id", Toast.LENGTH_SHORT).show();
                }
                else{

                FirebaseAuth auth = FirebaseAuth.getInstance();
                    progressDialog.setMessage("Checking Please Wait...");
                    progressDialog.show();


                    auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                    progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Sent Mail", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }}
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();



            }
        });


        get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Checking Please Wait...");
                progressDialog.show();
                Intent step1 = new Intent(v.getContext(), step2.class);
                progressDialog.dismiss();
                startActivity(step1);
            }
        });


    }
    public void register() {
        initialize();
        if(!validate()) {
            Toast.makeText(this,"Please fill the required fields",Toast.LENGTH_SHORT).show();

        }
        else{
            userLogin();

        }

    }
    public void initialize() {
        email = email_id.getText().toString().trim();
    }


    public boolean validate() {
        boolean valid=true;
        if(email.isEmpty() ||!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_id.setError("Enter a valid email id");
            valid=false;

        }

        return valid;

    }



    private void userLogin(){
        String email = email_id.getText().toString().trim();
        String password  = passwords.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Checking Please Wait...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), home.class));
                        }
                        if(!(task.isSuccessful())){
                            //start the profile activity
                            Toast.makeText(LoginActivity.this,"Email/Password incorrect",Toast.LENGTH_LONG).show();

                        }
                    }
                });

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
