package devoirs.solveigCamilleJuliette.bibliotheque;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Catalogue {
	List<Livre> livres;
	
	public Catalogue(List<Livre> livres) {
		this.livres = livres;
	}
	
	public void addBook(Livre livre) {
		this.livres.add(livre);
	}
	
	public void removeBook(String auteur, String titre) {
		for (int i=0; i<this.livres.size(); i++) {
			Livre livre = this.livres.get(i);
			if (livre.auteur.contains(auteur) && livre.titre.contains(titre)) {
				this.livres.remove(i);
			}
		}
	}
	
	public void modifyBook(String auteur, String titre, String attribut, String new_value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		for (int i=0; i<this.livres.size(); i++) {
			Livre livre = this.livres.get(i);
			if (livre.auteur.contains(auteur) && livre.titre.contains(titre)) {
				Field fieldToModify = Livre.class.getField(attribut);
				fieldToModify.set(this.livres.get(i), new_value);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		List<String> infosLivres = FichierXML.getInfosLivres("C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\devoirs\\solveigCamilleJuliette\\bibliotheque\\bibliotheque.xml");
		
		List<Livre> livres = new ArrayList<Livre>();
		Catalogue catalogue = new Catalogue(livres);
		
		for (int i=0; i<infosLivres.size(); i++) {
			String infoslivre = infosLivres.get(i);
			if (infoslivre.contains("lang=")) {
				catalogue.livres.add(new LivreVO(infoslivre));
			}
			else {
				catalogue.livres.add(new Livre(infoslivre));
			}
		}
		
		if (args.length==1) {
			List<String> oeuvres = new ArrayList<String>();
			for (int i=0; i<catalogue.livres.size(); i++) {
				Livre livre = catalogue.livres.get(i);
				if (livre.auteur.contains(args[0])) {
					oeuvres.add(livre.titre);
				}
			}
			System.out.println(args[0] + " a écrit " + oeuvres.size() + " livres dans le catalogue : " + oeuvres);
		}
		else if (args.length==2) {
			for (int i=0; i<catalogue.livres.size(); i++) {
				Livre livre = catalogue.livres.get(i);
				if (livre.auteur.contains(args[0]) && livre.titre.contains(args[1])) {
					System.out.println(args[1] + " de " + args[0] + " :\nNombre de tomes : " + livre.nb_tomes + ", date de publication : " + livre.date_publication + ", résumé : " + livre.resume);
				}
			}
		}
		
	//ajout d'un livre qui n'est pas dans le fichier XML
	LivreVO livrevo = new LivreVO("American Gods", "Neil Gaiman", "2001", "En sortant de prison, Ombre apprend la mort de sa femme et de son meilleur ami dans un accident de voiture. À bord de l'avion qui le ramène chez lui, il se fait embaucher comme garde du corps par un étrange personnage dénommé Voyageur (Mr Wednesday en version originale). Ombre le rencontre un mercredi (Jour de Wotan, ou Odin en anglais). Celui-ci l'entraîne dans un long périple à travers les États-Unis. Ombre découvre bientôt que Voyageur n'est autre que l'ancien dieu nordique Odin qui tente de rallier à sa cause les autres anciens dieux et quelques personnages folkloriques afin de mener une guerre sans merci aux divinités plus récentes de l'Amérique que sont la voiture, internet, la télévision et les médias.", "en");
	catalogue.addBook(livrevo);
	
	//modification du résumé
	
	try {
		catalogue.modifyBook("Neil Gaiman", "American Gods", "resume", "En sortant de prison, Ombre apprend la mort de sa femme et de son meilleur ami dans un accident de voiture.");
	} catch (NoSuchFieldException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//suppression d'un livre
	catalogue.removeBook("Neil Gaiman", "American Gods");

	}
	
}