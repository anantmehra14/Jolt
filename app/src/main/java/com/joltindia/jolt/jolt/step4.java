/*  package com.joltindia.jolt.jolt;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class step4 extends AppCompatActivity implements View.OnClickListener{


    //firebase auth object
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;
    boolean emailVerified;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //a constant to track the file chooser intent
    private static final int PICK_IMAGE_REQUEST = 234;
    private double re;
    //Buttons
    private Button buttonChoose;
    private Button buttonUpload;
    private Button step4;

    //ImageView
    private ImageView imageView;

    //a Uri object to store file path
    private Uri filePath;

    //firebase storage reference
    private StorageReference storageReference;

    //view objects
    private TextView textViewUserEmail;

    String jill, jack;
    int u=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step4);
        //getting views from layout
        buttonChoose = (Button) findViewById(R.id.buttonChoosed);
        buttonUpload = (Button) findViewById(R.id.buttonUploadd);
        step4=(Button) findViewById(R.id.Button6);
        imageView = (ImageView) findViewById(R.id.imageaadhar);

        //attaching listener
        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
        step4.setOnClickListener(this);

        //getting firebase storage reference
        storageReference = FirebaseStorage.getInstance().getReference();

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        user = firebaseAuth.getCurrentUser();
        jill=user.getUid();
        jack =user.getEmail();
        emailVerified= user.isEmailVerified();
        //getting current user
        //initializing views
        //adding listener to button
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //this method will upload the file
    private void uploadFile() {
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            StorageReference riversRef = storageReference.child("images/"+jack+"/Aadhar");
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                            u=1;

                            DatabaseReference one =
                                    database.getReference("USERS/"+jill+"/C");
                            one.setValue("Y");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            @SuppressWarnings("VisibleForTests") double re=taskSnapshot.getBytesTransferred();
                            @SuppressWarnings("VisibleForTests") double rem=taskSnapshot.getBytesTransferred();
                            double progress = (100.0 * re) / rem;

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }

    @Override
    public void onClick(View view) {
        //if logout is pressed

        if (view == buttonChoose) {
            showFileChooser();
        }
        //if the clicked button is upload
        else if (view == buttonUpload) {
            uploadFile();
        }
        else if(view==step4){


            if(u==1){
                if (emailVerified == false) {
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(step4.this,"Please confirm email verification and then login in",Toast.LENGTH_LONG).show();
                                firebaseAuth.signOut();
                                finish();
                                Intent log=new Intent(step4.this,LoginActivity.class);
                                startActivity(log);
                            }

                        }
                    });

                }
                else if (emailVerified == true) {
                    Intent log=new Intent(step4.this,home.class);
                    startActivity(log);
                }


            }

            else if (u==0){
                Toast.makeText(getApplicationContext(), "Upload Image!!!", Toast.LENGTH_LONG).show();
            }
        }

    }
}

//public void send mail


*/