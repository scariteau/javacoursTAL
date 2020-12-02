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

import etudiants.EtudiantTALInalco;

public class ParsingFichier {

	// java traitementTextes.ParsingFichier
	// C:\Users\33616\Documents\Boulot\javacours\javacoursTALCode\src\traitementTextes\fichierAparser.txt
	// C:\Users\33616\Documents\Boulot\javacours\javacoursTALCode\src\traitementTextes\nouveauFichierCreer.txt

	/**
	 * Lire dans un fichier avec la classe Scanner
	 * 
	 * @param path : chemin vers le fichier a lire
	 * @throws IOException
	 */
	protected String lireFichierAvecScanner(String path) throws IOException {

		java.util.Scanner lecteur;

		java.io.File fichier = new java.io.File(path);
		lecteur = new java.util.Scanner(fichier);

		String contenuFichier = "";
		while (lecteur.hasNext())
			contenuFichier += lecteur.next();
		System.out.println(contenuFichier);
		lecteur.close();
		return contenuFichier;
	}

	/**
	 * Lire dans un fichier avec la classe BufferedReader tester
	 * 
	 * @param path : chemin vers le fichier a lire
	 * @throws IOException
	 */
	protected String lireFichierAvecBufferedReader(String path) throws IOException {
		BufferedReader lecteurAvecBuffer = null;
		String contenuFichier="";
		String line="";
		lecteurAvecBuffer = new BufferedReader(new FileReader(path));

		while ((line = lecteurAvecBuffer.readLine()) != null)
			contenuFichier=contenuFichier+line;
		lecteurAvecBuffer.close();
		return contenuFichier;
	}

	/**
	 * Ecrire dans un fichier avec la classe BufferedWriter
	 * 
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
	 * Ecrire dans un fichier avec la classe Files
	 * 
	 * @param path           : chemin vers le fichier a lire
	 * @param contenuAjouter : contenu a ajouter au fichier
	 * @throws IOException
	 */

	protected void modifierFichierAvecFiles(String path, String contenuAjouter) throws IOException {

		List<String> lignes = Arrays.asList(contenuAjouter);
		Path fichier = Paths.get(path);
		Files.write(fichier, lignes, Charset.forName("UTF-8"), StandardOpenOption.APPEND);

	}

	/**
	 * Lire dans un fichier avec la classe Scanner
	 * 
	 * @param path      : chemin vers le fichier a lire
	 * @param occurence : texte a rechercher
	 * @throws IOException
	 */
	protected void rechercherNbChiffres(String path) throws IOException {

		java.util.Scanner lecteur;

		java.io.File fichier = new java.io.File(path);
		lecteur = new java.util.Scanner(fichier);

		int contenuLigne;
		int nbChiffres = 0;
		while (lecteur.hasNextInt()) {
			contenuLigne = lecteur.nextInt();
			System.out.println("Chiffre lue==" + contenuLigne);
			nbChiffres++;

		}
		System.out.println("Nombre de chiffres dans ce texte ==" + nbChiffres);
		lecteur.close();
	}

	protected void rechercherNbOccurences(String path, String occurence) throws IOException {
		BufferedReader lecteurAvecBuffer = null;
		String ligne;

		lecteurAvecBuffer = new BufferedReader(new FileReader(path));

		int nbOccurence = 0;
		while ((ligne = lecteurAvecBuffer.readLine()) != null)

		{
			System.out.println("Ligne lue==" + ligne);
			if (ligne.contains(occurence))
				nbOccurence++;

		}
		System.out.println("NB occurence du mot " + occurence + "==" + nbOccurence);
		lecteurAvecBuffer.close();
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
			parsingFichier.modifierFichierAvecFiles(args[1], "\n une nouvelle ligne ajouter au fichier");

			EtudiantTALInalco sylvie = new EtudiantTALInalco("Sylvie", 1235, "japonais");

			System.out.println("Modifier fichier avec ajouterFichierTxtFiles avec un etudiant");
			parsingFichier.modifierFichierAvecFiles(args[1], "\n" + sylvie.toString());

			parsingFichier.rechercherNbOccurences(args[2], "Il meurt lentement");

			parsingFichier.rechercherNbChiffres(args[2]);

		} catch (FileNotFoundException exc) {
			System.out.println("Erreur d'ouverture du fichier");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
