package com.example.android.gps;

/**
 * Created by Qiufeng on 2017/3/12 0012.
 */

public class StudentLocations {
    private String name, unityid, time, lon, lat;

    public StudentLocations(String name, String unityid, String time, String lon, String lat) {
        this.setName(name);
        this.setUnityid(unityid);
        this.setTime(time);
        this.setLon(lon);
        this.setLat(lat);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnityid() {
        return unityid;
    }

    public void setUnityid(String unityid) {
        this.unityid = unityid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
