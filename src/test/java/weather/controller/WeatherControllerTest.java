package weather.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeatherControllerTest {

    private FakeWeatherModel model;
    private WeatherController controller;

    @BeforeEach
    void setUp() {
        model = new FakeWeatherModel();
        controller = new WeatherController(model);
    }

    @Test
    void searchWithValidCityFetchesTrimmedName() {
        controller.onSearch("  Riyadh ");
        assertEquals("Riyadh", model.lastFetchedCity);
        assertEquals(1, model.fetchCalls);
        assertNull(model.lastError);
    }

    @Test
    void searchWithBlankReportsErrorAndDoesNotFetch() {
        controller.onSearch("   ");
        assertEquals(0, model.fetchCalls);
        assertEquals("Please enter a city name", model.lastError);
    }

    @Test
    void searchWithNullReportsErrorAndDoesNotFetch() {
        controller.onSearch(null);
        assertEquals(0, model.fetchCalls);
        assertNotNull(model.lastError);
    }

    @Test
    void refreshDelegatesToModel() {
        controller.onRefresh();
        assertEquals(1, model.refreshCalls);
    }

    @Test
    void constructorRejectsNullModel() {
        assertThrows(IllegalArgumentException.class, () -> new WeatherController(null));
    }
}
