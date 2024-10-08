package com.example.vinveli;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class DBHelper extends SQLiteOpenHelper {
        public DBHelper(@Nullable Context context) {
            super(context, "moon_missions.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table moon_missions(" +
                    "mission_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "mission_title TEXT," +
                    "mission_summary TEXT)");
        }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists moon_missions");
        onCreate(sqLiteDatabase);
    }
    }


