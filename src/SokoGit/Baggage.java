package SokoGit;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Baggage extends Actor {

    private Image image;
    private String imagePath;
    public Baggage(int x, int y,String imagePath) {
        super(x, y);
        this.imagePath = imagePath;
        initBaggage();
    }

    public String identifyType() {
        // Example logic to identify the type based on imagePath
        if (imagePath.contains("Crate_Gray")) {
            return "$";
        } else if (imagePath.contains("Crate_Yellow")) {
            return "K";
        } else if (imagePath.contains("Crate_Purple")) {
            return "P";
        } else if (imagePath.contains("Crate_Red")) {
            return "J";
        } else {
            return "";  // Default or unknown type
        }
    }

    public void setNewImage(String newImagePath) {
        ImageIcon icon = new ImageIcon(newImagePath);
        image = icon.getImage();
        setImage(image);
    }
    private void initBaggage() {

        ImageIcon icon = new ImageIcon(imagePath);
        image = icon.getImage();
        setImage(image);
    }

    public void move(int x, int y) {

        int dx = x() + x;
        int dy = y() + y;

        setX(dx);
        setY(dy);
    }
}