package bases.priseEnMain;

import java.util.Scanner;

public class Duplicateur {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner nombre = new Scanner(System.in);
		// affichage d'un message à l'écran demandant à l'utilisateur de taper un nombre
		System.out.println("Veuillez saisir un nombre : ");
		double x = nombre.nextDouble();
		double y = 2 * x;
		System.out.println("le double de : " + x + " est " + y);
		nombre.close();
	}
}
