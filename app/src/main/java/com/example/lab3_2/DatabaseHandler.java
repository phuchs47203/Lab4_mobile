package com.example.lab3_2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " +
                TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }


    public void addContact(Contact contact) {
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(KEY_ID, contact.getID());
        inititalValues.put(KEY_NAME, contact.getName());
        inititalValues.put(KEY_PH_NO, contact.getPhoneNumber());
        sqLiteDatabase.insert(TABLE_CONTACTS, null, inititalValues);
    }

    @SuppressLint("Range")
    public Contact getContact(int id) {
        Cursor cursor = sqLiteDatabase.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToNext();
        Contact res = new Contact();
        res.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
        res.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
        res.setPhoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PH_NO)));
        return res;
    }

    @SuppressLint("Range")
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_NAME, KEY_PH_NO}, null, null, null, null, null);
        while (cursor.moveToNext()){
            Contact res = new Contact();
            res.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
            res.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            res.setPhoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PH_NO)));
            contacts.add(res);
        }
        return contacts;
    }

    public int updateContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, "updated_name");
        return sqLiteDatabase.update(TABLE_CONTACTS, values, KEY_ID + "=" + contact.getID(), null);
    }

    public void deleteContact(Contact contact) {
        sqLiteDatabase.delete(TABLE_CONTACTS, KEY_ID + "=" + contact.getID(), null);
    }

    public void deleteAllContacts() {
        sqLiteDatabase.delete(TABLE_CONTACTS, null, null);
    }
}
