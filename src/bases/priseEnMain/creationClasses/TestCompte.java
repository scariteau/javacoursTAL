package bases.priseEnMain.creationClasses;

public class TestCompte {

	public static void main(String[] args) {

		Compte monCompte = new Compte(5000.0);
		monCompte.deposer(3000);
		monCompte.retirer(2000);
		monCompte.afficher();
	}
}
