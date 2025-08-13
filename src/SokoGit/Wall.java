package SokoGit;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Wall extends Actor {

    private Image image;
    private String imagePath;

    public Wall(int x, int y, String imagePath) {
        super(x, y);
        this.imagePath = imagePath;
        initWall();

    }

    private void initWall() {
        ImageIcon icon = new ImageIcon(imagePath);
        image = icon.getImage();
        setImage(image);
    }


}