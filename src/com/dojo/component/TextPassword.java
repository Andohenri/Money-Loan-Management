package com.dojo.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Ando Henri
 */
public class TextPassword extends JPasswordField {
    private Color backgroundColor = new Color(255,255,255);
    private String hints;
    private Color bordColor;

    public Color getBordColor() {
        return bordColor;
    }

    public void setBordColor(Color bordColor) {
        this.bordColor = bordColor;
    }

    public String getHints() {
        return hints;
    }

    public void setHints(String hints) {
        this.hints = hints;
    }
    public TextPassword() {
        hints = "Search...";
        bordColor = new Color(67,206,162);
        super.setBackground(new Color(255,255,255));
        setOpaque(false);
        setBorder(new EmptyBorder(10,10,10,10));
        setFont(new Font("Segoe UI Semibold", 0, 14));
        setSelectionColor(new Color(67,206,162));
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        g2.setColor(bordColor);
        g2.fillRect(0, getHeight() - 4, getWidth(), 4);
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (String.valueOf(getPassword()).isEmpty()) {
            int h = getHeight();
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g.setColor(new Color(c2, true));
            g.drawString(hints, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }

    @Override
    public void setBackground(Color bg) {
        this.backgroundColor = bg;
    }
}
