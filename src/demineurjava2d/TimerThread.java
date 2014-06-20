package demineurjava2d;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jerome
 */
public class TimerThread extends Thread {
    
    public static int MILLI = 0;
    
    @Override
    public void run() {
        
        while (true) {
            
            try {
                Thread.sleep(1);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(TimerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            MILLI++;
        }
    }
}
