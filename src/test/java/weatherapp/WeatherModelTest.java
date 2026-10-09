
package weatherapp;

import org.junit.Test;
import weatherapp.model.WeatherData;
import weatherapp.model.WeatherModel;

import static org.junit.Assert.*;

public class WeatherModelTest {

    @Test
    public void testSearchExistingCity() {
        WeatherModel model = new WeatherModel();

        model.searchCity("Riyadh");

        WeatherData weather = model.getCurrentWeather();

        assertNotNull(weather);
        assertEquals("Riyadh", weather.getCity());
        assertEquals(30.0, weather.getTemperature(), 0.001);
    }

    @Test
    public void testSearchCityIgnoringCase() {
        WeatherModel model = new WeatherModel();

        model.searchCity("riyadh");

        assertNotNull(model.getCurrentWeather());
    }

    @Test
    public void testSearchUnknownCity() {
        WeatherModel model = new WeatherModel();

        model.searchCity("London");

        assertNull(model.getCurrentWeather());
    }

    @Test
    public void testInvalidHumidity() {
        assertThrows(IllegalArgumentException.class, () ->
                new WeatherData("Riyadh", 30.0,
                        "Sunny", 120, 15.0));
    }

    @Test
    public void testEqualWeatherData() {
        WeatherData first = new WeatherData(
                "Riyadh", 30.0, "Sunny", 40, 15.0);

        WeatherData second = new WeatherData(
                "Riyadh", 30.0, "Sunny", 40, 15.0);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }
}