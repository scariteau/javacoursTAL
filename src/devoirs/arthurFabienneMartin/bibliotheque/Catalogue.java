package devoirs.arthurFabienneMartin.bibliotheque;

public class Catalogue {

	public static void main(String[] args) {
		if(args.length == 1 && (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("h"))) {
			System.out.println("\nUtilisation :\nAfficher l'auteur et ses oeuvres - Catalogue 'auteur'\nAfficher le détail d'un livre - Catalogue 'Auteur' 'Titre'");
			System.out.println("Ajouter un livre au fichier - Catalogue 'Auteur' 'Titre' 'Nombre de tomes' 'Date de publication' 'Resume'");
			System.out.println("Retirer un livre du fichier - Catalogue 'Auteur' 'Titre' 'Date de publication'");
		}
		else if(args.length == 1) {
			Bibliotheque biblio = new Bibliotheque("C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\devoirs\\arthurFabienneMartin\\bibliotheque\\bibliotheque.xml");
			System.out.println(biblio.catalogue(args[0]));
		}
		else if(args.length == 2) {
			Bibliotheque biblio = new Bibliotheque("C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\devoirs\\arthurFabienneMartin\\bibliotheque\\bibliotheque.xml");
			System.out.println(biblio.catalogue(args[0], args[1]));
		}
		else if(args.length == 3) {
			Bibliotheque biblio = new Bibliotheque("C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\devoirs\\arthurFabienneMartin\\bibliotheque\\bibliotheque.xml");
			biblio.retirerLivre(args[0], args[1], args[2]);
			biblio.sauvegarderBiblio("C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\devoirs\\arthurFabienneMartin\\bibliotheque\\bibliotheque.xml");
		}
		else if(args.length == 5) {
			try {
			Integer.parseInt(args[2]);
			Integer.parseInt(args[3]);
			}
			catch (Exception e) {
				System.out.println("Erreur : Le nombre de tomes et la date de publication doivent être un nombre entier.");
				System.exit(0);
			}
			Bibliotheque biblio = new Bibliotheque("C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\devoirs\\arthurFabienneMartin\\bibliotheque\\bibliotheque.xml");
			biblio.ajouterLivre(args[0], args[1], args[2], args[3], args[4]);
			biblio.sauvegarderBiblio("C:\\Users\\33616\\Documents\\Boulot\\javacours\\javacoursTALCode\\src\\devoirs\\arthurFabienneMartin\\bibliotheque\\bibliotheque.xml");
		}
		else {
			System.out.println("Catalogue 'help' pour afficher l'aide.");
		}
	}//main
}//Lancer