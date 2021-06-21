package com.sleepingpandaaa.bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9876543210,'Vishal Babal',10000.00,'vishal@abc.com','XXXXXXXXXXXX1234','ABC09876543')");
        db.execSQL("insert into user_table values(9876543211,'Vikas Upadhyay',10000.00,'vikas@abc.com','XXXXXXXXXXXX2341','BCA98765432')");
        db.execSQL("insert into user_table values(9876543212,'Deepak Kumar',10000.00,'deepak@abc.com','XXXXXXXXXXXX3412','CAB87654321')");
        db.execSQL("insert into user_table values(9876543213,'Nihal Choudhary',10000.00,'nihal@abc.com','XXXXXXXXXXXX4123','ABC76543210')");
        db.execSQL("insert into user_table values(9876543214,'Aniket Kumar',10000.00,'aniket@abc.com','XXXXXXXXXXXX2345','BCA65432109')");
        db.execSQL("insert into user_table values(9876543215,'Rohit Kumar',10000.00,'rohit@abc.com','XXXXXXXXXXXX3452','CAB54321098')");
        db.execSQL("insert into user_table values(9876543216,'Shubham Mittal',10000.00,'shubham@abc.com','XXXXXXXXXXXX4523','ABC43210987')");
        db.execSQL("insert into user_table values(9876543217,'P. Rohith',10000.00,'rohith@abc.com','XXXXXXXXXXXX5234','BCA32109876')");
        db.execSQL("insert into user_table values(9876543218,'Arif Khan',10000.00,'arif@abc.com','XXXXXXXXXXXX3456','CAB21098765')");
        db.execSQL("insert into user_table values(9876543219,'Alok Kumar',10000.00,'alok@abc.com','XXXXXXXXXXXX4563','ABC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
