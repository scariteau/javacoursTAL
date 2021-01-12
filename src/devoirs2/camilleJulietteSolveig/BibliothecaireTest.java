package devoirs2.camilleJulietteSolveig;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe permettant de tester les méthodes de la classe Bibliothecaire
 * @see Bibliothecaire
 */
class BibliothecaireTest {
	
	private Bibliothecaire bibliothecaire;
	private Auteur auteur1;
	private Livre livre1;
	private Auteur auteur2;
	private Livre livre2;
	private LocalDate datePassee;
	private LocalDate dateFuture;
	private Emprunteur emprunteur1;
	private Emprunteur emprunteur2;

	@BeforeEach
	void setUp() throws Exception {
		
		HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();		
		bibliothecaire = new Bibliothecaire(catalogue);
		auteur1 = new Auteur("Romain Gary");
		livre1 = new Livre(auteur1, "La Vie devant soi");
		auteur2 = new Auteur("Michel Houellebecq");
		livre2 = new Livre(auteur2, "Serotonine");
		datePassee = LocalDate.of(2020, Month.SEPTEMBER, 7);
		dateFuture = LocalDate.of(2021, Month.SEPTEMBER, 7);
		emprunteur1 = new Emprunteur("Poder", "Solveig");
		emprunteur2 = new Emprunteur("Caron", "Juliette");
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testAfficherOeuvresAuteur() {
		
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);
		
		//WHEN
		ArrayList<Livre> listeOeuvres = bibliothecaire.listerLivres(auteur1);
		
		//THEN
		assertFalse(listeOeuvres.isEmpty());
		assertTrue(listeOeuvres.contains(livre1));
	}
	
	@Test
	void testAjouterLivre() {
		
		//WHEN
		bibliothecaire.ajouterLivre(livre1);
		
		//THEN
		assertNotNull(bibliothecaire.getCatalogue().get(auteur1));
		assertTrue(bibliothecaire.getCatalogue().get(auteur1).contains(livre1));
	}

	@Test
	void testEnleverLivre() {
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);
		
		//WHEN
		bibliothecaire.enleverLivre(livre1);
		
		//THEN
		ArrayList<Livre> listeLivres = bibliothecaire.getCatalogue().get(auteur1);
		assertFalse(listeLivres.contains(livre1));
		assertEquals(bibliothecaire.getCatalogue().get(livre1.getAuteur()).size(), 0);
	}

	@Test
	void testPreterUnLivre() {
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);
		
		//WHEN
		bibliothecaire.preterLivre(livre1, emprunteur1, datePassee);
		bibliothecaire.preterLivre(livre1, emprunteur2, dateFuture);
		
		//THEN
		assertNotNull(emprunteur1.getLivresEmpruntes().get(livre1));
		assertTrue(emprunteur1.getLivresEmpruntes().get(livre1).equals(datePassee));
		assertNotNull(bibliothecaire.getEmprunteurs());
		assertTrue(bibliothecaire.getEmprunteurs().contains(emprunteur1));
		assertTrue(bibliothecaire.getEmprunteurs().contains(emprunteur2));
	}
	
	@Test
	void testListerEmprunteursEnRetard_vide() {
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);
		bibliothecaire.preterLivre(livre1, emprunteur1, dateFuture);
		
		//WHEN
		HashMap<Emprunteur, ArrayList<Livre>> retards = bibliothecaire.listerEmprunteursEnRetard();
		
		//THEN
		assertTrue(retards.isEmpty());
	}
	
	@Test
	void testListerEmprunteursEnRetard() {
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);
		bibliothecaire.ajouterLivre(livre2);
		bibliothecaire.preterLivre(livre1, emprunteur1, dateFuture);
		bibliothecaire.preterLivre(livre2, emprunteur1, datePassee);
		bibliothecaire.preterLivre(livre1, emprunteur2, datePassee);
		
		//WHEN
		HashMap<Emprunteur, ArrayList<Livre>> retards = bibliothecaire.listerEmprunteursEnRetard();
		
		//THEN
		assertFalse(retards.isEmpty());
		assertNotNull(retards.get(emprunteur1));
		assertNotNull(retards.get(emprunteur2));
		assertFalse(retards.get(emprunteur1).contains(livre1));
		assertTrue(retards.get(emprunteur1).contains(livre2));
		assertTrue(retards.get(emprunteur2).contains(livre1));
	}
	
	@Test
	void testRelancerEmprunteurEnRetard() {
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);		
		bibliothecaire.ajouterLivre(livre2);
		bibliothecaire.preterLivre(livre1, emprunteur1, dateFuture);
		bibliothecaire.preterLivre(livre1, emprunteur2, datePassee);
		
		//WHEN
		bibliothecaire.RelancerEmprunteurEnRetard();	

		//THEN
		assertTrue(emprunteur1.getMessagerie().isEmpty());
		assertFalse(emprunteur2.getMessagerie().isEmpty());
		assertTrue(emprunteur2.getMessagerie().containsKey("retard"));
	}	
	
	@Test
	void testListerPersonnesAyantEmpruntesUnLivre() {
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);
		bibliothecaire.preterLivre(livre1, emprunteur1, dateFuture);
		bibliothecaire.preterLivre(livre1, emprunteur2, datePassee);
		
		//WHEN
		ArrayList<Emprunteur> emprunteurs = bibliothecaire.getEmprunteurs();
		
		//THEN
		assertFalse(emprunteurs.isEmpty());
		assertTrue(emprunteurs.contains(emprunteur1));
		assertTrue(emprunteurs.contains(emprunteur2));
	}
	
	@Test
	void testListerLivresEmpruntes() {
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);
		bibliothecaire.ajouterLivre(livre2);
		EtudiantEmprunteur etudiant=new EtudiantEmprunteur("Poder", "Solveig", 21903145);
		bibliothecaire.preterLivre(livre1, etudiant, dateFuture);
		bibliothecaire.preterLivre(livre2, emprunteur1, datePassee);
		
		//WHEN
		ArrayList<Livre> livresEmpruntes = bibliothecaire.listerLivresEmpruntes();
		
		//THEN
		assertFalse(livresEmpruntes.isEmpty());
		assertTrue(livresEmpruntes.contains(livre1));
		assertTrue(livresEmpruntes.contains(livre2));
	}
	
	@Test
	void testListerLivresEmpruntesParEtudiant() {
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);
		bibliothecaire.ajouterLivre(livre2);
		EtudiantEmprunteur etudiant=new EtudiantEmprunteur("Poder", "Solveig", 21903145);
		bibliothecaire.preterLivre(livre1, etudiant, dateFuture);
		bibliothecaire.preterLivre(livre2, emprunteur1, dateFuture);
		
		//WHEN
		ArrayList<Livre> livresEtudiants = bibliothecaire.listerLivresEmpruntes(EtudiantEmprunteur.class);
		
		//THEN
		assertFalse(livresEtudiants.isEmpty());
		assertTrue(livresEtudiants.contains(livre1));
		assertFalse(livresEtudiants.contains(livre2));
	}
	
	@Test
	void testListerLivresEmpruntesPourUnAuteur() {
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);
		bibliothecaire.ajouterLivre(livre2);
		Livre livre3 = new Livre(auteur2, "Soumission");
		bibliothecaire.ajouterLivre(livre3);
		bibliothecaire.preterLivre(livre2, emprunteur2, dateFuture);
		bibliothecaire.preterLivre(livre3, emprunteur2, dateFuture);
		
		//WHEN
		ArrayList<Livre> livresAuteur1 = bibliothecaire.listerLivresEmpruntes(auteur1);
		ArrayList<Livre> livresAuteur2 = bibliothecaire.listerLivresEmpruntes(auteur2);
		
		//THEN
		assertTrue(livresAuteur1.isEmpty());
		assertFalse(livresAuteur2.isEmpty());
		assertFalse(livresAuteur2.contains(livre1));
		assertTrue(livresAuteur2.contains(livre2));
		assertTrue(livresAuteur2.contains(livre3));
		assertTrue(livresAuteur2.size() == 2);
		
	}
	
	@Test
	void testListerLivresAnglais() {
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);
		Auteur auteurEn=new Auteur("Neil Gaiman");
		LivreAnglais livreEn=new LivreAnglais(auteurEn, "American Gods", "Michel Pagel");
		bibliothecaire.ajouterLivre(livreEn);
		
		//WHEN
		ArrayList<Livre> livresAnglais = bibliothecaire.listerLivres(LivreAnglais.class);
		
		//THEN
		assertFalse(livresAnglais.isEmpty());
		assertTrue(livresAnglais.contains(livreEn));
		assertFalse(livresAnglais.contains(livre1));
	}
	
	@Test
	void testTrouverLivreSurUnTheme() {
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);
		bibliothecaire.ajouterLivre(livre2);
		Livre livre3=new Livre(auteur2, "Le petit lapin");
		bibliothecaire.ajouterLivre(livre3);
		livre1.setTheme("biographie");
		livre2.setTheme("aventure");
		livre3.setTheme("aventure");
		
		//WHEN
		Livre result = bibliothecaire.TrouverLivreSurUnTheme("aventure");
		Livre result2 = bibliothecaire.TrouverLivreSurUnTheme("thriller");
		
		//THEN
		assertNotNull(result);
		assertTrue(result.getTheme() == "aventure");
		assertTrue(result.equals(livre2)||result.equals(livre3));
		assertNull(result2);
	}
	
	@Test
	void testEnvoyerAmendeRetardaire() {
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);		
		bibliothecaire.ajouterLivre(livre2);
		bibliothecaire.preterLivre(livre1, emprunteur1, dateFuture);
		bibliothecaire.preterLivre(livre1, emprunteur2, datePassee);
		
		//WHEN
		bibliothecaire.EnvoyerAmendeRetardaire();
		
		//THEN
		assertNotNull(emprunteur1.getSolde());
		assertTrue(emprunteur2.getSolde()>0);
		assertTrue(emprunteur1.getSolde()==0);
	}
	
	@Test
	void testEncaisserAmendeRetardaire() {
		//GIVEN
		bibliothecaire.ajouterLivre(livre1);		
		bibliothecaire.preterLivre(livre1, emprunteur1, datePassee);
		bibliothecaire.EnvoyerAmendeRetardaire();

		//WHEN
		bibliothecaire.EncaisserAmendeRetardaire(emprunteur1, 2.0);
		
		//THEN
		assertTrue(emprunteur1.getSolde()==0);
		assertTrue(bibliothecaire.getCaisse() == 2);
	}
}
