package weather.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/** A rounded button with subtle hover and press feedback. */
public class RoundedButton extends JButton {
    private final boolean secondary;

    public RoundedButton(String text) {
        this(text, false);
    }

    public RoundedButton(String text, boolean secondary) {
        super(text);
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
        g.dispose();
    }
}
