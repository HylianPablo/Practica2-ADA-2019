import java.util.ArrayList;

public class Algoritmo3 {

    private ArrayList<Integer> array;

    public Algoritmo3(ArrayList<Integer> al) {
        array = al;
        // array.remove(0);
    }

    public void ejecutar() {
        int[] res = recursivo(array);
        if (res != null) {
            System.out.println(ganancia(res) + ", " + (res[1]) + ", " + (res[3]));
        } else {
            System.out.println("No hay ganancia posible.");
        }

    }

    public static int ganancia(int[] r) {
        if (r == null)
            return -1;
        return (r[2] - r[0]);
    }

    public static int[] recursivo(ArrayList<Integer> al) {
        //int backup = al.size() - 1;
        int[] res = new int[4];
        if (al.size() > 1) {
            res[3] = al.size() - 1;
            res[2] = al.get(al.size() - 1);
            res[1] = 0;
            res[0] = al.get(0);

            for (int i = al.size() - 1; i > 0; i--) {
                if (al.get(i) > res[2]) {
                    //backup = res[3];
                    res[2] = al.get(i);
                    res[3] = i;
                    if (res[3] < res[1] /* && backup >= res[1] */) {
                        ArrayList<Integer> al2 = new ArrayList<Integer>(al.subList(res[1], al.size()));
                        ArrayList<Integer> al3 = new ArrayList<Integer>(al.subList(0, res[1]));
                        int[] res2 = recursivo(al2);
                        int[] res3 = recursivo(al3);
                        // if (res2 != null && res3 != null) {
                        if (res2 != null) {
                            res2[1] = res2[1] + res[1] + 0;
                            res2[3] = res2[3] + res[1] + 0;
                        }
                        return (ganancia(res2) >= ganancia(res3)) ? res2 : res3;
                        // }
                    }
                }
                if (al.get(i) < res[0]) {
                    res[0] = al.get(i);
                    res[1] = i;
                }
                // if (res[1] == res[3]) {
                // return null;
                // }
            }
        }
        return (res[1] == res[3]) ? null : res;
    }
}