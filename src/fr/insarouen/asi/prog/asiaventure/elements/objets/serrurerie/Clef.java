package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;


public final class Clef extends Objet {

	static int nbClefCree = 0;

	/**
	* Constructeur de Clef
	* @param nom 	Nom de la clef
	* @param monde 	Monde où se trouve la clef
	*/
	protected Clef (String nom, Monde monde)
	throws NomDEntiteDejaUtiliseDansLeMondeException{
		super(nom,monde);
	}

	/**
	* estDeplacable méthode a redéfinir pour chaque objet
	* @return boolean indiquant si l'objet est deplacable ou non
	*/
	@Override
	public  boolean estDeplacable(){
		return true;
	}

}
