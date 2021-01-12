package devoirs2.jianyingMeiSheherazade;

public class Etudiant extends Emprunteur {
    /**
     * la classe Etudiant
     */
    private String etablissement;

    public Etudiant(String nom, String unversite) {
        super(nom);
        this.etablissement = etablissement;
    }

    public Etudiant(String nom) {
        super(nom);
        this.profession = "Etudiant";
    }

    public String getEtablissement() { return etablissement; }

    public void setEtablissement(String etablissement) { this.etablissement = etablissement; }

//    polymorphisme
//    public String getProfession() { return "Etudiant"; }




}
