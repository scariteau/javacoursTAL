package bases.priseEnMain;

import java.util.Scanner;

public class MaxDeuxNombres {
	public static void main(String[] args) {
		System.out.println("Veuillez saisir le premier nombre :");
		Scanner sc1 = new Scanner(System.in);
		float x = sc1.nextFloat();

		System.out.println("Veuillez saisir le 2 ème nombre :");

		float y = sc1.nextFloat();
		if (x <= y) {
			System.out.println("Le maximum des deux nombres " + x + " et " + y + " est égale à  " + y);
		} else {

			System.out.println("Le maximum des deux nombres " + x + " et " + y + " est égale à  " + x);
		}
		sc1.close();
	}
}