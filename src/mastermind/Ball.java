package mastermind;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Natasa
 */
public class Ball extends Rectangle.Double implements GameObject {
    private final int w = 50;
    private final int h = 50;
    private Board board;
    private Ellipse2D.Double ellipseForDrawing;
    
    private Color fillColor = Color.BLUE;
    private Color borderColor = Color.BLACK;
    
    /**
     * 
     * @param board Tabla kojoj lopta pripada.
     */
    public Ball(Board board) {
        this.board = board;
        width = w;
        height = h;
    }
    /**
     * Vrši iscrtavanje lopte na tabli.
     * @param g2 Graphics2D objekat na kojem se vrši iscrtavanje.
     */
    @Override
    public void draw(Graphics2D g2) {
        ellipseForDrawing = new Ellipse2D.Double(x, y, w, h);
        
        g2.setPaint(fillColor);
        g2.fill(ellipseForDrawing);
        
        g2.setPaint(borderColor);
        g2.draw(ellipseForDrawing);
    }
}
