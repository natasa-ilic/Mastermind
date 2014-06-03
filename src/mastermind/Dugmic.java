package mastermind;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import java.awt.*;
import javax.swing.JButton;

/**
 *
 * @author Natasa
 */
/* public abstract class Dugmici extends JFrame implements ActionListener {

    private boolean b1, b2, b3, b4, b5, b6;

    Container contentPane = getContentPane();
    JButton h = new JButton("herc");
    JButton p = new JButton("pik");
    JButton k = new JButton("karo");
    JButton t = new JButton("tref");
    JButton z1 = new JButton("zeko");
    JButton z2 = new JButton("zvezdica");
    private Image image1, image2, image3, image4, image5, image6;

    public Dugmici() {
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new FlowLayout());

        h.addActionListener(this);
        contentPane.add(h).setVisible(true);

        p.addActionListener(this);
        contentPane.add(p).setVisible(true);

        k.addActionListener(this);
        contentPane.add(k).setVisible(true);

        t.addActionListener(this);
        contentPane.add(t).setVisible(true);

        z1.addActionListener(this);
        contentPane.add(z1).setVisible(true);

        z2.addActionListener(this);
        contentPane.add(z2).setVisible(true);

    }

    public void init() {

        image1 = Toolkit.getDefaultToolkit().getImage("herc.png");
        image2 = Toolkit.getDefaultToolkit().getImage("pik.png");
        image3 = Toolkit.getDefaultToolkit().getImage("karo.png");
        image4 = Toolkit.getDefaultToolkit().getImage("tref.png");
        image5 = Toolkit.getDefaultToolkit().getImage("zeko.png");
        image6 = Toolkit.getDefaultToolkit().getImage("zvezdica.png");

    }

    @Override
    public void paint(Graphics g) {

        if (b1 == true) {
            g.drawImage(image1, 0, 0, this);
        } else if (b2 == true) {
            g.drawImage(image2, 0, 0, this);
        } else if (b3 == true) {
            g.drawImage(image3, 0, 0, this);
        } else if (b4 == true) {
            g.drawImage(image4, 0, 0, this);
        } else if (b5 == true) {
            g.drawImage(image5, 0, 0, this);
        } else if (b6 == true) {
            g.drawImage(image6, 0, 0, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String actionCommand = event.getActionCommand();

        if (actionCommand.equals("h")) {
            b1 = true;
        } else if (actionCommand.equals("p")) {
            b2 = true;
        } else if (actionCommand.equals("k")) {
            b3 = true;
        } else if (actionCommand.equals("t")) {
            b4 = true;
        } else if (actionCommand.equals("z1")) {
            b5 = true;
        } else if (actionCommand.equals("z2")) {
            b6 = true;
        }
    }

    public static void main(String args[]) {
        Dugmici m = new Dugmici() {
        };
        m.setVisible(true);
    } */

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