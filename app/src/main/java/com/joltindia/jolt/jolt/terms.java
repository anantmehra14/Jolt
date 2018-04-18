package com.joltindia.jolt.jolt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class terms extends AppCompatActivity {
    TextView tv;
    Intent i;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        tv=(TextView)findViewById(R.id.tv2);
        String text;
        text="1. The customer must present his/her membership card at the time of ride confirmation. Also, the basic payment must be done in advance.\n" +
                "2. Drive safe. JOLT will not be responsible for any mishappenings to the customer during the ride.\n" +
                "3. Any damage caused to the JOLT bike during a ride is the responsibility of the customer. In such case, the customer will be charged for the same.\n" +
                "4. JOLT is not liable for paying any parking bills for the customer.\n" +
                "5. In case the JOLT bike is towed away due to parking it in unauthorised parking area, the customer will be charged for the same.\n" +
                "6. In case of a challan due to disobedience of the traffic rules, the customer will have to pay for the same.\n" +
                "7. Any commercial or illegal use of JOLT bikes is strictly prohibited.\n" +
                "8. Just the application for membership does not prove one’s eligibility for membership. Membership will be granted only after the documents have been completely verified by an authorised personal.\n" +
                "9. Membership details can be modified at any time by JOLT without any prior notice.\n" +
                "10. The vehicle shall be used strictly in accordance with the procedure prescribed by the manufacturer of the vehicle as mentioned in the vehicle’s manual.\n" +
                "11. Exceeding the time/distance limit will incur additional charges, as mentioned in the price list.\n" +
                "12. The number of passengers must not exceed the permissible number as specified in MVT Law.\n" +
                "13. In case of theft of the JOLT bike during the ride, the customer will be held liable for the same.";
        tv.setText(text);
        firebaseAuth = FirebaseAuth.getInstance();

    } public boolean onCreateOptionsMenu(Menu menu) {
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
                Intent qrcode=new Intent(terms.this,qrcode.class);
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
                Intent log=new Intent(terms.this,LoginActivity.class);
                startActivity(log);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
