package weather.controller;

import weather.model.WeatherModelApi;

/**
 * Controller handling user interactions and input validation.
 * Connects View actions to Model logic.
 */
public class WeatherController {
    private final WeatherModelApi model;

    public WeatherController(WeatherModelApi model) {
        if (model == null) {
            throw new IllegalArgumentException("Model cannot be null");
        }
        this.model = model;
    }

    /**
     * Handles city search request after validating user input.
     * @param cityName The raw input city name from the GUI text field.
     * @return true if input was valid and sent to model; false if input was empty.
     */
    public boolean handleSearch(String cityName) {
        if (cityName == null || cityName.trim().isEmpty()) {
            return false;
        }
        model.searchCity(cityName.trim());
        return true;
    }

    /**
     * Handles refresh user action to update current weather data.
     */
    public void handleRefresh() {
        model.refreshCurrentCity();
    }
} 