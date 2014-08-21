/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mastermind;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Natasa
 */
public class Polje {
    
    private final int x;
    private final int y;
    int width;
    private final int hight;
    
    
    public Polje(int x, int y) {

      
       this.x=x;
       this.y=y;
       width=50;
       hight=60;
       Rectangle r = new Rectangle(x,y,width,hight);
       
    }
    
     public void draw(Graphics2D g2) {
            
        Rectangle r = new Rectangle(x,y,width,hight);
        
        g2.setColor(Color.LIGHT_GRAY);
        g2.fill(r);
        g2.setColor(Color.black);
        g2.draw(r);
        
        }
}
