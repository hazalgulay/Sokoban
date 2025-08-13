package SokoGit;
import javax.swing.*;
import java.awt.*;

public class LevelPanel extends JPanel {
    private JPanel level_panel = new JPanel(); //Seviye seçim butonlarını içeren panel
    private LogoPanel logo_panel; //Seviye seçim ekranının üst kısmında yer alan logo paneli
    private SokoGit.LogoPanel box = new SokoGit.LogoPanel("src/resources/levelmenu_boxes.jpg"); // Oyun kutularını gösteren panel.
    private JPanel boxWrapperPanel = new JPanel(); //box panelini saran bir başka panel
    private  Image image;

    LevelPanel(JFrame frame) { // oyun seviyelerini seçmek

        this.setBackground(Color.BLACK);
        level_panel.setLayout(new GridLayout(3, 3, 10, 30)); // seviye butonlarını ızgaraya yerleştirdi
        level_panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 50, 110));
        logo_panel = new LogoPanel("src/resources/selectlevels.png");
        logo_panel.setBorder(BorderFactory.createEmptyBorder(1270, 0, 0, 0));
        this.add(logo_panel);

        logo_panel.setBackground(Color.BLACK);
        image = new ImageIcon("src/resources/2.jpeg").getImage();

        for (int i = 1; i <= 9; i++) {
            MenuButton button = new MenuButton();
            styleButton(button, i);
            ImageIcon icon = new ImageIcon("src/resources/levelbutton" + i + ".jpg");
            button.setBorder(null); //kenar kalınlığı kaldırıldı
            button.setIcon(icon);

            button.addActionListener(e -> { // eylem dinleyicisi eklenir butona tıklandığında gerçekleşen işlemleri tanımlar
                MenuButton tiklananButon = (MenuButton) e.getSource();
                int butonMetni = tiklananButon.getLevel();
                System.out.println("Butona tıklandım: " + butonMetni);
                startGame(frame, butonMetni);
            });
            level_panel.setBackground(Color.BLACK);
            level_panel.add(button);
        }

        boxWrapperPanel.setBackground(Color.BLACK);
        boxWrapperPanel.setLayout(new BorderLayout());
        boxWrapperPanel.setBorder(BorderFactory.createEmptyBorder(200, 50, 10, 10));
        boxWrapperPanel.add(box);
        box.setPreferredSize(new Dimension(100, 150));
        level_panel.setBackground(Color.BLACK);
        this.add(boxWrapperPanel);
        this.add(level_panel);
    }

    private void styleButton(MenuButton button, int index) { //buton düzeni ve boyut ayarı
        button.setLevel(index); //buton numarasını ayarlar
        int boyut = 60;
        button.setPreferredSize(new Dimension(boyut, boyut));
    }

    private void startGame(JFrame frame, int i) { //seviye butonlarına tıklandığında çağrılan metottur
        frame.dispose();
        new Sokoban(i - 1); //kurucu metodu çağırır
    }

    class LogoPanel extends JPanel {
        private ImageIcon logoIcon; //resim görüntülenir

        public LogoPanel(String imagePath) { //Kurucu metot bir logo panelini oluşturur

            logoIcon = new ImageIcon(imagePath);

            setLayout(null); // layout yönetimi devre dışı bırakılır

            setPreferredSize(new Dimension(600, 120));

            JLabel logoLabel = new JLabel(logoIcon);

            int logoX = 70;
            int logoY = 30;
            logoLabel.setBounds(logoX, logoY, logoIcon.getIconWidth(), logoIcon.getIconHeight()); //konum ve boyut belirlenir
            add(logoLabel);
        }

        @Override //panelin içeriğini çizer
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image,0,0,getWidth(),getHeight(),null);
        }
    }
}