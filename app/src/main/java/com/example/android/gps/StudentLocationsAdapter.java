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
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            studentLocationHolder = new StudentLocationHolder();
            studentLocationHolder.tv_name = (TextView) row.findViewById(R.id.textView1);
            studentLocationHolder.tv_unityid = (TextView) row.findViewById(R.id.textView2);
            studentLocationHolder.tv_time = (TextView) row.findViewById(R.id.textView3);
            studentLocationHolder.tv_lon = (TextView) row.findViewById(R.id.textView4);
            studentLocationHolder.tv_lat = (TextView) row.findViewById(R.id.textView5);
            row.setTag(studentLocationHolder);
        }
        else{
            studentLocationHolder = (StudentLocationHolder) row.getTag();
        }
        StudentLocations studentLocations = (StudentLocations) this.getItem(position);
        studentLocationHolder.tv_name.setText(studentLocations.getName());
        studentLocationHolder.tv_unityid.setText(studentLocations.getUnityid());
        studentLocationHolder.tv_time.setText(studentLocations.getTime());
        studentLocationHolder.tv_lat.setText(studentLocations.getLat());
        studentLocationHolder.tv_lon.setText(studentLocations.getLon());
        return row;
    }

    static class StudentLocationHolder{
        TextView tv_name, tv_unityid, tv_time, tv_lon, tv_lat;
    }
}
