package com.hazem.udacity.sunshine;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mezah on 4/30/2016.
 */
public class FetchWeatherTask extends AsyncTask<String, Void, String> {

    private static final String TAG = FetchWeatherTask.class.getName();

    @Override
    protected String doInBackground(String... params) {
        // Define outside the try and catch to be able to close them in final

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String forecastJsonStr = null;
        String cityId = params[0];
        try {

            String baseUrl = "http://api.openweathermap.org/data/2.5/forecast/city?";//city?id=360630&mode=json&units=metric&cnt=7";


            String apiKey = "&APPID=" + BuildConfig.OPEN_WEATHER_MAP_API_KEY;

            Uri builder = Uri.parse(baseUrl).buildUpon()
                    .appendQueryParameter("id", cityId)
                    .appendQueryParameter("mode","json")
                    .appendQueryParameter("units","metric")
                    .appendQueryParameter("cnt","7")
                    .appendQueryParameter("APPID",BuildConfig.OPEN_WEATHER_MAP_API_KEY).build();

            Log.d(TAG,builder.toString());
            URL url = new URL(builder.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // read the inputStream
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                // do nothing
                return null;

            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line = reader.readLine()) != null) {

                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // stream is empty
                return null;
            }

            forecastJsonStr = buffer.toString();

            Log.d(TAG, forecastJsonStr);


        } catch (IOException e) {

            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            return null;

        } finally {

            if (urlConnection != null) {

                urlConnection.disconnect();

            }
            if (reader != null) {

                try {

                    reader.close();

                } catch (final IOException e) {

                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }

        }

        return forecastJsonStr;
    }
}
