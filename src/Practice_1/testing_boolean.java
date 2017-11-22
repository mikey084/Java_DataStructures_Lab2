package Practice_1;

public class testing_boolean {
	
	public static void main(String[] args){
		int[] array = {1,2,3,4};
		System.out.println(containme(2, array));
	}
	public static boolean containme(int x, int[] array1){
		
		return (array1[x] != x);
	}
}
