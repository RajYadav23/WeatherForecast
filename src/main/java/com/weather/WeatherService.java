package com.weather;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {

    // 🔴 TIP: For now it's OK here, later we will move this to config.properties
    private static final String API_KEY = "f291e2e4de2e1c0845cd5765e2bf7d13";

    /**
     * Fetch current weather for a given city
     */
    public static WeatherData getCurrentWeather(String city) {

        try {
            String urlStr =
                    "https://api.openweathermap.org/data/2.5/weather?q="
                            + city.trim()
                            + "&units=metric"
                            + "&appid=" + API_KEY;

            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            if (con.getResponseCode() != 200) {
                return null;
            }

            JsonObject json = JsonParser.parseReader(
                    new InputStreamReader(con.getInputStream())
            ).getAsJsonObject();

            double temp = json.getAsJsonObject("main").get("temp").getAsDouble();
            int humidity = json.getAsJsonObject("main").get("humidity").getAsInt();
            double wind = json.getAsJsonObject("wind").get("speed").getAsDouble();

            String condition = json.getAsJsonArray("weather")
                    .get(0).getAsJsonObject()
                    .get("description").getAsString();

            String icon = json.getAsJsonArray("weather")
                    .get(0).getAsJsonObject()
                    .get("icon").getAsString();

            String iconUrl =
                    "https://openweathermap.org/img/wn/" + icon + "@2x.png";

            return new WeatherData(
                    temp,
                    humidity,
                    wind,
                    condition,
                    iconUrl
            );

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
