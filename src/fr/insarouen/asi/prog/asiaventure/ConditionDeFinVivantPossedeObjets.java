
package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantPossedeObjets extends ConditionDeFin {

  protected Vivant vivant;
  protected Objet[] objets;

  /**
	* constructeur de ConditionDeFinVivantMort
  * @param etatConditionVerifiee etat du jeu en cas de resolution de la condition
  * @param vivant vivant qui doit avoir les objets
  * @param objets liste d'objets que le vivant doit posseder
	*/
  public ConditionDeFinVivantPossedeObjets(EtatDuJeu etatConditionVerifiee, Vivant vivant, Objet[] objets) {
    super(etatConditionVerifiee);
    this.vivant = vivant;
    this.objets = objets;
  }

  /**
	* verifierCondition verifie et renvoie un etat du jeu pour la condition
  * @return l'etat du jeu apres verification de la condition
	*/
  public EtatDuJeu verifierCondition() {
    for (Objet obj : this.objets) {
      if (!this.vivant.possede(obj)) {
        return EtatDuJeu.ENCOURS;
      }
    }
    return this.getEtatConditionVerifiee();
  }

}
