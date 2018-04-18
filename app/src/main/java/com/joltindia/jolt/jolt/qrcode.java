package com.joltindia.jolt.jolt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class qrcode extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    ImageView image;
    Button button2;
    Button button3;
    TextView userid;
    int cond=0;

    ProgressDialog progressDialog ;

    String uid,name,college,phone,email,adhaar,qrgen;
    Intent i=getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);
        uid=user.getUid();

        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        image = (ImageView) findViewById(R.id.image);
        userid=(TextView)findViewById(R.id.uid);

        userid.setText(""+uid);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Fetching Data Please Wait...");
                progressDialog.show();

                FirebaseDatabase database = FirebaseDatabase.getInstance();


                final DatabaseReference myRef = database.getReference("USERS/"+uid);
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        name = "" + dataSnapshot.child("NAME").getValue();
                        college = "" + dataSnapshot.child("CollegeID").getValue();
                        phone = "" + dataSnapshot.child("PHONE").getValue();
                        email =""+user.getEmail();
                        adhaar=""+ dataSnapshot.child("Adhaar").getValue();
                        qrgen=" Name: "+name+"\n CollegeID: "+college+"\n Phone number: "+phone+"\n Aadhar number: "+adhaar+"\n Email: "+email;
                        cond=1;
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "NO SPACE AVAILABLE", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
//                Toast.makeText(getApplicationContext(),"Name"+name+" college"+college+" Phi"+phone+" Ad"+adhaar+"   Email"+email, Toast.LENGTH_LONG).show();
if(cond==0){
    Toast.makeText(getApplicationContext(),"Click on Retrieve First", Toast.LENGTH_LONG).show();
}
else {
    Bitmap bitmap = net.glxn.qrgen.android.QRCode.from(qrgen).bitmap();
    image.setImageBitmap(bitmap);
}

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
                finish();
                i= new Intent(this,home.class);
                startActivity(i);
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(),"QR Code", Toast.LENGTH_LONG).show();

                //Anchit
                return true;
            case R.id.item4:
                finish();
                i= new Intent(this,terms.class);
                startActivity(i);
                return true;
            case R.id.item5:
                finish();
                i= new Intent(this,faq.class);
                startActivity(i);
                return true;
            case R.id.item6:
                finish();
                i= new Intent(this,queries.class);
                startActivity(i);
                return true;
            case R.id.item7:

                firebaseAuth.signOut();
                finish();
                Intent log=new Intent(qrcode.this,LoginActivity.class);
                startActivity(log);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}