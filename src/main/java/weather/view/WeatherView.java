package weather.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import weather.controller.WeatherController;
import weather.model.WeatherData;
import weather.model.WeatherModelApi;
import weather.model.WeatherObserver;

/** Weatherly's main window: handles user actions and displays Model updates. */
public class WeatherView extends JFrame implements WeatherObserver {
    private static final Color BACKGROUND = new Color(22, 24, 42);
    private static final Color LAVENDER = new Color(190, 171, 255);

    private final WeatherModelApi model;
    private final WeatherController controller;
    private final WeatherCard weatherCard = new WeatherCard();
    private final JTextField cityField = new HintTextField();
    private final RoundedButton searchButton = new RoundedButton("Search");
    private final RoundedButton refreshButton = new RoundedButton("↻  Refresh", true);

    /** Preview-only constructor, without a connected Model. */
    public WeatherView() {
        this(null);
    }

    /** Constructor for the final MVC application. */
    public WeatherView(WeatherModelApi model) {
        this.model = model;
        this.controller = model == null ? null : new WeatherController(model);

        setTitle("Weatherly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 630);
        setMinimumSize(new Dimension(500, 600));
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);
        mainPanel.add(createHeader(), BorderLayout.NORTH);
        mainPanel.add(createContent(), BorderLayout.CENTER);
        setContentPane(mainPanel);

        searchButton.addActionListener(event -> performSearch());
        cityField.addActionListener(event -> performSearch());
        refreshButton.addActionListener(event -> performRefresh());

        if (model != null) {
            model.addObserver(this);
            WeatherData latest = model.getLatestData();
            if (latest != null) {
                weatherCard.showWeather(latest);
            }
        }
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(new EmptyBorder(24, 25, 18, 25));

        GlowTitle title = new GlowTitle("WEATHERLY");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setPreferredSize(new Dimension(440, 56));
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));

        JLabel subtitle = new JLabel("A little forecast, a little calm.");
        subtitle.setForeground(new Color(170, 165, 194));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(title);
        header.add(subtitle);
        return header;
    }

    private JPanel createContent() {
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(8, 30, 20, 30));

        cityField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        cityField.setForeground(Color.WHITE);
        cityField.setCaretColor(Color.WHITE);
        cityField.setBorder(new EmptyBorder(10, 5, 10, 5));
        cityField.setToolTipText("Enter a city name");

        RoundedPanel inputPanel = new RoundedPanel(22, new Color(48, 49, 73));
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(0, 12, 0, 12));
        inputPanel.add(cityField, BorderLayout.CENTER);

        JPanel searchRow = new JPanel(new BorderLayout(10, 0));
        searchRow.setOpaque(false);
        searchRow.add(inputPanel, BorderLayout.CENTER);
        searchRow.add(searchButton, BorderLayout.EAST);
        searchRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        searchRow.setAlignmentX(Component.CENTER_ALIGNMENT);

        weatherCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        footer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel sampleLabel = new JLabel("Sample weather data");
        sampleLabel.setForeground(new Color(155, 151, 178));
        sampleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footer.add(sampleLabel, BorderLayout.WEST);
        footer.add(refreshButton, BorderLayout.EAST);

        content.add(searchRow);
        content.add(Box.createVerticalStrut(24));
        content.add(weatherCard);
        content.add(Box.createVerticalStrut(16));
        content.add(footer);
        content.add(Box.createVerticalGlue());
        return content;
    }

    private void performSearch() {
        String city = cityField.getText().trim();
        if (city.isEmpty()) {
            weatherCard.showEmptyInput();
            cityField.requestFocusInWindow();
            return;
        }
        if (controller == null) {
            weatherCard.showPreviewOnly();
            return;
        }

        weatherCard.showLoading();
        try {
            if (!controller.handleSearch(city)) {
                weatherCard.showEmptyInput();
            }
        } catch (RuntimeException exception) {
            weatherCard.showError("Unable to search for this city");
        }
    }

    private void performRefresh() {
        if (controller == null) {
            weatherCard.showPreviewOnly();
            return;
        }
        if (model.getLatestData() == null) {
            weatherCard.showError("Search for a city first");
            return;
        }
        weatherCard.showLoading();
        try {
            controller.handleRefresh();
        } catch (RuntimeException exception) {
            weatherCard.showError("Unable to refresh weather information");
        }
    }

    @Override
    public void onWeatherDataUpdated(Object data) {
        Runnable update = () -> {
            if (data instanceof WeatherData) {
                weatherCard.showWeather((WeatherData) data);
            } else {
                weatherCard.showError("Unexpected weather data received");
            }
        };
        if (SwingUtilities.isEventDispatchThread()) {
            update.run();
        } else {
            SwingUtilities.invokeLater(update);
        }
    }

    @Override
    public void onError(String errorMessage) {
        Runnable update = () -> weatherCard.showError(errorMessage);
        if (SwingUtilities.isEventDispatchThread()) {
            update.run();
        } else {
            SwingUtilities.invokeLater(update);
        }
    }

    /** Displays example data when previewing the UI without a Model. */
    public void showPreviewData(WeatherData data) {
        weatherCard.showWeather(data);
    }

    private static class HintTextField extends JTextField {
        HintTextField() {
            setOpaque(false);
            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent event) { repaint(); }
                @Override
                public void focusLost(FocusEvent event) { repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            if (getText().isEmpty() && !isFocusOwner()) {
                Graphics2D g = (Graphics2D) graphics.create();
                g.setFont(getFont());
                g.setColor(new Color(176, 170, 198));
                FontMetrics metrics = g.getFontMetrics();
                int y = (getHeight() + metrics.getAscent() - metrics.getDescent()) / 2;
                g.drawString("Enter a city...", getInsets().left + 2, y);
                g.dispose();
            }
        }
    }

    private static class GlowTitle extends JLabel {
        GlowTitle(String text) {
            super(text, SwingConstants.CENTER);
            setFont(new Font("Segoe UI Semibold", Font.BOLD, 29));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setFont(getFont());
            FontMetrics metrics = g.getFontMetrics();
            int x = (getWidth() - metrics.stringWidth(getText())) / 2;
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g.setColor(new Color(190, 171, 255, 38));
            for (int dx = -2; dx <= 2; dx += 2) {
                for (int dy = -2; dy <= 2; dy += 2) {
                    g.drawString(getText(), x + dx, y + dy);
                }
            }
            g.setColor(new Color(217, 204, 255));
            g.drawString(getText(), x, y);
            g.dispose();
        }
    }
}
