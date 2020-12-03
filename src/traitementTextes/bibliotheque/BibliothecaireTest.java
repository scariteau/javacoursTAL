package traitementTextes.bibliotheque;

import static org.junit.jupiter.api.Assertions.*;

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
		System.out.println(listeOeuvres);
	}
	
	
	@Test
	void testAjouterLivre() {
		fail("Not yet implemented");
	}

	@Test
	void testEnleverLivre() {
		fail("Not yet implemented");
	}

	
	
	/*
	 * 
	 * Partie concernee par le devoir
	 * Voici le décompte des notes:
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
		fail("Not yet implemented");
	}
	
	@Test
	void testRelancerEmprunteurEnRetard() {
		fail("Not yet implemented");
	}
	
	@Test
	void testListerPersonnesAyantEmpruntesUnLivre() {
		fail("Not yet implemented");
	}
	
	@Test
	void testListerLivresEmpruntesParEtudiant() {
		fail("Not yet implemented");
	}
	
	@Test
	void testListerLivresEmpruntes() {
		fail("Not yet implemented");
	}
	
	@Test
	void testListerLivresAnglais() {
		fail("Not yet implemented");
	}
	
	@Test
	void testListerNbLivresEmpruntesPourUnAuteur() {
		fail("Not yet implemented");
	}
	
	@Test
	void testTrouverLivreSurUnTheme() {
		fail("Not yet implemented");
	}
	
	@Test
	void testEnvoyerAmendeRetardaire() {
		fail("Not yet implemented");
	}
	
	@Test
	void testEncaisserAmendeRetardaire() {
		fail("Not yet implemented");
	}

}
