package mastermind;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Natasa
 */
public class Board extends JPanel implements Runnable {

    public final int PANEL_WIDTH = 700;

    public final int PANEL_HEIGHT = 600;

    final Color BACKGROUND_COLOR = Color.orange;
    final Thread runner;
    
    Dugmic[] dugmici = new Dugmic[6];
    
    Dugmic[] pokusaj = new Dugmic[2];

    Boolean inGame;
    Polje polje;
    Table table1;
    Table table2;
    
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
        
        table1 = new Table(50,100);
        table2 = new Table (450,100);
        runner = new Thread(this);
        runner.start();
       
        
    }

    public void startGame() {
        inGame = true;
        for (int i = 0; i < 6; i++) {
            dugmici[i].setVisible(true); 
        }
        for (int i = 0; i < 2; i++) {
            pokusaj[i].setVisible(true);
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
            g2.setColor(Color.red);
           
            table1.draw(g2);
            table2.draw(g2);
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
        String[] slike1 = {
            "herc.png",
            "karo.png",
            "pik.png",
            "tref.png",
            "zeko.png",
            "zvezdica.png"
        };
        
        String[] slike2 = {
            "delete.png",
            "true.png"
        };
        
        for (int i = 0; i < 6; i++) {
            dugmici[i] = new Dugmic(i*50, 0, 50, 60, ".\\pictures\\" + slike1[i]);
            dugmici[i].setVisible(false);
            add(dugmici[i]);
            
        }
        for (int i = 0; i < 2; i++) {
            pokusaj[i] = new Dugmic (320, i*50, 70, 30, ".\\pictures\\" + slike2[i]);
            pokusaj[i].setVisible(false);
            add(pokusaj[i]);
        }
    }
   
}
