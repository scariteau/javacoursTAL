package bases.priseEnMain.creationClasses;

public class Compte {
	Double solde;

	public Compte(Double s) {
		this.solde = s;
	}

	public void deposer(double d) {
		this.solde += d;
	}

	public void retirer(double r) {
		this.solde -= r;
	}

	public void afficher() {
		System.out.println("Votre solde est  " + this.solde + " Euro " + " sauf erreur ou omission");
	}
}
