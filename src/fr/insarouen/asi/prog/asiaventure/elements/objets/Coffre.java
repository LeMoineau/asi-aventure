package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;


public class Coffre extends Objet implements Activable {

	private Etat etat;

	/**
	 * Constructeur de coffre
	 *
	 * @param nom   nom du coffre
	 * @param monde monde du coffre
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 */
	public Coffre(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom,monde);
		this.etat = Etat.FERME;
	}


 	/**
  * activableAvec condition d'activation
  * @param obj objet permettant l'activation
  * @throws ActivationException()
  */
	public boolean activableAvec(Objet obj){
		return false;
	}


	/**
	* activer permet d'activer l'effet de l'activable
	* @throws ActivationException
	*/
	public void activer() throws ActivationException {
		switch(this.etat) {
			case FERME:
				this.etat = Etat.OUVERT;
				break;
			case OUVERT:
				this.etat = Etat.FERME;
				break;
			default:
				throw new ActivationException(String.format("Le coffre %s n'est pas activable en l'etat", this.getNom()));
		}
	}

	/**
	* activerAvec permet d'activer l'effet avec un objet
	* @param obj objet permettant l'activation
	* @throws ActivationException
	*/
	public void activerAvec(Objet obj) throws ActivationException {
		throw new ActivationException();
	}

	/**
	* getEtat renvoie l'etat actuel du coffre
	* @return etat courant du coffre
	*/
	public Etat getEtat() {
		return this.etat;
	}

	/**
	* estDeplacable méthode a redéfinir pour chaque objet
	* @return boolean indiquant si l'objet est deplacable ou non
	*/
	@Override
	public boolean estDeplacable(){
		return false;
	}

}
