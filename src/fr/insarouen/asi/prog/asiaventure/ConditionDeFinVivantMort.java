
package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantMort extends ConditionDeFin {

  protected Vivant vivant;

  /**
	* constructeur de ConditionDeFinVivantMort
  * @param etatConditionVerifiee etat du jeu en cas de resolution de la condition
  * @param vivant vivant qui doit mourir
	*/
  public ConditionDeFinVivantMort(EtatDuJeu etatConditionVerifiee, Vivant vivant) {
    super(etatConditionVerifiee);
    this.vivant = vivant;
  }

  /**
	* verifierCondition verifie et renvoie un etat du jeu pour la condition
  * @return l'etat du jeu apres verification de la condition
	*/
  public EtatDuJeu verifierCondition() {
    if (this.vivant.estMort()) {
      return this.getEtatConditionVerifiee();
    } else {
      return EtatDuJeu.ENCOURS;
    }
  }

}
