package ru.myrusakov.weatherwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link WeatherWidgetConfigureActivity WeatherWidgetConfigureActivity}
 */
public class WeatherWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.weather_widget);

        try {
            double lat = Double.parseDouble(WeatherWidgetConfigureActivity.loadTitlePref(context, appWidgetId, "lat"));
            double lon = Double.parseDouble(WeatherWidgetConfigureActivity.loadTitlePref(context, appWidgetId, "lon"));
            WeatherTask task = new WeatherTask(appWidgetManager, appWidgetId, views, lat, lon);
            task.execute();
        } catch (NumberFormatException ex) {}

        Intent intent = new Intent(context, WeatherWidgetConfigureActivity.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        PendingIntent pIntent = PendingIntent.getActivity(context, appWidgetId, intent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            WeatherWidgetConfigureActivity.deleteTitlePref(context, appWidgetId, "lat");
            WeatherWidgetConfigureActivity.deleteTitlePref(context, appWidgetId, "lon");
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static class WeatherTask extends AsyncTask<Void, Void, String> {

        private AppWidgetManager appWidgetManager;
        private int appWidgetId;
        private RemoteViews views;
        private double lat;
        private double lon;
        private String key = "2e3592e13c514adeb0950bcfb6c3d301";

        public WeatherTask(AppWidgetManager appWidgetManager,
                           int appWidgetId, RemoteViews views, double lat, double lon) {
            this.appWidgetManager = appWidgetManager;
            this.appWidgetId = appWidgetId;
            this.views = views;
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
                views.setTextViewText(R.id.appwidget_text, "Температура: " + Math.round(temp) + " \u2103");
                appWidgetManager.updateAppWidget(appWidgetId, views);
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

