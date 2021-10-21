package fr.insarouen.asi.prog.asiaventure.elements.structure;

public class VivantAbsentDeLaPieceException extends PieceException {

    /**
     * Constructeur de VivantAbsentDeLaPieceException
     */
    public VivantAbsentDeLaPieceException() {
        super();
    }

    /**
     * Constructeur de VivantAbsentDeLaPieceException
     * @param message message a renvoyer en cas d'erreur
     */
    public VivantAbsentDeLaPieceException(String message) {
        super(message);
    }

}
