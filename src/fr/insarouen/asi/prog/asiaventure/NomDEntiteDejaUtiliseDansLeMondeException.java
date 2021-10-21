package fr.insarouen.asi.prog.asiaventure;

public class NomDEntiteDejaUtiliseDansLeMondeException extends MondeException {
    
    /**
     * Constructeur de NomDEntiteDejaUtiliseDansLeMondeException
     */
    public NomDEntiteDejaUtiliseDansLeMondeException() {
        super();
    }

    /**
     * Constructeur de NomDEntiteDejaUtiliseDansLeMondeException
     * @param message message a renvoyer en cas d'erreur
     */
    public NomDEntiteDejaUtiliseDansLeMondeException(String message) {
        super(message);
    }

}
