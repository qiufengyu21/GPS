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
    String json_string;
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

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server response");
            int count = 0;
            String name, unityid, time, lon, lat;
            Log.d("displayview", String.valueOf(jsonArray.length()));
            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("name");
                unityid = jo.getString("unityid");
                time = jo.getString("time");
                lon = jo.getString("lon");
                lat = jo.getString("lat");

                StudentLocations studentLocations = new StudentLocations(name, unityid, time, lon, lat);

                studentLocationsAdapter.add(studentLocations);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
