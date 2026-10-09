
package weatherapp.observer;

import weatherapp.model.WeatherData;

/**
 * Observer interface for receiving weather updates.
 */
public interface WeatherObserver {

    /**
     * Receives updated weather information.
     * A null value indicates that the city was not found.
     */
    void update(WeatherData weather);
}