package devoirs.jianyingMeiSheherazade.bibliotheque;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.InputSource;

public class Catalogue {
    private static final String BIBLIOTHEQUE_XML = ".\\bibliotheque.xml";
    private static final String BIBLIOTHEQUE_XML_OUTPOUT = ".\\bibliothequeNew.xml";

    /**
     * @throws JAXBException
     * @throws IOException
     *
     */

    public static void main(String[] args) throws JAXBException, IOException {

        Bibliotheque myBibliotheque = new Bibliotheque();
        ArrayList<Livre>  myListLivres = convertXMLToObject();
        myBibliotheque.setBibliotheque(myListLivres);

        if (args.length == 1) {

            ArrayList<String> myListTitresArgs = new ArrayList<String>();
            myBibliotheque.donneListeLivresParAuteur(args[0], myListTitresArgs);
            myBibliotheque.printNbLivresEtListeLivres(args[0], myListTitresArgs);

        }else if (args.length == 2) {

            myBibliotheque.printNbTomesDatePubEtRes(args[0], args[1]);

        } else if (args.length == 0) {

            System.out.print("Voulez-vous modifier le catalogue (oui/non) : ");
            Scanner scanner = new Scanner(System.in);
            //le boucle while sert à réaliser plusieurs (éventuellement) changements dans un seul lancement
            while (true) {
                if ((scanner.nextLine()).equals("non"))
                    break;
                System.out.print("add/modify/delete : ");
                String choix = scanner.nextLine();

                switch (choix){
                    case "add":
                        addLivre(myBibliotheque, scanner);
                        break;
                    case "delete":
                        deleteLivre(myBibliotheque, scanner);
                        break;
                    case "modify":
                        modifyLivre(myBibliotheque, scanner);
                        break;
                }
                System.out.print("Voulez-vous continuer à modifier le catalogue (oui/non) : " );
            }

        }


        // creation du nouveau fichier XML
        convertObjectToXML(myBibliotheque.getBibliotheque());

        System.out.println("\n Fin d'exécution. \n");

    }

    /**
     * les méthodes pour ajouter/modifier/supprimer un livre objet
     */
    private static void addLivre(Bibliotheque myBibliotheque, Scanner scanner) {
        Livre livre = new Livre();
        System.out.print("Auteur: ");
        livre.setAuteur(scanner.nextLine());
        System.out.print("Titre: ");
        livre.setTitre(scanner.nextLine());
        System.out.print("Nombre de tomes: ");
        livre.setNb_tomes( Integer.parseInt(scanner.nextLine()));
        System.out.print("Date de publication: ");
        livre.setDate_publication( Integer.parseInt(scanner.nextLine()));
        System.out.print("Resume: ");
        livre.setResume(scanner.nextLine());
        myBibliotheque.addLivreAuCatalogue(livre);
    }

    private static void deleteLivre(Bibliotheque myBibliotheque, Scanner scanner) {
        System.out.print("Titre du livre à supprimer: ");
        String titre = scanner.nextLine();
        myBibliotheque.removeLivreByTitreDuCatalogue(titre);
    }

    private static void modifyLivre(Bibliotheque myBibliotheque, Scanner scanner) {
        System.out.print("Donnez le titre: ");
        String titre1 = scanner.nextLine();
        System.out.print("Donnez l'auteur du livre à modifier: ");
        String auteur = scanner.nextLine();
        System.out.print("L'élément à changer? (auteur/nbtomes/datepublication/resume): ");
        String element = scanner.nextLine();
        System.out.print("Quelle est votre modification: ");
        String modification = scanner.nextLine();
        myBibliotheque.modifierLivre( titre1,  auteur, element, modification);
    }

    /**
     * Les deux méthodes suivantes servent à faire la conversion entre fichier XML et Objet Bibliotheque
     */
    public static ArrayList<Livre> convertXMLToObject() {
        try {

            InputStream inputStream= new FileInputStream(BIBLIOTHEQUE_XML);
            Reader reader = new InputStreamReader(inputStream,"UTF-8");
            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");

            Bibliotheque bib  = new Bibliotheque();

            JAXBContext xmlJAXBContext = JAXBContext.newInstance(Bibliotheque.class);
            Unmarshaller xmlUnMarshaller= xmlJAXBContext.createUnmarshaller();
            bib = (Bibliotheque) xmlUnMarshaller.unmarshal(is);
            return bib.getBibliotheque();

        } catch (JAXBException | FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null ;
    }


    public static void convertObjectToXML( ArrayList<Livre>  mbibliotheque) throws IOException,JAXBException  {
        try {
            JAXBContext context; BufferedWriter writer = null;
            writer = new BufferedWriter(new FileWriter(BIBLIOTHEQUE_XML_OUTPOUT));
            context =  JAXBContext.newInstance(Bibliotheque.class);
            Marshaller m   =context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

            m.marshal(new Bibliotheque(mbibliotheque), writer);
            writer.close();

        } catch(JAXBException e) {
            e.printStackTrace();
        }

    }

}