package devoirs2.jianyingMeiSheherazade;

public class ExceptionCataloque extends Exception {

    public ExceptionCataloque(){
        super("Invalid input Auteur or ArrayList of the catalogue HashMap<Auteur, ArrayList<Livre>> catalogue ");
        // HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();
    }


    public ExceptionCataloque(String message){
        super(message);
    }



}
