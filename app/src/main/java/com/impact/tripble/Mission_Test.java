package com.impact.tripble;

import java.io.Serializable;

public class Mission_Test implements Serializable{

    private int _id;

    String name;
    String latitude;
    String longitude;
    String tel;
    String host;

    public int get_id() {
        return this._id;
    }

    public String getName() {
        return this.name;
    }
    public String getLatitude() {
        return this.latitude;
    }
    public String getLongitude() {
        return this.longitude;
    }

    public String getTel() {
        return this.tel;
    }

    public String getHost() {
        return this.host;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
