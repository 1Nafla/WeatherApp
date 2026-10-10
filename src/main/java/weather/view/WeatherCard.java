package weather.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Displays weather information in a card.
 */
public class WeatherCard extends JPanel {

    private final JLabel cityLabel;
    private final JLabel temperatureLabel;
    private final JLabel conditionLabel;
    private final JLabel humidityLabel;
    private final JLabel windLabel;

    public WeatherCard() {
        setLayout(new BorderLayout(0, 15));
        setBackground(new Color(41, 43, 70));
        setBorder(new EmptyBorder(25, 20, 20, 20));

        // City name
        cityLabel = new JLabel("Search for a city");
        cityLabel.setForeground(new Color(190, 171, 255));
        cityLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        cityLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Temperature
        temperatureLabel = new JLabel("--°C");
        temperatureLabel.setForeground(Color.WHITE);
        temperatureLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Weather condition
        conditionLabel = new JLabel("Weather information will appear here");
        conditionLabel.setForeground(new Color(175, 172, 196));
        conditionLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        conditionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Center section
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        centerPanel.setOpaque(false);
        centerPanel.add(temperatureLabel);
        centerPanel.add(conditionLabel);

        // Humidity information
        JPanel humidityCard = new JPanel(new GridLayout(2, 1));
        humidityCard.setBackground(new Color(48, 49, 73));
        humidityCard.setBorder(new EmptyBorder(12, 15, 12, 15));

        JLabel humidityTitle = new JLabel("Humidity");
        humidityTitle.setForeground(new Color(190, 171, 255));

        humidityLabel = new JLabel("--%");
        humidityLabel.setForeground(Color.WHITE);
        humidityLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        humidityCard.add(humidityTitle);
        humidityCard.add(humidityLabel);

        // Wind speed information
        JPanel windCard = new JPanel(new GridLayout(2, 1));
        windCard.setBackground(new Color(48, 49, 73));
        windCard.setBorder(new EmptyBorder(12, 15, 12, 15));

        JLabel windTitle = new JLabel("Wind Speed");
        windTitle.setForeground(new Color(190, 171, 255));

        windLabel = new JLabel("-- km/h");
        windLabel.setForeground(Color.WHITE);
        windLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        windCard.add(windTitle);
        windCard.add(windLabel);

        // Arrange humidity and wind speed side by side
        JPanel detailsPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        detailsPanel.setOpaque(false);
        detailsPanel.add(humidityCard);
        detailsPanel.add(windCard);

        // Assemble the weather card
        add(cityLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(detailsPanel, BorderLayout.SOUTH);
    }
}
