
package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Monstre;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.JoueurHumain;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.asi.prog.asiaventure.ConditionDeFinVivantDansPiece;
import fr.insarouen.asi.prog.asiaventure.elements.Executable;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Collection;
import java.util.Collections;
import java.lang.Throwable;
import java.util.List;
import java.util.stream.Collectors;

import java.io.ObjectInputStream;
import java.io.Reader;
import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectOutputStream;

import fr.insarouen.asi.prog.asiaventure.elements.Entite;

public class Simulateur {

  private Monde monde;
  private ArrayList<ConditionDeFin> conditions;
  private EtatDuJeu etatDuJeu = EtatDuJeu.ENCOURS;

  /**
	* construirePiece construction d'une piece avec ses parametres
  * @param nomPiece nom de la piece a creer
  * @throws NomDEntiteDejaUtiliseDansLeMondeException
	*/
  private void construirePiece(String nomPiece)
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    new Piece(nomPiece, this.monde);
  }

  /**
	* construirePorteSerrure construction d'une porte a serrure
  * @param nomPorte nom de la porte a creer
	* @param nomP1 nom la premier piece reliee
  * @param nomP2 nom la seconde piece reliee
  * @throws NomDEntiteDejaUtiliseDansLeMondeException
	*/
  private void construirePorteSerrure(String nomPorte, String nomP1, String nomP2)
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Piece p1 = (Piece)this.monde.getEntite(nomP1);
    Piece p2 = (Piece)this.monde.getEntite(nomP2);
    new Porte(nomPorte, this.monde, new Serrure(this.monde), p1, p2);
  }

  /**
	* construirePorte construction d'une porte
  * @param nomPorte nom de la porte a creer
	* @param nomP1 nom la premier piece reliee
  * @param nomP2 nom la seconde piece reliee
  * @throws NomDEntiteDejaUtiliseDansLeMondeException
	*/
  private void construirePorte(String nomPorte, String nomP1, String nomP2)
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Piece p1 = (Piece)this.monde.getEntite(nomP1);
    Piece p2 = (Piece)this.monde.getEntite(nomP2);
    new Porte(nomPorte, this.monde, p1, p2);
  }

  /**
	* construireClef construction d'une clé avec ses parametres
  * @param nomPorte nom de la porte a ouvrir
	* @param nomOuDepose nom de la piece ou depose la clef
  * @throws NomDEntiteDejaUtiliseDansLeMondeException
	*/
  private void construireClef(String nomPorte, String nomOuDepose)
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Porte porte = (Porte)this.monde.getEntite(nomPorte);
    Piece where = (Piece)this.monde.getEntite(nomOuDepose);
    Clef cle = porte.getSerrure().creerClef();
    where.deposer(cle);
  }

  /**
	* construireClef construction d'un joueur humain
  * @param nom nom du joueur
	* @param pointVie point de vie du joueur
  * @param pointForce point de force du joueur
  * @param nomPiece nom de la piece du joueur
  * @throws NomDEntiteDejaUtiliseDansLeMondeException
	*/
  private void construireJoueurHumain(String nom, int pointVie, int pointForce, String nomPiece)
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Piece piece = (Piece)monde.getEntite(nomPiece);
    new JoueurHumain(nom, this.monde, pointVie, pointForce, piece);
  }

  /**
	* construireClef construction d'une clé avec ses parametres
  * @param etatStr nom de l'etat de la condition
	* @param nomVivant nom du vivant concerne par la condition
  * @param nomPiece nom de la piece ou se deroule la condition
  * @throws NomDEntiteDejaUtiliseDansLeMondeException
	*/
  private void construireCondition(String etatStr, String nomVivant, String nomPiece)
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    EtatDuJeu etat = EtatDuJeu.valueOf(etatStr);
    Piece piece = (Piece)this.monde.getEntite(nomPiece);
    JoueurHumain vivant = (JoueurHumain)this.monde.getEntite(nomVivant);
    this.ajouterConditionDeFin(new ConditionDeFinVivantDansPiece(etat,vivant,piece));
  }

  /**
	* lireFichierTexte fonction  de lecteur d'un ficher texte
	* @param reader reader d'un fichier texte
  * @return monde generer avec le reader
  * @throws NomDEntiteDejaUtiliseDansLeMondeException
  * @throws IOException
	*/
  private Monde lireFichierTexte(Reader reader)
  throws NomDEntiteDejaUtiliseDansLeMondeException, IOException {
    Scanner scanner = new Scanner(reader);
    scanner.useDelimiter(" |\n|\t|\r");
    while (scanner.hasNextLine()) {
      switch(scanner.next()) {
        case "Monde":
          this.monde = new Monde(scanner.next());
          break;
        case "Piece":
          construirePiece(scanner.next());
          break;
        case "PorteSerrure":
          construirePorteSerrure(scanner.next(),scanner.next(),scanner.next());
          break;
        case "Porte":
          construirePorte(scanner.next(),scanner.next(),scanner.next());
          break;
        case "Clef":
          construireClef(scanner.next(),scanner.next());
          break;
        case "JoueurHumain":
          construireJoueurHumain(scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.next());
          break;
        case "ConditionDeFinVivantDansPiece":
          construireCondition(scanner.next(),scanner.next(),scanner.next());
          break;
      }
    }
    scanner.close();
    return monde;
  }

	/**
	* Constructeur du Simulateur
	* @param nomDuMonde nom du monde
  * @throws NomDEntiteDejaUtiliseDansLeMondeException
  * @throws IOException
	*/
	public Simulateur(Monde monde, ConditionDeFin condition)
  throws NomDEntiteDejaUtiliseDansLeMondeException, IOException {
    this.monde = monde;
    this.conditions = new ArrayList<>();
    this.conditions.add(condition);
	}

  /**
	* Constructeur du Simulateur
	* @param ois flux de donne de sauvegarde
  * @throws NomDEntiteDejaUtiliseDansLeMondeException
  * @throws IOException
  * @throws ClassNotFoundException
	*/
  public Simulateur(ObjectInputStream ois)
  throws NomDEntiteDejaUtiliseDansLeMondeException, IOException, ClassNotFoundException {
    this.conditions = new ArrayList<>();
    this.monde = (Monde)ois.readObject();
    ConditionDeFin cond = null;
    try {
      while (!(cond = (ConditionDeFin)ois.readObject()).equals(null)) {
        this.conditions.add(cond);
      }
    }
    catch (Exception e){

    }

  }

  /**
	* Constructeur du Simulateur
	* @param reader Reader de fichier texte
  * @throws NomDEntiteDejaUtiliseDansLeMondeException
  * @throws IOException
	*/
  public Simulateur(Reader reader)
  throws NomDEntiteDejaUtiliseDansLeMondeException, IOException {
    this.conditions = new ArrayList<>();
    this.monde = lireFichierTexte(reader);
  }

  /**
	* getMonde Getter de monde
	* @return monde du Simulateur
	*/
  public Monde getMonde() {
    return this.monde;
  }

  /**
	* ajouterConditionsDeFin ajoute des conditions de fin au simulateur
  * @param conditions Collection de condition a ajouter
	*/
  public void ajouterConditionsDeFin(Collection<ConditionDeFin> conditions){
    this.conditions.addAll(conditions);
  }

  /**
	* ajouterConditionDeFin ajoute une condition de fin au simulateur
  * @param condition condition a ajouter
	*/
  public void ajouterConditionDeFin(ConditionDeFin condition){
    this.conditions.add(condition);
  }

  /**
  * enregister enregistre la partie en cours
  * @param oos ObjectOutputStream a ecraser
  * @throws IOException
  */
  public void enregistrer(ObjectOutputStream oos)
  throws IOException {
    oos.writeObject(this.monde);
    for (ConditionDeFin cond : this.conditions) {
      oos.writeObject(cond);
    }
  }

  /**
	* executerJusquALaFin execute les tour jusqu'a qu'une condition de fin se resolve
	* @return etat du jeu a la fin
	*/
  public void executerJusquALaFin() throws Throwable{
    while (this.getEtatDuJeu() == EtatDuJeu.ENCOURS) {
      this.executerUnTour();
    }
  }

  /**
	* executerNbTours execute n nombre de tour
  * @param n nombre de tour a executer
	* @return etat du jeu apres n tours
	*/
  public void executerNbTours(int n) throws Throwable{
    for (int i = 0; i<n; i++) {
      this.executerUnTour();
    }
  }

  /**
	* executerUnTour execute 1 tour
	* @return etat du jeu apres 1 tour
	*/
  public void executerUnTour () throws Throwable{
    for (Executable e  : this.monde.getExecutables()){
      if (e instanceof JoueurHumain){
        JoueurHumain tmp = (JoueurHumain)e;
        System.out.println(tmp);
        System.out.println(tmp.getPiece());
        System.out.println("Votre ordre?");
        Scanner sc = new Scanner(System.in);
        tmp.setOrdre(sc.nextLine());
      }
      e.executer();
    }
    for (ConditionDeFin condition: this.conditions) {
      this.etatDuJeu = condition.verifierCondition();
      if (this.etatDuJeu != EtatDuJeu.ENCOURS) {
        return;
      }
    }

  }

  /**
	* getEtatDuJeu Getter de l'etat actuel du jeu
	* @return etat du jeu
	*/
  public EtatDuJeu getEtatDuJeu() {
    return this.etatDuJeu;
  }

  /**
	* stopperJeu arrete le jeu
	*/
  public void	stopperJeu() {
    return;
  }

}
