package devoirs.solveigCamilleJuliette.bibliotheque;

public class Livre {
	public String titre;
	public String auteur;
	public String nb_tomes;
	public String date_publication;
	public String resume;

	public Livre(String titre, String auteur, String date_publication, String resume) {
		this.titre = titre;
		this.auteur = auteur;
		this.nb_tomes = "1";
		this.date_publication = date_publication;
		this.resume = resume;
	}
	
	//surcharge du constructeur 
	public Livre(String infoslivre) {
		this.titre = FichierXML.getBaliseContent(infoslivre, "titre");
    	this.auteur = FichierXML.getBaliseContent(infoslivre, "auteur");
    	this.date_publication = FichierXML.getBaliseContent(infoslivre, "date_publication");
    	this.resume = FichierXML.getBaliseContent(infoslivre, "resume");
    	String nb_tomes = FichierXML.getBaliseContent(infoslivre, "nb_tomes");
    	if (nb_tomes != "") {
    		this.nb_tomes = nb_tomes;
    	}
    	else {
    		this.nb_tomes = "1";
    	}
	}

	public void setNb_tomes(String nb_tomes) {
		this.nb_tomes = nb_tomes;
	}
}
