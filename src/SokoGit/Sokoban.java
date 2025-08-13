package SokoGit;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Sokoban extends JFrame {

    Board kesintisizAyniResimArkaPlanPaneli;
    private static final long serialVersionUID = 1L;
    private final int OFFSET = 30;

    public Sokoban(int i) {

        initUI(i);
    }

    private void initUI(int i) {

setUndecorated(true);
        setTitle("Sokoban");
        kesintisizAyniResimArkaPlanPaneli = new Board(i);
        add(kesintisizAyniResimArkaPlanPaneli);
        getContentPane().add(kesintisizAyniResimArkaPlanPaneli);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kesintisizAyniResimArkaPlanPaneli.kaydir();
            }
        });
        timer.start();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(PlayMenu::new);
    }
}

