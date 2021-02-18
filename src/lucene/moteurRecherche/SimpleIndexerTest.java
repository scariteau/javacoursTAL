package lucene.moteurRecherche;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleIndexerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void testCreateSimpleIndex() throws IOException, ParseException {

		String path = new File("src/lucene/moteurRecherche/ressources").getAbsolutePath();
		String indexDir = path.concat("/indexsimple");
		
		SimpleIndexer simpleIndexer=new SimpleIndexer(indexDir);
		simpleIndexer.createIndex();
	}
	
	@Test
	void testSearchSimpleIndex() throws IOException, ParseException {

		String path = new File("src/lucene/moteurRecherche/ressources").getAbsolutePath();
		String indexDir = path.concat("/indexsimple");
		
		SimpleSearcher simpleSearcher=new SimpleSearcher();
		simpleSearcher.search("huile d’olive", indexDir);
		//simpleSearcher.search("recette:rondelles AND de AND maïs", indexDir);
		//simpleSearcher.search("nomRecette:huile*", indexDir);
		
	}

}
