package com.example.taxibooking;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.taxibooking.User;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE user (\n" +
                "  email varchar(255) PRIMARY KEY,\n" +
                "  name varchar(50),\n" +
                "  gender varchar(10),\n" +
                "  age int,\n" +
                "  password varchar(50),\n" +
                "  phone_number varchar(11)\n" +
                ");");
        MyDB.execSQL("CREATE TABLE `driver` (\n" +
                "  `email` varchar(255) PRIMARY KEY,\n" +
                "  `name` varchar(50),\n" +
                "  `gender` varchar(10),\n" +
                "  `age` int,\n" +
                "  `password` varchar(50) NOT NULL,\n" +
                "  `phone_number` varchar(11),\n" +
                "  `rating` decimal,\n" +
                "  `is_available` boolean,\n" +
                "  `curr_car_number` varchar(12),\n" +
                "  `curr_car_type` varchar(12)\n," +
                "  `curr_car_loc` varchar(12)\n" +
                ");");
        MyDB.execSQL("CREATE TABLE `route` (\n" +
                "  `route_id` varchar(10) PRIMARY KEY,\n" +
                "  `loc_start` varchar(10),\n" +
                "  `loc_end` varchar(10),\n" +
                "  `distance` decimal\n" +
                ");");
        MyDB.execSQL("CREATE TABLE `location` (\n" +
                "  `location_id` varchar(10) PRIMARY KEY,\n" +
                "  `location_name` varchar(50),\n" +
                "  `is_outstation` int\n" +
                ");");
        MyDB.execSQL("CREATE TABLE `booking_received` (\n" +
                "  `booking_id` varchar(10) PRIMARY KEY,\n" +
                "  `car_num` varchar(12) NOT NULL,\n" +
                "  `driver_email` varchar(255) NOT NULL,\n" +
                "  `is_started` bool NOT NULL,\n" +
                "  `time_epochs` varchar(255) NOT NULL,\n" +
                "  `route_id` varchar(10) NOT NULL,\n" +
                "  `user_email` varchar(255) NOT NULL\n" +
                ");");
        MyDB.execSQL("INSERT INTO location VALUES(\"0000000000\",\"Cab Base Point\",0);");
        MyDB.execSQL("INSERT INTO location VALUES(\"1\",\"MS\",0);");
        MyDB.execSQL("INSERT INTO location VALUES(\"2\",\"VS\",0);");
        MyDB.execSQL("INSERT INTO location VALUES(\"3\",\"SNIG\",0);");
        MyDB.execSQL("INSERT INTO route VALUES (\"I2\",\"1\",\"1\",0);");
        MyDB.execSQL("INSERT INTO route VALUES (\"I3\",\"2\",\"2\",0);");
        MyDB.execSQL("INSERT INTO route VALUES (\"I4\",\"3\",\"3\",0);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists user");
        MyDB.execSQL("CREATE TABLE user (\n" +
                "  email varchar(255) PRIMARY KEY,\n" +
                "  name varchar(50),\n" +
                "  gender varchar(10),\n" +
                "  age int,\n" +
                "  password varchar(50),\n" +
                "  phone_number varchar(11)\n" +
                ");");
    }

    public Boolean insert_dummy_route(String route_id, String loc_start, String loc_end, Integer distance){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("route_id", route_id);
        contentValues.put("loc_start", loc_start);
        contentValues.put("loc_end", loc_end);
        contentValues.put("distance", distance);
        long result = MyDB.insert("route", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean insert_location(String loc_id, String loc_name, Integer is_outstation){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("location_id", loc_id);
        contentValues.put("location_name", loc_name);
        contentValues.put("is_outstation", is_outstation);
        long result = MyDB.insert("location", null, contentValues);
        insert_dummy_route(loc_id,loc_id,loc_id,0);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean check_location(String loc_id,String loc_name) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from location where location_id = ? or location_name = ?", new String[]{loc_id,loc_name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean insert_reverse_route(String route_id, String loc_start, String loc_end, Integer distance){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("route_id", route_id);
        contentValues.put("loc_start", loc_start);
        contentValues.put("loc_end", loc_end);
        contentValues.put("distance", distance);
        long result = MyDB.insert("route", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean insert_route(String route_id, String loc_start, String loc_end, Integer distance){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("route_id", route_id);
        contentValues.put("loc_start", loc_start);
        contentValues.put("loc_end", loc_end);
        contentValues.put("distance", distance);
        String idd = route_id + "rr";
        insert_reverse_route(idd,loc_end,loc_start,distance);
        long result = MyDB.insert("route", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean check_route_id(String route_id) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from route where route_id = ?", new String[]{route_id});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean check_route(String loc_start,String loc_end) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from route where (loc_start = ? and loc_end = ?) or (loc_end = ? and loc_start = ?)", new String[]{loc_start,loc_end,loc_end,loc_start});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean check_loc_already(String loc_start,String loc_end) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor1 = MyDB.rawQuery("Select * from location where location_id = ?", new String[]{loc_start});
        Cursor cursor2 = MyDB.rawQuery("Select * from location where location_id = ?", new String[]{loc_end});
        if (cursor1.getCount() > 0 && cursor2.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean insert_user(String email, String password, String gender, Integer age, String name, String phone_no){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("age", age);
        contentValues.put("gender", gender);
        contentValues.put("name", name);
        contentValues.put("phone_number", phone_no);

        long result = MyDB.insert("user", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkemail_user(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkpassword_user(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public void update_curr_user(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where email = ?", new String[]{email});
//        System.out.println(cursor.getCount());
//        System.out.println(cursor.getColumnIndex("name"));
        String a = new String();
        cursor.moveToFirst();
//        System.out.println(cursor.getString(2));
        User.age = cursor.getInt(cursor.getColumnIndex("age"));
        User.name = cursor.getString(cursor.getColumnIndex("name"));
        User.password = cursor.getString(cursor.getColumnIndex("password"));
        User.gender = cursor.getString(cursor.getColumnIndex("gender"));
        User.email = email;
        User.phone_no = cursor.getString(cursor.getColumnIndex("phone_number"));
    }
}