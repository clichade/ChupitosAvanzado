package com.example.trjano.chupitos.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by trjano on 14/02/17.
 */

public class ChupitosDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "chupitos.db";

    public ChupitosDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + LawyerEntry.TABLE_NAME + " ("
                + LawyerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LawyerEntry.ID + " TEXT NOT NULL,"
                + LawyerEntry.NAME + " TEXT NOT NULL,"
                + LawyerEntry.SPECIALTY + " TEXT NOT NULL,"
                + LawyerEntry.PHONE_NUMBER + " TEXT NOT NULL,"
                + LawyerEntry.BIO + " TEXT NOT NULL,"
                + LawyerEntry.AVATAR_URI + " TEXT,"
                + "UNIQUE (" + LawyerEntry.ID + "))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
