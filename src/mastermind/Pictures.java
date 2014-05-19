package mastermind;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Natasa
 */
    public class Pictures {
    
    private final int x;
    private final int y;
    private int dx;
    private int dy;
    
    private final int width;
    private final int height;
    
    private final Image image1, image2, image3, image4, image5, image6;
   

    public Pictures(int x, int y) {
        this.x = x;
        this.y = y;
        
        width = 50;
        height = 50;
        
        image1 = new ImageIcon(getClass().getResource("herc.png")).getImage();
        image2 = new ImageIcon(getClass().getResource("karo.png")).getImage();
        image3 = new ImageIcon(getClass().getResource("pik.png")).getImage();
        image4 = new ImageIcon(getClass().getResource("tref.png")).getImage();
        image5 = new ImageIcon(getClass().getResource("zeko.png")).getImage();
        image6 = new ImageIcon(getClass().getResource("zvezdica.png")).getImage();
        
    }
    
    public void draw(Graphics2D g2) {
        g2.drawImage(image1, x, y, width, height, null);
        g2.drawImage(image2, x, y, width, height, null);
        g2.drawImage(image3, x, y, width, height, null);
        g2.drawImage(image4, x, y, width, height, null);
        g2.drawImage(image5, x, y, width, height, null);
        g2.drawImage(image6, x, y, width, height, null);
        
    }
   
} 