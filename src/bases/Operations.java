package bases;

import java.util.Scanner;

public class Operations {

	private static Scanner clavier = new Scanner(System.in);

	protected void swap() {

		System.out.print("Entrez la premiere  valeur : ");
		int a = clavier.nextInt();

		System.out.print("Entrez la deuxieme  valeur : ");
		int b = clavier.nextInt();

		System.out.print("Entrez la troisieme valeur : ");
		int c = clavier.nextInt();

		System.out.println("Les valeurs entrees sont : a = " + a + ", b = " + b + " et c = " + c);

		System.out.println("Permutation: a ==> b, b ==> c, c ==> a");

		/*******************************************
		 * Completez le programme a partir d'ici.
		 *******************************************/

		int swap = a;
		// int swap2 = b;
		a = c;
		c = b;
		b = swap;
		// c = swap2;

		/*******************************************
		 * Ne rien modifier apres cette ligne.
		 *******************************************/

		System.out.println("Les valeurs permutees sont : a = " + a + ", b = " + b + " et c = " + c);

	}

	protected void cluedo() {

		String nom = "==> Le personnage auquel vous pensez est ";

		System.out.print("Pensez a un personnage : Mlle Rose, ");
		System.out.println("le Professeur Violet, le Colonel Moutarde,");
		System.out.println("le Reverend Olive ou Mme Leblanc.\n");

		System.out.print("Est-ce que votre personnage est un homme ? ");
		boolean homme = clavier.nextBoolean();

		if (homme) {

			System.out.print("Votre personnage a-t-il des moustaches ? ");
			System.out.print("(true : oui, false : non) ");
			boolean moustaches = clavier.nextBoolean();
			if (moustaches) {
				nom += "le Colonel Moutarde";

			} else {
				System.out.print("Votre personnage porte-t-il un chapeau ? ");
				boolean chapeau = clavier.nextBoolean();
				if (chapeau) {
					nom += "le Professeur Violet";
				} else {
					nom += "le Reverend Olive";
				}
			}

		} else {
			System.out.print("Votre personnage porte-t-il des lunettes ? ");
			boolean lunettes = clavier.nextBoolean();

			if (!lunettes) {
				nom += "Mlle Rose";

			} else {
				nom += "Mme Leblanc";
			}
		}

		System.out.print(nom);

		System.out.println();
	}

	public static void main(String[] args) {

		Operations operations = new Operations();
		operations.swap();
		operations.cluedo();

	}
}
