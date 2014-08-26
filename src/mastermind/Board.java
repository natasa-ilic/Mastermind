package mastermind;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Natasa
 */
public class Board extends JPanel implements Runnable {

    public final int PANEL_WIDTH = 900;
    public final int PANEL_HEIGHT = 600;
    
    int i = 1;
    int j = 0;
    int brpo = 0;
    
    final Color BACKGROUND_COLOR = Color.orange;
    final Thread runner;
    int br = 0;
    Dugmic[] dugmici = new Dugmic[6];

    //dva dugmeta za unos i brisanje kombinacije i dva nova panela na kojima ce se iscrtavati kombinacija i vrsiti provjera kombinacije
    JButton entry = new JButton("Potvrdi");
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JButton delete = new JButton("Nazad");
    
    Boolean inGame;
    
    //niz od 6 znakova koji se koriste za kombinaciju
    private Vector<Character> lettersVector = new Vector<>(6);
   //niz za rjesenje
    private Vector<Character> rjesenje = new Vector<>(4);
    //niz za pokusaj
    private Vector<Character> pokusaj = new Vector<>(4);
    
    String message;

    public Board() {

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setFocusable(true);
        setFont(getFont().deriveFont(Font.BOLD, 20f));
        setDoubleBuffered(true);

        initDugmici();
        
        //dodavanje dugmeta za unos kombinacije (potvrdu)
        entry.setBounds(415, 100, 80, 30);
        entry.setVisible(false);
        add(entry);
        
        //dodavanje panela
        add(panel);
        add(panel1);
        
        //dodavanje dugmeta za brisanje
        delete.setBounds(415, 140, 80, 30);
        delete.setVisible(false);
        add(delete);
        
        inGame = false;
        message = "Mastermind!";
        
        //kreiranje liste znakova
        lettersVector.addAll(Arrays.asList('H', 'K', 'P', 'T', 'S', 'Z'));
        Collections.shuffle(lettersVector);
        System.out.println(lettersVector);
        
        //kreiranje niza rjesenja (random generise niz od 4 znaka)
        rjesenje.addAll(lettersVector.subList(0, 4));
        System.out.println(rjesenje);
     
        //odredjivanje granica za panele
        panel.setBounds(550, 100, 300, 450);
        panel1.setBounds(50, 100, 300, 450);
        panel.setVisible(false);
        panel1.setVisible(false);

        runner = new Thread(this);
        runner.start();
    }

    public void startGame() {
        inGame = true;
        for (int i = 0; i < 6; i++) {
            dugmici[i].setVisible(true);
        }
        entry.setVisible(true);
        delete.setVisible(true);
        panel.setVisible(true);
        panel1.setVisible(true);
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
            dugmici[i] = new Dugmic(i * 50, 0, 50, 60, ".\\pictures\\" + slike[i]);
            dugmici[i].setVisible(false);
            add(dugmici[i]);

        }

        /*dugmici[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Click("herc", 'H', 1, 0, slike[0]);
            }
        });*/
        
        //dugme sa znakom "herc" se iscrtava u panelu klikom na njega
        dugmici[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("herc");
                if (br < 4) {
                    pokusaj.add('H');
                    panel1.add(new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + slike[0]));
                    br++;
                    i++;
                }
                System.out.println(pokusaj);
            }
        });

        //dugme sa znakom "karo" se iscrtava u panelu klikom na njega
        dugmici[1].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("karo");
                if (br < 4) {
                    pokusaj.add('K');
                    panel1.add(new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + slike[1]));
                    br++;
                    i++;
                }
                System.out.println(pokusaj);
            }
        });
        
        //dugme sa znakom "pik" se iscrtava u panelu klikom na njega
        dugmici[2].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("pik");
                if (br < 4) {
                    pokusaj.add('P');
                    panel1.add(new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + slike[2]));
                    i++;
                    br++;

                }
                System.out.println(pokusaj);
            }
        });

        //dugme sa znakom "tref" se iscrtava u panelu klikom na njega
        dugmici[3].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("tref");
                if (br < 4) {
                    pokusaj.add('T');
                    panel1.add(new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + slike[3]));
                    i++;
                    br++;
                }
                System.out.println(pokusaj);
            }
        });

        //dugme sa znakom "zeka" se iscrtava u panelu klikom na njega
        dugmici[4].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("zeka");
                if (br < 4) {
                    pokusaj.add('S');
                    panel1.add(new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + slike[4]));
                    i++;
                    br++;
                }
                System.out.println(pokusaj);
            }
        });

        //dugme sa znakom "zvezda" se iscrtava u panelu klikom na njega
        dugmici[5].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("zvezda");
                if (br < 4) {
                    pokusaj.add('Z');
                    panel1.add(new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + slike[5]));
                    i++;
                    br++;
                }
                System.out.println(pokusaj);
            }
        });

        //dugme za brisanje
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                br--;
                if (br < 0) {
                    br = 0;
                } else {
                    pokusaj.remove(br);
                    i--;
                }
            }
        });

        //dugme za unos/potvrdu kombinacije
        entry.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (brpo == 5) {
                    JOptionPane.showMessageDialog(panel, "Kraj igre!");
                } else {
                    if (br == 4) {
                        System.out.println("potvrda");
                        Provjera();
                        j++;
                        pokusaj.removeAll(pokusaj);
                        i = 1;
                        br = 0;
                        brpo++;
                    } else {
                        JOptionPane.showMessageDialog(panel, "Morate popuniti sva polja!");
                    }
                }
            }
        });
    }
    
    private void Click(String znak, String imagePath, char z, int i, int j) {
        System.out.println(znak);
        if (br < 4) {
            pokusaj.add(z);
            panel1.add(new Dugmic(i * 50, j * 70, 50, 60, ".\\pictures\\" + imagePath));
        }
        System.out.println(pokusaj);
    }

    public Board(Thread runner) {
        this.runner = runner;
    }

    //provjera rjesenja, tj. da li se kombinacija koju mi unosimo poklapa sa zadatom kombinacijom
    private void Provjera() {
        int brX = 0; //broj pogodjenih koji se nalaze na tacnom mjestu
        int brO = 0; //broj pogodjenih koji se ne nalaze na tacnom mjestu

        // prolazi se kroz rjesenje
        for (int s = 0; s < rjesenje.size(); s++) {
            // ako se i-to slovo rjesenja nalazi na i-tom mjestu pokusaja, to je X
            if (Objects.equals(rjesenje.get(s), pokusaj.get(s))) {
                brX++;
            } 
            
            
            else {
                // prolazi se kroz pokusaj
                for (int v = 0; v < pokusaj.size(); v++) // pri nailasku na prvu pojavu i-tog slova rjesenja u pokusaju
                {
                    if (Objects.equals(rjesenje.get(s), pokusaj.get(v))) {
                        brO++; // ubroji se O i prelazi na sledece slovo rjesenja
                        break;
                    }
                }
            }
        }
        
        JButton mjesto;
        int k;
        int m = brX + brO;
        for (k = 0; k < m; k++) {
            if (k < brX) {
                System.out.println("na mjestu");
                mjesto = new JButton();
                //ako je znak pogodjen i na tacnom mjestu, iscrta se crveno dugme
                mjesto.setBackground(Color.RED);
                mjesto.setBounds(k * 50, j * 70, 50, 60);
                panel.add(mjesto);
            } else {
                System.out.println("nije na mjestu");
                mjesto = new JButton();
                //ako je znak pogodjen i nije na tacnom mjestu, iscrta se zuto dugme
                mjesto.setBackground(Color.YELLOW);
                mjesto.setBounds(k * 50, j * 70, 50, 60);
                panel.add(mjesto);
            }
        }
        
        //ako je pogodjena trazena kombinacija, otvori nam se kartica sa obavjestenjem
            if (brX == 4) {
            JOptionPane.showMessageDialog(panel1, "Pogodak");
            stopGame(message);
            //startGame();
        }
        
    }

}
