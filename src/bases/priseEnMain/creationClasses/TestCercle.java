package bases.priseEnMain.creationClasses;

public class TestCercle {
	public static void main(String[] args) {
		Point centre = new Point(0.0, 0.0);
		Cercle monCercle = new Cercle(centre, 3.0);
		Point M = new Point(3.0, 0.0);
		monCercle.afficherCercle();
		monCercle.testAppartenance(M);
	}
}