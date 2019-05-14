package com.impact.tripble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class setHost extends AppCompatActivity {

    Button bt_next;
    EditText et_host_name, et_address, et_host_call, et_host_tag;

    String address, name, call, tag;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_host);

        setHost();
        //addToLat();

    }

    public void setHost(){


        //bt_addToLat = (Button) findViewById(R.id.addToLat);
        bt_next = (Button)findViewById(R.id.next);
        et_host_name = (EditText)findViewById(R.id.host_name);
        et_address = (EditText)findViewById(R.id.et_address);
        et_host_call = (EditText)findViewById(R.id.host_call);
        et_host_tag = (EditText)findViewById(R.id.host_tag);
        //tv_address = (TextView)findViewById(R.id.tv_address);
       //et_detailAddress = (EditText)findViewById(R.id.detailAddress);



        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();
                int sort = random.nextInt(100); //호스트가 여러개 미션을 생성할 경우 구분 //공과대 학생회: 미션1, 미션2...

                Intent intent = new Intent(setHost.this, CreateMission.class);

                //address = tv_address.getText().toString();
                name = et_host_name.getText().toString();
                call = et_host_call.getText().toString();
                //tv_address.setText(address + et_detailAddress.getText().toString());    //입력한 주소와 상세주롤 합하여 출력한다.
                address = et_address.getText().toString();
                tag = et_host_tag.getText().toString();

                intent.putExtra("address", address);
                intent.putExtra("name", name);
                intent.putExtra("call", call);
                intent.putExtra("sort", sort);

                startActivity(intent);
            }
        });


    }

//    public void addToLat(){
//
//
//        bt_addToLat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<Address> list = null;
//                //Geocoder geocoder = new Geocoder(setHost.this,this);
//                String find_address = et_address.getText().toString();
//
//                try{
//                    list = geocoder.getFromLocationName(find_address, 1);
//                } catch (IOException e){
//                    e.printStackTrace();
//                    Log.e("geoCoder", "입출력 오류: 주소 -> 좌표 변환 에러");
//                }
//
//                if(list != null){
//                    if(list.size() == 0){
//                        Toast.makeText(setHost.this,"해당되는 주소가 없습니다.", Toast.LENGTH_LONG).show();
//                    }else{
//                        latLng = new LatLng(list.get(0).getLatitude(), list.get(0).getLongitude());
//
//                        String a = Double.toString(latLng.latitude);
//                        String b = Double.toString(latLng.longitude);
//                        Toast.makeText(setHost.this, a+"/"+b,Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//        });
//
//    }


}
