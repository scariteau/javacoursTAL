package lucene.moteurRecherche;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;

public class FileIndexer {
	private Indexer indexer;

	public FileIndexer(String indexDir) {
		try {
			this.indexer = new Indexer(indexDir);
		} catch (IOException e) {
			System.out.println("Une erreur est survenue lors de la creation de l'index: " + e.getMessage());
		}
	}

	private Document getDocument(File file) throws IOException {
		Document document = new Document();
		TextField contentField = new TextField(LuceneConstants.CONTENTS, new String(Files.readAllBytes(file.toPath())),
				TextField.Store.YES);
		TextField fileNameField = new TextField(LuceneConstants.FILE_NAME, file.getName(), TextField.Store.YES);
		TextField filePathField = new TextField(LuceneConstants.FILE_PATH, file.getCanonicalPath(),
				TextField.Store.YES);
		document.add(contentField);
		document.add(fileNameField);
		document.add(filePathField);
		return document;
	}

	private int indexFile(File file) throws IOException {
		System.out.println("Indexing " + file.getCanonicalPath());
		Document document = getDocument(file);
		return indexer.createIndex(document);
	}

	public int createIndex(String dataDirPath, FileFilter filter) throws IOException {
		File[] files = new File(dataDirPath).listFiles();

		for (File file : files) {
			if (!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead() && filter.accept(file)) {
				indexFile(file);
			}
		}
		int nbFichiersIndexees = indexer.getNbFichiersIndexes();
		indexer.close();
		return nbFichiersIndexees;
	}
}