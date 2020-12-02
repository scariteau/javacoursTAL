package devoirs.anaelleAnnaQi.bibliotheque;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class Catalogue {

	
	protected void auteur(String auteur) throws IOException, ParserConfigurationException, SAXException{
		
		ArrayList<String> liste = new ArrayList<String>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse("C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\devoirs\\anaelleAnnaQi\\bibliotheque\\bibliotheque.xml");
		
		NodeList booklist = document.getElementsByTagName("livre");
		
		int cmt=0;	
		for (int i = 0; i<booklist.getLength(); i++) {
				Node book = booklist.item(i);
				NodeList childNodes = book.getChildNodes();
				
			for (int k=0; k<childNodes.getLength(); k++) {
				if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
					if (childNodes.item(k).getNodeName() == "auteur" && childNodes.item(k).getTextContent().contains(auteur)) {
						cmt++;
						liste.add(childNodes.item(1).getTextContent());				
					}	
				}	
			 }
		 }
						System.out.println("L'auteur est: " + auteur);
						System.out.println("Le nombre de livres de cet auteur: " + cmt);
						System.out.println("La liste des titres de ses oeuvres: "+ liste);
		
	}
	
	protected void auteur_oeuvre(String auteur, String oeuvre) throws IOException, ParserConfigurationException, SAXException{
		

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse("C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\devoirs\\anaelleAnnaQi\\bibliotheque\\bibliotheque.xml");
		NodeList booklist = document.getElementsByTagName("livre");
		

		for (int i = 0; i<booklist.getLength(); i++) {
				Node book = booklist.item(i);
				NodeList childNodes = book.getChildNodes();

				
			for (int k = 0; k<childNodes.getLength(); k++) {
				if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
					if (childNodes.item(k).getNodeName() == "titre" && childNodes.item(k).getTextContent().contains("Les MisÃ©rables") && childNodes.item(k+2).getNodeName() == "auteur" && childNodes.item(k+2).getTextContent().contains(auteur)) {
							System.out.println("L'auteur est: " + auteur);
							System.out.println("Le titre du livre est: " + oeuvre);						
							System.out.println("Le nombre de tomes: " + childNodes.item(5).getTextContent());
							System.out.println("La date de publication: " + childNodes.item(7).getTextContent());
							System.out.println("Le resume du livre: " + childNodes.item(9).getTextContent());
					}						
				}	
			 }	
		   }
		}
	
	protected void modifier(Document document) throws IOException, ParserConfigurationException, SAXException{
		
		NodeList booklist = document.getElementsByTagName("livre");
		Element emp = null;
		
		for (int i = 0; i<booklist.getLength(); i++) {
			emp = (Element) booklist.item(i);
			//modifier le nom 'auteur' en majuscule
			Node name = emp.getElementsByTagName("auteur").item(0).getFirstChild();
			name.setNodeValue(name.getNodeValue().toUpperCase());	
		}						
	}	
			 	
	
	protected void supprimer(Document document) throws IOException, ParserConfigurationException, SAXException{
		
		NodeList bibliotheque = document.getElementsByTagName("bibliotheque");
		Element emp = null;
		emp = (Element) bibliotheque.item(0);
		// On supprime le cinquieme livre, L'Assomoir.
		Node livreNode = emp.getElementsByTagName("livre").item(4);
		emp.removeChild(livreNode);
	}

	protected void ajouter(Document document) throws IOException, ParserConfigurationException, SAXException{
		
		NodeList bibliotheque = document.getElementsByTagName("bibliotheque");
		Element emp = null;

           emp = (Element) bibliotheque.item(0);
           
           Element livreElement = document.createElement("livre");
           
           Element titreElement = document.createElement("titre");
           titreElement.appendChild(document.createTextNode("Crime et Chatiment"));
           
           Element auteurElement = document.createElement("auteur");
           auteurElement.appendChild(document.createTextNode("Fiodor Dostoïevski"));
           
           Element tomeElement = document.createElement("nb_tomes");
           tomeElement.appendChild(document.createTextNode("2"));
           
           Element dateElement = document.createElement("date_publication");
           dateElement.appendChild(document.createTextNode("1866"));
           
           Element resumeElement = document.createElement("resume");
           resumeElement.appendChild(document.createTextNode("Ce roman raconte l'histoire d'une rédemption et soulève la question de la responsabilité des actes de chaque individu, sur fond de lutte entre Dieu, la morale et la théorie du Surhomme. "));
           
           emp.appendChild(livreElement);
           livreElement.appendChild(titreElement);
           livreElement.appendChild(auteurElement);
           livreElement.appendChild(tomeElement);
           livreElement.appendChild(dateElement);
           livreElement.appendChild(resumeElement);
	}
	
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse("C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\devoirs\\anaelleAnnaQi\\bibliotheque\\bibliotheque.xml");
		
		Catalogue catalogue = new Catalogue();
		
		try {
			System.out.println("Reponse 1 :");
			catalogue.auteur(args[0]);	
			
			System.out.println("Reponse 2 :");
			catalogue.auteur_oeuvre(args[0],args[1]);	
			
			System.out.println("Reponse 3 : modifier toutes les valeurs des noeuds 'auteur' en majuscule");
			catalogue.modifier(document);	
			
			System.out.println("Reponse 3 : supprimer le cinquieme livre, L'Assomoir");
			catalogue.supprimer(document);		
			
			System.out.println("Reponse 3 : ajouter un nouveau livre \"Crime et Chatiment\" ");
			catalogue.ajouter(document);		

			document.getDocumentElement().normalize();
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(document);
	        StreamResult result = new StreamResult(new File("C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\devoirs\\anaelleAnnaQi\\bibliotheque\\bibliotheque_update.xml"));
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(source, result);
	        System.out.println("Le fichier d'XML vient d'etre mise a jour, veuillez consulter bibliotheque_updated.xml.");

			
		} catch (FileNotFoundException exc) {
			System.out.println("Erreur d'ouverture du fichier");		
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}	
}

/*
- java bibliotheque.Catalogue "Victor Hugo" doit afficher l'auteur "Victor Hugo" le nombre de livre de cet auteur dans le catalogue 
  et la liste des titres de ses oeuvres.
- java bibliotheque.Catalogue "Victor Hugo" "Les Misérables" doit afficher l'auteur "Victor Hugo", 
  le titre du livre "Les Misérables" , le nombre de tomes de ce livre, la date de publication et le résumé du livre
- Comment modifieriez-vous votre code pour nous permettre de pouvoir ajouter, modifier ou enlever un livre?
	*/
