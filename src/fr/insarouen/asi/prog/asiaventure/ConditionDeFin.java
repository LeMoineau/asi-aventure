
package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.EtatDuJeu;
import java.io.Serializable;

public abstract class ConditionDeFin implements Serializable {

  protected EtatDuJeu etatConditionVerifiee;

  /**
	* constructeur de ConditionDeFin
  * @param etatConditionVerifiee etat du jeu en cas de resolution de la condition
	*/
  public ConditionDeFin(EtatDuJeu etatConditionVerifiee) {
    this.etatConditionVerifiee = etatConditionVerifiee;
  }

  /**
	* getEtatConditionVerifiee renvoie l'etat du jeu en cas de resolution
  * @return l'etat du jeu en cas de resolution de la condition
	*/
  public EtatDuJeu getEtatConditionVerifiee() {
    return this.etatConditionVerifiee;
  }

  /**
	* verifierCondition verifie et renvoie un etat du jeu pour la condition
  * @return l'etat du jeu apres verification de la condition
	*/
  public abstract EtatDuJeu verifierCondition();

}
