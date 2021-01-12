package traitementTextes.bibliotheque.catalogue;

import java.io.Serializable;

public class Livre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Auteur auteur;
	private String titre;
	private String resume;
	private int anneePublication;
	private int nbTomes;
	private String theme;

	public Livre(Auteur auteur, String titre) {
		this.auteur = auteur;
		this.titre = titre;
	}

	@Override
	public boolean equals(Object livreIdentique) {
		if (this == livreIdentique) {
			return true;
		}
		if (livreIdentique instanceof Livre) {
			return (titre.equals(((Livre) livreIdentique).getTitre())
					&& (auteur.equals(((Livre) livreIdentique).getAuteur())));
		}
		return false;

	}

	@Override
	public int hashCode() {
		return titre.hashCode() + auteur.hashCode();
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

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}
