package com.impact.tripble;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class Mission_SelectTest extends AppCompatActivity {

    private DBHelper dbHelper;
    ListView lv_Mission;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_select_test);

        //dbHelper = (DBHelper) getIntent().getSerializableExtra("dbHelper");
        //db = openOrCreateDatabase(dbname, MODE_PRIVATE, null);
        lv_Mission = (ListView) findViewById(R.id.lv_mission);

        findViewById(R.id.btn_DataSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dbHelper == null){
                    dbHelper = new DBHelper(Mission_SelectTest.this, dbHelper.DBNAME, null, dbHelper.DB_VERSION);
                }

                List mission = dbHelper.getMissionData();
                lv_Mission.setAdapter(new MissionListAdapter_Test(mission, Mission_SelectTest.this ));
            }
        });

    }
}
