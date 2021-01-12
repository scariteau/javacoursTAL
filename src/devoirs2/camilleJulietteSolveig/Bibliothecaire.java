package devoirs2.camilleJulietteSolveig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.time.LocalDate;

/**
 * Classe représentant virtuellement une bibliothèque
 */
public class Bibliothecaire {

	private HashMap<Auteur, ArrayList<Livre>> catalogue;
	private ArrayList<Emprunteur> emprunteurs;
	private double caisse;
	
	 /**
	  * Constructeur de la classe Bibliothecaire
	  * @param catalogue un objet de type HashMap associant un auteur
	  * à la liste de ses livres présents dans la bibliothèque
	  */
	public Bibliothecaire(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue=catalogue;
		this.setCaisse(0);
		this.emprunteurs = new ArrayList<Emprunteur>();
	}
	
	 /**
	  * Méthode pour ajouter un livre au catalogue
	  * @param nouveauLivre un objet de type Livre correspondant au livre à ajouter
	  */
	public void ajouterLivre(Livre nouveauLivre) {
		if (Objects.nonNull(getCatalogue().get(nouveauLivre.getAuteur()))) {
			getCatalogue().get(nouveauLivre.getAuteur()).add(nouveauLivre);
		} else {
			ArrayList<Livre> livres = new ArrayList<>();
			livres.add(nouveauLivre);
			getCatalogue().put(nouveauLivre.getAuteur(), livres);
		}
	}

	 /**
	  * Méthode pour supprimer un livre du catalogue
	  * @param ancienLivre un objet de type Livre correspondant au livre à supprimer
	  */
	public void enleverLivre(Livre ancienLivre) {
		if ((Objects.nonNull(getCatalogue().get(ancienLivre.getAuteur()))) ) {
			getCatalogue().get(ancienLivre.getAuteur()).remove(ancienLivre);
		}
	}
	
	 /**
	  * Méthode pour lister tous les livres du catalogue
	  * @return listeLivres une liste d'objets de type Livre 
	  */
	public ArrayList<Livre> listerLivres() {
		ArrayList<Livre> listeLivres = new ArrayList<Livre>();
		for (ArrayList<Livre> livres : getCatalogue().values()) {
			for (Livre livre : livres) {
				listeLivres.add(livre);
			}
		}
		return listeLivres;
	}
	
	 /**
	  * Méthode pour lister tous les livres d'un auteur
	  * Surcharge de la méthode listerLivres
	  * @param auteur un objet de type Auteur correspondant à l'auteur recherché 
	  * @return une liste d'objets de type Livre 
	  */
	public ArrayList<Livre> listerLivres(Auteur auteur) {
		ArrayList<Livre> listeLivres =  getCatalogue().get(auteur);
		if (Objects.nonNull(listeLivres)) {
			return listeLivres;
		}
		return new ArrayList<Livre>();
	}
	
	 /**
	  * Méthode pour lister tous les livres d'un thème spécifique
	  * Surcharge de la méthode listerLivres
	  * @param theme une chaîne de car. correspondant au thème recherché
	  * @return listeLivresTheme une liste d'objets de type Livre
	  */
	public ArrayList<Livre> listerLivres(String theme) {
		ArrayList<Livre> listeLivresTheme = new ArrayList<Livre>();
		for (Livre livre : listerLivres()) {
			if (livre.getTheme() == theme) {
				listeLivresTheme.add(livre);
			}
		}
		return listeLivresTheme;
	}
	
	 /**
	  * Méthode pour lister tous les livres du catalogue appartenant à
	  * une classe spécifique dérivée de la classe Livre
	  * (Permet notamment de lister tous les livres en anglais = les livres
	  * de la classe LivreAnglais, qui hérite de la classe Livre)
	  * Surcharge de la méthode listerLivres
	  * @param cls une classe dérivée de la classe Livre  
	  * @return listeLivresClasse une liste d'objets de type Livre
	  */
	public ArrayList<Livre> listerLivres(Class<? extends Livre> cls) {
		ArrayList<Livre> listeLivresClasse = new ArrayList<Livre>();
		for (Livre livre : listerLivres()) {
			if (cls.isInstance(livre)) {
				listeLivresClasse.add(livre);
			}
		}
		return listeLivresClasse;
	}
	
	 /**
	  * M�thode pour prêter un livre à un individu
	  * Ajoute à l'inventaire d'un emprunteur un livre associé à une date de rendu 
	  * @param livre un objet de type Livre correspondant au livre prêté
	  * @param emprunteur objet de type Emprunteur correspondant à l'individu qui emprunte le livre 
	  * @param date la date à laquelle le livre doit être rendu
	  */
	public void preterLivre(Livre livre, Emprunteur emprunteur, LocalDate date) {
		emprunteur.addEmprunt(livre, date);
		if (!getEmprunteurs().contains(emprunteur)) {
			getEmprunteurs().add(emprunteur);
		}
	}
	
	 /**
	  * Méthode pour lister tous les emprunteurs en retard = les emprunteurs ayant
	  * dans leur inventaire un ou plusieurs livres dont la date de rendu est dépassée
	  * @return retards un objet HashMap associant un emprunteur (objet Emprunteur)
	  * à la liste de ses livres (objets Livre) en retard
	  */
	public HashMap<Emprunteur, ArrayList<Livre>> listerEmprunteursEnRetard() {
		LocalDate aujourdhui = LocalDate.now();
		HashMap<Emprunteur, ArrayList<Livre>> retards = new HashMap<Emprunteur, ArrayList<Livre>>();
		for (Emprunteur emprunteur : emprunteurs) {
			for (Map.Entry<Livre, LocalDate> emprunt : emprunteur.getLivresEmpruntes().entrySet()){
				LocalDate dateRendu = emprunt.getValue();
				if (dateRendu.isBefore(aujourdhui)) {
					if (Objects.nonNull(retards.get(emprunteur))){
						retards.get(emprunteur).add(emprunt.getKey());
					} else {
						ArrayList<Livre> livres = new ArrayList<Livre>();
						livres.add(emprunt.getKey());
						retards.put(emprunteur, livres);
					}
				}
			}
		}
		return retards;
	}
	
	 /**
	  * Méthode pour "relancer" un emprunteur en retard
	  * Envoie un message à l'emprunteur en retard : ajoute à sa boîte de réception
	  * (attribut messagerie) un message associé à l'intitulé "retard"
	  */
	public void RelancerEmprunteurEnRetard() {
		HashMap<Emprunteur, ArrayList<Livre>> retards = listerEmprunteursEnRetard();
		String livresRetard = "";
		for (Emprunteur emprunteur : retards.keySet()) {
			for (Livre livre : retards.get(emprunteur)) {
				livresRetard += "\n- " + livre.getTitre();
			}
			emprunteur.addMessage("retard", "vous avez des livres en retard : " + livresRetard);
		}
	}

	 /**
	  * Méthode pour lister tous les livres du catalogue qui sont empruntés
	  * @return livres une liste d'objets de type Livre correspondant aux livres
	  * qui sont dans les inventaires des emprunteurs
	  */
	public ArrayList<Livre> listerLivresEmpruntes() {
		ArrayList<Livre> livres = new ArrayList<Livre>();
		for (Emprunteur emprunteur : emprunteurs) {
			for (Map.Entry<Livre, LocalDate> emprunt : emprunteur.getLivresEmpruntes().entrySet()){
				livres.add(emprunt.getKey());
			}
		}
		return livres;
	}
	
	 /**
	  * Méthode pour lister tous les livres empruntés par un type d'emprunteur spécifique
	  * (Permet notamment de lister tous les livres empruntés par des étudiants, 
	  * de la classe EtudiantEmprunteur qui hérite de la classe Emprunteur)
	  * Surcharge de la méthode listerLivresEmpruntes
	  * @param cls une classe dérivée de la classe Emprunteur
	  * @return livres une liste d'objets de type Livre 
	  */
	public ArrayList<Livre> listerLivresEmpruntes(Class<? extends Emprunteur> cls) {
		ArrayList<Livre> livres = new ArrayList<Livre>();
		for (Emprunteur emprunteur : emprunteurs) {
			if (cls.isInstance(emprunteur)) {
				for (Map.Entry<Livre, LocalDate> emprunt : emprunteur.getLivresEmpruntes().entrySet()){
					livres.add(emprunt.getKey());
				}
			}
		}
		return livres;
	}
	
	 /**
	  * Méthode pour lister tous les livres d'un auteur qui sont empruntés
	  * Surcharge de la méthode listerLivresEmpruntes
	  * @param auteurFiltre un objet de type Auteur correspondant à l'auteur recherché 
	  * @return livresAuteur une liste d'objets de type Livre, les livres écrits par
	  * l'auteur recherché qui sont empruntés
	  */
	public ArrayList<Livre> listerLivresEmpruntes(Auteur auteurFiltre) {
		ArrayList<Livre> livresAuteur = new ArrayList<Livre>();
		for (Livre livre : listerLivresEmpruntes()) {
			if (livre.getAuteur() == auteurFiltre) {
					livresAuteur.add(livre);
			}
		}
		return livresAuteur;
	}
	
	 /**
	  * Méthode sélectionner un livre d'un thème spécifique
	  * @param theme une chaîne de car. correspondant au thème recherché
	  * @return choix un livre (objet Livre) sélectionné au hasard 
	  * parmi les livres du catalogue qui correspondent au thème recherché
	  * (si aucun livre ne correspond au thème : renvoie la valeur null)
	  */
	public Livre TrouverLivreSurUnTheme(String theme) {
		ArrayList<Livre> livresTheme = listerLivres(theme);
		Random randomGenerator = new Random();
		try {
			int index = randomGenerator.nextInt(livresTheme.size());
			Livre choix = livresTheme.get(index);
			return choix;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	 /**
	  * Méthode pour envoyer une amende à un retardataire
	  * Ajoute au solde de l'emprunteur 2 euros pour chaque livre en retard 
	  * (objet Livre de son inventaire dont la date de rendu est dépassée)
	  * Le solde généré correspond à la somme qu'il doit à la bibliothèque
	  */
	public void EnvoyerAmendeRetardaire() {
		HashMap<Emprunteur, ArrayList<Livre>> retards = listerEmprunteursEnRetard();
		for (Emprunteur emprunteur : retards.keySet()) {
			emprunteur.changeSolde(2 * retards.get(emprunteur).size());
		}
	}
	
	 /**
	  * Méthode pour encaisser l'amende d'un retardataire
	  * Transfère une somme du solde de l'emprunteur vers la caisse de la bibliothèque
	  * @param retardataire l'emprunteur en retard (objet Emprunteur) qui
	  * paye son amende
	  * @param versement nombre correspondant à la somme versée par l'emprunteur
	  */
	public void EncaisserAmendeRetardaire(Emprunteur retardataire, double versement) {
		retardataire.changeSolde(-versement);
		this.changeCaisse(versement);
		}
	
	public HashMap<Auteur, ArrayList<Livre>> getCatalogue() {
		return catalogue;
	}

	public void initialiserCatalogue(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue = catalogue;
	}

	public ArrayList<Emprunteur> getEmprunteurs() {
		return emprunteurs;
	}

	public void setEmprunteurs(ArrayList<Emprunteur> emprunteurs) {
		this.emprunteurs = emprunteurs;
	}

	public double getCaisse() {
		return caisse;
	}

	public void setCaisse(double caisse) {
		this.caisse = caisse;
	}
	
	public void changeCaisse(double montant) {
		this.caisse += montant;
	}
	
}
