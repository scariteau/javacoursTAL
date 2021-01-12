package devoirs2.arthurFabienneMartin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Bibliothecaire {

	private HashMap<Auteur, ArrayList<Livre>> catalogue;
	
	HashMap<Client, ArrayList<livrePrete>> reg = new HashMap<>();
	private Registre registre = new Registre(reg);
	
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
		if ((Objects.nonNull(getCatalogue().get(ancienLivre.getAuteur()))) && (getCatalogue().get(ancienLivre.getAuteur())).contains(ancienLivre)) {
			getCatalogue().get(ancienLivre.getAuteur()).remove(ancienLivre);
		}
		if (getCatalogue().get(ancienLivre.getAuteur()).isEmpty()) {
			getCatalogue().remove(ancienLivre.getAuteur());
		}
	}
	
	/**
	 * Ajoute à l'attribut emprunts de l'objet Registre le livre indiqué en paramètre pour le client indiqué en paramètre. Si le client a déjà emprunté un livre, ajoute le livre à l'array correspondant aux livres empruntés par le client.
	 * @param client Le Client à qui le livre est prêté.
	 * @param ancienLivre Le livre prêté.
	 * @param etiquette L'étiqutte comportant des informations sur le livre prêté.
	 */
	public void preterLivre(Client client, Livre ancienLivre, Etiquette etiquette) {
		livrePrete nouveauLivre = new livrePrete(ancienLivre, etiquette);
		if (Objects.nonNull(getRegistre().getEmprunts().get(client))) {
			getRegistre().getEmprunts().get(client).add(nouveauLivre);
		}
		else {
			ArrayList<livrePrete> livres = new ArrayList<>();
			livres.add(nouveauLivre);
			getRegistre().getEmprunts().put(client, livres);
		}
	}
	
	public HashMap<Auteur, ArrayList<Livre>> getCatalogue() {
		return catalogue;
	}

	public void initialiserCatalogue(HashMap<Auteur, ArrayList<Livre>> catalogue) {
		this.catalogue = catalogue;
	}
	
	public Registre getRegistre() {
		return registre;
	}
}