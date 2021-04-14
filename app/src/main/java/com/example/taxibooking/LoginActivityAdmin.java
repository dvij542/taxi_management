package com.example.taxibooking;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.taxibooking.User;

public class LoginActivityAdmin extends AppCompatActivity {
    EditText username, password;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        username = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = username.getText().toString();
                String pass = password.getText().toString();

                if(email.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivityAdmin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    boolean checkuserpass = (email.equals("admin@gmail.com")|pass.equals("pass"));
                    if(checkuserpass){
                        Toast.makeText(LoginActivityAdmin.this, "Log in successful", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomeActivityAdmin.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivityAdmin.this, "Invalid Admin Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
