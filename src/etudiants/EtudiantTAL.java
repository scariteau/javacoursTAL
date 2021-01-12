package etudiants;

import java.io.Serializable;

import traitementTextes.bibliotheque.personnes.Abonne;

public class EtudiantTAL extends Abonne implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer age;
	private String nom;
	private long numeroEtudiant;


	private static String ville;

	EtudiantTAL(String nom) {
		super(nom, 0);
	}

	public EtudiantTAL(String nom, long numeroEtudiant) {
		super(nom, numeroEtudiant);
	}

	EtudiantTAL(String nom, long numeroEtudiant, int age) {
		super(nom, numeroEtudiant);
		this.age = age;
	}

	public String toString() {
		return "Methode ToString de la classe EtudiantTAL. \n" + "Nom =" + nom + "\n" + "age =" + age + "\n"
				+ "numeroEtudiant =" + numeroEtudiant + "\n";
	}

	String imprimerID(String nom, long numeroEtudiant) {
		return "Je m'appelle " + nom + " , mon numéro d'étudiant est " + numeroEtudiant + " ";
	}

	public int getAge() {
		return age - 2;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static String getVille() {
		return ville;
	}

	public static void setVille(String ville) {
		EtudiantTAL.ville = ville;
	}

	
	public long getNumeroEtudiant() {
		return numeroEtudiant;
	}

	public void setNumeroEtudiant(long numeroEtudiant) {
		this.numeroEtudiant = numeroEtudiant;
	}
}
