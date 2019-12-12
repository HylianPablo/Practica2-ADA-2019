import java.util.ArrayList;

public class Algoritmo3 {

    private static ArrayList<Integer> array;

    public static int numComparaciones;
    public static int numAsignaciones;

    /**
     * Segundo algoritmo implementado, basado en las técnicas de recursividad y programación dinámica. Se explica en profundidad en la memoria de trabajo.
     * @param al Vector de enteros del que se necesita obtener los valores óptimos.
     */
    public Algoritmo3(ArrayList<Integer> al) {
        array = al;
        numAsignaciones=0;
        numComparaciones=0;
    }

    /**
     * Ejecuta el algoritmo y calcula lña ganancia total.
     * @return Cadena de caracteres que representa la ganancia total y las posiciones del vector óptimas para la venta y la compra.
     */
    public String ejecutar() {
        int[] res = recursivo(0,array.size()-1);
        if (res != null) {
            return (""+ganancia(res) + ", " + (res[1]+1) + ", " + (res[3]+1));
        } else {
            return "No hay ganancia posible.";
        }

    }

    /**
     * Calcula la ganancia de un vector. En el caso de ser un vector nulo, devuelve ganancia '-1'.
     * @param r Vector de cuatro números enteros a comparar. Las posiciones pares representan el precio del instante de compra y las impares el propio instante de compra (posición del vector)
     * @return Número entero que representa la ganancia obtenida.
     */
    public static int ganancia(int[] r) {
        if (r == null)
            return -1;
        return (r[2] - r[0]);
    }

    /**
     * Obtiene los valores óptimos de compra y venta linealmente, hasta que encuentra un caso donde exista un valor de venta antes que uno de compra, donde se crea un subvectoir y se llama recursivamente, conservando la parte ya analizada.
     * @param inf Número entero que representa el índice inferior del vector original.
     * @param sup Número entero que representa el índice superior del vector original.
     * @return Vector de cuatro números enteros. Las posiciones pares representan el precio del instante de compra y las impares el propio instante de compra (posición del vector)
     */
    public static int[] recursivo(int inf, int sup) {
        int[] res = new int[4];
        if (array.size() > 1) {
            res[3] = sup - 1; //instante de venta
            res[2] = array.get(sup - 1);//precio de instante de venta
            res[1] = inf;//instante de compra
            res[0] = array.get(inf);//precio de instante de compra
            numAsignaciones+=4;

            for (int i = sup - 1; i > inf; i--) {
                numComparaciones++;
                if (array.get(i) > res[2]) {
                    int tmp=res[2];
                    int mpt =res[3];
                    res[2] = array.get(i);
                    res[3] = i;
                    numAsignaciones+=2;
                    numComparaciones++;
                    if (res[3] < res[1] ) {
                        res[2]=tmp;
                        res[3]=mpt;
			numAsignaciones+=2;
                        int[] res3 = recursivo(inf,res[1]);
                        return (ganancia(res) >= ganancia(res3)) ? res : res3;
                    }
                }
		numComparaciones++;
                if (array.get(i) < res[0]) {
                    res[0] = array.get(i);
                    res[1] = i;
                    numAsignaciones+=2;
                }
            }
        }
        return (res[1] == res[3]) ? null : res;
    }

    /**
     * Devuelve el número de operaciones elementales de la ejecución.
     * @return Cadena de caracteres que representa el número de operaciones elementales (comparaciones y asignaciones) de la ejecución.
     */
    public String getResultados(){
        return("Num asignaciones: "+numAsignaciones+", num comparaciones: "+numComparaciones);
    }
}
