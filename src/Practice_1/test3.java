package Practice_1;

import java.text.DecimalFormat;
import java.util.*;

public class test3
{
   public static void main (String[] args)
   {
	   System.out.println(gcd(252,198));

   }
   
   
   public static int gcd(int a , int b){
	   int x = a;
	   int y = b;
	   int r;
	   while( y != 0){
		   r = x % y;
		   x = y;
		   y = r;
	   }
	   return x;
	   
   }
}