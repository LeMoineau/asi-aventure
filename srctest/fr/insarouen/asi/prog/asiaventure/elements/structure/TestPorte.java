package fr.insarouen.asi.prog.asiaventure.elements.structure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;

import java.util.HashMap;

public class TestPorte {

  public Monde monde;
  public Piece pieceA;
  public Piece pieceB;
  public Porte porte;
  public Serrure serrure;

  @Before
	public void init() throws Exception {
    monde = new Monde("un monde");
    pieceA = new Piece("une piece", monde);
    pieceB = new Piece("une autre piece", monde);
    serrure = new Serrure(monde);
    porte = new Porte("une porte", monde, serrure, pieceA, pieceB);
	}

	@Test
	public void test_activer_getEtat()
  throws ActivationImpossibleException, NomDEntiteDejaUtiliseDansLeMondeException {
    Porte porteSansClef = new Porte("une porte sans clef", monde, pieceA, pieceB);
    assertThat(porteSansClef.getEtat(), equalTo(Etat.FERME));
    porteSansClef.activer();
    assertThat(porteSansClef.getEtat(), equalTo(Etat.OUVERT));
	}

  @Test(expected=ActivationImpossibleException.class)
  public void test_activer_exception()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleException {
    Clef clef = this.porte.getSerrure().creerClef();
    PiedDeBiche pied = new PiedDeBiche(this.monde);
    this.porte.activerAvec(pied);
    assertThat(this.porte.getEtat(), equalTo(Etat.CASSE));
    this.porte.activer();
  }

  @Test
  public void test_getPieceAutreCote() {
    assertThat(porte.getPieceAutreCote(pieceA), equalTo(pieceB));
    assertThat(porte.getPieceAutreCote(pieceB), equalTo(pieceA));
  }

  @Test
  public void test_activableAvec_getSerrure()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Clef clef = this.porte.getSerrure().creerClef();
    assertThat(this.porte.activableAvec(clef), is(true));
    Serrure serrure2 = new Serrure(monde);
    Porte porteFake = new Porte("une autre porte", monde, serrure2, this.pieceA, this.pieceB);
    Clef fake = porteFake.getSerrure().creerClef();
    assertThat(this.porte.activableAvec(fake), is(false));
  }

  @Test
  public void test_activerAvec()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleAvecObjetException, ActivationImpossibleException {
    Clef clef = this.porte.getSerrure().creerClef();
    assertThat(this.porte.getEtat(),equalTo(Etat.VERROUILLE));
    porte.activerAvec(clef);
    assertThat(this.porte.getEtat(),equalTo(Etat.OUVERT));
    porte.activerAvec(clef);
    assertThat(this.porte.getEtat(),equalTo(Etat.VERROUILLE));
    PiedDeBiche pied = new PiedDeBiche(this.monde);
    porte.activerAvec(pied);
    assertThat(this.porte.getEtat(), equalTo(Etat.CASSE));
  }

  @Test(expected=ActivationImpossibleException.class)
  public void test_activerAvec_exception_1()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleAvecObjetException, ActivationImpossibleException {
    Clef clef = this.porte.getSerrure().creerClef();
    PiedDeBiche pied = new PiedDeBiche(this.monde);
    porte.activerAvec(pied);
    assertThat(this.porte.getEtat(),equalTo(Etat.CASSE));
    porte.activerAvec(clef);
  }

  @Test(expected=ActivationImpossibleException.class)
  public void test_activerAvec_exception_2()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleAvecObjetException, ActivationImpossibleException {
    Clef clef = this.porte.getSerrure().creerClef();
    PiedDeBiche pied = new PiedDeBiche(this.monde);
    porte.activerAvec(clef);
    assertThat(this.porte.getEtat(),equalTo(Etat.OUVERT));
    porte.activerAvec(pied);
  }

  @Test(expected=ActivationImpossibleException.class)
  public void test_activerAvec_exception_3()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleAvecObjetException, ActivationImpossibleException {
    Clef clef = this.porte.getSerrure().creerClef();
    PiedDeBiche pied = new PiedDeBiche(this.monde);
    porte.activerAvec(pied);
    assertThat(this.porte.getEtat(),equalTo(Etat.CASSE));
    porte.activerAvec(pied);
  }

  @Test(expected=ActivationImpossibleAvecObjetException.class)
  public void test_activerAvec_exception_4()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleAvecObjetException, ActivationImpossibleException {
    Clef clef = this.porte.getSerrure().creerClef();
    Objet test = new Objet("un objet", this.monde) {
      public boolean estDeplacable() {
        return true;
      }
    };
    porte.activerAvec(test);
  }

}
