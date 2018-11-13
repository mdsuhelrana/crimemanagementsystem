package com.example.mdsuhelrana.helloandroid.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Md Suhel Rana on 3/5/2018.
 */

public class CrimeDBHelper extends SQLiteOpenHelper {
    public static final String DATABAS_NAME="crime_database";
    public static final int DATABAS_VERSION=6;
    public static final String COL_ID="_id";


    public static final String TABLE_NAME="table_crime";
    public static final String TABLE_MISSING_PEOPLE="table_missing_people";
    public static final String TABLE_SIGNUP="table_signup";

    public static final String COL_LOCATION="location";
    public static final String COL_POSTALCODE="postalcode";
    public static final String COL_CITY="city";
    public static final String COL_DATE="date";
    public static final String COL_SUBJECT="subject";
    public static final String COL_COMPLAIN="complain";
    public static final String COL_COMPLAIN_STATUS="complainstatus";

    public static final String COL_MISSPEOPLE_NAME="misspeoplename";
    public static final String COL_MISSPEOPLE_AGE="misspeopleage";
    public static final String COL_MISSPEOPLE_GENDER="misspeoplegender";
    public static final String COL_MISSPEOPLE_LASTSEEN="misspeoplelastseen";
    public static final String COL_MISSPEOPLE_DETAILS="misspeopledetails";
    public static final String COL_MISSPEOPLE_DATE="misspeopledate";
    public static final String COL_MISSPEOPLE_ADDRESS="missingpeopleaddress";
    public static final String COL_MISSPEOPLE_IMAGE="missingpeopleimg";
    public static final String COL_SIGNNAME="name";
    public static final String COL_PHONE="phone";
    public static final String COL_EMAIL="email";
    public static final String COL_PASSWORD="password";


    String CREATE_MISSINGPEOPLE_TABLE = "CREATE TABLE " + TABLE_MISSING_PEOPLE + "("

            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"

            + COL_MISSPEOPLE_NAME + " TEXT," + COL_MISSPEOPLE_AGE + " TEXT,"

            + COL_MISSPEOPLE_GENDER +" TEXT,"+ COL_MISSPEOPLE_LASTSEEN + " TEXT,"

            + COL_MISSPEOPLE_DATE +" TEXT,"+ COL_MISSPEOPLE_DETAILS + " TEXT,"

            + COL_MISSPEOPLE_ADDRESS + " TEXT," + COL_MISSPEOPLE_IMAGE + " BLOB," + COL_COMPLAIN_STATUS+" TEXT);";

    public static final String CREATE_CRIME_TABLE="create table "+TABLE_NAME+"("+
            COL_ID+" integer primary key autoincrement, "+COL_LOCATION+" text, "
            +COL_POSTALCODE+" text, "+COL_CITY+" text, "+COL_DATE+" text, "
            +COL_SUBJECT+" text, "+COL_COMPLAIN+" text, "+COL_COMPLAIN_STATUS+" text);";

    String CREATE_SIGNUP_TABLE=" CREATE TABLE " + TABLE_SIGNUP + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_SIGNNAME + " TEXT,"
            + COL_PHONE + " TEXT , "+ COL_EMAIL + " TEXT, " + COL_PASSWORD + " TEXT);";



    public CrimeDBHelper(Context context){
        super(context,DATABAS_NAME, null, DATABAS_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CRIME_TABLE);
        sqLiteDatabase.execSQL(CREATE_MISSINGPEOPLE_TABLE);
        sqLiteDatabase.execSQL(CREATE_SIGNUP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_MISSING_PEOPLE);
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_SIGNUP);
        onCreate(sqLiteDatabase);
    }
}
