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
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
        User.age = cursor.getInt(cursor.getColumnIndex("age"));
        User.name = cursor.getString(cursor.getColumnIndex("name"));
        User.password = cursor.getString(cursor.getColumnIndex("password"));
        User.gender = cursor.getString(cursor.getColumnIndex("gender"));
        User.email = email;
        User.phone_no = cursor.getString(cursor.getColumnIndex("phone_number"));
    }
}