package com.ousl.clothify;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name
    public static final String DATABASE_NAME = "clothyfy.db";

    // Seller table
    public static final String SELLER_TABLE = "seller_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PHONE";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "USERNAME";
    public static final String COL_6 = "PASSWORD";


    // Buyer table
    public static final String BUYER_TABLE = "buyer_table";
    public static final String BUYER_COL_1 = "ID";
    public static final String BUYER_COL_2 = "NAME";
    public static final String BUYER_COL_3 = "PHONE";
    public static final String BUYER_COL_4 = "EMAIL";
    public static final String BUYER_COL_5 = "USERNAME";
    public static final String BUYER_COL_6 = "PASSWORD";


    // item table
    public static final String ITEM_TABLE = "item_table";
    public static final String ITEM_COL_1 = "ID";
    public static final String ITEM_COL_2 = "ITEM";
    public static final String ITEM_COL_3 = "CATEGORY";
    public static final String ITEM_COL_4 = "PRICE";
    public static final String ITEM_COL_5 = "DESCRIPTION";
    public static final String ITEM_COL_6 = "IMAGE";
    //feedback table
    public static final String FEEDBACK_TABLE = "feedback";
    public static final String FEEDBACK_COL_1 = "ID";
    public static final String FEEDBACK_COL_2 = "TOPIC";
    public static final String FEEDBACK_COL_3 = "DESCRIPTION";

    // Review table
    public static final String REVIEW_TABLE = "review_table";
    public static final String REVIEW_COL1 = "ID";
    public static final String REVIEW_COL2 = "IMAGE";
    public static final String REVIEW_COL3 = "MESSAGE";



    //    create db constructor
    public DatabaseHelper( Context context ) {
        super(context, DATABASE_NAME, null, 1);
    //   Create database and database tables
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // seller table
        db.execSQL("create table " + SELLER_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT , PHONE INTEGER , EMAIL TEXT , USERNAME TEXT , PASSWORD TEXT)");

        // Buyer table
        db.execSQL("CREATE TABLE " + BUYER_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, PHONE INTEGER, EMAIL TEXT, USERNAME TEXT, PASSWORD TEXT)");

        // item table
        db.execSQL("create table " + ITEM_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ITEM TEXT, CATEGORY TEXT, PRICE TEXT, DESCRIPTION TEXT, IMAGE BLOB)");

        // feedback table
        db.execSQL("create table " + FEEDBACK_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TOPIC TEXT, DESCRIPTION TEXT)");

        // review table
        db.execSQL("create table " + REVIEW_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "IMAGE BLOB, MESSAGE TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // avoid table repetitions
        db.execSQL("DROP TABLE IF EXISTS " + SELLER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BUYER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FEEDBACK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + REVIEW_TABLE);

        onCreate(db);
    }

    //    seller data insert method
    public boolean insertData(String name, String phone, String email, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contextValues = new ContentValues();
        contextValues.put(COL_2, name);
        contextValues.put(COL_3, phone);
        contextValues.put(COL_4, email);
        contextValues.put(COL_5, username);
        contextValues.put(COL_6, password);

        long result = db.insert(SELLER_TABLE, null, contextValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //    buyer data insert method
    public boolean insertBuyerData(String name, String phone, String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BUYER_COL_2, name);
        contentValues.put(BUYER_COL_3, phone);
        contentValues.put(BUYER_COL_4, email);
        contentValues.put(BUYER_COL_5, username);
        contentValues.put(BUYER_COL_6, password);

        long result = db.insert(BUYER_TABLE, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //   update ITEM data
    public boolean updateItem(int itemId, String name, String category, String price, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_COL_2, name);
        contentValues.put(ITEM_COL_3, category);
        contentValues.put(ITEM_COL_4, price);
        contentValues.put(ITEM_COL_5, description);
        int rowsAffected = db.update(ITEM_TABLE, contentValues, ITEM_COL_1 + " = ?", new String[]{String.valueOf(itemId)});
        return rowsAffected > 0;
    }

    //Authentication seller login credentials
    public boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_1};
        String selection = COL_5 + "=? and " + COL_6 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(SELLER_TABLE, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    //Authentication buyer login credentials
    public boolean checkBuyerCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {BUYER_COL_1};
        String selection = BUYER_COL_5 + "=? and " + BUYER_COL_6 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(BUYER_TABLE, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    //    new ITEM insert method
    public boolean insertItem(String item, String category, String price, String description, byte[] imageBytes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contextValues = new ContentValues();
        contextValues.put(ITEM_COL_2, item);
        contextValues.put(ITEM_COL_3, category);
        contextValues.put(ITEM_COL_4, price);
        contextValues.put(ITEM_COL_5, description);
        contextValues.put(ITEM_COL_6, imageBytes);

        long result = db.insert(ITEM_TABLE, null, contextValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    // Insert review text into the database
    public boolean insertReview(String reviewText) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REVIEW_COL3, reviewText);
        long result = db.insert(REVIEW_TABLE, null, contentValues);
        return (result != -1);
    }

    // Insert feedback data
    public boolean insertFeedback(String topic, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contextValues = new ContentValues();
        contextValues.put(FEEDBACK_COL_2, topic);
        contextValues.put(FEEDBACK_COL_3, description);

        long result = db.insert(FEEDBACK_TABLE, null, contextValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    // view items method
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from " + ITEM_TABLE,null);
        return result;
    }

    public Cursor getAllCustomers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from " + BUYER_TABLE,null);
        return result;
    }

    // Get all reviews from the database
    public Cursor getAllReviews() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("Select * from " + REVIEW_TABLE,null);
        return result;
    }

    // view feedback method
    public Cursor getAllFeedback(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from " + FEEDBACK_TABLE,null);
        return result;
    }

    // delete item method
    public  Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(ITEM_TABLE, "ID = ?", new String[] {id});
    }

    // function to validate email format
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    // changing Password
    public boolean changePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_6, password);

        String selection = COL_5 + "=?";
        String[] selectionArgs = { username };

        int rowsAffected = db.update(SELLER_TABLE, contentValues, selection, selectionArgs);
        return rowsAffected > 0;
    }

    public boolean changeBuyerPassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BUYER_COL_6, password);

        String selection = BUYER_COL_5 + "=?";
        String[] selectionArgs = { username };

        int rowsAffected = db.update(BUYER_TABLE, contentValues, selection, selectionArgs);
        return rowsAffected > 0;
    }

}
