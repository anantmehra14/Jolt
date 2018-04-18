package com.joltindia.jolt.jolt;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class timings extends AppCompatActivity {

    String time1,time2;
    int passengers;
    TextView tim1,tim2;
    ImageButton b1,b2;
    int add;

    Button b4;

    int minutes,total,fare,h1,h2,m1,m2,t1,t2,k;
    int check1,check2=0,check3=0,check4;
    int mHour,Hour;
    int mMinute,Minute;
    String fare1,time ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timings);

        //et_show_date_time = (EditText) findViewById(R.id.ed1);
        b1 = (ImageButton) findViewById(R.id.b1);
        b2 = (ImageButton) findViewById(R.id.b2);

        b4 = (Button) findViewById(R.id.b4);
        tim1= (TextView)findViewById(R.id.tv3);
        tim2= (TextView)findViewById(R.id.tv5);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                check1=1;
                timePicker(1);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                check2=1;
                timePicker(2);

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
                int id = rg.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.r1:
                        add = 0;
                        check4=1;
                        passengers=1;
                        break;
                    case R.id.r2:
                        add = 10;
                        check4=1;
                        passengers=2;
                        break;
                }



                if(check1==1&&check2==1&&check4==1) {
                    Intent i = new Intent(getApplicationContext(), hotspot.class);
                    i.putExtra("add1", add);

                    i.putExtra("passengers",passengers);
                    i.putExtra("pickuptime",time1);
                    i.putExtra("dropofftime",time2);
                    minutes=t2-t1;

                    fare=0;
                    if(mHour>=h1&&mMinute>m1||mHour>=h2&&mMinute>=m2)
                    {
                     Toast.makeText(timings.this,"Enter correct time",Toast.LENGTH_SHORT).show();
                    }
                    else
                    { if(h2>h1){
                        if(minutes>35) {

                            k = minutes - 35;
                            total = ((k / 3) * 2);

                            fare = fare + total;
                            fare1= Integer.toString(fare);
                            i.putExtra("fare",fare1);
                            startActivity(i);
                        }
                    else
                        {
                            fare1= Integer.toString(fare);
                            i.putExtra("fare",fare1);
                            startActivity(i);}}
                    else if((h1>=h2&&m1>=m2)||(h1>h2&&m1<=m2))
                    {
                        Toast.makeText(timings.this,"Enter correct time",Toast.LENGTH_SHORT).show();
                    }

                    else if(h1==h2) {

                        if(m2>=m1){
                            if(minutes>35) {

                                k = minutes - 35;
                                total = ((k / 3) * 2);

                                fare = fare + total;
                                fare1= Integer.toString(fare);
                                i.putExtra("fare",fare1);
                                startActivity(i);
                            }
                            else{
                                fare1= Integer.toString(fare);
                                i.putExtra("fare",fare1);
                                startActivity(i);
                            }

                        }
                        else if(m1>=m2) {
                            Toast.makeText(timings.this, "Enter correct time", Toast.LENGTH_SHORT).show();
                        }

                    }





                }}
                else if(check1==0&&check2==1&&check4==1)
                    Toast.makeText(getApplicationContext(),"enter pickup time",Toast.LENGTH_LONG).show();
                else if(check1==1&&check2==0&&check4==1)
                    Toast.makeText(getApplicationContext(),"enter dropoff time",Toast.LENGTH_LONG).show();

                else if(check1==1&&check2==1&&check4==0)
                    Toast.makeText(getApplicationContext(),"enter no. of passengers",Toast.LENGTH_LONG).show();
                else
                {
                    Toast.makeText(getApplicationContext(),"enter all details",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void timePicker(final int i) {

        final java.util.Calendar c = java.util.Calendar.getInstance();
        mHour = c.get(java.util.Calendar.HOUR_OF_DAY);
        mMinute = c.get(java.util.Calendar.MINUTE);


        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Hour = hourOfDay;
                        Minute = minute;

                        time=hourOfDay + ":" + minute;
                        if(i==1) {
                            time1=time;
                            h1 = Hour;
                            m1 = Minute;
                            t1 = ((h1*60)+m1);

                            tim1.setText(time);
                        }
                        else if(i==2){
                            time2=time;


                            h2= Hour;
                            m2= Minute;
                            t2 = ((h2*60)+m2);

                            tim2.setText(time);
                        }







                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

}

