
package weather.controller;

import weather.model.WeatherModelApi;

/**
 * Handles user actions and validates user input.
 * Connects the View to the Model without containing UI logic.
 */
public class WeatherController {

    private final WeatherModelApi model;

    /**
     * Creates a Controller connected to a Model implementation.
     */
    public WeatherController(WeatherModelApi model) {
        if (model == null) {
            throw new IllegalArgumentException("Model cannot be null");
        }

        this.model = model;
    }

    /**
     * Validates a city name and forwards the search to the Model.
     *
     * @param cityName the city entered by the user
     * @return true if the input is valid; otherwise false
     */
    public boolean handleSearch(String cityName) {
        if (cityName == null || cityName.trim().isEmpty()) {
            return false;
        }

        model.searchCity(cityName.trim());
        return true;
    }

    /**
     * Requests the Model to refresh the current city.
     */
    public void handleRefresh() {
        model.refreshCurrentCity();
    }
}