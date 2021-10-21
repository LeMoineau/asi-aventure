
package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;

public class Porte extends ElementStructurel implements Activable {

  private Etat etat;
  private Piece pieceA;
  private Piece pieceB;
  private Serrure serrure;

	/**
	 * Constructeur de la Porte
	 *
	 * @param nom   nom de l'element
	 * @param monde monde de l'element
   * @param pieceA premiere piece
   * @param pieceB seconde piece
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 */
	public Porte(String nom, Monde monde, Piece pieceA, Piece pieceB)
			throws NomDEntiteDejaUtiliseDansLeMondeException {
		this(nom, monde, null, pieceA, pieceB);
    this.etat = Etat.FERME;
	}

  public Porte(String nom, Monde monde, Serrure serrure, Piece pieceA, Piece pieceB)
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    super(nom, monde);
    this.etat = Etat.VERROUILLE;
    this.pieceA = pieceA;
    this.pieceA.addPorte(this);
    this.pieceB = pieceB;
    this.pieceB.addPorte(this);
    this.serrure = serrure;
  }

  /**
  * activableAvec Permet de savaoir si on peut activer porte
  @param obj Objet avec lequel activer
  @return un booleen, vrai si activable
  */

  public boolean activableAvec(Objet obj) {
    return ((this.serrure == null) || this.serrure.activableAvec(obj));
  }

  /**
  * activer Permet activer porte
  *@throws ActivationImpossibleException
  */
  public void activer()
    throws ActivationImpossibleException {
      if (this.etat == Etat.OUVERT) {
        this.etat = Etat.FERME;
      }
      else {
        if (this.etat == Etat.FERME) {
          this.etat = Etat.OUVERT;
        }
        else {
          throw new ActivationImpossibleException(String.format("La porte %s n'est pas activable en l'etat", this.getNom()));
         }
        }
  }



  /**
  * activerAvec Permet activer porte
  @param obj Objet avec lequel activer
  */
  public void activerAvec(Objet obj)
  throws ActivationImpossibleAvecObjetException, ActivationImpossibleException {
    if (obj instanceof Clef) {
      if (this.activableAvec(obj)) {
        switch(this.etat) {
          case VERROUILLE:
            this.etat = Etat.OUVERT;
            break;
          case OUVERT:
            this.etat = Etat.VERROUILLE;
            break;
          case FERME:
            this.etat = Etat.VERROUILLE;
            break;
          case CASSE:
            throw new ActivationImpossibleException(String.format("La porte %s est déjà cassée", this.getNom()));
        }
      }
    } else if (obj instanceof PiedDeBiche) {
      switch(this.etat) {
        case VERROUILLE:
          this.etat = Etat.CASSE;
          break;
        case FERME:
          this.etat = Etat.CASSE;
          break;
        case OUVERT:
          throw new ActivationImpossibleException(String.format("La porte %s est déjà ouverte", this.getNom()));
        case CASSE:
          throw new ActivationImpossibleException(String.format("La porte %s est déjà cassée", this.getNom()));
      }
    } else {
      throw new ActivationImpossibleAvecObjetException(String.format("La porte %s ne peut pas etre activer avec l'objet %s", this.getNom(), obj.getNom()));
    }
  }


  /**
  * getEtat Permet obtenir Etat porte
  @return Etat porte
  */

  public Etat getEtat() {
    return this.etat;
  }

  /**
  * getSerrure Permet obtenir serrure porte
  * @return Serrure de la porte
  */
  public Serrure getSerrure() {
    return this.serrure;
  }

  /**
  * getPieceAutreCote Donne la ppièce de l'autre coté
  @param piece Piece dans laquel se trouve la porte
  @return porte de l'autre coté
  */

  public Piece getPieceAutreCote(Piece piece) {
    if (piece.equals(this.pieceA)) {
      return this.pieceB;
    } else if (piece.equals(this.pieceB)) {
      return this.pieceA;
    } else {
      return null;
    }
  }


  /**
  * toString créer un String décrivant la porte
  * @return le String de description
  */
  public String toString() {
    StringBuilder str = new StringBuilder();
		str.append("\n----- Porte -----\n");
		str.append(String.format("nom: %s\nnom du monde: %s\nnom pieceA: %s\nnom pieceB: %s\netat: %s\n",
      this.nom, this.monde.getNom(), this.pieceA.getNom(), this.pieceB.getNom(), this.etat.toString()));
		str.append("\n-----------------\n");
		return str.toString();
  }

}
