package devoirs2.chinatsuLaraXinyi;

import java.io.Serializable;

public class Personne implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String nom;
	private String prenom;
	private String idEtudiant;
	
	public Personne(String id, String nom, String prenom) {
		this.id=id;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Personne(String id, String nom, String prenom, String idEtudiant) {
		this.id=id;
		this.nom = nom;
		this.prenom = prenom;
		this.idEtudiant = idEtudiant;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(String idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
}
