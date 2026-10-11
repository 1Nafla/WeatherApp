
package weather.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Stores sample weather data and notifies observers about search results.
 * Implements the Model component and the Observer design pattern.
 */
public class WeatherModel implements WeatherModelApi {

    private final Map<String, WeatherData> weatherData;
    private final List<WeatherObserver> observers;

    private WeatherData currentWeather;

    /**
     * Initializes the available sample weather records.
     */
    public WeatherModel() {
        weatherData = new HashMap<>();
        observers = new ArrayList<>();

        addSampleWeather(
                new WeatherData("Riyadh", 30.0, "Sunny", 40, 15.0));

        addSampleWeather(
                new WeatherData("Jeddah", 32.0, "Cloudy", 65, 20.0));

        addSampleWeather(
                new WeatherData("Al Kharj", 29.0, "Clear", 35, 12.0));
    }

    /**
     * Adds a weather record using a normalized city name as its key.
     */
    private void addSampleWeather(WeatherData data) {
        weatherData.put(normalizeCity(data.getCity()), data);
    }

    /**
     * Registers an observer only if it is not already registered.
     */
    @Override
    public void addObserver(WeatherObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Removes a previously registered observer.
     */
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    /**
     * Searches for a city in the available sample data.
     */
    @Override
    public void searchCity(String cityName) {
        if (cityName == null || cityName.trim().isEmpty()) {
            notifyError("Please enter a city name.");
            return;
        }

        WeatherData result = weatherData.get(normalizeCity(cityName));

        if (result == null) {
            currentWeather = null;
            notifyError("City not found. Try Riyadh, Jeddah, or Al Kharj.");
            return;
        }

        currentWeather = result;
        notifyWeatherUpdated(currentWeather);
    }

    /**
     * Re-sends the currently selected sample weather data.
     */
    @Override
    public void refreshCurrentCity() {
        if (currentWeather == null) {
            notifyError("Search for a city first.");
            return;
        }

        notifyWeatherUpdated(currentWeather);
    }

    /**
     * Returns the latest successfully selected weather record.
     */
    @Override
    public WeatherData getLatestData() {
        return currentWeather;
    }

    /**
     * Returns a read-only view of the available sample records.
     */
    public Map<String, WeatherData> getAvailableWeatherData() {
        return Collections.unmodifiableMap(weatherData);
    }

    /**
     * Normalizes city names to support case-insensitive searching.
     */
    private String normalizeCity(String cityName) {
        return cityName.trim().toLowerCase(Locale.ROOT);
    }

    /**
     * Notifies a snapshot of registered observers about new data.
     */
    private void notifyWeatherUpdated(WeatherData data) {
        List<WeatherObserver> snapshot = new ArrayList<>(observers);

        for (WeatherObserver observer : snapshot) {
            observer.onWeatherDataUpdated(data);
        }
    }

    /**
     * Notifies registered observers when an operation fails.
     */
    private void notifyError(String message) {
        List<WeatherObserver> snapshot = new ArrayList<>(observers);

        for (WeatherObserver observer : snapshot) {
            observer.onError(message);
        }
    }
}