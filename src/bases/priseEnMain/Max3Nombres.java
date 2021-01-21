package bases.priseEnMain;

import java.util.Scanner;

public class Max3Nombres {
	public static double max(double x, double y) {
		if (x <= y) {
			return y;
		} else {
			return x;
		}
	}

	public static void main(String[] args) {
		System.out.println("Saisissez la valeur de x :");
		Scanner sc1 = new Scanner(System.in);
		double x = sc1.nextDouble();

		System.out.println("Saisissez la valeur de y :");
		double y = sc1.nextDouble();

		System.out.println("Saisissez la valeur de z:");
		double z = sc1.nextDouble();

		double m = max(max(x, y), z);

		System.out.println("Le maximum des 3 nombres  x , y et z  est  " + m);
		sc1.close();
	}
}
