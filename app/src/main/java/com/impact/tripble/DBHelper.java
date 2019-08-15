package com.impact.tripble;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper implements Serializable {

    private Context context;
    public static String DBNAME = "MISSION_TEST";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE MISSION_TEST ( ");
        sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" NAME TEXT, ");
        sb.append(" LATITUDE TEXT, ");
        sb.append(" LONGITUDE TEXT, ");
        sb.append(" TEL TEXT, ");
        sb.append(" HOST TEXT ) ");

        db.execSQL(sb.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "DB version Upgrade", Toast.LENGTH_SHORT).show();
    }

    public void addMission(Mission_Test mission_test){
        SQLiteDatabase db = getWritableDatabase();
        //SQLiteDatabase db;
        //db.openOr

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO MISSION_TEST ( ");
        sb.append(" NAME, LATITUDE, LONGITUDE, TEL, HOST ) ");
        sb.append(" VALUES ( ?, ?, ?, ?, ? ) ");

        db.execSQL(sb.toString(),
                new Object[]{
                        mission_test.getName(),
                        mission_test.getLatitude(),
                        mission_test.getLongitude(),
                        mission_test.getTel(),
                        mission_test.getHost()
                });
        Toast.makeText(context, "미션DB 생성 완료", Toast.LENGTH_SHORT).show();
    }

    public List getMissionData(){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, HOST FROM "+ DBNAME );
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(sb.toString(), null);

        List Mission = new ArrayList();
        Mission_Test mission_test = null;

        while( cursor.moveToNext()){
            mission_test = new Mission_Test();
            mission_test.set_id(cursor.getInt(0));
            mission_test.setHost(cursor.getString(1));

            Mission.add(mission_test);
        }

        return Mission;
    }
}
