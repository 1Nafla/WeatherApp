
package weather.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the WeatherController.
 * Tests input validation and delegation to the Model.
 */
public class WeatherControllerTest {

    private FakeWeatherModel fakeModel;
    private WeatherController controller;

    @BeforeEach
    void setUp() {
        fakeModel = new FakeWeatherModel();
        controller = new WeatherController(fakeModel);
    }

    @Test
    void testValidCitySearch() {
        boolean result = controller.handleSearch("Riyadh");

        assertTrue(result);
        assertEquals("Riyadh", fakeModel.getLastSearchedCity());
    }

    @Test
    void testCitySearchTrimsWhitespace() {
        boolean result = controller.handleSearch("  Riyadh  ");

        assertTrue(result);
        assertEquals("Riyadh", fakeModel.getLastSearchedCity());
    }

    @Test
    void testEmptyCitySearch() {
        boolean result = controller.handleSearch("   ");

        assertFalse(result);
        assertNull(fakeModel.getLastSearchedCity());
    }

    @Test
    void testNullCitySearch() {
        boolean result = controller.handleSearch(null);

        assertFalse(result);
        assertNull(fakeModel.getLastSearchedCity());
    }

    @Test
    void testHandleRefresh() {
        controller.handleRefresh();

        assertTrue(fakeModel.isRefreshCalled());
    }

    @Test
    void testNullModelIsRejected() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new WeatherController(null)
        );
    }
}