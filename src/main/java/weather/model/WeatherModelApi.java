
package weather.model;

/**
 * Defines the operations available to the Controller.
 * This interface reduces coupling between the Controller and Model.
 */
public interface WeatherModelApi {

    /**
     * Searches for weather information by city name.
     */
    void searchCity(String cityName);

    /**
     * Refreshes the currently selected city's weather.
     */
    void refreshCurrentCity();

    /**
     * Registers a View or another observer for updates.
     */
    void addObserver(WeatherObserver observer);

    /**
     * Returns the latest successfully selected weather data.
     */
    WeatherData getLatestData();
}