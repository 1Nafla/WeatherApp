package weather.view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

        setContentPane(mainPanel);
    }
}
