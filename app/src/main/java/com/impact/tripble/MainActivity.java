package com.impact.tripble;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

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

//public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {
public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker currentMarker = null;

    private final int REQUEST_MISSION1 = 100;
    private final int REQUEST_MISSION2 = 200;
    private final int REQUEST_MISSION3 = 300;
    private final int REQUEST_MISSION4 = 400;

    private static final String TAG = "googlemap";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int UPDATE_INTERVAL_MS = 1000;
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500;

    private static final int PERISSIONS_REQUEST_CODE = 100;
    boolean needRequest = false;

    String[] REQUIED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

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

        testButton();
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


    public void testButton(){

        Button bt3 = (Button)findViewById(R.id.bt3);
        Button bt4 = (Button)findViewById(R.id.bt4);
        Button bt5 = (Button)findViewById(R.id.bt5);

        setButton SB = new setButton(MainActivity.this);

        bt3.setOnClickListener(SB);
        bt4.setOnClickListener(SB);
        bt5.setOnClickListener(SB);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng library = new LatLng(36.352709, 127.423285);

        MarkerOptions markerOptions_1 = new MarkerOptions();
        markerOptions_1.position(library).title("중앙도서관");
        mMap.addMarker(markerOptions_1);

        LatLng engineer = new LatLng(36.356301,127.419467);

        MarkerOptions markerOptions_2 = new MarkerOptions();
        markerOptions_2.position(engineer).title("공과대학");
        mMap.addMarker(markerOptions_2);

        LatLng CPD = new LatLng(36.353809,127.423121);

        MarkerOptions markerOptions_3 = new MarkerOptions();
        markerOptions_3.position(CPD).title("한남 HDF");
        mMap.addMarker(markerOptions_3);

        LatLng Memorial_56 = new LatLng(36.351982,127.422199);

        MarkerOptions markerOptions_4 = new MarkerOptions();
        markerOptions_4.position(Memorial_56).title("56주년 기념관");
        mMap.addMarker(markerOptions_4);

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(36.354440,127.421142)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));

        mMap.setOnInfoWindowClickListener(infoWindowClickListener);
    }

    GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            String markerId = marker.getId();

            switch (markerId)
            {
                case "m0":
                    Intent intent1 = new Intent(MainActivity.this, GEOMain.class);
                    startActivityForResult(intent1, REQUEST_MISSION1);
                    break;

                case "m1":
                    Intent intent2 = new Intent(MainActivity.this, QRcode.class);
                    startActivityForResult(intent2, REQUEST_MISSION2);
                    break;

                case "m2":
                    Intent intent3 = new Intent(MainActivity.this, offline.class);
                    startActivityForResult(intent3, REQUEST_MISSION3);
                    break;

                case "m3":
                    Intent intent4 = new Intent(MainActivity.this, NFC.class);
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
}