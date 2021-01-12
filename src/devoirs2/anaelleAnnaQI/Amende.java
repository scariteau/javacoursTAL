package devoirs2.anaelleAnnaQI;

/**
* Classe qui présente les informations sur les amendes
* @author Anna Niskovskikh, Anaëlle Pierredon et Qi Wang
* @version 1.3
*/

public class Amende {
	
	private int nbJourEnRetard;
	private double amende;
	private static final int prixParJour = 1 ;

	
	/**
    * Constructeur de la classe Amende
    * @param nbJourEnRetard Le nombre de jours de retard dans le rendu du livre.
    */
	public Amende(int nbJourEnRetard) {
		this.nbJourEnRetard = nbJourEnRetard;
		this.amende = calculer(nbJourEnRetard);
	}
	
	/**
    * Méthode pour calculer le montant de l'amende en fonction du nombre de jours en retard.
    * Le prix par jour est fixé à 1 euro.
    * @param nbJourEnRetard Le nombre de jours de retard dans le rendu du livre.
    * @return le montant de l'amende
    */
	public int calculer(int nbJourEnRetard) {
		return prixParJour * nbJourEnRetard;
		}
	
	public double getAmende() {
		return amende;
	}

}
