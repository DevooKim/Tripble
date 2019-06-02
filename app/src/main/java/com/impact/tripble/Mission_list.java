package com.impact.tripble;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Mission_list extends AppCompatActivity {

    private final int REQUEST_MISSION1 = 100;
    private final int REQUEST_MISSION2 = 200;
    private final int REQUEST_MISSION3 = 300;
    private final int REQUEST_MISSION4 = 400;

    RelativeLayout mission1, mission2, mission3, mission4, mission5;
    ImageView check1, check2, check3, check4, check5;
    ImageView stamp1, stamp2, stamp3, stamp4;
    boolean isClear;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_list_new);
        scrollView();

        mission1 = (RelativeLayout) findViewById(R.id.mission1);
        mission2 = (RelativeLayout) findViewById(R.id.mission2);
        mission3 = (RelativeLayout) findViewById(R.id.mission3);
        mission4 = (RelativeLayout) findViewById(R.id.mission4);

        check1 = (ImageView)findViewById(R.id.check1);
        check2 = (ImageView)findViewById(R.id.check2);
        check3 = (ImageView)findViewById(R.id.check3);
        check4 = (ImageView)findViewById(R.id.check4);

        stamp1 = (ImageView)findViewById(R.id.st1);
        stamp2 = (ImageView)findViewById(R.id.st2);
        stamp3 = (ImageView)findViewById(R.id.st3);
        stamp4 = (ImageView)findViewById(R.id.st4);

        mission5 = (RelativeLayout)findViewById(R.id.mission5);
        check5 = (ImageView)findViewById(R.id.check5);






    }

    public void scrollView(){
        Button btnmission = (Button)findViewById(R.id.first_bt);

        btnmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mission_list.this, Mission_list.class);
                startActivity(intent);
            }
        });

        Button btnGift = (Button)findViewById(R.id.quard_bt);

        btnGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mission_list.this, Gifticon.class);
                startActivity(intent);
            }
        });

        Button btnprofile = (Button)findViewById(R.id.second_bt);

        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mission_list.this, Profile.class);
                startActivity(intent);
            }
        });
    }

    public void test(View v){

        switch(v.getId()){
            case R.id.mission1:
                Intent intent1 = new Intent(Mission_list.this, GEOMain.class);
                startActivityForResult(intent1, REQUEST_MISSION1);
                break;

            case R.id.mission2:
                Intent intent2 = new Intent(Mission_list.this, QRcode.class);
                startActivityForResult(intent2, REQUEST_MISSION2);
                //todo QR완성
                break;

            case R.id.mission3:
                Intent intent3 = new Intent(Mission_list.this, offline.class);
                startActivityForResult(intent3, REQUEST_MISSION3);

                break;

            case R.id.mission4:
                Intent intent4 = new Intent(Mission_list.this, NFC.class);
                startActivityForResult(intent4, REQUEST_MISSION4);
                //todo NFC완성

                break;

            case R.id.mission5:
                Intent intent5 = new Intent(Mission_list.this, NFC_NoBluetooth.class);
                startActivityForResult(intent5, REQUEST_MISSION4);
                break;


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case REQUEST_MISSION1:
                    isClear = data.getBooleanExtra("isClear", false);
                    if(isClear){
                        stamp1.setImageResource(R.drawable.im_clear_stamp);
                        check1.setVisibility(View.VISIBLE);
                    }
                    break;

                case REQUEST_MISSION2:
                    isClear = data.getBooleanExtra("isClear", false);
                    if(isClear){
                        stamp2.setImageResource(R.drawable.im_clear_stamp);
                        check2.setVisibility(View.VISIBLE);
                    }
                    break;

                case REQUEST_MISSION3:
                    isClear = data.getBooleanExtra("isClear", false);
                    if(isClear){
                        stamp3.setImageResource(R.drawable.im_clear_stamp);
                        check3.setVisibility(View.VISIBLE);
                    }
                    break;

                case REQUEST_MISSION4:
                    isClear = data.getBooleanExtra("isClear", false);
                    if(isClear){
                        stamp4.setImageResource(R.drawable.im_clear_stamp);
                        check4.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    }
}
