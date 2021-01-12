package devoirs2.jianyingMeiSheherazade;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * Classe Livre
 *
 *
 * @author
 * @version 1.2, 10 Jan 2021
 */

public class Livre implements Serializable  {
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
	private String langue;
	private boolean preter = false;
	private Date dateRetour;
	private Emprunteur emprunteur;
	private double amende=0;
	private boolean horsDelai = false;

	/**
	 * Cette methode retourne l'emprunteur du livre
	 * @return emprunteur qui est de type Emprunteur
	 *
	 */
	public Emprunteur getEmprunteur() {
		return emprunteur;
	}


	/**
	 * Cette methode enregistre l emprunteur du livre
	 * @param emprunteur qui est de type Emprunteur
	 *
	 */
	public void setEmprunteur(Emprunteur emprunteur) {

		this.emprunteur = emprunteur;
		this.setPreter(true);
//		this.listeEmprunteurs.add(this.emprunteur);
	}

	/**
	 * Cette methode enregistre aureur et le tire d un livre
	 * @param auteur est l auteur du livre (de type Auteur)
	 * @param titre est le titre du livre (de type String)
	 */
	public Livre(Auteur auteur,String titre) {
		this.auteur = auteur;
		this.titre = titre;
	}

	/**
	 * Cette methode surcharge la méthode equals
	 * @return boolean
	 *
	 */
	@Override
	public boolean equals(Object livre) {
		if (this == livre) {
			return true;
		}
		if (livre instanceof Livre) {
			return ((this.getAuteur().equals(((Livre) livre).getAuteur()))
					&& (this.titre.equals(((Livre) livre).getTitre())));
		}
		return false;
	}
	/**
	 * Cette methode redéfinit la méthode hashCode
	 * car on a re-définit la méthode equals
	 * @return int
	 *
	 */
	public int hashCode() {
		return titre.hashCode();
	}
	/**
	 * Cette methode renvoit l'auteur
	 * @return Auteur
	 *
	 */
	public Auteur getAuteur() {
		return auteur;
	}
	/**
	 * Cette methode renvoit l'année
	 * @return anneePublication
	 *
	 */
	public int getAnneePublication() {
		return anneePublication;
	}

	/**
	 * Cette methode set envoie l'anneePublication
	 * @param anneePublication
	 *
	 */
	public void setAnneePublication(int anneePublication) {
		this.anneePublication = anneePublication;
	}

	/**
	 * Cette methode renvoit le résumé
	 * @return resume
	 *
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * Cette methode enregistre le résumé
	 * @return resume
	 *
	 */
	public void setResume(String resume) {
		this.resume = resume;
	}

	/**
	 * Cette methode renvoit le titre
	 * @return titre
	 *
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Cette methode enregistre le titre
	 * @param titre
	 *
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNbTomes() {
		return nbTomes;
	}

	public void setNbTomes(int nbTomes) {
		this.nbTomes = nbTomes;
	}


	public boolean isPreter() {
		return preter;
	}
	public void setPreter(boolean preter) {

		this.preter = preter;


	}

	public Date getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public boolean isHorsDelai() { return horsDelai; }
	public void setHorsDelai(boolean horsDelai) {
		this.horsDelai = horsDelai;
		if (this.horsDelai == true){
			this.amende = 2;
		}
	}

	public String getLangue() { return langue; }
	public void setLangue(String langue) { this.langue = langue; }

	public String getTheme() { return theme; }
	public void setTheme(String theme) { this.theme = theme; }

	public double getAmende() { return amende; }
	public void setAmende(double amende) { this.amende = amende; }


}
