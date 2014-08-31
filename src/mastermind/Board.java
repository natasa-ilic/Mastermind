package mastermind;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Stack;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Natasa
 */
public class Board extends JPanel implements Runnable {

    //širina panela
    public final int PANEL_WIDTH = 900;
    //visina panela
    public final int PANEL_HEIGHT = 600;
    
    //brojači
    int i = 1;
    int j = 0;
    int brpo = 0;
    
    private Image beginning_image;
    
    //postavljanje pozadinske boje
    final Color BACKGROUND_COLOR = Color.orange;
    final Thread runner;
    int br = 0;
    Dugmic[] dugmici = new Dugmic[6];

    //dva dugmeta za unos i brisanje kombinacije i dva nova panela na kojima ce se iscrtavati kombinacija i vrsiti provjera kombinacije
    JButton entry = new JButton("Confirm");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JButton backspace = new JButton("Delete");
    
    Boolean inGame;
    
    //niz od 6 znakova koji se koriste za kombinaciju
    private Vector<Character> lettersVector = new Vector<>(6);
    //niz za rjesenje
    private Vector<Character> solution = new Vector<>(4);
    //niz za pokusaj
    private Vector<Character> attempt = new Vector<>(4);
    private Stack<Dugmic> delete_stack = new Stack<Dugmic>();
    
    String message;

    public Board() {

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setFocusable(true);
        setFont(getFont().deriveFont(Font.BOLD, 20f));
        setDoubleBuffered(true);
    
        initDugmici();
        
        //dodavanje dugmica za unos kombinacije (potvrdu)
        entry.setBounds(415, 100, 80, 30);
        entry.setVisible(false);
        add(entry);
        
        //dodavanje panela
        add(panel1);
        add(panel2);
        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        
        //dodavanje dugmeta za brisanje
        backspace.setBounds(415, 140, 80, 30);
        backspace.setVisible(false);
        add(backspace);
        
        inGame = false;
           
        //kreiranje liste znakova
        lettersVector.addAll(Arrays.asList('H', 'K', 'P', 'T', 'S', 'Z'));
        Collections.shuffle(lettersVector);
        System.out.println(lettersVector);
        
        //kreiranje niza rjesenja (random generise niz od 4 znaka)
        solution.addAll(lettersVector.subList(0, 4));
        System.out.println(solution);
     
        //određivanje granica za panele
        panel1.setBounds(50, 100, 300, 450);
        panel2.setBounds(550, 100, 300, 450);
        panel1.setVisible(false);
        panel2.setVisible(false);

        runner = new Thread(this);
        runner.start();
    }

    //metoda koja se poziva prilikom svakog startovanja igrice, postavlja panele i sve dugmice vidljivim
    public void startGame() {
        inGame = true;
        for (int i = 0; i < 6; i++) {
            dugmici[i].setVisible(true);
        }
        entry.setVisible(true);
        backspace.setVisible(true);
        panel1.setVisible(true);
        panel2.setVisible(true);
    }

    //metoda koja se poziva prilikom zavrsetka igrice 
    public void stopGame(String message) {
        inGame = false;
        entry.setVisible(false);
        backspace.setVisible(false);
        for (int i = 0; i < 6; i++) {
            dugmici[i].setVisible(false);
        }
        panel1.setVisible(false);
        panel2.setVisible(false); 
    }
    

    //metoda za iscrtavanje; ako igrica nije aktivna, vraća nam početnu sliku
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        if (inGame) {
            //sinhronizovanje sa grafičkom kartom
            Toolkit.getDefaultToolkit().sync();
            //optimizacija upotrebe RAM-a
            g.dispose();
        } else {
           beginning_image = new ImageIcon(getClass().getResource("pocetna.png")).getImage();
           g2.drawImage(beginning_image, 0, 0, null ); 
        }
    }

    //metoda koja provjerava da li je igrica aktivna, ako jeste onda ponovo iscrtava
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

    //rad sa dugmicima
    private void initDugmici() {
        final String[] slike = {
            "herc.png",
            "karo.png",
            "pik.png",
            "tref.png",
            "zeko.png",
            "zvezdica.png"
        };

        //ubacivanje 6 dugmica na vrhu stranice
        for (int i = 0; i < 6; i++) {
            dugmici[i] = new Dugmic(i*50, 0, 50, 60, ".\\pictures\\" + slike[i]);
            dugmici[i].setVisible(false);
            add(dugmici[i]);
        }

     /*   dugmici[i].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Click("herc", 'H', 1, 0, slike[0]);
                Click("karo", 'K', 1, 0, slike[1]);
                Click("pik", 'P', 1, 0, slike[2]);
                Click("tref", 'T', 1, 0, slike[3]);
                Click("zeko", 'S', 1, 0, slike[4]);
                Click("zvezdica", 'Z',1, 0, slike[5]);
            }
        });*/
        
        //dugme sa znakom "herc" se iscrtava u panelu klikom na njega
        dugmici[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("herc");
                if (br < 4) {
                    attempt.add('H');
	Dugmic s = new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + slike[0]);
                    panel1.add(s);
                    delete_stack.push(s);
                    br++;
                    i++;
                }
                System.out.println(attempt);
            }
        });

        //dugme sa znakom "karo" se iscrtava u panelu klikom na njega
        dugmici[1].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("karo");
                if (br < 4) {
                    attempt.add('K');
                    Dugmic s = new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + slike[1]);
                    panel1.add(s);
                    delete_stack.push(s);
                    br++;
                    i++;
                }
                System.out.println(attempt);
            }
        });
        
        //dugme sa znakom "pik" se iscrtava u panelu klikom na njega
        dugmici[2].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("pik");
                if (br < 4) {
                    attempt.add('P');
                    Dugmic s = new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + slike[2]);
                    panel1.add(s);
                    delete_stack.push(s);
                    i++;
                    br++;
                }
                System.out.println(attempt);
            }
        });

        //dugme sa znakom "tref" se iscrtava u panelu klikom na njega
        dugmici[3].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("tref");
                if (br < 4) {
                    attempt.add('T');
                    Dugmic s = new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + slike[3]);
                    panel1.add(s);
                    delete_stack.push(s);
                    i++;
                    br++;
                }
                System.out.println(attempt);
            }
        });

        //dugme sa znakom "zeka" se iscrtava u panelu klikom na njega
        dugmici[4].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("zeka");
                if (br < 4) {
                    attempt.add('S');
                    Dugmic s = new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + slike[4]);
                    panel1.add(s);
                    delete_stack.push(s);
                    i++;
                    br++;
                }
                System.out.println(attempt);
            }
        });

        //dugme sa znakom "zvezda" se iscrtava u panelu klikom na njega
        dugmici[5].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("zvezda");
                if (br < 4) {
                    attempt.add('Z');
                    Dugmic s = new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + slike[5]);
                    panel1.add(s);
                    delete_stack.push(s);
                    i++;
                    br++;
                }
                System.out.println(attempt);
            }
        });

        //dugme za brisanje
        backspace.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                br--;
                if (br < 0) {
                    br = 0;
                } else {
                    attempt.remove(br);
                    System.out.println(attempt);
                    panel1.remove(delete_stack.pop());
                    //potezi.pop();
                    System.out.println(delete_stack);
                    i--;
                }
            }
        });

        //dugme za unos/potvrdu kombinacije
        entry.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (brpo == 5) {
                    Provjera();
                    JOptionPane.showMessageDialog(panel2, "Game over!");
                    stopGame(message);
                } else {
                    if (br == 4) {
                        System.out.println("potvrda");
                        Provjera();
                        j++;
                        attempt.removeAll(attempt);
                        i = 1;
                        br = 0;
                        brpo++;
                    } else {
                        JOptionPane.showMessageDialog(panel2, "You have to fill in all fields!");
                    }
                }
            }
        });
    }
    
    private void Click(String znak, char z, int i, int j,  String imagePath) {
        System.out.println(znak);
        if (br < 4) {
            attempt.add(z);
            panel1.add(new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + imagePath));
        }
        System.out.println(attempt);
    }

    public Board(Thread runner) {
        this.runner = runner;
    }

    //provjera rjesenja, tj. da li se kombinacija koju mi unosimo poklapa sa zadatom kombinacijom
    private void Provjera() {
        int brX = 0; //broj pogodjenih koji se nalaze na tacnom mjestu
        int brO = 0; //broj pogodjenih koji se ne nalaze na tacnom mjestu

        // prolazi se kroz rjesenje
        for (int s = 0; s < solution.size(); s++) {
            // ako se i-to slovo rjesenja nalazi na i-tom mjestu pokusaja, to je X
            if (Objects.equals(solution.get(s), attempt.get(s))) {
                brX++;
            } else {
                // prolazi se kroz pokusaj
                for (int v = 0; v < attempt.size(); v++) // pri nailasku na prvu pojavu i-tog slova rjesenja u pokusaju
                {
                    if (Objects.equals(solution.get(s), attempt.get(v))) {
                        brO++; // ubroji se O i prelazi na sledece slovo rjesenja
                        break;
                    }
                }
            }
        }
        
        JButton location;
        int k;
        int m = brX + brO;
        for (k = 0; k < m; k++) {
            if (k < brX) {
                System.out.println("na mjestu");
                location = new JButton();
                //ako je znak pogodjen i na tacnom mjestu, iscrta se crveno dugme
                location.setBackground(Color.RED);
                location.setBounds(k * 50, j * 70, 50, 60);
                panel2.add(location);
            } else {
                System.out.println("nije na mjestu");
                location = new JButton();
                //ako je znak pogodjen i nije na tacnom mjestu, iscrta se zuto dugme
                location.setBackground(Color.YELLOW);
                location.setBounds(k * 50, j * 70, 50, 60);
                panel2.add(location);
            }
        }
        
        //ako je pogođena trazena kombinacija, otvori nam se kartica sa obavjestenjem
            if (brX == 4) {
            JOptionPane.showMessageDialog(panel1, "You win!");
            stopGame(message);
        }
        
    }
    
    //metoda koja daje dodatno objašnjenje kako se igrica igra
    public void Help() {
        JOptionPane.showMessageDialog(null,
                "There are six signs at the top of the table.\n\n"
                + "Computer randomly takes four out of those six\n\n"
                + "and in that way makes combination which the player should guess.\n\n"
                + "There are red rectangles drawn in the right panel\n\n"
                + "if the signs exist and if they are in the right place,\n\n"
                + "yellow rectangles are drown if the signs exist \n\n"
                + "but are not in the right place,\n\n"
                + "and grey rectangles are drown if those signs don't exist in that combination.\n\n"
                        + "Good luck!",
                "Help", JOptionPane.INFORMATION_MESSAGE);
    }

}