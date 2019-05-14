package com.impact.tripble;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtraMission extends AppCompatActivity {

    EditText et_title, et_address, et_position, et_contents, et_reward, et_image;
    Button bt_addToLag, bt_next;
    Spinner spinner;
    TextView tv_address;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    String title, address, position, contents, reward, image, complete, tag, host, sort;
    LatLng latLng;
    Intent receive_intent, send_intent;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_mission);

        receive_intent = getIntent();
        setMission();
        addToLag();

        bt_next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                title = et_title.getText().toString();
                address = tv_address.getText().toString();
                position = et_position.getText().toString();
                contents = et_contents.getText().toString();
                reward = et_reward.getText().toString();
                image = et_image.getText().toString();

                Mission mission = new Mission(title, latLng, position, contents, reward, image, complete, tag, host, sort);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mission", mission);

                send_intent = new Intent(ExtraMission.this, CreateMission.class);

                send_intent.putExtras(bundle);
                startActivity(send_intent);

            }
        });
    }

    public void setMission(){

        et_title = (EditText)findViewById(R.id.title);
        et_address = (EditText)findViewById(R.id.address);
        et_position = (EditText)findViewById(R.id.position);
        et_contents = (EditText)findViewById(R.id.contents);
        et_reward = (EditText)findViewById(R.id.reward);
        et_image = (EditText)findViewById(R.id.image);
        bt_addToLag = (Button)findViewById(R.id.addToLat);
        bt_next = (Button)findViewById(R.id.bt_next);
        tv_address = (TextView)findViewById(R.id.tv_address);
        spinner = (Spinner)findViewById(R.id.spinner);
        host = receive_intent.getExtras().getString("host");
        tag = "hnu";
        sort = Integer.toString(receive_intent.getExtras().getInt("sort"));

        arrayList = new ArrayList<>();
        arrayList.add("qr");
        arrayList.add("nfc");
        arrayList.add("gps");
        arrayList.add("staff");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,arrayList);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                complete = arrayList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addToLag(){
        final Geocoder geocoder = new Geocoder(this);
        bt_addToLag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Address> list = null;
                List<Address> list2 = null;


                String str = et_address.getText().toString();

                //주소 혹은 지명 -> 좌표//
                try{
                    list = geocoder.getFromLocationName(str,10);
                }catch(IOException e){
                    e.printStackTrace();
                    Log.e("geoCoder","입출력 오류 - 서버에서 주소 변환시 에러 발생");
                }

                if(list != null){
                    if(list.size() == 0){
                        Toast.makeText(ExtraMission.this,"해당되는 주소 정보는 없습니다.", Toast.LENGTH_LONG).show();
                    }else{
                        Address addr = list.get(0);
                        latLng = new LatLng(addr.getLatitude(), addr.getLongitude());
                        //Toast.makeText(ExtraMission.this, Double.toString(addr.getLatitude()) + " / "+Double.toString(addr.getLongitude()), Toast.LENGTH_LONG).show();
                        //todo 값 intent로 넘기고 shared 저장

                        //좌표 -> 주소//
                        try{
                            double d1 = addr.getLatitude();
                            double d2 = addr.getLongitude();
                            list2 = geocoder.getFromLocation(d1,d2,10);
                        }catch (IOException e ){
                            e.printStackTrace();
                            Log.e("geoCoder","입출력 오류 - 서버에서 주소변환시 에러발생");
                        }

                        if(list2 != null){
                            if(list2.size() == 0){
                                Toast.makeText(ExtraMission.this, "해당되는 주소 정보는 없습니다.", Toast.LENGTH_LONG).show();
                            }else{
                                tv_address.setText(list2.get(0).getAddressLine(0).substring(5,list2.get(0).getAddressLine(0).length()));//대전광역시 대덕구 오정동 한남로 70
                            }
                        }

                    }
                }
            }
        });
    }
}