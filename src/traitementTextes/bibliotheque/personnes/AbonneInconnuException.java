package traitementTextes.bibliotheque.personnes;

public class AbonneInconnuException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AbonneInconnuException(String messageErreur) {
		super(messageErreur);
	}

}
