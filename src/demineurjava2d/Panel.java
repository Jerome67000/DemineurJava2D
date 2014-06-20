package demineurjava2d;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author jerome
 */
public class Panel extends JPanel {

    public Panel() {
        
        System.out.println("Panel créée");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getSize().width, getSize().height);
        
        for (int x=0; x < Frame.x; x++) {
            for (int y=0; y < Frame.y; y++) {
                if (Frame.cases[x][y] == 0) {
                    g.drawImage(new ImageIcon("/home/jerome/NetBeansProjects/DemineurJava2D/src/demineurjava2d/Images/case.png").getImage(), x*Frame.unite, y*Frame.unite, null);
                }
                else if (Frame.cases[x][y] == -1) {
                    g.drawImage(new ImageIcon("/home/jerome/NetBeansProjects/DemineurJava2D/src/demineurjava2d/Images/case.png").getImage(), x*Frame.unite, y*Frame.unite, null);
                    g.drawImage(new ImageIcon("/home/jerome/NetBeansProjects/DemineurJava2D/src/demineurjava2d/Images/-1.png").getImage(), x*Frame.unite, y*Frame.unite, null);
                }
                else if (Frame.cases[x][y] > 0) {
                    g.drawImage(new ImageIcon("/home/jerome/NetBeansProjects/DemineurJava2D/src/demineurjava2d/Images/case.png").getImage(), x*Frame.unite, y*Frame.unite, null);
                    g.setColor(Color.red);
                    g.drawString(Integer.toString((Frame.cases[x][y])), x*Frame.unite, y*Frame.unite);
                }
            }
        }
        
    }

    
}
