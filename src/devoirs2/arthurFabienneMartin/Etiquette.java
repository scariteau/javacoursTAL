package devoirs2.arthurFabienneMartin;
import java.util.Arrays;
import java.util.List;

/**
 * <ul>
 * <li>String themes[] : Liste les thèmes possibles pour l'Etiquette.</li>
 * <li>List de String themesPossibles : String themes[] sous forme de liste pour faciliter la manipulation de son contenu.</li>
 * <li>String theme : Le thème passé en paramètre au constructeur lors de l'instanciation de l'objet.</li>
 * <li>bollean enAnglais : Indique si c'est anglais ou non.</li>
 * </ul>
 */
public class Etiquette { 
	
	private String themes[] = new String[] {"sf", "polar", "action", "education", "litterature", "comedie", "theatre", "culture", "actualite", "romance"};
	private List<String> themesPossibles = Arrays.asList(themes);
	private String theme;
	private boolean enAnglais;
	
	Etiquette(boolean enAnglais, String theme) throws IllegalArgumentException {
		if(themesPossibles.contains(theme)) {
			this.enAnglais = enAnglais;
			this.theme = theme;
		}
		else {
			throw new IllegalArgumentException("Le thème indiqué sur l'étiquette doit être parmi les thèmes suivants :\n" + themes.toString());
		}
	}
	
	@Override
	public String toString() {
		String result = "";
		for(String theme : themes) {
			result += theme + " ";
		}
		return result;
	}
	
	public String[] getThemes() {
		return themes;
	}

	public void setThemes(String[] themes) {
		this.themes = themes;
	}
	
	public List<String> getThemesPossibles() {
		return themesPossibles;
	}

	public void setThemesPossibles(List<String> themesPossibles) {
		this.themesPossibles = themesPossibles;
	}

	public String getTheme() {
		return theme;
	}

	public boolean isEnAnglais() {
		return enAnglais;
	}

}
