package com.scheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * DBOpenHelper
 * make Column and basic
 *
 */

public class DBOpenHelper  {
    private static final String DATABASE_NAME = "schedule.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    public static final class CreateDB implements BaseColumns {
        public static final String year = "year";
        public static final String month = "month";
        public static final String day =  "day";
        public static final String time = "time";
        public static final String memo = "memo";
        public static final String  _TABLENAME = "schedule";
        public static final String _CREATE =
                "create table "+_TABLENAME+" ( "
                        +_ID+" integer primary key autoincrement, "
                        +year+"integer, "
                        +month+" integer, "
                        +day+" integer, "
                        +time+" integer, "
                        +memo+" text );";
    }

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateDB._CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVirsion) {
            db.execSQL("DROP TABLE IF EXISTS "+CreateDB._TABLENAME);
            onCreate(db);
        }
    }

    public DBOpenHelper (Context context) {
        this.mCtx = context;
    }

    public DBOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        mDB.close();
    }

    public void addData(int year, int month, int day, int time) {
        ContentValues values = new ContentValues();
        values.put(CreateDB.year, year);
        values.put(CreateDB.month, month);
        values.put(CreateDB.day, day);
        values.put(CreateDB.time, time);
        long newRowId = mDB.insert(CreateDB._TABLENAME,null,values);
    }
    public void addData(int year, int month, int day, int time, String memo) {
        ContentValues values = new ContentValues();
        values.put(CreateDB.year, year);
        values.put(CreateDB.month, month);
        values.put(CreateDB.day, day);
        values.put(CreateDB.time, time);
        values.put(CreateDB.memo, memo);
        long newRowId = mDB.insert(CreateDB._TABLENAME,null,values);
    }
}
