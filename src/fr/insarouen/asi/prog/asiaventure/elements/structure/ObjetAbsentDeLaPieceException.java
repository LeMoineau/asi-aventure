package fr.insarouen.asi.prog.asiaventure.elements.structure;

public class ObjetAbsentDeLaPieceException extends PieceException {

    /**
     * Constructeur de ObjetAbsentDeLaPieceException
     */
    public ObjetAbsentDeLaPieceException() {
        super();
    }

    /**
     * Constructeur de ObjetAbsentDeLaPieceException
     * @param message message a renvoyer en cas d'erreur
     */
    public ObjetAbsentDeLaPieceException(String message) {
        super(message);
    }

}
