
package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Coffre;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;

import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;

public class Piece extends ElementStructurel {

	private HashMap<String, Objet> objets = new HashMap<String, Objet>();
	private HashMap<String, Vivant> vivants = new HashMap<String, Vivant>();
	private HashMap<String, Porte> portes = new HashMap<String, Porte>();

	/**
	 * Constructeur de la piece
	 *
	 * @param nom   nom de la piece
	 * @param monde monde de la piece
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 */
	public Piece(String nom, Monde monde)
			throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde);
	}

	/**
	 * addPorte ajoute une porte a la place
	 * @param porte porte a ajouter
	 */
	protected void addPorte(Porte porte) {
		this.portes.put(porte.getNom(), porte);
	}

	/**
	 * aLaPorte verifie si la piece contient une porte
	 * @param porte porte a verifier
	 */
	public boolean aLaPorte(Porte porte) {
		return aLaPorte(porte.getNom());
	}

	/**
	 * aLaPorte verifie si la piece contient une porte
	 * @param nom nom de la porte a verifier
	 */
	public boolean aLaPorte(String nom) {
		return (this.portes.get(nom) != null);
	}

	/**
	* contientObjet verifie si la piece contient un objet
	* @param obj objet a chercher
	* @return un boolean pour la reponse
	*/
	public boolean contientObjet(Objet obj) {
		return contientObjet(obj.getNom());
	}

	/**
	* contientObjet verifie si la piece contient un objet
	* @param nom nom de l'objet a chercher
	* @return un boolean pour la reponse
	*/
	public boolean contientObjet(String nom) {
		return (this.objets.get(nom) != null);
	}

	/**
	 * contientVivant verifie si la piece contient un vivant
	 * @param vivant vivant a chercher
	 * @return un boolean, true pour trouve, false sinon
	 */
	public boolean contientVivant(Vivant vivant) {
		return contientVivant(vivant.getNom());
	}

	/**
	 * contientVivant verifie si la piece contient un vivant d'un certain nom
	 * @param nom nom du vivant a chercher
	 * @return un boolean, true pour trouve, false sinon
	 */
	public boolean contientVivant(String nom) {
		return (this.vivants.get(nom) != null);
	}

	/**
	 * deposer depose un objet dans la piece
	 * @param obj objet a deposer
	 */
	public void deposer(Objet obj) {
		this.objets.put(obj.getNom(), obj);
	}

	/**
	 * entrer fait entrer dans la piece un nouveau vivant
	 * @param vivant vivant a faire entrer
	 */
	public void entrer(Vivant vivant) {
		this.vivants.put(vivant.getNom(), vivant);
	}

	/**
	 * getObjets Getter de la liste des objets dans la piece
	 * @return la liste des objets dans la piece
	 */
	public Collection<Objet> getObjets() {
		return Collections.unmodifiableList(new ArrayList<Objet>(this.objets.values()));
	}

	/**
	 * getVivants Getter de la liste des vivants dans la piece
	 * @return la liste des vivants dans la piece
	 */
	public Collection<Vivant> getVivants() {
		return Collections.unmodifiableList(new ArrayList<Vivant>(this.vivants.values()));
	}

	/**
	 * getPieces Getter de la liste des portes dans la piece
	 * @return la liste des portes dans la piece
	 */
	public Collection<Porte> getPortes() {
		return Collections.unmodifiableList(new ArrayList<Porte>(this.portes.values()));
	}

	/**
	 * getPorte renvoie la porte demandee
	 * @param nom nom de la porte a renvoyer
	 */
	public Porte getPorte(String nom) {
		return this.portes.get(nom);
	}

	/**
	 * retirer retire un objet dans la piece
	 *
	 * @param obj objet a retirer de la piece
	 * @return objet retire
	 * @throws ObjetNonDeplacableException
	 * @throws ObjetAbsentDeLaPieceException
	 */
	public Objet retirer(Objet obj) throws ObjetNonDeplacableException, ObjetAbsentDeLaPieceException {
		return retirer(obj.getNom());
	}

	/**
	 * retirer retire un objet dans la piece par son nom
	 *
	 * @param nom nom de l'objet a retirer de la piece
	 * @return objet retire
	 * @throws ObjetNonDeplacableException
	 * @throws ObjetAbsentDeLaPieceException
	 */
	public Objet retirer(String nom) throws ObjetNonDeplacableException, ObjetAbsentDeLaPieceException {
		Objet res = null;
		if (this.contientObjet(nom)) {
			if (!this.objets.get(nom).estDeplacable()) {
				throw new ObjetNonDeplacableException(String.format("L'objet %s n'est pas deplacable", nom));
			} else {
				res = this.objets.remove(nom);
			}
		} else {
			throw new ObjetAbsentDeLaPieceException(String.format("L'objet %s n'est pas dans la piece", nom));
		}
		return res;
	}

	/**
	 * sortir retire un vivant de la piece
	 *
	 * @param vivant vivant a faire sortir
	 * @return vivant sortis
	 * @throws VivantAbsentDeLaPieceException
	 */
	public Vivant sortir(Vivant vivant) throws VivantAbsentDeLaPieceException {
		return sortir(vivant.getNom());
	}

	/**
	 * sortir retire un vivant de la piece par son nom
	 *
	 * @param nom nom du vivant a faire sortir
	 * @return vivant sortis
	 * @throws VivantAbsentDeLaPieceException
	 */
	public Vivant sortir(String nom) throws VivantAbsentDeLaPieceException {
		Vivant res = null;
		if (this.contientVivant(nom)) {
			this.vivants.remove(nom);
		} else {
			throw new VivantAbsentDeLaPieceException(String.format("Le vivant %s n'est pas dans la piece", nom));
		}
		return res;
	}

	/**
	* toString créer un String décrivant la piece
	* @return le String de description
	*/
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\n----- Piece -----\n");
		str.append(super.toString());
		str.append("\nobjets dans piece: ");
		for (Objet o: this.objets.values()) {
			str.append(String.format("\n-%s", o.toString()));
		}
		str.append("\nvivants dans piece: ");
		for (Vivant v: this.vivants.values()) {
			str.append(String.format("\n-%s", v.getNom()));
		}
		str.append("\nportes dans piece: ");
		for (Porte p: this.portes.values()) {
			str.append(String.format("\n-%s", p.getNom()));
		}
		str.append("\n-----------------\n");
		return str.toString();
	}

}
