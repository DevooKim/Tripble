package com.impact.tripble;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gun0912.tedpermission.util.ObjectUtils;

//public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {
public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker currentMarker = null;

    private final int REQUEST_MISSION1 = 100;
    private final int REQUEST_MISSION2 = 200;
    private final int REQUEST_MISSION3 = 300;
    private final int REQUEST_MISSION4 = 400;
    private final int REQUEST_MISSION_R1 = 1000;
    private final int REQUEST_MISSION_R2 = 2000;
    private final int REQUEST_MISSION_R3 = 3000;
    private final int REQUEST_MISSION_R4 = 4000;

    private static final String TAG = "googlemap";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int UPDATE_INTERVAL_MS = 1000;
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500;

    private static final int PERISSIONS_REQUEST_CODE = 100;
    boolean needRequest = false;

    String[] REQUIED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    String title, content, longitude, latitude;
    Double latitude_double, longitude_double;

    Location mCurrentLocation;
    LatLng currentPostion;

    private FusedLocationProviderClient mFuesdLocationClient;
    private LocationRequest locationRequest;
    private  Location location;

    private View mLayout;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnmission = (Button)findViewById(R.id.first_bt);

        btnmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Mission_list.class);
                startActivity(intent);
            }
        });

        Button btnGift = (Button)findViewById(R.id.quard_bt);

        btnGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Gifticon.class);
                startActivity(intent);
            }
        });

        Button btnprofile = (Button)findViewById(R.id.second_bt);

        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });

        setmap();

        //bottomNavigator();
    }

    public void setmap(){

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mLayout = findViewById(R.id.layout_main);

        Log.d(TAG,"onCreate");

        locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(UPDATE_INTERVAL_MS).setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);

        //mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(MainActivity.this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng library = new LatLng(36.354020, 127.422689);

        MarkerOptions markerOptions_1 = new MarkerOptions();
        markerOptions_1.position(library).title("Histiory-1");
        markerOptions_1.snippet("미션 : 히스토리 카페를 방문해보자!");
        mMap.addMarker(markerOptions_1);

        LatLng engineer = new LatLng(36.352602,127.424841);

        MarkerOptions markerOptions_2 = new MarkerOptions();
        markerOptions_2.position(engineer).title("창업 마켓");
        markerOptions_2.snippet("미션 : 숨겨진 코드를 해석해라!");
        mMap.addMarker(markerOptions_2);

        LatLng CPD = new LatLng(36.353809,127.423121);

        MarkerOptions markerOptions_3 = new MarkerOptions();
        markerOptions_3.position(CPD).title("한남 HDF");
        markerOptions_3.snippet("미션 : 흩어진 카드의 순서를 맞춰라!");
        mMap.addMarker(markerOptions_3);

        LatLng Memorial_56 = new LatLng(36.351982,127.422199);

        MarkerOptions markerOptions_4 = new MarkerOptions();
        markerOptions_4.position(Memorial_56).title("56주년 기념관");
        markerOptions_4.snippet("미션 : 독수리 탈을 찾아라!");
        mMap.addMarker(markerOptions_4);

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(36.354440,127.421142)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));

        mMap.setOnInfoWindowClickListener(infoWindowClickListener);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        longitude = intent.getStringExtra("longitude");
        latitude = intent.getStringExtra("latitude");
        longitude_double = intent.getDoubleExtra("longitude_double",0);
        latitude_double = intent.getDoubleExtra("latitude_double",0);

        if(ObjectUtils.isEmpty(longitude) == true && ObjectUtils.isEmpty(latitude) == true )
        {
            Toast.makeText(MainActivity.this,latitude_double + "  " + longitude_double, Toast.LENGTH_LONG).show();

            LatLng Maker = new LatLng(longitude_double, latitude_double);

            MarkerOptions markerOptions_5 = new MarkerOptions();
            markerOptions_5.position(Maker).title(title);
            markerOptions_5.snippet(content);
            mMap.addMarker(markerOptions_5);
        }
    }

    GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            String markerId = marker.getId();

            switch (markerId)
            {
                case "m0":
                    Intent intent1 = new Intent(MainActivity.this, Mission_info.class);
                    intent1.putExtra("markerId",markerId);
                    startActivityForResult(intent1, REQUEST_MISSION1);
                    break;

                case "m1":
                    Intent intent2 = new Intent(MainActivity.this, Mission_info.class);
                    intent2.putExtra("markerId",markerId);
                    startActivityForResult(intent2, REQUEST_MISSION2);
                    break;

                case "m2":
                    Intent intent3 = new Intent(MainActivity.this, Mission_info.class);
                    intent3.putExtra("markerId",markerId);
                    startActivityForResult(intent3, REQUEST_MISSION3);
                    break;

                case "m3":
                    Intent intent4 = new Intent(MainActivity.this, Mission_info.class);
                    intent4.putExtra("markerId",markerId);
                    startActivityForResult(intent4, REQUEST_MISSION4);
                    break;
            }
        }
    };
/*
    protected void bottomNavigator(){

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigationBar);

        FixBottomIcon.disableShiftMode(bottomNavigationView);
    }
    */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent;
        boolean temp;
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_MISSION1:
                    temp = data.getBooleanExtra("isClear", false);
                    if(temp)
                        Log.d("test", "m1 - temp: true");

                    intent = new Intent(MainActivity.this, Mission_list.class);
                    intent.putExtra("isClear", temp);
                    intent.putExtra("key",1);
                    startActivityForResult(intent, REQUEST_MISSION_R1);
                    break;
                case REQUEST_MISSION2:
                    temp = data.getBooleanExtra("isClear", false);
                    if(temp)
                        Log.d("test", "m1 - temp: true");
                    intent = new Intent(MainActivity.this, Mission_list.class);
                    intent.putExtra("isClear", temp);
                    intent.putExtra("key",2);
                    startActivityForResult(intent, REQUEST_MISSION_R2);
                    break;
                case REQUEST_MISSION3:
                    temp = data.getBooleanExtra("isClear", false);
                    if(temp)
                        Log.d("test", "m1 - temp: true");
                    intent = new Intent(MainActivity.this, Mission_list.class);
                    intent.putExtra("isClear", temp);
                    intent.putExtra("key",3);
                    startActivityForResult(intent, REQUEST_MISSION_R3);
                    break;
                case REQUEST_MISSION4:
                    temp = data.getBooleanExtra("isClear", false);
                    if(temp)
                        Log.d("test", "m1 - temp: true");
                    intent = new Intent(MainActivity.this, Mission_list.class);
                    intent.putExtra("isClear", temp);
                    intent.putExtra("key",4);
                    startActivityForResult(intent, REQUEST_MISSION_R4);
                    break;

                case REQUEST_MISSION_R1:
                    //todo 마커 지우기//
                    break;
            }
        }
    }
}