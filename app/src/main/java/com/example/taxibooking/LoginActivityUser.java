package com.example.taxibooking;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.taxibooking.User;

public class LoginActivityUser extends AppCompatActivity {
    EditText username, password;
    Button btnlogin,btnback;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_login);

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

                if(email.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivityUser.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkpassword_user(email,pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivityUser.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        DB.update_curr_user(email);
                        Intent intent  = new Intent(getApplicationContext(), HomeActivityUser.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivityUser.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
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