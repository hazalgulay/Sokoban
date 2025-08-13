package SokoGit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel {

    private int score = 0;

    private int kaymaMiktari = 2;
    private Image arkaPlanResmi;
    private int arkaPlanX;
    Duration duration = new Duration();
    private final int OFFSET = 30;
    private final int SPACE = 64;
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;

    Sound sound;
    private ArrayList<Wall> walls;


    private ArrayList<Baggage> baggs;
    private ArrayList<Area> areas;
    private ArrayList<Ground> grounds;

    private Player soko;
    private int w = 0;
    private int h = 0;
    private int currentLevel;
    private int numMoves = 0;
    private int numLevels = 9;
    private boolean isCompleted = false;

    private Timer completionTimer;
    private Timer gameOverTimer;
    private Image completionImage;  // Tamamlama resmi
    private Image gameOverImage;
    private int completionImageSize = 100;  // Başlangıç boyutu
    private int completionImageMaxSize = 600;  // Maksimum boyutprivate
    int gameOverImageSize = 100;  // Başlangıç boyutu
    private int gameOverImageMaxSize = 300;  // Maksimum boyut
    private String[] level
            = {
            "\n"
                    + "        ###\n"
                    + "       #sss###\n"
                    + "       #@s$sss#\n"
                    + "        #ss--s#\n"
                    + "       #ssss-s#\n"
                    + "       #sss$-s#\n"          //Level 1
                    + "       #.--sss#\n"
                    + "       #s--ss#\n"
                    + "       #sss.s#\n"
                    + "        #####",

            "\n"
                    + "           ####\n"
                    + "         ###ss#\n"
                    + "        ##sss.#\n"
                    + "        #sss-s#\n"
                    + "        #.$s-s#\n"
                    + "        #$ssss#\n"         //Level 2
                    + "        #s--s@#\n"
                    + "       #ss--s#\n"
                    + "       #ss--s#\n"
                    + "        #ssss#\n"
                    + "        ######",

            "\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "       ########\n"
                    + "       #sssssk#\n"
                    + "       #sssK$@#\n"        //Level 3
                    + "       #ss#ss.#\n"
                    + "       ########",

            "\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "        ######\n"
                    + "        #sssp#\n"
                    + "        #s$P.#\n"          //Level 4
                    + "        #sK@k#\n"
                    + "        ######",

            "\n"
                    + "        ######\n"
                    + "       ##ssss#\n"
                    + "       #ssssj###\n"
                    + "       #j-sssss#\n"
                    + "       #s-s-s-s#\n"
                    + "       #s-s-sJs#\n"
                    + "       #sJJ-j###\n"       //Level 5
                    + "       #sss-j#\n"
                    + "       #s###J#\n"
                    + "       #s# #@#\n"
                    + "       ### ###",

            "\n"
                    + "         ##### \n"
                    + "        #sssss#\n"
                    + "        #s---s#\n"
                    + "       #sss-ss#\n"
                    + "       #sK.s$s#\n"
                    + "       #sss--s#\n"         //Level 6
                    + "       #s-.s$s#\n"
                    + "       #ks--s##\n"
                    + "       #sssss#\n"
                    + "       #s###@#\n"
                    + "        #   # \n",

            "\n"
                    + "\n"
                    + "\n"
                    + "        ##### \n"
                    + "        #pss##\n"
                    + "        #ss$s#\n"
                    + "        ##Pss#\n"         //Level 7
                    + "         #@s.#\n"
                    + "         #####",

            "\n"
                    + "\n"
                    + "          ####\n"
                    + "         #sssk#\n"
                    + "        #ssK-s#\n"
                    + "       #ssss-k#\n"
                    + "       #K-sJ-s#\n"        //Level 8
                    + "       #@-ssss#\n"
                    + "       #k-sKjs#\n"
                    + "       #ssss##\n"
                    + "        ####   ",

            "\n"
                    + "            ####\n"
                    + "           #ssss#\n"
                    + "           #k--s#\n"
                    + "      ######s--s#\n"
                    + "     #sKsssss--s#\n"
                    + "     #ssss-sKkks#\n"        //Level 9
                    + "     #s--ssKK###\n"
                    + "     #ssss--@#\n"
                    + "      ##ss--k#\n"
                    + "       #sssss#\n"
                    + "        #####   "
    };

    public Board(int i) {

        currentLevel = i;
        initBoard(i);
        initCompletionTimer();
        initGameOverTimer();
    }

    private void initCompletionTimer() {
        completionTimer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNextLevel();
                completionTimer.stop();
            }
        });
    }

    private void initGameOverTimer() {
        gameOverTimer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartLevel(currentLevel);
                gameOverTimer.stop();
            }
        });
    }

    public void playMusic(int i) {

        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {

        sound.stop();
    }

    public void playSE(int i) {

        sound.setFile(i);
        sound.play();
    }

    private void initBoard(int k) {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld(k);
        this.setLayout(new BorderLayout());
        add(duration, BorderLayout.WEST);
        playMusic(0);
    }


    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    private String getWallImageForLevel(String wall) {

        switch (wall) {
            case "#":
                return "src/resources/Wall_Gray.png";
            case "-":
                return "src/resources/Wall_Beige.png";

            default:
                return "src/resources/default_wall.png";
        }
    }

    private String getGroundImageForLevel(String ground) {
        switch (ground) {
            case "s":
                return "src/resources/Ground_Sand.png";

            default:
                return "src/resources/default_wall.png";
        }
    }

    private String getBaggageImageForLevel(String baggage) {
        switch (baggage) {
            case "$":
                return "src/resources/Crate_Gray.png";
            case "K":
                return "src/resources/Crate_Yellow.png";
            case "P":
                return "src/resources/Crate_Purple.png";
            case "J":
                return "src/resources/Crate_Red.png";

            default:
                return "src/resources/default_wall.png";
        }
    }

    private String getBaggageImageForLevelComplete(String baggage) {
        switch (baggage) {
            case "$":
                return "src/resources/CrateDark_Gray.png";
            case "K":
                return "src/resources/CrateDark_Yellow.png";
            case "P":
                return "src/resources/CrateDark_Purple.png";
            case "J":
                return "src/resources/CrateDark_Red.png";

            default:
                return "src/resources/default_wall.png";
        }
    }


    private String getAreaImageForLevel(String area) {
        switch (area) {
            case ".":
                return "src/resources/AreaSandWhite.png";
            case "k":
                return "src/resources/AreaSandYellow.png";
            case "p":
                return "src/resources/AreaSandPurple.png";
            case "j":
                return "src/resources/AreaSandRed.png";

            default:
                return "src/resources/default_wall.png";
        }
    }

    private String[] backgroundImages = {
            "src/resources/kolay.jpg",
            "src/resources/orta.jpg",
            "src/resources/3.jpeg"
    };

    private void initWorld(int k) {
        sound = new Sound();
        int backgroundIndex = k / 3;
        arkaPlanResmi = new ImageIcon(backgroundImages[backgroundIndex]).getImage();
        walls = new ArrayList<>();
        baggs = new ArrayList<>();
        areas = new ArrayList<>();
        grounds = new ArrayList<>();

        int x = OFFSET;
        int y = OFFSET;
        Wall wall;
        Baggage b;
        Area a;
        Ground g;

        for (int i = 0; i < level[k].length(); i++) {
            char item = level[k].charAt(i);
            String imagePath;
            switch (item) {

                case '\n':
                    y += SPACE;

                    if (this.w < x) {
                        this.w = x;
                    }

                    x = OFFSET;
                    break;

                case '#':
                    imagePath = getWallImageForLevel("#");
                    wall = new Wall(x, y, imagePath);
                    walls.add(wall);
                    x += SPACE;
                    break;

                case '$':
                    imagePath = getGroundImageForLevel("s");
                    g = new Ground(x, y, imagePath);
                    grounds.add(g);
                    imagePath = getBaggageImageForLevel("$");
                    b = new Baggage(x, y, imagePath);
                    baggs.add(b);
                    x += SPACE;
                    break;

                case '.':
                    imagePath = getAreaImageForLevel(".");
                    a = new Area(x, y, imagePath, "$");
                    areas.add(a);
                    x += SPACE;
                    break;

                case 'k':
                    imagePath = getAreaImageForLevel("k");
                    a = new Area(x, y, imagePath, "K");
                    areas.add(a);
                    x += SPACE;
                    break;


                case 'p':
                    imagePath = getAreaImageForLevel("p");
                    a = new Area(x, y, imagePath, "P");
                    areas.add(a);
                    x += SPACE;
                    break;

                case 'j':
                    imagePath = getAreaImageForLevel("j");
                    a = new Area(x, y, imagePath, "J");
                    areas.add(a);
                    x += SPACE;
                    break;

                case '@':
                    imagePath = getGroundImageForLevel("s");
                    g = new Ground(x, y, imagePath);
                    grounds.add(g);
                    soko = new Player(x, y);

                    x += SPACE;
                    break;

                case '-':
                    imagePath = getWallImageForLevel("-");
                    wall = new Wall(x, y, imagePath);
                    walls.add(wall);
                    x += SPACE;
                    break;

                case 's':
                    imagePath = getGroundImageForLevel("s");
                    g = new Ground(x, y, imagePath);
                    grounds.add(g);
                    x += SPACE;
                    break;

                case ' ':
                    x += SPACE;
                    break;

                case 'K':
                    imagePath = getGroundImageForLevel("s");
                    g = new Ground(x, y, imagePath);
                    grounds.add(g);
                    imagePath = getBaggageImageForLevel("K");
                    b = new Baggage(x, y, imagePath);
                    baggs.add(b);
                    x += SPACE;
                    break;

                case 'J':
                    imagePath = getGroundImageForLevel("s");
                    g = new Ground(x, y, imagePath);
                    grounds.add(g);
                    imagePath = getBaggageImageForLevel("J");
                    b = new Baggage(x, y, imagePath);
                    baggs.add(b);
                    x += SPACE;
                    break;

                case 'P':
                    imagePath = getGroundImageForLevel("s");
                    g = new Ground(x, y, imagePath);
                    grounds.add(g);
                    imagePath = getBaggageImageForLevel("P");
                    b = new Baggage(x, y, imagePath);
                    baggs.add(b);
                    x += SPACE;
                    break;


                default:
                    break;
            }

            h = y;
        }


    }

    public void kaydir() {
        arkaPlanX -= kaymaMiktari;
        if (arkaPlanX <= -getWidth()) {
            arkaPlanX = 0;
        }
        repaint();
    }


    private void buildWorld(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        ArrayList<Actor> world = new ArrayList<>();
        world.addAll(grounds);
        world.addAll(walls);
        world.addAll(areas);
        world.addAll(baggs);

        world.add(soko);

        for (int i = 0; i < world.size(); i++) {
            Actor item = world.get(i);

            if (item instanceof Player) {

                g.drawImage(item.getImage(), item.x() + 14, item.y() + 2, this);
            } else {

                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }


        }

        if (isCompleted) {

            // Resmin boyutunu kademeli olarak arttır
            if (completionImageSize < completionImageMaxSize) {
                completionImageSize += 5;  // Ayarladığınız büyüme miktarını değiştirebilirsiniz
            }

            int centerX = (getWidth() - completionImageSize) / 2;
            int centerY = (getHeight() - completionImageSize) / 2;
            String message = "MOVES: " + numMoves + "   SCORE: " + score;
            g2d.setColor(Color.GREEN);
            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            g2d.drawString(message, getWidth() / 2 - 230, getHeight() / 2 + completionImageSize / 2 + 30);


            // Resmi kademeli olarak büyütülmüş boyutta çiz
            g2d.drawImage(completionImage, centerX, centerY, completionImageSize, completionImageSize, this);

        }

        if (duration.isTimerUp()) {
            gameOverImage = new ImageIcon("src/resources/gameover2.png").getImage();
            gameOverTimer.start();
            // Resmin boyutunu kademeli olarak arttır
            if (gameOverImageSize < gameOverImageMaxSize) {
                gameOverImageSize += 5;  // Ayarladığınız büyüme miktarını değiştirebilirsiniz
            }

            int centerX = (getWidth() - gameOverImageSize) / 2;
            int centerY = (getHeight() - gameOverImageSize) / 2;

            // Resmi kademeli olarak büyütülmüş boyutta çiz
            g2d.drawImage(gameOverImage, centerX, centerY, gameOverImageSize, gameOverImageSize, this);


        }


    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        super.paintComponent(g2d);

        g2d.drawImage(arkaPlanResmi, arkaPlanX, 0, getWidth(), getHeight(), this);
        g2d.drawImage(arkaPlanResmi, arkaPlanX + getWidth(), 0, getWidth(), getHeight(), this);

        buildWorld(g2d);
    }

    private void changePlayerDirection(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                soko.setDirection(0); // Set direction to right
                break;
            case KeyEvent.VK_LEFT:
                soko.setDirection(1); // Set direction to left
                break;
            case KeyEvent.VK_UP:
                soko.setDirection(2); // Set direction to up
                break;
            case KeyEvent.VK_DOWN:
                soko.setDirection(3); // Set direction to down
                break;
        }
    }

    public int getCurrentLevel() {
        return currentLevel;
    }


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (isCompleted) {
                return;
            }

            int key = e.getKeyCode();

            int originalX = soko.x();
            int originalY = soko.y();

            changePlayerDirection(key);

            switch (key) {
                case KeyEvent.VK_LEFT:
                    if (checkWallCollision(soko, LEFT_COLLISION)) {
                        playSE(4);
                        return;
                    }

                    if (checkBagCollision(LEFT_COLLISION)) {
                        return;
                    }

                    playSE(2);
                    soko.move(-SPACE, 0);
                    break;

                case KeyEvent.VK_RIGHT:
                    if (checkWallCollision(soko, RIGHT_COLLISION)) {
                        playSE(4);
                        return;
                    }

                    if (checkBagCollision(RIGHT_COLLISION)) {
                        return;
                    }

                    playSE(2);
                    soko.move(SPACE, 0);
                    break;

                case KeyEvent.VK_UP:
                    if (checkWallCollision(soko, TOP_COLLISION)) {
                        playSE(4);
                        return;
                    }

                    if (checkBagCollision(TOP_COLLISION)) {
                        return;
                    }

                    playSE(2);
                    soko.move(0, -SPACE);
                    break;

                case KeyEvent.VK_DOWN:
                    if (checkWallCollision(soko, BOTTOM_COLLISION)) {
                        playSE(4);
                        return;
                    }

                    if (checkBagCollision(BOTTOM_COLLISION)) {
                        return;
                    }

                    playSE(2);
                    soko.move(0, SPACE);
                    break;

                case KeyEvent.VK_R:
                    restartLevel(getCurrentLevel());
                    break;

                case KeyEvent.VK_ENTER:
                    startNextLevel();
                    break;

                case KeyEvent.VK_BACK_SPACE:
                    startPreviousLevel();
                    break;
                case KeyEvent.VK_ESCAPE:
                    int exitConfirmation = JOptionPane.showConfirmDialog(null, "Oyundan çıkmak istediğinizden emin misiniz?", "Çıkış Onayı", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (exitConfirmation == JOptionPane.YES_OPTION) {
                        LevelMenu levelMenu = new LevelMenu();
                        LevelPanel levelPanel = new LevelPanel(levelMenu);
                        levelPanel.setVisible(true);
                        stopMusic();
                        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(Board.this);
                        currentFrame.dispose();
                    }

                default:
                    return;
            }

            // Check if the Sokoban has moved
            if (soko.x() != originalX || soko.y() != originalY) {
                numMoves++;
                duration.setMove(numMoves);
            }

            repaint();
        }
    }

    private boolean checkWallCollision(Actor actor, int type) {

        switch (type) {

            case LEFT_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);
                    if (actor.isLeftCollision(wall)) {
                        return true;
                    }
                }

                return false;

            case RIGHT_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);
                    if (actor.isRightCollision(wall)) {
                        return true;
                    }
                }

                return false;

            case TOP_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);
                    if (actor.isTopCollision(wall)) {
                        return true;
                    }
                }

                return false;

            case BOTTOM_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);
                    if (actor.isBottomCollision(wall)) {
                        return true;
                    }
                }

                return false;

            default:
                break;
        }

        return false;
    }


    private boolean checkBagCollision(int collisionType) {
        switch (collisionType) {
            case LEFT_COLLISION:
                for (int i = 0; i < baggs.size(); i++) {
                    Baggage bag = baggs.get(i);

                    if (soko.isLeftCollision(bag)) {
                        for (int j = 0; j < baggs.size(); j++) {
                            Baggage item = baggs.get(j);

                            if (!bag.equals(item)) {
                                if (bag.isLeftCollision(item)) {
                                    playSE(4);
                                    return true;
                                }
                            }

                            if (checkWallCollision(bag, LEFT_COLLISION)) {
                                playSE(4);
                                return true;
                            }
                        }

                        // Check if the bag is moving away from its target area
                        if (checkMovedAwayFromTargetArea(bag, LEFT_COLLISION)) {
                            bag.setNewImage(getBaggageImageForLevel(bag.identifyType()));
                        }

                        bag.move(-SPACE, 0);
                        playSE(5);
                        isCompleted();
                    }
                }
                return false;

            case RIGHT_COLLISION:
                for (int i = 0; i < baggs.size(); i++) {
                    Baggage bag = baggs.get(i);

                    if (soko.isRightCollision(bag)) {
                        for (int j = 0; j < baggs.size(); j++) {
                            Baggage item = baggs.get(j);

                            if (!bag.equals(item)) {
                                if (bag.isRightCollision(item)) {
                                    playSE(4);
                                    return true;
                                }
                            }

                            if (checkWallCollision(bag, RIGHT_COLLISION)) {
                                playSE(4);
                                return true;
                            }
                        }

                        // Check if the bag is moving away from its target area
                        if (checkMovedAwayFromTargetArea(bag, RIGHT_COLLISION)) {
                            bag.setNewImage(getBaggageImageForLevel(bag.identifyType()));
                        }

                        bag.move(SPACE, 0);
                        playSE(5);
                        isCompleted();
                    }
                }
                return false;

            case TOP_COLLISION:
                for (int i = 0; i < baggs.size(); i++) {
                    Baggage bag = baggs.get(i);

                    if (soko.isTopCollision(bag)) {
                        for (int j = 0; j < baggs.size(); j++) {
                            Baggage item = baggs.get(j);

                            if (!bag.equals(item)) {
                                if (bag.isTopCollision(item)) {
                                    playSE(4);
                                    return true;
                                }
                            }

                            if (checkWallCollision(bag, TOP_COLLISION)) {
                                playSE(4);
                                return true;
                            }
                        }

                        // Check if the bag is moving away from its target area
                        if (checkMovedAwayFromTargetArea(bag, TOP_COLLISION)) {
                            bag.setNewImage(getBaggageImageForLevel(bag.identifyType()));
                        }

                        bag.move(0, -SPACE);
                        playSE(5);
                        isCompleted();
                    }
                }
                return false;

            case BOTTOM_COLLISION:
                for (int i = 0; i < baggs.size(); i++) {
                    Baggage bag = baggs.get(i);

                    if (soko.isBottomCollision(bag)) {
                        for (int j = 0; j < baggs.size(); j++) {
                            Baggage item = baggs.get(j);

                            if (!bag.equals(item)) {
                                if (bag.isBottomCollision(item)) {
                                    playSE(4);
                                    return true;
                                }
                            }

                            if (checkWallCollision(bag, BOTTOM_COLLISION)) {
                                playSE(4);
                                return true;
                            }
                        }

                        // Check if the bag is moving away from its target area
                        if (checkMovedAwayFromTargetArea(bag, BOTTOM_COLLISION)) {
                            bag.setNewImage(getBaggageImageForLevel(bag.identifyType()));
                        }

                        bag.move(0, SPACE);
                        playSE(5);
                        isCompleted();
                    }
                }
                return false;

            default:
                break;
        }

        return false;
    }


    private boolean checkMovedAwayFromTargetArea(Baggage bag, int collisionType) {
        int targetX = 0;
        int targetY = 0;

        switch (collisionType) {
            case LEFT_COLLISION, RIGHT_COLLISION:
                return bag.x() > targetX;
            case TOP_COLLISION, BOTTOM_COLLISION:
                return bag.y() > targetY;

            default:
                return false;
        }
    }


    private boolean canMoveToTargetArea(Baggage bag, Area area) {

        return bag.identifyType().equals(area.identifyType());
    }

    public void isCompleted() {
        int nOfBags = baggs.size();
        int finishedBags = 0;

        for (int i = 0; i < nOfBags; i++) {
            Baggage bag = baggs.get(i);

            for (int j = 0; j < nOfBags; j++) {
                Area area = areas.get(j);

                if (bag.x() == area.x() && bag.y() == area.y() && canMoveToTargetArea(bag, area)) {

                    String finishedBagImage = getBaggageImageForLevelComplete(bag.identifyType());
                    bag.setImage(new ImageIcon(finishedBagImage).getImage());
                    finishedBags += 1;
                }
            }
        }

        if (finishedBags == nOfBags) {
            stopMusic();
            playSE(1);
            isCompleted = true;
            score = 100 - numMoves;
            duration.stopDuration();
            completionImage = new ImageIcon("src/resources/Yeni_Proje__5_-removebg-preview.png").getImage();
            repaint();

            completionTimer.start();
        }

    }

    private void restartLevel(int k) {
        duration.resetDuration();
        duration.startDuration();
        areas.clear();
        baggs.clear();
        walls.clear();
        numMoves = 0;

        initWorld(k);

        if (isCompleted) {
            isCompleted = false;
        }
    }

    private void startNextLevel() {
        duration.resetDuration();
        duration.startDuration();
        currentLevel = getCurrentLevel() + 1;
        if (getCurrentLevel() == numLevels) {
            currentLevel = 0;
        }


        areas.clear();
        baggs.clear();
        walls.clear();
        numMoves = 0;

        initWorld(getCurrentLevel());

        if (isCompleted) {

            isCompleted = false;
            isCompleted = false;
            completionImageSize = 100;  // Reset the completion image size
            completionImage = new ImageIcon("src/resources/2.jpeg").getImage();
            repaint();
        }


    }

    private void startPreviousLevel() {
        duration.resetDuration();
        duration.startDuration();
        currentLevel = getCurrentLevel() - 1;
        if (getCurrentLevel() == -1) {
            currentLevel = numLevels - 1;
        }
        areas.clear();
        baggs.clear();
        walls.clear();
        numMoves = 0;

        initWorld(getCurrentLevel());

        if (isCompleted) {

            isCompleted = false;
        }

    }

}