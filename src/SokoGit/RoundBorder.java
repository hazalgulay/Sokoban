package SokoGit;

import javax.swing.border.Border;
import java.awt.*;

class RoundBorder implements Border {
    private final int radius; // Kenarlığın yuvarlak köşelerinin yarıçapı
    private final int thickness; // Kenarlığın kalınlığın

    public RoundBorder(int radius, int thickness) { //özel değişkenlere atar
        this.radius = radius;
        this.thickness = thickness;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //kenar yumuşatma
        g2d.setStroke(new BasicStroke(thickness));
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    //kenarlık içinde nasıl yerleştirileceğini belirler
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius + thickness, radius + thickness, radius + thickness, radius + thickness);
    }

    //kenarlığın opak olup olmadığını belirtir.
    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}