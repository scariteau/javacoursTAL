package devoirs2.anaelleAnnaQI;

/**
 * Classe qui présente les informations pour l'étudiant 
 * comprenant son établissement et son numéro d'étudiant
 * aussi avec l'héritage de la classe Lecteur.
 * 
 * @author Anna Niskovskikh, Anaëlle Pierredon et Qi Wang
 * @version 1.4
 */

public class Etudiant extends Lecteur {
	
	private String etablissement;
	private int numeroEtudiant;

	/**
	 * Constructeur de la classe Etudiant
	 * @param nom Nom de famille de l'étudiant venant de la classe Lecteur
	 * @param prenom Prenom de l'étudiant venant de la classe Lecteur
	 */
	public Etudiant(String nom, String prenom) {
		super(nom, prenom);
	}
	
	public String getEtablissement() {
		return etablissement;
	}
	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}
	
	public int getNumeroEtudiant() {
		return numeroEtudiant;
	}
	
	public void setNumeroEtudiant(int numeroEtudiant) {
		this.numeroEtudiant = numeroEtudiant;
	}
	
	/**
	 * Méthode pour renvoyer sa catégorie socio-professionnelle
	 * donc Etudiant pour la classe Etudiant
	 * @return CategorieSocioProfessionelle.Etudiant
	 */
	@Override
	public CategorieSocioProfessionelle GetCategorie() {
		return CategorieSocioProfessionelle.Etudiant;
	}
}
