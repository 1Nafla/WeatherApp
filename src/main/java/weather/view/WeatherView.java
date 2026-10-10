package weather.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Displays the weather application interface.
 */
public class WeatherView extends JFrame {

    private final Color backgroundColor = new Color(22, 24, 42);
    private final Color lavenderColor = new Color(190, 171, 255);

    public WeatherView() {
        setTitle("Weatherly");
        setSize(520, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(backgroundColor);
        mainPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("WEATHERLY");
        titleLabel.setForeground(lavenderColor);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(30, 10, 20, 10));

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Container for the search bar and weather details
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(backgroundColor);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 30, 20, 30));

        // City search field
        JTextField cityField = new JTextField();
        cityField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        cityField.setBackground(new Color(48, 49, 73));
        cityField.setForeground(Color.WHITE);
        cityField.setCaretColor(Color.WHITE);
        cityField.setBorder(new EmptyBorder(10, 12, 10, 12));
        cityField.setToolTipText("Enter a city name");

        // Search button
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        searchButton.setBackground(lavenderColor);
        searchButton.setForeground(backgroundColor);
        searchButton.setFocusPainted(false);
        searchButton.setBorder(new EmptyBorder(10, 18, 10, 18));

        // Place the field and button next to each other
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setBackground(backgroundColor);
        searchPanel.add(cityField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(searchPanel);

        // Display the weather card
        WeatherCard weatherCard = new WeatherCard();
        weatherCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 190));

        contentPanel.add(Box.createVerticalStrut(25));
        contentPanel.add(weatherCard);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }
}
