
package weatherapp.model;

import weatherapp.observer.WeatherObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Model responsible for storing and searching weather data.
 * Uses the Observer pattern to notify registered observers.
 */
public class WeatherModel {

    private final Map<String, WeatherData> weatherData;
    private final List<WeatherObserver> observers;

    private WeatherData currentWeather;

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

    private void addSampleWeather(WeatherData data) {
        String key = data.getCity().toLowerCase(Locale.ROOT);
        weatherData.put(key, data);
    }

    public void addObserver(WeatherObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    public void searchCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            currentWeather = null;
        } else {
            String key = city.trim().toLowerCase(Locale.ROOT);
            currentWeather = weatherData.get(key);
        }

        notifyObservers();
    }

    public WeatherData getCurrentWeather() {
        return currentWeather;
    }

    public void refresh() {
        notifyObservers();
    }

    public Map<String, WeatherData> getAvailableWeatherData() {
        return Collections.unmodifiableMap(weatherData);
    }

    private void notifyObservers() {
        for (WeatherObserver observer : new ArrayList<>(observers)) {
            observer.update(currentWeather);
        }
    }
}