package com.example.mdsuhelrana.helloandroid.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mdsuhelrana.helloandroid.ModelClasses.CrimeModel;
import com.example.mdsuhelrana.helloandroid.ModelClasses.MissingPeople;
import com.example.mdsuhelrana.helloandroid.ModelClasses.Signup;

import java.util.ArrayList;

/**
 * Created by Md Suhel Rana on 3/5/2018.
 */

public class CrimeDBSource {
    private Context context;
    private CrimeDBHelper crimeDBHelper;
    private SQLiteDatabase sqLiteDatabase;
    private CrimeModel crimeModel;
    public CrimeDBSource(Context context) {
        crimeDBHelper=new CrimeDBHelper(context);
        this.context = context;
    }
    public void open(){
        sqLiteDatabase=crimeDBHelper.getWritableDatabase();
    }
    public void close(){
        sqLiteDatabase.close();
    }
    public void read(){
        sqLiteDatabase=crimeDBHelper.getReadableDatabase();
    }

    public boolean inserData(CrimeModel crimeModel) {
        this.open();
        ContentValues values = new ContentValues();
        values.put(CrimeDBHelper.COL_LOCATION, crimeModel.getLocation());
        values.put(CrimeDBHelper.COL_POSTALCODE, crimeModel.getPostalcode());
        values.put(CrimeDBHelper.COL_CITY, crimeModel.getCity());
        values.put(CrimeDBHelper.COL_DATE, crimeModel.getDate());
        values.put(CrimeDBHelper.COL_SUBJECT, crimeModel.getSubject());
        values.put(CrimeDBHelper.COL_COMPLAIN, crimeModel.getComplain());
        values.put(CrimeDBHelper.COL_COMPLAIN_STATUS, crimeModel.getComplainstaus());
        long row = sqLiteDatabase.insert(CrimeDBHelper.TABLE_NAME, null, values);
        this.close();
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertMissPeople(MissingPeople missingPeople){
        this.open();
        ContentValues values=new ContentValues();
        values.put(CrimeDBHelper.COL_MISSPEOPLE_NAME,missingPeople.getName());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_AGE,missingPeople.getAge());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_GENDER,missingPeople.getGender());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_LASTSEEN,missingPeople.getLastseen());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_DATE,missingPeople.getDate());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_DETAILS,missingPeople.getDetails());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_ADDRESS,missingPeople.getAddrdss());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_IMAGE,missingPeople.getImage());
        values.put(CrimeDBHelper.COL_COMPLAIN_STATUS,missingPeople.getMissingStatus());

        long insertRow=sqLiteDatabase.insert(CrimeDBHelper.TABLE_MISSING_PEOPLE,null,values);
        this.close();
        if(insertRow > 0){
            return true;
        }else {
            return false;
        }
    }


    public boolean inserSignupData(Signup signup) {
        this.open();
        ContentValues values = new ContentValues();
        values.put(CrimeDBHelper.COL_SIGNNAME, signup.getName());
        values.put(CrimeDBHelper.COL_PHONE, signup.getPhone());
        values.put(CrimeDBHelper.COL_EMAIL, signup.getEmail());
        values.put(CrimeDBHelper.COL_PASSWORD, signup.getPassword());

        long row = sqLiteDatabase.insert(CrimeDBHelper.TABLE_SIGNUP, null, values);
        this.close();
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }



    public ArrayList<CrimeModel> getAllData(){
        ArrayList<CrimeModel> crimeModels=new ArrayList<>();
        this.open();
        Cursor cursor=sqLiteDatabase.query(CrimeDBHelper.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        if(cursor !=null && cursor.getCount()>0){
            do{
                int id= cursor.getInt(cursor.getColumnIndex(CrimeDBHelper.COL_ID));
                String location= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_LOCATION));
                String postalcode= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_POSTALCODE));
                String city= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_CITY));
                String date= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_DATE));
                String subject= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_SUBJECT));
                String complain= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_COMPLAIN));
                String complainstatus= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_COMPLAIN_STATUS));
                crimeModel=new CrimeModel(id,location,postalcode,city,date,subject,complain,complainstatus);
                crimeModels.add(crimeModel);
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return crimeModels;
    }

    public ArrayList<Signup> getSignupData(){
        ArrayList<Signup> signups=new ArrayList<>();
        this.open();
        Cursor cursor=sqLiteDatabase.query(CrimeDBHelper.TABLE_SIGNUP,null,null,null,null,null,null);
        cursor.moveToFirst();
        if(cursor !=null && cursor.getCount()>0){
            do{
                int id= cursor.getInt(cursor.getColumnIndex(CrimeDBHelper.COL_ID));
                String name= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_SIGNNAME));
                String phone= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_PHONE));
                String email= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_EMAIL));
                String passwrd= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_PASSWORD));
                 Signup signup=new Signup(id,name,phone,email,passwrd);
                 signups.add(signup);
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return signups;
    }


    public ArrayList<MissingPeople> getMissPeopleData(){
        ArrayList<MissingPeople> misslist=new ArrayList<>();
        this.open();
        Cursor cursor=sqLiteDatabase.query(CrimeDBHelper.TABLE_MISSING_PEOPLE,null,null,null,null,null,null);
        cursor.moveToFirst();
        if(cursor !=null && cursor.getCount()>0){
            do{
                int id= cursor.getInt(cursor.getColumnIndex(CrimeDBHelper.COL_ID));
                String name= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_MISSPEOPLE_NAME));
                String age= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_MISSPEOPLE_AGE));
                String gender= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_MISSPEOPLE_GENDER));
                String lastseen= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_MISSPEOPLE_LASTSEEN));
                String date= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_MISSPEOPLE_DATE));
                String details= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_MISSPEOPLE_DETAILS));
                String address= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_MISSPEOPLE_ADDRESS));
                byte[] image= cursor.getBlob(cursor.getColumnIndex(CrimeDBHelper.COL_MISSPEOPLE_IMAGE));
                String missingstatus= cursor.getString(cursor.getColumnIndex(CrimeDBHelper.COL_COMPLAIN_STATUS));
                MissingPeople missingPeople=new MissingPeople(id,name,age,gender,address,lastseen,details,image,missingstatus,date);
                misslist.add(missingPeople);
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return misslist;
    }

    public boolean deleteData(int rowId){
        this.open();
        int deletedrow=sqLiteDatabase.delete(CrimeDBHelper.TABLE_NAME,
                CrimeDBHelper.COL_ID+" = ?", new String[]{Integer.toString(rowId)});
        this.close();
        if(deletedrow > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean updateData(CrimeModel crimeModel, int rowId){
        this.open();
        ContentValues values=new ContentValues();
        values.put(CrimeDBHelper.COL_LOCATION,crimeModel.getLocation());
        values.put(CrimeDBHelper.COL_POSTALCODE,crimeModel.getPostalcode());
        values.put(CrimeDBHelper.COL_CITY,crimeModel.getCity());
        values.put(CrimeDBHelper.COL_DATE,crimeModel.getDate());
        values.put(CrimeDBHelper.COL_SUBJECT,crimeModel.getSubject());
        values.put(CrimeDBHelper.COL_COMPLAIN,crimeModel.getComplain());
        values.put(CrimeDBHelper.COL_COMPLAIN_STATUS,crimeModel.getComplainstaus());
        int updatedrow= sqLiteDatabase.update(CrimeDBHelper.TABLE_NAME,values,
                CrimeDBHelper.COL_ID+" = ?",new String[]{String.valueOf(rowId)});

        this.close();
        if (updatedrow > 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean deleteMissPeople(int rowId){
        this.open();
       int id= sqLiteDatabase.delete(CrimeDBHelper.TABLE_MISSING_PEOPLE,CrimeDBHelper.COL_ID+" = ?",new String[]{String.valueOf(rowId)});
       this.close();
        if(id>0){
          return true;
        }else {
            return false;
        }
    }

    public boolean updateMissPeople(MissingPeople missingPeople,int rowId){
        this.open();
        ContentValues values=new ContentValues();
        values.put(CrimeDBHelper.COL_MISSPEOPLE_NAME,missingPeople.getName());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_AGE,missingPeople.getAge());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_GENDER,missingPeople.getGender());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_LASTSEEN,missingPeople.getLastseen());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_DATE,missingPeople.getDate());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_DETAILS,missingPeople.getDetails());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_ADDRESS,missingPeople.getAddrdss());
        values.put(CrimeDBHelper.COL_MISSPEOPLE_IMAGE,missingPeople.getImage());
        values.put(CrimeDBHelper.COL_COMPLAIN_STATUS,missingPeople.getMissingStatus());
        int updateId=sqLiteDatabase.update(CrimeDBHelper.TABLE_MISSING_PEOPLE,values,CrimeDBHelper.COL_ID+" = ?",new String[]{Integer.toString(rowId)});
        this.close();
       if(updateId > 0){
           return true;

       }else {
           return false;
       }
    }

    public boolean updateStatus(CrimeModel model,int rowId){
        this.open();
         ContentValues values=new ContentValues();
         values.put(CrimeDBHelper.COL_COMPLAIN_STATUS,model.getComplainstaus());
        int updatedCol= sqLiteDatabase.update(CrimeDBHelper.TABLE_NAME,values,
                CrimeDBHelper.COL_ID+" = ?",new String[]{String.valueOf(rowId)});
        this.close();
       if(updatedCol > 0){
           return true;
       }else {
           return false;
       }
    }
    public boolean updateMissStatus(MissingPeople people,int rowId){
        this.open();
        ContentValues values=new ContentValues();
        values.put(CrimeDBHelper.COL_COMPLAIN_STATUS,people.getMissingStatus());
        int updateCol=sqLiteDatabase.update(CrimeDBHelper.TABLE_MISSING_PEOPLE,values,CrimeDBHelper.COL_ID+" = ?",new String[]{Integer.toString(rowId)});
        this.close();
        if(updateCol > 0){
            return true;

        }else{
            return false;
        }
    }
}
