package com.example.taxibooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddLocationAdmin extends AppCompatActivity {

    EditText loc_id, loc_name, is_outstation;
    Button add,back;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location_admin);

        loc_id = (EditText) findViewById(R.id.locationid);
        loc_name = (EditText) findViewById(R.id.locationname);
        is_outstation = (EditText) findViewById(R.id.isoutstation);
        add = (Button) findViewById(R.id.add);
        back = (Button) findViewById(R.id.back);
        DB = new DBHelper(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location_id = loc_id.getText().toString();
                String location_name = loc_name.getText().toString();
                Integer is_out = Integer.parseInt(is_outstation.getText().toString());
                if(location_id.equals("")||location_name.equals("")||!(is_out==0||is_out==1))
                    Toast.makeText(AddLocationAdmin.this, "Please enter valid fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkloc = DB.check_location(location_id,location_name);
                    if(!checkloc){
                        Boolean insert = DB.insert_location(location_id,location_name,is_out);
                        if(insert){
                            Toast.makeText(AddLocationAdmin.this, "Location added successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AddLocationAdmin.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(AddLocationAdmin.this, "Addition failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(AddLocationAdmin.this, "Location id/name already exists", Toast.LENGTH_SHORT).show();
                    }

                } }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivityAdmin.class);
                startActivity(intent);
            }
        });
    }
}
