package SokoGit;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Area extends Actor {
    private Image image;
    private String imagePath;
    private String type;

    public Area(int x, int y, String imagePath, String type) {

        super(x, y);
        this.imagePath = imagePath;
        this.type = type;
        initArea();
    }

    public String identifyType() {

        return type;
    }

    private void initArea() {

        ImageIcon icon = new ImageIcon(imagePath);
        image = icon.getImage();
        setImage(image);
    }
}