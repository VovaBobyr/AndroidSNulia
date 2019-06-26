package ru.myrusakov.parsinginternet;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double lat = Double.parseDouble(((EditText) findViewById(R.id.lat)).getText().toString());
                double lon = Double.parseDouble(((EditText) findViewById(R.id.lon)).getText().toString());
                new WeatherTask(lat, lon).execute();
            }
        });
    }

    private class WeatherTask extends AsyncTask<Void, Void, String> {

        private double lat;
        private double lon;
        private String key = "2e3592e13c514adeb0950bcfb6c3d301";

        public WeatherTask(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String content = getContent("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + key);
            return content;
        }

        protected void onPostExecute(String content) {
            try {
                JSONObject json = new JSONObject(content);
                double temp = json.getJSONObject("main").getDouble("temp") / 10;
                tv.setText("Температура: " + temp + " \u2103");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private String getContent(String path) {
            try {
                URL url = new URL(path);
                HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setReadTimeout(20000);
                c.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
                String content = "";
                String line = "";
                while ((line = reader.readLine()) != null) {
                    content += line + "\n";
                }
                System.out.println(content);
                return content;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

    }

}
