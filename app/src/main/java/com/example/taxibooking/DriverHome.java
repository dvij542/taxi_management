package com.example.taxibooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.taxibooking.Booking;

public class DriverHome extends AppCompatActivity {

    TextView driver_name,driver_location,trip_status,user_name,user_email,user_phone,start_loc,end_loc;
    String driver_email = Driver.email;//"d1@gmail.com";
    Button btnstart, btnend,btnlogout;
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
        user_name = (TextView) findViewById(R.id.user_name);
        user_email = (TextView) findViewById(R.id.user_email);
        user_phone = (TextView) findViewById(R.id.user_phone);
        start_loc = (TextView) findViewById(R.id.start_loc);
        end_loc = (TextView) findViewById(R.id.end_loc);



        String status = "";
        if(Booking.booking_id==null)
        {
            status = "No booking, Please wait for booking";
            user_name.setText("User Name: ");
            user_email.setText("Email: ");
            user_phone.setText("Phone No: " );
            start_loc.setText("Pickup Location: " );
            end_loc.setText("Drop Location: " );
        }
        else
        {
            user_name.setText("User Name: " + Booking.user_name);
            user_email.setText("Email: " + Booking.user_email);
            user_phone.setText("Phone No: " + Booking.user_phone);
            start_loc.setText("Pickup Location: " + Booking.start_loc);
            end_loc.setText("Drop Location: " + Booking.end_loc);

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
        String location = DB.get_location_by_email(driver_email);
        driver_name.setText("Name is :" + Driver.name);
        driver_location.setText("Loc is: " + location);

        btnstart = (Button) findViewById(R.id.start_trip);
        btnend = (Button) findViewById(R.id.end_trip);
        btnlogout = (Button) findViewById(R.id.logout);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Booking.booking_id!=null)
                    DB.start_trip(driver_email);
                Intent intent  = new Intent(getApplicationContext(), DriverHome.class);
                startActivity(intent);
            }
        });
        btnend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Booking.booking_id!=null)
                    DB.end_trip(driver_email);
                Intent intent  = new Intent(getApplicationContext(), DriverHome.class);
                startActivity(intent);
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), MainActivityWelcome.class);
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