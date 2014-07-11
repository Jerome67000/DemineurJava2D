package demineurjava2d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author jerome
 */
public class Game extends JFrame{
    
    public static Panel     panel = new Panel();
    public static Case      cases[][] = new Case[1000][1000];
    private static int      frameRate = 12;
    public static int       nbCasesX =15;
    public static int       nbCasesY = 15;
    public static int       unite = 32;
    
    private final int       difficulte = 10;
    public static boolean   fini = false;
    public static boolean   gagne = false;
    
    public static MouseListener ml = new MouseListener();
    public static boolean   cursorOnPanel = false;
    public static int       cursorX;
    public static int       cursorY;
   
    public Game (String title) {
        
        this.setTitle(title);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((nbCasesX*unite)+2, (nbCasesY*unite)+24);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel);
        this.addMouseListener(ml);
        
        generate(difficulte);
        gameTrame();
        
        System.out.println("Fenêtre créée");
    }
    
    public static void gameTrame() {

        while (true) {
            
            int buffer = TimerThread.MILLI;
            cursorOnPanel = false;
            
            if (panel.mouseX > 0 && panel.mouseX < nbCasesX*unite &&
                panel.mouseY > 0 && panel.mouseY < nbCasesY*unite) {
                
                cursorOnPanel = true;
                cursorX = panel.mouseX/unite;
                cursorY = panel.mouseY/unite;
                if (ml.clicGauche && !fini) {
                    supprCase(cursorX, cursorY);
                }
                if (ml.clicDroit && !fini) {
                    
                }
            }
                
            if (ml.clicGauche) ml.clicGauche = false;
            if (ml.clicDroit) ml.clicDroit = false;
            
            panel.repaint();
            
            if (TimerThread.MILLI - buffer < frameRate) {
                try {
                    Thread.sleep(frameRate - (TimerThread.MILLI - buffer));
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Génère les informations stockées dans le tableau
     * @param pourcent
     */
    public static void generate(int pourcent) {
        
        // Création des objets Case et placement des bombes
        for (int x=0; x < Game.nbCasesX; x++) {
            for (int y=0; y < Game.nbCasesY; y++) {
                if (Math.random()*100 < pourcent)
                    cases[x][y] = new Case(-1, x, y);
                else
                    cases[x][y] = new Case(0, x, y);
            }
        } 
      
        // Calculs les indices du nombres de bombes adjacentes
        for (int x=0; x < Game.nbCasesX; x++) {
            for (int y=0; y < Game.nbCasesY; y++) {
                int nbMines = 0;
                if (cases[x][y].getVal() != -1) {
                    // Vérification colone Gauche
                    if (x == 0) {
                        if (y == 0) {
                            if (cases[x+1][y].getVal() == -1)   nbMines++;
                            if (cases[x][y+1].getVal() == -1)   nbMines++;
                            if (cases[x+1][y+1].getVal() == -1) nbMines++;
                        }
                        else if (y == Game.nbCasesY-1) {
                            if (cases[x+1][y].getVal() == -1)   nbMines++;
                            if (cases[x][y-1].getVal() == -1)   nbMines++;
                            if (cases[x+1][y-1].getVal() == -1) nbMines++;
                        }
                        else {
                            if (cases[x][y-1].getVal() == -1)   nbMines++;
                            if (cases[x+1][y-1].getVal() == -1)   nbMines++;
                            if (cases[x+1][y].getVal() == -1)   nbMines++;
                            if (cases[x+1][y+1].getVal() == -1)   nbMines++;
                            if (cases[x][y+1].getVal() == -1)   nbMines++;
                        }
                    }
                    // Vérification colonne Droite
                    else if (x == Game.nbCasesX-1) {
                        if (y == 0) {
                            if (cases[x-1][y].getVal() == -1)   nbMines++;
                            if (cases[x-1][y].getVal() == -1)   nbMines++;
                            if (cases[x][y].getVal() == -1) nbMines++;
                        }
                        else if (y == Game.nbCasesY-1) {
                            if (cases[x-1][y].getVal() == -1)   nbMines++;
                            if (cases[x-1][y-1].getVal() == -1)   nbMines++;
                            if (cases[x][y-1].getVal() == -1) nbMines++;
                        }
                        else {
                            if (cases[x][y-1].getVal() == -1)   nbMines++;
                            if (cases[x-1][y-1].getVal() == -1)   nbMines++;
                            if (cases[x-1][y].getVal() == -1)   nbMines++;
                            if (cases[x-1][y+1].getVal() == -1)   nbMines++;
                            if (cases[x][y+1].getVal() == -1)   nbMines++;
                        }
                    }
                    // Vérification colonnes intermédiaires
                    else {
                        if (y == 0) {
                            if (cases[x-1][y].getVal() == -1)   nbMines++;
                            if (cases[x-1][y+1].getVal() == -1)   nbMines++;
                            if (cases[x][y+1].getVal() == -1) nbMines++;
                            if (cases[x+1][y+1].getVal() == -1) nbMines++;
                            if (cases[x+1][y].getVal() == -1) nbMines++;
                        }
                        else if (y == Game.nbCasesY-1) {
                            if (cases[x-1][y].getVal() == -1)   nbMines++;
                            if (cases[x-1][y-1].getVal() == -1)   nbMines++;
                            if (cases[x][y-1].getVal() == -1) nbMines++;
                            if (cases[x+1][y-1].getVal() == -1) nbMines++;
                            if (cases[x+1][y].getVal() == -1) nbMines++;
                        }
                        else {
                            if (cases[x-1][y].getVal() == -1)   nbMines++;
                            if (cases[x-1][y-1].getVal() == -1)   nbMines++;
                            if (cases[x][y-1].getVal() == -1) nbMines++;
                            if (cases[x+1][y-1].getVal() == -1) nbMines++;
                            if (cases[x+1][y].getVal() == -1) nbMines++;
                            if (cases[x+1][y+1].getVal() == -1) nbMines++;
                            if (cases[x-1][y+1].getVal() == -1) nbMines++;
                            if (cases[x][y+1].getVal() == -1) nbMines++;
                        }
                    }
                    cases[x][y].setVal(nbMines);
                }
            }
        }
        
        // TODO : réparrer affichage console
        for (int y=0; y < Game.nbCasesY; y++) {
            for (int x=0; x < Game.nbCasesX; x++) {
                if (x == nbCasesX-1) 
                    System.out.println("");
                else if (cases[x][y].getVal() == -1)
                    System.out.print("|"+cases[x][y].getVal());
                else 
                    System.out.print("| "+cases[x][y].getVal());
            }
        }
        System.out.println("génération terminé");
    }
    
    public static void supprCase(int x, int y) {
        
        
        if (cases[x][y].getVal() == -1) {
            cases[x][y].setVisible(true);
            for (int x1=0; x1 < Game.nbCasesX; x1++) {
                for (int y1=0; y1 < Game.nbCasesY; y1++) {
                    cases[x1][y1].setVisible(true);
                }
            }
            fini = true;
            gagne = false;
            
        }
        else if (cases[x][y].getVal() > 0) {
            cases[x][y].setVisible(true);
        }
        else if (cases[x][y].getVal() == 0) {
            
            ArrayList<Case> alCases = new ArrayList<Case>();
            alCases.add(cases[cursorX][cursorY]);
            cases[x][y].setVisible(true);
            
            int size = 1;
            while (size > 0) {
                
                Iterator<Case> iterator = alCases.iterator();
                while (iterator.hasNext() && size > 0) {
                    
                    Case c = iterator.next();
                    int x1 = c.getX();
                    int y1 = c.getY();
                    
                    System.out.println("case "+c.getX()+" "+c.getY()+" "+c.getVal());
                    
                    if (cases[x1][y1].getY() < 14 && cases[x1][y1+1].getVal() == 0) {
                        if (!cases[x1][y1+1].isVisible())
                            alCases.add(cases[x1][y1+1]);
//                        System.out.println("casea"+cases[x1][y1+1].getX()+" "+cases[x1][y1+1].getY()+" "+cases[x1][y1+1].getVal());
                    }
                    else if (cases[x1][y1].getY() >= 0 && cases[x1][y1-1].getVal() == 0) {
                        if (!cases[x1][y1-1].isVisible())
                            alCases.add(cases[x1][y1-1]);
//                        System.out.println("casea"+cases[x1][y1-1].getX()+" "+cases[x1][y1-1].getY()+" "+cases[x1][y1-1].getVal());
                    }
                    cases[x1][y1].setVisible(true);
                    alCases.remove(cases[x1][y1]);
                    size = alCases.size();
                    System.out.println("fin de boucle, nb arrayliste "+alCases.size());
                }
            }
        }
    }
}
