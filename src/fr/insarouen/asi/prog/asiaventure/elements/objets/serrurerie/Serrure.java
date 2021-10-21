package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;


public class Serrure extends Objet implements Activable {

	static int nbSerrure = 0;
	private Etat etat;
	private Clef clefAssocie;

	/**
	* Constructeur de Serrure
	* @param nom 	nom de la serrure
	* @param monde 	monde où se trouve la serrure
	*/
	public Serrure(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
		super(nom, monde);
		this.etat = Etat.VERROUILLE;
		this.clefAssocie = null;
	}

	/**
	* Constructeur de Serrure
	* @param monde 	monde où se trouve la serrure
	*/
	public Serrure(Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
		this(Serrure.genererNom(monde), monde);

	}

	private static String genererNom(Monde monde) {
		String tmpNom = String.format("Serrure%d",Serrure.nbSerrure);
		while (monde.getEntite(tmpNom)!= null) {
			Serrure.nbSerrure += 1;
			tmpNom = String.format("Serrure%d",Serrure.nbSerrure);
		}
		return tmpNom;
	}

	/**
	* creerClef permet de creer une claf, attention une serrure ne possède qu'ne clef unique, retourne alors null si la clef est déjà créée
	* @return la Clef créee ou la valeur null
	*/
	public final Clef creerClef(){
		if (this.clefAssocie == null) {
			String tmpNom = String.format("Clef%d",Clef.nbClefCree);
			while (monde.getEntite(tmpNom)!= null) {
				Clef.nbClefCree += 1;
				tmpNom = String.format("Clef%d",Clef.nbClefCree);
			}
			try {
				clefAssocie = new Clef(tmpNom,monde);
				return clefAssocie;
			}
			catch (NomDEntiteDejaUtiliseDansLeMondeException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	* getEtat renvoie l'etat actuel de la serrure
	* @return l'etat actuel de la serrure
	*/
	public Etat getEtat(){
		return this.etat;

	}

 	/**
  * activableAvec condition d'activation
	* @param obj objet permettant l'activation
  */
	public boolean activableAvec(Objet obj){
		return ((obj instanceof Clef)
			&& (clefAssocie != null)
			&& (clefAssocie.equals((Clef)obj)));
	}


	/**
	* activer permet d'activer l'effet de l'activable
	* @throws ActivationException
	*/
	public void activer() throws ActivationException {
		throw new ActivationException(String.format("La serrure %s ne peut pas etre ouverte sans clef", this.getNom()));
	}

	/**
	* activerAvec permet d'activer l'effet avec un objet
	* @param obj objet permettant l'activation
	* @throws ActivationException
	*/
	public void activerAvec(Objet obj) throws ActivationImpossibleAvecObjetException{
		if (this.activableAvec(obj)){
			if (this.etat == Etat.VERROUILLE) {
				this.etat = Etat.DEVEROUILLE;
			}
			else {
				this.etat = Etat.VERROUILLE;
			}
		}
		else {
			throw new ActivationImpossibleAvecObjetException(String.format("La serrure %s ne peut pas etre ouverte avec l'objet %s", this.getNom(), obj.getNom()));
		}
	}

	/**
	* estDeplacable méthode a redéfinir pour chaque objet
	* @return boolean indiquant si l'objet est deplacable ou non
	*/
	@Override
	public  boolean estDeplacable(){
		return false;
	}


}
