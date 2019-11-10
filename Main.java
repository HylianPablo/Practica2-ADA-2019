import java.util.ArrayList;

public class Main{
    public static void main(String args[]){
        ArrayList<Integer> dummy = new ArrayList<>();
        dummy.add(24);
        dummy.add(48);
        dummy.add(10);
        dummy.add(23);
        dummy.add(10);
        dummy.add(41);
        dummy.add(17);
        dummy.add(72);
        dummy.add(33);
        dummy.add(82);
        dummy.add(70);
        dummy.add(69);
        dummy.add(72);
        dummy.add(80);
        dummy.add(74);

    Algoritmo2 al2= new Algoritmo2(dummy);

    al2.createTreeMap();
    ArrayList<int[]> vec = al2.mapToVector();
    int[] res = al2.getMaxMin(vec);

    for(int i=0;i<res.length;i++){
        System.out.println(res[i]);
    }
    }
}