package com.example.android.gps;

/**
 * Created by Qiufeng on 2017/3/12 0012.
 */

public class StudentLocations {
    private String name, unityid, time, lon, lat;
    private String prof_lat, prof_lon;

    public StudentLocations(String name, String unityid, String time, String lon, String lat, String prof_lat, String prof_lon) {
        this.setName(name);
        this.setUnityid(unityid);
        this.setTime(time);
        this.setLon(lon);
        this.setLat(lat);
        this.setProf_lat(prof_lat);
        this.setProf_lon(prof_lon);
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

    public String getProf_lat() {
        return prof_lat;
    }

    public void setProf_lat(String prof_lat) {
        this.prof_lat = prof_lat;
    }

    public String getProf_lon() {
        return prof_lon;
    }

    public void setProf_lon(String prof_lon) {
        this.prof_lon = prof_lon;
    }
}
