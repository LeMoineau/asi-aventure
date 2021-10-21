package fr.insarouen.asi.prog.asiaventure.elements.vivants;

public class CommandeImpossiblePourLeVivantException extends VivantException {
    
    /**
     * Constructeur de CommandeImpossiblePourLeVivantException
     */
    public CommandeImpossiblePourLeVivantException() {
        super();
    }

    /**
     * Constructeur de CommandeImpossiblePourLeVivantException
     * @param message message a renvoyer en cas d'erreur
     */
    public CommandeImpossiblePourLeVivantException(String message) {
        super(message);
    }

}
