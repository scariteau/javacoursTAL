package devoirs.arthurFabienneMartin.bibliotheque;
import java.util.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.FileWriter;
import java.io.IOException;



public class Bibliotheque {

	
	List<Livre> bibliotheque;
	
	//Constructeur: file=le fichier.xml lu pour former la bibliothèque.
	Bibliotheque(String file){
		this.bibliotheque = formerBiblio(file);
	}
	
	
	
	//Lit le fichier xml et stocke les infos des noeuds dans des objets livres stockés dans une liste qui est renvoyée.
	private List<Livre> formerBiblio(String file) {
		
		List<Livre> bibliotheque = new ArrayList<Livre>();
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
		    SAXParser saxParser = factory.newSAXParser();
	
		    DefaultHandler handler = new DefaultHandler() {
		    	
			    boolean checkTitre = false;
			    boolean checkAuteur = false;
			    boolean checkTomes = false;
			    boolean checkDate = false;
			    boolean checkResume = false;
			    String titre;
			    String auteur;
			    String nb_tomes;
			    String date;
			    String resume;
			    
			    
			    public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
			        if (qName.equalsIgnoreCase("TITRE")) {
			            checkTitre = true;
			        }
			        if (qName.equalsIgnoreCase("AUTEUR")) {
			            checkAuteur = true;
			        }
			        if (qName.equalsIgnoreCase("NB_TOMES")) {
			            checkTomes = true;
			        }
			        if (qName.equalsIgnoreCase("DATE_PUBLICATION")) {
			            checkDate = true;
			        }
			        if (qName.equalsIgnoreCase("RESUME")) {
			            checkResume = true;
			        }
			    }
			    
			    public void endElement(String uri, String localName, String qName) throws SAXException {
			        if (qName.equalsIgnoreCase("LIVRE")) {
			            Livre livre = new Livre(titre, auteur, nb_tomes, date, resume);
			            bibliotheque.add(livre);
			        }
			        if (qName.equalsIgnoreCase("AUTEUR")) {
			        	nb_tomes = "1";
			        }
			    }
			    
			    public void characters(char ch[], int start, int length) throws SAXException {
			    	if (checkTitre) {
			    		titre = new String(ch, start, length);
			    		checkTitre = false;
			    	}
			    	if (checkAuteur) {
			    		auteur = new String(ch, start, length);
			    		checkAuteur = false;
			    	}
			    	if (checkTomes) {
			    		nb_tomes = new String(ch, start, length);
			    		checkTomes = false;
			    	}		
			    	if (checkDate) {
			    		date = new String(ch, start, length);
			    		checkDate = false;
			    	}
			    	if (checkResume) {
			    		resume = new String(ch, start, length);
			    		checkResume = false;
			    	}
			    }
			    
			    
			};//fin du handler.
			
			saxParser.parse(file, handler);
		}
			
		catch (Exception e) {
			e.printStackTrace();
		}
	    
		return bibliotheque;
	}//fin de formerBiblio;
	
	//ajoute un livre à la bibliothèque
	protected void ajouterLivre(String titre, String auteur, String nb_tome, String date, String resume) {
		Livre livre = new Livre(titre, auteur, nb_tome, date, resume);
		this.bibliotheque.add(livre);
		System.out.println("Le livre a correctement été ajouté à la bibliothèque.");
	}//fin de ajouterLivre
	
	//retire un livre de la bibliothèque d'après son titre.
	protected void retirerLivre(String monTitre, String monAuteur, String maDate) {
		List<Livre> new_biblio = new ArrayList<Livre>();
		for(Livre livre : this.bibliotheque) {
			if(!(livre.titre.equalsIgnoreCase(monTitre) && livre.auteur.equalsIgnoreCase(monAuteur))) {
				new_biblio.add(livre);
			}
		}
		if(new_biblio.size() == this.bibliotheque.size()) {
			System.out.println("Le livre que vous voulez retirer de la bibliothèque n'existe pas.");
		}
		else {
			System.out.println("Le livre a correctement été supprimé de la bibliothèque.");
		}
		this.bibliotheque = new_biblio;
	}//fin de retirerLivre
	
	//Renvoie une string avec les informations sur l'oeuvre recherchée.
	protected String catalogue(String monAuteur, String monTitre) {
		for(Livre livre : this.bibliotheque) {
			if(livre.titre.equalsIgnoreCase(monTitre) && livre.auteur.equalsIgnoreCase(monAuteur)) {
				String result = "Auteur : " + livre.auteur + "\nTitre : " + livre.titre + "\nNombre de tomes : " + livre.nb_tome + "\nDate de publication : " + livre.date + "\nRésumé : " + livre.resume;
				return result;
			}
		}
		return "Aucun livre correspondant à la requête n'a été trouvé dans le fichier.";
	}//fin de catalogue 2params
	
	//Renvoie le nombre d'oeuvres et le titre des oeuvres de l'auteur recherché.
	protected String catalogue(String monAuteur) {
		String oeuvres = "";
		int nombreOeuvres = 0;
		
		for(Livre livre : this.bibliotheque) {
			if(livre.auteur.equalsIgnoreCase(monAuteur)) {
				int tomes =  Integer.parseInt(livre.nb_tome);
				nombreOeuvres = nombreOeuvres + tomes;
				oeuvres = oeuvres + livre.titre + " (" + tomes + ")" + "\n";
			}
		}
		if(oeuvres.equals("")) {
			return "Aucun livre correspondant à la requête n'a été trouvé dans le fichier.";
		}
		else {
		return monAuteur + "\n" + "Nombre d'oeuvres : " + nombreOeuvres + "\n" + oeuvres;
		}
	}//fin de catalogue 1param
	
	//Modifie le fichier xml selon l'état actuel de la bibliotheque.
	protected void sauvegarderBiblio(String file) {
		try {
			FileWriter nouvBibli = new FileWriter(file, false);
			nouvBibli.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<bibliotheque>\n");
			for(Livre livre : this.bibliotheque) {
				nouvBibli.write("<livre>\n" + "\t<titre>" + livre.titre + "</titre>\n" + "\t<auteur>" + livre.auteur + "</auteur>\n");
				nouvBibli.write("\t<nb_tomes>" + livre.nb_tome + "</nb_tomes>\n" + "\t<date_publication>" + livre.date + "</date_publication>\n");
				nouvBibli.write("\t<resume>" + livre.resume + "</resume>\n</livre>\n");
			}
			nouvBibli.write("</bibliotheque>");
			nouvBibli.close();
		}
		catch (IOException e) {
			
			System.out.println("Impossible d'écrire dans le fichier " + file + ". Veuillez vérifier si le fichier n'est pas déjà ouvert dans une application.");
		
		}
	}
    
}//class Catalogue
