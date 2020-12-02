package traitementTextes;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import etudiants.EtudiantTAL;

class SerialisateurTest {

	private static final String Nom_Etudiant = "Gerard";
	static final String Fichier_Obj_Serialise = "C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\traitementTextes\\objetSerialise.txt";
	static final String Fichier_Obj_ASerialise = "C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\traitementTextes\\objetASerialise.txt";

	@Test
	void testSerialiseObject() {

		EtudiantTAL objetAserialiser = new EtudiantTAL("Mariette", 1235);
		Serialisateur.serialiseObject(Fichier_Obj_ASerialise, objetAserialiser);
		File fichierAvecObjetSerialise = new File(Fichier_Obj_ASerialise);
		assertTrue(fichierAvecObjetSerialise.exists());

		ParsingFichier parsingFichier = new ParsingFichier();
		try {
			String contenuStocke = parsingFichier.lireFichierAvecBufferedReader(Fichier_Obj_ASerialise);

			assertNotNull(contenuStocke);
			assertTrue(contenuStocke.length() > 0);
			assertTrue(contenuStocke.contains("EtudiantTAL"));

		} catch (IOException e) {
			fail("Une exception est apparue pendant la lecture du fichier==" + e.getMessage());
		}
	}

	@Test
	void testDeSerialiseObject() {

		Object objetDeserialise = Serialisateur.deSerialiseObject(Fichier_Obj_Serialise);

		assertNotNull(objetDeserialise);

		EtudiantTAL etudiant = (EtudiantTAL) objetDeserialise;

		assertNotNull(etudiant);
		assertEquals(etudiant.nom, Nom_Etudiant);
	}

	@Test
	void testSerialiserEtDeSerialiseXml() {
		fail("Not yet implemented");
	}

}
