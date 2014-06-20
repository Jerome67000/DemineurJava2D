package demineurjava2d;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author jerome
 */
public class Frame extends JFrame{
    
    public static Panel panel = new Panel();
    public static int[][] cases = new int[100][100];
    public static int x =10;
    public static int y = 10;
    public static int unite = 20;
    public static int frameRate = 12;
    private int difficulte = 15;
    
    public Frame (String title) {
        
        this.setTitle(title);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(x*unite+2, y*unite+4);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel);
        
        generate(10, 10, difficulte);
        gameTrame();
        
        System.out.println("Fenêtre créée");
       
    }
    
    public static void gameTrame() {
        
        int i = 0;
        
        while (true) {
            
            int buffer = TimerThread.MILLI;
            
            
            if (TimerThread.MILLI - buffer < frameRate)
                try {
                    Thread.sleep(frameRate - (TimerThread.MILLI - buffer));
            } catch (InterruptedException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void generate(int X, int Y, int pourcent) {
        
        x = X;
        y = Y;
        
        for (int x=0; x < Frame.x; x++) {
            for (int y=0; y < Frame.y; y++) {
                if (Math.random()*100 < pourcent)
                    cases[x][y] = -1;
            }
        } 
    }
}
