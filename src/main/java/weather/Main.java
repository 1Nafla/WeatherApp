
package weather;

import javax.swing.SwingUtilities;

import weather.model.WeatherModel;
import weather.view.WeatherView;

/**
 * Application entry point.
 * Creates the Model and launches the MVC application.
 */
public class Main {

    public static void main(String[] args) {

        // Create the Model containing the sample weather data.
        WeatherModel model = new WeatherModel();

        // Create and display the View on the Swing Event Dispatch Thread.
        SwingUtilities.invokeLater(() -> {
            WeatherView view = new WeatherView(model);
            view.setVisible(true);
        });
    }
}