package SokoGit;

import javax.swing.*;
import java.awt.*;

public class Ground extends Actor{

    private String imagePath;
    private Image image;
    public Ground(int x, int y, String imagePath) {
        super(x, y);
        this.imagePath = imagePath;
        initPlace();
    }
    private void initPlace(){
        ImageIcon icon = new ImageIcon(imagePath);
        image = icon.getImage();
        setImage(image);
    }
}
