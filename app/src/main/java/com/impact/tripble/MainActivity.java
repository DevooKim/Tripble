package com.impact.tripble;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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
        Button bt3 = (Button)findViewById(R.id.bt3);

        setButton SB = new setButton(main_activity);

        bt1.setOnClickListener(SB);
        bt2.setOnClickListener(SB);
        bt3.setOnClickListener(SB);

    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;
        init_marker(mMap);
    }

    public void setMarker() {

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void init_marker(GoogleMap mMap){
        LatLng latLng = new LatLng(36.354018, 127.422446);
        Group group = new Group(latLng, "한남대학교", "hnu");
        //프로토타입: 초기세팅 - 한남대; => 추후 생성함수 구현.


        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(group.latLng);

        mMap.setOnInfoWindowClickListener(infoWindowClickListener_host);

        CustomInfoWindow_Group customInfoWindow_group = new CustomInfoWindow_Group(this);
        mMap.setInfoWindowAdapter(customInfoWindow_group);


        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(group.latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
        mMap.addMarker(markerOptions);
    }

    GoogleMap.OnInfoWindowClickListener infoWindowClickListener_host = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            Intent intent = new Intent(MainActivity.this, InitWindowInfo.class);
            startActivity(intent);
        }
    };

    public Host setHost(){
        Host host;
        host = new Host("창업지원단", "타이틀", "DFGN", "042-629-0000", "1");
        return host;
    }

    public Mission setMission(){
        Mission mission;
        LatLng latLng = new LatLng(36.356325, 127.419504);
        mission = new Mission("코끼리코", latLng, "공과대", "미션내용", "보상", "사진", "qr", "hnu", "host", "1");

        return mission;
    }

    protected void bottomNavigator(){
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigationBar);
        FixBottomIcon.disableShiftMode(bottomNavigationView);
    }
}