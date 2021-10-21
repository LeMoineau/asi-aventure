
package fr.insarouen.asi.prog.asiaventure.elements;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;

public interface Activable {

  /**
   * activableAvec condition d'activation
   * @param obj objet permettant l'activation
   */
	public boolean activableAvec(Objet obj);

  /**
   * activer permet d'activer l'effet de l'activable
   * @throws ActivationException
   */
  public void activer() throws ActivationException;

  /**
   * activerAvec permet d'activer l'effet avec un objet
   * @param obj objet permettant l'activation
   * @throws ActivationException
   */
  public void activerAvec(Objet obj) throws ActivationException;

}
