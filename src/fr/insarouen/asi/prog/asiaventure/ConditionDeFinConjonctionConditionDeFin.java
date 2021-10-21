
package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;

public class ConditionDeFinConjonctionConditionDeFin extends ConditionDeFin {

  protected ConditionDeFin[] conditions;

  /**
	* constructeur de ConditionDeFinConjonctionConditionDeFin
  * @param etatConditionVerifiee etat du jeu en cas de resolution de la condition
  * @param cfs liste de conditions a verifier pour resoudre cette condition
	*/
  public ConditionDeFinConjonctionConditionDeFin(EtatDuJeu etatConditionVerifiee, ConditionDeFin... cfs)  {
    super(etatConditionVerifiee);
    this.conditions = cfs;
  }

  /**
	* verifierCondition verifie et renvoie un etat du jeu pour la condition
  * @return l'etat du jeu apres verification de la condition
	*/
  public EtatDuJeu verifierCondition() {
    for (ConditionDeFin condition: this.conditions) {
      if (condition.verifierCondition() != this.getEtatConditionVerifiee()) {
        return EtatDuJeu.ENCOURS;
      }
    }
    return this.getEtatConditionVerifiee();
  }

}
