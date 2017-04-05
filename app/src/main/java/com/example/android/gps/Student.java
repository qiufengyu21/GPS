package com.example.android.gps;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Student extends AppCompatActivity {
    EditText t_nametext, t_idtext, t_lontext, t_lattext;
    String name, unityid, time;
    String lon, lat;
    TextView tv_lon, tv_lat, t_timetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_layout);

        lon = getIntent().getExtras().getString("lon");
        lat = getIntent().getExtras().getString("lat");

        tv_lat = (TextView) findViewById(R.id.textView2);
        tv_lon = (TextView) findViewById(R.id.textView3);
        t_nametext = (EditText) findViewById(R.id.t_nametext);
        t_idtext = (EditText) findViewById(R.id.t_idtext);
        t_timetext = (TextView) findViewById(R.id.t_timetext);

        // create a java timestamp object that represents the current time (i.e., a "current timestamp")
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
        //set the edit view to the current time and make it uneditable
        t_timetext.setText(String.valueOf(ourJavaTimestampObject));
        time = String.valueOf(ourJavaTimestampObject);
        t_timetext.setKeyListener(null);

        tv_lat.append(lat);
        tv_lon.append(lon);
    }

    public void sendData(View view) {
        name = t_nametext.getText().toString();
        unityid = t_idtext.getText().toString();

        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(name, unityid, time, lon, lat);
        finish();
    }

    class BackgroundTask extends AsyncTask<String, Void, String> {
        String add_info_url;

        @Override
        protected void onPreExecute() {
            add_info_url = "http://unmasked-hats.000webhostapp.com/add_info.php";
        }

        @Override
        protected String doInBackground(String... args) {
            String name, unityid, time, lon, lat;
            name = args[0];
            unityid = args[1];
            time = args[2];
            lon = args[3];
            lat = args[4];

            try {
                URL url = new URL(add_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data_string = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("unityid", "UTF-8") + "=" + URLEncoder.encode(unityid, "UTF-8") + "&" +
                        URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8") + "&" +
                        URLEncoder.encode("lon", "UTF-8") + "=" + URLEncoder.encode(lon, "UTF-8") + "&" +
                        URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "Your location has been sent successfully!";
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
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
    }
}
