import javax.swing.*;
import java.awt.*;

public class WumpusFrame extends JFrame {
    public WumpusFrame(String frameName, int panelWidth, int panelHeight) {
        super(frameName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        WumpusPanel p = new WumpusPanel(panelWidth, panelHeight);
        p.setBounds(0, 0, panelWidth, panelHeight);
        Insets frameInsets = getInsets();
        int frameWidth = panelWidth + (frameInsets.left + frameInsets.right);
        int frameHeight = panelHeight + (frameInsets.top + frameInsets.bottom);
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setLayout(null);
        add(p);
        pack();
        setVisible(true);
    }
}