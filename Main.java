import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.management.*;
import java.math.BigInteger;

public class Main {

    public static void main(String args[]) {
        try {
            Scanner scan = new Scanner(new File("entrada_grande.txt"));

            int numVect = Integer.parseInt(scan.nextLine());

            BigInteger tiempoMedia = BigInteger.valueOf(0);
            BigInteger tM = BigInteger.valueOf(0);
            

            long tiempoInicio=getTime();
	    BigInteger tiempoTotal=BigInteger.valueOf(0);

            for (int i = 0; i < numVect; i++) {
                String vector = scan.nextLine();
                String[] vecArray = vector.split(" ");
                ArrayList<Integer> al = arrayToArrayList(vecArray);
                al.remove(0);
                Algoritmo3 al3 = new Algoritmo3(al);
            	tM = BigInteger.valueOf(0);
            	long tI = System.currentTimeMillis();
                al3.ejecutar();
            	long tF = System.currentTimeMillis();
            	tM=tM.add(BigInteger.valueOf(tF-tI));
            	tiempoTotal=tiempoTotal.add(BigInteger.valueOf(tF-tI));
                System.out.println(al3.getResultados());
            	System.out.println("Tiempo de ejecución del fichero: "+ tM.longValue()/1000.0+" segundos.");
            }
            long tiempoFinal=getTime();

            tiempoMedia=tiempoMedia.add(BigInteger.valueOf(tiempoFinal-tiempoInicio));
            System.out.println("Tiempo de ejecución del fichero: "+ tiempoTotal.longValue()/1000.0+" segundos.");

            scan.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> arrayToArrayList(String[] arr) {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            al.add(Integer.parseInt(arr[i]));
        }
        return al;
    }

    public static long getTime(){
		ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
		return bean.isCurrentThreadCpuTimeSupported( ) ?
		bean.getCurrentThreadCpuTime() : 0L;
	}
}
