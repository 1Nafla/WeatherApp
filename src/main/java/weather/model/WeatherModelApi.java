package weather.model;

public interface WeatherModelApi {
    void addObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void fetchWeather(String city);
    void refresh();
    void reportError(String message);
}

