package com.example.taxibooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DuringTripActivityUser extends AppCompatActivity {
    TextView hello,details,driver_name,driver_phone,car_no,car_type,arrival_dist;
    Button btnback;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_during_trip_user);
        DB = new DBHelper(this);
        hello = (TextView) findViewById(R.id.hello);
        details = (TextView) findViewById(R.id.details);
        driver_name = (TextView) findViewById(R.id.driver_name);
        driver_phone = (TextView) findViewById(R.id.driver_phone);
        car_no = (TextView) findViewById(R.id.car_no);
        car_type = (TextView) findViewById(R.id.car_type);
        arrival_dist = (TextView) findViewById(R.id.arrival_dist);
        btnback = (Button) findViewById(R.id.btnback);
        if (DB.get_trip_status_user()){
            hello.setText("Your trip has started!");
            details.setText("Sit back and enjoy your trip!!");
            driver_name.setText("Driver name : " + AvailableTrip.driver_name);
            driver_phone.setText("Driver phone no : " + AvailableTrip.driver_no);
            car_type.setText("Car model : " + AvailableTrip.car_type);
            car_no.setText("Car no : " + AvailableTrip.car_no);
            arrival_dist.setText("Length of yout trip : " + AvailableTrip.dist.toString());
        }
        else {
            driver_name.setText("Driver name : " + AvailableTrip.driver_name);
            driver_phone.setText("Driver phone no : " + AvailableTrip.driver_no);
            car_type.setText("Car model : " + AvailableTrip.car_type);
            car_no.setText("Car no : " + AvailableTrip.car_no);
            arrival_dist.setText("Distance from your location : " + AvailableTrip.arrival_dist.toString());
        }
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), MainActivityWelcome.class);
                startActivity(intent);
            }
        });
    }
}
