package devoirs2.jianyingMeiSheherazade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Bibliothecaire {

	private HashMap<Auteur, ArrayList<Livre>> catalogue;
	public Bibliothecaire(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue=catalogue;

	}

	public HashMap<Auteur, ArrayList<Livre>> getCatalogue() { return catalogue; }

	public void initialiserCatalogue(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue = catalogue;
	}



	/**
	 * Cette méthode rajoute un livre dans l'objet catalogue.
	 * @param nouveauLivre un objet Livre
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
	 * Cette méthode va imprimer tous les auteurs dans le catalogue et le nombre de ses oeuvres ainsi que leurs titre
	 * @param auteur un objet Auteur
	 * @return imprimer les noms des auteurs et les nombres et titres de leurs oeuvres
	 */
	public String listerOeuvresAuteur(Auteur auteur) {
		ArrayList<Livre> livres= catalogue.get(auteur);
		String titreDesLivres="";
		for (Livre livre : livres) {
			titreDesLivres+=livre.getTitre()+ "\n";
		}
		return "L'auteur "+auteur.getNom()+" a ecrit "+livres.size() +" livres:\n"+ titreDesLivres;
	}

	/**
	 * Cette méthode va enlever le livre fourni du catalogue
	 * @param ancienLivre  un objet Livre
	 */
	public void enleverLivre(Livre ancienLivre) {
		if ((Objects.nonNull(getCatalogue().get(ancienLivre.getAuteur())))) {
			getCatalogue().get(ancienLivre.getAuteur()).remove(ancienLivre);
		}
	}

	/**
	 * Cette méthode change la valeur de l'attribut preter de l'objet livre à True
	 * @param ancienLivre
	 */
	public void preterLivre(Livre ancienLivre) {
		if ((Objects.nonNull(getCatalogue().get(ancienLivre.getAuteur()))) ) {
			ancienLivre.setPreter(true);
		}
	}

	/**
	 * Cette méthode prend en entrée le livre et l'emprunteur
	 * et changer l'attribut "preter" et emprunteur du livre
	 * @param ancienLivre un objet Livre qui va être emprunté
	 * @param emprunteur  un objet Emprunteur
	 */
	public void preterLivre(Livre ancienLivre, Emprunteur emprunteur) {
		if ((Objects.nonNull(getCatalogue().get(ancienLivre.getAuteur()))) ) {
			ancienLivre.setPreter(true);
			ancienLivre.setEmprunteur(emprunteur);
		}
	}

	/**
	 * Cette méthode sert aussi à changer le status du livre, mais elle rajoute un argument date de retour
	 * @param ancienLivre  objet Livre  ses attributs à changer
	 * @param emprunteur  objet Emprunteur à rajouter dans "emprunteur" du livre
	 * @param dateRetour  du type String  la date sous forme dd-MM-yyyy
	 * @throws ParseException  renvoie cette erreur quand la string de date n'est pas bon
	 */
	public void preterLivre(Livre ancienLivre, Emprunteur emprunteur, String dateRetour) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		if ((Objects.nonNull(getCatalogue().get(ancienLivre.getAuteur()))) ) {
			ancienLivre.setPreter(true);
			ancienLivre.setEmprunteur(emprunteur);
			Date btRetour=sdf.parse(dateRetour);
			ancienLivre.setDateRetour(btRetour);

		}
	}



	/**
	 * Cette méthode compare la date de rendu de chaque livre avec la date actuelle
	 * et imprime le titre du livre et son emprunteur si la date de rendu est passée
	 */
	public void RelancerEmprunteurEnRetard() {
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		for (Auteur auhtor : getCatalogue().keySet()){
			for (Livre livre : getCatalogue().get(auhtor)){
				Date dateRetour = livre.getDateRetour();
				if (dateRetour.before(date)){
					livre.setHorsDelai(true);
					System.out.println("Le livre " + livre.getTitre()+ " emprunté par " + livre.getEmprunteur().toString() + "est en retard de rendu.");
				}
			}
		}
	}


	/**
	 * Cette méthode renvoie une liste des emprunteurs qui sont en train d'emprunter au moins un livre du catalogue
	 * @return un ArrayList des emprunteurs
	 */
	public ArrayList<Emprunteur> listerPersonnesAyantEmpruntesUnLivre() {
		ArrayList<Emprunteur> emprunteurs = new ArrayList<Emprunteur>();
		if (Objects.nonNull(getCatalogue())) {
			for (Auteur a : getCatalogue().keySet()) {
				for (Livre l : getCatalogue().get(a)){
					if (l.isPreter()) {
						emprunteurs.add(l.getEmprunteur());
					}
				}
			}
		}
		HashSet<Emprunteur> set = new HashSet<Emprunteur>(emprunteurs);
		emprunteurs = new ArrayList<Emprunteur>(set);
		return emprunteurs;
	}

	/**
	 * Cette méthode teste tous les livres dans catalogue,
	 * et renvoie les livres qui a classe Etudiant comme son attribut Emprunteur
	 * @return un ArrayList des livres empruntés par étudiant
	 */
	public ArrayList<Livre> listerLivresEmpruntesParEtudiant(){
		ArrayList<Livre> livresEmpruntesParEtudiant = new ArrayList<Livre>();
		if (Objects.nonNull(getCatalogue())){
			for (Auteur a:getCatalogue().keySet() ){
				for (Livre l : getCatalogue().get(a)){
					if (l.getEmprunteur() instanceof Etudiant){
//					if (l.getEmprunteur().getProfession().toUpperCase().equals("ETUDIANT")){
						livresEmpruntesParEtudiant.add(l);
					}
				}
			}
		}
		return livresEmpruntesParEtudiant;
	}

	/**
	 * Cette méthode renvoie une liste des livres empruntés
	 * @return un ArrayList des livres empruntés
	 */
	public ArrayList<Livre> ListerLivresEmpruntes() {
		ArrayList<Livre> livresEmpruntes = new ArrayList<Livre>();
		if (Objects.nonNull(getCatalogue())){
			for (Auteur a:getCatalogue().keySet() ){
				for (Livre l : getCatalogue().get(a)){
					if (l.isPreter() == true){
						livresEmpruntes.add(l);
					}
				}
			}

		}
		return livresEmpruntes;
	}

	/**
	 * Cette méthode renvoie tous les livres qui ont la valeur "anglais" pour l'attribut langue
	 * @return un ArrayList des livres anglais
	 */
	public ArrayList<Livre> ListerLivresAnglais() {
		ArrayList<Livre> livreLangue = new ArrayList<Livre>();
		if (Objects.nonNull(getCatalogue())) {
			for (Auteur a : getCatalogue().keySet()) {
				for (Livre l : getCatalogue().get(a)){
					if (l.getLangue().toUpperCase().equals("ANGLAIS")){
						livreLangue.add(l);
					}
				}
			}

		}
		return livreLangue;
	}


	/**
	 * Cette méthode renvoie le nombre des livres empruntés d'un auteur
	 * @param author un objet Auteur, on examine s
	 * @return un int des nombres de livres conformes
	 */
	public int listerNbLivresEmpruntesPourUnAuteur(Auteur author){
		int nbLivresEmpruntesPourUnAuteur = 0;
		if (Objects.nonNull(getCatalogue())) {
			for (Auteur a : getCatalogue().keySet()) {
				if (a.equals(author)){
					for (Livre l: getCatalogue().get(a)){
						if (l.isPreter() == true){
							nbLivresEmpruntesPourUnAuteur++;
						}
					}
				}
			}
		}
		return nbLivresEmpruntesPourUnAuteur;
	}

	/**
	 * Cette méthode renvoie les livres qui ont le même thème que celui donné
	 * @param theme un String de thème
	 * @return un ArrayList des livres conformes à la demande
	 */
	public ArrayList<Livre> trouverLivreSurUnTheme(String theme){
		ArrayList<Livre> livreSurUnTheme = new ArrayList<Livre>();
		if (Objects.nonNull(getCatalogue())) {
			for (Auteur a : getCatalogue().keySet()){
				for (Livre l : getCatalogue().get(a)){
					if (l.getTheme().toUpperCase().equals(theme.toUpperCase())){
						livreSurUnTheme.add(l);
					}
				}
			}

		}
		return livreSurUnTheme;
	}

	/**
	 *Cette méthode imprime dans le terminal les livres qui sont en retard et
	 * l'emprunteur qui doit payer l'amande
	 * l'amande de chaque livre est fixée à une somme, mais cette somme est changeable par l'objet Livre
	 */
	public void EnvoyerAmendeRetardaire(){
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		for (Auteur auhtor : getCatalogue().keySet()){
			for (Livre livre : getCatalogue().get(auhtor)){
				Date dateRetour = livre.getDateRetour();
				if (dateRetour.before(date)){
					livre.setHorsDelai(true);
					System.out.println(livre.getEmprunteur().toString()+ " doit payer " + livre.getAmende()+" euros pour le livre "+livre.getTitre());
				}
			}
		}
	}

	/**
	 * Cette méthode change l'attribut "horsDelais" du livre à "false" et puis son "amende" à 0
	 * @param livre un objet Livre  qu'on va changer son status
	 */
	public void encaisserAmendeRetardaire(Livre livre){
		EnvoyerAmendeRetardaire();
		if (Objects.nonNull(getCatalogue())) {
			for (Auteur a : getCatalogue().keySet()) {
				for (Livre l : getCatalogue().get(a)) {
					if (livre.equals(l) && l.getAmende()!=0){
//						System.out.println(l.getTitre());
						System.out.println("La bibliothque a encaissé "+l.getAmende()+" euros de " +l.getEmprunteur().getNom()+" pour le livre "+l.getTitre() );
						l.setHorsDelai(false);
						l.setAmende(0);
					}
				}
			}
		}
	}

}
