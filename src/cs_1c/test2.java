package cs_1c;

public class test2 {
	public static void main(String[] args){
	int x = 10;
	double y = 20.28;
	String chicken = "chicken";
	
	displayInAsterisks(x);
	displayInAsterisks(y);
	displayInAsterisks(chicken);
		
	}
	public static <E> void displayInAsterisks(E x){
		System.out.println("***" + x + "***");
	}
}
