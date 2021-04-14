package com.example.taxibooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.taxibooking.Booking;

public class DriverHome extends AppCompatActivity {

    TextView driver_name,driver_location,car_no,car_type,arrival_dist,trip_status;
    String driver_email = "d1@gmail.com";
    Button btnstart, btnend;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_driver);
        DB = new DBHelper(this);
        DB.get_booking();
        driver_name = (TextView) findViewById(R.id.driver_name);
        driver_location = (TextView) findViewById(R.id.location);
        trip_status = (TextView) findViewById(R.id.trip_status);
        String status = "";
        if(Booking.booking_id==null)
        {
            status = "No booking, Please wait for booking";
        }
        else
        {
            if(Booking.is_started == 1)
            {
                status = "trip ongoing, end trip on reaching destination";
            }
            else
            {
                status = "You have following booking, kindly start your trip";
            }
        }
        trip_status.setText("Status: " + status);
        String location = DB.get_location_by_email("d1@gmail.com");
        driver_name.setText("Name is :" + driver_email);
        driver_location.setText("Loc is: " + location);
        //driver_location.setText("Loc : " + location);
//        car_no = (TextView) findViewById(R.id.car_no);
//        car_type = (TextView) findViewById(R.id.car_type);
//        arrival_dist = (TextView) findViewById(R.id.arrival_dist);
        btnstart = (Button) findViewById(R.id.start_trip);
        btnend = (Button) findViewById(R.id.end_trip);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.start_trip(driver_email);
                Intent intent  = new Intent(getApplicationContext(), DriverHome.class);
                startActivity(intent);
            }
        });
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.end_trip(driver_email);
                Intent intent  = new Intent(getApplicationContext(), DriverHome.class);
                startActivity(intent);
            }
        });
//        driver_name.setText("Driver name : " + AvailableTrip.driver_name);
//        driver_phone.setText("Driver phone no : " + AvailableTrip.driver_no);
//        car_type.setText("Car model : " + AvailableTrip.car_type);
//        car_no.setText("Car no : " + AvailableTrip.car_no);
//        arrival_dist.setText("Distance from your location : " + AvailableTrip.arrival_dist.toString());
    }
}