package devoirs2.chinatsuLaraXinyi;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class Bibliothecaire {

	private HashMap<Auteur, ArrayList<Livre>> catalogue;

	public Bibliothecaire(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue=catalogue;	
	}

    /**
     * Ajouter les livres
     * @param nouveauLivre
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
     * Enlever un livre
     * @param ancienLivre
     */
	public void enleverLivre(Livre ancienLivre) {
		if ((Objects.nonNull(getCatalogue().get(ancienLivre.getAuteur()))) ) {
			getCatalogue().get(ancienLivre.getAuteur()).remove(ancienLivre);
		}
	}

    /**
     * Lister les œuvres d'un auteur.
     * @param auteur
     * @return ArrayList<String>
     */
    public ArrayList<String> listerOeuvresAuteur(Auteur auteur) {
        ArrayList<Livre> livres= catalogue.get(auteur);
        ArrayList<String> livresAuteur= new ArrayList<>();
        String titreDesLivres="";
        for (Livre livre : livres) {
            titreDesLivres=livre.getTitre();
            livresAuteur.add(titreDesLivres);
        }
        return livresAuteur;
    }
	
	/**
     * Lister les livres Anglais.
     * @param 
     * @return ArrayList<Livre>
     */
	public ArrayList<Livre> listerLivresAnglais() {
		ArrayList<Livre> livresAnglais= new ArrayList<>();
	    for (Map.Entry me : catalogue.entrySet()) {
	    	ArrayList<Livre> livres= catalogue.get(me.getKey());
	    	for (Livre livre : livres) {
	    		if (livre.getLangue().equals("Anglais")){
	    			//titreDesLivresAnglais+=livre.getTitre()+ "\n";
	    			livresAnglais.add(livre);
	    		}
			}
	    } 
	    return livresAnglais;
	}
	
	/**
     * Trouver le premier livre sur un thème.
     * @param theme
     * @return Livre
     */
    public Livre trouverLivreSurUnTheme(String theme) {
        ArrayList<Livre> livresTheme= new ArrayList<>();
        for (Map.Entry me : catalogue.entrySet()) {
            ArrayList<Livre> livres= catalogue.get(me.getKey());
            for (Livre livret : livres) {
                if (livret.getTheme().equals(theme)){
                    livresTheme.add(livret);
                }
            }
        }
        return livresTheme.get(0);
    }        	
    
	/* --------------- Getters and Setters ----------------- */

	public HashMap<Auteur, ArrayList<Livre>> getCatalogue() {
		return catalogue;
	}

	public void initialiserCatalogue(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue = catalogue;
	}
}
 