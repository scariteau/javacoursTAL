package bases.priseEnMain.creationClasses;

public class Exécution {

	public static void main(String[] args) {
		Voiture maVoiture = new Voiture();
		maVoiture.setMarque("Renault");
		maVoiture.setPrix(17500.0);
		maVoiture.afficher();
	}
}