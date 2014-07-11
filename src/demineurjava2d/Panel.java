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
                
                if (Game.cases[x][y].getVal() == -1)
                    g.drawImage(new ImageIcon("/home/jerome/NetBeansProjects/DemineurJava2D/src/demineurjava2d/images/bombe.png").getImage(), x*Game.unite, y*Game.unite, null);
                else if (Game.cases[x][y].getVal() > 0) {
                    if (Game.cases[x][y].getVal() == 1) g.setColor(Color.darkGray);
                    if (Game.cases[x][y].getVal() == 2) g.setColor(Color.blue);
                    if (Game.cases[x][y].getVal() == 3) g.setColor(Color.yellow);
                    if (Game.cases[x][y].getVal() == 4) g.setColor(Color.orange); 
                    if (Game.cases[x][y].getVal() == 5) g.setColor(Color.pink);
                    if (Game.cases[x][y].getVal() == 6) g.setColor(Color.magenta);
                    if (Game.cases[x][y].getVal() == 7) g.setColor(Color.green);
                    if (Game.cases[x][y].getVal() == 8) g.setColor(Color.black);
                    
                    g.setFont(new Font("Capture it", Font.PLAIN, 17));
                    g.drawString(Integer.toString((Game.cases[x][y].getVal())), x*Game.unite+12, (y+1)*Game.unite-9);
                }
                
                // Génère image case non cliqué
                if (Game.cases[x][y].isVisible() == false) {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                    g.drawImage(new ImageIcon("/home/jerome/NetBeansProjects/DemineurJava2D/src/demineurjava2d/images/caseN.png").getImage(), x*Game.unite, y*Game.unite, null);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
                }
                else {
                    
                }
                
                if (Game.fini) {
                    g.setFont(new Font("Capture it", Font.PLAIN, 40));
                    g.setColor(Color.red);
                    g.drawString("BOOOUUUMMM", 150, 200);
                }
            }   
        }
        // Dessine la case actuellement sélectionnée
        if (Game.cursorOnPanel && !Game.fini) {
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(Game.cursorX*Game.unite, Game.cursorY*Game.unite, Game.unite, Game.unite);
        }
    }

    
}
