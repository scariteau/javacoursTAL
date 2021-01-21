package bases.priseEnMain;

import java.util.Scanner;

public class Factorielle {
	public static void main(String[] args) {
		System.out.println("Saisissez la valeur de n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int j = 1;
		for (int i = 1; i <= n; i++) {
			j = j * i;
		}
		System.out.println("Factorielle de " + n + " est : " + j);
		sc.close();
	}
}