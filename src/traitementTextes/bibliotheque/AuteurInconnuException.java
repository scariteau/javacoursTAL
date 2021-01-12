package traitementTextes.bibliotheque;

public class AuteurInconnuException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AuteurInconnuException(String messageErreur) {
		super(messageErreur);
	}
}
