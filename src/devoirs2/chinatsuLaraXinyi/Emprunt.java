package devoirs2.chinatsuLaraXinyi;

import java.util.Date;
import java.io.Serializable;

public class Emprunt implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 private int idEmprunt;
	 private Livre livre;
	 private Personne personne;
	 private Date datePrete;
	 private String dateRetourne;
	 private Date dateDelais;
	 private int amende;
	 
	 public Emprunt(int idEmprunt, Livre livre, Personne personne,  Date datePrete, String dateRetourne,Date dateDelais, int amende ) {
		 this.setIdEmprunt(idEmprunt);
		 this.setLivre(livre);
		 this.setPersonne(personne);
		 this.setDatePrete(datePrete);
		 this.setDateRetourne(dateRetourne);
		 this.setDateDelais(dateDelais);
		 this.setAmende(0);
	 }

	public int getIdEmprunt() {
		return idEmprunt;
	}

	public void setIdEmprunt(int idEmprunt) {
		this.idEmprunt = idEmprunt;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Date getDatePrete() {
		return datePrete;
	}

	public void setDatePrete(Date datePrete) {
		this.datePrete = datePrete;
	}

	public String getDateRetourne() {
		return dateRetourne;
	}

	public void setDateRetourne(String dateRetourne) {
		this.dateRetourne = dateRetourne;
	}

	public Date getDateDelais() {
		return dateDelais;
	}

	public void setDateDelais(Date dateDelais) {
		this.dateDelais = dateDelais;
	}

	public int getAmende() {
		return amende;
	}

	public void setAmende(int amende) {
		this.amende = amende;
	} 
}

