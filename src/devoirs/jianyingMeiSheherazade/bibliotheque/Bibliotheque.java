package devoirs.jianyingMeiSheherazade.bibliotheque;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="bibliotheque")
public class Bibliotheque {

    @XmlElement(name="livre" ,type = Livre.class)
    private ArrayList<Livre> livres;

    public Bibliotheque() {
    }

    public Bibliotheque(ArrayList<Livre> livres) {
        this.livres =livres; }

    /**
     * @return the bibliotheque
     */
    public ArrayList<Livre> getBibliotheque() {
        return livres;
    }

    /**
     * @param bibliotheque the bibliotheque to set
     */
    public void setBibliotheque(ArrayList<Livre> mbibliotheque) {
        this.livres = mbibliotheque;
    }

    /**
     * Méthode pour obtenir la liste des oeuvres de l'auteur donné
     */
    public void donneListeLivresParAuteur(String nomAuteur, ArrayList<String> myListTitres) {

        if (!this.livres.isEmpty())
        {
            for (Livre livre : this.livres) {

                if (livre.getAuteur().equals(nomAuteur)) {
                    myListTitres.add(livre.getTitre());
                }
            }
        }
    }

    /**
     * Méthode pour imprimer la réponse de la première question
     */
    public void printNbLivresEtListeLivres(String nomAuteur, ArrayList<String> myListTitres) {

        System.out.println("L'auteur: " + nomAuteur + " a " + myListTitres.size()
                + " livre(s) dans ce catalogue. Voici la liste des titres de ses oeuvres: \n");

        if (!myListTitres.isEmpty()) {
            for (String titre : myListTitres) {
                System.out.println(" - " + titre + "\n");
            }
        }

    }

    /**
     * méthode pour imprimer tous les infos de livre donnée
     */
    public void printNbTomesDatePubEtRes(String nomAuteur, String titreLivre) {

        if (!this.livres.isEmpty()) {  //pour assurer que la liste livres n'est pas vide
            for (Livre livre : this.livres) {
                if (livre.getTitre().equals(titreLivre) && livre.getAuteur().equals(nomAuteur)) {
                    System.out.println(nomAuteur + " est l'auteur du livre \"" + titreLivre + "\""
                            + ". Celui-ci est composé de " + livre.getNb_tomes() + " tome(s). Il a été publié en "
                            + livre.getDate_publication() + ". Voici son résumé:\n");
                    System.out.println(livre.getResume());
                    break;
                }
            }

        }
    }

    /**
     * rajouter un livre dans le catalogue
     */
    public void addLivreAuCatalogue(Livre livreAAjouter) {
        this.livres.add(livreAAjouter);
    }

    /**
     * Méthode pour supprimer le livre
     * en argument: le nom du livre
     */
    public void removeLivreByTitreDuCatalogue(String livreAenlever) {

        if (!this.livres.isEmpty()) {
            for (Livre livre : this.livres) {
                if (livre.getTitre().equals(livreAenlever)) {
                    livres.remove(livre);
                    System.out.println("\n On a enlevé " + livre.getTitre() + " du catalogue. \n");
                    break;
                }
            }
        }
    }

    /**
     * Méthode pour modifier le livre existant dans le catalogue
     * Dans paramètres:
     * le titre et l'auteur du livre pour trouver le noeud à modifier
     * le noeud fils (element) à modifier
     * la nouvelle valeur (modification)
     */
    public void modifierLivre(String titre, String auteur,String element, String modification) {
        if (!this.livres.isEmpty()) {
            for (Livre livre : this.livres) {

                // trouver le bon livre à modifier selon le titre et son auteur
                if ((livre.getTitre().equals(titre)) && (livre.getAuteur().equals(auteur))) {
                    if (element.equals("titre")) {
                        livre.setAuteur(modification);
                    } else if (element.toLowerCase().equals("auteur")) {
                        livre.setAuteur(modification);
                    } else if (element.toLowerCase().equals("nbtomes")) {
                        livre.setNb_tomes( Integer.parseInt(modification));
                    } else if (element.toLowerCase().equals("datepublication")) {
                        livre.setDate_publication(Integer.parseInt(modification));
                    } else if (element.toLowerCase().equals("resume")) {
                        livre.setResume(modification);
                    } else {
                        System.out.println("Element '" + element+"' not found.");
                        break;
                    }
                }
            }
        }
    }

}