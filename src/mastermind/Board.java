package mastermind;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
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
    
    Dugmic[] dugmici = new Dugmic[6];

    Boolean inGame;

    String message;

    public Board() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setFocusable(true);
        setFont(getFont().deriveFont(Font.BOLD, 20f));
        setDoubleBuffered(true);
        
        initDugmici();

        inGame = false;
        message = "Mastermind!";

        runner = new Thread(this);
        runner.start();
    }

    public void startGame() {
        inGame = true;
        for (int i = 0; i < 6; i++) {
            dugmici[i].setVisible(true); 
        }
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

    private void initDugmici() {
        String[] slike = {
            "herc.png",
            "karo.png",
            "pik.png",
            "tref.png",
            "zeko.png",
            "zvezdica.png"
        };
        
        for (int i = 0; i < 6; i++) {
            dugmici[i] = new Dugmic(i*50, 0, 50, 60, ".\\pictures\\" + slike[i]);
            dugmici[i].setVisible(false);
            add(dugmici[i]);
        }
    }
}
