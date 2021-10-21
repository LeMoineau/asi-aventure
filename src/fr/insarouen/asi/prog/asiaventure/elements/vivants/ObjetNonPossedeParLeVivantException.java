package fr.insarouen.asi.prog.asiaventure.elements.vivants;

public class ObjetNonPossedeParLeVivantException extends VivantException {
    
    /**
     * Constructeur de ObjetNonPossedeParLeVivantException
     */
    public ObjetNonPossedeParLeVivantException() {
        super();
    }

    /**
     * Constructeur de ObjetNonPossedeParLeVivantException
     * @param message message a renvoyer en cas d'erreur
     */
    public ObjetNonPossedeParLeVivantException(String message) {
        super(message);
    }

}
