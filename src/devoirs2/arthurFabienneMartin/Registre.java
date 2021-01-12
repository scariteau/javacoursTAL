package devoirs2.arthurFabienneMartin;

import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.HashMap;
import java.util.stream.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

/**
 * <ul>
 * <li>HashMap emprunts : Clé = Client, Valeur = ArrayList de livrePrete</li>
 * <li>int prixAmende : La valeur de l'amende encaissé pour un retard.</li>
 * <li>int caisseAmende : Le montant total récolté des amendes.</li>
 * </ul>
 * @authors Arthur, Fabienne, Martin
 *
 */
class Registre {
	
	private HashMap<Client, ArrayList<livrePrete>> emprunts;
	private int prixAmende = 10;
	private int caisseAmende = 0;

	public Registre(HashMap<Client, ArrayList<livrePrete>> registre){
		this.emprunts = registre;
	}
	
	/**
	 * Créer une liste contenant les clients dont la date de retour des livres ont dépassé la date limite. Renvoie l'erreur NullPointerException si l'attribut Hashmap (emprunts) est vide.
	 * @return ArrayList de Client.
	 */
	private ArrayList<Client> listerRetardataires(){
		ArrayList<Client> retardataires = new ArrayList<Client>();
		if(!(getEmprunts().isEmpty())) {
			for(Client client : getEmprunts().keySet()) {
				for(livrePrete livre : getEmprunts().get(client)){
					if(livre.getDateRendu().isBefore(java.time.LocalDate.now().plusMonths(2))) {
						retardataires.add(client);
						break;
					}
				}
			}
			return retardataires;
		}
		else {
			throw new NullPointerException("Le registre des emprunts doit contenir au moins un client.");
		}
		
	}
	
	/**
	 * Créer la liste des clients qui ont été défini comme retardataires et donc soumis à devoir payer une amende. Renvoie l'erreur NullPointerException si l'attribut Hashmap (emprunts) est vide.
	 * @return ArrayList de Client.
	 */
	private ArrayList<Client> listerAmendataires(){
		ArrayList<Client> amendataires = new ArrayList<Client>();
		if(!(getEmprunts().isEmpty())) {
			for(Client client : getEmprunts().keySet()) {
				if(client.isAmande()) {
					amendataires.add(client);
				}
			}
			return amendataires;
		}
		else {
			throw new NullPointerException("Le registre des emprunts doit contenir au moins un client.");
		}
	}

	/**
	 * Liste dans une string les clients qui dont la date de rendu des livres ont dépassé la data limite.
	 * @return String
	 */
	public String relancerEmprunteursEnRetard(){
		ArrayList<Client> retardataires = listerRetardataires();
		String clientsRetardataires = "";
		for(Client client : retardataires) {
			clientsRetardataires += client.getNom() + "\n";
		}
		if(clientsRetardataires.equals("")) {
			return "Aucun client n'est en retard pour rendre son livre.";
		}
		else {
			return clientsRetardataires;
		}
	}
	
	/**
	 * Liste dans une string les clients ayant empruntés au moins un livre.
	 * @return String
	 */
	public String listerPersonnesAyantEmpruntesUnLivre() {
		String chaqueClient = "";
		if(!(getEmprunts().isEmpty())) {
			for (Client client : getEmprunts().keySet()) {
				chaqueClient += client.getNom() + "\n";
			}
		}
		if(chaqueClient.equals("")) {
			return "Aucun livre n'a été emprunté actuellement.";
		}
		else {
			return chaqueClient;
		}
	}
	
	/**
	 * Liste dans une string tous les livres empruntés par les Clients étant étudiant.
	 * @return String
	 */
	public String listerLivresEmpruntesParEtudiant() {
		String livresEmpruntes = "";
		for (Client client : getEmprunts().keySet()) {
			if (client.isEtudiant()) {
				livresEmpruntes += client.getNom() + " :\n";
				for(livrePrete livre : getEmprunts().get(client))
					livresEmpruntes += livre.getAuteur().getNom() + "\t" + livre.getTitre() + "\n";
				}	
		}
		if(livresEmpruntes.equals("")) {
			return "Aucun livre n'a été emprunté par un étudiant.";
		}
		else {
			return livresEmpruntes;	
		}
	}
	
	/**
	 * Liste dans une string tous les livres empruntés.
	 * @return String
	 */
	public String listerLivresEmpruntes() {
		List<livrePrete> livres = getEmprunts().values().stream().flatMap(List::stream).collect(Collectors.toList());
		String livresEmpruntes = "";
		for(livrePrete livre : livres) {
			livresEmpruntes += livre.getAuteur().getNom() + "\t" + livre.getTitre() + "\n";
		}
		if(livresEmpruntes.equals("")) {
			return "Aucun livre n'a été emprunté actuellement.";
		}
		else {
			return livresEmpruntes;
		}
	}
	
	/**
	 * Indique dans une string le nombre de livres empruntés pour l'Auteur indiqué en paramètre.
	 * @param monAuteur Auteur : classe Auteur - L'auteur dont on veut savoir le nombre de livres empruntés.
	 * @return String
	 */
	public String listerLivresEmpruntes(Auteur monAuteur) {
        List<livrePrete> livres = getEmprunts().values().stream().flatMap(List::stream).collect(Collectors.toList());
		int compteur = 0;
		for(livrePrete livre : livres) {
			if(livre.getAuteur().equals(monAuteur)) {
				compteur += 1;
			}
		}
		switch(compteur) {
		default :
			return "Au total, " + compteur + " livres de l'auteur nommé " + monAuteur.getNom() + " ont été empruntés.";
		case 0 :
			return "Aucun livre de cet auteur n'a été emprunté.";
		case 1 :
			return "Un seul livre de l'auteur nommé " + monAuteur.getNom() + " a été emprunté.";
		}
	}
	
	/**
	 * Liste dans une string les livres étant écrit en Anglais.
	 * @return String
	 */
	public String listerLivresAnglais() {
		List<livrePrete> livres = getEmprunts().values().stream().flatMap(List::stream).collect(Collectors.toList());
		String livresAnglais = "";
		for(livrePrete livre : livres) {
			if(livre.getEtiquette().isEnAnglais()) {
				livresAnglais += livre.getAuteur().getNom() + "\t" + livre.getTitre() + "\n";
			}
		}
		if(livresAnglais.equals("")) {
			return "Aucun livre en anglais n'a été emprunté actuellement.";
		}
		else {
			return livresAnglais;
		}
	}
	
	/**
	 * Modifie l'attribut Amande (sic) du Client pour True s'il est défini comme retardataire.
	 */
	public void envoyerAmendeRetardataire() {
		ArrayList<Client> retardataires = listerRetardataires();
		if (retardataires.isEmpty()){
			System.out.println("Il n'y a aucun retardataire.");
		}
		else {
			for(Client client : retardataires) {
				ArrayList<livrePrete> livres = getEmprunts().get(client);
				getEmprunts().remove(client, livres);
				client.setAmande(true);
				getEmprunts().put(client, livres);
			}
		}
	}
	
	/**
	 * Modifie l'attribut Amande de tous les Clients pour False et incrémente l'attribut caisseAmende de la valeur prixAmende pour chaque client en retard.
	 */
	public void encaisserAmendeRetardataire() {
		Collection<Client> amendataires = listerAmendataires();
		if(amendataires.isEmpty()) {
			System.out.println("Il n'y a aucune amende à encaisser.");
		}
		else {
			for(Client client : amendataires) {
				if(client.isAmande() == true) {
					ArrayList<livrePrete> livres = getEmprunts().get(client);
					getEmprunts().remove(client, livres);
					client.setAmande(false);
					getEmprunts().put(client, livres);
					this.caisseAmende += this.prixAmende;
				}
			}
		}
	}
	
	/**
	 * Liste dans une string la liste des livres correspondant au thème indiqué en paramètre.
	 * @param monTheme - String correspondant à l'un des thèmes défini dans la classe Etiquette. Si le thème n'est pas défini, renvoie l'erreur Illegal Argument Exception.
	 * @return String
	 */
	public String listerLivreSurUnTheme(String monTheme) {
		Etiquette etiquette = new Etiquette(true, "sf");
		String themes = etiquette.toString();
		if(!(etiquette.getThemesPossibles().contains(monTheme))) {
			throw new IllegalArgumentException("Le thème indiqué doit être parmi les thèmes suivants :\n" + themes);
		}
		else {
			List<livrePrete> livres = getEmprunts().values().stream().flatMap(List::stream).collect(Collectors.toList());
			String livresMonTheme = "";
			for(livrePrete livre : livres) {
				if(livre.getEtiquette().getTheme().equalsIgnoreCase(monTheme)) {
					livresMonTheme += livre.getAuteur().getNom() + "\t" + livre.getTitre() + "\n";
				}
			}
			if(livresMonTheme.equals("")) {
				return "Aucun livre ne correspond au thème donné.";
			}
			else {
				return livresMonTheme;
			}
		}
	}

	public HashMap<Client, ArrayList<livrePrete>> getEmprunts() {
		return emprunts;
	}

	public int getPrixAmende() {
		return prixAmende;
	}

	public void setPrixAmende(int prixAmende) {
		this.prixAmende = prixAmende;
	}
	
	public int getCaisseAmende() {
		return caisseAmende;
	}

	public void setCaisseAmende(int caisseAmende) {
		this.caisseAmende = caisseAmende;
	}

}
