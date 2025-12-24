package com.weather;

public class WeatherData {

    private double temp;
    private int humidity;
    private double wind;
    private String condition;
    private String iconUrl;

    public WeatherData(double temp, int humidity, double wind,
                       String condition, String iconUrl) {
        this.temp = temp;
        this.humidity = humidity;
        this.wind = wind;
        this.condition = condition;
        this.iconUrl = iconUrl;
    }

    public double getTemp() { return temp; }
    public int getHumidity() { return humidity; }
    public double getWind() { return wind; }
    public String getCondition() { return condition; }
    public String getIconUrl() { return iconUrl; }
}
