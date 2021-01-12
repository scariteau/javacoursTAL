package devoirs2.arthurFabienneMartin;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistreTest {

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
	void testRelancerEmprunteurEnRetard() {
		//GIVEN
		Client client = new Client("Michel Pertinent", false);
		Auteur auteur = new Auteur("Doudou");
		Livre ancienLivre = new Livre(auteur, "java");
		Etiquette etiquette = new Etiquette(false, "sf");
		bibliothecaire.preterLivre(client, ancienLivre, etiquette);
		
		//WHEN
		String resultat = bibliothecaire.getRegistre().relancerEmprunteursEnRetard();
		
		bibliothecaire.getRegistre().envoyerAmendeRetardataire();
		bibliothecaire.getRegistre().encaisserAmendeRetardataire();
		
		//THEN
		assertTrue(resultat.equals("Michel Pertinent\n"));
		
	}
	
	
	@Test
	void testListerPersonnesAyantEmpruntesUnLivre() {
		//GIVEN
		Client client = new Client("Michel Pertinent", false);
		Auteur auteur = new Auteur("Doudou");
		Livre ancienLivre = new Livre(auteur, "java");
		Etiquette etiquette = new Etiquette(false, "sf");
		
		
		//WHEN
		String resultat = bibliothecaire.getRegistre().listerPersonnesAyantEmpruntesUnLivre();
		bibliothecaire.preterLivre(client, ancienLivre, etiquette);
		String resultat2 = bibliothecaire.getRegistre().listerPersonnesAyantEmpruntesUnLivre();
		
		//THEN
		assertTrue(resultat.equals("Aucun livre n'a été emprunté actuellement."));
		assertTrue(resultat2.equals("Michel Pertinent\n"));
	}
	
	
	@Test
	void testListerLivresEmpruntesParEtudiant() {
		//GIVEN
		Client client = new Client("Michel Pertinent", false);
		Client client2 = new Client("Patati Patata", true);
		Auteur auteur = new Auteur("Doudou");
		Livre ancienLivre = new Livre(auteur, "java");
		Livre ancienLivre2 = new Livre(auteur, "zbl");
		Etiquette etiquette = new Etiquette(false, "sf");
		bibliothecaire.preterLivre(client, ancienLivre, etiquette);
		
		
		//WHEN
		String resultat = bibliothecaire.getRegistre().listerLivresEmpruntesParEtudiant();
		bibliothecaire.preterLivre(client2, ancienLivre2, etiquette);
		String resultat2 = bibliothecaire.getRegistre().listerLivresEmpruntesParEtudiant();
		
		//THEN
		assertTrue(resultat.equals("Aucun livre n'a été emprunté par un étudiant."));
		assertTrue(resultat2.equals("Patati Patata :\nDoudou\tzbl\n"));
	}
	
	
	@Test
	void testListerLivresEmpruntes() {
		//GIVEN
		Client client = new Client("Michel Pertinent", false);
		Client client2 = new Client("Patati Patata", true);
		Auteur auteur = new Auteur("Doudou");
		Livre ancienLivre = new Livre(auteur, "java");
		Livre ancienLivre2 = new Livre(auteur, "zbl");
		Etiquette etiquette = new Etiquette(false, "sf");
		
		//WHEN
		String resultat = bibliothecaire.getRegistre().listerLivresEmpruntes();
		bibliothecaire.preterLivre(client, ancienLivre, etiquette);
		bibliothecaire.preterLivre(client2, ancienLivre2, etiquette);
		String resultat2 = bibliothecaire.getRegistre().listerLivresEmpruntes();
		
		//THEN
		assertTrue(resultat.equals("Aucun livre n'a été emprunté actuellement."));
		assertTrue(resultat2.equals("Doudou\tzbl\nDoudou\tjava\n"));
	}

	
	@Test
	void testListerLivresAnglais() {
		//GIVEN
		Client client = new Client("Michel Pertinent", false);
		Client client2 = new Client("Patati Patata", true);
		Auteur auteur = new Auteur("Doudou");
		Livre ancienLivre = new Livre(auteur, "java");
		Livre ancienLivre2 = new Livre(auteur, "zbl");
		Etiquette etiquette = new Etiquette(true, "sf");
		Etiquette etiquette2 = new Etiquette(false, "sf");
		bibliothecaire.preterLivre(client2, ancienLivre2, etiquette2);
		
		//WHEN
		String resultat2 = bibliothecaire.getRegistre().listerLivresAnglais();
		bibliothecaire.preterLivre(client, ancienLivre, etiquette);
		String resultat = bibliothecaire.getRegistre().listerLivresAnglais();
		
		//THEN
		assertTrue(resultat.equals("Doudou\tjava\n"));
		assertTrue(resultat2.equals("Aucun livre en anglais n'a été emprunté actuellement."));
	}
	
	
	@Test
	void testListerNbLivresEmpruntesPourUnAuteur() {
		//GIVEN
		Client client = new Client("Michel Pertinent", false);
		Client client2 = new Client("Patati Patata", true);
		Auteur auteur = new Auteur("Doudou");
		Auteur auteur2 = new Auteur("Doudidou");
		Auteur auteur3 = new Auteur("Doudidouda");
		Livre ancienLivre = new Livre(auteur, "java");
		Livre ancienLivre2 = new Livre(auteur, "zbl");
		Livre ancienLivre3 = new Livre(auteur3, "piscine");
		Etiquette etiquette = new Etiquette(false, "sf");
		bibliothecaire.preterLivre(client, ancienLivre, etiquette);
		bibliothecaire.preterLivre(client2, ancienLivre2, etiquette);
		bibliothecaire.preterLivre(client2, ancienLivre3, etiquette);
		
		//WHEN
		String resultat = bibliothecaire.getRegistre().listerLivresEmpruntes(auteur);
		String resultat2 = bibliothecaire.getRegistre().listerLivresEmpruntes(auteur2);
		String resultat3 = bibliothecaire.getRegistre().listerLivresEmpruntes(auteur3);
		
		//THEN
		assertTrue(resultat.equals("Au total, " + 2 + " livres de l'auteur nommé " + "Doudou" + " ont été empruntés."));
		assertTrue(resultat2.equals("Aucun livre de cet auteur n'a été emprunté."));
		assertTrue(resultat3.equals("Un seul livre de l'auteur nommé Doudidouda a été emprunté."));
		
	}
	
	
	@Test
	void testTrouverLivreSurUnTheme() {
		//GIVEN
		Client client = new Client("Michel Pertinent", false);
		Client client2 = new Client("Patati Patata", true);
		Auteur auteur = new Auteur("Doudou");
		Livre ancienLivre = new Livre(auteur, "java");
		Livre ancienLivre2 = new Livre(auteur, "zbl");
		Livre ancienLivre3 = new Livre(auteur, "zbl");
		Etiquette etiquette = new Etiquette(false, "sf");
		Etiquette etiquette2 = new Etiquette(false, "polar");
		bibliothecaire.preterLivre(client, ancienLivre, etiquette);
		bibliothecaire.preterLivre(client2, ancienLivre2, etiquette);
		bibliothecaire.preterLivre(client2, ancienLivre3, etiquette2);
		
		//WHEN
		String result = bibliothecaire.getRegistre().listerLivreSurUnTheme("sf");
		String result2 = bibliothecaire.getRegistre().listerLivreSurUnTheme("theatre");
		
		//THEN
		assertTrue(result.equals("Doudou\tzbl\nDoudou\tjava\n"));
		assertTrue(result2.equals("Aucun livre ne correspond au thème donné."));
	}
	
	
	@Test
	void testEnvoyerAmendeRetardaire() {
		//GIVEN
		Client client = new Client("Michel Pertinent", false);
		Client client2 = new Client("Patati Patata", true);
		Auteur auteur = new Auteur("Doudou");
		Livre ancienLivre = new Livre(auteur, "java");
		Livre ancienLivre2 = new Livre(auteur, "zbl");
		Etiquette etiquette = new Etiquette(false, "sf");
		bibliothecaire.preterLivre(client, ancienLivre, etiquette);
		bibliothecaire.preterLivre(client2, ancienLivre2, etiquette);
		client.setAmande(true);
		client2.setAmande(true);
		//WHEN
		bibliothecaire.getRegistre().envoyerAmendeRetardataire();
		
		//THEN
		assertTrue(bibliothecaire.getRegistre().getEmprunts().keySet().contains(client));
		assertTrue(bibliothecaire.getRegistre().getEmprunts().keySet().contains(client2));
	}
	
	

	@Test
	void testEncaisserAmendeRetardaire() {
		
		//GIVEN
		Client client = new Client("Michel Pertinent", false);
		Client client2 = new Client("Patati Patata", true);
		Auteur auteur = new Auteur("Doudou");
		Livre ancienLivre = new Livre(auteur, "java");
		Livre ancienLivre2 = new Livre(auteur, "zbl");
		Etiquette etiquette = new Etiquette(false, "sf");
		bibliothecaire.preterLivre(client, ancienLivre, etiquette);
		bibliothecaire.preterLivre(client2, ancienLivre2, etiquette);
		bibliothecaire.getRegistre().envoyerAmendeRetardataire();
		
		//WHEN
		bibliothecaire.getRegistre().encaisserAmendeRetardataire();
		//Aucune amende à encaisser s'afficher sur la sortie standard si aucun client n'a reçu d'amende.
		bibliothecaire.getRegistre().encaisserAmendeRetardataire();
		
		//THEN
		assertTrue(bibliothecaire.getRegistre().getEmprunts().keySet().contains(client));
		assertTrue(bibliothecaire.getRegistre().getEmprunts().keySet().contains(client2));
		assertTrue(bibliothecaire.getRegistre().getCaisseAmende() == 20);
	}
	
}
