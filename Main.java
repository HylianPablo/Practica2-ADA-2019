import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.management.*;

public class Main {

   	public static int[] toInt(String[] linea) {

    	int[] precios = new int[Integer.parseInt(linea[0])];
    	for(int i = 0; i < precios.length; i++) {
    		precios[i] = Integer.parseInt(linea[i+1]);
    	}
    	return precios;
    }
    
    public static int[][] lectura(String ruta) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(ruta));
            int numLineas = Integer.parseInt(lector.readLine());
            String[] linea;
            int[][] precios = new int[numLineas][];
            int[] preciosTemp;
            for (int i = 0; i < numLineas; i++) {
                linea = (lector.readLine()).split(" ");
                preciosTemp = toInt(linea);
                precios[i] = preciosTemp;
            }
            lector.close();

            return precios;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String args[]) {

        int[][] precios = lectura("entrada_grande.txt");
        ArrayList<ArrayList<Integer> > preciosA = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < precios.length; i++){
            preciosA.add(new ArrayList<Integer>());
            for(int j = 0; j < precios[i].length;j++){
                preciosA.get(i).add(precios[i][j]);
            }
        }
        System.out.println("Lectura terminada");

        long timeTot = 0;
        for (int i = 0; i < precios.length; i++) {
            ArrayList<Integer> al = preciosA.get(i);
            al.remove(0);
            Algoritmo3 al3 = new Algoritmo3(al);
            long tI = System.nanoTime();
            String res = al3.ejecutar();
            long tF = System.nanoTime();
            long timeDif = (tF - tI);
            double time = timeDif / (double) 1000000000;
            timeTot += timeDif;
            System.out.println(res);
            System.out.println(al3.getResultados());
            System.out.println("Tiempo de ejecución del vector: " + time + " segundos.");
        }

        System.out.println("Tiempo de ejecución del fichero: " + timeTot/(double)1000000000 + " segundos.");

    }

    public static ArrayList<Integer> arrayToArrayList(String[] arr) {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            al.add(Integer.parseInt(arr[i]));
        }
        return al;
    }

    public static long getTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ? bean.getCurrentThreadCpuTime() : 0L;
    }
}
