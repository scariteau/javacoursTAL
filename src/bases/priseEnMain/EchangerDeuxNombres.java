package bases.priseEnMain;

import java.util.Scanner;

public class EchangerDeuxNombres {
	public static void main(String[] args) {
		double x, y;
		System.out.println("Saisissez la valeur de x :");
		Scanner sc1 = new Scanner(System.in);
		x = sc1.nextDouble();
		System.out.println("Saisissez la valeur de y :");
		y = sc1.nextDouble();
		double z;
		z = x;
		x = y;
		y = z;
		System.out.println("La valeur de x est : " + x);
		System.out.println("La valeur de yest : " + y);
		sc1.close();
	}
}
