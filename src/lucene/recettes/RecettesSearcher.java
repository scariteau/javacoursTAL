package lucene.recettes;

import java.io.IOException;
import java.util.Scanner;

import org.apache.lucene.queryparser.classic.ParseException;

import lucene.moteurRecherche.MoteurLucene;


public class RecettesSearcher {

	public static void main(String[] args) throws IOException, ParseException {

		if (args.length > 1) {
			MoteurLucene moteurLucene = new MoteurLucene(args[0], args[1]);
			try {
				moteurLucene.createIndex(true);
				if (args.length == 3)
					moteurLucene.search(args[2]);

				boolean continuer = true;
				Scanner scanner = new Scanner(System.in);
				while (continuer) {
					System.out.println("Saisissez la terme a chercher. Saisissez exit pour quitter");
					String termeAchercher = scanner.next();
					if ("exit".equalsIgnoreCase(termeAchercher)) {
						continuer = false;
					} else
						System.out.println("Au revoir");
					moteurLucene.search(termeAchercher);
				}
				scanner.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
