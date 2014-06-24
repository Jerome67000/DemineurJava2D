package demineurjava2d;

/**
 * Main 
 * @author jerome
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game frame = new Game("Démineur");
        TimerThread timer = new TimerThread();
        timer.start();
        System.out.println("timer créé");
        }
    
}
