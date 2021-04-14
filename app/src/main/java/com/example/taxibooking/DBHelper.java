package com.example.taxibooking;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.taxibooking.User;
import com.example.taxibooking.Driver;
import com.example.taxibooking.AvailableTrip;

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
                "  `is_outstation` boolean\n" +
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
        MyDB.execSQL("INSERT INTO route VALUES (\"12\",\"1\",\"2\",12);");
        MyDB.execSQL("INSERT INTO route VALUES (\"21\",\"2\",\"1\",12);");
        MyDB.execSQL("INSERT INTO route VALUES (\"13\",\"1\",\"3\",8);");
        MyDB.execSQL("INSERT INTO route VALUES (\"23\",\"2\",\"3\",8);");
        MyDB.execSQL("INSERT INTO driver VALUES(\"d1@gmail.com\",\"Nikhil Driver\",\"Male\",20,\"pass1\",\"9292929292\",NULL,True,\"GJ5408\",\"sedan\",\"1\");");
        MyDB.execSQL("INSERT INTO driver VALUES(\"d2@gmail.com\",\"Second Driver\",\"Male\",20,\"pass1\",\"9292929292\",NULL,True,\"GJ5401\",\"basic\",\"2\");");
        MyDB.execSQL("INSERT INTO user VALUES(\"dvij123\",\"Dvij\",\"Male\",20,\"pass\",\"9992999200\");");
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
                "  `is_outstation` boolean\n" +
                ");");
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
    public Boolean insert_driver(String email, String password, String gender, Integer age, String name, String phone_no, String car_num, String car_type){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("age", age);
        contentValues.put("gender", gender);
        contentValues.put("name", name);
        contentValues.put("phone_number", phone_no);
        contentValues.put("rating", 3);
        contentValues.put("is_available", 1);
        contentValues.put("curr_car_number", car_num);
        contentValues.put("curr_car_type", car_type);
        contentValues.put("curr_car_loc", "1");


        long result = MyDB.insert("driver", null, contentValues);
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
    public void update_curr_driver(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from driver where email = ?", new String[]{email});
//        System.out.println(cursor.getCount());
//        System.out.println(cursor.getColumnIndex("name"));
        String a = new String();
        cursor.moveToFirst();
//        System.out.println(cursor.getString(2));
        Driver.age = cursor.getInt(cursor.getColumnIndex("age"));
        Driver.name = cursor.getString(cursor.getColumnIndex("name"));
        Driver.password = cursor.getString(cursor.getColumnIndex("password"));
        Driver.gender = cursor.getString(cursor.getColumnIndex("gender"));
        Driver.email = email;
        Driver.phone_number = cursor.getString(cursor.getColumnIndex("phone_number"));
        Driver.curr_car_num = cursor.getString(cursor.getColumnIndex("curr_car_number"));
        Driver.curr_car_loc = cursor.getString(cursor.getColumnIndex("curr_car_loc"));
        Driver.curr_car_type = cursor.getString(cursor.getColumnIndex("curr_car_type"));
        Driver.rating = cursor.getInt(cursor.getColumnIndex("rating"));
        Driver.is_available = cursor.getInt(cursor.getColumnIndex("is_available"));
    }
    public Cursor find_trip(String from, String to, String car_type){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        if (car_type.equals("")){
            Cursor cursor = MyDB.rawQuery("SELECT driver.name,driver.phone_number,driver.curr_car_number,driver.curr_car_type, f.location_name,r.distance,driver.email " +
                    "FROM driver as driver, location as f, location as t, route as r " +
                    "WHERE t.location_name=? AND driver.curr_car_loc=f.location_id " +
                    "AND r.loc_start=f.location_id AND r.loc_end=t.location_id " +
                    "AND driver.is_available=1 " +
                    "ORDER BY r.distance", new String[]{from});
            return cursor;
        }
        else{
            Cursor cursor = MyDB.rawQuery("SELECT driver.name, driver.phone_number,driver.curr_car_number,driver.curr_car_type,f.location_name,r.distance,driver.email " +
                    "FROM driver as driver, location as f, location as t, route as r " +
                    "WHERE t.location_name=? AND driver.curr_car_loc=f.location_id " +
                    "AND r.loc_start=f.location_id AND r.loc_end=t.location_id " +
                    "AND driver.is_available=1 AND driver.curr_car_type=?" +
                    "ORDER BY r.distance", new String[]{from,car_type});
            return cursor;
        }
    }
    public Integer getdist(String from, String to){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT route.route_id,route.loc_start,route.loc_end,route.distance " +
                        "FROM route as route, location as start, location as end1 " +
                        "WHERE route.loc_start=start.location_id AND route.loc_end=end1.location_id " +
                        "AND start.location_name=? AND end1.location_name=?",new String[]{from,to});
        cursor.moveToFirst();
        System.out.println(cursor.getColumnIndex("distance"));
        return cursor.getInt(cursor.getColumnIndex("distance"));
    }
    public String get_route_id(String from, String to){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT route.route_id,route.loc_start,route.loc_end,route.distance " +
                "FROM route as route, location as start, location as end1 " +
                "WHERE route.loc_start=start.location_id AND route.loc_end=end1.location_id " +
                "AND start.location_name=? AND end1.location_name=?",new String[]{from,to});
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("route_id"));
    }
    public Cursor get_driver_details(String driver_email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * from driver WHERE email=?",new String[]{driver_email});
        cursor.moveToFirst();
        return cursor;
    }

    public void update_trip_details(String driver_name, String driver_no, String from, String to, String driver_loc, String car_no, String car_type, String driver_email){
        AvailableTrip.arrival_dist = getdist(driver_loc,from);
        AvailableTrip.car_no = car_no;
        AvailableTrip.car_type = car_type;
        AvailableTrip.dist = getdist(from,to);
        AvailableTrip.driver_name = driver_name;
        AvailableTrip.driver_email = driver_email;
        AvailableTrip.route_id = get_route_id(from,to);
        AvailableTrip.driver_no = driver_no;
        AvailableTrip.from = from;
        AvailableTrip.to = to;
    }
    public void register_booking(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        long curr_id = System.currentTimeMillis();
        contentValues.put("booking_id", Long.toString(curr_id));
        contentValues.put("car_num", AvailableTrip.car_no);
        contentValues.put("driver_email", AvailableTrip.driver_email);
        contentValues.put("is_started", false);
        contentValues.put("time_epochs", System.currentTimeMillis());
        contentValues.put("route_id", AvailableTrip.route_id);
        contentValues.put("user_email", User.email);
        AvailableTrip.booking_id = Long.toString(curr_id);
        long result = MyDB.insert("booking_received", null, contentValues);

    }
    public boolean get_trip_status_user(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT is_started FROM booking_received WHERE booking_id = ?",new String[]{AvailableTrip.booking_id});
        cursor.moveToFirst();
        return (cursor.getInt(0)==1);
    }
    public Cursor get_from_to(String route_id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT s.location_name,t.location_name FROM route,location as s, location as t WHERE route_id = ? AND route.loc_start=s.location_id AND route.loc_end=t.location_id",new String[]{route_id});
        cursor.moveToFirst();
        return cursor;
    }
    public String get_loc_name(String loc_id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT location_name FROM location WHERE location_id = ?",new String[]{loc_id});
        cursor.moveToFirst();
        return cursor.getString(0);
    }
    public boolean has_active_trip_user(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM booking_received WHERE user_email = ?",new String[]{User.email});
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            Cursor cursor_driver = get_driver_details(cursor.getString(2));
            Cursor cursor_locs = get_from_to(cursor.getString(5));
            update_trip_details(cursor_driver.getString(1),cursor_driver.getString(5)
                    ,cursor_locs.getString(0),cursor_locs.getString(1),get_loc_name(cursor_driver.getString(10)),cursor_driver.getString(8),
                    cursor_driver.getString(9),cursor.getString(2));
            AvailableTrip.booking_id = cursor.getString(0);
        }
        return (cursor.getCount()>0);
    }
}