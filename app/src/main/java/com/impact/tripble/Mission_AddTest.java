package com.impact.tripble;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Mission_AddTest extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_add_test);
        //dbHelper = new DBHelper(Mission_AddTest.this, dbHelper.DBNAME, null, dbHelper.DB_VERSION);

        findViewById(R.id.btn_DataCreate).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(dbHelper == null){
                    dbHelper = new DBHelper(Mission_AddTest.this, dbHelper.DBNAME, null, dbHelper.DB_VERSION);
                }

                final EditText etName = new EditText(Mission_AddTest.this);
                final EditText etLatitude = new EditText(Mission_AddTest.this);
                final EditText etLongitude = new EditText(Mission_AddTest.this);
                final EditText etTel = new EditText(Mission_AddTest.this);
                final EditText etHost = new EditText(Mission_AddTest.this);

                String name = etName.getText().toString();
                String latitude = etLatitude.getText().toString();
                String longitude = etLongitude.getText().toString();
                String tel = etTel.getText().toString();
                String host = etHost.getText().toString();

                Mission_Test mission_test = new Mission_Test();
                mission_test.setName(name);
                mission_test.setLatitude(latitude);
                mission_test.setLongitude(longitude);
                mission_test.setTel(tel);
                mission_test.setHost(host);

                dbHelper.addMission(mission_test);
            }


        });

        findViewById(R.id.btn_intent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mission_AddTest.this, Mission_SelectTest.class);
                //intent.putExtra("dbHelper", dbHelper);
                startActivity(intent);
            }
        });


    }
}
