package devoirs2.chinatsuLaraXinyi;

import java.io.Serializable;

public class Livre implements Serializable  {

	private static final long serialVersionUID = 1L;
	private Auteur auteur;
	private String titre;
	private String resume;
	private String langue;
	private int anneePublication;
	private int nbTomes;
	private String theme;
	
	public Livre(Auteur auteur,String titre) {
		this.auteur=auteur;
		this.titre=titre;
	}
	
	public Livre(Auteur auteur,String titre, String langue) {
		this.auteur=auteur;
		this.titre=titre;
		this.langue=langue;
	}
	
	public Livre(Auteur auteur,String titre, String langue, String theme ) {
		this.auteur=auteur;
		this.titre=titre;
		this.langue=langue;
		this.theme =theme;
	}
	
	public Auteur getAuteur() {
		return auteur;
	}

	public int getAnneePublication() {
		return anneePublication;
	}

	public void setAnneePublication(int anneePublication) {
		this.anneePublication = anneePublication;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNbTomes() {
		return nbTomes;
	}

	public void setNbTomes(int nbTomes) {
		this.nbTomes = nbTomes;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
}

