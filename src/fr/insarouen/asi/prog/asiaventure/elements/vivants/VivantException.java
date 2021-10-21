package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.ASIAventureException;

public class VivantException extends ASIAventureException {

    /**
     * Constructeur de VivantException
     */
    public VivantException() {
        super();
    }

    /**
     * Constructeur de VivantException
     * @param message message a renvoyer en cas d'erreur
     */
    public VivantException(String message) {
        super(message);
    }

}
