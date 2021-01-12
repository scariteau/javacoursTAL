package traitementTextes.bibliotheque;

import traitementTextes.bibliotheque.catalogue.LivreEmprunte;

public class Amende {
	private double montantAmende;
	private LivreEmprunte livreEnRetard;

	public Amende(LivreEmprunte livreEmprunte, double montantAmende) {
		this.livreEnRetard = livreEmprunte;
		this.montantAmende = montantAmende;
	}

	@Override
	public boolean equals(Object amendeIdentique) {
		if (this == amendeIdentique) {
			return true;
		}
		if (amendeIdentique instanceof Amende) {
			return (montantAmende == (((Amende) amendeIdentique).getMontantAmende())
					&& (livreEnRetard.equals(((Amende) amendeIdentique).getLivreEnRetard())));
		}
		return false;

	}

	@Override
	public int hashCode() {
		return livreEnRetard.hashCode();
	}

	public double getMontantAmende() {
		return montantAmende;
	}

	public void setMontantAmende(double montantAmende) {
		this.montantAmende = montantAmende;
	}

	public LivreEmprunte getLivreEnRetard() {
		return livreEnRetard;
	}

	public void setLivreEnRetard(LivreEmprunte livreEnRetard) {
		this.livreEnRetard = livreEnRetard;
	}

}
