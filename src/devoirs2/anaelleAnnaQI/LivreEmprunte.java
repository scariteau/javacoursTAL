package devoirs2.anaelleAnnaQI;

import java.time.LocalDate;

/**
 * Classe qui présente les informations pour le livre emprunté
 * @author Anna Niskovskikh, Anaëlle Pierredon et Qi Wang
 * @version 1.3
 */

public class LivreEmprunte {
	
	private LocalDate dateEmprunt;
	private int nbJourEmprunt;
	private Lecteur lecteur;
	private Livre livre;
	
	public LivreEmprunte() {
	}
	

	/**
	 * Méthode equals pour vérifier qu'un objet donné,
	 * donc livreEmprunte est bien une instance de LivreEmprunte
	 * et ensuite vérifier que cette instance est de type LivreEmprunte 
	 * @param livreEmprunte Un objet livre emprunté
	 * @return true L'objet est bien une instance de LivreEmprunte ou Cette instance est de type LivreEmprunte.
	 * @return false Si l'objet n'est pas une instance de LivreEmprunte, et l'instance n'est pas de type LivreEmprunte.
	 */
	@Override
	public boolean equals(Object livreEmprunte) {
		if (this == livreEmprunte) {
			return true;
		}
		if (livreEmprunte instanceof LivreEmprunte) {
			return ((this.getLivre().equals(((LivreEmprunte) livreEmprunte).getLivre())));
		}
		return false;
	}
	
	
	public int hashCode() {
		return livre.hashCode() + lecteur.hashCode();
	}
	
	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	
	public int getNbJourEmprunt() {
		return nbJourEmprunt;
	}

	public void setNbJourEmprunt(int nbJourEmprunt) {
		this.nbJourEmprunt = nbJourEmprunt ;
	}
	
	public Lecteur getLecteur() {
		return lecteur;
	}

	public void setLecteur(Lecteur lecteur) {
		this.lecteur = lecteur;
	}
	
	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}	
}