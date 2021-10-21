package fr.insarouen.asi.prog.asiaventure;

public class MondeException extends ASIAventureException {
    
    /**
     * Constructeur de MondeException
     */
    public MondeException() {
        super();
    }

    /**
     * Constructeur de MondeException
     * @param message message a renvoyer en cas d'erreur
     */
    public MondeException(String message) {
        super(message);
    }

}
