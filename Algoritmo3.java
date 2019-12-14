import java.util.ArrayList;

public class Algoritmo3 {

    private static ArrayList<Integer> array;

    public static int numComparaciones;
    public static int numAsignaciones;

    public Algoritmo3(ArrayList<Integer> al) {
        array = al;
        numAsignaciones=0;
        numComparaciones=0;
    }

    public String ejecutar() {
        int[] res = recursivo(0,array.size()-1);
        if (res != null) {
            return (""+ganancia(res) + ", " + (res[1]+1) + ", " + (res[3]+1));
        } else {
            return "No hay ganancia posible.";
        }

    }

    public static int ganancia(int[] r) {
        if (r == null)
            return -1;
        return (r[2] - r[0]);
    }

    public static int[] recursivo(int inf, int sup) {
        int[] res = new int[4];
        if (array.size() > 1) {
            res[3] = sup - 1; //instante de venta
            res[2] = array.get(sup - 1);//precio de instante de venta
            res[1] = inf;//instante de compra
            res[0] = array.get(inf);//precio de instante de compra
            numAsignaciones+=4;

            for (int i = sup - 1; i > inf; i--) {
                if (array.get(i) > res[2]) {
                    int tmp=res[2];
                    int mpt =res[3];
                    res[2] = array.get(i);
                    res[3] = i;
                    numAsignaciones+=2;
                    numComparaciones++;
                    if (res[3] < res[1] ) {
                        numComparaciones++;
                        res[2]=tmp;
                        res[3]=mpt;
                        int[] res3 = recursivo(inf,res[1]);
                        return (ganancia(res) >= ganancia(res3)) ? res : res3;
                    }
                }
                if (array.get(i) < res[0]) {
                    res[0] = array.get(i);
                    res[1] = i;
                    numAsignaciones+=2;
                }
            }
        }
        return (res[1] == res[3]) ? null : res;
    }

    public String getResultados(){
        return("Num asignaciones: "+numAsignaciones+", num comparaciones: "+numComparaciones);
    }
}
