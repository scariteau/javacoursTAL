package bases.priseEnMain;

import java.util.Scanner;

public class PairOuImpair {
	public static void main(String[] args) {
//affichage � l'�cran d'un message demandant � l'utilisateur de taper un nombre entier
		System.out.println("Veuillez saisir un nombre entier :");
//r�cup�ration de la saisie clavier � l'aide de la classe Scanner
		Scanner entier = new Scanner(System.in);
		int n = entier.nextInt();
//r�cup�ration du reste de la division euclidienne de n par 2
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