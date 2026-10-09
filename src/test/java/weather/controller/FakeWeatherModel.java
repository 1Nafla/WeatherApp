package weather.controller;

import weather.model.WeatherData;
import weather.model.WeatherModelApi;
import weather.model.WeatherObserver;

public class FakeWeatherModel implements WeatherModelApi {
    private String lastSearchedCity = null;
    private boolean refreshCalled = false;

    @Override
    public void searchCity(String cityName) {
        this.lastSearchedCity = cityName;
    }

    @Override
    public void refreshCurrentCity() {
        this.refreshCalled = true;
    }

    @Override
    public void addObserver(WeatherObserver observer) {
    // Intentional empty implementation for testing purposes
    }

    @Override
    public WeatherData getLatestData() {
    return null;
    }

    public String getLastSearchedCity() {
        return lastSearchedCity;
    }

    public boolean isRefreshCalled() {
        return refreshCalled;
    }
} 