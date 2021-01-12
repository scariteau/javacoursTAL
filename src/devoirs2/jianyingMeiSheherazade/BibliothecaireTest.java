package devoirs2.jianyingMeiSheherazade;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BibliothecaireTest {

    private Bibliothecaire bibliothecaire;

    @BeforeEach
    void setUp() throws Exception {

        HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();
        bibliothecaire=new Bibliothecaire(catalogue);
    }

    @AfterEach
    void tearDown() throws Exception {
    }


    @Test
    void testAfficherOeuvresAuteur() {

        //GIVEN
        Auteur auteur=new Auteur("nomAuteur");
        ArrayList<Livre> livres=new ArrayList<>();
        String titre = "un titre presomptueux";
        Livre premierLivre=new Livre(auteur, titre);
        livres.add(premierLivre);
        bibliothecaire.getCatalogue().put(auteur, livres);

        //WHEN
        String listeOeuvres = bibliothecaire.listerOeuvresAuteur(auteur);

        //THEN
        assertNotNull(listeOeuvres);
        assertTrue(listeOeuvres.contains(titre));
        System.out.println("Résualt de la fonction AfficherOeuvresAuteur()");
        System.out.println(listeOeuvres);
    }

    /**
     * Cette méthode va tester la méthode ajouterLivre de la classe Bibliothecaire
     */
    @Test
    void testAjouterLivre() {
//		GIVEN
        Auteur auteur = new Auteur("Didier");
        String titre = "Retour à Reims";
        Livre ajoutLivre = new Livre(auteur,titre);
//		WHEN
        bibliothecaire.ajouterLivre(ajoutLivre);
//		THEN
        assertNotNull(ajoutLivre.getAuteur());
        ArrayList<Livre> livresDidier = bibliothecaire.getCatalogue().get(auteur);
        assertNotNull(livresDidier);
        assertTrue(livresDidier.contains(ajoutLivre));
    }

    @Test
    void testEnleverLivre() {
//		GIVEN
        Livre livreDidier = creerLivre("Didier","Retour à Reims");
        bibliothecaire.ajouterLivre(livreDidier);
        assertEquals(bibliothecaire.getCatalogue().get(livreDidier.getAuteur()).size(), 1);
//		WHEN
        bibliothecaire.enleverLivre(livreDidier);
//		THEN
        assertFalse(bibliothecaire.getCatalogue().get(livreDidier.getAuteur()).contains(livreDidier));
        assertEquals(bibliothecaire.getCatalogue().get(livreDidier.getAuteur()).size(), 0);
    }

    private Livre creerLivre(String nonAuteur, String nomLivre){
        Auteur auteur = new Auteur(nonAuteur);
        String titre = nomLivre;
        Livre livreDidier = new Livre(auteur,titre);
        return livreDidier;
    }

    @Test
    void testEnleverLivrePlusieur() {

//		GIVEN
        Auteur auteurA = new Auteur("Didier");
        String titreA = "Retour à ReimsA";
        Livre livreDidierA = new Livre(auteurA,titreA);
        String titreB = "Retour à ReimsB";
        Livre livreDidierB = new Livre(auteurA,titreB);
        livreDidierB.setAnneePublication(2020);
        String titreC = "Retour à ReimsC";
        Livre livreDidierC = new Livre(auteurA,titreA);

        bibliothecaire.ajouterLivre(livreDidierA);
        bibliothecaire.ajouterLivre(livreDidierB);
        assertEquals(bibliothecaire.getCatalogue().get(auteurA).size(), 2);

//		WHEN
        bibliothecaire.enleverLivre(livreDidierC);

//		THEN
        assertFalse(bibliothecaire.getCatalogue().get(auteurA).contains(livreDidierA));
        assertEquals(bibliothecaire.getCatalogue().get(auteurA).size(), 1);

    }




    /*
     *
     * Partie concernee par le devoir
     * Voici le d�compte des notes:
     * 1pts par test OK==>10pts
     * 2 pour pour la mis en place de l'heritage
     * 1pt pour la javadoc
     * 1pt pour le polymorphisme et la surchage
     * 1pt pour la reutilisation de l'existant
     * 1pt pour la gestion des exceptions
     * 1pt pour la creation d'exceptions
     * 1pt: utilisation de l'encapsulation
     * 1pt: utilisation de git
     * 1pt: lisibilité du code
     * -1pt: méthode avec plus de 3 arguments
     * -1pt: classe de plus de 200 lignes
     * -1pt: plus de 2 boucles for
     * -1pt: trop d'utilisation de if
     *
     */

    @Test
    void testPreterUnLivre() {
//		given
        Auteur auteur = new Auteur("Didier");
        String titre = "Retour à Reims";
        Livre livreDidier = new Livre(auteur,titre);
        bibliothecaire.ajouterLivre(livreDidier);
//		when
        bibliothecaire.preterLivre(livreDidier);
//		then
        assertTrue(livreDidier.isPreter());
    }
    @Test
    void testRelancerEmprunteurEnRetard() throws ParseException {
//		given
        Emprunteur p = new Emprunteur("Marie");
//		ajoute 1er livre
        Auteur auteurJ = new Auteur("J.K");
        String titreJ = "Harry Potter";
        Livre livreJK = new Livre(auteurJ,titreJ);
//		ajoute 2eme livre
        Auteur auteur = new Auteur("Didier");
        String titre = "Retour à Reims";
        Livre livreDidier = new Livre(auteur,titre);
//		ajoute dans la cataloque
        bibliothecaire.ajouterLivre(livreJK);
        bibliothecaire.ajouterLivre(livreDidier);
        bibliothecaire.preterLivre(livreJK,p,"15-05-2021");
        bibliothecaire.preterLivre(livreDidier,p,"15-12-2020");
//		when
        bibliothecaire.RelancerEmprunteurEnRetard();
//		then
        assertTrue(livreDidier.isHorsDelai());
        assertFalse(livreJK.isHorsDelai());
        assertEquals(livreDidier.getEmprunteur(),p);
    }

    @Test
    void testListerPersonnesAyantEmpruntesUnLivre() {
        //		given
        Emprunteur tom = new Emprunteur("Tom");
        Emprunteur paul = new Emprunteur("paul");
//		ajoute livre
        Auteur auteurJ = new Auteur("J.K");
        String titreJ = "Harry Potter";
        Livre livreJK = new Livre(auteurJ,titreJ);
        Auteur auteruDidier = new Auteur("Didier");
        String titreR = "Retour à Reims";
        Livre livreDidier = new Livre(auteruDidier,titreR);
        bibliothecaire.ajouterLivre(livreJK);
        bibliothecaire.ajouterLivre(livreDidier);
        bibliothecaire.preterLivre(livreJK,tom);
        bibliothecaire.preterLivre(livreDidier,tom);
//		when
        ArrayList<Emprunteur> emprunteurs = bibliothecaire.listerPersonnesAyantEmpruntesUnLivre();
//		then
        ArrayList<Emprunteur> etudiantTest = new ArrayList<Emprunteur>();
        etudiantTest.add(tom);
        assertTrue(emprunteurs.equals(etudiantTest));
        System.out.println("Les personnes ayant empruntes un livre : ");
        for (Emprunteur p : emprunteurs){
            System.out.println(p.getNom());
        }
    }

    @Test
    void testListerLivresEmpruntesParEtudiant() {
        //		given
        Etudiant etudiantLin = new Etudiant("Lin");
        Emprunteur paul = new Emprunteur("Bin");
//		ajoute 1er livre
        Auteur auteurJ = new Auteur("J.K");
        String titreJ = "Harry Potter";
        Livre livreJK = new Livre(auteurJ,titreJ);
//		ajoute 2eme livre
        Auteur auteur = new Auteur("Didier");
        String titre = "Retour à Reims";
        Livre livreDidier = new Livre(auteur,titre);
//		ajoute 3eme livre
        Auteur auteurM = new Auteur("Manon");
        String titreJava = "Java";
        Livre livreJava = new Livre(auteurM,titreJava);

        bibliothecaire.ajouterLivre(livreJK);
        bibliothecaire.ajouterLivre(livreDidier);
        bibliothecaire.ajouterLivre(livreJava);
        bibliothecaire.preterLivre(livreJK,etudiantLin);
        bibliothecaire.preterLivre(livreDidier,paul);

//		when
        ArrayList<Livre> livresEmpruntesParEtudiant = bibliothecaire.listerLivresEmpruntesParEtudiant();
//		then
        ArrayList<Livre> livresTest = new ArrayList<Livre>();
        livresTest.add(livreJK);
        assertTrue(livresEmpruntesParEtudiant.equals(livresTest));
        System.out.println("livres empruntes par etudiant : ");
        for (Livre l : livresEmpruntesParEtudiant){
            System.out.println(l.getTitre());
        }
    }

    @Test
    void testListerLivresEmpruntes() {
        //		given
//		ajoute 1er livre
        Auteur auteurJ = new Auteur("J.K");
        String titreJ = "Harry Potter";
        Livre livreJK = new Livre(auteurJ,titreJ);
//		ajoute 2eme livre
        Auteur auteur = new Auteur("Didier");
        String titre = "Retour à Reims";
        Livre livreDidier = new Livre(auteur,titre);
        bibliothecaire.ajouterLivre(livreJK);
        bibliothecaire.ajouterLivre(livreDidier);
        bibliothecaire.preterLivre(livreJK);
//		when
        ArrayList<Livre> livres = bibliothecaire.ListerLivresEmpruntes();

//		then
        ArrayList<Livre> livresTest = new ArrayList<Livre>();
        livresTest.add(livreJK);
        assertTrue(livresTest.equals(livres));
        System.out.println("Le nombre de livre empruntés : ");
        for (Livre l : livres){
            System.out.print(l.getTitre());
        }
    }

    @Test
    void testListerLivresAnglais() {
        //		given
//		ajoute 1er livre
        Auteur auteurJ = new Auteur("J.K");
        String titreJ = "Harry Potter";
        Livre livreJK = new Livre(auteurJ,titreJ);
        livreJK.setLangue("anglais");
//		ajoute 2eme livre
        Auteur auteur = new Auteur("Didier");
        String titre = "Retour à Reims";
        Livre livreDidier = new Livre(auteur,titre);
        livreDidier.setLangue("français");
        bibliothecaire.ajouterLivre(livreJK);
        bibliothecaire.ajouterLivre(livreDidier);
        bibliothecaire.preterLivre(livreJK);
//		when
        ArrayList<Livre> livreLangue = bibliothecaire.ListerLivresAnglais();
//		then
        ArrayList<Livre> livresTest = new ArrayList<Livre>();
        livresTest.add(livreJK);
        assertTrue(livreLangue.equals(livresTest));
    }

    @Test
    void testListerNbLivresEmpruntesPourUnAuteur() {
//		given
//		ajoute 1er livre
        Auteur auteurJ = new Auteur("J.K");
        String titreJ = "Harry Potter";
        Livre livreJK = new Livre(auteurJ,titreJ);
//		ajoute 2eme livre
        Auteur auteur = new Auteur("Didier");
        String titreA = "Retour à Reims";
        String titreB = "Michel Foucault";
        Livre livreDidierA = new Livre(auteur,titreA);
        Livre livreDidierB = new Livre(auteur,titreB);
//		ajouter dans bibliothecaire
        bibliothecaire.ajouterLivre(livreJK);
        bibliothecaire.ajouterLivre(livreDidierA);
        bibliothecaire.ajouterLivre(livreDidierB);
        bibliothecaire.preterLivre(livreJK);
        bibliothecaire.preterLivre(livreDidierA);
//		when
        int NbLivresEmpruntesPourUnAuteur = bibliothecaire.listerNbLivresEmpruntesPourUnAuteur(auteur);
//		then
        assertEquals(NbLivresEmpruntesPourUnAuteur,1);
    }

    @Test
    void testTrouverLivreSurUnTheme() {
//		given
//		ajoute 1er et 2eme livres
        Auteur auteurJ = new Auteur("J.K");
        String titreJ = "Harry Potter1";
        String titreK = "Harry Potter2";
        Livre livreJKOne = new Livre(auteurJ,titreJ);
        Livre livreJKTwo = new Livre(auteurJ,titreK);
        livreJKOne.setTheme("magie");
        livreJKTwo.setTheme("Magie");
//		ajoute 3eme livre
        Auteur auteur = new Auteur("Didier");
        String titreA = "Retour à Reims";
        Livre livreDidierA = new Livre(auteur,titreA);
        livreDidierA.setTheme("vie");
//		ajouter dans bibliothecaire
        bibliothecaire.ajouterLivre(livreJKOne);
        bibliothecaire.ajouterLivre(livreJKTwo);
        bibliothecaire.ajouterLivre(livreDidierA);
//		when
        ArrayList<Livre> LivreSurUnTheme = bibliothecaire.trouverLivreSurUnTheme("magie");
//		then
        ArrayList<Livre> livresTest = new ArrayList<Livre>();
        livresTest.add(livreJKOne);
        livresTest.add(livreJKTwo);
        assertTrue(LivreSurUnTheme.equals(livresTest));

    }

    @Test
    void testEnvoyerAmendeRetardaire() throws ParseException {
 //		given
        Emprunteur Hum = new Emprunteur("Hum");
//		ajoute 1er et 2eme livres
        Auteur auteurJ = new Auteur("J.K");
        String titreJ = "Harry Potter1";
        String titreK = "Harry Potter2";
        Livre livreJKOne = new Livre(auteurJ,titreJ);
        Livre livreJKTwo = new Livre(auteurJ,titreK);
//		ajouter dans bibliothecaire
        bibliothecaire.ajouterLivre(livreJKOne);
        bibliothecaire.ajouterLivre(livreJKTwo);
        bibliothecaire.preterLivre(livreJKOne,Hum,"15-05-2020");
        bibliothecaire.preterLivre(livreJKTwo,Hum,"15-05-2020");
//		when
        bibliothecaire.EnvoyerAmendeRetardaire();
        ArrayList<Livre> livres = bibliothecaire.getCatalogue().get(auteurJ);
        for (Livre l :livres ){
            if (l.equals(livreJKOne)){
                assertEquals(l.getAmende(),2.0);
            }
        }

    }

    @Test
    void testEncaisserAmendeRetardaire() throws ParseException {
        //		given
        Emprunteur tom = new Emprunteur("Tom");
//		ajoute 1er et 2eme livres
        Auteur auteurJ = new Auteur("J.K");
        String titreJ = "Harry Potter1";
        String titreK = "Harry Potter2";
        Livre livreJKOne = new Livre(auteurJ,titreJ);
        Livre livreJKTwo = new Livre(auteurJ,titreK);
//		ajouter dans bibliothecaire
        bibliothecaire.ajouterLivre(livreJKOne);
        bibliothecaire.ajouterLivre(livreJKTwo);
        bibliothecaire.preterLivre(livreJKOne, tom, "15-10-2020");
        bibliothecaire.preterLivre(livreJKTwo, tom, "15-05-2021");
//		when
        bibliothecaire.encaisserAmendeRetardaire(livreJKOne);

//		then
        ArrayList<Livre> livres = bibliothecaire.getCatalogue().get(auteurJ);
        for (Livre l :livres ){
            if (l.equals(livreJKOne)){
                assertEquals(l.getAmende(),0.0);
            }
        }

    }

    @Test
    void testExceptionCatalogue() {
        /// pour test
        try{
            //        given
            Emprunteur tom = new Emprunteur("Tom");
            Emprunteur paul = new Emprunteur("paul");

            //        ajoute livre
            Auteur auteurJ = new Auteur("J.K");
            String titreJ = "Harry Potter";
            Livre livreJK = new Livre(auteurJ,titreJ);
            bibliothecaire.ajouterLivre(livreJK);
            bibliothecaire.preterLivre(livreJK,tom);
            Auteur auteurKG = new Auteur("Khalil Gibran");
            String titreKG = "Le Prophete";
            Livre livreKG = new Livre(auteurKG,titreKG);
            bibliothecaire.getCatalogue().get(livreKG.getAuteur());

            //        when
            ArrayList<Emprunteur> emprunteurs = bibliothecaire.listerPersonnesAyantEmpruntesUnLivre();
            //        then
            assertNotNull(bibliothecaire.getCatalogue().get(livreJK.getAuteur()));
            assertTrue(bibliothecaire.getCatalogue().get(livreJK.getAuteur()).contains(livreJK));
//            assertArrayEquals(livreJK.getListeEmprunteurs().toArray(),emprunteurs.toArray());


            throw new ExceptionCataloque();
        }
        catch(ExceptionCataloque e){
            System.out.println(e.getMessage());
        }
    }
}
