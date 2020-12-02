package traitementTextes;

import etudiants.EtudiantTAL;

public class GestionnaireBD {

	public static void main(String[] args) {

		Serialisateur serialisateur = new Serialisateur();
		String pathFichierSauvegarde = args[2];

		EtudiantTAL sylvie = new EtudiantTAL("Sylvie", 1235);
		serialisateur.serialiseObject(pathFichierSauvegarde, sylvie);
		EtudiantTAL sosieSylvie =(EtudiantTAL)serialisateur.deSerialiseObject(pathFichierSauvegarde);

		System.out.println("sylvie==" + sylvie);

		System.out.println("sosieSylvie==" + sosieSylvie);

	}

}
