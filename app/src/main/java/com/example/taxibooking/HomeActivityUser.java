package com.example.taxibooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taxibooking.User;

public class HomeActivityUser extends AppCompatActivity {
    TextView hello;
    EditText from,to,car_type;
    Button btnfind, btnback;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_home);
        hello = (TextView) findViewById(R.id.hello);
        hello.setText("Hello " + User.name + "!!!");
        from = (EditText) findViewById(R.id.from);
        DB = new DBHelper(this);
        if(DB.has_active_trip_user()){
            Intent intent  = new Intent(getApplicationContext(), DuringTripActivityUser.class);
            startActivity(intent);
        }
        to = (EditText) findViewById(R.id.to);
        car_type = (EditText) findViewById(R.id.car_type);
        btnfind = (Button) findViewById(R.id.btnfind);
        btnback = (Button) findViewById(R.id.btnback);
        btnfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from_text = from.getText().toString();
                String to_text = to.getText().toString();
                String car_type_text = car_type.getText().toString();
                Cursor available_options = DB.find_trip(from_text,to_text,car_type_text);
                if (available_options.getCount()==0){
                    Toast.makeText(HomeActivityUser.this, "Sorry, no available cabs for this route at this time :(", Toast.LENGTH_SHORT).show();
                }
                else {
                    available_options.moveToFirst();
                    DB.update_trip_details(available_options.getString(0).toString(),available_options.getString(1).toString(),
                            from_text,to_text,available_options.getString(4).toString(),available_options.getString(2).toString(),
                            available_options.getString(3).toString(),available_options.getString(6).toString());
                    Intent intent  = new Intent(getApplicationContext(), AcceptActivityUser.class);
                    startActivity(intent);
                }
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), MainActivityWelcome.class);
                startActivity(intent);
            }
        });
    }
}
