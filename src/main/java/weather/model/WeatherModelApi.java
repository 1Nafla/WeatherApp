package weather.model;

/**
 * Interface defining the API contract for the Weather Model.
 * Provides separation of interface from implementation and enables loose coupling 
 * between the Controller and the Model.
 */
public interface WeatherModelApi {
    void searchCity(String cityName);
    void refreshCurrentCity();
    void addObserver(WeatherObserver observer);
    WeatherData getLatestData();
} 