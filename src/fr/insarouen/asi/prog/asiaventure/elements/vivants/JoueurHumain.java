
package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import java.lang.reflect.InvocationTargetException;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.ASIAventureException;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.Executable;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Collection;
import java.util.Collections;
import java.lang.Throwable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.lang.reflect.Method;

import static java.util.stream.Collectors.toList;

public class JoueurHumain extends Vivant implements Executable {

  private String ordre;

  /**
	 * Constructeur d'un joueur humain
	 * @param nom nom du monstre
	 * @param monde monde du monstre
	 * @param pointVie point de vie du monstre
	 * @param pointForce point de force du monstre
	 * @param piece piece dans laquelle est le monstre
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 */
  public JoueurHumain(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets)
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    super(nom, monde, pointVie, pointForce, piece, objets);
    this.ordre = "";
  }

  /**
  * setOrdre defini l'ordre d'action d'un joueur
  * @param ordre ordre d'action du joueur
  */
  public void setOrdre(String ordre) {
    this.ordre = ordre;
  }

  /**
   * commandePrendre prend un objet
   * @param nomObjet nom de l'objet a prendre'
   * @throws ObjetAbsentDeLaPieceException
   * @throws ObjetNonDeplacableException
   */
  public void commandePrendre(String nomObjet)
  throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
    this.prendre(nomObjet);
  }

  /**
   * commandePoser pose un objet
   * @param nomObjet nom de l'objet a poser
   * @throws ObjetNonPossedeParLeVivantException
   */
  void commandePoser(String nomObjet)
  throws ObjetNonPossedeParLeVivantException {
    this.deposer(nomObjet);
  }

  /**
   * commandeFranchir franchir une porte
   * @param nomPorte nom de la porte a franchir
   * @throws PorteFermeException
   * @throws PorteInexistanteDansLaPieceException
   */
  void commandeFranchir(String nomPorte)
  throws PorteFermeException, PorteInexistanteDansLaPieceException {
    this.franchir(nomPorte);
  }

  /**
   * commandeOuvrirPorte ouvre une porte sans objet
   * @param nomPorte nom de la porte a ouvrir
   * @throws ActivationException
   * @throws PorteInexistanteDansLaPieceException
   */
  void commandeOuvrirPorte(String nomPorte)
  throws ActivationException, PorteInexistanteDansLaPieceException {
    this.piece.getPorte(nomPorte).activer();
  }

  /**
	 * commandeOuvrirPorte ouvre une porte avec un objet
   * @param nomPorte nom de la porte a ouvrir
   * @param nomObjet nom de l'objet a utiliser
   * @throws ActivationException
   * @throws PorteInexistanteDansLaPieceException
   * @throws ObjetNonPossedeParLeVivantException
	 */
  void commandeOuvrirPorte(String nomPorte, String nomObjet)
  throws ActivationException, PorteInexistanteDansLaPieceException, ObjetNonPossedeParLeVivantException {
    this.piece.getPorte(nomPorte).activerAvec(this.getObjet(nomObjet));
  }


  /**
	 * executer facon de bouger et d'agir du monstre
   * @throws CommandeImpossiblePourLeVivantException
   * @throws Throwable
	 */
  public void executer()
  throws CommandeImpossiblePourLeVivantException, Throwable {
    this.ordre = this.ordre.replace(" avec "," ");
    String[] args = ordre.split(" ");
    Class[] parameterType = new Class[args.length - 1];
    Object[] param = new Object[args.length - 1];
    for (int i = 0; i < args.length-1; i++ ) {
      parameterType[i] = String.class;
      param[i] = args[1+i];
    }
    try {
      String cap = args[0].substring(0, 1).toUpperCase() + args[0].substring(1);
      Method method = JoueurHumain.class.getDeclaredMethod(String.format("commande%s", cap), parameterType);
      method.invoke(this , (Object[])param);
    } catch (InvocationTargetException ex) {
      throw (ASIAventureException)ex.getCause();
    } catch (Exception ex) {
      throw new CommandeImpossiblePourLeVivantException(String.format("Commande impossible %s", this.ordre));
    }
  }

}
