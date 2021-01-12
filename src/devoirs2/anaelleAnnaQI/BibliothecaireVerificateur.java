package devoirs2.anaelleAnnaQI;

/**
* Classe qui contient des méthodes permettant de vérifier des informations.
* @author Anna Niskovskikh, Anaëlle Pierredon et Qi Wang
* @version 1.3
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class BibliothecaireVerificateur {	
	
	/**
	 * Méthode pour vérifier qu'un livre donné n'a pas été déjà emprunté.
	 * @param HashSet livresEmpruntes est un tableau contenant tous les livres empruntés;
	 * 		  Livre livre est une instance de Livre à vérifier.
	 * @return il renvoie une exception si le livre a été déjà emprunté, false sinon.
	 * @exception Exception sur erreur de input.
     * @see Exception
	 */
	static public void VerifierSiLivreDejaEmprunte(HashSet<LivreEmprunte> livresEmpruntes, Livre livre) throws Exception {
		for(LivreEmprunte livreEmprunte : livresEmpruntes){
			Livre livreCourant = livreEmprunte.getLivre();
			if (livreCourant.equals(livre)){
				throw new Exception("Le livre '" + livre.getTitre() +"' a déjà été emprunté."); 
			}
		}
	}

	/**
	 * Méthode pour vérifier qu'un lecteur donné n'a pas déjà emprunté un livre.
	 * @param Lecteur lecteur est une instance de Lecteur à vérifier;
	 * 		  LivreEmprunte livreEmprunte est une instance de LivreEmprunte à vérifier.
	 * @return il renvoie une exception si le lecteur a déjà emprunté un livre, false sinon.
	 * @exception Exception sur erreur de input.
     * @see Exception
	 */
	static public void VerifierSiLecteurADejaEmprunteLivre(Lecteur lecteur, LivreEmprunte livreEmprunte) throws Exception {
		if (Objects.nonNull(livreEmprunte)){
			throw new Exception("Le lecteur '" + lecteur.getNom() +"' a déjà emprunté un livre.");
		}		
	}
	
	/**
	 * Méthode pour vérifier qu'un livre demandé existe dans le catalogue.
	 * @param HashMap<Auteur, ArrayList<Livre>> catalogue est un tableau contenant des livres existants dans le catalogue;
	 * 		  Livre livre est une instance de Livre à vérifier.
	 * @return il renvoie une exception si le livre n'existe pas dans le catalogue, false sinon.
	 * @exception Exception sur erreur de input.
     * @see Exception
	 */
	static public void VerifierSiLivreExisteDansCatalogue(HashMap<Auteur, ArrayList<Livre>> catalogue, Livre livre) throws Exception {
		// on vérifie si le livre existe bien dans le catalogue
		ArrayList<Livre> listeLivre = catalogue.get(livre.getAuteur());
		if (!listeLivre.contains(livre)){
			throw new Exception("Le livre '" + livre.getTitre() +"' n'est pas dans le catalogue.");
		}		
	}
	
	/**
	 * Méthode pour vérifier si un lecteur est en retard pour rendre un livre emprunté.
	 * @param Lecteur lecteur est une instance de Lecteur à vérifier.
	 * @return il renvoie une exception si le lecteur n'est pas en retard, false sinon.
	 * @exception Exception sur erreur de input.
     * @see Exception
	 */
	static public void VerifierLecteurARetard(Lecteur lecteur) throws Exception
	{
		if (!lecteur.aLivreEnRetard()) {
			throw new Exception("Le lecteur '" + lecteur.getNom() +"' n'est pas en retard.");
		}
	}
	
	/**
	 * Méthode pour vérifier si un lecteur a eu une amende.
	 * @param Lecteur lecteur est une instance de Lecteur à vérifier.
	 * @return il renvoie une exception si le lecteur n'a pas d'amende à payer, false sinon.
	 * @exception Exception sur erreur de input.
     * @see Exception
	 */
	static public void VerifierLecteurAAmende(Lecteur lecteur) throws Exception
	{
		Amende amende = lecteur.getAmende();
		if (Objects.isNull(amende)){
			throw new Exception("Le lecteur '" + lecteur.getNom() +"' n'a pas d'amende à payer.");
		}
	}
	
	/**
	 * Méthode pour vérifier si un lecteur a payé son amende.
	 * @param Lecteur lecteur est une instance de Lecteur à vérifier;
	 * 		  Amende amende est une instance de Amende à payer;
	 * 		  double amendePayee est le montant déjà payé par le lecteur.
	 * @return il renvoie une exception si le lecteur n'a pas payé son amende en entier, false sinon.
	 * @exception Exception sur erreur de input.
     * @see Exception
	 */
	static public void VerifierLecteurAPayeBonMontantAmende(Lecteur lecteur, Amende amende, double amendePayee) throws Exception
	{
		if (amendePayee != amende.getAmende()){
			throw new Exception("Le lecteur '" + lecteur.getNom() +"' n'a pas payé le montant de l'amende. Montant dû : " + amende.getAmende() + ", montant payé : " + amendePayee);
		}		
	}
}