package traitementTextes.bibliotheque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Bibliothecaire {

	private HashMap<Auteur, ArrayList<Livre>> catalogue;
	
	public Bibliothecaire(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue=catalogue;
		
	}

	public void ajouterLivre(Livre nouveauLivre) {
		if (Objects.nonNull(getCatalogue().get(nouveauLivre.getAuteur()))) {
			getCatalogue().get(nouveauLivre.getAuteur()).add(nouveauLivre);
		} else {
			ArrayList<Livre> livres = new ArrayList<>();
			livres.add(nouveauLivre);
			getCatalogue().put(nouveauLivre.getAuteur(), livres);
		}

	}

	public String listerOeuvresAuteur(Auteur auteur) {
		ArrayList<Livre> livres= catalogue.get(auteur);
		String titreDesLivres="";
		for (Livre livre : livres) {
			titreDesLivres+=livre.getTitre()+ "\n";
		}
		return "L'auteur "+auteur.getNom()+" a ecrit "+livres.size() +" livres:\n"+ titreDesLivres;
	}
	
	public void enleverLivre(Livre ancienLivre) {

	}
	
	public void preterLivre(Livre ancienLivre) {

	}
	

	public HashMap<Auteur, ArrayList<Livre>> getCatalogue() {
		return catalogue;
	}

	public void initialiserCatalogue(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue = catalogue;
	}

}
