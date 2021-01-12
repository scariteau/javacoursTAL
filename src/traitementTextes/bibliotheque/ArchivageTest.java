package traitementTextes.bibliotheque;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import traitementTextes.Serialisateur;
import traitementTextes.bibliotheque.catalogue.Auteur;
import traitementTextes.bibliotheque.catalogue.Livre;

class ArchivageTest {

	private Archivage archivage;
	private HashMap<Auteur, ArrayList<Livre>> catalogue;

	@BeforeEach
	void setUp() throws Exception {
		archivage = new Archivage();
		catalogue = new HashMap<>();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testArchiverCatalogue() {

		// GIVEN
		String pathFichierArchive = "C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\traitementTextes\\bibliotheque\\bibliothequeSerialisee.txt";

		Auteur emilieBronte =new Auteur("Emilie Bronte");
		ArrayList<Livre> livresEmilie=new ArrayList<>();
		Livre premierLivre=new Livre(emilieBronte, "premier livre emilie");
		livresEmilie.add(premierLivre);
		Livre deuxiemLivre=new Livre(emilieBronte, "premier livre emilie");
		livresEmilie.add(deuxiemLivre);
		catalogue.put(emilieBronte, livresEmilie);
		
		// WHEN
		archivage.archiverCatalogue(catalogue, pathFichierArchive);

		// THEN
		File file = new File(pathFichierArchive);
		assertTrue(file.exists());
		
		HashMap<Auteur, ArrayList<Livre>> catalogueStocke= (HashMap<Auteur, ArrayList<Livre>>) Serialisateur.deSerialiseObject(pathFichierArchive);
		assertNotNull(catalogueStocke);
		assertTrue(catalogueStocke.containsKey(emilieBronte));
		
	}

	@Test
	void testInitialiserCatalogueAvecXml() {

		// GIVEN
		String pathFichierXml = "C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\traitementTextes\\bibliotheque\\bibliotheque.xml";

		// WHEN
		archivage.initialiserCatalogueAvecXml(pathFichierXml, catalogue);

		// THEN
		assertNotNull(catalogue);
		Auteur auteur = new Auteur("Victor Hugo");
		assertTrue(catalogue.containsKey(auteur));
		Auteur sosieVictor = new Auteur("Victor Hugo le sosie");
		assertFalse(catalogue.containsKey(sosieVictor));

	}

}
