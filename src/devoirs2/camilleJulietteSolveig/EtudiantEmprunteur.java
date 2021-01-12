package devoirs2.camilleJulietteSolveig;

/**
 * Classe représentant un emprunteur qui est étudiant
 * Hérite de la classe Emprunteur : {@inheritDoc} 
 */
public class EtudiantEmprunteur extends Emprunteur {
	
	private long numeroEtudiant;

	 /**
	  * Constructeur de la classe EtudiantEmprunteur
	  * @param nom le nom de l'individu emprunteur
	  * @param prenom le prénom de l'emprunteur
	  * @param numeroEtudiant un nombre correspondant au numéro étudiant de l'emprunteur
	  */
	public EtudiantEmprunteur(String nom, String prenom, long numeroEtudiant) {
		super(nom, prenom);
		this.setNumeroEtudiant(numeroEtudiant);
	}

	public long getNumeroEtudiant() {
		return numeroEtudiant;
	}

	public void setNumeroEtudiant(long numeroEtudiant) {
		this.numeroEtudiant = numeroEtudiant;
	}
}
