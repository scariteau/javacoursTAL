package etudiants;

public class EtudiantTALInalco extends EtudiantTAL {

	private String langue;

	EtudiantTALInalco(String nom, long numeroEtudiant, String langue) {
		super(nom, numeroEtudiant);
		this.langue = langue;
	}

	protected String imprimerID(String nom, long numeroEtudiant, String langue) {
		return super.imprimerID(nom, numeroEtudiant) + "ma langue de spécialité est: " + langue;
	}

	public String toString() {
		return "Methode ToString de la classe EtudiantTALInalco. \n" + "Nom =" + nom + "\n" + "langue =" + langue + "\n"
				+ "numeroEtudiant =" + numeroEtudiant + "\n";
	}

	public static void main(String[] args) {
		EtudiantTALInalco etudianteLeonard = new EtudiantTALInalco("Leonard", 32, "anglais");
		EtudiantTAL etudianteMylene = new EtudiantTAL("Mylene", 22);
		System.out.println(etudianteLeonard.imprimerID(etudianteLeonard.nom, etudianteLeonard.numeroEtudiant,
				etudianteLeonard.langue));

		System.out.println(
				etudianteLeonard.imprimerID(etudianteLeonard.nom, etudianteLeonard.numeroEtudiant, "allemand"));
		// System.out.println("nom de Bernard="+etudianteLeonard.nom +
		// "age="+etudianteLeonard.age + "langue=" +etudianteLeonard.langue);

		System.out.println(etudianteLeonard);
		System.out.println(etudianteLeonard.toString());
		System.out.println(etudianteMylene.toString());

	}

}
