package com.weather;

public class DailyForecast {

    public double tempMin;
    public double tempMax;
    public String condition;
    public String iconUrl;

    // Constructor
    public DailyForecast(double tempMin, double tempMax,
                         String condition, String iconUrl) {
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.condition = condition;
        this.iconUrl = iconUrl;
    }
}
