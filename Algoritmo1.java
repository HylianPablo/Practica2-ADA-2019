import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Implementacion del primer algoritmo.
 * 
 * Para cada instante de tiempo se calcula la ganancia 
 * con respecto de los instantes posteriores y el algoritmo 
 * se queda con la mayor, devolviendo el valor de dicha ganancia 
 * y los instantes de tiempo de compra y venta. Si en ninguno de 
 * los instantes se puede obtener ganancia devolverá 0.
 */
public class Algoritmo1 {

	/*
	 * Ejecuta el algoritmo e imprime sus resultados por pantalla
	 * a partir del fichero de entrada especificado
	 * 
	 * args[0] ruta al fichero de entrada
	 */
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Número incorrecto de argumentos");
			System.out.println("Se debe especificar la ruta al fichero con los datos");
			return;
		}
		int[][] precios = lectura(args[0]);
		
        int[][] instantes = new int[precios.length][3];
        
        for(int i = 0; i < precios.length; i++) {
        	algoritmo(precios[i], instantes[i]);
        	imprimir(instantes[i]);
        } 
	}
	
	/**
	 * Lee el fichero con los datos de entrada
	 * 
	 * @param ruta la ruta al fichero
	 * @return una matriz de int con los datos; null si 
	 * 		   hay algun error de lectura
	 */
	public static int[][] lectura(String ruta){
		try {
			BufferedReader lector = new BufferedReader(new FileReader(ruta));
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
	
	/**
	 * 	Devuelve un array de int equivalente al 
	 * array de string especificado
	 * 
	 * @param linea el array especificado
	 * @return un int[] con el array
	 */
	public static int[] toInt(String[] linea) {

    	int[] precios = new int[Integer.parseInt(linea[0])];
    	for(int i = 0; i < precios.length; i++) {
    		precios[i] = Integer.parseInt(linea[i+1]);
    	}
    	return precios;
	}
	
	/**
	 * Calcula la ganancia con respecto del instante de compra
	 * especificado con respecto de todos los instantes posteriores
	 * 
	 * @param precios la lista de precios
	 * @param instanteCompra el instante especificado
	 * @return un int[] con la ganancia y el instante de venta
	 */
	public static int[] calcularGanancia(int[] precios, int instanteCompra) {
		int ganancia = 0;
		int instanteVenta = 0;
		for(int i = instanteCompra; i < precios.length; i++) {
			int gananciaActual = precios[i]-precios[instanteCompra];
			if(ganancia < gananciaActual) {
				ganancia = gananciaActual;
				instanteVenta = i;
			}
		}
		return new int[] {ganancia, instanteVenta};
	}
	
	/**
	 * Implementacion del algoritmo
	 * 
	 * @param precios los precios de entrada
	 * @param instantes
	 */
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
	
	/**
	 * Imprime la salida del algoritmo con el formato
	 * especificado en la practica
	 * 
	 * @param instantes[0] ganacia
	 * 		  instantes[1] instante de compra
	 * 		  instantes[2] instante de venta
	 */
	public static void imprimir(int[] instantes) {
		if(instantes[0]!= 0) {
			System.out.println(instantes[0] +" "+ instantes[1] +" "+ instantes[2]);
		}else {
			System.out.println("0");
		}
	}
}