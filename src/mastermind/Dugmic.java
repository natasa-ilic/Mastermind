package mastermind;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Natasa
 */

    public class Dugmic extends JButton implements ActionListener {

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param filename
     */
    public Dugmic(int x, int y, int width, int height, String filename) {
        setBounds(x, y, width, height);
        setIcon(new ImageIcon(getClass().getResource(filename)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
 }