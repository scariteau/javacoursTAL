package devoirs2.chinatsuLaraXinyi;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestSuite;

class BibliothecaireTest {
	
	private Bibliothecaire bibliothecaire;
	private BibliothecaireEmprunt bibliothecaireEmprunt;

	@BeforeEach
	void setUp() throws Exception {
		
		HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();		
		bibliothecaire=new Bibliothecaire(catalogue);
		
		ArrayList <Personne> listePersonnes = new ArrayList <Personne>();
		ArrayList <Emprunt> listeEmprunts= new ArrayList <Emprunt>();
		bibliothecaireEmprunt = new BibliothecaireEmprunt(catalogue, listePersonnes, listeEmprunts);
	}
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAjouterLivre() {
		//GIVEN
		Auteur auteur = new Auteur("Didier");
		String titre = "Retour à Reims";
		Livre ajoutLivre = new Livre(auteur,titre);

		//WHEN
		bibliothecaire.ajouterLivre(ajoutLivre);
		
		//THEN
		ArrayList<Livre> livresDidier = bibliothecaire.getCatalogue().get(auteur);
		assertNotNull(livresDidier);
		assertTrue(livresDidier.contains(ajoutLivre));
	}
	
	@Test
	void testListerOeuvresAuteur() {
		
		//GIVEN
		Auteur auteur=new Auteur("nomAuteur");
		ArrayList<Livre> livres=new ArrayList<>();
		String titre = "un titre presomptueux";
		Livre premierLivre=new Livre(auteur, titre);
		livres.add(premierLivre);
		String titre2 = "un deuxième titre";
		Livre deuxiemeLivre=new Livre(auteur, titre2);
		livres.add(deuxiemeLivre);
		bibliothecaire.getCatalogue().put(auteur, livres);
		
		//WHEN
		ArrayList<String> livresAuteur= new ArrayList<>();
		livresAuteur = bibliothecaire.listerOeuvresAuteur(auteur);
		
		//THEN
		assertNotNull(livresAuteur);
		assertTrue(livresAuteur.contains(titre));
		System.out.println("*--- testListerOeuvresAuteur ---* ");
		System.out.println(livresAuteur);
	}
	
	@Test
	void testEnleverLivre() {

		//GIVEN
		Livre livreDidier = creerLivre("Didier","Retour à Reims");

		bibliothecaire.ajouterLivre(livreDidier);
		assertEquals(bibliothecaire.getCatalogue().get(livreDidier.getAuteur()).size(), 1);

		//WHEN
		bibliothecaire.enleverLivre(livreDidier);

		//THEN
		assertFalse(bibliothecaire.getCatalogue().get(livreDidier.getAuteur()).contains(livreDidier));
		assertEquals(bibliothecaire.getCatalogue().get(livreDidier.getAuteur()).size(), 0);
	}

	@Test
	void testEnleverLivrePlusieur() {
		//GIVEN
		Auteur auteurA = new Auteur("Didier");
		String titreA = "Retour à ReimsA";
		Livre livreDidierA = new Livre(auteurA,titreA);
		//Auteur auteurB = new Auteur("Didier");
		String titreB = "Retour à ReimsB";
		Livre livreDidierB = new Livre(auteurA,titreB);
		livreDidierB.setAnneePublication(2020);
		//Auteur auteurC = new Auteur("Didier");
		Livre livreDidierC = new Livre(auteurA,titreA);
		

		bibliothecaire.ajouterLivre(livreDidierA);
		bibliothecaire.ajouterLivre(livreDidierB);
		//bibliothecaire.ajouterLivre(livreDidierC);
		assertEquals(bibliothecaire.getCatalogue().get(auteurA).size(), 2);

		//WHEN
		//bibliothecaire.enleverLivre(livreDidierA);
		bibliothecaire.enleverLivre(livreDidierC);

		//THEN
		//assertNotNull(enleverLivre.getAuteur())
		//assertTrue(bibliothecaire.getCatalogue().get(auteurA).contains(livreDidierA));
		//assertEquals(bibliothecaire.getCatalogue().get(auteurA).size(), 2);
	}	
	
	
	// *----- A partir d'ici c'set notre projet.------* 
	/**
     * Creer un Livre
     * @param nomAuteur, nomLivre
     * @return Livre
     */
	private Livre creerLivre(String nomAuteur, String nomLivre){
		Auteur auteur = new Auteur(nomAuteur);
		String titre = nomLivre;
		Livre livreDidier = new Livre(auteur,titre);
		return livreDidier;
	}
	
	/**
     * Creer un Livre Anglais
     * @param nomAuteur, nomLivre, nomLangue
     * @return Livre
     */
	private Livre creerLivreAnglais(String nomAuteur, String nomLivre, String nomLangue){
		Auteur auteur = new Auteur(nomAuteur);
		String titre = nomLivre;
		String langue = nomLangue;
		Livre livreAnglais = new Livre(auteur,titre,langue);
		return livreAnglais;
	}
	
	/**
     * Verifier Format de Date
     * @param data
     * @return boolean
     */
	private boolean verifieFormatDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(date.equals(sdf.format(sdf.parse(date)))){
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	@Test
	void testPreterUnLivre() throws ParseException {
		//GIVEN
		Livre livreDidier1 = creerLivre("Didier","Retour à Reims");
		Livre livreDidier2 = creerLivre("Didier","Retour à Paris");
		bibliothecaire.ajouterLivre(livreDidier1);
		bibliothecaire.ajouterLivre(livreDidier2);
		Personne personne = new Personne("123", "Dupont", "Paul");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateRetourne1 = ("0000-00-00");
		String datePret = "2020-12-20";
		String dateDelais = "2020-12-20";
		
		if((verifieFormatDate(datePret)) && (verifieFormatDate(dateDelais))){
			Date datePret1 = sdf.parse(datePret);
			Date dateDelais1 = sdf.parse(dateDelais);
			Emprunt e1 = new Emprunt(1,livreDidier1, personne, datePret1,dateRetourne1, dateDelais1,0);
			Emprunt e2 = new Emprunt(2,livreDidier2, personne, datePret1, dateRetourne1, dateDelais1,0);
			//WHEN
			bibliothecaireEmprunt.preterUnLivre(e1);
			bibliothecaireEmprunt.preterUnLivre(e2);
			
		}else {
			System.out.println("Le format de date doit être 'YYYY-MM-DD'.");
		}
		
		//THEN
		System.out.println("*--- testPreterUnLivre ---* ");
		System.out.println(bibliothecaireEmprunt.getListeEmprunts().get(0).getPersonne().getNom());
		System.out.println(bibliothecaireEmprunt.getListeEmprunts().get(1).getLivre().getTitre());
		System.out.println(bibliothecaireEmprunt.getListePersonnes().get(0).getPrenom());
		assertNotNull(bibliothecaireEmprunt.getListeEmprunts());
		assertEquals(bibliothecaireEmprunt.getListeEmprunts().size(), 2);
		
		
		
		//THEN
		
		assertEquals(bibliothecaireEmprunt.getListeEmprunts().get(0).getPersonne().getNom(), "Dupont");
		assertEquals(bibliothecaireEmprunt.getListeEmprunts().get(1).getLivre().getTitre(), "Retour à Paris");
		assertEquals(bibliothecaireEmprunt.getListePersonnes().get(0).getPrenom(), "Paul");
		assertNotNull(bibliothecaireEmprunt.getListeEmprunts());
		assertEquals(bibliothecaireEmprunt.getListeEmprunts().size(), 2);
		
	}
	
	
	@Test
	void testRelancerEmprunteurEnRetard() throws ParseException {
	//GIVEN
		Livre livreDidier1 = creerLivre("Didier","Retour à Reims");
		Livre livreDidier2 = creerLivre("Didier","Retour à Paris");
		Livre livreDidier3 = creerLivre("Didier","Retour à Bordeaux");
				
		bibliothecaire.ajouterLivre(livreDidier1);
		bibliothecaire.ajouterLivre(livreDidier2);
		bibliothecaire.ajouterLivre(livreDidier3);
		Personne personneA = new Personne("123", "Nouvel", "Paul");
		Personne personneB = new Personne("456", "Nouvel", "Clement","678");
				
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calobj1 = Calendar.getInstance();
		calobj1.add(Calendar.DAY_OF_MONTH, -5);
		Calendar calobj2 = Calendar.getInstance();
		calobj2.add(Calendar.DAY_OF_MONTH, 3);
		String datePret = "2020-12-20";
		String dateDelaisAvant = sdf.format(calobj1.getTime());
		String dateDelaisApres = sdf.format(calobj2.getTime());
		String dateRetourne1 = ("0000-00-00");
		String dateRetourne2 = ("2020-12-26");
		
		if((verifieFormatDate(datePret)) && verifieFormatDate(dateDelaisAvant) && verifieFormatDate(dateDelaisApres)) {
			Date datePret1 = sdf.parse(datePret);
			Date dateDelais1 = sdf.parse(dateDelaisAvant);
			Date dateDelais2 = sdf.parse(dateDelaisApres);
			Emprunt e1 = new Emprunt(1,livreDidier1, personneA, datePret1, dateRetourne1,dateDelais1,0 );
			Emprunt e2 = new Emprunt(2,livreDidier2, personneA, datePret1, dateRetourne2, dateDelais1,0);
			Emprunt e3 = new Emprunt(3,livreDidier3, personneB, datePret1, dateRetourne1, dateDelais2,0);
			bibliothecaireEmprunt.preterUnLivre(e1);
			bibliothecaireEmprunt.preterUnLivre(e2);
			bibliothecaireEmprunt.preterUnLivre(e3);
			
			//WHEN
			//bibliothecaireEmprunt.relancerEmprunteurEnRetard();
			
			//THEN
			System.out.println("*--- testRelancerEmprunteurEnRetard ---* ");
			bibliothecaireEmprunt.relancerEmprunteurEnRetard();
			Calendar calobj = Calendar.getInstance();
			assertTrue(sdf.format(e1.getDateDelais()).compareTo(sdf.format(calobj.getTime())) < 0);
			assertTrue(sdf.format(e2.getDateDelais()).compareTo(sdf.format(calobj.getTime())) < 0);
			assertFalse(sdf.format(e3.getDateDelais()).compareTo(sdf.format(calobj.getTime())) < 0);
		
		}else {
			System.out.println("Le format de date doit être 'YYYY-MM-DD'.");
		}
	}
	
	@Test
	void testListerPersonnesAyantEmpruntesUnLivre() throws ParseException {
		//GIVEN
		Livre livreDidier1 = creerLivre("Didier","Retour à Reims");
		Livre livreDidier2 = creerLivre("Didier","Retour à Paris");
		Livre livreDidier3 = creerLivre("Didier","Retour à Paris");
		
		bibliothecaire.ajouterLivre(livreDidier1);
		bibliothecaire.ajouterLivre(livreDidier2);
		Personne personneA = new Personne("123", "Nouvel", "Paul");
		Personne personneB = new Personne("456", "Nouvel", "Clement");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datePret = "2020-12-20";
		String dateDelais = "2020-12-29";
		String dateRetourne1 = ("0000-00-00");
		
		if((verifieFormatDate(datePret)) && verifieFormatDate(dateDelais)) {
			Date datePret1 = sdf.parse(datePret);
			Date dateDelais1 = sdf.parse(dateDelais);
			
			Emprunt e1 = new Emprunt(1,livreDidier1, personneA, datePret1, dateRetourne1,dateDelais1,0);
			Emprunt e2 = new Emprunt(2,livreDidier2, personneA, datePret1, dateRetourne1, dateDelais1,0);
			Emprunt e3 = new Emprunt(3,livreDidier3, personneB, datePret1, dateRetourne1, dateDelais1,0);
			bibliothecaireEmprunt.preterUnLivre(e1);
			bibliothecaireEmprunt.preterUnLivre(e2);
			bibliothecaireEmprunt.preterUnLivre(e3);
		}else {
			System.out.println("Le format de date doit être 'YYYY-MM-DD'.");
		}
		
		//WHEN
		ArrayList<Personne> personnes = bibliothecaireEmprunt.listerPersonnesAyantEmpruntesUnLivre();
		
		//THEN
		assertNotNull(personnes);
		assertEquals(personnes.size(), 2);
		System.out.println("*--- testListerPersonnesAyantEmpruntesUnLivre ---* ");
		for (Personne personne: personnes) {
			System.out.println(personne.getNom() + "\t" + personne.getPrenom());
		}
	}
	
	@Test
	void testListerLivresEmpruntesParEtudiant() throws ParseException {
		//GIVEN
		Livre livreDidier1 = creerLivre("Didier","Retour à Reims");
		Livre livreDidier2 = creerLivre("Didier","Retour à Paris");
		Livre livreDidier3 = creerLivre("Didier","Retour à Lyon");
		Personne personneA = new Personne("123", "Dupont", "Paul");
		Personne personneB = new Personne("789", "Daube", "Clément","123");
		Personne personneC = new Personne("234", "Nouvel", "Kata","457");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datePret = "2020-12-20";
		String dateDelais = "2020-12-29";
		String dateRetourne1 = ("0000-00-00");
		
		if((verifieFormatDate(datePret)) && verifieFormatDate(dateDelais)) {
			Date datePret1 = sdf.parse(datePret);
			Date dateDelais1 = sdf.parse(dateDelais);
			
			Emprunt e1 = new Emprunt(1,livreDidier1, personneA, datePret1, dateRetourne1, dateDelais1,0);
			Emprunt e2 = new Emprunt(2,livreDidier2, personneB, datePret1, dateRetourne1, dateDelais1,0);
			Emprunt e3 = new Emprunt(3,livreDidier3, personneC, datePret1, dateRetourne1, dateDelais1,0);
			bibliothecaire.ajouterLivre(livreDidier1);
			bibliothecaire.ajouterLivre(livreDidier2);
			bibliothecaire.ajouterLivre(livreDidier3);
			bibliothecaireEmprunt.preterUnLivre(e1);
			bibliothecaireEmprunt.preterUnLivre(e2);
			bibliothecaireEmprunt.preterUnLivre(e3);
		}else {
			System.out.println("Le format de date doit être 'YYYY-MM-DD'.");
		}
		
		//WHEN
		ArrayList<Livre> livres = bibliothecaireEmprunt.listerLivresEmpruntesParEtudiant();
		
		//THEN
		assertNotNull(livres);
		assertEquals(livres.size(), 2);
		System.out.println("*--- testListerLivresEmpruntesParEtudiant ---* ");
		for (Livre livre: livres) {
			System.out.println(livre.getTitre());
		}
	}
	
	@Test
	void testListerLivresEmpruntes() throws ParseException {
		//GIVEN
		Livre livreDidier1 = creerLivre("Didier","Retour à Reims");
		Livre livreDidier2 = creerLivre("Didier","Retour à Paris");
		Livre livreDidier3 = creerLivre("Didier","Retour à Lyon");
		bibliothecaire.ajouterLivre(livreDidier1);
		bibliothecaire.ajouterLivre(livreDidier2);
		bibliothecaire.ajouterLivre(livreDidier3);
		Personne personne = new Personne("123", "Dupont", "Paul");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datePret = "2020-12-20";
		String dateDelais = "2020-12-29";
		String dateRetourne1 = ("0000-00-00");
		
		if((verifieFormatDate(datePret)) && verifieFormatDate(dateDelais)) {
			Date datePret1 = sdf.parse(datePret);
			Date dateDelais1 = sdf.parse(dateDelais);
			
			Emprunt e1 = new Emprunt(1,livreDidier1, personne,datePret1, dateRetourne1, dateDelais1,0);
			Emprunt e2 = new Emprunt(2,livreDidier3, personne, datePret1, dateRetourne1, dateDelais1,0);
			bibliothecaireEmprunt.preterUnLivre(e1);
			bibliothecaireEmprunt.preterUnLivre(e2);
		}else {
			System.out.println("Le format de date doit être 'YYYY-MM-DD'.");
		}
		
		//WHEN
		ArrayList<Livre> livres = bibliothecaireEmprunt.listerLivresEmpruntes();
		
		//THEN
		assertNotNull(livres);
		assertEquals(livres.size(), 2);
		System.out.println("*--- testListerLivresEmpruntes ---* ");
		for (Livre livre: livres) {
			System.out.println(livre.getTitre());
		}
	}
	
	@Test
	void testListerLivresAnglais() {
		//GIVEN
		Livre livreDidierA = creerLivreAnglais("Didier","Retour à Reims A","Anglais");
		Livre livreDidierB = creerLivreAnglais("Didier","Retour à Reims B","Anglais");
		Livre livreDidierC = creerLivreAnglais("Didier","Retour à Reims C","Français");
		Livre livreAutreAuteur = creerLivreAnglais("Pas Didier","Retour à Paris","Anglais");
	
		bibliothecaire.ajouterLivre(livreDidierA);
		bibliothecaire.ajouterLivre(livreDidierB);
		bibliothecaire.ajouterLivre(livreDidierC);
		bibliothecaire.ajouterLivre(livreAutreAuteur);

		//WHEN
		ArrayList<Livre> livresAnglais = bibliothecaire.listerLivresAnglais();

		//THEN
		System.out.println("*--- testListerLivresAnglais ---* ");
		for (Livre livre : livresAnglais) {
			System.out.println(livre.getTitre() + "\t" + livre.getLangue());
			assertTrue(livre.getLangue().equals("Anglais"));
		}
		assertEquals(livresAnglais.size(), 3);

	}
	
	@Test
	void testListerNbLivresEmpruntesPourUnAuteur() throws ParseException, AuteurInvalideException {
		//GIVEN
		Livre livreDidier1 = creerLivre("Didier","Retour à Reims");
		Livre livreDidier2 = creerLivre("Didier","Retour à Paris");
		Livre livreRowling3 = creerLivre("Jk.Rowling","Retour à Lyon");
		bibliothecaire.ajouterLivre(livreDidier1);
		bibliothecaire.ajouterLivre(livreDidier2);
		bibliothecaire.ajouterLivre(livreRowling3);
		Personne personne = new Personne("123", "Dupont", "Paul");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datePret = "2020-12-20";
		String dateDelais = "2020-12-29";
		String dateRetourne1 = ("0000-00-00");
		
		if((verifieFormatDate(datePret)) && verifieFormatDate(dateDelais)) {
			Date datePret1 = sdf.parse(datePret);
			Date dateDelais1 = sdf.parse(dateDelais);
			
			Emprunt e1 = new Emprunt(1,livreDidier1, personne,datePret1, dateRetourne1, dateDelais1,0);
			Emprunt e2 = new Emprunt(2,livreDidier2, personne, datePret1, dateRetourne1, dateDelais1,0);
			Emprunt e3 = new Emprunt(3,livreRowling3, personne, datePret1, dateRetourne1, dateDelais1,0);
			bibliothecaireEmprunt.preterUnLivre(e1);
			bibliothecaireEmprunt.preterUnLivre(e2);
			bibliothecaireEmprunt.preterUnLivre(e3);
			
			//WHEN
			Auteur auteur1 = new Auteur("Didier");
			String res1 = bibliothecaireEmprunt.listerNbLivresEmpruntesPourUnAuteur(auteur1);
			Auteur auteur2 = new Auteur("Jk.Rowling");
			String res2 = bibliothecaireEmprunt.listerNbLivresEmpruntesPourUnAuteur(auteur2);
			
			//THEN
			System.out.println("*--- testListerNbLivresEmpruntesPourUnAuteur ---* ");
			assertTrue(e1.getLivre().getAuteur().equals(auteur1));
			assertFalse(e2.getLivre().getAuteur().equals(auteur2));
			System.out.println(res1);
			System.out.println(res2);
		}else {
			System.out.println("Le format de date doit être 'YYYY-MM-DD'.");
		}
	}
	
	@Test
	void testTrouverLivreSurUnTheme() {
		//GIVEN
		Livre livreDidierA = creerLivreAnglais("Didier","Retour à Reims A","Anglais");
		Livre livreDidierB = creerLivreAnglais("Didier","Retour à Reims B","Anglais");
		Livre livreDidierC = creerLivreAnglais("Didier","Retour à Reims C","Français");
		Livre livreAutreAuteur = creerLivreAnglais("Pas Didier","Retour à Paris","Anglais");
		
		livreDidierA.setTheme("Fantasy");
		livreDidierB.setTheme("Science Fiction");
		livreDidierC.setTheme("Fantasy");
		livreAutreAuteur.setTheme("");

		bibliothecaire.ajouterLivre(livreDidierA);
		bibliothecaire.ajouterLivre(livreDidierB);
		bibliothecaire.ajouterLivre(livreDidierC);
		bibliothecaire.ajouterLivre(livreAutreAuteur);

		//WHEN
		Livre livreTheme = bibliothecaire.trouverLivreSurUnTheme("Fantasy");

		//THEN
		System.out.println("*--- testTrouverLivreSurUnTheme ---* ");
		System.out.println(livreTheme.getTheme());
		System.out.println(livreTheme.getTitre());
		assertEquals(livreTheme.getTheme(), "Fantasy");
	}
	
	
	@Test
	void testEnvoyerAmendeRetardaire() throws ParseException {
		//GIVEN
		Livre livreDidier1 = creerLivre("Didier","Retour à Reims");
		Livre livreDidier2 = creerLivre("Didier","Retour à Paris");
		Livre livreDidier3 = creerLivre("Didier","Retour à Bordeaux");
						
		bibliothecaire.ajouterLivre(livreDidier1);
		bibliothecaire.ajouterLivre(livreDidier2);
		bibliothecaire.ajouterLivre(livreDidier3);
		Personne personneA = new Personne("123", "Nouvel", "Paul");
		Personne personneB = new Personne("456", "Nouvel", "Clement","678");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calobj1 = Calendar.getInstance();
		calobj1.add(Calendar.DAY_OF_MONTH, -5);
		Calendar calobj2 = Calendar.getInstance();
		calobj2.add(Calendar.DAY_OF_MONTH, 7);
		String datePret = "2020-12-20";
		String dateDelaisAvant = sdf.format(calobj1.getTime());
		String dateDelaisApres = sdf.format(calobj2.getTime());
		String dateRetourne1 = ("0000-00-00");
		String dateRetourne2 = ("2020-12-25");
		
		if((verifieFormatDate(datePret)) && verifieFormatDate(dateDelaisAvant)&& verifieFormatDate(dateDelaisApres)) {
			Date datePret1 = sdf.parse(datePret);
			Date dateDelais1 = sdf.parse(dateDelaisAvant);
			Date dateDelais2 = sdf.parse(dateDelaisApres);
			
			Emprunt e1 = new Emprunt(1,livreDidier1, personneA, datePret1, dateRetourne1,dateDelais1,0);
			Emprunt e2 = new Emprunt(2,livreDidier2, personneA, datePret1, dateRetourne2, dateDelais2,0);
			Emprunt e3 = new Emprunt(3,livreDidier3, personneB, datePret1, dateRetourne1, dateDelais2,0);
			bibliothecaireEmprunt.preterUnLivre(e1);
			bibliothecaireEmprunt.preterUnLivre(e2);
			bibliothecaireEmprunt.preterUnLivre(e3);
			
			//WHEN
			//bibliothecaireEmprunt.envoyerAmendeRetardaire();
			
			//THEN
			System.out.println("*--- testEnvoyerAmendeRetardaire ---* ");
			bibliothecaireEmprunt.envoyerAmendeRetardaire();
			Calendar calobj = Calendar.getInstance();
			assertTrue(sdf.format(e1.getDateDelais()).compareTo(sdf.format(calobj.getTime())) < 0);
			assertFalse(sdf.format(e2.getDateDelais()).compareTo(sdf.format(calobj.getTime())) < 0);
			assertFalse(sdf.format(e3.getDateDelais()).compareTo(sdf.format(calobj.getTime())) < 0);
		}else {
			System.out.println("Le format de date doit être 'YYYY-MM-DD'.");
		}
	}
	
	@Test
	void testEncaisserAmendeRetardaire() throws ParseException {
		//GIVEN
		Livre livreDidier1 = creerLivre("Didier","Retour à Reims");
		Livre livreDidier2 = creerLivre("Didier","Retour à Paris");
		Livre livreDidier3 = creerLivre("Didier","Retour à Bordeaux");
								
		bibliothecaire.ajouterLivre(livreDidier1);
		bibliothecaire.ajouterLivre(livreDidier2);
		bibliothecaire.ajouterLivre(livreDidier3);
		Personne personneA = new Personne("123", "Nouvel", "Paul");
		Personne personneB = new Personne("456", "Nouvel", "Clement","678");
								
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calobj1 = Calendar.getInstance();
		calobj1.add(Calendar.DAY_OF_MONTH, -2);
		String datePret = "2020-12-20";
		String dateDelais = sdf.format(calobj1.getTime());
		String dateRetourne1 = ("0000-00-00");
		String dateRetourne2 = ("2020-12-26");
		
		if((verifieFormatDate(datePret)) && verifieFormatDate(dateDelais)) {
			Date datePret1 = sdf.parse(datePret);
			Date dateDelais1 = sdf.parse(dateDelais);
			
			Emprunt e1 = new Emprunt(1,livreDidier1, personneA, datePret1, dateRetourne1,dateDelais1,0);
			Emprunt e2 = new Emprunt(2,livreDidier2, personneA, datePret1, dateRetourne2, dateDelais1,0);
			Emprunt e3 = new Emprunt(3,livreDidier3, personneB, datePret1, dateRetourne1, dateDelais1,0);
			bibliothecaireEmprunt.preterUnLivre(e1);
			bibliothecaireEmprunt.preterUnLivre(e2);
			bibliothecaireEmprunt.preterUnLivre(e3);
			
			//WHEN
			bibliothecaireEmprunt.envoyerAmendeRetardaire();
			System.out.println("*--- testEncaisserAmendeRetardaire ---* ");
			bibliothecaireEmprunt.encaisserAmendeRetardaire(4,e1);
			bibliothecaireEmprunt.encaisserAmendeRetardaire(0,e2);
			bibliothecaireEmprunt.encaisserAmendeRetardaire(5,e3);
			
			//THEN
			Calendar calobj = Calendar.getInstance();
			assertEquals(e1.getDateRetourne(),sdf.format(calobj.getTime()));
			System.out.println(e1.getAmende());
			System.out.println(e1.getDateRetourne());
			System.out.println(e2.getAmende());
			System.out.println(e2.getDateRetourne());
			System.out.println(e3.getAmende());
			System.out.println(e3.getDateRetourne());
			
		}else {
			System.out.println("Le format de date doit être 'YYYY-MM-DD'.");
		}
	}
	
	public static void main(String[] args) {
        junit.textui.TestRunner.run(new TestSuite(BibliothecaireTest.class));
    }
}

