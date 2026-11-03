
package weather.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Objects;
import javax.swing.JPanel;

/** A reusable presentation-only panel with smooth rounded corners. */
public class RoundedPanel extends JPanel {
    private final int radius;
    private final Color panelColor;

    /**
     * @param radius nonnegative corner arc size in pixels
     * @param panelColor non-null panel fill color
     * @throws IllegalArgumentException if radius is negative
     * @throws NullPointerException if panelColor is null
     */
    public RoundedPanel(int radius, Color panelColor) {
        if (radius < 0) {
            throw new IllegalArgumentException("radius cannot be negative");
        }
        this.radius = radius;
        this.panelColor = Objects.requireNonNull(panelColor, "panelColor");
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
