package etudiants;

import professeurs.ProfTAL;

public class EtudiantTALInalco extends EtudiantTAL {

	private String langue;
	private ProfTAL profPrefere;

	public EtudiantTALInalco(String nom, long numeroEtudiant, String langue) {
		super(nom, numeroEtudiant);
		this.langue = langue;
	}

	protected String imprimerID(String nom, long numeroEtudiant, String langue) {
		return super.imprimerID(nom, numeroEtudiant) + "ma langue de spécialité est: " + langue;
	}

	
	public void afficherProfPrefere() {
		System.out.println(
				"Mon Prof preferee est =" + profPrefere.getNom());
		
	}
	public String toString() {
		return "Methode ToString de la classe EtudiantTALInalco. \n" + "Nom =" + nom + "\n" + "langue =" + langue + "\n"
				+ "numeroEtudiant =" + numeroEtudiant + "\n";
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public ProfTAL getProfPrefere() {
		return profPrefere;
	}

	public void setProfPrefere(ProfTAL profPrefere) {
		this.profPrefere = profPrefere;
	}

}
