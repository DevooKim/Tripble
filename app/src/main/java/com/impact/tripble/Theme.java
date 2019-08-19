package com.impact.tripble;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//호스트 - 테마 - 미션
public class Theme extends Host{
    String Theme_Name;
    String Reward;
    List<Mission> mMission;

    public Theme(){
        super();
    }

    public Theme(String Theme_Name, String Reward, String theme){
        this.Theme_Name = Theme_Name;
        this.Reward = Reward;
        this.mMission = new ArrayList<Mission>();
    }

    protected void Add_Mission(Mission mission){
        this.mMission.add(mission);
    }

    protected List get_LatLng(){
        List<LatLng> mLatLng = new ArrayList<LatLng>();

        Iterator iterator = mMission.iterator();
        while(iterator.hasNext()){
            Mission mission = (Mission) iterator.next();
            mLatLng.add(mission.getLatLng());
        }
    }



}
