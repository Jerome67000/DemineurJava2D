/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demineurjava2d;

import java.awt.event.MouseEvent;

/**
 *
 * @author jerome
 */
public class MouseListener implements java.awt.event.MouseListener {

    public static boolean clicGauche = false;
    public static boolean clicDroit = false;
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        if (e.getButton() == 1) clicGauche = true;
        if (e.getButton() == 3) clicDroit = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
