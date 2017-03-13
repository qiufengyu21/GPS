package com.example.android.gps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {
    String json_string, prof_lat, prof_lon;
    JSONObject jsonObject;
    JSONArray jsonArray;
    StudentLocationsAdapter studentLocationsAdapter;
    ListView listView;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);

        listView = (ListView) findViewById(R.id.listview);
        studentLocationsAdapter = new StudentLocationsAdapter(this, R.layout.row_layout);
        listView.setAdapter(studentLocationsAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        prof_lat = getIntent().getExtras().getString("prof_lat");
        prof_lon = getIntent().getExtras().getString("prof_lon");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server response");
            int count = 0;
            String name, unityid, time, lon, lat;

            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("name");
                unityid = jo.getString("unityid");
                time = jo.getString("time");
                lon = jo.getString("lon");
                lat = jo.getString("lat");

                StudentLocations studentLocations = new StudentLocations(name, unityid, time, lon, lat, prof_lat, prof_lon);

                studentLocationsAdapter.add(studentLocations);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        tv = (TextView) findViewById(R.id.textView6);
//        tv.setText(prof_lat + " and " + prof_lon);

    }
}
