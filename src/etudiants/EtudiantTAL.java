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
		return "Methode ToString de la classe EtudiantTAL. \n"+"Nom =" +nom+"\n" +"age =" +age+"\n"+"numeroEtudiant =" +numeroEtudiant+"\n";
	}
	 String imprimerID(String nom, long numeroEtudiant) {
		return "Je m'appelle "+nom+" , mon numéro d'étudiant est "+numeroEtudiant+" ";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("HEllo you!");
		
		EtudiantTAL etudianteSylvie=new EtudiantTAL("sylvie");
		
		EtudiantTAL etudianteSylvies=new EtudiantTAL("sylvie", 0, 0);
		
		EtudiantTAL etudianteSylviese=new EtudiantTAL(null, 0, 0);
		System.out.println("nom de sylvie="+etudianteSylvie.nom);
		
		etudianteSylvie.nom="Mrlene";
		System.out.println("nom de sylvie="+etudianteSylvie.nom);
		
		EtudiantTAL etudianteBernard=new EtudiantTAL("Bernard",32);
		System.out.println("nom de Bernard="+etudianteBernard.nom + "age=" +etudianteBernard.age);
	}

}
