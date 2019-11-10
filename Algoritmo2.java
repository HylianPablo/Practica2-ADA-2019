import java.util.ArrayList;
import java.util.TreeMap;

public class Algoritmo2{

    private ArrayList<Integer> keys;
    private TreeMap<Integer,ArrayList<Integer>> treeM;

    public Algoritmo2(ArrayList<Integer> k){
        keys=k;
    }

    //Se crea el mapa (valor,indice) a partir del vector inicial
    public void createTreeMap(){
      TreeMap<Integer,ArrayList<Integer>> map = new TreeMap<>();
      for(int i=0;i<keys.size();i++){
        if(!map.containsKey(keys.get(i))){
          ArrayList<Integer> list= new ArrayList<>();
          list.add(i);
          map.put(keys.get(i),list); 
        }else{
          ArrayList <Integer> l=map.get(keys.get(i));
          l.add(i);
          map.put(keys.get(i),l);
        }
      }
      treeM=map;
    }

    //Se transforma el mapa a vector ordenado por indice y valor
    public ArrayList<int[]> mapToVector(){
      ArrayList<int[]> arr = new ArrayList<>();
      Integer[] keysArr= (Integer[]) treeM.keySet().toArray(new Integer[0]);
      for(int i=0;i<treeM.size();i++){
          for(int j=0;j<treeM.get(keysArr[i]).size();j++){
            int[] value = new int[2];
            value[0]=keysArr[i];
            value[1]=treeM.get(keysArr[i]).get(j);
            arr.add(value);
          }
        }
      return arr;
    }

    //Busca el maximo y minimo
    public int[] getMaxMin(ArrayList<int[]> al){
      int[] arr = new int[4];
      for(int i=0;i<al.size();i++){
       int[] min=al.get(i);
       for(int j=al.size()-1;j>i;j--){
         if(min[1]<al.get(j)[1]){
           arr[0]=min[0];
           arr[1]=min[1]+1; //En este caso el vector no tiene como posicion inicial el tamaño del mismo
           arr[2]=al.get(j)[0];
           arr[3]=al.get(j)[1]+1; //En este caso el vector no tiene como posicion inicial el tamaño del mismo
           return arr;
         }
       } 
      }
      return null;
    }
}