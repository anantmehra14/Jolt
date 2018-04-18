package com.joltindia.jolt.jolt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class hotspot extends AppCompatActivity {
    Intent i;
    int fare;
    String destination;
    int passengers,add;
    String pickuptime;
    String dropofftime,date;
    String faree;
    //int a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspot);


        i= getIntent();
        passengers=i.getIntExtra("passengers",-1);
        pickuptime=i.getStringExtra("pickuptime");
        dropofftime=i.getStringExtra("dropofftime");
        faree=i.getStringExtra("fare");
        add=i.getIntExtra("add1",-1);



    }


    public void onButtonClicked(View view) {

        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        int id = rg.getCheckedRadioButtonId();
        switch (id) {
            case R.id.r1:

                i=new Intent(getApplicationContext(),fare.class);
                fare=33+add+Integer.parseInt(faree);
                i.putExtra("fare",fare);

                destination="Mc"+" " +" Donalds";
                i.putExtra("destination",destination);
                i.putExtra("passengers",passengers);
                i.putExtra("pickuptime",pickuptime);
                i.putExtra("dropofftime",dropofftime);
                i.putExtra("date",date);
                startActivity(i);
                break;
            case R.id.r2:
                i=new Intent(getApplicationContext(),fare.class);
                fare=29+add+Integer.parseInt(faree);

                destination="Ansal"+" "+" Plaza";
                i.putExtra("destination",destination);
                i.putExtra("passengers",passengers);
                i.putExtra("pickuptime",pickuptime);
                i.putExtra("dropofftime",dropofftime);
                i.putExtra("date",date);
                i.putExtra("fare",fare);
                startActivity(i);
                break;
            case R.id.r3:
                i=new Intent(getApplicationContext(),fare.class);
               fare=29+add+Integer.parseInt(faree);

                destination="Sec-23"+" "+" Market";
                i.putExtra("destination",destination);
                i.putExtra("passengers",passengers);
                i.putExtra("pickuptime",pickuptime);
                i.putExtra("dropofftime",dropofftime);
                i.putExtra("date",date);
                i.putExtra("fare",fare);
                startActivity(i);

                break;
            case R.id.r4:i=new Intent(getApplicationContext(),fare.class);
                fare=29+add+Integer.parseInt(faree);

                destination="Green"+" "+"Chick"+" "+ "Chop";
                i.putExtra("destination",destination);
                i.putExtra("passengers",passengers);
                i.putExtra("pickuptime",pickuptime);
                i.putExtra("dropofftime",dropofftime);
                i.putExtra("date",date);
                i.putExtra("fare",fare);
                startActivity(i);
                break;
            case R.id.r5:
                i=new Intent(getApplicationContext(),fare.class);
                fare=29+add+Integer.parseInt(faree);

                destination="Sec-22"+" "+"Market";
                i.putExtra("destination",destination);
                i.putExtra("passengers",passengers);
                i.putExtra("pickuptime",pickuptime);
                i.putExtra("dropofftime",dropofftime);
                i.putExtra("date",date);
                i.putExtra("fare",fare);
                startActivity(i);

                break;
            case R.id.r6:
                i=new Intent(getApplicationContext(),fare.class);
                fare=69+add+Integer.parseInt(faree);

                destination="DLF"+" "+" Cyberhub";
                i.putExtra("destination",destination);
                i.putExtra("passengers",passengers);
                i.putExtra("pickuptime",pickuptime);
                i.putExtra("dropofftime",dropofftime);
                i.putExtra("date",date);
                i.putExtra("fare",fare);
                startActivity(i);
                break;
            case R.id.r7:
                i=new Intent(getApplicationContext(),fare.class);
               fare=29+add+Integer.parseInt(faree);

                destination="Pizza"+" "+" Hut";
                i.putExtra("destination",destination);
                i.putExtra("passengers",passengers);
                i.putExtra("pickuptime",pickuptime);
                i.putExtra("dropofftime",dropofftime);
                i.putExtra("date",date);
                i.putExtra("fare",fare);
                startActivity(i);

                break;
            case R.id.r8:
                i=new Intent(getApplicationContext(),fare.class);
                fare=49+add+Integer.parseInt(faree);
                destination="Burger king (MG Road)";
                i.putExtra("destination",destination);
                i.putExtra("passengers",passengers);
                i.putExtra("pickuptime",pickuptime);
                i.putExtra("dropofftime",dropofftime);
                i.putExtra("date",date);
                i.putExtra("fare",fare);
                startActivity(i);
                break;

            case R.id.r9:
                i=new Intent(getApplicationContext(),fare.class);
                fare=29+add+Integer.parseInt(faree);
                destination="Other";
                i.putExtra("destination",destination);
                i.putExtra("passengers",passengers);
                i.putExtra("pickuptime",pickuptime);
                i.putExtra("dropofftime",dropofftime);
                i.putExtra("date",date);
                i.putExtra("fare",fare);
                startActivity(i);
                break;




            default:
                Toast.makeText(getApplicationContext(),"enter destination",Toast.LENGTH_LONG).show();
        }



    }
}
