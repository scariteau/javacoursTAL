package devoirs.chinatsuLaraXinyi.bibliotheque;

public class Livre {
	
	private String titre;
	private String auteur;
	private String nbTomes;
	private String datePublication;
	private String resume;

	public Livre(String titre, String auteur, String nbTomes, String datePublication, String resume) {
		this.titre = titre;
		this.auteur = auteur;
		this.nbTomes = nbTomes;
		this.datePublication = datePublication;
		this.resume = resume;
	}
	
	public Livre(String titre) {
		this.titre = titre;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getNbTomes() {
		return nbTomes;
	}

	public void setNbTomes(String nbTomes) {
		this.nbTomes = nbTomes;
	}

	public String getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(String datePublication) {
		this.datePublication = datePublication;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

}
