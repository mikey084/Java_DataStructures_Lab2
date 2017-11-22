package Practice_1;
import java.util.*;



public class Array_list_analyis {
	public static void main(String[] args){
		int k, avg = 0;
		long starttime, stoptime, totaltime;
		final int arraySize = 200000;
		starttime = System.nanoTime();
		ArrayList<Integer> listOfInts = new ArrayList<Integer>(arraySize);
		for (k = 0; k < arraySize; k++){
			listOfInts.add((int)(Math.random()*100));
		}
		for (k = 0; k < arraySize; k++){
			avg += listOfInts.get(k);
			avg /= arraySize;
		}
		stoptime = System.nanoTime();
		totaltime = stoptime - starttime;
		System.out.println("avg: + " + avg);
		System.out.println("\nElapsed time for Time for ArrayList add/get " + totaltime);		
	}
}
