package devoirs.solveigCamilleJuliette.bibliotheque;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class FichierXML {

	public static List<String> getInfosLivres(String path) throws IOException {

		BufferedReader lecteurAvecBuffer = null;
		String ligne;

		lecteurAvecBuffer = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8));
		List<String> infosLivres = new ArrayList<String>();
		String livre = "";
		
		while ((ligne = lecteurAvecBuffer.readLine()) != null) {
			if (ligne.contains("<livre")) {
				livre = "";
			}
			else if (ligne.contains("</livre>")) {
				infosLivres.add(livre);
			}
			else {
				livre += " "+ligne;
			}
		}
		lecteurAvecBuffer.close();
		
		return infosLivres;
	}
	
	public static String getBaliseContent(String extract, String balise) {
        Pattern p;
        Matcher m;
        String pattern = "<"+balise+".*>(.+)</"+balise+">";
        p = Pattern.compile(pattern);
        m = p.matcher(extract);
        
        if(m.find()) {
        	return m.group(1);
        }
        return "";
	}
	
	public static String getAttributeContent(String extract, String balise, String Attribut) {
        Pattern p;
        Matcher m;
        String pattern = "<"+balise+" .+=\"(.+)\">.+</"+balise+">";
        p = Pattern.compile(pattern);
        m = p.matcher(extract);
        
        if(m.find()) {
        	return m.group(1);
        }
        return "";
	}
}