
package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;

public class ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction extends ConditionDeFinConjonctionConditionDeFin {

  /**
	* constructeur de ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction
  * @param etatConditionVerifiee etat du jeu en cas de resolution de la condition
  * @param vivant vivant qui doit etre present et avoir les objets
  * @param piece piece dans laquelle le vivant doit etre
  * @param objets liste d'objets que le vivant doit posseder
	*/
  public ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction(EtatDuJeu etatConditionVerifiee, Vivant vivant, Piece piece, Objet... objets) {
    super(etatConditionVerifiee,
      new ConditionDeFinVivantDansPiece(etatConditionVerifiee, vivant, piece),
      new ConditionDeFinVivantPossedeObjets(etatConditionVerifiee, vivant, objets));
  }

}
