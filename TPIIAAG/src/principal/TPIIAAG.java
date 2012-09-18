/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import Logica.Poblacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author German
 */
public class TPIIAAG {

    public static final int CANTIDAD_ITERACIONES = 1000;
    private List<Poblacion> Generaciones = new ArrayList(CANTIDAD_ITERACIONES);
    private final int mIngresados[] = {700, 450, 380, 595, 727, 
        1102, 826, 494};
    private int iteracionActual = 0;

    public void ejecutar(int cantidadIteraciones) {

        Generaciones.add(this.generacionInicial());
        Generaciones.get(0).evaluarAptitud(mIngresados);
        while (!condicionParada(cantidadIteraciones)) {
            Poblacion actual = Generaciones.get(iteracionActual);
            Poblacion seleccionada = actual.seleccionarPoblacion();
            /*
             * BASTANTE CHOTO ESTE METODO DE CRUZA...cREO QUE ESTA MAL, RECIBE
             * DE PARAMETRO LA POBLACION QUE VA A RECIBIR LA CRUZA. VER.
             */
            actual.cruzarPoblacion(seleccionada); 
            seleccionada.mutarPoblacion(iteracionActual);
            seleccionada.evaluarAptitud(mIngresados);
            Generaciones.add(seleccionada);
            iteracionActual++;
        }
        Generaciones.get(CANTIDAD_ITERACIONES-1).ordenarPobladoPorAptitud();
        System.out.println(Generaciones.get(CANTIDAD_ITERACIONES-1).getIndividuo(0).getAptitud());
        System.out.println(Generaciones.get(CANTIDAD_ITERACIONES-1).getIndividuo(0).getP1());
        System.out.println(Generaciones.get(CANTIDAD_ITERACIONES-1).getIndividuo(0).getP2());
        System.out.println(Generaciones.get(CANTIDAD_ITERACIONES-1).getIndividuo(0).getP3());
        System.out.println(Generaciones.get(CANTIDAD_ITERACIONES-1).getIndividuo(0).getP4());
        System.out.println(Generaciones.get(CANTIDAD_ITERACIONES-1).getIndividuo(0).calcularUtilidad());
    }

    private Boolean condicionParada(int cantidadIteraciones) {
        return cantidadIteraciones == iteracionActual;
    }

    public Poblacion generacionInicial() {
        /*
         * VEEEERR EL TEMA DE GENERAR POBLACION INICIAL DONDE TIENE QUE IR. ¿EN
         * POBLACION O EN CONTROLADOR DE POBLACION (ESTA)? DE QUIEN ES LA
         * RESPONSABILIDAD? PORQUE ESTA QUEDANDO FULERO ESTO
         */
        Poblacion nueva = new Poblacion();
        nueva.generarPoblacionIncial();
        return nueva;
    }

    public static void main(String[] args) {
        TPIIAAG principal = new TPIIAAG();
        principal.ejecutar(CANTIDAD_ITERACIONES);
        
    }
}
