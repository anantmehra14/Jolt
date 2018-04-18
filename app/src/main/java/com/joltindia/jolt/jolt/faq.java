package com.joltindia.jolt.jolt;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class faq extends AppCompatActivity {
    TextView tv;
    Intent i;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);
        String text = "1.  How to book a ride?\n" + "    You can book the ride through APP & website \n" +
                "2.  Who can invest?\n" + "    Anybody can invest in JOLT. For further details mail us at indiajoltElectric@gmail.com\n" +
                "3.  Do we need liscence to drive the vehicle?\n" + "    No, you dont need any liscence to operate the vehicle.\n" +
                "4.  Who will pay the parking charges\n" + "    The customer will pay the parking charges at his/her own expense.\n";
        tv = (TextView) findViewById(R.id.tv2);
        tv.setText(text);

        firebaseAuth = FirebaseAuth.getInstance();
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
                finish();
                Intent qrcode=new Intent(faq.this,qrcode.class);
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
                Toast.makeText(getApplicationContext(),"Faq Page", Toast.LENGTH_LONG).show();

                return true;
            case R.id.item6:
                finish();
                i= new Intent(this,queries.class);
                startActivity(i);
                return true;
            case R.id.item7:

                firebaseAuth.signOut();
                finish();
                Intent log=new Intent(faq.this,LoginActivity.class);
                startActivity(log);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}