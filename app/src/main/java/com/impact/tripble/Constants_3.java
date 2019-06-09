package com.impact.tripble;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by brijesh on 15/4/17.
 */

public class Constants_3 {


    public static final String GEOFENCE_ID_STAN_UNI_3 = "STAN_UNI";
    public static final float GEOFENCE_RADIUS_IN_METERS = 30;

    /**
     * Map for storing information about stanford university in the Stanford.
     */
    public static final HashMap<String, LatLng> AREA_LANDMARKS_3 = new HashMap<String, LatLng>();

    static {
        // 특정 위치 배정
        AREA_LANDMARKS_3.put(GEOFENCE_ID_STAN_UNI_3, new LatLng(36.354020, 127.422689));
    }
}
