package bases.priseEnMain;

import java.util.Scanner;

public class sommeDesNpremiersEntiers {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Saisissez la valeur de N");
		int N = scanner.nextInt();
		int j = 0;
		for (int i = 1; i <= N; i++) {
			j = j + i;
		}
		System.out.println("La somme des " + N + " premiers nombres est  : " + j);
		scanner.close();
	}
}
