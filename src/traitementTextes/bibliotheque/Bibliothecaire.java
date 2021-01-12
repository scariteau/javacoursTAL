package traitementTextes.bibliotheque;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import etudiants.EtudiantTAL;
import traitementTextes.bibliotheque.catalogue.Auteur;
import traitementTextes.bibliotheque.catalogue.Livre;
import traitementTextes.bibliotheque.catalogue.LivreEmprunte;
import traitementTextes.bibliotheque.catalogue.LivreVO;
import traitementTextes.bibliotheque.personnes.Abonne;
import traitementTextes.bibliotheque.personnes.AbonneInconnuException;

/**
 * Classe permettant de gerer une bibliotheque
 * 
 * @author scariteau
 *
 */
public class Bibliothecaire {

	private HashMap<Auteur, ArrayList<Livre>> catalogue;
	private List<LivreEmprunte> livresEmpruntes;
	private int NB_JOURS_EMPRUNT_MAX = 30;
	private double MONTANT_AMENDE = 2;

	public Bibliothecaire(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue = catalogue;
		livresEmpruntes = new ArrayList<LivreEmprunte>();

	}

	/**
	 * methode permettant d'ajouter un livre au catalogue des livres de la
	 * bibliotheque
	 * 
	 * @param nouveauLivre : le nouveau livre a jouter dans la bibliotheque
	 * @return
	 */
	public ArrayList<Livre> ajouterLivre(Livre nouveauLivre) {
		Auteur auteur = nouveauLivre.getAuteur();
		if (Objects.nonNull(getCatalogue().get(auteur))) {
			getCatalogue().get(auteur).add(nouveauLivre);
		} else {
			ArrayList<Livre> livres = new ArrayList<>();
			livres.add(nouveauLivre);
			getCatalogue().put(auteur, livres);
		}
		return getCatalogue().get(auteur);

	}

	/**
	 * méthode permettant de lister toutes les oeuvres présentes dans le catalogue
	 * de la bibliotheque pour un auteur donné
	 * 
	 * @param auteur: l'auteur des oeuvres recherchées
	 * @return
	 */
	public String listerOeuvresAuteur(Auteur auteur) {
		ArrayList<Livre> livres = catalogue.get(auteur);
		String titreDesLivres = "";
		for (Livre livre : livres) {
			titreDesLivres += livre.getTitre() + "\n";
		}
		return "L'auteur " + auteur.getNom() + " a ecrit " + livres.size() + " livres:\n" + titreDesLivres;
	}

	/**
	 * methode permettant d'nelever une livre du catalogue de la bibliotheque
	 * 
	 * @param ancienLivre: le livre a enlever
	 * @return : true si le livre a bien ete enlevee , false s'il n'y'a pas de livre
	 *         pour cet auteur dans la bibliotheque
	 * @throws AuteurInconnuException : si l'auteur n'existe pas dans la
	 *                                bibliotheque
	 */
	public boolean enleverLivre(Livre ancienLivre) throws AuteurInconnuException {

		Auteur auteur = ancienLivre.getAuteur();
		if (Objects.nonNull(getCatalogue().get(auteur))) {
			return getCatalogue().get(auteur).remove(ancienLivre);
		}
		throw new AuteurInconnuException(
				"Auteur: " + auteur + " non trouvée dans le catalogue pour le livre: +" + ancienLivre);
	}

	/**
	 * methode permettant de preter un livre a un abonné
	 * 
	 * @param livreAEmprunte: le livre a preter
	 * @param emprunteur      : l'abonné qui va emprunter le livre
	 * @return : le livre emprunte avec la date d'emprunt et la date de retour
	 */
	public LivreEmprunte preterUnLivre(Livre livreAEmprunte, Abonne emprunteur) {
		LivreEmprunte livreEmprunte = new LivreEmprunte(livreAEmprunte, emprunteur);
		livreEmprunte.setDateDebutEmprunt(LocalDate.now());
		livreEmprunte.setDateFinEmprunt(LocalDate.now().plusDays(NB_JOURS_EMPRUNT_MAX));
		livresEmpruntes.add(livreEmprunte);
		return livresEmpruntes.get(livresEmpruntes.size() - 1);
	}

	/**
	 * methode permettant de connaitre les personnes ayant empruntes un livre
	 * 
	 * @return : liste des personnes ayant au moins un livre emprunte
	 */
	public Set<Abonne> listerPersonnesAyantEmprunteUnLivre() {
		HashSet<Abonne> personnesAyantEmprunteUnLivre = new HashSet<Abonne>();
		for (LivreEmprunte livreEmprunte : livresEmpruntes) {
			personnesAyantEmprunteUnLivre.add(livreEmprunte.getEmprunteur());
		}

		/*
		 * return livresEmpruntes.stream().map(x -> x.getEmprunteur()).distinct()
		 * .collect(Collectors.toList());
		 */
		return personnesAyantEmprunteUnLivre;
	}

	/**
	 * methode permettant de lister tous les livres empruntes par un abonné etudiant
	 * 
	 * @return la liste des livres interessants les etudiants
	 */
	public Set<LivreEmprunte> listerLivresEmpruntesParEtudiant() {
		HashSet<LivreEmprunte> livreEmprunteParUnEtudiants = new HashSet<LivreEmprunte>();
		for (LivreEmprunte livreEmprunte : livresEmpruntes) {
			if (livreEmprunte.getEmprunteur() instanceof EtudiantTAL)
				livreEmprunteParUnEtudiants.add(livreEmprunte);
		}
		return livreEmprunteParUnEtudiants;
	}

	/**
	 * methode permettant de connaitre le nombre de livres ayant ete empruntes pour un auteur donnes
	 * @param auteur: auteur des oeuvres recherches
	 * @return le nombre de livres 
	 */
	public int compterNbLivresEmpruntesPourUnAuteur(Auteur auteur) {
		/*
		 * List<LivreEmprunte> listeEmpruntesPourAuteur = livresEmpruntes.stream()
		 * .filter(x -> x.getAuteur().equals(auteur)) .collect(Collectors.toList());
		 */
		List<LivreEmprunte> livresEmpruntesPourAuteur = new ArrayList<LivreEmprunte>();
		for (LivreEmprunte livreEmprunte : livresEmpruntes) {
			if (auteur.equals(livreEmprunte.getAuteur()))
				livresEmpruntesPourAuteur.add(livreEmprunte);
		}

		return livresEmpruntesPourAuteur.size();
	}

	/**
	 * Methode permettant de retrouver tous les livres sur un theme donnee
	 * @param theme : le theme cherche
	 * @return: une liste contenant les livres sur ce theme
	 */
	public ArrayList<Livre> trouverLivresSurUnTheme(String theme) {
		ArrayList<Livre> livresSurUnThemes = new ArrayList<Livre>();
		for (ArrayList<Livre> livresAuteur : catalogue.values()) {
			for (Livre livre : livresAuteur) {
				if (theme.equals(livre.getTheme()))
					livresSurUnThemes.add(livre);
			}
		}

		return livresSurUnThemes;
	}

	/**
	 * methode permettant de creer des amende aux abonnes n'ayant pas rendu ses livres atemps
	 * @return la liste des amendes creer
	 */
	public ArrayList<Amende> envoyerAmendeRetardataires() {

		ArrayList<Amende> amendesEnvoyees = new ArrayList<Amende>();
		for (LivreEmprunte livreEmprunte : livresEmpruntes) {
			long tempsEmprunt = ChronoUnit.DAYS.between(livreEmprunte.getDateDebutEmprunt(), LocalDate.now());
			if (tempsEmprunt > NB_JOURS_EMPRUNT_MAX) {
				Amende amende = new Amende(livreEmprunte, MONTANT_AMENDE);
				livreEmprunte.getEmprunteur().envoyerAmende(amende);
				amendesEnvoyees.add(amende);
			}

		}
		return amendesEnvoyees;

	}

	/**
	 * methode permettant de regulariser les amendes d'un abonne
	 * @param emprunteur: l'abonne payant son amende
	 * @param amende : l'amende a regulariser
	 * @return : vrai si l'amende a bien ete annule, false si on ne retrouve pas cet amende
	 * @throws AbonneInconnuException: si l'abonne n'hexiste pas 
	 */
	public boolean encaisserAmendeRetardataire(Abonne emprunteur, Amende amende) throws AbonneInconnuException {
		for (LivreEmprunte livreEmprunte : livresEmpruntes) {

			if (emprunteur.equals(livreEmprunte.getEmprunteur())) {
				return livreEmprunte.getEmprunteur().getAmendes().remove(amende);
			}

		}
		throw new AbonneInconnuException("Abonné: " + emprunteur + "non trouvée pour l'amende: +" + amende);
	}

	/**
	 * Methode permettant de relancer un emprunteur en retard
	 * @return le nombre de relances envoyees a l'abonnee
	 */
	public int relancerEmprunteursEnRetard(Abonne abonnee) {
		int nbRelances=0;
		for (LivreEmprunte livreEmprunte : livresEmpruntes) {
			if (abonnee.equals(livreEmprunte.getEmprunteur()) && (livreEmprunte.getDateFinEmprunt().isBefore(LocalDate.now()))) {
			 livreEmprunte.incrementNBRelances();
			 nbRelances++;
			}
		}
		return nbRelances;

	}

	/**
	 * methode permettant de recuperer tous les livres en version original
	 * @param langue : la langue
	 * @return
	 */
	public List<Livre> getLivresVO(String langue) {

		ArrayList<Livre> livresVO = new ArrayList<Livre>();

		for (ArrayList<Livre> livres : catalogue.values()) {
			for (Livre livre : livres) {
				if ((livre instanceof LivreVO) && (langue.equals(((LivreVO) livre).getLangue()))) {
					livresVO.add(livre);
				}
			}

		}

		return livresVO;
	}

	public List<LivreEmprunte> getLivresEmpruntes() {
		return livresEmpruntes;
	}

	public void setLivresEmpruntes(List<LivreEmprunte> livresEmpruntes) {
		this.livresEmpruntes = livresEmpruntes;
	}

	public HashMap<Auteur, ArrayList<Livre>> getCatalogue() {
		return catalogue;
	}

	public void initialiserCatalogue(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue = catalogue;
	}

}
