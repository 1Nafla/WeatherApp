package weather.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Locale;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import weather.model.WeatherData;

/** Displays weather values or a clear status message. */
public class WeatherCard extends RoundedPanel {
    private static final Color LAVENDER = new Color(190, 171, 255);
    private static final Color MUTED = new Color(181, 176, 204);

    private final CardLayout states = new CardLayout();
    private final JPanel statePanel = new JPanel(states);
    private final JLabel cityLabel = makeLabel("", 19, LAVENDER);
    private final JLabel temperatureLabel = makeLabel("", 48, Color.WHITE);
    private final JLabel conditionLabel = makeLabel("", 14, MUTED);
    private final JLabel humidityLabel = makeLabel("", 22, Color.WHITE);
    private final JLabel windLabel = makeLabel("", 22, Color.WHITE);
    private final JLabel statusIcon = makeLabel("", 34, LAVENDER);
    private final JLabel statusTitle = makeLabel("", 19, Color.WHITE);
    private final JLabel statusDetail = makeLabel("", 13, MUTED);
    private final Timer loadingTimer;
    private int loadingDots = 0;

    public WeatherCard() {
        super(28, new Color(41, 43, 70));
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(23, 20, 20, 20));
        setPreferredSize(new Dimension(440, 315));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 315));

        statePanel.setOpaque(false);
        statePanel.add(createWeatherContent(), "weather");
        statePanel.add(createStatusContent(), "status");
        add(statePanel, BorderLayout.CENTER);

        loadingTimer = new Timer(280, event -> {
            loadingDots = (loadingDots + 1) % 4;
            statusTitle.setText(new String[]{"Loading", "Loading.", "Loading..", "Loading..."}[loadingDots]);
        });
        showInitial();
    }

    private JPanel createWeatherContent() {
        JPanel content = new JPanel(new BorderLayout(0, 12));
        content.setOpaque(false);

        JPanel heading = new JPanel(new GridLayout(2, 1, 0, 3));
        heading.setOpaque(false);
        heading.add(cityLabel);
        heading.add(conditionLabel);

        JPanel details = new JPanel(new GridLayout(1, 2, 12, 0));
        details.setOpaque(false);
        details.add(createMetric("Humidity", humidityLabel));
        details.add(createMetric("Wind Speed", windLabel));

        content.add(heading, BorderLayout.NORTH);
        content.add(temperatureLabel, BorderLayout.CENTER);
        content.add(details, BorderLayout.SOUTH);
        return content;
    }

    private JPanel createMetric(String title, JLabel valueLabel) {
        RoundedPanel metric = new RoundedPanel(20, new Color(48, 49, 73));
        metric.setLayout(new GridLayout(2, 1, 0, 5));
        metric.setBorder(new EmptyBorder(12, 15, 12, 15));
        JLabel name = makeLabel(title, 13, LAVENDER);
        name.setHorizontalAlignment(SwingConstants.LEFT);
        valueLabel.setHorizontalAlignment(SwingConstants.LEFT);
        metric.add(name);
        metric.add(valueLabel);
        return metric;
    }

    private JPanel createStatusContent() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        statusIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusDetail.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalGlue());
        panel.add(statusIcon);
        panel.add(Box.createVerticalStrut(12));
        panel.add(statusTitle);
        panel.add(Box.createVerticalStrut(8));
        panel.add(statusDetail);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private static JLabel makeLabel(String text, int size, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, size));
        label.setForeground(color);
        return label;
    }

    private void showStatus(String icon, String title, String detail) {
        loadingTimer.stop();
        statusIcon.setText(icon);
        statusTitle.setText(title);
        statusDetail.setText(detail);
        statusDetail.setToolTipText(detail);
        states.show(statePanel, "status");
    }

    public void showInitial() {
        showStatus("☁", "Explore the weather", "Enter a city to get started");
    }

    public void showLoading() {
        showStatus("◌", "Loading", "Getting weather information...");
        loadingDots = 0;
        loadingTimer.start();
    }

    public void showEmptyInput() {
        showStatus("!", "Enter a city name", "The search field cannot be empty");
    }

    public void showPreviewOnly() {
        showStatus("☁", "Preview mode", "Connect the Model to enable search");
    }

    public void showError(String errorMessage) {
        String detail = errorMessage == null || errorMessage.trim().isEmpty()
                ? "Unable to load weather information" : errorMessage.trim();
        String lower = detail.toLowerCase(Locale.ROOT);
        boolean missing = lower.contains("not found") || lower.contains("unknown city")
                || lower.contains("unsupported city");
        String displayDetail = missing ? "Try another city name" : detail;
        if (displayDetail.length() > 45) {
            displayDetail = displayDetail.substring(0, 42) + "...";
        }
        showStatus("!", missing ? "City not found" : "Something went wrong", displayDetail);
        statusDetail.setToolTipText(detail);
    }

    public void showWeather(WeatherData data) {
        loadingTimer.stop();
        cityLabel.setText(data.getCity());
        temperatureLabel.setText(String.format(Locale.US,
                "%.0f°C", data.getTemperature()));
        conditionLabel.setText(data.getCondition());
        humidityLabel.setText(data.getHumidity() + "%");
        windLabel.setText(String.format(Locale.US,
                "%.1f km/h", data.getWindSpeed()));
        states.show(statePanel, "weather");
    }
}
