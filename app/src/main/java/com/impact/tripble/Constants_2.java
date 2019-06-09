package com.impact.tripble;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by brijesh on 15/4/17.
 */

public class Constants_2 {


    public static final String GEOFENCE_ID_STAN_UNI_2 = "STAN_UNI";
    public static final float GEOFENCE_RADIUS_IN_METERS = 30;

    /**
     * Map for storing information about stanford university in the Stanford.
     */
    public static final HashMap<String, LatLng> AREA_LANDMARKS_2 = new HashMap<String, LatLng>();

    static {
        // 특정 위치 배정
        AREA_LANDMARKS_2.put(GEOFENCE_ID_STAN_UNI_2, new LatLng(36.354742, 127.421985));
    }
}
