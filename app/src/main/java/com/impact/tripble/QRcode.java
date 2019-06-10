package com.impact.tripble;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class QRcode extends AppCompatActivity {

    //view Objects
    private Button buttonScan;
    private final int REQUEST_MISSION = 200;
    private TextView textViewName, textViewAddress, textViewResult, Address;

    //qr code scanner object
    private IntentIntegrator qrScan;
    String url_name=" ";

    private final String CLEAR_URL = "http://www.hannam.ac.kr/main/";
    Button clear;
    Intent recvIntent;
    TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);

        //View Objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewAddress = (TextView) findViewById(R.id.textViewAddress);
        textViewResult = (TextView)  findViewById(R.id.textViewResult);
        clear = (Button) findViewById(R.id.clearButton);
        state = (TextView)findViewById(R.id.state);
        recvIntent = new Intent();

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //button onClick
        buttonScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scan option
                qrScan.setPrompt("Scanning...");
                //qrScan.setOrientationLocked(false);
                qrScan.initiateScan();
            }
        });

        textViewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url_name));
                startActivity(intent);
            }
        });

        textViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url_name));
                startActivity(intent);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QRcode.this, Popup.class);
                startActivityForResult(intent,REQUEST_MISSION);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(url_name.equals(CLEAR_URL)){
            clear.setVisibility(View.VISIBLE);
            clear.setClickable(true);
            state.setVisibility(View.INVISIBLE);
        }else{
            state.setVisibility(View.VISIBLE);
        }
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(QRcode.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면
                Toast.makeText(QRcode.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                try {
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());
                    textViewName.setText(obj.getString("name"));
                    textViewAddress.setText(obj.getString("address"));
                    Address = textViewAddress;
                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();
                    textViewResult.setText(result.getContents());
                    Address = textViewResult;
                }

                url_name = Address.getText().toString();
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

        super.onActivityResult(requestCode, resultCode, data);
        Intent intent;
        boolean temp;
        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case REQUEST_MISSION:

                    temp = data.getBooleanExtra("isClear", false);
                    intent = new Intent(QRcode.this, Mission_list.class);
                    intent.putExtra("isClear", temp);
                    intent.putExtra("key", 2);
                    startActivityForResult(intent, REQUEST_MISSION);
                    break;
                }
            }
        }
    }
