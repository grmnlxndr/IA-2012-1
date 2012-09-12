/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author German
 */
public class Poblacion {
    
    public static final short CANTIDAD_POBLACION = 1000;
    
    private ArrayList<Individuo> poblado = new ArrayList(CANTIDAD_POBLACION);
    private Random random = new Random();
    private float aptitudPromedio = 0;
    
    public Individuo nuevoIndividuo(short p1, short p2, short p3, short p4){
        Individuo nuevo = Individuo.crearUnIndividuo(p1, p2, p3, p4);
        poblado.add(nuevo);
        return nuevo;
    }
    
    public void generarPoblacionIncial(){
        
        for (int i = 0; i < CANTIDAD_POBLACION; i++) {
            nuevoIndividuo((short) random.nextInt(1024), (short)  random.nextInt(1024),
                           (short)  random.nextInt(1024),(short)  random.nextInt(1024));
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
    
    public float evaluarAptitud(int m1, int m2, int m3, int m4, int m5, int m6, int m7, int m8) {

        float aptitudPoblacion = 0;

        for (int i = 0; i < Poblacion.CANTIDAD_POBLACION; i++) {
            aptitudPoblacion += poblado.get(i).evaluarAptitud(m1, m2, m3, m4, m5, m6, m7, m8);
        }

        //calculo el promedio de al aptitud de la poblacion
        aptitudPoblacion = aptitudPoblacion / Poblacion.CANTIDAD_POBLACION;

        return aptitudPoblacion;
    }
    
    
}
