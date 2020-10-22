package professeurs;

import etudiants.EtudiantTAL;
import etudiants.EtudiantTALInalco;

public class ProfTAL {

	private String nom;
	String prenom;
	String matiere;
	EtudiantTAL etudiantPref;
	EtudiantTALInalco etudiantDeteste;

	public ProfTAL(String nom, String prenom, String matiere) {
		this.nom=nom;
		this.prenom=prenom;
		this.matiere=matiere;

	}

	public void afficherEtudiantPrefere() {
		System.out.println(
				"Mon etudiant preferee est =" + etudiantPref.nom + " et il a ==" + etudiantPref.getAge());
	}

	public EtudiantTAL getEtudiantPref() {
		return etudiantPref;
	}

	public void setEtudiantPref(EtudiantTAL etudiantPref) {
		this.etudiantPref = etudiantPref;
	}

	public EtudiantTALInalco getEtudiantDeteste() {
		return etudiantDeteste;
	}

	public void setEtudiantDeteste(EtudiantTALInalco etudiantDeteste) {
		this.etudiantDeteste = etudiantDeteste;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
