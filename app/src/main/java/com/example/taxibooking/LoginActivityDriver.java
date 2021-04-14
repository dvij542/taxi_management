package com.example.taxibooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivityDriver extends AppCompatActivity {
    EditText username, password;
    Button btnlogin, btnback;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_driver);

        username = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password1);

        btnlogin = (Button) findViewById(R.id.btnsignin1);
        btnback = (Button) findViewById(R.id.btnback);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = username.getText().toString();
                String pass = password.getText().toString();

                if (email.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivityDriver.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkdriverpass = DB.checkpassword_driver(email, pass);
                    if (checkdriverpass == true) {
                        Toast.makeText(LoginActivityDriver.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        DB.update_curr_driver(email);
                        DB.get_booking();
                        Intent intent = new Intent(getApplicationContext(), DriverHome.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivityDriver.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivityWelcome.class);
                startActivity(intent);
            }
        });
    }
}
