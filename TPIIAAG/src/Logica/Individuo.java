/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author German
 */
public class Individuo {

    //  Variables estatcias; Nombres de las propiedades
    public static final String PROP_APTITUD = "aptitud";
    public static final String PROP_P1 = "p1";
    public static final String PROP_P2 = "p2";
    public static final String PROP_P3 = "p3";
    public static final String PROP_P4 = "p4";
    private static final byte[][] MMinimos = {
        {80, 0, 44, 72, 0, 55, 22, 0},
        {15, 49, 12, 0, 50, 21, 0, 70},
        {0, 84, 0, 34, 62, 62, 43, 0},
        {41, 0, 0, 74, 24, 82, 55, 52}
    };
    private static final byte[][] MMaximos = {
        {95, 0, 65, 88, 0, 77, 50, 0},
        {35, 55, 28, 0, 60, 44, 0, 91},
        {0, 99, 0, 55, 78, 70, 65, 0},
        {55, 0, 0, 95, 35, 88, 74, 78}
    };
    private static final byte[] Utilidad = {90, 115, 120, 100};
//  Variables privadas
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private float aptitud;
    private short p1;
    private short p2;
    private short p3;
    private short p4;

//  Constructor
    private Individuo(short p1, short p2, short p3, short p4) {
        setAptitud(0);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }
    
    public static Individuo crearUnIndividuo(int p1, int p2, int p3, int p4) {
        return new Individuo((short) p1, (short) p2, (short) p3, (short) p4);
    }

//  Agregar un escuchador para las propiedades de esta clase
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

//  Quitar un escuchador
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    
    public PropertyChangeSupport getPcs() {
        return pcs;
    }

//  Getters
    public short getP1() {
        return p1;
    }
    
    public short getP2() {
        return p2;
    }
    
    public short getP3() {
        return p3;
    }
    
    public short getP4() {
        return p4;
    }
    
    public float getAptitud() {
        return aptitud;
    }

//    setters
    public void setP1(short p1) {
        short old = this.p1;
        this.p1 = p1;
        pcs.firePropertyChange(PROP_P1, old, p1);
    }
    
    public void setP2(short p2) {
        short old = this.p2;
        this.p2 = p2;
        pcs.firePropertyChange(PROP_P2, old, p2);
    }
    
    public void setP3(short p3) {
        short old = this.p3;
        this.p3 = p3;
        pcs.firePropertyChange(PROP_P3, old, p3);
    }
    
    public void setP4(short p4) {
        short old = this.p4;
        this.p4 = p4;
        pcs.firePropertyChange(PROP_P4, old, p4);
    }
    
    private void setAptitud(float aptitud) {
        float oldAptitud = this.aptitud;
        this.aptitud = aptitud;
        pcs.firePropertyChange(PROP_APTITUD, oldAptitud, aptitud);
    }

    //Metodo de Cruza de dos individuos
    public Individuo cruzarseCon(Individuo unIndividuo) {
        Individuo nuevoIndividuo = null;
        
        return nuevoIndividuo;
    }
//  Metodo para mutar al individuo

    public void mutarse() {
    }
    /*
     * Metodo que calcula la aptitud del individuo
     * recibe como parametro un arreglo de materiales ingresados por el usuario
     */
    
    public float evaluarAptitud(int[] matIngs) {
        float nuevaAptitud = 0;
        int[] matMinimos = calcularMaterialesMinimos();
//        int[] matMaximos = calcularMaterialesMaximos();
        int[] diferencia = new int[8];
        
        for (int i = 0; i < diferencia.length; i++) {
            diferencia[i] = matIngs[i] - matMinimos[i];
        }
        /*
         * Aca se va a preguntar por la factibilidad del individuo, es decir, si
         * los materiales que solicita el individuo estan dentro de los ingre-
         * -sados por el usario.
         */

        if (factibilidad(diferencia)) {
            /*
             * En es este caso el individuo es factible, hay que ver la utilidad
             * y los materiales remanentes que deja.
             */
            //Por ser factibles se le suma la utildidad
            nuevaAptitud += calcularUtilidad();
            
        } else {
            /*
             * Aca, se castiga severamente al individuo con una aptitud muuuuy
             * baja, debido a que no es factible porque pide mas materiales que
             * los ingresados/existentes.
             */
            nuevaAptitud -= (9999 + calcularUtilidad());
        }

        //FINCODIGO
        setAptitud(nuevaAptitud);
        return nuevaAptitud;
    }
    
    private int[] calcularMaterialesMinimos() {
        int[] materiales = new int[8];
        for (int i = 0; i < materiales.length; i++) {
            materiales[i] = p1 * MMinimos[0][i] + p2 * MMinimos[1][i]
                    + p3 * MMinimos[2][i] + p4 * MMinimos[3][i];
        }
        return materiales;
    }
    
    private int[] calcularMaterialesMaximos() {
        int[] materiales = new int[8];
        for (int i = 0; i < materiales.length; i++) {
            materiales[i] = p1 * MMaximos[0][i] + p2 * MMaximos[1][i]
                    + p3 * MMaximos[2][i] + p4 * MMaximos[3][i];
        }
        return materiales;
    }
    
    private boolean factibilidad(int[] diferencia) {
        boolean valor = true;
        for (int i = 0; i < diferencia.length; i++) {
            if (diferencia[i] < 0) {
                valor = false;
            }
        }
        return valor;
    }
    
    private float calcularUtilidad() {
        float utilidad;
        utilidad = Utilidad[0] * p1 + Utilidad[1] * p2
                + Utilidad[2] * p3 + Utilidad[3] * p4;
        return utilidad;
    }
    //    public static void main(String[] args) {
//        Individuo a = Individuo.crearUnIndividuo(10, 2, 3, 3);
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 8; j++) {
//                System.out.print(Individuo.MMinimos[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
}
