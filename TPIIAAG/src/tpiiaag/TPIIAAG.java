/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tpiiaag;

import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import sun.swing.DefaultLayoutStyle;

/**
 *
 * @author German
 */
public class TPIIAAG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }


        //RUBEN GIL DE MIERDA............................
        JOptionPane.showMessageDialog(null, "Esteban se la come", "MENSAJE MUY IMPORTANTE", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Rubén, no te hagas el vivo... Buscá la forma de eliminar el Branch!", "Más importante todavía", JOptionPane.ERROR_MESSAGE);
    }
}
