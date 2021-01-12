package devoirs2.chinatsuLaraXinyi;

public class LivreTheme extends Livre{
    
    private static final long serialVersionUID = 1L;
    private String theme;

    public LivreTheme(Auteur auteur,String titre, String theme) {
        super(auteur, titre);
        this.setTheme(theme);
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
