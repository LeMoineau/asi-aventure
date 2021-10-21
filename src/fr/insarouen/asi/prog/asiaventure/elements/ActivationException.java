
package fr.insarouen.asi.prog.asiaventure.elements;

import fr.insarouen.asi.prog.asiaventure.ASIAventureException;

public class ActivationException extends ASIAventureException {

    /**
     * Constructeur de ActivationException
     */
    public ActivationException() {
        super();
    }

    /**
     * Constructeur de ActivationException
     * @param message message a renvoyer en cas d'erreur
     */
    public ActivationException(String message) {
        super(message);
    }

}
