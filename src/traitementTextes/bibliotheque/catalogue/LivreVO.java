package traitementTextes.bibliotheque.catalogue;

public class LivreVO extends Livre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String langue;

	public LivreVO(Auteur auteur, String titre, String langue) {
		super(auteur, titre);
		this.langue=langue;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

}
