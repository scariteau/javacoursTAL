package devoirs2.anaelleAnnaQI;

/**
* Classe qui contient les informations sur la bibliothecaire et ses méthodes.
* @author Anna Niskovskikh, Anaëlle Pierredon et Qi Wang
* @version 1.8
*/

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Objects;

public class Bibliothecaire {
    
    private int tresorerie = 0;
    private HashMap<Auteur, ArrayList<Livre>> catalogue;
    private HashSet<LivreEmprunte> livresEmpruntes;
    
    /**
    * Constructeur de la classe Bibliothecaire
    * @param catalogue l'ensemble des auteurs et de leurs livres.
    */
    public Bibliothecaire(HashMap<Auteur, ArrayList<Livre>> catalogue) {
        this.catalogue=catalogue;
        livresEmpruntes = new HashSet<LivreEmprunte>();
    }
    
    public int getTresorerie() {
        return tresorerie;
    }
    
    /**
    * Méthode permettant d'ajouter un livre dans le catalogue
    * @param nouveauLivre le livre à ajouter.
    */
    public void ajouterLivre(Livre nouveauLivre) {
        if (Objects.nonNull(getCatalogue().get(nouveauLivre.getAuteur()))) {
            getCatalogue().get(nouveauLivre.getAuteur()).add(nouveauLivre);
            tresorerie -= 10;
        } else {
            ArrayList<Livre> livres = new ArrayList<>();
            livres.add(nouveauLivre);
            getCatalogue().put(nouveauLivre.getAuteur(), livres);
            tresorerie -= 10;
        }
    }
    
    /**
    * Méthode permettant de lister les oeuvres d'un auteur donné.
    * @param auteur l'auteur dont il faut lister les oeuvres.
    * @return la liste des oeuvres de l'auteur
    */
    public ArrayList<Livre> listerOeuvresAuteur(Auteur auteur) {
        return catalogue.get(auteur);
    }
    
    /**
    * Méthode permettant de retirer un livre du catalogue
    * @param ancienLivre le livre qu'il faut retirer.
    */
    public void enleverLivre(Livre ancienLivre) {
        if ((Objects.nonNull(getCatalogue().get(ancienLivre.getAuteur()))) ) {
            getCatalogue().get(ancienLivre.getAuteur()).remove(ancienLivre);
        }
    }
	
    /**
     * Méthode qui reprend 3 exceptions pour pouvoir prêter un livre.
     * @param Livre livre est une instance de Livre à prêter;
     *           Lecteur lecteur est une instance de Lecteur à qui un livre est prêté.
     * @return il renvoie une exception si le livre n'est pas disponible ou si le lecteur a déjà emprunté un livre,
               sinon le livre est prêté.
     * @exception Exception sur erreur de input.
     * @see Exception
     */
	public void preterLivre(Livre livre, Lecteur lecteur) throws Exception {
		BibliothecaireVerificateur.VerifierSiLivreExisteDansCatalogue(getCatalogue(),livre);
		LivreEmprunte livreEmprunteParLecteur = retourneLivreEmprunte(lecteur);
		BibliothecaireVerificateur.VerifierSiLecteurADejaEmprunteLivre(lecteur, livreEmprunteParLecteur);
		BibliothecaireVerificateur.VerifierSiLivreDejaEmprunte(getLivresEmpruntes(), livre);
		
		LivreEmprunte livreEmprunte = new LivreEmprunte();
		livreEmprunte.setNbJourEmprunt(getNbJourEmprunt());
		livreEmprunte.setLecteur(lecteur);
		livreEmprunte.setDateEmprunt(LocalDate.now());
		livreEmprunte.setLivre(livre);
		getLivresEmpruntes().add(livreEmprunte);
	}
	
	/**
     * Méthode qui reprend 2 exceptions pour avertir si un lecteur est en retard.
     * @return imprime soit un message que le lecteur est en retard, 
     *         soit qu'il ne l'est pas.
     */
	public void relancerEmprunteurEnRetard() {
		for(LivreEmprunte livreEmprunte : getLivresEmpruntes()){
			LocalDate dateEmprunt = livreEmprunte.getDateEmprunt();
			Period p = Period.between(dateEmprunt, LocalDate.now());
			if (p.getDays() >= getNbJourEmprunt()){
				Lecteur lecteur = livreEmprunte.getLecteur();
				lecteur.setLivreEnRetard(true);
			}
		}
	}
	
	/**
     * Méthode imprime une liste de lecteurs ayant empruntés un livre.
     * @return ArrayList<Lecteur> listeLecteur est un liste de lecteurs.
     */
	public ArrayList<Lecteur> listerPersonnesAyantEmpruntesUnLivre() {
		ArrayList<Lecteur> listeLecteur = new ArrayList<Lecteur>();
		for(LivreEmprunte livreEmprunte : getLivresEmpruntes()){
			listeLecteur.add((livreEmprunte.getLecteur()));
		}
			
		return listeLecteur;
	}
	
	
	/**
     * Méthode imprime une liste de livres empruntés par lecteurs-étudiants.
     * @return ArrayList<Livre> listeLivres est un liste de livres.
     */
	public ArrayList<Livre> listerLivresEmpruntesParEtudiant() {
        ArrayList<Livre> listeLivres = new ArrayList<Livre>();
        for(LivreEmprunte livreEmprunte : getLivresEmpruntes()){
            Lecteur lecteur = livreEmprunte.getLecteur();
            if (lecteur.GetCategorie() == CategorieSocioProfessionelle.Etudiant) {
                listeLivres.add(livreEmprunte.getLivre());
            }
        }
        return listeLivres;
    }
	
	 /**
     * Méthode pour lister les livres empruntés. 
     * @return La liste des liresempruntés.
     */
	public ArrayList<Livre> listerLivresEmpruntes() {
        ArrayList<Livre> listeLivres = new ArrayList<Livre>();
        for(LivreEmprunte livreEmprunte: getLivresEmpruntes()) {
        	listeLivres.add(livreEmprunte.getLivre());
        }
        return listeLivres;
    }
	
	/**
     * Méthode pour trouver et lister les livres anglais. 
     * @return La liste des titre des livres anglais
     */
	public ArrayList<Livre>  listerLivresAnglais() {
		ArrayList<Livre> titresDesLivresAnglais = new ArrayList<Livre>();        
        for (Entry<Auteur, ArrayList<Livre>> paire : catalogue.entrySet()) {
            for (Livre livre: paire.getValue()) {
                if(livre.getLangue() == "en") {
                	titresDesLivresAnglais.add(livre);
                }
            }
        }
        return titresDesLivresAnglais;
    }
	
	/**
     * Méthode pour lister le nombre des livres empruntés pour un auteur. 
     * @param auteur L'auteur à chercher
     * @return Le nombre des livres empruntés pour cet auteur.
     */
	public int listerNbLivresEmpruntesPourUnAuteur(Auteur auteur) {
        int n=0;
        for(LivreEmprunte livreEmprunte: getLivresEmpruntes()) {
        	Livre livre = livreEmprunte.getLivre();
        	if (livre.getAuteur().equals(auteur)) {
                n++;
            }
        }
        return n;
    }
	
	/**
     * Méthode pour trouver des livres d'un thème
     * @param theme Le nom d'un thème
     * @return La liste de livres de ce thème.
     */
	public ArrayList<Livre> trouverLivreSurUnTheme(String theme) {
		ArrayList<Livre> listeLivres=new ArrayList<Livre>();
		for ( Entry<Auteur, ArrayList<Livre>> paire : catalogue.entrySet()) {
    		for (Livre livre : paire.getValue()) {
    			if (livre.getTheme() == theme) {
    				listeLivres.add(livre);
    			}
    		}
		}
		return listeLivres;
	}
	
	/**
     * Méthode pour vérifier si un lecteur est en retard, si oui, mettre le montant d'amende
     * @throws Exception pour renvoyer l'exception si le lecteur n'est pas en retard.
     * @see Exception
     */
	public void envoyerAmendeRetardaire() throws Exception {
		for(LivreEmprunte livreEmprunte : getLivresEmpruntes())
		{
			Lecteur lecteur = livreEmprunte.getLecteur();
			BibliothecaireVerificateur.VerifierLecteurARetard(lecteur);
								
			LocalDate dateEmprunt = livreEmprunte.getDateEmprunt();
			Period p = Period.between(dateEmprunt, LocalDate.now());
			Amende amende = new Amende(p.getDays());
			lecteur.setAmende(amende);
		}
	}
	
	/**
     * Méthode pour vérifier si un lecteur a une amende et si un lecteur a bien payé
     * ensuite encaisser l'amende et mettre l'amende en 0.
     * @param lecteur 
     * @throws Exception pour renvoyer si un lecteur n'a pas d'amende ou/et si un lecteur n'a pas bien payé le montant.
     * @see Exception
     */
	public void encaisserAmendeRetardaire(Lecteur lecteur) throws Exception {
		BibliothecaireVerificateur.VerifierLecteurAAmende(lecteur);
		Amende amende = lecteur.getAmende(); 
		double amendePayee = lecteur.paieAmende(amende);
		BibliothecaireVerificateur.VerifierLecteurAPayeBonMontantAmende(lecteur, amende, amendePayee);

		tresorerie += amendePayee;
		lecteur.setAmende(null);
		lecteur.setLivreEnRetard(false);
	}

	private HashMap<Auteur, ArrayList<Livre>> getCatalogue() {
		return catalogue;
	}
	
	private HashSet<LivreEmprunte> getLivresEmpruntes() {
		return livresEmpruntes;
	}	
	
	/**
     * Méthode qui initialise, fait une instance du catalogue.
     * @param HashMap<Auteur, ArrayList<Livre>> catalogue est un tableau de livres avec leurs auteurs.
     */
	public void initialiserCatalogue(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue = catalogue;
	}
	
	/**
     * Méthode qui renvoie un nombre de jours autorisés pour un emprunt.
     * @return int 15 est un nombre de jours autorisés.
     */
	public int getNbJourEmprunt() {
		return 15;
	}
	
	/**
     * Méthode vérifie si un livre est emprunté par un lecteur.
     * @param Lecteur lecteur est une instance de Lecteur à vérifier.
     * @return renvoie un livre pour un lecteur donné, null sinon.
     */
	public LivreEmprunte retourneLivreEmprunte(Lecteur lecteur){
		for(LivreEmprunte livreEmprunte : getLivresEmpruntes()){
			Lecteur lecteurCourant = livreEmprunte.getLecteur();
			if ( lecteurCourant.equals(lecteur) ) {
				return livreEmprunte;
			}
		}		
		return null;
	}
	
	/**
     * Méthode qui initialise le catalogue et les livres empruntés à zéro pour pouvoir faire des tests indépendamment.
     */
	public void supprimeTout() {
		getCatalogue().clear();
		getLivresEmpruntes().clear();
	}
}
