package traitementTextes.bibliotheque;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import traitementTextes.Serialisateur;
import traitementTextes.bibliotheque.catalogue.Auteur;
import traitementTextes.bibliotheque.catalogue.Livre;

public class Archivage {


	
	public void archiverCatalogue(HashMap<Auteur, ArrayList<Livre>> catalogue, String pathFichierArchive) {
		
		Serialisateur.serialiseObject(pathFichierArchive, catalogue);
		
	}
	/**
	 * @param pathFichierXml
	 * @param catalogue
	 */
	public void initialiserCatalogueAvecXml(String pathFichierXml, HashMap<Auteur, ArrayList<Livre>> catalogue) {

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
				String nomAuteur;
				String nb_tomes;
				String date;
				String resume;

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
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
						ajouterLivre(catalogue);
					}
					if (qName.equalsIgnoreCase("AUTEUR")) {
						nb_tomes = "1";
					}
				}

				private void ajouterLivre(HashMap<Auteur, ArrayList<Livre>> catalogue) {
					Auteur auteur = new Auteur(nomAuteur);
					Livre livre = new Livre(auteur, titre);
					livre.setAnneePublication(Integer.valueOf(date));
					livre.setResume(resume);
					livre.setNbTomes(Integer.valueOf(nb_tomes));
					catalogue.putIfAbsent(auteur, new ArrayList<>());
					catalogue.get(auteur).add(livre);
				}

				public void characters(char ch[], int start, int length) throws SAXException {
					if (checkTitre) {
						titre = new String(ch, start, length);
						checkTitre = false;
					}
					if (checkAuteur) {
						nomAuteur = new String(ch, start, length);
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

			};

			saxParser.parse(pathFichierXml, handler);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
