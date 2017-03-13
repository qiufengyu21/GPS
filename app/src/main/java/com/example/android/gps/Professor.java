package com.example.android.gps;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Professor extends AppCompatActivity {
    String lon, lat;
    TextView tv_lon, tv_lat;

    String JSON_STRING;
    String result_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_layout);

        lon = getIntent().getExtras().getString("lon");
        lat = getIntent().getExtras().getString("lat");

        tv_lat = (TextView) findViewById(R.id.textView2);
        tv_lon = (TextView) findViewById(R.id.textView3);
        tv_lat.append(lat);
        tv_lon.append(lon);
    }

    public void getData(View view) {
        new BackgroundTask().execute();
    }

    public void parseData(View view) {
        if (result_string == null) {
            Toast.makeText(getApplicationContext(), "Please get the JSON file", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, DisplayListView.class);
            intent.putExtra("json_data", result_string);
            intent.putExtra("prof_lat", lat);
            intent.putExtra("prof_lon", lon);
            startActivity(intent);
        }
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "http://unmasked-hats.000webhostapp.com/json_get_data.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView = (TextView) findViewById(R.id.textView4);
            textView.setText(result);
            result_string = result;
            if (result_string == null) {
                Toast.makeText(getApplicationContext(), "Failed to retrieve the JSON file, please try again.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Successfully retrieved the JSON file!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
