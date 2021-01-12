package devoirs2.arthurFabienneMartin;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
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
	}
	
	
	@Test
	void testAjouterLivre() {
		//GIVEN
		Auteur auteur = new Auteur("nomAuteur");
		ArrayList<Livre> livres = new ArrayList<>();
		String titre = "Un titre";
		Livre nouveauLivre = new Livre(auteur, titre);
		livres.add(nouveauLivre);
		
		//WHEN
		bibliothecaire.ajouterLivre(nouveauLivre);
		
		//THEN
		assertNotNull(nouveauLivre.getAuteur());
		assertNotNull(bibliothecaire.getCatalogue().get(auteur));
		assertNotNull(bibliothecaire.getCatalogue().get(auteur).contains(nouveauLivre));
	}


	@Test
	void testEnleverLivre() {
		//GIVEN
		Auteur auteur = new Auteur("nomAuteur");
		String titre = "Un titre";
		Livre nouveauLivre = new Livre(auteur, titre);
		bibliothecaire.ajouterLivre(nouveauLivre);
		
		//WHEN
		bibliothecaire.enleverLivre(nouveauLivre);
		
		//THEN
		assertNull(bibliothecaire.getCatalogue().get(auteur));
		assertTrue(bibliothecaire.getCatalogue().isEmpty());
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
	
	//Le test des méthodes suivantes se trouve dans la class RegistreTest.

	@Test
	void testPreterUnLivre() {
		//GIVEN
		Client client = new Client("Michel Pertinent", false);
		Auteur auteur = new Auteur("Doudou");
		Livre ancienLivre = new Livre(auteur, "java");
		Etiquette etiquette = new Etiquette(false, "sf");
		//WHEN
		bibliothecaire.preterLivre(client, ancienLivre, etiquette);
		
		//THEN
		assertFalse(bibliothecaire.getRegistre().getEmprunts().isEmpty());
		assertNotNull(bibliothecaire.getRegistre().getEmprunts().get(client));
		assertFalse(bibliothecaire.getRegistre().getEmprunts().get(client).isEmpty());
	}
	
}
