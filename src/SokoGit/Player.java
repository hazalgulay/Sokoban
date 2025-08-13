package SokoGit;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Player extends Actor {

    private int direction; // 0: facing right, 1: facing left, 2: facing up, 3: facing down

    public Player(int x, int y) {
        super(x, y);

        initPlayer();
    }

    private void initPlayer() {
        ImageIcon icon = new ImageIcon("src/resources/Character4.png"); // Set the initial image
        Image image = icon.getImage();
        setImage(image);

        direction = 0; // Initially facing right
    }

    public void setDirection(int newDirection) {
        if (newDirection >= 0 && newDirection <= 3) {
            direction = newDirection;
            updatePlayerImage();
        }


    }

    private void updatePlayerImage() {
        // Update the player image based on the direction
        String imagePath = "src/resources/Character4.png";
        switch (direction) {
            case 0: // Facing right
                imagePath = "src/resources/Character2.png";
                break;
            case 1: // Facing left
                imagePath = "src/resources/Character1.png";
                break;
            case 2: // Facing up
                imagePath = "src/resources/Character7.png";
                break;
            case 3: // Facing down
                imagePath = "src/resources/Character4.png";
                break;

        }

        ImageIcon icon = new ImageIcon(imagePath);
        Image newImage = icon.getImage();
        setImage(newImage);
    }

    public void move(int x, int y) {
        int dx = x() + x;
        int dy = y() + y;

        setX(dx);
        setY(dy);
    }
}
