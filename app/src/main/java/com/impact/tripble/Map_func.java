package com.impact.tripble;

import android.app.Activity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class Map_func {

    private Activity mActivity;
    GoogleMap mMap;

    public Map_func(GoogleMap mMap, Activity mActivity){
        this.mMap = mMap;
        this.mActivity = mActivity;
    }

    public void test(GoogleMap googleMap){
        mMap = googleMap;

        final LatLng defaultLocation = new LatLng(36.351475, 127.426131);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(defaultLocation);

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
        mMap.addMarker(markerOptions);
    }

    //세부 미션 등록//
    protected void onAddMarker(Mission mission){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(mission.latLng).title(mission.title);

        mMap.setOnInfoWindowClickListener(infoWindowClickListener);

        CustomInfoWindow customInfoWindow = new CustomInfoWindow(mActivity);
        mMap.setInfoWindowAdapter(customInfoWindow);

        //Marker marker = mMap.addMarker(markerOptions);
        mMap.addMarker(markerOptions);
    }

    //호스트 등록//
    protected void onAddHostMarker(Host host){
        MarkerOptions markerOptions = new MarkerOptions();
        //markerOptions.position(host.latLng).title(host.title);

        mMap.setOnInfoWindowClickListener(infoWindowClickListener_host);

        CustomInfoWindow_Group customInfoWindow_group = new CustomInfoWindow_Group(mActivity);
        mMap.setInfoWindowAdapter(customInfoWindow_group);

        //Marker marker = mMap.addMarker(markerOptions);
        mMap.addMarker(markerOptions);

    }

    GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            String markerId = marker.getId();
            Toast.makeText(mActivity, "정보창 클릭 테스트", Toast.LENGTH_LONG).show();
        }
    };

    GoogleMap.OnInfoWindowClickListener infoWindowClickListener_host = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            String markerId = marker.getId();
            Toast.makeText(mActivity, "정보창 클릭 테스트", Toast.LENGTH_LONG).show();
        }
    };

}
