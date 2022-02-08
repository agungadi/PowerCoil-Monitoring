package org.uts.powercoil;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


public class SqliteHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "powercoil";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //TABLE NAME
    public static final String TABLE_USERS = "users";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";

    //COLUMN user name
    public static final String KEY_USER_NAME = "username";

    //COLUMN user name
    public static final String KEY_ROLES = "roles";

    //COLUMN email
    public static final String KEY_EMAIL = "email";

    //COLUMN password
    public static final String KEY_PASSWORD = "password";

    //COLUMN password
    public static final String KEY_NOHP = "nohp";

    public static final String KEY_PERUSAHAAN = "perusahaan";


    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_ROLES + " TEXT, "
            + KEY_PASSWORD + " TEXT, "
            + KEY_NOHP + " TEXT, "
            + KEY_PERUSAHAAN + " TEXT"
            + " ) ";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    //using this method we can add users to user table
    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_USER_NAME, user.userName);


        //Put email in  @values
        values.put(KEY_EMAIL, user.email);

        //Put roles in  @values
        values.put(KEY_ROLES, user.roles);


        //Put password in  @values
        values.put(KEY_PASSWORD, user.password);

        //Put password in  @values
        values.put(KEY_NOHP, user.nohp);
        //Put password in  @values
        values.put(KEY_PERUSAHAAN, user.perusahaan);


        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_ROLES, KEY_PASSWORD, KEY_NOHP, KEY_PERUSAHAAN},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL,KEY_ROLES, KEY_PASSWORD, KEY_NOHP, KEY_PERUSAHAAN},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }

//    public String getUsername() throws SQLException {
//        String username = "";
//        Cursor cursor = this.getReadableDatabase().query(
//                TABLE_USERS, new String[] { KEY_ID },
//                null, null, null, null, null);
//        if (cursor.moveToFirst()) {
//            do {
//                username = cursor.getString(0);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return username;
//    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(KEY_ID, cursor.getString(0));
                map.put(KEY_USER_NAME, cursor.getString(1));
                map.put(KEY_EMAIL, cursor.getString(2));
                map.put(KEY_ROLES, cursor.getString(3));
                map.put(KEY_PASSWORD, cursor.getString(4));
                map.put(KEY_NOHP, cursor.getString(5));
                map.put(KEY_PERUSAHAAN, cursor.getString(6));

                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }

    public ArrayList<HashMap<String, String>> getDataKonsultan() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_ROLES + "=" + "'Pekerja Lapangan'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(KEY_ID, cursor.getString(0));
                map.put(KEY_USER_NAME, cursor.getString(1));
                map.put(KEY_EMAIL, cursor.getString(2));
                map.put(KEY_ROLES, cursor.getString(3));
                map.put(KEY_PASSWORD, cursor.getString(4));
                map.put(KEY_NOHP, cursor.getString(5));
                map.put(KEY_PERUSAHAAN, cursor.getString(6));

                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }


    public void insert(String userName, String email, String roles, String password, String nohp, String perusahaan) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_USERS + " (userName, email, roles, password, nohp, perusahaan) " +
                "VALUES ('" + userName + "', '" + email + "', '" + roles + "', '" + password + "', '" + nohp + "', '" + perusahaan + "')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String userName, String email, String roles, String password, String nohp, String perusahaan) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_USERS + " SET "
                + KEY_USER_NAME + "='" + userName + "', "
                + KEY_EMAIL + "='" + email + "', "
                + KEY_ROLES + "='" + roles + "', "
                + KEY_PASSWORD + "='" + password + "', "
                + KEY_NOHP + "='" + nohp + "', "
                + KEY_PERUSAHAAN + "='" + perusahaan + "'"
                + " WHERE " + KEY_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_USERS + " WHERE " + KEY_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

}