package devoirs2.jianyingMeiSheherazade;
import java.util.ArrayList;

/**
 *
 * Classe Livre
 *
 *
 * @author
 * @version 1.2, 10 Jan 2021
 */
public class Emprunteur {
    /**
     *
     */
    public String nom;
    protected int id;
    protected String profession;
    protected Boolean hasDelai;
    protected ArrayList<Livre> livreEmprunte = new ArrayList<Livre>();
    protected int maxLivre;

    public Emprunteur(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Livre> getLivreEmprunte() {
        return livreEmprunte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void ajouteLivre(Livre l){
        this.livreEmprunte.add(l);
    }
    public void rendrelivre(Livre l) {this.livreEmprunte.remove(l);}
    public String getProfession() { return profession; }

    public void setProfession(String profession) { this.profession = profession; }

    public Boolean getHasDelai() { return hasDelai; }

    public void setHasDelai(Boolean hasDelai) { this.hasDelai = hasDelai; }

    @Override
    public String toString() {
        return getNom();
    }

    @Override
    public boolean equals(Object emprunteur) {
        if (this == emprunteur) {
            return true;
        }
        if (emprunteur instanceof Emprunteur) {
            return this.getNom().equals(((Emprunteur) emprunteur).getNom());
        }
        return false;
    }

}

