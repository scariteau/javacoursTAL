package traitementTextes.bibliotheque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import etudiants.EtudiantTAL;
import traitementTextes.bibliotheque.catalogue.Auteur;
import traitementTextes.bibliotheque.catalogue.Livre;
import traitementTextes.bibliotheque.catalogue.LivreEmprunte;
import traitementTextes.bibliotheque.catalogue.LivreVO;
import traitementTextes.bibliotheque.personnes.Abonne;
import traitementTextes.bibliotheque.personnes.AbonneInconnuException;

class BibliothecaireTest {

	private static final String UN_TITRE_PRESOMPTUEUX = "un titre presomptueux";
	private static final String NOM_AUTEUR = "nomAuteur";
	private Bibliothecaire bibliothecaire;

	@BeforeEach
	void setUp() throws Exception {

		HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();
		bibliothecaire = new Bibliothecaire(catalogue);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAfficherOeuvresAuteur() {

		// GIVEN
		Livre premierLivre = ajouterLivreDansCatalogue(NOM_AUTEUR, UN_TITRE_PRESOMPTUEUX);

		// WHEN
		String listeOeuvres = bibliothecaire.listerOeuvresAuteur(premierLivre.getAuteur());

		// THEN
		assertNotNull(listeOeuvres);
		assertTrue(listeOeuvres.contains(premierLivre.getTitre()));
	}

	@Test
	void testAjouterLivres() {
		// GIVEN
		Livre nouveauLivre = creerLivre(NOM_AUTEUR, UN_TITRE_PRESOMPTUEUX);

		// WHEN
		ArrayList<Livre> listeOeuvres = bibliothecaire.ajouterLivre(nouveauLivre);

		// THEN
		assertNotNull(listeOeuvres);
		assertTrue(listeOeuvres.contains(nouveauLivre));
	}

	@Test
	void testAjouter2Livres() {
		// GIVEN
		Livre nouveauLivre = creerLivre(NOM_AUTEUR, UN_TITRE_PRESOMPTUEUX);
		Livre unAutreLivre = creerLivre(nouveauLivre.getAuteur().getNom(), "un autre titre presomptueux");
		Livre unLivreInconnu = creerLivre(nouveauLivre.getAuteur().getNom(), "un titre presomptueux inconnu");

		// WHEN
		ArrayList<Livre> listeOeuvres = bibliothecaire.ajouterLivre(nouveauLivre);
		ArrayList<Livre> listeAvec2Oeuvres = bibliothecaire.ajouterLivre(unAutreLivre);

		// THEN
		assertNotNull(listeOeuvres);
		assertEquals(listeOeuvres.size(), 2);
		assertTrue(listeOeuvres.contains(nouveauLivre));
		assertTrue(listeOeuvres.contains(unAutreLivre));
		assertTrue(listeAvec2Oeuvres.contains(unAutreLivre));
		assertEquals(listeAvec2Oeuvres, listeOeuvres);
		assertFalse(listeOeuvres.contains(unLivreInconnu));
	}

	@Test
	void testEnleverLivre() throws AuteurInconnuException {
		Livre livreAEnlever = ajouterLivreDansCatalogue(NOM_AUTEUR, UN_TITRE_PRESOMPTUEUX);

		// WHEN
		boolean livreEnleve = bibliothecaire.enleverLivre(livreAEnlever);

		// THEN
		assertTrue(livreEnleve);
		assertEquals(bibliothecaire.getCatalogue().get(livreAEnlever.getAuteur()).size(), 0);
		assertFalse(bibliothecaire.enleverLivre(creerLivre(NOM_AUTEUR, "un autre titre tres presomptueux")));
	}

	@Test
	void testEnleverLivreInconnu() {
		try {
			// WHEN
			assertFalse(bibliothecaire.enleverLivre(creerLivre("auteurNonReference", UN_TITRE_PRESOMPTUEUX)));
		} catch (AuteurInconnuException exception) {
			assertTrue(exception.getMessage().contains("Auteur"));
			assertTrue(exception.getMessage().contains("non trouvée dans le catalogue pour le livre"));

		}
	}

	/*
	 * 
	 * Partie concernee par le devoir Voici le décompte des notes: 1pts par test
	 * OK==>10pts 2 pour pour la mis en place de l'heritage 1pt pour la javadoc 1pt
	 * pour le polymorphisme et la surchage 1pt pour la reutilisation de l'existant
	 * 1pt pour la gestion des exceptions 1pt pour la creation d'exceptions 1pt:
	 * utilisation de l'encapsulation 1pt: utilisation de git 1pt: lisibilité du
	 * code -1pt: méthode avec plus de 3 arguments -1pt: classe de plus de 200
	 * lignes -1pt: plus de 2 boucles for -1pt: trop d'utilisation de if
	 * 
	 */

	@Test
	void testPreterUnLivre() {

		// GIVEN
		Livre livreAEmprunte = ajouterLivreDansCatalogue(NOM_AUTEUR, UN_TITRE_PRESOMPTUEUX);
		Abonne emprunteur = new Abonne("Magalie", 12345);

		// WHEN
		LivreEmprunte livreEmprunte = bibliothecaire.preterUnLivre(livreAEmprunte, emprunteur);

		// THEN
		assertEquals(bibliothecaire.getLivresEmpruntes().size(), 1);
		assertTrue(bibliothecaire.getLivresEmpruntes().contains(livreEmprunte));
		assertEquals(bibliothecaire.getLivresEmpruntes().get(0).getEmprunteur().getNom(), "Magalie");
	}

	@Test
	void testListerPersonnesAyantEmpruntesUnLivre() {
		// GIVEN
		Abonne emprunteur = new Abonne("Yuno", 12340);
		Livre livreAEmprunte = ajouterLivreDansCatalogue(NOM_AUTEUR, UN_TITRE_PRESOMPTUEUX);
		LivreEmprunte livreEmprunte = new LivreEmprunte(livreAEmprunte, emprunteur);
		bibliothecaire.getLivresEmpruntes().add(livreEmprunte);

		Abonne unAutreemprunteur = creerUnAutreEmprunteur(emprunteur);

		// WHEN
		Set<Abonne> personnesAyantEmprunteUnLivre = bibliothecaire.listerPersonnesAyantEmprunteUnLivre();
		// THEN
		assertEquals(personnesAyantEmprunteUnLivre.size(), 2);
		assertTrue(personnesAyantEmprunteUnLivre.contains(emprunteur));
		assertTrue(personnesAyantEmprunteUnLivre.contains(unAutreemprunteur));
	}

	@Test
	void testListerLivresEmpruntesParEtudiant() {
		// GIVEN

		EtudiantTAL emprunteur = new EtudiantTAL("Yuno", 12340);
		Livre livreAEmprunte = ajouterLivreDansCatalogue(NOM_AUTEUR, UN_TITRE_PRESOMPTUEUX);
		LivreEmprunte livreEmprunte = new LivreEmprunte(livreAEmprunte, emprunteur);
		bibliothecaire.getLivresEmpruntes().add(livreEmprunte);

		Abonne unAutreemprunteur = new Abonne("Yuno2", 12340);
		Livre livreAEmprunte2 = ajouterLivreDansCatalogue(NOM_AUTEUR, "un autre titre");
		LivreEmprunte livreEmprunte2 = new LivreEmprunte(livreAEmprunte2, unAutreemprunteur);
		bibliothecaire.getLivresEmpruntes().add(livreEmprunte2);
		

		// WHEN
		Set<LivreEmprunte> personnesAyantEmprunteUnLivre = bibliothecaire.listerLivresEmpruntesParEtudiant();

		// THEN
		assertEquals(personnesAyantEmprunteUnLivre.size(), 1);
		assertTrue(personnesAyantEmprunteUnLivre.contains(livreAEmprunte));
		assertFalse(personnesAyantEmprunteUnLivre.contains(livreEmprunte2));
	}

	@Test
	void testListerLivresEmpruntes() {

		// GIVEN
		EtudiantTAL emprunteur = new EtudiantTAL("Yuno", 12340);
		Livre livreAEmprunte = ajouterLivreDansCatalogue(NOM_AUTEUR, UN_TITRE_PRESOMPTUEUX);
		LivreEmprunte livreEmprunte = new LivreEmprunte(livreAEmprunte, emprunteur);
		bibliothecaire.getLivresEmpruntes().add(livreEmprunte);
		creerUnAutreEmprunteur(emprunteur);

		// WHEN
		List<LivreEmprunte> listeEmpruntes = bibliothecaire.getLivresEmpruntes();

		// THEN
		assertEquals(listeEmpruntes.size(), 3);
		assertTrue(listeEmpruntes.contains(livreEmprunte));
	}

	@Test
	void testListerLivresAnglais() {

		// GIVEN
		Auteur auteur = new Auteur(NOM_AUTEUR);
		LivreVO livreVO = new LivreVO(auteur, UN_TITRE_PRESOMPTUEUX, "EN");
		Livre livreVOAjoute = ajouterLivreDansCatalogue(livreVO);
		Livre unAutreLivreAjoute = ajouterLivreDansCatalogue(NOM_AUTEUR, "un autre titre presomptueux");

		// WHEN
		List<Livre> listeAnglais = bibliothecaire.getLivresVO("EN");

		// THEN
		assertEquals(listeAnglais.size(), 1);
		assertTrue(bibliothecaire.getCatalogue().get(auteur).contains(livreVOAjoute));
		assertTrue(bibliothecaire.getCatalogue().get(auteur).contains(unAutreLivreAjoute));
	}

	@Test
	void testListerNbLivresEmpruntesPourUnAuteur() {
		// GIVEN
		Auteur auteur = new Auteur(NOM_AUTEUR);
		EtudiantTAL emprunteur = new EtudiantTAL("Yuno", 12340);
		Livre livreAEmprunte = ajouterLivreDansCatalogue(NOM_AUTEUR, UN_TITRE_PRESOMPTUEUX);
		LivreEmprunte livreEmprunte = new LivreEmprunte(livreAEmprunte, emprunteur);
		bibliothecaire.getLivresEmpruntes().add(livreEmprunte);

		Livre livreAEmprunte2 = ajouterLivreDansCatalogue(NOM_AUTEUR, "un autre titre presomptueux");
		bibliothecaire.getLivresEmpruntes().add(new LivreEmprunte(livreAEmprunte2, emprunteur));

		Abonne unAutreemprunteur = new Abonne("Asta", 1234);
		Livre livreAEmprunte3 = ajouterLivreDansCatalogue("un autre auteur", "un autre titre tres presomptueux");
		bibliothecaire.getLivresEmpruntes().add(new LivreEmprunte(livreAEmprunte3, unAutreemprunteur));

		// WHEN
		int nbLivres = bibliothecaire.compterNbLivresEmpruntesPourUnAuteur(auteur);
		// THEN
		assertEquals(nbLivres, 2);
	}

	@Test
	void testTrouverLivreSurUnTheme() {
		// GIVEN
		String themeFantasy = "fantasy";
		Livre livreATheme = ajouterLivreDansCatalogue(NOM_AUTEUR, UN_TITRE_PRESOMPTUEUX);
		livreATheme.setTheme(themeFantasy);
		Livre livreATheme2 = ajouterLivreDansCatalogue(NOM_AUTEUR, "un autre titre presomptueux");
		livreATheme2.setTheme("Romance");
		Livre livreATheme3 = ajouterLivreDansCatalogue("un autre auteur", "un autre titre tres presomptueux");
		livreATheme3.setTheme(themeFantasy);

		// WHEN
		ArrayList<Livre> livresFantasy = bibliothecaire.trouverLivresSurUnTheme(themeFantasy);
		ArrayList<Livre> livresRomance = bibliothecaire.trouverLivresSurUnTheme("Romance");

		// THEN
		assertEquals(livresFantasy.size(), 2);
		assertEquals(livresRomance.size(), 1);
	}

	@Test
	void testRelancerEmprunteurEnRetard() {
		// GIVEN
		Abonne abonnee=creerRetardataire();

		// WHEN
		int nbRelance = bibliothecaire.relancerEmprunteursEnRetard(abonnee);
		// THEN
		assertEquals(nbRelance, 1);
	}

	@Test
	void testEnvoyerAmendeRetardaire() throws ParseException {

		// GIVEN
		creerRetardataire();

		// WHEN
		ArrayList<Amende> amendes = bibliothecaire.envoyerAmendeRetardataires();

		// THEN
		assertEquals(amendes.size(), 1);

	}

	@Test
	void testEncaisserAmendeRetardaire() throws AbonneInconnuException {
		// GIVEN
		Abonne retardataire = new Abonne("sandy", 1369);
		Livre livreAEmprunte1 = ajouterLivreDansCatalogue(NOM_AUTEUR, "un autre titre presomptueux");
		LivreEmprunte livreEmprunte = new LivreEmprunte(livreAEmprunte1, retardataire);
		livreEmprunte.setDateDebutEmprunt(LocalDate.now().minusDays(40));
		bibliothecaire.getLivresEmpruntes().add(livreEmprunte);
		Amende amende = new Amende(livreEmprunte, 2);
		retardataire.getAmendes().add(amende);

		Livre livreAEmprunte2 = ajouterLivreDansCatalogue(NOM_AUTEUR, "un autre titre tres presomptueux");
		LivreEmprunte livreEmprunte2 = new LivreEmprunte(livreAEmprunte2, retardataire);
		livreEmprunte2.setDateDebutEmprunt(LocalDate.now().minusDays(60));
		bibliothecaire.getLivresEmpruntes().add(livreEmprunte2);
		Amende amende2 = new Amende(livreEmprunte2, 2);
		retardataire.getAmendes().add(amende2);

		// WHEN
		boolean amendeEncaissee = bibliothecaire.encaisserAmendeRetardataire(retardataire, amende);
		// THEN
		assertTrue(amendeEncaissee);
		assertEquals(retardataire.getAmendes().size(), 1);
	}

	@Test
	void testEncaisserAmendeInconnuRetardaire() throws AbonneInconnuException {
		// GIVEN
		Abonne retardataire = new Abonne("sandy", 1369);
		Livre livreAEmprunte1 = ajouterLivreDansCatalogue(NOM_AUTEUR, "un autre titre presomptueux");
		LivreEmprunte livreEmprunte = new LivreEmprunte(livreAEmprunte1, retardataire);
		livreEmprunte.setDateDebutEmprunt(LocalDate.now().minusDays(40));
		bibliothecaire.getLivresEmpruntes().add(livreEmprunte);
		Amende amende = new Amende(livreEmprunte, 2);

		// WHEN
		boolean amendeEncaissee = bibliothecaire.encaisserAmendeRetardataire(retardataire, amende);
		// THEN
		assertFalse(amendeEncaissee);
		assertEquals(retardataire.getAmendes().size(), 0);
	}

	@Test
	void testEncaisserAmendeRetardaireInconnu() {
		// GIVEN
		Abonne retardataire = new Abonne("sandy", 1369);
		Livre livreAEmprunte1 = ajouterLivreDansCatalogue(NOM_AUTEUR, "un autre titre presomptueux");
		LivreEmprunte livreEmprunte = new LivreEmprunte(livreAEmprunte1, retardataire);
		livreEmprunte.setDateDebutEmprunt(LocalDate.now().minusDays(40));
		Amende amende = new Amende(livreEmprunte, 2);

		try {
			// WHEN
			bibliothecaire.encaisserAmendeRetardataire(retardataire, amende);
		} catch (AbonneInconnuException e) {
			// THEN
			assertTrue(e.getMessage().contains("Abonné"));
		}
	}

	@Test
	void testEncaisserAmendeRetardaireInconnuAvecLambda() throws AbonneInconnuException {
		// GIVEN
		Abonne retardataire = new Abonne("sandy", 1369);
		Livre livreAEmprunte1 = ajouterLivreDansCatalogue(NOM_AUTEUR, "un autre titre presomptueux");
		LivreEmprunte livreEmprunte = new LivreEmprunte(livreAEmprunte1, retardataire);
		livreEmprunte.setDateDebutEmprunt(LocalDate.now().minusDays(40));
		Amende amende = new Amende(livreEmprunte, 2);

		// THEN
		assertThrows(AbonneInconnuException.class, () -> {
			bibliothecaire.encaisserAmendeRetardataire(retardataire, amende);
		}, "Abonne");
	}

	private Abonne creerRetardataire() {
		Abonne retardataire = new Abonne("sandy", 1369);
		Livre livreAEmprunte1 = ajouterLivreDansCatalogue(NOM_AUTEUR, "un autre titre presomptueux");
		LivreEmprunte livreEmprunte = new LivreEmprunte(livreAEmprunte1, retardataire);
		livreEmprunte.setDateDebutEmprunt(LocalDate.now().minusDays(40));
		livreEmprunte.setDateFinEmprunt(LocalDate.now().minusDays(10));
		bibliothecaire.getLivresEmpruntes().add(livreEmprunte);

		Livre livreAEmprunte2 = ajouterLivreDansCatalogue(NOM_AUTEUR, "un autre titre tres presomptueux");
		LivreEmprunte livreEmprunte2 = new LivreEmprunte(livreAEmprunte2, retardataire);
		livreEmprunte2.setDateDebutEmprunt(LocalDate.now().minusDays(10));
		livreEmprunte2.setDateFinEmprunt(LocalDate.now().plusDays(20));
		bibliothecaire.getLivresEmpruntes().add(livreEmprunte2);
		return retardataire;
	}

	private Abonne creerUnAutreEmprunteur(Abonne emprunteur) {
		Livre livreAEmprunte2 = ajouterLivreDansCatalogue(NOM_AUTEUR, "un autre titre presomptueux");
		bibliothecaire.getLivresEmpruntes().add(new LivreEmprunte(livreAEmprunte2, emprunteur));

		Abonne unAutreemprunteur = new Abonne("Asta", 1234);
		Livre livreAEmprunte3 = ajouterLivreDansCatalogue(NOM_AUTEUR, "un autre titre tres presomptueux");
		bibliothecaire.getLivresEmpruntes().add(new LivreEmprunte(livreAEmprunte3, unAutreemprunteur));
		return unAutreemprunteur;
	}

	private Livre ajouterLivreDansCatalogue(String nomAuteur, String tire) {

		return ajouterLivreDansCatalogue(creerLivre(nomAuteur, tire));
	}

	private Livre ajouterLivreDansCatalogue(Livre livreAAjouter) {

		Auteur auteur = livreAAjouter.getAuteur();
		if (bibliothecaire.getCatalogue().containsKey(auteur)) {
			bibliothecaire.getCatalogue().get(auteur).add(livreAAjouter);
		} else {
			ArrayList<Livre> livres = new ArrayList<>();
			livres.add(livreAAjouter);
			bibliothecaire.getCatalogue().put(auteur, livres);
		}
		return livreAAjouter;
	}

	private Livre creerLivre(String nomAuteur, String titreLivre) {
		Auteur auteur = new Auteur(nomAuteur);
		return new Livre(auteur, titreLivre);
	}
}
