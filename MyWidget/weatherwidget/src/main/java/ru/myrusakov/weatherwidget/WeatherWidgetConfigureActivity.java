package ru.myrusakov.weatherwidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * The configuration screen for the {@link WeatherWidget WeatherWidget} AppWidget.
 */
public class WeatherWidgetConfigureActivity extends Activity {

    private static final String PREFS_NAME = "ru.myrusakov.weatherwidget.WeatherWidget";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    EditText lat;
    EditText lon;

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = WeatherWidgetConfigureActivity.this;

            // When the button is clicked, store the string locally
            String latText = lat.getText().toString();
            String lonText = lon.getText().toString();
            saveTitlePref(context, mAppWidgetId, "lat", latText);
            saveTitlePref(context, mAppWidgetId, "lon", lonText);

            // It is the responsibility of the configuration activity to update the app widget
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            WeatherWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);

            // Make sure we pass back the original appWidgetId
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    };

    public WeatherWidgetConfigureActivity() {
        super();
    }

    // Write the prefix to the SharedPreferences object for this widget
    static void saveTitlePref(Context context, int appWidgetId, String name, String text) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(appWidgetId + "_" + name, text);
        prefs.apply();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
    static String loadTitlePref(Context context, int appWidgetId, String name) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(appWidgetId + "_" + name, "");
    }

    static void deleteTitlePref(Context context, int appWidgetId, String name) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(appWidgetId + "_" + name);
        prefs.apply();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.weather_widget_configure);
        lat = (EditText) findViewById(R.id.lat);
        lon = (EditText) findViewById(R.id.lon);

        findViewById(R.id.save_button).setOnClickListener(mOnClickListener);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        lat.setText(loadTitlePref(WeatherWidgetConfigureActivity.this, mAppWidgetId, "lat"));
        lon.setText(loadTitlePref(WeatherWidgetConfigureActivity.this, mAppWidgetId, "lon"));
    }
}

