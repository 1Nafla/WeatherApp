package weather.controller;

import weather.model.WeatherModelApi;

public class WeatherController {

    private final WeatherModelApi model;

    public WeatherController(WeatherModelApi model) {
        if (model == null) {
            throw new IllegalArgumentException("model must not be null");
        }
        this.model = model;
    }

    public void onSearch(String cityName) {
        if (cityName == null || cityName.isBlank()) {
            model.reportError("Please enter a city name");
            return;
        }
        model.fetchWeather(cityName.trim());
    }

    public void onRefresh() {
        model.refresh();
    }
}