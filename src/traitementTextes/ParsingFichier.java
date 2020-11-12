package traitementTextes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ParsingFichier {
	
	//java traitementTextes.ParsingFichier C:\Users\33616\Documents\Boulot\javacours\javacoursTALCode\src\traitementTextes\fichierAparser.txt

	protected void lireFichierAvecScanner(String path) throws IOException {

		java.util.Scanner lecteur;

		java.io.File fichier = new java.io.File(path);
		lecteur = new java.util.Scanner(fichier);

		String somme = "";
		while (lecteur.hasNext())
			somme += lecteur.next();
		System.out.println(somme);

		lecteur.close();
	}

	protected void lireFichierAvecBufferedReader(String path) throws IOException {
		BufferedReader lecteurAvecBuffer = null;
		String ligne;

		lecteurAvecBuffer = new BufferedReader(new FileReader(path));

		while ((ligne = lecteurAvecBuffer.readLine()) != null)
			System.out.println(ligne);
		lecteurAvecBuffer.close();
	}

	public static void main(String[] args) {

		ParsingFichier parsingFichier = new ParsingFichier();

		try {
			System.out.println("lecture fichier avec lireFichierAvecScanner");
			parsingFichier.lireFichierAvecScanner(args[0]);

			System.out.println("lecture fichier avec lireFichierAvecBufferedReader");
			parsingFichier.lireFichierAvecBufferedReader(args[0]);

		} catch (FileNotFoundException exc) {
			System.out.println("Erreur d'ouverture du fichier");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
