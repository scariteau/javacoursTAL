package traitementTextes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class ParsingFichier {

	// java traitementTextes.ParsingFichier
	// C:\Users\33616\Documents\Boulot\javacours\javacoursTALCode\src\traitementTextes\fichierAparser.txt

	/**
	 * Lire dans un fichier avec la classe Scanner
	 * @param path : chemin vers le fichier a lire
	 * @throws IOException
	 */
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

	/**
	 * Lire dans un fichier avec la classe BufferedReader
	 * @param path : chemin vers le fichier a lire
	 * @throws IOException
	 */
	protected void lireFichierAvecBufferedReader(String path) throws IOException {
		BufferedReader lecteurAvecBuffer = null;
		String ligne;

		lecteurAvecBuffer = new BufferedReader(new FileReader(path));

		while ((ligne = lecteurAvecBuffer.readLine()) != null)
			System.out.println(ligne);
		lecteurAvecBuffer.close();
	}

	/**
	 * Ecrire dans un fichier avec la classe BufferedWriter
	 * @param path : chemin vers le fichier a lire
	 * @throws IOException
	 */
	protected void ecrireFichierAvecBufferedWriter(String path) throws IOException {

		String content = "Je suis une nouvelle ligne dans le fichier == " + path;

		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();

		System.out.println("Ecriture dans le fichier terminee");

	}

	/**
	 *  Ecrire dans un fichier avec la classe Files
	 * @param path : chemin vers le fichier a lire
	 * @throws IOException
	 */
	protected void modifierFichierAvecFiles(String path) throws IOException {

		String content = "\n Je suis une 2eme ligne dans le fichier avec Files";

		List<String> lignes = Arrays.asList(content);
		Path fichier = Paths.get(path);

		Files.write(fichier, lignes, Charset.forName("UTF-8"), StandardOpenOption.APPEND);

	}

	public static void main(String[] args) {

		ParsingFichier parsingFichier = new ParsingFichier();

		try {
			System.out.println("lecture fichier avec lireFichierAvecScanner");
			parsingFichier.lireFichierAvecScanner(args[0]);

			System.out.println("lecture fichier avec lireFichierAvecBufferedReader");
			parsingFichier.lireFichierAvecBufferedReader(args[0]);

			System.out.println("Ecriture fichier avec ecrireFichierTxtBufferedReader");
			parsingFichier.ecrireFichierAvecBufferedWriter(args[1]);

			System.out.println("Modifier fichier avec ajouterFichierTxtFiles");
			parsingFichier.modifierFichierAvecFiles(args[1]);

		} catch (FileNotFoundException exc) {
			System.out.println("Erreur d'ouverture du fichier");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
