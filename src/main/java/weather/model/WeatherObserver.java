
package weather.model;

/**
 * Observer interface for receiving weather updates and errors.
 * The View implements this interface to react to Model changes.
 */
public interface WeatherObserver {

    /**
     * Called when new weather data is available.
     *
     * @param data the updated weather information
     */
    void onWeatherDataUpdated(Object data);

    /**
     * Called when a search or refresh operation fails.
     *
     * @param errorMessage a user-friendly error description
     */
    void onError(String errorMessage);
}