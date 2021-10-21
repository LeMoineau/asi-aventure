package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;

public abstract class Objet extends Entite {

	/**
	 * Constructeur de l'objet
	 * 
	 * @param nom   nom de l'objet
	 * @param monde monde de l'objet
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 */
	public Objet(String nom, Monde monde)
			throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde);
	}

	/**
	* estDeplacable méthode a redéfinir pour chaque objet
	* @return boolean indiquant si l'objet est deplacable ou non
	*/
	public abstract boolean estDeplacable();

	/**
	* toString créer un String décrivant l'objet
	* @return le String de description
	*/
	public String toString() {
		return String.format("%s, deplacable: %b", super.toString(), this.estDeplacable());
	}

}
