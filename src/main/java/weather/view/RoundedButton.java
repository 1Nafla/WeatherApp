
package weather.view;

import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/** A View-only button with rounded corners and keyboard/mouse feedback. */
public class RoundedButton extends JButton {
    private final boolean secondary;

    /** @param text non-null label for the primary button */
    public RoundedButton(String text) {
        this(text, false);
    }

    /**
     * @param text non-null button label
     * @param secondary true to use the secondary visual style
     * @throws NullPointerException if text is null
     */
    public RoundedButton(String text, boolean secondary) {
        super(Objects.requireNonNull(text, "text"));
        this.secondary = secondary;
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setForeground(secondary ? new Color(218, 202, 255)
                : new Color(22, 24, 42));
        setBorder(new EmptyBorder(10, 18, 10, 18));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setRolloverEnabled(true);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Color color;
        if (!isEnabled()) {
            color = new Color(85, 83, 107);
        } else if (getModel().isPressed()) {
            color = secondary ? new Color(74, 68, 103)
                    : new Color(155, 134, 220);
        } else if (getModel().isRollover()) {
            color = secondary ? new Color(64, 61, 90)
                    : new Color(213, 199, 255);
        } else {
            color = secondary ? new Color(48, 49, 73)
                    : new Color(190, 171, 255);
        }

        g.setColor(color);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 22, 22);
        if (getModel().isPressed()) {
            g.translate(0, 1);
        }
        super.paintComponent(g);
        // Visible keyboard focus even though the standard focus border is hidden.
        if (isFocusOwner()) {
            g.setColor(new Color(245, 240, 255));
            g.setStroke(new BasicStroke(1.4f));
            g.drawRoundRect(3, 3, Math.max(0, getWidth() - 7),
                    Math.max(0, getHeight() - 7), 18, 18);
        }
        g.dispose();
    }
}
