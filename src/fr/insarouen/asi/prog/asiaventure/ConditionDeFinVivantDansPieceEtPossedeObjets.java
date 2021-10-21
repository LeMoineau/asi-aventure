
package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;

public class ConditionDeFinVivantDansPieceEtPossedeObjets extends ConditionDeFin {

  protected Vivant vivant;
  protected Piece piece;
  protected Objet[] objets;

  /**
	* constructeur de ConditionDeFinVivantDansPieceEtPossedeObjets
  * @param etatConditionVerifiee etat du jeu en cas de resolution de la condition
  * @param vivant vivant qui doit etre present et avoir les objets
  * @param piece piece dans laquelle le vivant doit etre
  * @param objets liste d'objets que le vivant doit posseder
	*/
  public ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu etatConditionVerifiee, Vivant vivant, Piece piece, Objet... objets) {
    super(etatConditionVerifiee);
    this.vivant = vivant;
    this.objets = objets;
    this.piece = piece;
  }

  /**
	* verifierCondition verifie et renvoie un etat du jeu pour la condition
  * @return l'etat du jeu apres verification de la condition
	*/
  public EtatDuJeu verifierCondition() {
    ConditionDeFinVivantDansPiece c1 = new ConditionDeFinVivantDansPiece(
      this.getEtatConditionVerifiee(),
      this.vivant,
      this.piece);
    ConditionDeFinVivantPossedeObjets c2 = new ConditionDeFinVivantPossedeObjets(
      this.getEtatConditionVerifiee(),
      this.vivant,
      this.objets);
    if (c1.verifierCondition() == this.getEtatConditionVerifiee() && c2.verifierCondition() == this.getEtatConditionVerifiee()) {
      return this.getEtatConditionVerifiee();
    } else {
      return EtatDuJeu.ENCOURS;
    }
  }
}
