package lucene.moteurRecherche;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class SimpleIndexer {

	protected static final String CONTENU = "recette";
	protected static final String TITRE = "nomRecette";
	private Indexer indexer;

	public SimpleIndexer(String indexDir) {
		try {
			this.indexer = new Indexer(indexDir);
		} catch (IOException e) {
			System.out.println("Une erreur est survenue lors de la creation de l'index: " + e.getMessage());
		}
	}

	private Document getDocument(String titre, String contenu) throws IOException {

		Document document = new Document();
		document.add(new StringField(TITRE, titre, Field.Store.YES));
		document.add(new TextField(CONTENU, contenu, Field.Store.YES));
		// document.add(new TextField("contenu", contenu, Field.Store.NO));
		return document;
	}

	private int indexFile(String titre, String contenu) throws IOException {
		System.out.println("Indexing " + contenu);
		Document document = getDocument(titre, contenu);
		return indexer.createIndex(document);
	}

	public int createIndex() throws IOException {

		String doc1_contenu = "Lavez les tomates cerises et coupez-les en deux. Lavez et essorez les pousses d’épinards. Égouttez et coupez les mini-maïs en rondelles. Préparez une vinaigrette avec l’huile d’olive, le vinaigre balsamique, le sel et le poivre. Assaisonnez les tomates cerises, les mini-maïs et les pousses d’épinards";
		String doc2_contenu = "Répartissez les pousses d’épinards, les tomates cerises et les rondelles de maïs dans un plat. Ajoutez les pommes de terre rôties par-dessus et servez immédiatement";
		String doc3_contenu = "Répartissez les pousses d’épinards, les tomates cerises et les rondelles de maïs dans un plat. Ajoutez les pommes de terre rôties par-dessus et servez immédiatement. Arrosez les pommes de terre d’un généreux filet d’huile d’olive, salez et poivrez. Enfournez et faites-les cuire pendant 40 min en les mélangeant régulièrement.";
		String doc4_contenu = "Répartissez les pousses d’épinards.";
		String doc5_contenu = "Répartissez les pousses d’épinards, les tomates cerises et les rondelles de maïs dans un plat.avez les tomates cerises et coupez-les en deux. Lavez et essorez les pousses d’épinards.Assaisonnez les tomates cerises, les mini-maïs et les pousses d’épinards.";
		String[] doc_contenu = { "", doc1_contenu, doc2_contenu, doc3_contenu, doc4_contenu, doc5_contenu };
		for (int i = 0; i < doc_contenu.length; i++) {
			indexFile(TITRE + i, doc_contenu[i]);
		}

		int nbFichiersIndexees = indexer.getNbFichiersIndexes();
		indexer.close();
		return nbFichiersIndexees;
	}

}
