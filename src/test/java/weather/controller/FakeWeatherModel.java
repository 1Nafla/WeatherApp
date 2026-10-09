package weather.controller;

import weather.model.WeatherModelApi;
import weather.model.WeatherObserver;

class FakeWeatherModel implements WeatherModelApi {
    String lastFetchedCity;
    int fetchCalls = 0;
    int refreshCalls = 0;
    String lastError;

    @Override public void addObserver(WeatherObserver o) { }
    @Override public void removeObserver(WeatherObserver o) { }

    @Override public void fetchWeather(String city) {
        lastFetchedCity = city;
        fetchCalls++;
    }

    @Override public void refresh() { refreshCalls++; }

    @Override public void reportError(String message) { lastError = message; }
}
