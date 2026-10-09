package weather.model;

public record WeatherData(String city, double temperature, String condition) {
    public WeatherData {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or blank");
        }
    }
}
