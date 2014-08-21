package mastermind;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Natasa
 */

public class Combination {
    private final Board board;
    
    public enum Znak {herc, karo, pik, tref, zeko, zvezdica, prazno;

        private void draw(Graphics2D g2) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    Znak znakovi[][] = new Znak[4][6]; 
    public boolean fillFields;
    
    int x;
    private int y;
    private int width;
    private int height;
    private final int w = 50;
    private final int h = 60;
    
     public Combination (Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
     }
  
    
        public void draw(Graphics2D g2) {
        int a = 4;
        int b = 6;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                znakovi[i][j].draw(g2);
            }
        }
    }

    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x, y+10, w, h-20);
    }
    public void addSign() {
        
    }
    
    public void deleteSign() {
        
    }
    
    
}
