package com.example.alexgarston.healthprofiler;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by alexgarston on 4/10/18.
 */

public class DB_controller extends SQLiteOpenHelper {
    public DB_controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "TEST.db", factory, version); //name parameter is db name. changed to "TEST.db".
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STUDENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT, BMI TEXT, DEC TEXT, MAXHEART TEXT, MIN TEXT, MAX TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS STUDENTS;");
        onCreate(db);
    }

    public void insert_student(String bmi, String dec, String maxheart, String min, String max )
    {
        ContentValues cv = new ContentValues();
        cv.put("BMI", bmi);
        cv.put("DEC", dec);
        cv.put("MAXHEART", maxheart);
        cv.put("MIN", min);
        cv.put("MAX", max);

        this.getWritableDatabase().insertOrThrow("STUDENTS", null, cv);
    }



    public void list_all_students(TextView textView)
    {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM STUDENTS", null);
        textView.setText("");
        while(cursor.moveToNext())
        {
            //textView.append(cursor.getString(1) + " " + cursor.getString(2)+ "\n");
            textView.append(cursor.getInt(0) + " " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) +" " + cursor.getString(5) + "\n" );
        }
    }
}
