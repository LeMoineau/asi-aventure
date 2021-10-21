
package fr.insarouen.asi.prog.asiaventure.elements.structure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

import java.util.Collection;
import java.util.HashMap;

public class TestPiece {

  public Monde monde;
	public Piece piece;

  class ObjetTest extends Objet {

    public ObjetTest(String nom, Monde monde)
    throws NomDEntiteDejaUtiliseDansLeMondeException {
      super(nom, monde);
    }

    public boolean estDeplacable() {
      return true;
    }

  }

  class VivantTest extends Vivant {

    public VivantTest(String nom, Monde monde, Piece piece)
    throws NomDEntiteDejaUtiliseDansLeMondeException {
      super(nom, monde, 100, 10, piece);
    }

  }

	@Before
	public void init() throws Exception {
    monde = new Monde("un monde");
    piece = new Piece("la piece", monde);
	}

	@Test
	public void test_contientObjet()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    ObjetTest obj = new ObjetTest("un objet", monde);
    ObjetTest obj2 = new ObjetTest("un autre objet", monde);
    piece.deposer(obj);
		assertThat(piece.contientObjet(obj), is(true));
    assertThat(piece.contientObjet(obj2), is(false));
    assertThat(piece.contientObjet("un objet"), is(true));
    assertThat(piece.contientObjet("un autre objet"), is(false));
	}

  @Test
  public void test_contientVivant()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    VivantTest viv = new VivantTest("un vivant", monde, piece);
    Piece piece2 = new Piece("un autre piece", monde);
    VivantTest viv2 = new VivantTest("un autre vivant", monde, piece2);
    assertThat(piece.contientVivant(viv), is(true));
    assertThat(piece.contientVivant(viv2), is(false));
    assertThat(piece.contientVivant("un vivant"), is(true));
    assertThat(piece.contientVivant("un autre vivant"), is(false));
  }

  @Test
  public void test_deposer_getObjets()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    ObjetTest obj = new ObjetTest("un objet", monde);
    ObjetTest obj2 = new ObjetTest("un autre objet", monde);
    piece.deposer(obj);
    HashMap<String, Objet> contient = new HashMap<String, Objet>();
    contient.put(obj.getNom(), obj);
    assertThat(piece.getObjets().toString(), equalTo(contient.values().toString()));
    piece.deposer(obj2);
    contient.put(obj2.getNom(), obj2);
    assertThat(piece.getObjets().toString(), equalTo(contient.values().toString()));
  }

  @Test
  public void test_entrer_getVivants()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    //piece.entrer() est utilise dans le constructeur de Vivant
    VivantTest viv = new VivantTest("un vivant", monde, piece);
    HashMap<String, Vivant> contient = new HashMap<String, Vivant>();
    contient.put(viv.getNom(), viv);
    assertThat(piece.getVivants().toString(), equalTo(contient.values().toString()));
    VivantTest viv2 = new VivantTest("un autre vivant", monde, piece);
    contient.put(viv2.getNom(), viv2);
    assertThat(piece.getVivants().toString(), equalTo(contient.values().toString()));
  }

  @Test
  public void test_retirer()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ObjetNonDeplacableException, ObjetAbsentDeLaPieceException {
    ObjetTest obj = new ObjetTest("un objet", monde);
    ObjetTest obj2 = new ObjetTest("un autre objet", monde);
    piece.deposer(obj);
    piece.deposer(obj2);
    piece.retirer(obj);
    HashMap<String, Objet> contient = new HashMap<String, Objet>();
    contient.put(obj2.getNom(), obj2);
    assertThat(piece.getObjets().toString(), equalTo(contient.values().toString()));
    piece.retirer("un autre objet");
    contient.remove(obj2.getNom());
    assertThat(piece.getObjets().toString(), equalTo(contient.values().toString()));
  }

  @Test(expected=ObjetAbsentDeLaPieceException.class)
  public void test_retirer_absentDeLaPiece()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ObjetNonDeplacableException, ObjetAbsentDeLaPieceException {
    ObjetTest obj = new ObjetTest("un objet", monde);
    piece.retirer(obj);
  }

  @Test(expected=ObjetNonDeplacableException.class)
  public void test_retirer_nonDeplacable()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ObjetNonDeplacableException, ObjetAbsentDeLaPieceException {
    Objet nonDeplacable = new Objet("encore un autre", monde) {
      public boolean estDeplacable() {
        return false;
      }
    };
    piece.deposer(nonDeplacable);
    piece.retirer(nonDeplacable);
  }

  @Test
  public void test_sortir()
  throws NomDEntiteDejaUtiliseDansLeMondeException, VivantAbsentDeLaPieceException {
    VivantTest viv = new VivantTest("un vivant", monde, piece);
    VivantTest viv2 = new VivantTest("un autre vivant", monde, piece);
    piece.sortir(viv);
    HashMap<String, Vivant> contient = new HashMap<String, Vivant>();
    contient.put(viv2.getNom(), viv2);
    assertThat(piece.getVivants().toString(), equalTo(contient.values().toString()));
    piece.sortir("un autre vivant");
    contient.remove(viv2.getNom());
    assertThat(piece.getVivants().toString(), equalTo(contient.values().toString()));
  }

  @Test(expected=VivantAbsentDeLaPieceException.class)
  public void test_sortir_absentDeLaPiece()
  throws NomDEntiteDejaUtiliseDansLeMondeException, VivantAbsentDeLaPieceException {
    Piece piece2 = new Piece("une autre piece", monde);
    VivantTest viv = new VivantTest("un vivant", monde, piece2);
    piece.sortir(viv);
  }

  @Test
  public void test_addPorte_aLaPorte()
  throws NomDEntiteDejaUtiliseDansLeMondeException, VivantAbsentDeLaPieceException {
    Piece piece2 = new Piece("une autre piece", monde);
    Porte porte = new Porte("une porte", monde, piece, piece2); //addPorte utilise
    assertThat(piece.aLaPorte(porte), is(true));
    assertThat(piece2.aLaPorte(porte), is(true));
  }

  @Test(expected=UnsupportedOperationException.class)
  public void test_getPortes()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Piece piece2 = new Piece("une autre piece", monde);
    Porte porte = new Porte("une porte", monde, piece, piece2);
    Collection<Porte> portes = piece.getPortes();
    portes.add(porte);
  }

}
