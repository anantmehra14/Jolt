package com.joltindia.jolt.jolt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class fare extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    int fare;
    Intent i;
    int passengers,addd,mHour,mMinute;
    private FirebaseAuth firebaseAuth;
    String pickuptime;

    String dropofftime,date,mail,id;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fare);

        final java.util.Calendar c = java.util.Calendar.getInstance();
        mHour = c.get(java.util.Calendar.HOUR_OF_DAY);
        date =""+ c.get(Calendar.DATE)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR);


        Intent i=getIntent();
        passengers=i.getIntExtra("passengers",-1);
        pickuptime=i.getStringExtra("pickuptime");
        dropofftime=i.getStringExtra("dropofftime");
        tv1=(TextView)findViewById(R.id.textView4);
        tv2=(TextView)findViewById(R.id.textView6);
        tv3=(TextView)findViewById(R.id.pass);
        fare=i.getIntExtra("fare",-1);
        final String destination=i.getStringExtra("destination");
        tv1.setText(destination);
        confirm=(Button)findViewById(R.id.button2);
        tv2.setText("Rs"+" "+Integer.toString(fare));
        tv3.setText(Integer.toString(passengers));




        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent confirm=new Intent(fare.this,qrcode.class);

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                firebaseAuth= FirebaseAuth.getInstance();

                FirebaseUser user = firebaseAuth.getCurrentUser();
                mail =user.getEmail();
                id=user.getUid();
                DatabaseReference myRef = database.getReference("Wait/List");
                myRef.push().setValue(id);
                DatabaseReference m = database.getReference("Wait/List/"+id+"/Email");
                m.setValue(mail);
                DatabaseReference my = database.getReference("Wait/List/"+id+"/Passengers");
                my.setValue(passengers);
                DatabaseReference myr = database.getReference("Wait/List/"+id+"/PickUP");
                myr.setValue(pickuptime);
                DatabaseReference myrr = database.getReference("Wait/List/"+id+"/Date");
                myrr.setValue(date);
                DatabaseReference myre = database.getReference("Wait/List/"+id+"/DropOff");
                myre.setValue(dropofftime);
                DatabaseReference myref = database.getReference("Wait/List/"+id+"/Fare");
                myref.setValue(fare);

                DatabaseReference myrefs = database.getReference("Wait/List/"+id+"/Location");
                myrefs.setValue(destination);



                finish();
                Toast.makeText(fare.this, "Your ride has been successfully booked", Toast.LENGTH_SHORT).show();
                startActivity(confirm);





            }
        });
    } public boolean onCreateOptionsMenu(Menu menu) {
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
                finish();
                Intent qrcode=new Intent(fare.this,qrcode.class);
                Toast.makeText(getApplicationContext(),"Click on Retrieve First", Toast.LENGTH_LONG).show();
                startActivity(qrcode);
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
                Intent log=new Intent(fare.this,LoginActivity.class);
                startActivity(log);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

