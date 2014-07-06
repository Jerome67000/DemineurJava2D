package demineurjava2d;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author jerome
 */
public class Game extends JFrame{
    
    public static Panel     panel = new Panel();
    public static int       cursor[] = new int[2];
    public static boolean   cursorOnPanel = false;
    public static Case      cases[][] = new Case[1000][1000];
    public static int       nbCasesX =15;
    public static int       nbCasesY = 15;
    
    public static boolean   fini = false;
    public static boolean   gagne = false;
    private final int       difficulte = 10;
    public static int       unite = 32;
    private static int      frameRate = 12;
    public static MouseListener ml = new MouseListener();
    
    public Game (String title) {
        
        this.setTitle(title);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((nbCasesX*unite)+2, (nbCasesY*unite)+24);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel);
        this.addMouseListener(ml);
        
        generate(difficulte);
//        gameTrame();
        
        System.out.println("Fenêtre créée");
    }
    
    public static void gameTrame() {

        while (true) {
            
            int buffer = TimerThread.MILLI;
            cursorOnPanel = false;
            
            if (panel.mouseX > 0 && panel.mouseX < nbCasesX*unite &&
                panel.mouseY > 0 && panel.mouseY < nbCasesY*unite) {
                
                cursorOnPanel = true;
                cursor[0] = panel.mouseX/unite;
                cursor[1] = panel.mouseY/unite;
                if (ml.clicGauche && !fini) {
                    supprCase(cursor[0], cursor[1]);
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
      
        // Place les indices du nombres de bombes voisines
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
        
        // TODO: réparrer affichage console
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
    }
    
    public static void supprCase(int x, int y) {
        
        if (cases[x][y].getVal() == -1) {
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
//        else if (cases[x][y].getVal() == 0) {
//            int posCases[][] = new int [1000][2];
//            int posCases2[][] = new int [1000][2];
//            int size = 1;
//            int size2 = 0;
//            posCases[0][0] = x;
//            posCases[0][1] = y;
//            while (size > 0) {
//                System.out.println("size = "+size);
//                for (int s = 0; s < size; s++) {
//                    if (casesHidden[posCases[s][0]][posCases[s][1]]) {
//                        casesHidden[posCases[s][0]][posCases[s][1]] = false;
//                        if (cases[posCases[s][0]][posCases[s][1]] == 0) {
//                            
//                            if (posCases[s][0] > 0) {
//                                posCases2[size2][0] = posCases[s][0]-1;
//                                posCases2[size2][1] = posCases[s][1];
//                                size2++;
//                            }
//                            if (posCases[s][0] < x) {
//                                posCases2[size2][0] = posCases[s][0]+1;
//                                posCases2[size2][1] = posCases[s][1];
//                                size2++;
//                            }
//                            if (posCases[s][1] > 0) {
//                                posCases2[size2][0] = posCases[s][0];
//                                posCases2[size2][1] = posCases[s][1]-1;
//                                size2++;
//                            }
//                            if (posCases[s][1] < y) {
//                                posCases2[size2][0] = posCases[s][0];
//                                posCases2[size2][1] = posCases[s][1]+1;
//                                size2++;
//                            }
//                        }
//                        
//                    }
//                }
//                
//                size = size2;
//                for (int s=0; s < size; s++) {
//                    posCases[s][0] = posCases2[s][0];
//                    posCases[s][1] = posCases2[s][1];
//                    size2--;
//                }
//            }
//        }
    }
}
