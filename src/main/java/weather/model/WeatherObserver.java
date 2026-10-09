package weather.model;

public interface WeatherObserver {
    void onWeatherUpdated(WeatherData data);
    void onError(String message);
}
