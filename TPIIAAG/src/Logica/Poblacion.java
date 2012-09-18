/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;
import principal.TPIIAAG;

/**
 *
 * @author German
 */
public class Poblacion {

    public static final short CANTIDAD_POBLACION = 1000;
    public static final byte CANTIDAD_SELECCION_ELITISTA = 4;
    public static final float PMMax = 0.4f;
    public static final float PMMin = 0f;
    public static final String PROP_APTITUDPROMEDIO = "aptitudPromedio";
    public static final String PROP_PMUTACION = "pMutacion";
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private ArrayList<Individuo> poblado = new ArrayList(CANTIDAD_POBLACION);
    private Random random = new Random();
    private float aptitudPromedio = 0f;
    private float pMutacion = PMMax;

    public Poblacion() {
    }

    public Individuo nuevoIndividuo(short p1, short p2, short p3, short p4) {
        Individuo nuevo = Individuo.crearUnIndividuo(p1, p2, p3, p4);
        poblado.add(nuevo);
        return nuevo;
    }

    public void nuevoIndividuo(Individuo nuevo) {
        poblado.add(nuevo);
    }

    public Individuo getIndividuo(int index) {
        return poblado.get(index);
    }

    public void generarPoblacionIncial() {

        for (int i = 0; i < CANTIDAD_POBLACION; i++) {
            nuevoIndividuo((short) random.nextInt(1024), (short) random.nextInt(1024),
                    (short) random.nextInt(1024), (short) random.nextInt(1024));
        }
    }

    public void ordenarPobladoPorAptitud() {

        ArrayList<Individuo> auxiliar = new ArrayList(CANTIDAD_POBLACION);
        ArrayList<Individuo> borrador = poblado;
        float maxF;
        Individuo elegido;

        for (int i = 0; i < CANTIDAD_POBLACION; i++) {
            elegido = null;
            maxF = -2000000000;
            for (Individuo individuo : borrador) {

                if (individuo.getAptitud() >= maxF) {
                    maxF = individuo.getAptitud();
                    elegido = individuo;
                }
            }
            auxiliar.add(elegido);
            borrador.remove(elegido);
        }
        poblado = auxiliar;
    }

    public float evaluarAptitud(int[] matIngresados) {

        float aptitudPoblacion = 0f;

        for (int i = 0; i < Poblacion.CANTIDAD_POBLACION; i++) {
            aptitudPoblacion += poblado.get(i).evaluarAptitud(matIngresados);
        }

        //calculo el promedio de al aptitud de la poblacion
        aptitudPoblacion = aptitudPoblacion / Poblacion.CANTIDAD_POBLACION;

        return aptitudPoblacion;
    }

    public Poblacion seleccionarPoblacion() {
        Poblacion nuevaPoblacion = new Poblacion();
        ordenarPobladoPorAptitud();
        for (int i = 0; i < CANTIDAD_SELECCION_ELITISTA; i++) {
            nuevaPoblacion.nuevoIndividuo(poblado.get(i));
        }
        return nuevaPoblacion;
    }

    public void cruzarPoblacion(Poblacion nueva) {

        for (int i = (CANTIDAD_SELECCION_ELITISTA-1); i < (CANTIDAD_POBLACION - CANTIDAD_SELECCION_ELITISTA -1); i = i + 2) {
            boolean rango = random.nextBoolean();
            if (rango) {
                nueva.nuevoIndividuo(this.getIndividuo(i).cruzarseNivelIndividuo(this.getIndividuo(i + 1), random));
                nueva.nuevoIndividuo(this.getIndividuo(i + 1).cruzarseNivelIndividuo(this.getIndividuo(i), random));
            } else {
                int posicion = random.nextInt(9);//va 9 porque son 9 posiciones de corte para 10 bits
                nueva.nuevoIndividuo(this.getIndividuo(i).cruzarseNivelProducto(this.getIndividuo(i + 1), posicion));
                nueva.nuevoIndividuo(this.getIndividuo(i + 1).cruzarseNivelProducto(this.getIndividuo(i), posicion));
            };
        }
    }

    public float getAptitudPromedio() {
        return aptitudPromedio;
    }

    public void setAptitudPromedio(float aptitudPromedio) {
        float valorViejo = this.aptitudPromedio;
        this.aptitudPromedio = aptitudPromedio;
        pcs.firePropertyChange(PROP_APTITUDPROMEDIO, valorViejo, aptitudPromedio);
    }

    public float getpMutacion() {
        return pMutacion;
    }

    public void setpMutacion(float pMutacion) {
        float valorViejo = this.pMutacion;
        this.pMutacion = pMutacion;
        pcs.firePropertyChange(PROP_APTITUDPROMEDIO, valorViejo, pMutacion);
    }

    public void mutarPoblacion(int nroIteracion) {
        float relacion = nroIteracion / (TPIIAAG.CANTIDAD_ITERACIONES * 10);
        if (pMutacion > PMMin) {
            pMutacion -= relacion;
        } else {
            pMutacion = PMMin;
        }
        for (Individuo individuo : poblado) {
            float nroAleatorio = random.nextFloat();
            if (nroAleatorio <= pMutacion) {
                individuo.mutarse();
            }
        }
    }

    //  Agregar un escuchador para las propiedades de esta clase
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

//  Quitar un escuchador
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}
