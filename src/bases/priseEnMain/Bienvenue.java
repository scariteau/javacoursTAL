package bases.priseEnMain;

/*Programme java qui demande à l'utilisateur de saisir son nom 
et de lui afficher son nom avec le message de bienvenue
*/
import java.util.Scanner;

public class Bienvenue {

	public static void main(String[] args) {
		Scanner nom = new Scanner(System.in);
		System.out.println("Veuillez saisir votre nom : ");
		String nm = nom.nextLine();
		System.out.println("Bienvenue : " + nm);
		nom.close();
	}
}
