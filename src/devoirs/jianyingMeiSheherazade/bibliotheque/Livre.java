package devoirs.jianyingMeiSheherazade.bibliotheque;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="livre")

public class Livre {

    public Livre() {
        // TODO Auto-generated constructor stub
    }

    private String titre;
    private String auteur;
    private Integer nb_tomes;
    private Integer date_publication;
    private String resume;
    private String lang;

    Livre(String titre, String auteur, Integer nb_tomes, Integer date_publication, String resume) {
        this.auteur = auteur;
        this.titre = titre;
        this.nb_tomes = nb_tomes;
        this.date_publication = date_publication;
        this.resume = resume;
    }

    Livre(String titre, String auteur, Integer nb_tomes, Integer date_publication, String resume, String lan) {
        this.auteur = auteur;
        this.titre = titre;
        this.nb_tomes = nb_tomes;
        this.date_publication = date_publication;
        this.resume = resume;
        this.lang = lan;
    }


    //@XmlAttribute
    public String getLang() { return lang; }


    public void setLang(String lang) {
        this.lang = lang;
    }


    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public Integer getNb_tomes() {
        return nb_tomes;
    }
    public void setNb_tomes(Integer nb_tomes) {
        this.nb_tomes = nb_tomes;
    }
    public Integer getDate_publication() {
        return date_publication;
    }
    public void setDate_publication(Integer date_publication) {
        this.date_publication = date_publication;
    }
    public String getResume() {
        return resume;
    }
    public void setResume(String resume) {
        this.resume = resume;
    }


}