package com.example.taxibooking;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivityUser extends AppCompatActivity {

    EditText email, password, age, name, phone_no, gender;
    Button signup, signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_signup);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        age = (EditText) findViewById(R.id.age);
        name = (EditText) findViewById(R.id.name);
        phone_no = (EditText) findViewById(R.id.phone_no);
        gender = (EditText) findViewById(R.id.gender);

        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_ = email.getText().toString();
                String pass = password.getText().toString();
                Integer age_ = Integer.parseInt(age.getText().toString());
                String name_ = name.getText().toString();
                String phone = phone_no.getText().toString();
                String gender_ = gender.getText().toString();
                if(email.equals("")||pass.equals("")||gender_.equals("")||name_.equals("")||phone.equals(""))
                    Toast.makeText(SignupActivityUser.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                        Boolean checkuser = DB.checkemail_user(email_);
                        if(checkuser==false){
                            Boolean insert = DB.insert_user(email_,pass,gender_,age_,name_,phone);
                            if(insert==true){
                                DB.update_curr_user(email_);
                                Toast.makeText(SignupActivityUser.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivityUser.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignupActivityUser.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignupActivityUser.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }

                } }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivityUser.class);
                startActivity(intent);
            }
        });
    }
}