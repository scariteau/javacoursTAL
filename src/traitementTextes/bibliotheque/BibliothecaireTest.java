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
	void testAjouterLivre() {
		fail("Not yet implemented");
	}

	@Test
	void testEnleverLivre() {
		fail("Not yet implemented");
	}

	@Test
	void testAfficherOeuvresAuteur() {
		Auteur auteur=new Auteur("nomAuteur");
		ArrayList<Livre> livres=new ArrayList<>();
		String titre = "un titre presomptueux";
		Livre premierLivre=new Livre(auteur, titre);
		livres.add(premierLivre);
		bibliothecaire.getCatalogue().put(auteur, livres);
		String listeOeuvres = bibliothecaire.listerOeuvresAuteur(auteur);
		assertNotNull(listeOeuvres);
		assertTrue(listeOeuvres.contains(titre));
		System.out.println(listeOeuvres);
	}

}
