package SokoGit;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class LevelMenu extends JFrame {
    public LevelMenu() {
        initUI();
    }

    private void initUI() {

        add(new LevelPanel(this));
        setTitle("Sokoban");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

