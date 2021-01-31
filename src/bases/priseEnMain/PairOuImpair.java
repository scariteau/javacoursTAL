package bases.priseEnMain;

import java.util.Scanner;

public class PairOuImpair {
	public static void main(String[] args) {
		System.out.println("Veuillez saisir un nombre entier :");
		Scanner entier = new Scanner(System.in);
		int n = entier.nextInt();
		int r = n % 2;
		if (r == 0) {
			System.out.println("Le nombre " + n + " que vous venez de taper est pair");
		} else {
			{
				System.out.println("Le nombre  " + n + " que vous venez de taper est impair ");
			}
			entier.close();
		}
	}
}