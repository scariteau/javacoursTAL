package devoirs2.chinatsuLaraXinyi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class BibliothecaireEmprunt extends Bibliothecaire{
	
	private ArrayList <Personne> listePersonnes; 
	private ArrayList <Emprunt> listeEmprunts;
    
	public BibliothecaireEmprunt(HashMap<Auteur, ArrayList<Livre>> catalogue, ArrayList<Personne> listePersonnes, ArrayList<Emprunt> listeEmprunts) {
		super(catalogue);
		this.setListePersonnes(listePersonnes);
		this.setListeEmprunts(listeEmprunts);
	}
	
	/**
	* Préter un livre.
    * @param emprunt
    */
	public void preterUnLivre(Emprunt emprunt) {
		if (Objects.nonNull(getCatalogue().get(emprunt.getLivre().getAuteur()))) {
			listeEmprunts.add(emprunt);
			if (listePersonnes.contains(emprunt.getPersonne())){;}
			else {
				listePersonnes.add(emprunt.getPersonne());
			}
		}
		else {
			System.out.println("Ce livre n'est pas disponible :'( ");
		}
	}
	
	/**
	* Lister Personnes Ayant Empruntes Un Livre .
    * @param 
	* @return ArrayList <Personne>
    */
	public  ArrayList <Personne> listerPersonnesAyantEmpruntesUnLivre(){
		return getListePersonnes();
	}
	
	/**
	* Lister Livres Empruntes
    * @param 
	* @return ArrayList <Livre>
    */
	public ArrayList<Livre> listerLivresEmpruntes(){
		ArrayList<Livre> listLivres = new ArrayList<Livre>();
		ArrayList <Emprunt> listeEmprunts = getListeEmprunts();
		for (Emprunt emprunt : listeEmprunts) {
			listLivres.add(emprunt.getLivre());
		}
		return listLivres;
	}
	
	/**
	* Lister Livres Empruntes Par Etudiant
	* @param 
	* @return ArrayList<Livre>
	*/
	public ArrayList<Livre> listerLivresEmpruntesParEtudiant(){
		ArrayList<Livre> listLivres = new ArrayList<Livre>();
		ArrayList <Emprunt> listeEmprunts = getListeEmprunts();
		for (Emprunt emprunt : listeEmprunts) {
			if (emprunt.getPersonne().getIdEtudiant() != null) {
				listLivres.add(emprunt.getLivre());
			}
		}
		return listLivres;
	} 
	
	/**
	* Relancer Emprunteur En Retard
	* @param 
	*/  
	 public void relancerEmprunteurEnRetard() {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Calendar calobj = Calendar.getInstance();
		 ArrayList <Emprunt> listeEmprunts = getListeEmprunts();
		 for (Emprunt emprunt : listeEmprunts) {
			 if ((emprunt.getDateRetourne().equals("0000-00-00")) && sdf.format(emprunt.getDateDelais()).compareTo(sdf.format(calobj.getTime())) < 0) {
				 System.out.println(emprunt.getPersonne().getNom()+" "+emprunt.getPersonne().getPrenom()+" ");
				 System.out.println("Vous avez un document en retard suivant : ");
				 System.out.println(emprunt.getLivre().getTitre()+'\n');
		 }
	   }
	 }
	 
	 /**
	  * Lister nombre de Livres Empruntes Pour Un Auteur
	  * @param auteur
	  * @return String 
	  * @throws AuteurInvalideException
	 w */  
	     public String listerNbLivresEmpruntesPourUnAuteur(Auteur auteur) throws AuteurInvalideException {
	        if (getCatalogue().containsKey(auteur)) {
	            int count = 0;
	            
	            ArrayList <Emprunt> listeEmprunts = getListeEmprunts();
	            for (Emprunt emprunt : listeEmprunts) {
	                if (emprunt.getLivre().getAuteur().equals(auteur)) {
	                        count +=1;
	                }
	            }
	            return (count +" livre(s) emprunté(s) pour "+ auteur.getNom()); 
	        } else {
				throw new AuteurInvalideException("L'auteur n'est pas dans le catalogue");
	        }
	     }
	 
	 /**
	* Envoyer Amende Retardaire
	* @param 
	*/  
	public void envoyerAmendeRetardaire() {
		int amende =0;
		HashMap<Emprunt, Integer> listAmende = new HashMap<Emprunt, Integer>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calobj = Calendar.getInstance();
		ArrayList <Emprunt> listeEmprunts = getListeEmprunts();
		for (Emprunt emprunt : listeEmprunts) {
			if ((emprunt.getDateRetourne().equals("0000-00-00")) && sdf.format(emprunt.getDateDelais()).compareTo(sdf.format(calobj.getTime())) < 0){	 
				 long from1 = calobj.getTime().getTime();  //date d'aujourd'hui
				 long to1 = emprunt.getDateDelais().getTime();  //date de delais pour retrouner un livre
				 int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));  //Calculer la difference de entre ces jours et convertir les mille-secondes aux jours
				 amende = days*2; //amende = par jours * 2euros
				 emprunt.setAmende(Math.abs(amende));
				 listAmende.put(emprunt, Math.abs(amende));
		 }
	   }
		for (Emprunt i : listAmende.keySet()) {
			System.out.println( i.getPersonne().getNom()+" "+i.getPersonne().getPrenom()+ ": Vous devez payer "+listAmende.get(i)+"€ amende pour " + i.getLivre().getTitre());
			}
	}
	
	
	/**
	* Encaisser Amende Retardaire
	* @param argent, emp
	*/  
	public void encaisserAmendeRetardaire(int argent, Emprunt emp){
		ArrayList <Emprunt> listeEmprunts = getListeEmprunts();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calobj = Calendar.getInstance();
		for (Emprunt emprunt : listeEmprunts) {
			if(emp.getIdEmprunt() == (emprunt.getIdEmprunt()) && (emprunt.getDateRetourne().equals("0000-00-00"))) {
				if (argent > emprunt.getAmende()){
					int diff = argent-emprunt.getAmende();
					emprunt.setAmende(0);
					emprunt.setDateRetourne(sdf.format(calobj.getTime()));
					System.out.println("Nous vous rendrons" + diff +"€.");
				}      
				else if (argent == emprunt.getAmende()){
					emprunt.setAmende(0);
					System.out.println("Merci");
					emprunt.setDateRetourne(sdf.format(calobj.getTime()));
				}
				else{
					int diff = emprunt.getAmende()-argent;
					emprunt.setAmende(diff);
					System.out.println("Il vous reste " + diff+ "€ amendes à payer.");
				}
			}
		}
	} 
	 
	/* --------------- Getters and Setters ----------------- */
	
	public ArrayList <Personne> getListePersonnes() {
		return listePersonnes;
	}

	public void setListePersonnes(ArrayList <Personne> listePersonnes) {
		this.listePersonnes = listePersonnes;
	}

	public ArrayList <Emprunt> getListeEmprunts() {
		return listeEmprunts;
	}

	public void setListeEmprunts(ArrayList <Emprunt> listeEmprunts) {
		this.listeEmprunts = listeEmprunts;
	}

}

