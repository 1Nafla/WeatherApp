
package weather.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Displays the weather information card.
 */
public class WeatherCard extends JPanel {

    private final JLabel cityLabel;
    private final JLabel temperatureLabel;
    private final JLabel conditionLabel;

    public WeatherCard() {
        setLayout(new BorderLayout(0, 10));
        setBackground(new Color(41, 43, 70));
        setBorder(new EmptyBorder(25, 15, 25, 15));

        cityLabel = new JLabel("Search for a city");
        cityLabel.setForeground(new Color(190, 171, 255));
        cityLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        cityLabel.setHorizontalAlignment(SwingConstants.CENTER);

        temperatureLabel = new JLabel("--°C");
        temperatureLabel.setForeground(Color.WHITE);
        temperatureLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);

        conditionLabel = new JLabel("Weather information will appear here");
        conditionLabel.setForeground(new Color(175, 172, 196));
        conditionLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        conditionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(cityLabel, BorderLayout.NORTH);
        add(temperatureLabel, BorderLayout.CENTER);
        add(conditionLabel, BorderLayout.SOUTH);
    }
}
