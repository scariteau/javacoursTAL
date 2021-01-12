package devoirs2.anaelleAnnaQI;

import java.io.Serializable;
import java.util.Comparator;
import java.util.function.BooleanSupplier;

/**
 * Classe qui présente les informations pour le livre
 * @author Anna Niskovskikh, Anaëlle Pierredon et Qi Wang
 * @version 1.2
 */

public class Livre implements Serializable  {

	private static final long serialVersionUID = 1L;
	private Auteur auteur;
	private String titre;
	private String resume;
	private int anneePublication;
	private int nbTomes;
	private String langue;
	private String theme;
	
	/**
	 * Constructeur de la classe Livre
	 * @param auteur Auteur du livre
	 * @param titre Titre du livre
	 */
	
	public Livre(Auteur auteur,String titre) {
		this.auteur=auteur;
		this.titre=titre;
	}
	
	/**
	 * Méthode equals pour vérifier qu'un objet donné, donc livre est bien une instance de Livre
	 * et ensuite vérifier que cette instance est de type Livre
	 * @param livre Un objet livre
	 * @return true L'objet est bien une instance de Livre ou Cette instance est de type Livre.
	 * @return false Si l'objet n'est pas une instance de Livre, et l'instance n'est pas de type Livre.
	 * 
	 */
	@Override
	public boolean equals(Object livre) {
		if (this == livre) {
			return true;
		}
		if (livre instanceof Livre) {
			return ((this.getAuteur().equals(((Livre) livre).getAuteur()))
					&& (this.titre.equals(((Livre) livre).getTitre())));
		}
		return false;

	}
	
	public int hashCode() {
		return titre.hashCode();
	}
	
	public Auteur getAuteur() {
		return auteur;
	}

	public int getAnneePublication() {
		return anneePublication;
	}

	public void setAnneePublication(int anneePublication) {
		this.anneePublication = anneePublication;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNbTomes() {
		return nbTomes;
	}

	public void setNbTomes(int nbTomes) {
		this.nbTomes = nbTomes;
	}
	
	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}
	
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
}

/**
 * Classe pour comparer deux livres
 * au niveau de leur titre en ASCII
 * @return livre1.getTitre().compareTo(livre2.getTitre())
 */
class CompareLivre implements Comparator<Livre>{
	public int compare(Livre livre1, Livre livre2) {
		return livre1.getTitre().compareTo(livre2.getTitre());
	}
}