// FHstack main
// CS 1C, Foothill College, Michael Loceff, creator

// import statement needed to gain access to the entire class
import cs_1c.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.lang.Exception;

public class Foothill{
	private static void test(SparseMat<Double> m, int[] r, int[] c, double[] v){
		
		for (int i = 0; i < r.length; i++){
			if (!m.set(r[i], c[i], v[i])){
				System.out.println("Error setting at " + "(" + r[i] + ", "
				+ c[i] + ", " + v[i] + ")");
			}
			if (m.set(r[i], c[i], v[i])){
				System.out.println("Success setting at " + "(" + r[i] + ", "
				+ c[i] + ", " + v[i] + ")");
			}
		}
		
	}
	final static int MAT_SIZE = 100000;
	// -------  main --------------
	public static void main(String[] args) throws Exception
	{
		// 100000 x 100000 filled with 0
		int k;
		SparseMat<Double> mat 
		= new SparseMat<Double>(MAT_SIZE, MAT_SIZE, 0.); 

		// test mutators
		for (k = 0; k < 10; k++)
		{
			mat.set(k, k, k*1.);
			mat.set(4, k, k*10.);
			mat.set(k, 4, -k*10.);
		}
		mat.showSubSquare(0, 12);
		System.out.println();

		SparseMat<Double> mat2 = (SparseMat<Double>)mat.clone();

		for (k = 0; k < 10; k++)
		{
			mat.set(k, k, 1.);
			mat.set(4, k, 10.);
			mat.set(k, 4, -10.);
		}

		mat.showSubSquare(0, 12);
		System.out.println();
		mat2.showSubSquare(0, 12);
		
		SparseMat<Double>mat3 = (SparseMat<Double>)mat.clone();
		mat3.showSubSquare(0, 12);
		System.out.println();
		
		System.out.println("Successful get Method at (2,7) " + mat3.get(2, 7));
		System.out.println("Successful get Method at (2,6) " + mat3.get(2, 6));
		System.out.println("Successful get Method at (2,5) " + mat3.get(2, 5));
		
		int[] testRow = {-1,2,3};
		int[] testCol = {1,100000000,3};
		double[] testVal = {1.0,2.0,69.0};
		
		test(mat3, testRow, testCol, testVal);
		System.out.println();
		mat3.showSubSquare(0, 12);
		System.out.println();
		
		try {
			mat3.get(-1, 100);
		}
		catch(Exception e){
			System.out.println("Get at (-1, 100) " + e);
		}
		try {
			mat3.get(100000000, 10);
		}
		catch(Exception e){
			System.out.println("Get at (100000000, 10) " + e);
		}	
	}
}
class SparseMat<E> implements Cloneable{
	private FHarrayList<FHlinkedList<MatNode>> data;
	private int numRows;
	private int numCols;
	private E dval;
	int k;
	public Object clone() throws CloneNotSupportedException {
		return (SparseMat)super.clone();
	}
	public SparseMat(int rows, int numCols, E defaultVal){
		this.numRows = rows;
		this.numCols = numCols;
		this.dval = defaultVal;

		allocateEmptyMatrix();
	}
	private void allocateEmptyMatrix(){
		data = new FHarrayList<>(numRows);
		for (k = 0; k < this.numRows; k++){
			this.data.add(new FHlinkedList<>());  
		}
	}
	public E get(int r, int c){
		if(r < 0 || r > numRows || c < 0 || c > numCols){
			throw new IndexOutOfBoundsException();
		}
		MatNode node = getNode(r, c);
		if (node == null){
			return dval;
		}
		return node.data;
	}
	//includes bounds check for compatible input
	public boolean set(int r, int c, E x){
		if (r < 0 || r > numRows || c < 0 || c > numCols){  
			return false;
		}
		MatNode node = getNode(r, c);
		FHlinkedList<MatNode> row = this.data.get(r);

		if (node == null && !x.equals(dval)){  

			row.add(new MatNode(c, x));			
		}
		else{
			if( x.equals(dval)){
				row.remove(node);
			}
			else{
				node.data = x;
			}
		}
		return true;
	}
	private MatNode getNode(int r, int c){
		FHlinkedList<MatNode> row = this.data.get(r);
		for (MatNode node: row){
			if(node.col ==  c){
				return node;
			}
		}
		return null;
	}
	public void clear(){
		allocateEmptyMatrix();
	}
	public void showSubSquare(int start, int size){
		StringJoiner sj = new StringJoiner("\n");
		int end = start + size;
		for (int i = start; i < end; i++){
			sj.add(stringifyRow(data.get(i), start, end));
		}
		System.out.println(sj.toString());
	}
	private String stringifyRow(FHlinkedList<MatNode> row, int start, int end){
		StringJoiner sj = new StringJoiner(" "); 
		E[] arrayCols = (E[]) new Object[numCols];
		for (MatNode node: row){
			arrayCols[node.col] = node.data;
		}
		for (int i = start; i < end; i++){
			if (arrayCols[i] == null){
				sj.add(dval.toString());
			}
			else{
				sj.add(arrayCols[i].toString());
			}
		}
		return sj.toString();
	}
	//MatNode class
	protected class MatNode implements Cloneable{
		public int col;
		public E data;

		// we need a default constructor for lists
		MatNode()
		{
			col = 0;
			data = null;
		}

		MatNode(int cl, E dt)
		{
			col = cl;
			data = dt;
		}

		public Object clone() throws CloneNotSupportedException
		{
			// shallow copy
			MatNode newObject = (MatNode)super.clone();
			return (Object) newObject;
		}
	};
}