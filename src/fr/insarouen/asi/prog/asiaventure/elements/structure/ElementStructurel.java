
package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;

public abstract class ElementStructurel extends Entite {

	/**
	 * Constructeur de l'element structurel de base
	 * 
	 * @param nom   nom de l'element
	 * @param monde monde de l'element
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 */
	public ElementStructurel(String nom, Monde monde)
			throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde);
	}

}
