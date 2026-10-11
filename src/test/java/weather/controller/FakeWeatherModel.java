
package weather.controller;

import weather.model.WeatherData;
import weather.model.WeatherModelApi;
import weather.model.WeatherObserver;

/**
 * A fake Model used to test the Controller independently.
 * It records method calls without requiring the real Model.
 */
public class FakeWeatherModel implements WeatherModelApi {

    private String lastSearchedCity;
    private boolean refreshCalled;
    private WeatherObserver observer;
    private WeatherData latestData;

    @Override
    public void searchCity(String cityName) {
        lastSearchedCity = cityName;
    }

    @Override
    public void refreshCurrentCity() {
        refreshCalled = true;
    }

    @Override
    public void addObserver(WeatherObserver observer) {
        this.observer = observer;
    }

    @Override
    public WeatherData getLatestData() {
        return latestData;
    }

    public String getLastSearchedCity() {
        return lastSearchedCity;
    }

    public boolean isRefreshCalled() {
        return refreshCalled;
    }

    public WeatherObserver getObserver() {
        return observer;
    }

    public void setLatestData(WeatherData latestData) {
        this.latestData = latestData;
    }
}