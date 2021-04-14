package com.example.taxibooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRouteAdmin extends AppCompatActivity {

    EditText routeid, locstart, locend, dis;
    Button add,back;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route_admin);

        routeid = (EditText) findViewById(R.id.routeid);
        locstart = (EditText) findViewById(R.id.locstart);
        locend = (EditText) findViewById(R.id.locend);
        dis = (EditText) findViewById(R.id.dis);
        add = (Button) findViewById(R.id.add);
        back = (Button) findViewById(R.id.back);
        DB = new DBHelper(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String route_id = routeid.getText().toString();
                String loc_start = locstart.getText().toString();
                String loc_end = locend.getText().toString();
                String dis_S = dis.getText().toString();
                Integer distance = Integer.parseInt(dis.getText().toString());
                if(route_id.equals("")||loc_start.equals("")||loc_end.equals("")||dis_S.equals(""))
                    Toast.makeText(AddRouteAdmin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkrouteid = DB.check_route_id(route_id);
                    Boolean checkroute = DB.check_route(loc_start,loc_end);
                    Boolean checklocalready = DB.check_loc_already(loc_start,loc_end);
                    if(checkrouteid){
                        Toast.makeText(AddRouteAdmin.this, "Route id already exists", Toast.LENGTH_SHORT).show();
                    }
                    else if(checkroute){
                        Toast.makeText(AddRouteAdmin.this, "Route already exists", Toast.LENGTH_SHORT).show();
                    }
                    else if(!checklocalready){
                        Toast.makeText(AddRouteAdmin.this, "Entered Location does not exists", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Boolean insert = DB.insert_route(route_id,loc_start,loc_end,distance);
                        if(insert){
                            Toast.makeText(AddRouteAdmin.this, "Route added successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AddRouteAdmin.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(AddRouteAdmin.this, "Addition failed", Toast.LENGTH_SHORT).show();
                        }
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
