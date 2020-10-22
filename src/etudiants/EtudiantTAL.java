package etudiants;

public class EtudiantTAL {

	private int age;
	public String nom;
	protected long numeroEtudiant;
	long numero;
	
	EtudiantTAL(String nom){
		this.nom=nom;
	}
	
	EtudiantTAL(String nom, long numeroEtudiant){
		this.nom=nom;
		this.numeroEtudiant=numeroEtudiant;
	}
	
	EtudiantTAL(String nom, long numeroEtudiant, int age){
		this.nom=nom;
		this.numeroEtudiant=numeroEtudiant;
		this.age=age;
	}
	
	public String toString() {
		return "Methode ToString de la classe EtudiantTAL. \n" + "Nom =" + nom + "\n" + "age =" + age + "\n"
				+ "numeroEtudiant =" + numeroEtudiant + "\n";
	}
	 String imprimerID(String nom, long numeroEtudiant) {
		return "Je m'appelle "+nom+" , mon numéro d'étudiant est "+numeroEtudiant+" ";
	}

	public int getAge() {
		return age -2;
	}

	public void setAge(int age) {
		this.age = age;
	}


}
