package professeurs;

import etudiants.EtudiantTALInalco;

public class ProfTAL {

	String nom;
	String prenom;
	String matiere;
	EtudiantTALInalco etudiantPref;

	public ProfTAL(String nom, String prenom, String matiere) {
		this.nom=nom;
		this.prenom=prenom;
		this.matiere=matiere;

	}

	public void imprimerEtudiantPrefere() {
		System.out.println(
				"Mon etudiant preferee est =" + etudiantPref.nom + " et il etudie==" + etudiantPref.getLangue());
	}

	public EtudiantTALInalco getEtudiantPref() {
		return etudiantPref;
	}

	public void setEtudiantPref(EtudiantTALInalco etudiantPref) {
		this.etudiantPref = etudiantPref;
	}

}
