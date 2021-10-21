
package fr.insarouen.asi.prog.asiaventure.elements;

import fr.insarouen.asi.prog.asiaventure.EntiteDejaDansUnAutreMondeException;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

import java.io.Serializable;

public abstract class Entite implements Serializable {

	protected Monde monde;
	protected String nom;

	/**
	 * Constructeur de l'entite
	 *
	 * @param nom   nom de l'entite
	 * @param monde monde de l'entite
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 */
	public Entite(String nom, Monde monde)
	throws NomDEntiteDejaUtiliseDansLeMondeException {
		this.nom = nom;
		this.monde = monde;
		try {
			monde.ajouter(this);
		} catch (EntiteDejaDansUnAutreMondeException e) {
			e.printStackTrace();
		}
	}

	/**
	* getNom Getter du nom de l'entite
	* @return nom de l'entite
	*/
	public String getNom() {
		return this.nom;
	}

	/**
	* getMonde Getter du monde de l'entite
	* @return monde de l'entite
	*/
	public Monde getMonde() {
		return this.monde;
	}

	/**
	* hashCode Créer un hashCode commun a toutes les entites aux memes caracteristiques
	* @return un hashCode générer depuis les attribut de la classe
	*/
	public int hashCode() {
		return 13 * this.nom.hashCode() + 17 * this.monde.hashCode();
	}

	/**
	* equals Compare deux Object (!= Objet)
	* @return un boolean true/false en fonction de la similarité
	*/
	public boolean equals(Object o) {
		boolean res;
		if (this == o) res = true;
		else if (o == null || this.getClass() != o.getClass()) res = false;
		else res = (this.monde.equals(((Entite)(o)).monde) && this.nom.equals(((Entite)(o)).nom));
		return res;
	}

	/**
	* toString créer un String décrivant l'entite
	* @return le String de description
	*/
	public String toString() {
		return String.format("nom: %s, monde: %s", this.nom, this.monde.getNom());
	}

}
