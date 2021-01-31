package bases.priseEnMain.creationClasses;

public class Voiture {
	private String marque;
	private Double prix;

	public Voiture() {

	}

	public void setMarque(String mq) {
		this.marque = mq;
	}

	public void setPrix(Double pr) {
		this.prix = pr;
	}

	public String getMarque() {
		return marque;
	}

	public Double getPrix() {
		return prix;
	}

	public void afficher() {
		System.out.println("La marque de ma voiture est : " + this.marque);
		System.out.println("La prix de ma voiture est : " + this.prix + " Euro");
	}

}
