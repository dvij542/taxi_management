package com.example.taxibooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.taxibooking.AvailableTrip;

public class AcceptActivityUser extends AppCompatActivity {
    TextView driver_name,driver_phone,car_no,car_type,arrival_dist;
    Button btnaccept, btnreject;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_user);
        DB = new DBHelper(this);
        driver_name = (TextView) findViewById(R.id.driver_name);
        driver_phone = (TextView) findViewById(R.id.driver_phone);
        car_no = (TextView) findViewById(R.id.car_no);
        car_type = (TextView) findViewById(R.id.car_type);
        arrival_dist = (TextView) findViewById(R.id.arrival_dist);
        btnaccept = (Button) findViewById(R.id.btnaccept);
        btnreject = (Button) findViewById(R.id.btnreject);
        driver_name.setText("Driver name : " + AvailableTrip.driver_name);
        driver_phone.setText("Driver phone no : " + AvailableTrip.driver_no);
        car_type.setText("Car model : " + AvailableTrip.car_type);
        car_no.setText("Car no : " + AvailableTrip.car_no);
        arrival_dist.setText("Distance from your location : " + AvailableTrip.arrival_dist.toString());
        btnaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.register_booking();
                Intent intent  = new Intent(getApplicationContext(), DuringTripActivityUser.class);
                startActivity(intent);
            }
        });
        btnreject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), HomeActivityUser.class);
                startActivity(intent);
            }
        });
    }
}
