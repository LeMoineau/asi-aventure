
package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;

import java.util.HashMap;
import java.util.Map;


public abstract class Vivant extends Entite {

	protected int pointVie;
	protected int pointForce;
	protected Piece piece;
	protected HashMap<String, Objet> objets;

	/**
	 * Constructeur d'un vivant
	 *
	 * @param nom        nom du vivant
	 * @param monde      monde du vivant
	 * @param pointVie   point de vie du vivant
	 * @param pointForce point de force du vivant
	 * @param piece      piece dans laquelle est le vivant
	 * @param objets     liste
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 */
	public Vivant(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets)
	throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde);
		this.pointVie = pointVie;
		this.pointForce = pointForce;
		this.piece = piece;
		piece.entrer(this);
		this.objets = new HashMap<String, Objet>();
		for (Objet o : objets){
			this.objets.put(o.getNom(),o);
		}
	}

	/**
	 * deposer Depose un objet dans la piece actuelle
	 *
	 * @param obj objet a déposer
	 * @throws ObjetNonPossedeParLeVivantException
	 */
	public void deposer(Objet obj)
	throws ObjetNonPossedeParLeVivantException {
		this.deposer(obj.getNom());
	}

	/**
	 * deposer Depose un objet dans la piece actuelle
	 *
	 * @param nom nom de l'objet a déposer
	 * @throws ObjetNonPossedeParLeVivantException
	 */
	public void deposer(String nom)
	throws ObjetNonPossedeParLeVivantException {
		Objet tmp = this.getObjet(nom);
		if (tmp != null) {
			this.piece.deposer(tmp);
			this.objets.remove(nom);
		}
		else {
			throw new ObjetNonPossedeParLeVivantException(String.format("L'objet %s n'est pas possede par le vivant", nom));
		}
	}

	/**
	 * estMort permet de savoir si le Vivant n'a plus de point de Vie
	 *
	 * @return un boolean true pour mort, false sinon
	 */
	public boolean estMort() {
		return (this.getPointVie() <= 0);
	}

	/**
	 * franchir permet passer d'une piece a une autre grace a une porte
	 *
	 * @param porte porte par laquelle passer
	 * @throws PorteFermeException
	 * @throws PorteInexistanteDansLaPieceException
	 */
	public void franchir(Porte porte)
	throws PorteFermeException, PorteInexistanteDansLaPieceException {
		try {
			if (!porte.getEtat().equals(Etat.FERME) && !porte.getEtat().equals(Etat.VERROUILLE)) {
				if (porte.getPieceAutreCote(this.piece) != null) {
					this.piece.sortir(this);
					this.piece = porte.getPieceAutreCote(this.piece);
					this.piece.entrer(this);
				} else {
					throw new PorteInexistanteDansLaPieceException(String.format("La porte %s n'existe pas dans la piece %s", porte.getNom(), this.piece.getNom()));
				}
			} else {
				throw new PorteFermeException(String.format("La porte %s est fermee", porte.getNom()));
			}
		} catch(VivantAbsentDeLaPieceException e) { //car le vivant forcémenet dans la piece puisque appelé depuis le vivant
			System.out.println("le vivant est forcement dans la piece car cette fonction est appele depuis le vivant...");
			return;
		}
	}

	/**
	 * franchir permet passer d'une piece a une autre grace a une porte
	 *
	 * @param nomPorte nom de la porte par laquelle passer
	 * @throws PorteFermeException
	 * @throws PorteInexistanteDansLaPieceException
	 */
	public void franchir(String nomPorte)
	throws PorteFermeException, PorteInexistanteDansLaPieceException {
		franchir(this.piece.getPorte(nomPorte));
	}

	/**
	 * getObjet chercher un objet dans les poches du vivant par son nom
	 *
	 * @param nom nom de l'objet a chercher
	 * @return l'objet portant le nom donne ou null si n'en a pas trouve
	 */
	public Objet getObjet(String nom) {
		return this.objets.get(nom);
	}

	/**
	 * getObjets Getter de la liste d'objet en poche du vivant
	 *
	 * @return la liste d'objet en poche
	 */
	public HashMap<String,Objet> getObjets() {
		return this.objets;
	}

	/**
	 * getPiece Getter de la piece actuelle du vivant
	 *
	 * @return la piece actuelle
	 */
	public Piece getPiece() {
		return this.piece;
	}

	/**
	 * getPointVie Getter des points de vie du vivant
	 *
	 * @return un entier des points de vie
	 */
	public int getPointVie() {
		return this.pointVie;
	}

	/**
	 * setPointVie Setter des points de vie du vivant
	 *
	 * @param pointVie nouvelle valeur des points de vie du vivant
	 */
	public void setPointVie(int pointVie) {
		this.pointVie = pointVie;
	}

	/**
	 * getPointVie Getter des points de force du vivant
	 *
	 * @return un entier des points de force
	 */
	public int getPointForce() {
		return this.pointForce;
	}

	/**
	 * possede Verifie si le vivant a en poche l'objet demande
	 *
	 * @param obj objet a chercher
	 * @return un boolean de reponse
	 */
	public boolean possede(Objet obj) {
		return (this.objets.get(obj.getNom()) != null);
	}

	/**
	 * prendre Prend un objet dans la piece dans laquelle se situe si c'est possible
	 *
	 * @param obj objet a prendre si dans la piece et deplacable
	 * @throws ObjetNonDeplacableException
	 * @throws ObjetAbsentDeLaPieceException
	 */
	public void prendre(Objet obj)
	throws ObjetNonDeplacableException, ObjetAbsentDeLaPieceException {
		prendre(obj.getNom());
	}

	/**
	 * prendre Prend un objet dans la piece actuelle en fonction du nom
	 *
	 * @param nom nom de l'objet a prendre si present et deplacable
	 * @throws ObjetNonDeplacableException
	 * @throws ObjetAbsentDeLaPieceException
	 */
	public void prendre(String nom)
	throws ObjetNonDeplacableException, ObjetAbsentDeLaPieceException {
		Objet o = this.piece.retirer(nom);
		if (o != null) {
				this.objets.put(nom, o);
		}
	}

	/**
	* toString créer un String décrivant le vivant
	* @return le String de description
	*/
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\n----- Vivant -----\n");
		str.append(String.format("%s, PV: %d, PF: %d, piece: %s, \nobjets dans poche: ", super.toString(), this.getPointVie(), this.getPointForce(), this.getPiece().getNom()));
		for (Objet o : this.objets.values()) {
			str.append(String.format("\n - '%s'", o.toString()));
		}
		str.append("\n------------------\n");
		return str.toString();
	}

}
