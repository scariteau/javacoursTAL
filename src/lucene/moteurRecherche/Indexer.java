package lucene.moteurRecherche;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Indexer {
	private IndexWriter writer;

	public Indexer(String indexDirectoryPath) throws IOException {
		Directory indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath));
		StandardAnalyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		writer = new IndexWriter(indexDirectory, iwc);
	}

	public void close() throws CorruptIndexException, IOException {
		writer.close();
	}

	public int createIndex(Document document) throws IOException {
		System.out.println("Indexing " + document);
		writer.addDocument(document);
		return writer.numRamDocs();
	}
	
	public int getNbFichiersIndexes() {
		return writer.numRamDocs();
	}

}