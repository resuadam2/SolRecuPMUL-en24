package com.example.solrecupmul.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.solrecupmul.model.Game;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "games.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "games";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_COMPANY = "company";
    private static final String COLUMN_PLATFORM = "platform";
    private static final String COLUMN_YEAR = "year";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_COMPANY + " TEXT, " + COLUMN_PLATFORM + " TEXT, " + COLUMN_YEAR + " INTEGER);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    /*
- Crash Bandicoot (1996), por Naughty Dog para Playstation 1.
- Pokémon Esmeralda (2004), por Game Freak para GameBoy Advance.
- Super Mario Run (2016), por Nintendo para Android.
- Super Mario Run (2016), por Nintendo para IOs.
- Dragon Quest VIII (2004), por Level-5 para Playstation 2.
- Rachet & Clank (2002), por Insomniac Games para PLaystation 2.
- Pokémon Go (2016), por Niantic para Android.
- Pokémon Go (2016), por Niantic para IOs.
- Final Fantasy VII (1997), por Square Enix para Playstation 1.
    * */

    private static final String [] initNames = {
      "Crash Bandicoot", "Pokémon Esmeralda", "Super Mario Run", "Super Mario Run", "Dragon Quest VIII", "Rachet & Clank", "Pokémon Go", "Pokémon Go", "Final Fantasy VII"
    };

    private static final String [] initCompanies = {
      "Naughty Dog", "Game Freak", "Nintendo", "Nintendo", "Level-5", "Insomniac Games", "Niantic", "Niantic", "Square Enix"
    };

    private static final String [] initPlatforms = {
      "PlayStation 1", "GameBoy Advance", "Android", "IOs", "PlayStation 2", "PlayStation 2", "Android", "IOs", "PlayStation 1"
    };

    private static final int [] initYears = {
      1996, 2004, 2016, 2016, 2004, 2002, 2016, 2016, 1997
    };

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(CREATE_TABLE);
           for (int i = 0; i < initNames.length; i++)
               db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ", " + COLUMN_COMPANY + ", " + COLUMN_PLATFORM + ", " + COLUMN_YEAR + ") VALUES ('" + initNames[i] + "', '" + initCompanies[i] + "', '" + initPlatforms[i] + "', " + initYears[i] + ");");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(String name, String company, String platform, int year)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ", " + COLUMN_COMPANY + ", " + COLUMN_PLATFORM + ", " + COLUMN_YEAR + ") VALUES ('" + name + "', '" + company + "', '" + platform + "', " + year + ");");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void delete(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id + ";");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void edit(int id, String name, String company, String platform, int year) {
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id + ";", null);
            if (cursor.moveToFirst()) {
                db.execSQL("UPDATE " + TABLE_NAME + " SET " + COLUMN_NAME + " = '" + name + "', " + COLUMN_COMPANY + " = '" + company + "', " + COLUMN_PLATFORM + " = '" + platform + "', " + COLUMN_YEAR + " = " + year + " WHERE " + COLUMN_ID + " = " + id + ";");
            } else {
                db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ", " + COLUMN_COMPANY + ", " + COLUMN_PLATFORM + ", " + COLUMN_YEAR + ") VALUES ('" + name + "', '" + company + "', '" + platform + "', " + year + ");");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("Range")
    public ArrayList<Game> getAllGames() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Game> games = new ArrayList<>();
        try
        {
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
            if (cursor.moveToFirst()) {
                do {
                    Game game = new Game();
                    game.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                    game.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                    game.setCompany(cursor.getString(cursor.getColumnIndex(COLUMN_COMPANY)));
                    game.setPlatform(cursor.getString(cursor.getColumnIndex(COLUMN_PLATFORM)));
                    game.setYear(cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR)));
                    games.add(game);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return games;
    }
}
