package devoirs2.anaelleAnnaQI;

/**
* Classe qui contient les informations sur les auteurs et les méthodes associées.
* @author Sandrine Cariteau
*/

import java.io.Serializable;

public class Auteur implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String nom;
	
	/**
	 * Constructeur de la classe Auteur
	 * @param nom
	 */
	public Auteur(String nom) {
		this.setNom(nom);
	}
	
	/**
	 * Méthode equals pour les objets de la classe Auteur. 
	 * Elle vérifie que les noms de deux objets de la classe Auteur sont égaux.
	 * @param anObject
	 * @return true s'ils sont égaux, false sinon
	 */
	@Override
	public boolean equals(Object anObject) {
		if (this == anObject) {
			return true;
		}
		if (anObject instanceof Auteur) {
			return nom.equals(((Auteur) anObject).getNom());
		}
		return false;

	}
	
	/**
	 * Méthode qui permet de vérifier que deux objets renvoient la même valeur.
	 * @return true s'ils sont égaux, false sinon
	 */
	public int hashCode() {
		return nom.hashCode();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
