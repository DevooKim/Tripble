package com.impact.tripble;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class setMission extends AppCompatActivity {

    private static final int PICK_FROM_ALBUM = 1;
    private File tempFile;
    EditText et_title, et_address, et_position, et_contents;
    ImageView iv_image, iv_qr, iv_nfc, iv_gps, iv_offline;
    Button bt_addToLag, bt_next;
    Spinner spinner;
    TextView tv_address,tv_address_2;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    String title, position, contents, complete;
    Bitmap image;
    LatLng latLng;

    Intent intent;

    byte[] byteArray;
    ByteArrayOutputStream stream;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_add_activity);

        et_title = (EditText)findViewById(R.id.title);
        et_address = (EditText)findViewById(R.id.address);
        et_position = (EditText)findViewById(R.id.position);
        et_contents = (EditText)findViewById(R.id.contents);

        iv_image = (ImageView) findViewById(R.id.image);
        bt_addToLag = (Button)findViewById(R.id.addToLat);
        bt_next = (Button)findViewById(R.id.bt_next);
        tv_address = (TextView)findViewById(R.id.tv_address);
        tv_address_2 = (TextView)findViewById(R.id.tv_address_2);
        iv_qr = (ImageView)findViewById(R.id.iv_qr);
        iv_nfc = (ImageView)findViewById(R.id.iv_nfc);
        iv_gps = (ImageView)findViewById(R.id.iv_gps);
        iv_offline = (ImageView)findViewById(R.id.iv_offline);

        tedPermission();
        addToLag();

        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, PICK_FROM_ALBUM);
            }
        });



        bt_next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                title = et_title.getText().toString();
                position = et_position.getText().toString();
                contents = et_contents.getText().toString();

                //Mission mission = new Mission(title, latLng, position, contents, image, complete);


                // intent.putExtra("position", position);
//                send_intent.putExtras(bundle);
                //intent.putExtra("mission", mission);
                //setResult(RESULT_OK,intent);
//                finish();




                intent = new Intent();
                intent.putExtra("title", title);
                intent.putExtra("contents", contents);
                intent.putExtra("position",position);
                intent.putExtra("image", byteArray);
                intent.putExtra("complete", complete);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }



    protected void missionSelect(View v){
        switch (v.getId()){
            case R.id.iv_qr:
                iv_qr.setImageResource(R.drawable.bt_qr_click);
                iv_nfc.setImageResource(R.drawable.add_bt_nfc);
                iv_gps.setImageResource(R.drawable.add_bt_gps);
                iv_offline.setImageResource(R.drawable.add_bt_off);
                complete = "qr";
                break;
            case R.id.iv_nfc:
                iv_qr.setImageResource(R.drawable.add_bt_qr);
                iv_nfc.setImageResource(R.drawable.bt_nfc_click);
                iv_gps.setImageResource(R.drawable.add_bt_gps);
                iv_offline.setImageResource(R.drawable.add_bt_off);
                complete = "nfc";
                break;
            case R.id.iv_gps:
                iv_qr.setImageResource(R.drawable.add_bt_qr);
                iv_nfc.setImageResource(R.drawable.add_bt_nfc);
                iv_gps.setImageResource(R.drawable.bt_gps_click);
                iv_offline.setImageResource(R.drawable.add_bt_off);
                complete = "gps";
                break;
            case R.id.iv_offline:
                iv_qr.setImageResource(R.drawable.add_bt_qr);
                iv_nfc.setImageResource(R.drawable.add_bt_nfc);
                iv_gps.setImageResource(R.drawable.add_bt_gps);
                iv_offline.setImageResource(R.drawable.bt_offline_click);
                complete = "offline";
                break;
        }
    }

    public void addToLag() {
        final Geocoder geocoder = new Geocoder(this);
        bt_addToLag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*List<Address> list = null;
                List<Address> list2 = null;
                */
                Intent intent = new Intent(setMission.this, Select_Location.class);
                startActivityForResult(intent,100);
            }

        });
    }

/*
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
                        Toast.makeText(setMission.this,"해당되는 주소 정보는 없습니다.", Toast.LENGTH_LONG).show();
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
                                Toast.makeText(setMission.this, "해당되는 주소 정보는 없습니다.", Toast.LENGTH_LONG).show();
                            }else{
                                tv_address.setText(list2.get(0).getAddressLine(0).substring(5,list2.get(0).getAddressLine(0).length()));//대전광역시 대덕구 오정동 한남로 70
                            }
                        }
                    }
                }
            }
        });
    }
*/

    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Intent intent = getIntent();

        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 100:
                    Double latitude_intent = data.getDoubleExtra("latitude",0);//여기가 문제네...
                    Double longitude_intent = data.getDoubleExtra("longitude",0);

                    //Toast.makeText(setMission.this,Double.toString(latitude_intent) + Double.toHexString(longitude_intent), Toast.LENGTH_LONG).show();

                    String latitude = Double.toString(latitude_intent);
                    String longitude = Double.toString(longitude_intent);

                    tv_address.setText(latitude);
                    tv_address_2.setText(longitude);
                    break;
            }
        }

        if (resultCode != Activity.RESULT_OK) {

            if(tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        tempFile = null;
                    }
                }
            }

            return;
        }

        if (requestCode == PICK_FROM_ALBUM) {

            Uri photoUri = data.getData();

            Cursor cursor = null;

            try {

                /*
                 *  Uri 스키마를
                 *  content:/// 에서 file:/// 로  변경한다.
                 */
                String[] proj = { MediaStore.Images.Media.DATA };

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();

        }
    }

    private void setImage() {

        BitmapFactory.Options options = new BitmapFactory.Options();
        image = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);

        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byteArray = stream.toByteArray();
        iv_image.setImageBitmap(image);

    }
}