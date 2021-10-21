package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.ASIAventureException;

public class ObjetException extends ASIAventureException {

    /**
     * Constructeur de ObjetException
     */
    public ObjetException() {
        super();
    }

    /**
     * Constructeur de ObjetException
     * @param message message a renvoyer en cas d'erreur
     */
    public ObjetException(String message) {
        super(message);
    }

}
