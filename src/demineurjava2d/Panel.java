package demineurjava2d;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author jeromex
 */
public class Panel extends JPanel {

    public static int mouseX = 0;
    public static int mouseY = 0;
        
    @Override
    protected void paintComponent(Graphics g) {
        
        mouseX = MouseInfo.getPointerInfo().getLocation().x-this.getLocationOnScreen().x;
        mouseY = MouseInfo.getPointerInfo().getLocation().y-this.getLocationOnScreen().y;

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                          RenderingHints.VALUE_ANTIALIAS_ON);

        // Parcour la grille et génère l'image à afficher
        for (int x=0; x < Game.nbCasesX; x++) {
            for (int y=0; y < Game.nbCasesY; y++) {
                // Génère image case cliqué
                g.drawImage(new ImageIcon("/home/jerome/NetBeansProjects/DemineurJava2D/src/demineurjava2d/images/case.png").getImage(), x*Game.unite, y*Game.unite, null);
                
                if (Game.cases[x][y] == -1)
                    g.drawImage(new ImageIcon("/home/jerome/NetBeansProjects/DemineurJava2D/src/demineurjava2d/images/bombe.png").getImage(), x*Game.unite, y*Game.unite, null);
                else if (Game.cases[x][y] > 0) {
                    if (Game.cases[x][y] == 1) g.setColor(Color.darkGray);
                    if (Game.cases[x][y] == 2) g.setColor(Color.blue);
                    if (Game.cases[x][y] == 3) g.setColor(Color.yellow);
                    if (Game.cases[x][y] == 4) g.setColor(Color.orange); 
                    if (Game.cases[x][y] == 5) g.setColor(Color.pink);
                    if (Game.cases[x][y] == 6) g.setColor(Color.magenta);
                    if (Game.cases[x][y] == 7) g.setColor(Color.green);
                    if (Game.cases[x][y] == 8) g.setColor(Color.black);
                    
                    g.setFont(new Font("Capture it", Font.PLAIN, 17));
                    g.drawString(Integer.toString((Game.cases[x][y])), x*Game.unite+12, (y+1)*Game.unite-9);
                }
                
                // Génère image case non cliqué
                if (Game.casesHidden[x][y] == true) {
//                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                    g.drawImage(new ImageIcon("/home/jerome/NetBeansProjects/DemineurJava2D/src/demineurjava2d/images/caseN.png").getImage(), x*Game.unite, y*Game.unite, null);
//                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
                }
                else {
                    
                }
            }   
        }
        // Dessine la case actuellement sélectionnée
        if (Game.cursorOnPanel) {
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(Game.cursor[0]*Game.unite, Game.cursor[1]*Game.unite, Game.unite, Game.unite);
        }
    }

    
}
