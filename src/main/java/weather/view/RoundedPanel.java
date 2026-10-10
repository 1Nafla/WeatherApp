package weather.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/** A reusable panel with smooth rounded corners. */
public class RoundedPanel extends JPanel {
    private final int radius;
    private final Color panelColor;

    public RoundedPanel(int radius, Color panelColor) {
        this.radius = radius;
        this.panelColor = panelColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(panelColor);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g.dispose();
    }
}
