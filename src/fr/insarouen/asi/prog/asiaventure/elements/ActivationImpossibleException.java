
package fr.insarouen.asi.prog.asiaventure.elements;

public class ActivationImpossibleException extends ActivationException {

    /**
     * Constructeur de ActivationImpossibleException
     */
    public ActivationImpossibleException() {
        super();
    }

    /**
     * Constructeur de ActivationImpossibleException
     * @param message message a renvoyer en cas d'erreur
     */
    public ActivationImpossibleException(String message) {
        super(message);
    }

}
