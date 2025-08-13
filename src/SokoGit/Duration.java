package SokoGit;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Duration extends JPanel {
    private int duration = 45;
    private ImageIcon chronometer = new ImageIcon("src/resources/timer2.gif");
    private ImageIcon stepIcon = new ImageIcon("src/resources/Character4.png");

    Sound sound = new Sound();
    private Timer timer;
    private JLabel label = new JLabel("00:" + duration);
    private JLabel moveLabel = new JLabel(" 00");
    private JLabel icon;
    private final Font font = loadFont();
    private boolean levelCompletedInTime = true;

    public Duration() {
        icon = new JLabel(chronometer);
        this.timer = new Timer(1000, e -> {
            duration--;
            updateTime();
        });
        label.setFont(font);
        moveLabel.setFont(font);


        Box verticalBox = Box.createVerticalBox();



        // Zamanlayıcı
        Box horizontalBoxTimer = Box.createHorizontalBox();
        horizontalBoxTimer.add(icon);
        horizontalBoxTimer.add(Box.createRigidArea(new Dimension(10, 0))); // 10 piksel boşluk ekle
        horizontalBoxTimer.add(label);

        // Adım Sayısı
        Box horizontalBoxStep = Box.createHorizontalBox();
        JLabel stepLabel = new JLabel(stepIcon);
        horizontalBoxStep.add(stepLabel);
        horizontalBoxStep.add(Box.createRigidArea(new Dimension(10, 0))); // 10 piksel boşluk ekle
        horizontalBoxStep.add(moveLabel);

        Box horizontalBoxLevel = Box.createHorizontalBox();


        // Dikey Kutu, Seviye Bilgisi, Zamanlayıcı ve Adım Sayısı
        Box verticalBoxMain = Box.createVerticalBox();

        verticalBoxMain.add(horizontalBoxTimer);
        verticalBoxMain.add(Box.createVerticalStrut(5)); // 5 piksel dikey boşluk ekle
        verticalBoxMain.add(horizontalBoxStep);
        verticalBoxMain.add(Box.createVerticalStrut(5)); // 5 piksel dikey boşluk ekle
        verticalBoxMain.add(horizontalBoxLevel);
        verticalBoxMain.add(Box.createVerticalStrut(5)); // 5 piksel dikey boşluk ekle

        verticalBox.add(verticalBoxMain);

        add(verticalBox);

        this.setOpaque(false);
        this.setPreferredSize(new Dimension(180, 120)); // Seviye numarasını içerecek şekilde yüksekliği artırın
        startDuration();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }

    public void stopDuration() {
        timer.stop();
    }

    public void startDuration() {
        timer.start();
    }



    public boolean isTimerUp(){
        if (duration<=0){
            return true;
        }
        return false;
    }
    private void updateTime() {
        label.setText(durationFormat(duration));
        if (duration <= 0) {
            stopDuration();
            playSE(8);
        } else if (duration <= 15) {
            label.setForeground(Color.RED);
            playSE(7);
        }
    }

    private String durationFormat(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    private Font loadFont() {
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/BADABB__.TTF")).deriveFont(Font.PLAIN, 30);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
        return font;
    }

    public void resetDuration() {
        duration = 45;
        label.setForeground(Color.BLACK);
        updateTime();
    }

    public void setMove(int move) {
        String formattedMove = String.format("%02d", move);
        moveLabel.setText(formattedMove);
    }

}