package com.impact.tripble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Select_Location extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MarkerOptions mOptions = new MarkerOptions();
    private View mLayout;
    private LocationRequest locationRequest;
    private static final String TAG = "googlemap";
    private static final int UPDATE_INTERVAL_MS = 1000;
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500;
    int count_nu = 0;
    Double latitude_intent;
    Double longitude_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_map);

        setmap();
        //bottomNavigator();
    }

    public void setmap() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mLayout = findViewById(R.id.layout_main);

        Log.d(TAG, "onCreate");

        locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(UPDATE_INTERVAL_MS).setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);

        //mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(Select_Location.this);
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                if (count_nu == 0) {
                    mOptions.title("마커 좌표");
                    Double latitude = point.latitude; // 위도 인텐트 예정
                    Double longitude = point.longitude; // 경도 인텐트 예정

                    latitude_intent = latitude;
                    longitude_intent = longitude;

                    Toast.makeText(Select_Location.this,latitude_intent.toString() + longitude_intent.toString(), Toast.LENGTH_LONG).show();
                    mOptions.snippet(latitude.toString() + ", " + longitude.toString());
                    mOptions.position(new LatLng(latitude, longitude));
                    googleMap.addMarker(mOptions);
                    count_nu++;
                }
            }
        });

        Button button_cancel = (Button)findViewById(R.id.cancel);
        Button button_select = (Button)findViewById(R.id.select);

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_nu = 0;
                mMap.clear();
            }
        });

        // TODO: 2019-08-16-016 오류 시작
        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_nu > 0) {
                    Intent intent = new Intent(Select_Location.this, setMission.class);

                    Toast.makeText(Select_Location.this,latitude_intent.toString() + longitude_intent.toString(), Toast.LENGTH_LONG).show();

                    intent.putExtra("latitude", latitude_intent);
                    intent.putExtra("longitude", longitude_intent);

                    intent.putExtra("test", "testString");

                    setResult(RESULT_OK, intent);

                    finish();

                // TODO: 2019-08-16-016 오류 끝
                }
            }
        });
    }
}

