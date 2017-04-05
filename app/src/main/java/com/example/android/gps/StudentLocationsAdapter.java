package com.example.android.gps;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qiufeng on 2017/3/12 0012.
 */

public class StudentLocationsAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public StudentLocationsAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(StudentLocations object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        StudentLocationHolder studentLocationHolder;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            studentLocationHolder = new StudentLocationHolder();
            studentLocationHolder.tv_name = (TextView) row.findViewById(R.id.textView1);
            studentLocationHolder.tv_unityid = (TextView) row.findViewById(R.id.textView2);
            studentLocationHolder.tv_time = (TextView) row.findViewById(R.id.textView3);
            studentLocationHolder.tv_lon = (TextView) row.findViewById(R.id.textView4);
            studentLocationHolder.tv_lat = (TextView) row.findViewById(R.id.textView5);
            studentLocationHolder.tv_lat2 = (TextView) row.findViewById(R.id.textView6);
            row.setTag(studentLocationHolder);
        } else {
            studentLocationHolder = (StudentLocationHolder) row.getTag();
        }
        StudentLocations studentLocations = (StudentLocations) this.getItem(position);

        studentLocationHolder.tv_name.setText(studentLocations.getName());
        studentLocationHolder.tv_unityid.setText(studentLocations.getUnityid());
        studentLocationHolder.tv_time.setText(studentLocations.getTime());
        studentLocationHolder.tv_lat.setText(studentLocations.getLat());
        studentLocationHolder.tv_lon.setText(studentLocations.getLon());
        double prof_lat = Double.parseDouble(studentLocations.getProf_lat());
        double prof_lon = Double.parseDouble(studentLocations.getProf_lon());
        double lat = Double.parseDouble(studentLocations.getLat());
        double lon = Double.parseDouble(studentLocations.getLon());
        double distance = distance(prof_lat, prof_lon, lat, lon, "M");
        Double rawDistance = BigDecimal.valueOf(distance).setScale(2, RoundingMode.HALF_UP).doubleValue();
        studentLocationHolder.tv_lon.setText(String.valueOf(rawDistance) + "M");
        if (distance <= 50) {
            studentLocationHolder.tv_lat.setText("Present");
            studentLocationHolder.tv_lat2.setText("");
        } else {
            studentLocationHolder.tv_lat.setText("");
            studentLocationHolder.tv_lat2.setText("Absent");
        }

        return row;
    }

    static class StudentLocationHolder {
        TextView tv_name, tv_unityid, tv_time, tv_lon, tv_lat, tv_lat2, tv_distance, tv_isPresent;
    }


    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "M") {
            dist = dist * 1.609344 * 1000;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    /* :: This function converts decimal degrees to radians : */
    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    /* :: This function converts radians to decimal degrees : */
    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
