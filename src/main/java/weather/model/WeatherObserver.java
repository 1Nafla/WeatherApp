package weather.model;

/**
 * Observer interface implementing the Observer Pattern.
 * Allows View components to listen for data updates and errors from the Model.
 */
public interface WeatherObserver {
    void onWeatherDataUpdated(Object data);
    void onError(String errorMessage);
} 