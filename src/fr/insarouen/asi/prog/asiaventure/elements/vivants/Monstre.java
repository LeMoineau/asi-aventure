
package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.Executable;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;



import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Collection;
import java.util.Collections;
import java.lang.Throwable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Monstre extends Vivant implements Executable {

  /**
	 * Constructeur d'un monstre
	 *
	 * @param nom        nom du monstre
	 * @param monde      monde du monstre
	 * @param pointVie   point de vie du monstre
	 * @param pointForce point de force du monstre
	 * @param piece      piece dans laquelle est le monstre
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 */
  public Monstre(String nom, Monde monde, int pointVie, int pointForce, Piece piece)
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    super(nom, monde, pointVie, pointForce, piece);
  }

  /**
  * getPorteDansPiece retourne toute les portes activables de la pièce
  * @return Liste des portes
  */
  private List<Porte> getPortesDansPiece(){
    return this.piece.getPortes()
        .stream()
        .filter(p -> (p.getEtat() != Etat.VERROUILLE))
        .collect(Collectors.toList());
  }

  /**
  * getPorteDansPiece retourne toute les portes activables de la pièce
  * @return Liste des portes
  */
  private Porte selectionPorte(List<Porte> portesDeLaPiece) throws ActivationImpossibleException{
    Random rand = new Random();
    int index = rand.nextInt(portesDeLaPiece.size());
    Porte selectedPorte = portesDeLaPiece.get(index);
    if (selectedPorte.getEtat() == Etat.FERME) {
       selectedPorte.activer();
    }
    return selectedPorte;
  }

  /**
  * getObjetsDeLaPiece retourne tout les objets deplacables de la piece
  * @return Liste des objets
  */
  private List<Objet> getObjetsDeLaPiece(){
    return this.piece.getObjets()
          .stream()
          .filter(Objet::estDeplacable)
          .collect(toList());
  }

  /**
  * getObjetsDuMonstre retourne tout les objets deplacables du Monstre
  * @return Liste des objets
  */
  private List<Objet> getObjetsDuMonstre(){
    return this.getObjets().values()
          .stream()
          .filter(Objet::estDeplacable)
          .collect(toList());
  }

  /**
	 * executer facon de bouger et d'agir du monstre
   * @throws Throwable
	 */
  public void executer() throws Throwable {
    if (!this.estMort()) {
      List<Porte> portesDeLaPiece = getPortesDansPiece();
      if (portesDeLaPiece.size() > 0) {
        this.franchir(selectionPorte(portesDeLaPiece));
        List<Objet> newObjets= getObjetsDeLaPiece();
        List<Objet> oldObjets= getObjetsDuMonstre();
        for (Objet old: oldObjets) {
          this.deposer(old);
        }
        for (Objet news: newObjets) {
          this.prendre(news);
        }
        this.setPointVie(this.getPointVie() - 1);
      }
    }
  }

}
