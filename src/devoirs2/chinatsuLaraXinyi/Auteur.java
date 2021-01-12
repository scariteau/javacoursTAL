package devoirs2.chinatsuLaraXinyi;

import java.io.Serializable;

public class Auteur implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nom;

	public Auteur(String nom) {
		this.setNom(nom);
	}

	@Override
	public boolean equals(Object anObject) {
		if (this == anObject) {
			return true;
		}
		if (anObject instanceof Auteur) {
			return nom.equals(((Auteur) anObject).getNom());
		}
		return false;

	}

	public int hashCode() {
		return nom.hashCode();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}

