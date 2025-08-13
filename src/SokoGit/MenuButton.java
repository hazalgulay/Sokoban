package SokoGit;

import javax.swing.*;

public class MenuButton extends JButton {
    private int level;

    public int getLevel() {
        return level;
    } //Düğmeyle ilişkili seviye numarasını almak için kullanılır

    public void setLevel(int level) {
        this.level = level;
    } //Düğmenin seviye numarasını ayarlamak için kullanılır.

}