package lucene.moteurRecherche;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class MoteurLucene {
	private String indexDir;
	private String dataDir;

	private FileIndexer indexer;
	private Searcher searcher;

	public  MoteurLucene(String indexDir, String dataDir) {
		this.indexDir = indexDir;
		this.dataDir = dataDir;

	}

	public void createIndex(boolean storeContents) throws IOException {
		indexer = new FileIndexer(indexDir,storeContents);
		int numIndexed;
		long startTime = System.currentTimeMillis();
		numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
		long endTime = System.currentTimeMillis();
		System.out.println(numIndexed + " Fichiers indexees en: " + (endTime - startTime) + " ms");
	}

	public void search(String searchQuery) throws IOException, ParseException {
		searcher = new Searcher(indexDir);
		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.search(searchQuery);
		long endTime = System.currentTimeMillis();

		System.out.println(hits.totalHits + " fichiers found. Temps de recherche :" + (endTime - startTime) +"millisecondes");
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			
			System.out.println("Nom du fichier contenant le terme chercher: " + doc.get(LuceneConstants.FILE_NAME));
			System.out.println("Path du fichier contenant le terme chercher: " + doc.get(LuceneConstants.FILE_PATH));
			System.out.println("Contenu du fichier contenant le terme chercher: " + doc.get(LuceneConstants.CONTENTS));
		}
	}

	public String getIndexDir() {
		return indexDir;
	}

	public void setIndexDir(String indexDir) {
		this.indexDir = indexDir;
	}

	public String getDataDir() {
		return dataDir;
	}

	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}

	public FileIndexer getIndexer() {
		return indexer;
	}

	public void setIndexer(FileIndexer indexer) {
		this.indexer = indexer;
	}

	public Searcher getSearcher() {
		return searcher;
	}

	public void setSearcher(Searcher searcher) {
		this.searcher = searcher;
	}
}
