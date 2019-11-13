import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Implementacion del primer algoritmo.
 * Para cada instante de tiempo se calcula la ganancia 
 * con respecto de los instantes posteriores y el algoritmo 
 * se queda con la mayor, devolviendo el valor de dicha ganancia 
 * y los instantes de tiempo de compra y venta. Si en ninguno de 
 * los instantes se puede obtener ganancia devolverá 0.
 */
public class Algoritmo1 {

	public static int[][] lectura(String args0){
		try {
			BufferedReader lector = new BufferedReader(new FileReader(args0));
        	int numLineas = Integer.parseInt(lector.readLine());
        	String[] linea;
        	int[][] precios = new int[numLineas][];
        	int[] preciosTemp;
        	for(int i = 0; i < numLineas; i++) {
        		linea = (lector.readLine()).split(" ");
        		preciosTemp = toInt(linea);
        		precios[i] = preciosTemp;
        	}
        	lector.close();

    		return precios;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public static int[] toInt(String[] linea) {

    	int[] precios = new int[Integer.parseInt(linea[0])];
    	for(int i = 0; i < precios.length; i++) {
    		precios[i] = Integer.parseInt(linea[i+1]);
    	}
    	return precios;
	}
	
	public static int[] calcularGanancia(int[] precios, int index) {
		int ganancia = 0;
		int instanteVenta = 0;
		for(int i = index; i < precios.length; i++) {
			int gananciaActual = precios[i]-precios[index];
			if(ganancia < gananciaActual) {
				ganancia = gananciaActual;
				instanteVenta = i;
			}
		}
		return new int[] {ganancia, instanteVenta};
	}
	
	public static void algoritmo(int[] precios, int[] instantes) {
		int ganancia = 0;
		int instanteVenta = 0;
		int gananciaTemp = 0;
		int instanteCompra = 0;
		int[] resultadoT;
		for(int i = 0; i < precios.length; i++) {
			if((i==0)||(precios[i] < precios[i-1])) {
				resultadoT = calcularGanancia(precios, i);
				gananciaTemp = resultadoT[0];
				if (ganancia < gananciaTemp) {
					instanteCompra = i;
					ganancia = gananciaTemp;
					instanteVenta = resultadoT[1];
				}
			}
		}
		instantes[0] = ganancia;
		instantes[1] = instanteCompra;
		instantes[2] = instanteVenta;
	}
	
	public static void imprimir(int[][] instantes) {
		for(int i = 0; i < instantes.length; i++) {
			if(instantes[i][0]!= 0) {
				System.out.println(instantes[i][0] +" "+ instantes[i][1] +" "+ instantes[i][2]);
			}else {
				System.out.println("0");
			}
		}
	}
	
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Número incorrecto de argumentos");
			System.out.println("Se debe especificar la ruta al fichero con los datos");
			return;
		}
		int[][] precios = lectura(args[0]);
		
        int[][] instantes = new int[precios.length][3];
        
        for(int i = 0; i < precios.length; i++)
        	algoritmo(precios[i], instantes[i]);
        
        imprimir(instantes);
	}
}
