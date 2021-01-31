package bases.priseEnMain.creationClasses;

public class Rectangle {
	public Double Longueur;
	public Double Largeur;

	public Rectangle(Double L, Double l) {
		this.Longueur = L;
		this.Largeur = l;
	}

	public Double surface() {
		return this.Longueur * this.Largeur;
	}

	public Double périmètre() {
		return 2 * (this.Longueur + this.Largeur);
	}

	public void afficher() {
		System.out.println("La surface du rectangle est : " + this.surface());
		System.out.println("Le périmètre du rectangle est : " + this.périmètre());
	}
}
