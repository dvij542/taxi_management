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
        MyDB.execSQL("CREATE TABLE `trip_completed` (\n" +
                "  `booking_id` varchar(10) PRIMARY KEY,\n" +
                "  `car_num` varchar(12) NOT NULL,\n" +
                "  `driver_email` varchar(255) NOT NULL,\n" +
                "  `time_start` varchar(255) NOT NULL,\n" +
                "  `time_end` varchar(255) NOT NULL,\n" +
                "  `route_id` varchar(10) NOT NULL,\n" +
                "  `user_email` varchar(255) NOT NULL\n" +
                ");");
        MyDB.execSQL("INSERT INTO location VALUES(\"0000000000\",\"Cab Base Point\",0);");
        MyDB.execSQL("INSERT INTO location VALUES(\"MS\",\"MS\",0);");
        MyDB.execSQL("INSERT INTO location VALUES(\"VS\",\"VS\",0);");
        //MyDB.execSQL("INSERT INTO location VALUES(\"SNIG\",\"SNIG\",0);");
        MyDB.execSQL("INSERT INTO route VALUES (\"I2\",\"MS\",\"MS\",0);");
        MyDB.execSQL("INSERT INTO route VALUES (\"I3\",\"VS\",\"VS\",0);");
        //MyDB.execSQL("INSERT INTO route VALUES (\"I4\",\"3\",\"3\",0);");
        MyDB.execSQL("INSERT INTO route VALUES (\"12\",\"MS\",\"VS\",12);");
        MyDB.execSQL("INSERT INTO route VALUES (\"21\",\"VS\",\"MS\",12);");
//        MyDB.execSQL("INSERT INTO route VALUES (\"13\",\"1\",\"3\",8);");
//        MyDB.execSQL("INSERT INTO route VALUES (\"23\",\"2\",\"3\",8);");
        MyDB.execSQL("INSERT INTO driver VALUES(\"d1@gmail.com\",\"Nikhil Driver\",\"Male\",20,\"pass1\",\"9292929292\",NULL,1,\"GJ5408\",\"sedan\",\"MS\");");
        MyDB.execSQL("INSERT INTO driver VALUES(\"d2@gmail.com\",\"Second Driver\",\"Male\",20,\"pass1\",\"9292929292\",NULL,1,\"GJ5401\",\"basic\",\"MS\");");
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
        contentValues.put("curr_car_loc", "MS");


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

    public Boolean checkpassword_driver(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from driver where email = ? and password = ?", new String[] {email,password});
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

    public String get_location_by_email(String s) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT location_name from location where location_id in(SELECT curr_car_loc FROM driver WHERE email = ?)",new String[]{s});
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public void start_trip(String driver_email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE booking_received SET is_started = 1 where driver_email = ?",new String[]{driver_email});


    }

    public void end_trip(String driver_email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("SELECT * from booking_received where driver_email = ?",new String[]{driver_email});
        cursor.moveToFirst();
        String booking_id = cursor.getString(0);
        String route_id = cursor.getString(5);
        ContentValues contentValues= new ContentValues();
        long curr_id = System.currentTimeMillis();
        contentValues.put("booking_id", booking_id);
        contentValues.put("car_num", cursor.getString(1));;
        contentValues.put("driver_email", cursor.getString(2));;
        contentValues.put("time_start", cursor.getLong(4));
        contentValues.put("time_end", System.currentTimeMillis());
        contentValues.put("route_id", cursor.getString(5));
        contentValues.put("user_email", cursor.getString(6));
        AvailableTrip.booking_id = Long.toString(curr_id);
        long result = MyDB.insert("trip_completed", null, contentValues);

        MyDB.execSQL("UPDATE driver SET is_available = 1 where email = ?",new String[]{driver_email});
        MyDB.execSQL("UPDATE driver SET curr_car_loc = (select loc_end from route where route_id = ? ) where email = ?", new String[]{route_id,driver_email});
        MyDB.execSQL("DELETE from booking_received where driver_email = ?",new String[]{driver_email});
    }

    public void get_booking() {
        String email = Driver.email;

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * from booking_received where driver_email = ?",new String[]{email});
        if(cursor.moveToFirst())
        {
            Booking.booking_id = cursor.getString(0);
            Booking.driver_email = cursor.getString(2);
            Booking.is_started = cursor.getInt(3);
            Booking.route_id = cursor.getString(5);
            Booking.user_email = cursor.getString(6);
            Cursor cursor1 = MyDB.rawQuery("SELECT * from route where route_id = ?",new String[]{Booking.route_id});
            cursor1.moveToFirst();

            Booking.start_loc = cursor1.getString(1);
            Cursor cursor12 = MyDB.rawQuery("SELECT location_name from location where location_id = ?",new String[]{Booking.start_loc});
            cursor12.moveToFirst();
            Booking.start_loc = cursor12.getString(0);

            Booking.end_loc = cursor1.getString(2);
            Cursor cursor11 = MyDB.rawQuery("SELECT location_name from location where location_id = ?",new String[]{Booking.end_loc});
            cursor11.moveToFirst();
            Booking.end_loc = cursor11.getString(0);


            Cursor cursor2 = MyDB.rawQuery("SELECT * from user where email = ?",new String[]{Booking.user_email});
            cursor2.moveToFirst();
            Booking.user_name = cursor2.getString(1);
            Booking.user_phone = cursor2.getString(5);

        }
        else
        {
            Booking.booking_id = null;
        }
    }


}