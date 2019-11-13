import java.util.ArrayList;

public class Algoritmo3 {

    public static void main(String args[]) {

        ArrayList<Integer> dummy = new ArrayList<>();
        dummy.add(20);
        dummy.add(19);
        dummy.add(18);
        dummy.add(17);
        dummy.add(16);
        dummy.add(15);
        dummy.add(14);
        dummy.add(13);
        dummy.add(12);
        dummy.add(11);
        dummy.add(10);
        dummy.add(9);
        dummy.add(8);
        dummy.add(7);
        dummy.add(21);

        // En este caso da 13 porque hay dos 79s y se toma el primero. REVISAR

        ArrayList<Integer> al = new ArrayList<>();
        int[] res = recursivo(dummy);
        if (res != null) {
            System.out.println(ganancia(res) + ", " + (res[1]) + ", " + (res[3]));
        } else {
            System.out.println("No hay ganancia posible.");
        }
    }

    public static int ganancia(int[] r) {
        return (r[2] - r[0]);
    }

    public static int[] recursivo(ArrayList<Integer> al) {
        int backup = al.size() - 1;
        int[] res = new int[4];
        if (res != null && al!=null && !al.isEmpty()) {
            res[3] = al.size() - 1;
            res[2] = al.get(al.size() - 1);
            res[1] = 0;
            res[0] = al.get(0);

            for (int i = al.size() - 1; i > 0; i--) {
                if (al.get(i) >= res[2]) {
                    backup = res[3];
                    res[2] = al.get(i);
                    res[3] = i;
                    if (i < res[1]) {
                        ArrayList<Integer> al2 = new ArrayList<Integer>(al.subList(res[1], backup + 1));
                        ArrayList<Integer> al3 = new ArrayList<Integer>(al.subList(0, res[1]));
                        int[] res2 = recursivo(al2);
                        int[] res3 = recursivo(al3);
                        if (res2 != null) {
                            res2[1] = res2[1] + i + 1;
                            res2[3] = res2[3] + i + 1;
                            return (ganancia(res2) >= ganancia(res3)) ? res2 : res3;
                        }
                    }
                }
                if (al.get(i) <= res[0]) {
                    res[0] = al.get(i);
                    res[1] = i;
                }
                if(res[1]==res[3]){
                    return null;
                }
            }
        }
        return (res[1] == res[3]) ? null : res;
    }
}