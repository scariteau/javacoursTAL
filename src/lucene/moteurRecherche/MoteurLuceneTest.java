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
		moteurLucene.createIndex();
		moteurLucene.search("don");
	}
	
}
