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

		String doc1_contenu = "Lavez les tomates cerises et coupez-les en deux. Lavez et essorez les pousses d��pinards. �gouttez et coupez les mini-ma�s en rondelles. Pr�parez une vinaigrette avec l�huile d�olive, le vinaigre balsamique, le sel et le poivre. Assaisonnez les tomates cerises, les mini-ma�s et les pousses d��pinards";
		String doc2_contenu = "R�partissez les pousses d��pinards, les tomates cerises et les rondelles de ma�s dans un plat. Ajoutez les pommes de terre r�ties par-dessus et servez imm�diatement";
		String doc3_contenu = "R�partissez les pousses d��pinards, les tomates cerises et les rondelles de ma�s dans un plat. Ajoutez les pommes de terre r�ties par-dessus et servez imm�diatement. Arrosez les pommes de terre d�un g�n�reux filet d�huile d�olive, salez et poivrez. Enfournez et faites-les cuire pendant 40 min en les m�langeant r�guli�rement.";
		String doc4_contenu = "R�partissez les pousses d��pinards.";
		String doc5_contenu = "R�partissez les pousses d��pinards, les tomates cerises et les rondelles de ma�s dans un plat.avez les tomates cerises et coupez-les en deux. Lavez et essorez les pousses d��pinards.Assaisonnez les tomates cerises, les mini-ma�s et les pousses d��pinards.";
		String[] doc_contenu = { "", doc1_contenu, doc2_contenu, doc3_contenu, doc4_contenu, doc5_contenu };
		for (int i = 0; i < doc_contenu.length; i++) {
			indexFile(TITRE + i, doc_contenu[i]);
		}

		int nbFichiersIndexees = indexer.getNbFichiersIndexes();
		indexer.close();
		return nbFichiersIndexees;
	}

}
