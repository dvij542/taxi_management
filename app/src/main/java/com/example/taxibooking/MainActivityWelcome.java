package com.example.taxibooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityWelcome extends AppCompatActivity {

    Button userlogin,driverlogin,adminlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_welcome);

        userlogin = (Button) findViewById(R.id.userlogin);
        driverlogin = (Button) findViewById(R.id.driverlogin);
        adminlogin = (Button) findViewById(R.id.adminlogin);

        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivityUser.class);
                startActivity(intent);
            }
        });
        driverlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivityDriver.class);
                startActivity(intent);
            }
        });
        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivityAdmin.class);
                startActivity(intent);
            }
        });
    }

}
