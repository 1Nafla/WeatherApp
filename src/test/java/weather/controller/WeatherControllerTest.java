package weather.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(result, "Search should succeed for valid city name");
        assertEquals("Riyadh", fakeModel.getLastSearchedCity(), "Model should receive trimmed city name");
    }

    @Test
    void testEmptyCitySearch() {
        boolean result = controller.handleSearch("   ");
        assertFalse(result, "Search should fail for empty city string");
        assertNull(fakeModel.getLastSearchedCity(), "Model should not be called with empty input");
    }

    @Test
    void testHandleRefresh() {
        controller.handleRefresh();
        assertTrue(fakeModel.isRefreshCalled(), "Refresh action should trigger model refresh");
    }
}
