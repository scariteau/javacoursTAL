package devoirs.solveigCamilleJuliette.bibliotheque;

//héritage
public class LivreVO extends Livre {
	
	public String langue;

	public LivreVO(String titre, String auteur, String date_publication, String resume, String langue) {
		super(titre, auteur, date_publication, resume);
		this.langue = langue;
	}

	public LivreVO(String infoslivre) {
		super(infoslivre);
		this.langue = FichierXML.getAttributeContent(infoslivre, "titre", "lang");
	}
}
