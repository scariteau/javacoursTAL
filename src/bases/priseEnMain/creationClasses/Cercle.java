package bases.priseEnMain.creationClasses;

public class Cercle {
	public Point centre;
	public Double rayon;

	public Cercle(Point point, Double r) {
		this.centre = point;
		this.rayon = r;
	}

	public Double périmètre() {
		Double pi = Math.PI;
		return 2 * pi * rayon;
	}

	public Double surface() {
		Double pi = Math.PI;
		return pi * rayon * rayon;
	}

	public void testAppartenance(Point q) {
		Double dx = q.abscisse - this.centre.abscisse;
		Double dy = q.ordonnee - this.centre.ordonnee;
		Double distance = Math.sqrt(dx * dx + dy * dy);
		if (distance.doubleValue() == this.rayon.doubleValue()) {
			System.out.println("Le point choisi apprtient au cercle");
		} else {
			System.out.println("Le point choisi n'apprtient pas au cercle");
		}
	}

	void afficherCercle() {
		System.out.println("Le rayon du cercle est " + this.rayon);
		System.out.println("Le centre du cercle est le point ayant pour abscisse = " + this.centre.abscisse
				+ " ayant pour ordonnée = " + this.centre.ordonnee);
	}
}
