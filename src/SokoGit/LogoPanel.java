package SokoGit;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LogoPanel extends JPanel {

    private BufferedImage image; //resmi tutmak

    public LogoPanel(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath)); //resim yolu geçerli değilse
        } catch (IOException e) {
            e.printStackTrace(); //durumu yakalanır ve hatanın detayları ekrana yazdırılır.
        }
        this.setSize(new Dimension(499,75));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
