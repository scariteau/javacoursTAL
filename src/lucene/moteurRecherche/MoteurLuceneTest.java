package lucene.moteurRecherche;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.jupiter.api.Test;

class MoteurLuceneTest {

	MoteurLucene moteurLucene;

	@Test
	void testCreateIndex() throws IOException, ParseException {

		String path = new File("src/lucene/moteurRecherche/ressources").getAbsolutePath();
		String indexDir = path.concat("/index");
		String dataDir = path.concat("/textes");
		moteurLucene = new MoteurLucene(indexDir, dataDir);
		moteurLucene.createIndex(true);
		moteurLucene.search("don");
	}

	@Test
	void testCreateIndexPerfTests1Fichier() throws IOException, ParseException {

		initMoteur("/index1Fichiers","/textes1Fichiers");
		moteurLucene.createIndex(false);
		long avantRecherche10Fichiers = System.currentTimeMillis();
		moteurLucene.search("amour");
		long apresRecherche10Fichiers = System.currentTimeMillis();
		System.out.println("Ça a pris " + (apresRecherche10Fichiers - avantRecherche10Fichiers) / 1000.0
				+ " secondes pour rechercher dans un index avec 1 fichiers");

	}

	@Test
	void testCreateIndexPerfTests10Fichiers() throws IOException, ParseException {

		initMoteur("/index10Fichiers","/textes10Fichiers");
		//moteurLucene.createIndex(false);
		long avantRecherche10Fichiers = System.currentTimeMillis();
		//moteurLucene.search("collaborateur");
		moteurLucene.search("amour");
		long apresRecherche10Fichiers = System.currentTimeMillis();
		System.out.println("Ça a pris " + (apresRecherche10Fichiers - avantRecherche10Fichiers) / 1000.0
				+ " secondes pour rechercher dans un index avec 10 fichiers");

	}
	
	
	@Test
	void testCreateIndexPerfTests20Fichiers() throws IOException, ParseException {

		initMoteur("/index20Fichiers", "/textes20Fichiers");
		//moteurLucene.createIndex(false);
		long avantRecherche10Fichiers = System.currentTimeMillis();
		//moteurLucene.search("collaborateur");
		moteurLucene.search("amour");
		long apresRecherche10Fichiers = System.currentTimeMillis();
		System.out.println("Ça a pris " + (apresRecherche10Fichiers - avantRecherche10Fichiers) / 1000.0
				+ " secondes pour rechercher dans un index avec 20 fichiers");

	}


	private void initMoteur(String nomRepertoireIndex, String nomRepertoireFichiers) {
		String path = new File("src/lucene/moteurRecherche/ressources").getAbsolutePath();
		String indexDir = path.concat(nomRepertoireIndex);
		String dataDir = path.concat(nomRepertoireFichiers);
		moteurLucene = new MoteurLucene(indexDir, dataDir);
	}

}
