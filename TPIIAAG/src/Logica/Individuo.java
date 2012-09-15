/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

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
    private static final byte[][] Rangos = {
        {15, 0, 21, 16, 0, 22, 28, 0},
        {20, 6, 16, 0, 10, 23, 0, 21},
        {0, 15, 0, 21, 16, 8, 22, 0},
        {14, 0, 0, 21, 11, 6, 19, 26}
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
    public Individuo cruzarseNivelIndividuo(Individuo unIndividuo, byte corte) {
        Individuo nuevoIndividuo = null;
        switch (corte) {
            case 0:
                nuevoIndividuo.setP1(this.getP1());
                nuevoIndividuo.setP2(unIndividuo.getP2());
                nuevoIndividuo.setP3(unIndividuo.getP3());
                nuevoIndividuo.setP4(unIndividuo.getP4());
                break;
            case 1:
                nuevoIndividuo.setP1(this.getP1());
                nuevoIndividuo.setP2(this.getP2());
                nuevoIndividuo.setP3(unIndividuo.getP3());
                nuevoIndividuo.setP4(unIndividuo.getP4());
                break;
            case 2:
                nuevoIndividuo.setP1(this.getP1());
                nuevoIndividuo.setP2(this.getP2());
                nuevoIndividuo.setP3(this.getP3());
                nuevoIndividuo.setP4(unIndividuo.getP4());
        }
        return nuevoIndividuo;
    }

    public Individuo cruzarseNivelProducto(Individuo unIndividuo, Random random) {
        //FUNCIONA
        Individuo nuevoIndividuo = crearUnIndividuo(0, 0, 0, 0);
        for (byte i = 0; i < 4; i++) {
            int posicion = random.nextInt(9);
            posicion = (int) Math.pow(2, posicion);
            posicion += (posicion - 1);
            short auxiliar1 = (short) (this.getP1() & posicion);
            posicion = posicion ^ 1023;
            short auxiliar2 = (short) (getProducto(i) & posicion);
            nuevoIndividuo.setProducto(i, (short) (auxiliar1 | auxiliar2));
        }
        return nuevoIndividuo;
    }

//  Metodo para mutar al individuo
    public void mutarse() {
    }
    /*
     * Metodo que calcula la aptitud del individuo recibe como parametro un
     * arreglo de materiales ingresados por el usuario
     */

    public float evaluarAptitud(int[] matIngs) {

        float nuevaAptitud = 0;
        int[] matMinimos = calcularMaterialesMinimos();
//        int[] matMaximos = calcularMaterialesMaximos();
        int[] diferencia = new int[8];
//        int[] remanenteMateriales = new int[8];

        for (int i = 0; i < diferencia.length; i++) {
            diferencia[i] = matIngs[i] - matMinimos[i];
        }

//        for (int i = 0; i < remanenteMateriales.length; i++) {
//            remanenteMateriales[i] = matIngs[i] - matMaximos[i];
//        }

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

            /*
             * Por ser factibles se le suma la utildidad. Ésta no sera lineal,
             * sino que se elevará al cubo el valor
             */

            nuevaAptitud += Math.pow(calcularUtilidad(), 3);

            /*
             * Aca se trata la puntuacion po utilizacion de los recursos
             */
            if (!eficienteConRecursos(diferencia)) {
                /*
                 * Si no hay remanente de materiales, se los descontara en
                 * puntos a la aptitud.
                 */
                int diferenciaTotal = 0;
                for (int i = 0; i < 4; i++) {
                    if (diferencia[i] < 0) {
                        diferenciaTotal += Math.pow(diferencia[i], 2);
                    }
                }
                nuevaAptitud -= diferenciaTotal;
            } else {
                /*
                 * El individuo que utilice los materiales de manera más
                 * eficiente (que el remanente de materiales sea 0), obtendra un
                 * premio en puntos de aptitud
                 */
                nuevaAptitud += (nuevaAptitud * 0.50);
            }

        } else {
            /*
             * Aca, se castiga severamente al individuo con una aptitud muuuuy
             * baja, debido a que no es factible porque pide mas materiales que
             * los ingresados/existentes.
             */
            int diferenciaTotal = 0;
            for (int i = 0; i < 4; i++) {
                if (diferencia[i] < 0) {
                    diferenciaTotal += Math.pow(diferencia[i], 2);
                }
            }
            nuevaAptitud -= (9999 + diferenciaTotal);
        }

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

    private int[] calcularMaterialesRango() {
        int[] materiales = new int[8];
        for (int i = 0; i < materiales.length; i++) {
            materiales[i] = p1 * Rangos[0][i] + p2 * Rangos[1][i]
                    + p3 * Rangos[2][i] + p4 * Rangos[3][i];
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

    private boolean eficienteConRecursos(int[] diferencia) {
        /*
         * Devuelve verdadero si (diferencia - rango*cantDeProductos)<=0 Si es
         * es menor pone 0 en la diferencia. Los valores de diferencia que
         * queden positivos, seran el remanente del material.
         */
        boolean valor = false;
        int[] rangos = calcularMaterialesRango();

        for (int i = 0; i < rangos.length; i++) {
            diferencia[i] -= rangos[i];
            if ((diferencia[i]) <= 0) {
                valor = true;
                diferencia[i] = 0;
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

    public short getProducto(byte nroProducto) {
        short var;
        switch (nroProducto) {
            case 0:
                var = getP1();
                break;
            case 1:
                var = getP2();
                break;
            case 2:
                var = getP3();
                break;
            default:
                var = getP4();
                break;
        }
        return var;
    }
    /*
     * El metodo setProducto recibe el nro del prducto al que se le va a asignar
     * el valor. Los valores de nro de prducto son: 0 para P1, 1 para P2, 2 para
     * P3 y 3 para P4. En el caso de que se agregue cualquier otro nro el valor
     * no sera seteado.
     */

    public void setProducto(byte nroProducto, short valor) {
        short var;
        switch (nroProducto) {
            case 0:
                setP1(valor);
                break;
            case 1:
                setP2(valor);
                break;
            case 2:
                setP3(valor);
                break;
            case 3:
                setP4(valor);
                break;
        }
    }

    public static void main(String[] args) {
//        Individuo a = Individuo.crearUnIndividuo(10, 2, 3, 3);
//        for (int i = 0; i < 4; i++) {
//            System.out.print("{");
//            for (int j = 0; j < 8; j++) {
//                System.out.print(Individuo.MMaximos[i][j] - Individuo.MMinimos[i][j] + ",");
//            }
//            System.out.println("}");
//        }

        Individuo ind1 = crearUnIndividuo(1111,989,456,778);
        Individuo ind2 = crearUnIndividuo(15,111,450,99);
        Random random = new Random();

//        Individuo nuevoIndividuo = crearUnIndividuo(0, 0, 0, 0);
//        int posicion = 2;//random.nextInt(9);
//        System.out.println("random " + posicion);
//        posicion = (int) Math.pow(2, posicion);
//        posicion += (posicion - 1);
//        short auxilar = (short) (ind1.getP1() & posicion);
//        posicion = posicion ^ 1023;
//        short auxiliar2 = (short) (ind2.getP1() & posicion);
//        auxilar = (short) (auxilar | auxiliar2);
//        nuevoIndividuo.setP1(auxilar);
//        System.out.println(nuevoIndividuo.getP1());
        
        
         Individuo nuevoIndividuo = crearUnIndividuo(0, 0, 0, 0);
        for (byte i = 0; i < 4; i++) {
            int posicion = random.nextInt(9);
            posicion = (int) Math.pow(2, posicion);
            posicion += (posicion - 1);
            short auxiliar1 = (short) (ind1.getProducto(i) & posicion);
            posicion = posicion ^ 1023;
            short auxiliar2 = (short) (ind2.getProducto(i) & posicion);
            nuevoIndividuo.setProducto(i, (short) (auxiliar1 | auxiliar2));
            System.out.println(nuevoIndividuo.getProducto(i));
        }
        
    }
}
