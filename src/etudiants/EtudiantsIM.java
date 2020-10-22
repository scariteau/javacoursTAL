package etudiants;

import professeurs.ProfTAL;

public class EtudiantsIM {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String nom="mylene";
		if (args!=null && args.length>0){
			nom=args[0];
		}
		EtudiantTALInalco etudianteMylene = new EtudiantTALInalco(nom, 22,"italien");
		System.out.println(etudianteMylene.toString());
		
		ProfTAL profDeJava = new ProfTAL("sandrine", "sandy","Java");
		profDeJava.setEtudiantPref(etudianteMylene);
		
		profDeJava.afficherEtudiantPrefere();
		
		EtudiantTAL etudianteGerad = new EtudiantTAL("Gerard", 52);
		profDeJava.setEtudiantPref(etudianteGerad);
		profDeJava.afficherEtudiantPrefere();
		
		
		EtudiantTALInalco etudianteTata = new EtudiantTALInalco("Tata", 22,"italien");
		profDeJava.setEtudiantDeteste(etudianteTata);
		
		EtudiantTAL etudianteToto = new EtudiantTAL("Toto", 52);
		/// KO: profDeJava.setEtudiantDeteste(etudianteToto);
		etudianteTata.setVille("gif sur yvette");
		
		System.out.println("ville Tata="+ etudianteTata.getVille());
		
		System.out.println("ville Gerard="+etudianteGerad.getVille());
		
		EtudiantTAL.setVille("Paris");
		
		System.out.println("ville Tata="+ etudianteTata.getVille());
		
		System.out.println("ville Gerard="+etudianteGerad.getVille());
		
		String[] matieres= new String[5];
		EtudiantTAL EtudiantArthur= new EtudiantTAL(matieres);

		
		
}


}
