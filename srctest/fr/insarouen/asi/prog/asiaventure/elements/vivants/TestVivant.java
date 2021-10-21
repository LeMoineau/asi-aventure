package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;

import java.util.HashMap;


public class TestVivant {

  public Monde monde;
  public Piece piece;

  class ObjetTest extends Objet {

    boolean deplacable;

    public ObjetTest(String nom, Monde monde, boolean deplacable)
    throws NomDEntiteDejaUtiliseDansLeMondeException {
      super(nom, monde);
      this.deplacable = deplacable;
    }

    public boolean estDeplacable() {
      return this.deplacable;
    }

  }

  @Before
  public void init() throws Exception{
    monde = new Monde("un monde");
    piece = new Piece("une piece", monde);
  }

  @Test(expected=ObjetNonPossedeParLeVivantException.class)
  public void test_deposer_getObjets()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ObjetNonPossedeParLeVivantException {
    ObjetTest obj = new ObjetTest("un objet", monde, true);
    ObjetTest obj2 = new ObjetTest("un autre objet", monde, true);
    Vivant vivant = new Vivant("un vivant", monde, 100, 10, piece, obj, obj2) {};
    vivant.deposer(obj2);
    HashMap<String,Objet> contient = new HashMap<String,Objet>();
    contient.put(obj.getNom(),obj);
    assertThat(vivant.getObjets(), equalTo(contient));
    vivant.deposer("un objet");
    contient.remove(obj.getNom());
    assertThat(vivant.getObjets(), equalTo(contient));
    vivant.deposer("un autre objet");
  }

  @Test
  public void test_estMort()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Vivant mort = new Vivant("un vivant ... mort", monde, -1000, 10, piece) {};
    Vivant vivant = new Vivant("un vivant vivant", monde, 1000, 10, piece) {};
    assertThat(mort.estMort(), is(true));
    assertThat(vivant.estMort(), is(false));
  }

  @Test
  public void test_getObjet()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    ObjetTest obj = new ObjetTest("un objet", monde, true);
    Vivant vivant = new Vivant("un vivant", monde, 100, 10, piece, obj) {};
    assertThat(vivant.getObjet("un objet"), equalTo(obj));
    assertThat(vivant.getObjet("un autre objet"), equalTo(null));
  }

  @Test
  public void test_getPiece()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Vivant vivant = new Vivant("un vivant", monde, 100, 10, piece) {};
    assertThat(vivant.getPiece(), equalTo(piece));
  }

  @Test
  public void test_getPointVie_setPointVie()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Vivant vivant = new Vivant("un vivant", monde, 100, 10, piece) {};
    assertThat(vivant.getPointVie(), equalTo(100));
    vivant.setPointVie(200);
    assertThat(vivant.getPointVie(), equalTo(200));
  }

  @Test
  public void test_getPointForce()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Vivant vivant = new Vivant("un vivant", monde, 100, 10, piece) {};
    assertThat(vivant.getPointForce(), equalTo(10));
  }

  @Test
  public void test_possede()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    ObjetTest obj = new ObjetTest("un objet", monde, true);
    ObjetTest obj2 = new ObjetTest("un autre objet", monde, true);
    Vivant vivant = new Vivant("un vivant", monde, 100, 10, piece, obj) {};
    assertThat(vivant.possede(obj), is(true));
    assertThat(vivant.possede(obj2), is(false));
  }

  @Test
  public void test_prendre()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ObjetNonDeplacableException, ObjetAbsentDeLaPieceException {
    ObjetTest obj = new ObjetTest("un objet", monde, true);
    ObjetTest obj2 = new ObjetTest("un autre objet", monde, true);
    piece.deposer(obj);
    piece.deposer(obj2);
    Vivant vivant = new Vivant("un vivant", monde, 100, 10, piece) {};
    vivant.prendre(obj);
    HashMap<String,Objet> contient = new HashMap<String,Objet>();
    contient.put(obj.getNom(),obj);
    assertThat(vivant.getObjets(), equalTo(contient));
    vivant.prendre("un autre objet");
    contient.put(obj2.getNom(),obj2);
    assertThat(vivant.getObjets(), equalTo(contient));
  }

  @Test(expected=ObjetAbsentDeLaPieceException.class)
  public void test_prendre_absentDeLaPiece()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ObjetNonDeplacableException, ObjetAbsentDeLaPieceException {
    Vivant vivant = new Vivant("un vivant", monde, 100, 10, piece) {};
    vivant.prendre("un objet");
  }

  @Test
  public void test_franchir()
  throws NomDEntiteDejaUtiliseDansLeMondeException, PorteFermeException, PorteInexistanteDansLaPieceException, ActivationImpossibleException {
    Vivant vivant = new Vivant("un vivant", monde, 100, 10, piece) {};
    Piece piece2 = new Piece("une autre piece", monde);
    Porte porte = new Porte("une porte", monde, piece, piece2);
    porte.activer();
    vivant.franchir(porte);
    assertThat(vivant.getPiece(), equalTo(piece2));
    vivant.franchir("une porte");
    assertThat(vivant.getPiece(), equalTo(piece));
  }

  @Test(expected=PorteFermeException.class)
  public void test_franchir_PorteFerme()
  throws NomDEntiteDejaUtiliseDansLeMondeException, PorteFermeException, PorteInexistanteDansLaPieceException, ActivationImpossibleException {
    Vivant vivant = new Vivant("un vivant", monde, 100, 10, piece) {};
    Piece piece2 = new Piece("une autre piece", monde);
    Porte porte = new Porte("une porte", monde, piece, piece2);
    vivant.franchir(porte);
  }

  @Test(expected=PorteInexistanteDansLaPieceException.class)
  public void test_franchir_PorteInexistante()
  throws NomDEntiteDejaUtiliseDansLeMondeException, PorteFermeException, PorteInexistanteDansLaPieceException, ActivationImpossibleException {
    Vivant vivant = new Vivant("un vivant", monde, 100, 10, piece) {};
    Piece piece2 = new Piece("une autre piece", monde);
    Piece piece3 = new Piece("encore une autre piece", monde);
    Porte porte = new Porte("une porte", monde, piece2, piece3);
    porte.activer();
    vivant.franchir(porte);
  }

}
