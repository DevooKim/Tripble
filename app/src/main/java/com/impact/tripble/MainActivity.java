package com.impact.tripble;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

//public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {
public class MainActivity extends FragmentActivity implements OnMapReadyCallback{

    GoogleMap mMap;
    private Activity main_activity = MainActivity.this;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMarker();

        testButton();
        bottomNavigator();
    }

    public void testButton(){
        Button bt1 = (Button)findViewById(R.id.bt1);
        Button bt2 = (Button)findViewById(R.id.bt2);
        setButton SB = new setButton(main_activity);

        bt1.setOnClickListener(SB);
        bt2.setOnClickListener(SB);
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;
        host_marker(mMap);
    }

    public void setMarker() {

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void host_marker(GoogleMap mMap){
        Host host = setHost();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(host.latLng);

        mMap.setOnInfoWindowClickListener(infoWindowClickListener_host);

        CustomInfoWindow_host customInfoWindow_host = new CustomInfoWindow_host(this);
        mMap.setInfoWindowAdapter(customInfoWindow_host);


        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(host.latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
        mMap.addMarker(markerOptions);
    }

    GoogleMap.OnInfoWindowClickListener infoWindowClickListener_host = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            Intent intent = new Intent(MainActivity.this,setMission.class);
            startActivity(intent);
        }
    };

    public Host setHost(){
        Host host;
        LatLng latLag = new LatLng(36.354018, 127.422446);
        host = new Host("한남대", "타이틀", latLag, "DFGN", "042-629-0000", "1");
        return host;
    }

    public Mission setMission(){
        Mission mission;
        mission = new Mission(36.356325, 127.419504, "공과대", "제목", "내용", "보상", "사진", "qr", "1");

        return mission;
    }

    protected void bottomNavigator(){
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigationBar);
        FixBottomIcon.disableShiftMode(bottomNavigationView);
    }
}