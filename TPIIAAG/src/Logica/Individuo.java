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

    public static Individuo crearUnIndividuo(short p1, short p2, short p3, short p4) {
        return new Individuo(p1, p2, p3, p4);
    }
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


    public Individuo cruzarseCon(Individuo unIndividuo) {
        Individuo nuevoIndividuo = null;

        return nuevoIndividuo;
    }

    public void mutarse() {
    }

    public float evaluarAptitud(int m1, int m2, int m3, int m4, int m5, int m6, int m7, int m8) {
       float nuevaAptitud = 0;
       //INICIOCODIGO
       
       
       
       //FINCODIGO
       setAptitud(nuevaAptitud);
       return nuevaAptitud;
    }
}