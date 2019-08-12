package com.impact.tripble;

import java.io.Serializable;

public class Mission_Test {

    private int _id;

    String name;
    String latitude;
    String longitude;
    String tel;
    String host;

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }

    public String getTel() {
        return tel;
    }

    public String getHost() {
        return host;
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
