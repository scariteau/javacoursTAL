package devoirs.chinatsuLaraXinyi.bibliotheque;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.Scanner;

public class Catalogue {
    
    public static void main(String[] args) {
        
        Catalogue catalogue = new Catalogue();
        
        //Changer le path si besoin
        String filePath = "C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\devoirs\\chinatsuLaraXinyi\\bibliotheque\\bibliotheque.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        
        try {
            
            dBuilder = dbFactory.newDocumentBuilder();
            // parse xml file and load into document
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            int count = args.length;
            if (count == 0) {
                
                @SuppressWarnings("resource")
                Scanner demande = new Scanner(System.in);  // Create a Scanner object
                System.out.println("***************Catalogue***************");
                System.out.println("Qu'est ce que vous voulez faire?");
                System.out.println("1 : Ajouter un livre");
                System.out.println("2 : Supprimer un livre");
                System.out.println("3 : Modifier un livre");

                int choix = demande.nextInt();  // Read user input
                System.out.println("Choix : " + choix);  // Output user input
                
                if (choix == 1) { 
                    @SuppressWarnings("resource")
                    Scanner demandeAdd = new Scanner(System.in);  // Create a Scanner object
                    System.out.println("On ajoute un livre.");
                    System.out.println("Titre:");
                    String addTitre = demandeAdd.nextLine();  // Read user input
                    System.out.println("Auteur:");
                    String addAuteur = demandeAdd.nextLine();  // Read user input
                    System.out.println("Nombres de tomes:");
                    String addNbTomes = demandeAdd.nextLine();  // Read user input
                    System.out.println("Date de publication:");
                    String addDatePublication = demandeAdd.nextLine();  // Read user input
                    System.out.println("Resumé:");
                    String addResume = demandeAdd.nextLine();  // Read user input
                    // ajouter un livre
                    Livre livre = new Livre(addTitre, addAuteur, addNbTomes, addDatePublication, addResume);
                    catalogue.addElement(doc, livre);
                }
                
                else if (choix == 2) {
                    @SuppressWarnings("resource")
                    Scanner demandeDelete = new Scanner(System.in);  // Create a Scanner object
                    System.out.println("On supprime un livre par le titre.");
                    System.out.println("Titre:");
                    String delTitre = demandeDelete.nextLine();  // Read user input
                    delTitre = '"' + delTitre + '"';
                    // enlever un livre
                    Livre livre = new Livre(delTitre);
                    catalogue.deleteElement(doc, livre);
                }
                
                else if (choix == 3) {
                    @SuppressWarnings("resource")
                    Scanner demandeModify = new Scanner(System.in);  // Create a Scanner object
                    System.out.println("On modifie le titre d'un livre en mettant en majuscule.");
                    System.out.println("Titre:");
                    String modTitre = demandeModify.nextLine();  // Read user input
                    // modifier un livre
                    Livre livre = new Livre(modTitre);
                    catalogue.modifyElementValue(doc, livre);
                }
                // write the updated document to file
                catalogue.writeXMLFile(doc);
            }
                
            else if (count == 1) {
                // afficher auteur, nombres de livres de cet auteur dans le fichier, liste des titres
                catalogue.afficheElementValue(doc, args[0]);
            }
                
            else if (count == 2) {
                // afficher auteur, titre, nombre de tomes, date de publication, resumé
                catalogue.afficheElementValue(doc, args[0], args[1]);
            }

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
    
    private void writeXMLFile(Document doc)
    throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException, XPathExpressionException {
        doc.getDocumentElement().normalize();
        XPathExpression xpath = XPathFactory.newInstance().newXPath().compile("//text()[normalize-space(.) = '']");
        NodeList blankTextNodes = (NodeList) xpath.evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < blankTextNodes.getLength(); i++) {
             blankTextNodes.item(i).getParentNode().removeChild(blankTextNodes.item(i));
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        //Changer le path si besoin
        StreamResult result = new StreamResult(new File("C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\devoirs\\chinatsuLaraXinyi\\bibliotheque\\bibliotheque_updated.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        //System.out.println("XML file updated successfully");  
    }

    /**
     * Ajouter un livre.
     * @param doc, livre
     */
    private void addElement(Document doc, Livre livre) {    
        // get root element
        Element racine = doc.getDocumentElement();
        
        Element elivre=doc.createElement("livre");
        Element etitre=doc.createElement("titre");
        Element eauteur=doc.createElement("auteur");
        Element enb_tomes=doc.createElement("nb_tomes");
        Element edate_publication=doc.createElement("date_publication");
        Element eresume=doc.createElement("resume");
        
        etitre.appendChild(doc.createTextNode(livre.getTitre()));
        eauteur.appendChild(doc.createTextNode(livre.getAuteur()));
        enb_tomes.appendChild(doc.createTextNode(livre.getNbTomes()));
        edate_publication.appendChild(doc.createTextNode(livre.getDatePublication()));
        eresume.appendChild(doc.createTextNode(livre.getResume()));
        
        elivre.appendChild(etitre);
        elivre.appendChild(eauteur);
        elivre.appendChild(enb_tomes);
        elivre.appendChild(edate_publication);
        elivre.appendChild(eresume);
        
        racine.appendChild(elivre);
        
        System.out.println("*****Livre ajouté dans le fichier*****");
    }

    /**
     * Enlever un livre
     * @param doc, livre
     * @throws XPathExpressionException 
     */
    private void deleteElement(Document doc, Livre livre) throws XPathExpressionException {     
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        String path = "//bibliotheque/livre[titre/text()=" + livre.getTitre() + "]";
        XPathExpression expression = xpath.compile(path);

        Node titreNode = (Node) expression.evaluate(doc, XPathConstants.NODE);
        if (titreNode != null) {
            titreNode.getParentNode().removeChild(titreNode);
            System.out.println("*****Livre supprimé dans le fichier*****");
        }
        else {
            System.out.println("*****Aucun livre avec ce titre dans le fichier*****");
        }
            
    }
    
    /**
     * Modifier les détails d'un livre (mettre le titre en majuscule)
     * @param doc, livre
     */
    private void modifyElementValue(Document doc, Livre livre) {
        NodeList livres = doc.getElementsByTagName("livre");
        Element elem = null;
        boolean flag = false;

        for (int i = 0; i < livres.getLength(); i++) {
            elem = (Element) livres.item(i);
            Node titreNode = elem.getElementsByTagName("titre").item(0).getFirstChild();
            
            if (titreNode.getNodeValue().equals(livre.getTitre())) {
                titreNode.setNodeValue(titreNode.getNodeValue().toUpperCase());
                flag = true;
                System.out.println("*****Livre modifié dans le fichier*****");
            }  
        }
        if (flag == false) {
            System.out.println("*****Aucun livre avec ce titre dans le fichier*****");
        }
    }

    /**
     * Afficher les détails d'un livre (auteur, nombre de livres de l'auteur dans le fichier, titres des livres)
     * @param doc, args[0] = auteur
     */
    private void afficheElementValue(Document doc, String auteur) {
        NodeList livres = doc.getElementsByTagName("livre");
        Element elem = null;
        ArrayList<String> al = new ArrayList<String>(); 

        int nbOccurence = 0;
        for (int i = 0; i < livres.getLength(); i++) {
            elem = (Element) livres.item(i);
            Node titreNode = elem.getElementsByTagName("titre").item(0).getFirstChild();
            Node auteurNode = elem.getElementsByTagName("auteur").item(0).getFirstChild();

            if (auteurNode.getNodeValue().equals(auteur)) {
                nbOccurence++;
                al.add(titreNode.getNodeValue());
            }  
        }
        System.out.println(auteur + " " + nbOccurence + " " + al);
    }
    
    /**
     * Afficher les détails d'un livre (titre, auteur, nbTomes, datePublication, resume)
     * @param doc, args[0] = auteur, args[1] = titre
     */
    private void afficheElementValue(Document doc, String auteur, String titre) {
        NodeList livres = doc.getElementsByTagName("livre");
        Element elem = null;
        boolean flag = false;
        try {
        for (int i = 0; i < livres.getLength(); i++) {
            
                elem = (Element) livres.item(i);
                Node titreNode = elem.getElementsByTagName("titre").item(0).getFirstChild();
                Node auteurNode = elem.getElementsByTagName("auteur").item(0).getFirstChild();
                Node nbTomesNode = elem.getElementsByTagName("nb_tomes").item(0).getFirstChild();
                Node datePublicationNode = elem.getElementsByTagName("date_publication").item(0).getFirstChild();
                Node resumeNode = elem.getElementsByTagName("resume").item(0).getFirstChild();

                if (auteurNode.getNodeValue().equals(auteur) && titreNode.getNodeValue().equals(titre)) {
                    System.out.println(auteurNode.getNodeValue() + "\n" + titreNode.getNodeValue() + "\n" + nbTomesNode.getNodeValue() + "\n" + datePublicationNode.getNodeValue() + "\n" + resumeNode.getNodeValue()); 
                    flag = true;
                }
            }
        }
        catch (NullPointerException n){
            for (int i = 0; i < livres.getLength(); i++) {
                elem = (Element) livres.item(i);
                Node titreNode = elem.getElementsByTagName("titre").item(0).getFirstChild();
                Node auteurNode = elem.getElementsByTagName("auteur").item(0).getFirstChild();
                Node datePublicationNode = elem.getElementsByTagName("date_publication").item(0).getFirstChild();
                Node resumeNode = elem.getElementsByTagName("resume").item(0).getFirstChild();

                if (auteurNode.getNodeValue().equals(auteur) && titreNode.getNodeValue().equals(titre)) {
                    System.out.println(auteurNode.getNodeValue() + "\n" + titreNode.getNodeValue() + "\n" + datePublicationNode.getNodeValue() + "\n" + resumeNode.getNodeValue()); 
                    flag = true;
                }
            }
        }
        if (flag == false) {
            System.out.println("*****Aucun livre avec cet auteur dans le fichier*****");
        }
    }
}