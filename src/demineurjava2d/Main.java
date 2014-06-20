package demineurjava2d;

/**
 *
 * @author jerome
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Frame frame = new Frame("Démineur");
        TimerThread timer = new TimerThread();
        timer.start();
        System.out.println("timer créé");
        }
    
}
