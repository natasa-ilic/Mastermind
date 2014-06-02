package mastermind;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Natasa
 */
public class Board extends JPanel implements Runnable {

    public final int PANEL_WIDTH = 800;

    public final int PANEL_HEIGHT = 600;

    final Color BACKGROUND_COLOR = Color.orange;
    final Thread runner;

    Boolean inGame;

    String message;

    public Board() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setFocusable(true);
        setFont(getFont().deriveFont(Font.BOLD, 20f));
        setDoubleBuffered(true);

        inGame = false;
        message = "Mastermind!";

        runner = new Thread(this);
        runner.start();
    }

    public void startGame() {

        inGame = true;
    }

    public void stopGame(String message) {
        inGame = false;
        this.message = message;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        if (inGame) {

            Dugmici button = new Dugmici(10, 0, 50, 50, "herc.png");
            add(button);
       
            Toolkit.getDefaultToolkit().sync();

            g.dispose();
        } else {
            int messageWidth = getFontMetrics(getFont()).stringWidth(message);
            g2.drawString(message, PANEL_WIDTH / 2 - messageWidth / 2, PANEL_HEIGHT / 2);
        }

    }

    @Override
    public void run() {
        while (true) {

            repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }
    }

}
