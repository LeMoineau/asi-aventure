
package fr.insarouen.asi.prog.asiaventure;

import java.io.Serializable;

public class ASIAventureException extends Exception implements Serializable {

    /**
     * Constructeur de ASIAventureException
     */
    public ASIAventureException() {
        super();
    }

    /**
     * Constructeur de ASIAventureException
     * @param message message a renvoyer en cas d'erreur
     */
    public ASIAventureException(String message) {
        super(message);
    }

}
