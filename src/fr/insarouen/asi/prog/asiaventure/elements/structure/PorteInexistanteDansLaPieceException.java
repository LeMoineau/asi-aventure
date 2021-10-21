package fr.insarouen.asi.prog.asiaventure.elements.structure;

public class PorteInexistanteDansLaPieceException extends ElementStructurelException {

    /**
     * Constructeur de PorteInexistanteDansLaPieceException
     */
    public PorteInexistanteDansLaPieceException() {
        super();
    }

    /**
     * Constructeur de PorteInexistanteDansLaPieceException
     * @param message message a renvoyer en cas d'erreur
     */
    public PorteInexistanteDansLaPieceException(String message) {
        super(message);
    }

}
