package weather.model;

import java.util.Objects;

/**
 * Immutable ADT representing weather information.
 *
 * RI (Representation Invariant):
 * - city and condition are not null or blank.
 * - humidity is between 0 and 100.
 * - temperature and windSpeed are finite.
 * - windSpeed is non-negative.
 *
 * AF (Abstraction Function):
 * Represents weather information for one city,
 * including temperature, condition, humidity,
 * and wind speed.
 */
public final class WeatherData {

    private final String city;
    private final double temperature;
    private final String condition;
    private final int humidity;
    private final double windSpeed;

    public WeatherData(String city, double temperature,
                       String condition, int humidity,
                       double windSpeed) {

        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "City cannot be empty");
        }

        if (condition == null || condition.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Condition cannot be empty");
        }

        if (!Double.isFinite(temperature)) {
            throw new IllegalArgumentException(
                    "Temperature must be finite");
        }

        if (humidity < 0 || humidity > 100) {
            throw new IllegalArgumentException(
                    "Humidity must be between 0 and 100");
        }

        if (!Double.isFinite(windSpeed) || windSpeed < 0) {
            throw new IllegalArgumentException(
                    "Wind speed must be finite and non-negative");
        }

        this.city = city.trim();
        this.temperature = temperature;
        this.condition = condition.trim();
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getCondition() {
        return condition;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof WeatherData)) {
            return false;
        }

        WeatherData other = (WeatherData) obj;

        return Double.compare(temperature, other.temperature) == 0
                && humidity == other.humidity
                && Double.compare(windSpeed, other.windSpeed) == 0
                && city.equals(other.city)
                && condition.equals(other.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                city, temperature, condition, humidity, windSpeed);
    }
}
