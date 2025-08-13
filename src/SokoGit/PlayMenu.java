package SokoGit;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PlayMenu extends JFrame {
    GridBagConstraints gbc = new GridBagConstraints(); //pencere ayarı
    JLabel sokobanLabel = new JLabel("SOKOBAN");
    JButton playButton = new JButton("PLAY");

    PlayMenu() {
        setTitle("SOKOBAN");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        getContentPane().setBackground(new Color(225, 240, 200));
        sokobanLabel.setFont(loadFont());
        sokobanLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 70, 0); // alttan 70 piksellik boşluk
        gbc.anchor = GridBagConstraints.PAGE_START;
        add(sokobanLabel, gbc);

        // "PLAY" düğmesi
        playButton.setFont(loadFont());
        playButton.addActionListener(e -> { // sonraki menüye geçiş sağlıyor
            this.dispose(); //mevcut pencere kapatılır
            new LevelMenu();//açılır
        });

        playButton.setBackground(new Color(68, 230, 10));
        playButton.setForeground(Color.BLACK);
        playButton.setBorder(new RoundBorder(20, 15));
        playButton.setFont(playButton.getFont().deriveFont(Font.PLAIN, 20));
        playButton.setPreferredSize(new Dimension(180, 70));
        gbc.gridy = 1; //satır indeksi
        gbc.insets = new Insets(50, 0, 0, 0); // iç boşlukları, üst kenardn 50 piksel
        gbc.anchor = GridBagConstraints.CENTER; //ortaya yerleştirme
        add(playButton, gbc);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    public Font loadFont() {
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/04B_30__.TTF")).deriveFont(Font.PLAIN, 80);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
        return font;
    }
}

