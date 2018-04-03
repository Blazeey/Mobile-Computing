package com.example.vsundar17697.attendance_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vsundar17697 on 23/01/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "data_manager";
    private static final String TABLE = "student";
    private static final String k_gender = "gender";
    private static final String k_name = "name";
    private static final String k_ph_no = "ph_no";
    private static final String k_dept = "dept";

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE " + TABLE + "(" + k_name + " TEXT ," +
                k_gender + " TEXT , " + k_ph_no + " TEXT PRIMARY KEY, " + k_dept +
                " TEXT " + ")";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void deleteDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE );
        onCreate(db);
    }

    void addStudent(student s){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(k_name , s.get_name());
        values.put(k_gender , s.get_id());
        values.put(k_ph_no , s.get_ph_no());
        values.put(k_dept , s.get_dept());


        db.insert(TABLE , null , values);
        db.close();
    }

    student getStudent(String ph){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE , new String[] {k_name , k_gender , k_ph_no , k_dept} , k_ph_no + "=?" , new String[]{ph} , null , null , null , null );
        if (cursor != null)
                cursor.moveToFirst();

        student s = new student(cursor.getString(0) , cursor.getString(1) , cursor.getString(2) , cursor.getString(3));
        return s;

    }

    public List <student> getAllStudents(){
        List<student> contactList = new ArrayList<student>();

        String selectQuery = "SELECT * FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery , null);

        if(cursor.moveToFirst()){
            do {
                student s = new student(cursor.getString(0) , cursor.getString(1) , cursor.getString(2) , cursor.getString(3));
                contactList.add(s);
            }while(cursor.moveToNext());
        }
        return contactList;
    }

    public List <student> getDeptStudents(String DEPT){
        List<student> contactList = new ArrayList<student>();
        String selectQuery = "SELECT * FROM " + TABLE + " WHERE " + k_dept + " = '" + DEPT + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery , null);

        if(cursor.moveToFirst()){
            do{
                student s = new student(cursor.getString(0) , cursor.getString(1) , cursor.getString(2) , cursor.getString(3));
                contactList.add(s);
            }while(cursor.moveToNext());
        }
        return contactList;
    }

    public int updateStudent(student s){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(k_name , s.get_name());
        values.put(k_gender , s.get_id());
        values.put(k_ph_no , s.get_ph_no());
        values.put(k_dept , s.get_dept());

        return db.update(TABLE , values , k_ph_no + " =? " , new String[] {s.get_ph_no()});
    }

    public void deleteContact(String ph){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE , k_ph_no + " = ? " , new String[] {ph});
        db.close();

    }




}
