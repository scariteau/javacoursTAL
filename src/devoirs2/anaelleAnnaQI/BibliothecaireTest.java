package devoirs2.anaelleAnnaQI;

/**
* Classe qui contient les tests de la classe bibliothecaire
* @author Anna Niskovskikh, Anaëlle Pierredon et Qi Wang
* @version 1.9
*/

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

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
		
		bibliothecaire.supprimeTout();
		Livre livre = AjouteLivreAuCatalogue("Léon Tolsloï", "Anna Karénine");
		Livre livre2 = AjouteLivreAuCatalogue("Léon Tolsloï", "Guerre et Paix");
		Livre livre3 = AjouteLivreAuCatalogue("Victor Hugo", "Les Misérables");
		Auteur auteurAchercher = new Auteur("Léon Tolsloï");
		ArrayList<Livre> listeLivresTolstoi = new ArrayList<Livre>();
		listeLivresTolstoi.add(livre);
		listeLivresTolstoi.add(livre2);
		
        //WHEN
        ArrayList<Livre> listeLivresPourUnAuteur = bibliothecaire.listerOeuvresAuteur(auteurAchercher);

        //THEN
        assertNotNull(listeLivresPourUnAuteur);
		Collections.sort(listeLivresPourUnAuteur, new CompareLivre());
		Collections.sort(listeLivresTolstoi, new CompareLivre());
		assertEquals(listeLivresPourUnAuteur, listeLivresTolstoi);
		System.out.println("testAfficherOeuvresAuteur : Les livres écrits par "+ auteurAchercher.getNom() + " sont "+ toStringLivre(listeLivresPourUnAuteur));
	}
	
	
	@Test
	void testAjouterLivre() {
		//GIVEN
		bibliothecaire.supprimeTout();
		Livre nouveauLivre = creerLivre("nomAuteur", "Un titre");
		int ancienMontant = bibliothecaire.getTresorerie();
		
		//WHEN
		bibliothecaire.ajouterLivre(nouveauLivre);
		
		//THEN
		assertNotNull(nouveauLivre.getAuteur());
		ArrayList<Livre> livresDidier = bibliothecaire.listerOeuvresAuteur(nouveauLivre.getAuteur());
		assertNotNull(livresDidier);
		assertEquals(livresDidier.size(), 1);
		assertEquals(livresDidier.get(0), nouveauLivre);
		assertTrue(bibliothecaire.getTresorerie() < ancienMontant);
		System.out.println("testAjouterLivre: " + nouveauLivre.getTitre() + " a été bien ajouté.");
	}

	@Test
	void testEnleverLivre() {
		//GIVEN
		bibliothecaire.supprimeTout();
		Livre ancienLivre = AjouteLivreAuCatalogue("nomAuteur", "Un titre");
		Livre ancienLivre2 = AjouteLivreAuCatalogue("nomAuteur", "Un titre2");
		
		//WHEN
		bibliothecaire.enleverLivre(ancienLivre);
		
		//THEN
		ArrayList<Livre> livres = bibliothecaire.listerOeuvresAuteur(ancienLivre.getAuteur());
		assertNotNull(livres);
		assertEquals(livres.size(), 1);
		assertEquals(livres.get(0), ancienLivre2);
		System.out.println("testEnleverLivre: " + ancienLivre.getTitre() + " a été bien enlevé.");
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
	 * 1pt: lisibilit� du code
	 * -1pt: m�thode avec plus de 3 arguments
	 * -1pt: classe de plus de 200 lignes
	 * -1pt: plus de 2 boucles for
	 * -1pt: trop d'utilisation de if
	 * 
	 */
	
	@Test
	void testPreterUnLivre() {
		//GIVEN	
		bibliothecaire.supprimeTout();
		Lecteur lecteur = new Travailleur("nom", "prenom");
		Lecteur lecteur2 = new Travailleur("nom2", "prenom2");
		Livre ancienLivre = AjouteLivreAuCatalogue("nomAuteur", "Un titre");
		Livre ancienLivre2 = AjouteLivreAuCatalogue("nomAuteur2", "Un titre2");
		Livre ancienLivre3 = AjouteLivreAuCatalogue("nomAuteur3", "Un titre3");
		Livre ancienLivre4 = creerLivre("nomAuteur4", "Un titre4");

		// WHEN
		boolean exceptionLeve = preterLivreTest(ancienLivre, lecteur);
		
		//THEN
		assertFalse(exceptionLeve);
		LivreEmprunte livreEmprunte =  bibliothecaire.retourneLivreEmprunte(lecteur);
		assertTrue(Objects.nonNull(livreEmprunte));
		Livre livreBiblio = livreEmprunte.getLivre();
		assertEquals(livreBiblio,ancienLivre);
		assertEquals(livreBiblio.getAuteur().getNom(), "nomAuteur");
		assertEquals(livreEmprunte.getDateEmprunt(),LocalDate.now());
		assertEquals(livreEmprunte.getNbJourEmprunt(), bibliothecaire.getNbJourEmprunt());
		assertEquals(livreEmprunte.getLecteur(),lecteur);
		System.out.println("testPreterUnLivre: " + ancienLivre.getTitre() + " a été bien prêté.");
			
		// test si le livre a déjà été emprunté
		// WHEN 
		boolean exceptionLeveLivreDejaEmp = preterLivreTest(ancienLivre, lecteur2);
		// THEN	
		assertTrue(exceptionLeveLivreDejaEmp);
		System.out.println("testPreterUnLivre Exception1: ATTENTION! " + ancienLivre.getTitre() + " a été déjà emprunté!!!");
		
		// test si le lecteur a déjà emprunté un livre 
		//WHEN
		boolean exceptionLeveLecteurDejaEmpr = preterLivreTest(ancienLivre2, lecteur);
		//THEN
		assertTrue(exceptionLeveLecteurDejaEmpr);
		System.out.println("testPreterUnLivre Exception2: ATTENTION! " + lecteur.getNom() + " a déjà emprunté un livre!");
		
		// test si le livre existe bien dans le catalogue
		//WHEN
		boolean exceptionLeveLivreDansCat = preterLivreTest(ancienLivre4, lecteur2) ;
		//THEN
		assertTrue(exceptionLeveLivreDansCat);
		System.out.println("testPreterUnLivre Exception3: ATTENTION! " + ancienLivre4.getTitre() + " n'existe pas dans le catalogue!");
	
	}
	
	@Test
	void testRelancerEmprunteurEnRetard() {
		// premier scenario : pas de retard
		//GIVEN
		bibliothecaire.supprimeTout();
		Livre livre = AjouteLivreAuCatalogue("nomAuteur", "Un titre");
		Lecteur lecteur = new Travailleur("nom", "prenom");		
		boolean exceptionLeve = preterLivreTest(livre, lecteur);

		//WHEN		
		bibliothecaire.relancerEmprunteurEnRetard();
		
		//THEN
		assertFalse(exceptionLeve);
		assertFalse(lecteur.aLivreEnRetard());
		System.out.println("testRelancerEmprunteurEnRetard var1: " + lecteur.getNom() + " n'est pas en retard.");
		
		// deuxième scenario : retard	
		//GIVEN
		LivreEmprunte livreEmprunte = bibliothecaire.retourneLivreEmprunte(lecteur);
		LocalDate localDate = livreEmprunte.getDateEmprunt();
		localDate= localDate.minusDays(16);
		livreEmprunte.setDateEmprunt(localDate);
		
		//WHEN		
		bibliothecaire.relancerEmprunteurEnRetard();
		
		//THEN
		assertTrue(lecteur.aLivreEnRetard());
		System.out.println("testRelancerEmprunteurEnRetard var2: " + lecteur.getNom() + " est EN RETARD!");
	}
	
	@Test
	void testListerPersonnesAyantEmpruntesUnLivre() {
		//GIVEN
		bibliothecaire.supprimeTout();
		Livre premierLivre = AjouteLivreAuCatalogue("nomAuteur", "Un titre présomptueux");
		Livre deuxiemeLivre = AjouteLivreAuCatalogue("nomAuteur2", "Un titre présomptueux2");
		
		Lecteur lecteur = new Travailleur("nom", "prenom");
		Lecteur lecteur2 = new Travailleur("nom2", "prenom2");
		ArrayList<Lecteur> listeLecteurs = new ArrayList<Lecteur>();
		listeLecteurs.add(lecteur);
		listeLecteurs.add(lecteur2);
		boolean exceptionLevee1 = preterLivreTest(premierLivre, lecteur);
		boolean exceptionLevee2 = preterLivreTest(deuxiemeLivre, lecteur2);
		
		//WHEN
		ArrayList<Lecteur> listeLecteurRetour = bibliothecaire.listerPersonnesAyantEmpruntesUnLivre();
		
		//THEN
		assertFalse(exceptionLevee1);
		assertFalse(exceptionLevee2);
		assertNotNull(listeLecteurs);
		assertEquals(listeLecteurs.size(),listeLecteurRetour.size());
		Collections.sort(listeLecteurs, new CompareLecteur());
		Collections.sort(listeLecteurRetour, new CompareLecteur());
		assertEquals(listeLecteurs,listeLecteurRetour);
		System.out.println("testListerPersonnesAyantEmpruntesUnLivre: Les lecteurs ayant emprunté un livre sont " + toStringLecteur(listeLecteurs));
	}
	
	@Test
    void testListerLivresEmpruntesParEtudiant() {
        //GIVEN
        bibliothecaire.supprimeTout();
        Livre premierLivre = AjouteLivreAuCatalogue("nomAuteur", "Un titre présomptueux");
        Livre deuxiemeLivre = AjouteLivreAuCatalogue("nomAuteur2", "Un titre présomptueux2");
        Livre troisiemeLivre = AjouteLivreAuCatalogue("nomAuteur3", "Un titre présomptueux3");
        Etudiant etudiant = new Etudiant("nom", "prenom");
        Etudiant etudiant2 = new Etudiant("nom2", "prenom2");
        Lecteur lecteur = new Travailleur("nom3", "prenom3");
        ArrayList<Livre> listeLivresEmpruntes = new ArrayList<Livre>();
        listeLivresEmpruntes.add(premierLivre);
        listeLivresEmpruntes.add(troisiemeLivre);
        boolean exceptionLevee1 = preterLivreTest(premierLivre, etudiant);
        boolean exceptionLevee2 = preterLivreTest(deuxiemeLivre, lecteur);
        boolean exceptionLevee3 = preterLivreTest(troisiemeLivre, etudiant2);

        //WHEN
        ArrayList<Livre> listeLivresEtudRetour = bibliothecaire.listerLivresEmpruntesParEtudiant();

        //THEN
        assertFalse(exceptionLevee1);
        assertFalse(exceptionLevee2);
        assertFalse(exceptionLevee3);
        assertNotNull(listeLivresEtudRetour);
        Collections.sort(listeLivresEmpruntes, new CompareLivre());
        Collections.sort(listeLivresEtudRetour, new CompareLivre());
        assertEquals(listeLivresEmpruntes, listeLivresEmpruntes);
        System.out.println(toStringLivre(listeLivresEtudRetour));
}
	
	@Test
    void testListerLivresEmpruntes() {
        //GIVEN
        bibliothecaire.supprimeTout();
		Livre livreUn = AjouteLivreAuCatalogue("auteurA", "titreA");
		Livre livreDeux = AjouteLivreAuCatalogue("auteurB", "titreB");
		ArrayList<Livre> listLivresRef = new ArrayList<Livre>();
		listLivresRef.add(livreUn);
		listLivresRef.add(livreDeux);
		Lecteur lecteur = new Travailleur("nom", "prenom");
		Lecteur lecteur2 = new Travailleur("nom2", "prenom2");
		boolean exceptionLevee1 = preterLivreTest(livreUn, lecteur);
		boolean exceptionLevee2 = preterLivreTest(livreDeux, lecteur2);

        //WHEN
        ArrayList<Livre> listeLivres = bibliothecaire.listerLivresEmpruntes();

        //THEN
        assertFalse(exceptionLevee1);
		assertFalse(exceptionLevee2);
        assertNotNull(listeLivres);
        Collections.sort(listLivresRef, new CompareLivre());
		Collections.sort(listeLivres, new CompareLivre());
		assertEquals(listLivresRef, listeLivres);
        System.out.println("testListerLivresEmpruntes: Les livres empruntés sont " + toStringLivre(listeLivres));

    }
	
	@Test
    void testListerLivresAnglais() {

        //GIVEN
		bibliothecaire.supprimeTout();
		Livre livreA = AjouteLivreAuCatalogue("James Joyce", "Ulysses");
        livreA.setLangue("en");
        Livre livreA1 = AjouteLivreAuCatalogue("James Joyce", "Un livre non anglais de Joyce");
        livreA1.setLangue("es");
        Livre livreB = AjouteLivreAuCatalogue("Gustav Flaubert", "Madame Bovary");
        livreB.setLangue("fr");
        Livre livreC = AjouteLivreAuCatalogue("George Orwell", "Animal Farm");
        livreC.setLangue("en");
        ArrayList<Livre> listeLivreAnglaisRef = new ArrayList<Livre>();
        listeLivreAnglaisRef.add(livreA);
        listeLivreAnglaisRef.add(livreC);

        //WHEN
       	ArrayList<Livre> listeLivreAnglais = bibliothecaire.listerLivresAnglais();
 
        //THEN
       	assertNotNull(listeLivreAnglais);
		Collections.sort(listeLivreAnglaisRef, new CompareLivre());
		Collections.sort(listeLivreAnglais, new CompareLivre());
		assertEquals(listeLivreAnglaisRef, listeLivreAnglais);
		System.out.println("testListerLivresAnglais: Les livres en ENG sont " + toStringLivre(listeLivreAnglais));
    }
	
	@Test
    void testListerNbLivresEmpruntesPourUnAuteur() {
        //GIVEN
		bibliothecaire.supprimeTout();
		Livre livre = AjouteLivreAuCatalogue("Léon Tolsloï", "Anna Karénine");
		Livre livre2 = AjouteLivreAuCatalogue("Léon Tolsloï", "Guerre et Paix");
		Livre livre3 = AjouteLivreAuCatalogue("Victor Hugo", "Les Misérables");
		Lecteur lecteur = new Travailleur("nom", "prenom");
		Lecteur lecteur2 = new Travailleur("nom2", "prenom2");
		Lecteur lecteur3 = new Etudiant("nom3", "prenom3");
		boolean exceptionLevee1 = preterLivreTest(livre, lecteur);
		boolean exceptionLevee2 = preterLivreTest(livre2, lecteur2);
		boolean exceptionLevee3 = preterLivreTest(livre3, lecteur3);
				
        //WHEN
        int nbLivreRetour = bibliothecaire.listerNbLivresEmpruntesPourUnAuteur(livre.getAuteur());

        //THEN
        assertFalse(exceptionLevee1);
		assertFalse(exceptionLevee2);
		assertFalse(exceptionLevee3);
		assertEquals(nbLivreRetour, 2);
		System.out.println("testListerNbLivresEmpruntesPourUnAuteur: Nombre de livres pour " + livre.getAuteur().getNom() + " est " + nbLivreRetour);
    }
	
	@Test
	void testTrouverLivreSurUnTheme() {
		//GIVEN	
		bibliothecaire.supprimeTout();
		String theme = "amour";
		Livre livreA = AjouteLivreAuCatalogue("Alexandre Pouchkine", "La fille du capitaine");
        livreA.setTheme(theme);
        Livre livreA1 = AjouteLivreAuCatalogue("BlahBlah", "Apprendre à coder en JAVA");
        livreA1.setTheme("programmation");
        Livre livreB = AjouteLivreAuCatalogue("Gustav Flaubert", "Madame Bovary");
        livreB.setTheme(theme);
        Livre livreC = AjouteLivreAuCatalogue("Jean-Claude Carrière", "Le Mahabharata");
        livreC.setTheme("vie");
        ArrayList<Livre> listeLivreUnThemeRef = new ArrayList<Livre>();
        listeLivreUnThemeRef.add(livreA);
        listeLivreUnThemeRef.add(livreB);
		
		//WHEN
		ArrayList<Livre> listeLivreUnThemeRetour = bibliothecaire.trouverLivreSurUnTheme(theme);
		
		//THEN
		assertNotNull(listeLivreUnThemeRetour);
		Collections.sort(listeLivreUnThemeRef, new CompareLivre());
		Collections.sort(listeLivreUnThemeRetour, new CompareLivre());
		assertEquals(listeLivreUnThemeRef, listeLivreUnThemeRetour);
		System.out.println("testTrouverLivreSurUnTheme: Les livres ayant le même theme sont " + toStringLivre(listeLivreUnThemeRetour));
	}
	
	@Test
	void testEnvoyerAmendeRetardaire() {
		//GIVEN
		bibliothecaire.supprimeTout();
		Livre livre = AjouteLivreAuCatalogue("nomAuteur", "Un titre pour ce test");
		Lecteur lecteur = new Travailleur("nom", "prenom");
		boolean exceptionLevee1 = preterLivreTest(livre, lecteur);
		
		LivreEmprunte livreEmprunte = bibliothecaire.retourneLivreEmprunte(lecteur);
		LocalDate localDate = livreEmprunte.getDateEmprunt().minusDays(16);
		livreEmprunte.setDateEmprunt(localDate);
		bibliothecaire.relancerEmprunteurEnRetard();
		
		//WHEN
		boolean exceptionLeveeSurEnvoyerAmendeRetardaire = false;
		try 
		{
			bibliothecaire.envoyerAmendeRetardaire();
		}
		catch(Exception ex)
		{
			exceptionLeveeSurEnvoyerAmendeRetardaire = true;
		}
		
		//THEN
		assertFalse(exceptionLevee1);
		assertFalse(exceptionLeveeSurEnvoyerAmendeRetardaire);
		assertTrue(lecteur.aLivreEnRetard());
		assertNotNull(lecteur.getAmende());
		assertTrue((lecteur.getAmende().getAmende()) == new Amende(Period.between(localDate, LocalDate.now()).getDays()).getAmende());
		System.out.println("testEnvoyerAmendeRetardaire: "+lecteur.getPrenom()+" "+lecteur.getNom()+" a reçu une amende de "+lecteur.getAmende().getAmende()+" euros.");
	}
	
	@Test
	void testEncaisserAmendeRetardaire() {
		//GIVEN
		bibliothecaire.supprimeTout();
		Livre livre = AjouteLivreAuCatalogue("nomAuteur", "Un titre");
		int ancienMontant = bibliothecaire.getTresorerie();
		Lecteur lecteur = new Travailleur("nom", "prenom");
		boolean exceptionLevee1 = preterLivreTest(livre, lecteur);
		
		LivreEmprunte livreEmprunte = bibliothecaire.retourneLivreEmprunte(lecteur);
		LocalDate localDate = livreEmprunte.getDateEmprunt().minusDays(16);
		livreEmprunte.setDateEmprunt(localDate);
		bibliothecaire.relancerEmprunteurEnRetard();
		
		boolean exceptionLeveeSurEnvoyerAmendeRetardaire = false;
		try
		{
			bibliothecaire.envoyerAmendeRetardaire();
		}
		catch(Exception e)
		{
			exceptionLeveeSurEnvoyerAmendeRetardaire = true;
		}
		
		//WHEN
		boolean exceptionLeveeSurEncaisserAmendeRetardaire = false;
		try
		{
		bibliothecaire.encaisserAmendeRetardaire(lecteur);
		}
		catch(Exception e)
		{
			exceptionLeveeSurEncaisserAmendeRetardaire = true;
		}
		
		//THEN
		assertFalse(exceptionLevee1);
		assertFalse(exceptionLeveeSurEncaisserAmendeRetardaire);
		assertFalse(exceptionLeveeSurEnvoyerAmendeRetardaire);
		assertNull(lecteur.getAmende());
		assertTrue(bibliothecaire.getTresorerie() > ancienMontant);
		System.out.println("testEncaisserAmendeRetardaire : "+lecteur.getPrenom()+" "+lecteur.getNom()+" a payé son a amende. La trésorerie est passée de "+ancienMontant+" à "+bibliothecaire.getTresorerie()+" euros.");
	}

	private boolean preterLivreTest(Livre ancienLivre, Lecteur lecteur) {
		
		boolean exceptionLeve=false;
		try
		{
			//WHEN
			bibliothecaire.preterLivre(ancienLivre, lecteur);			
		}
		catch(Exception e)
		{
			System.out.println(e);
			exceptionLeve = true;
		}
		
		return exceptionLeve;
	}
	
	private Livre creerLivre(String nomAuteur, String titreLivre){
		Auteur auteur=new Auteur(nomAuteur);
		return new Livre(auteur, titreLivre);
	}
	
	private Livre AjouteLivreAuCatalogue(String nomAuteur, String titreLivre){
		Livre livre = creerLivre(nomAuteur, titreLivre);
		bibliothecaire.ajouterLivre(livre);
		return livre;
	}
	
	String toStringLivre(ArrayList<Livre> listeLivre)
	{
		String retour = "[";
		for (Livre livre : listeLivre) {
			if (retour != "[") {
				retour +=", ";
			}
			retour += livre.getTitre();
		}
		retour+="]";
		return retour;
	}
	
	String toStringLecteur(ArrayList<Lecteur> listeLecteur)
	{
		String retour = "[";
		for (Lecteur lecteur : listeLecteur) {
			if (retour != "[") {
				retour +=", ";
			}
			retour += lecteur.getNom();
		}
		retour+="]";
		return retour;
	}
	
	String toStringEtudiant(ArrayList<Etudiant> listeEtudiant)
	{
		String retour = "[";
		for (Lecteur etudiant : listeEtudiant) {
			if (retour != "[") {
				retour +=", ";
			}
			retour += etudiant.getNom();
		}
		retour+="]";
		return retour;
	}
}
