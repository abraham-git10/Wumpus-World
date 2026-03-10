import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class WumpusPanel extends JPanel implements KeyListener {
    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int WON = 2;
    private int status;
    private boolean cheat;
    private boolean first;
    private boolean shotr;
    private boolean shotc;
    private boolean ws = false;
    private WumpusPlayer player;
    private WumpusMap map;
    private BufferedImage floor;
    private BufferedImage arrow;
    private BufferedImage fog;
    private BufferedImage gold;
    private BufferedImage ladder;
    private BufferedImage pit;
    private BufferedImage breeze;
    private BufferedImage wumpus;
    private BufferedImage deadWumpus;
    private BufferedImage stench;
    private BufferedImage playerUp;
    private BufferedImage playerDown;
    private BufferedImage playerLeft;
    private BufferedImage playerRight;
    private BufferedImage buffer;
    public WumpusPanel(int w, int h) {
        super();
        setSize(w, h);
        cheat = false;
        addKeyListener(this);
        reset();
        try {
            arrow = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/arrow.gif"));
            floor = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/Floor.gif"));
            fog = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/black.gif"));
            gold = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/gold.gif"));
            ladder = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/ladder.gif"));
            pit = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/pit.gif"));
            breeze = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/breeze-1.gif"));
            wumpus = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/wumpus.gif"));
            deadWumpus = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/deadwumpus.gif"));
            stench = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/stench.gif"));
            playerUp = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/playerUp.png"));
            playerDown = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/playerDown.png"));
            playerLeft = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/playerLeft.png"));
            playerRight = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/playerRight.png"));
            buffer = ImageIO.read(WumpusPanel.class.getResource("/ImagePackage/Picture1.gif"));
        }
        catch(Exception e) {
            System.out.println("Error");
            return;
        }
    }
    public void reset() {
        status = PLAYING;
        first = true;
        map = new WumpusMap();
        player = new WumpusPlayer();
        player.setColPosition(map.getLadderCol());
        player.setRowPosition(map.getLadderRow());
    }
    public void paint(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 600, 760);
        g.setColor(Color.BLACK);
        g.fillRect(0, 550, 190, 120);
        g.setColor(Color.RED);
        g.setFont(new Font("Calibre", Font.PLAIN, 25));
        g.drawString("Inventory:", 5, 575);
        if(player.getArrow()) {
            g.drawImage(arrow, 5, 595, null);
        }
        if(player.getGold()) {
            g.drawImage(gold, 55, 595, null);
        }
        g.setColor(Color.BLACK);
        g.fillRect(200, 550, 400, 120);
        g.setColor(Color.RED);
        g.setFont(new Font("Calibre", Font.PLAIN, 25));
        g.drawString("Messages: ", 220, 575);
        g.setColor(Color.CYAN);
        g.setFont(new Font("Calibre", Font.PLAIN, 15));
        int l = 0;
        if(status == WON) {
            g.drawString("You climb out of the cave with the treasure", 220, 595);
        }
        else {
            if (map.getSquare(player.getColPosition(), player.getRowPosition()).getBreeze()) {
                g.drawString("You feel a breeze", 220, 595 + l * 15);
                l++;
            }
            if (map.getSquare(player.getColPosition(), player.getRowPosition()).getStench()) {
                g.drawString("You smell a stench", 220, 595 + l * 15);
                l++;
            }
            if (map.getSquare(player.getColPosition(), player.getRowPosition()).getPit()) {
                g.drawString("You fell down a pit to your death", 220, 595 + l * 15);
                l++;
            }
            if (map.getSquare(player.getColPosition(), player.getRowPosition()).getWumpus()) {
                g.drawString("You are eaten by the Wumpus", 220, 595 + l * 15);
                l++;
            }
            if (map.getSquare(player.getColPosition(), player.getRowPosition()).getGold()) {
                g.drawString("You see a glimmer", 220, 595 + l * 15);
                l++;
            }
            if (map.getSquare(player.getColPosition(), player.getRowPosition()).getLadder()) {
                g.drawString("You bump into a ladder", 220, 595 + l * 15);
                l++;
            }
            if (ws) {
                g.drawString("You hear a scream", 220, 595 + l * 15);
                ws = false;
            }
        }
        int row = 0;
        int col = 0;
        for(int r = 20;r < 520;r+=50) {
            for (int c = 50; c < 550; c += 50) {
                g.drawImage(floor, c, r, null);
                if (map.getSquare(col, row).getPit()) {
                    g.drawImage(pit, c, r, null);
                }
                else if (map.getSquare(col, row).getBreeze() == true) {
                    g.drawImage(breeze, c, r, null);
                }if (map.getSquare(col, row).getStench() == true && !map.getSquare(col, row).getPit()) {
                    g.drawImage(stench, c, r, null);
                } if (map.getSquare(col, row).getGold() == true) {
                    g.drawImage(gold, c, r, null);
                } if (map.getSquare(col, row).getLadder() == true) {
                    g.drawImage(ladder, c, r, null);
                } if (map.getSquare(col, row).getWumpus() == true) {
                    g.drawImage(wumpus, c, r, null);
                } if (map.getSquare(col, row).getDeadWumpus() == true) {
                    g.drawImage(deadWumpus, c, r, null);
                }
                if(!cheat) {
                    if(!map.getSquare(col, row).getVisited()) {
                        if (!(player.getRowPosition() == row && player.getColPosition() == col)) {
                            g.drawImage(fog, c, r, 50, 50, null);
                        }
                    }
                }
                col++;
            }
            row++;
            col = 0;
        }
        if(first) {
            int o = map.getLadderRow();
            int u = map.getLadderCol();
            g.drawImage(playerUp, u * 50 + 50, o * 50 + 20, null);
            map.getSquare(u, o).setVisited(true);
            first = false;
        }
        else {
            if (player.getDirection() == 0) {
                g.drawImage(playerUp, player.getColPosition() * 50 + 50, player.getRowPosition() * 50 + 20, null);
                map.getSquare(player.getColPosition(), player.getRowPosition()).setVisited(true);
            } else if (player.getDirection() == 1) {
                g.drawImage(playerRight, player.getColPosition() * 50 + 50, player.getRowPosition() * 50 + 20, null);
                map.getSquare(player.getColPosition(), player.getRowPosition()).setVisited(true);
            } else if (player.getDirection() == 2) {
                g.drawImage(playerDown, player.getColPosition() * 50 + 50, player.getRowPosition() * 50 + 20, null);
                map.getSquare(player.getColPosition(), player.getRowPosition()).setVisited(true);
            } else if (player.getDirection() == 3) {
                g.drawImage(playerLeft, player.getColPosition() * 50 + 50, player.getRowPosition() * 50 + 20, null);
                map.getSquare(player.getColPosition(), player.getRowPosition()).setVisited(true);
            }
        }
        if(shotr) {
            if(player.getArrow()) {
                for (int i = 0; i < 10; i++) {
                    if (map.getSquare(player.getColPosition(), i).getWumpus()) {
                        map.getSquare(player.getColPosition(), i).setWumpus(false);
                        map.getSquare(player.getColPosition(), i).setDeadWumpus(true);
                        i = 10;
                        ws = true;
                    }
                }
                player.setArrow(false);
                repaint();
            }
            shotr = false;
        }
        if(shotc) {
            if(player.getArrow()) {
                for (int i = 0; i < 10; i++) {
                    if (map.getSquare(i, player.getRowPosition()).getWumpus()) {
                        map.getSquare(i, player.getRowPosition()).setWumpus(false);
                        map.getSquare(i, player.getRowPosition()).setDeadWumpus(true);
                        i = 10;
                        ws = true;
                    }
                }
                player.setArrow(false);
                repaint();
            }
            shotc = false;
        }
        if(map.getSquare(player.getColPosition(), player.getRowPosition()).getPit() || map.getSquare(player.getColPosition(), player.getRowPosition()).getWumpus()) {
            status = DEAD;
        }
    }
    public void keyPressed(KeyEvent e) {

    }
    public void keyReleased(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        if(key == 'w' && status == PLAYING) {
            player.setDirection(0);
            if(player.getRowPosition() > 0) {
                player.setRowPosition(player.getRowPosition() - 1);
            }
        }
        else if(key == 'd' && status == PLAYING) {
            player.setDirection(1);
            if(player.getColPosition() < 9) {
                player.setColPosition(player.getColPosition() + 1);
            }
        }
        else if(key == 's' && status == PLAYING) {
            player.setDirection(2);
            if(player.getRowPosition() < 9) {
                player.setRowPosition(player.getRowPosition() + 1);
            }
        }
        else if(key == 'a' && status == PLAYING) {
            player.setDirection(3);
            if(player.getColPosition() > 0) {
                player.setColPosition(player.getColPosition() - 1);
            }
        }
        else if(key == 'i' && status == PLAYING) {
            player.setDirection(0);
            shotr = true;
        }
        else if(key == 'l' && status == PLAYING) {
            player.setDirection(1);
            shotc = true;
        }
        else if(key == 'k' && status == PLAYING) {
            player.setDirection(2);
            shotr = true;
        }
        else if(key == 'j' && status == PLAYING) {
            player.setDirection(3);
            shotc = true;
        }
        else if(key == 'c' && status == PLAYING) {
            if(player.getGold()) {
                if(player.getRowPosition() == map.getLadderRow() && player.getColPosition() == map.getLadderCol()) {
                    status = WON;
                    //Add Message
                }
            }
        }
        else if(key == 'p' && status == PLAYING) {
            if(map.getSquare(player.getColPosition(), player.getRowPosition()).getGold()) {
                //Message
                player.setGold(true);
                map.getSquare(player.getColPosition(), player.getRowPosition()).setGold(false);
            }
        }
        else if(key == 'n') {
            if(status == WON || status == DEAD) {
                reset();
            }
        }
        else if(key == '*') {
            if(cheat) {
                cheat = false;
            }
            else {
                cheat = true;
            }
        }
        repaint();
    }
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
}