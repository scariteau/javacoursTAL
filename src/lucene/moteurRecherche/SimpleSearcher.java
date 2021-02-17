package lucene.moteurRecherche;

import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class SimpleSearcher {

	public void search(String searchQuery, String indexDirectoryPath) {

		try {

			StandardAnalyzer analyseur = new StandardAnalyzer();
			// Analyzer analyseur = new FrenchAnalyzer();
			String[] champs_recherche = { SimpleIndexer.TITRE, SimpleIndexer.CONTENU };
			QueryParser parser = new MultiFieldQueryParser(champs_recherche, analyseur);

			Query query = parser.parse(searchQuery);

			Directory indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath));
			IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(indexDirectory));

			TopDocs results = searcher.search(query, 4096);
			ScoreDoc[] hits = results.scoreDocs;

			System.out.println("Resultat: " + hits.length + " documents contenaient " + searchQuery);
			for (int i = 0; i < hits.length; i++) {
				Document doc = searcher.doc(hits[i].doc);
				String contenu = doc.get(SimpleIndexer.CONTENU);
				String titre = doc.get(SimpleIndexer.TITRE);
				System.out.println(" - Titre: " + titre + " - Contenu: " + contenu);
			}
		} catch (Exception e) {
			System.err.println("Erreur rencontrée: " + e.toString());
		}
	}
}
