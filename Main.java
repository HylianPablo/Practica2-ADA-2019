import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        try {
            Scanner scan = new Scanner(new File("entrada.txt"));

            int numVect=Integer.parseInt(scan.nextLine());

            for(int i=0;i<numVect;i++){
                String vector = scan.nextLine();
                String[] vecArray = vector.split(" ");
                ArrayList<Integer> al = arrayToArrayList(vecArray);
                Algoritmo3 al3 = new Algoritmo3(al);
                al3.ejecutar();
            }

            scan.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> arrayToArrayList(String[] arr){
        ArrayList<Integer> al = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            al.add(Integer.parseInt(arr[i]));
        }
        return al;
    }
}