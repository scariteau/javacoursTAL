package devoirs2.arthurFabienneMartin;

import java.io.Serializable;

/**
 * <ul>
 * Classe fille de la classe Auteur.
 * <li>boolean amande : Définit si le client doit payer une amende pour son retard.</li>
 * <li>boolean etudiant : Définit si le client est un étudiant.</li>
 * <li>Récupère tous les attributs de la classe mère Auteur.</li>
 * </ul>
 */
public class Client extends Auteur{
	
	private static final long serialVersionUID = 1L;
	private boolean amande = false;
	private boolean etudiant;
	

	Client(String nom, boolean etudiant){
		super(nom);
		this.etudiant = etudiant;
	}
	
	
	@Override
	public boolean equals(Object anObject) {
		if (this == anObject) {
			return true;
		}
		if (anObject instanceof Client) {
			return getNom().equals(((Client) anObject).getNom());
		}
		return false;
	}

	public boolean isAmande() {
		return amande;
	}


	public void setAmande(boolean amande) {
		this.amande = amande;
	}


	public boolean isEtudiant() {
		return etudiant;
	}

}
