package mastermind;


import java.awt.Color;
import java.awt.Graphics2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Natasa
 */
public class Table {
    
     Polje[][] polja;
    
    final int REDOVI;
    final int KOLONE;
   

    public Table (int x, int y) {
        REDOVI = 4;
        KOLONE = 6;

        polja = new Polje[REDOVI][KOLONE];

        for (int i = 0; i < REDOVI; i++) {
            for (int j = 0; j < KOLONE; j++) {
                polja[i][j] = new Polje((x + 50 * i), (y + 60 * j));
            }
        }
    }

    public void draw(Graphics2D g2) {
        int ROWS = 4;
        int COLS =6;
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                polja[i][j].draw(g2);
            }
        }
        
        
    }

}
