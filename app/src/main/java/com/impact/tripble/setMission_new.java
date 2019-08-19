package com.impact.tripble;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class setMission_new extends AppCompatActivity {

    // TODO: 2019-08-16-016 이미지 관련 나중에
    EditText et_title = (EditText) findViewById(R.id.et_title);
    EditText et_content = (EditText) findViewById(R.id.et_contents);
    EditText et_Address = (EditText) findViewById(R.id.et_Mission_Address);

    TextView tv_address = (TextView) findViewById(R.id.tv_address);
    TextView tv_address2 = (TextView) findViewById(R.id.tv_address_2);

    ImageView iv_qr = (ImageView)findViewById(R.id.iv_qr);
    ImageView iv_nfc = (ImageView)findViewById(R.id.iv_nfc);
    ImageView iv_gps = (ImageView)findViewById(R.id.iv_gps);
    ImageView iv_offline = (ImageView)findViewById(R.id.iv_offline);

    Button bt_toLat = (Button) findViewById(R.id.bt_addToLat);




    //ImageView

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
