package com.example.vamshi.udacitymoviesstage1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vamshi on 6/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Movies_Database";

    // Contacts table name
    private static final String TABLE_MOVIES = "Movies";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_M_ID = "Movie_id";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_MOVIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_M_ID + " INTEGER" + ")";
        db.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);

        onCreate(db);

    }


    public void addContact(FavMovies contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getMovieName()); // Contact Name
        values.put(KEY_M_ID, contact.getMovieID()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_MOVIES, null, values);
        db.close(); // Closing database connection
    }

    public FavMovies getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MOVIES, new String[]{KEY_ID,
                        KEY_NAME, KEY_M_ID}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        FavMovies contact = new FavMovies(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        // return contact
        return contact;
    }

    public List<FavMovies> getAllContacts() {
        List<FavMovies> contactList = new ArrayList<FavMovies>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FavMovies contact = new FavMovies(Integer.parseInt(cursor.getString(0)), cursor.getString(1));


                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MOVIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();

    }

    public void deleteContact(FavMovies contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getMovieID())});
        db.close();
    }
}
