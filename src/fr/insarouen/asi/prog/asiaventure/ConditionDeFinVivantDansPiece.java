
package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import java.io.Serializable;
public class ConditionDeFinVivantDansPiece extends ConditionDeFin implements Serializable{

  protected Vivant vivant;
  protected Piece piece;

  /**
	* constructeur de ConditionDeFinVivantDansPiece
  * @param etatConditionVerifiee etat du jeu en cas de resolution de la condition
  * @param vivant vivant qui doit etre present
  * @param piece piece dans laquelle le vivant doit etre
	*/
  public ConditionDeFinVivantDansPiece(EtatDuJeu etatConditionVerifiee, Vivant vivant, Piece piece) {
    super(etatConditionVerifiee);
    this.vivant = vivant;
    this.piece = piece;
  }

  /**
	* verifierCondition verifie et renvoie un etat du jeu pour la condition
  * @return l'etat du jeu apres verification de la condition
	*/
  public EtatDuJeu verifierCondition() {
    if (this.vivant.getPiece().equals(this.piece)) {
      return this.getEtatConditionVerifiee();
    } else {
      return EtatDuJeu.ENCOURS;
    }
  }

}
