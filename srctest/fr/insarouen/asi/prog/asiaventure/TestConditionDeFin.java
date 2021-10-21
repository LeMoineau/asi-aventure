package fr.insarouen.asi.prog.asiaventure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.EtatDuJeu;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;

public class TestConditionDeFin {

  Monde monde;
  Vivant vivant;
  Piece piece;

	@Before
	public void init() throws Exception {
    this.monde = new Monde("nn monde de test");
    this.piece = new Piece("une piece", monde);
    this.vivant = new Vivant("un vivant", monde, 20, 20, this.piece) {};
	}

	@Test
	public void test_conditionDeFinVivantMort() {
    ConditionDeFinVivantMort condition = new ConditionDeFinVivantMort(EtatDuJeu.SUCCES, this.vivant);
		assertThat(condition.verifierCondition(), equalTo(EtatDuJeu.ENCOURS));
    this.vivant.setPointVie(0);
    assertThat(condition.verifierCondition(), equalTo(condition.getEtatConditionVerifiee()));
	}

  @Test
	public void test_conditionDeFinVivantDansPiece()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Piece fakePiece = new Piece("une autre piece", this.monde);
    ConditionDeFinVivantDansPiece condition = new ConditionDeFinVivantDansPiece(EtatDuJeu.SUCCES, this.vivant, fakePiece);
		assertThat(condition.verifierCondition(), equalTo(EtatDuJeu.ENCOURS));
    condition = new ConditionDeFinVivantDansPiece(EtatDuJeu.SUCCES, this.vivant, this.piece);
    assertThat(condition.verifierCondition(), equalTo(condition.getEtatConditionVerifiee()));
	}

  @Test
	public void test_conditionDeFinVivantPossedeObjets()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Objet[] objets = new Objet[1];
    objets[0] = new PiedDeBiche(monde);
    Objet[] fakeObjets = new Objet[1];
    fakeObjets[0] = new PiedDeBiche(monde);
    this.vivant = new Vivant("un second vivant", monde, 20, 20, this.piece, objets) {};
    ConditionDeFinVivantPossedeObjets condition = new ConditionDeFinVivantPossedeObjets(EtatDuJeu.SUCCES, this.vivant, fakeObjets);
		assertThat(condition.verifierCondition(), equalTo(EtatDuJeu.ENCOURS));
    condition = new ConditionDeFinVivantPossedeObjets(EtatDuJeu.SUCCES, this.vivant, objets);
    assertThat(condition.verifierCondition(), equalTo(condition.getEtatConditionVerifiee()));
	}

  @Test
	public void test_conditionDeFinVivantDansPieceEtPossedeObjets()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Objet[] objets = new Objet[1];
    objets[0] = new PiedDeBiche(monde);
    Objet[] fakeObjets = new Objet[1];
    fakeObjets[0] = new PiedDeBiche(monde);
    this.vivant = new Vivant("un second vivant", monde, 20, 20, this.piece, objets) {};
    ConditionDeFinVivantDansPieceEtPossedeObjets condition =
      new ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu.SUCCES, this.vivant, this.piece, fakeObjets);
		assertThat(condition.verifierCondition(), equalTo(EtatDuJeu.ENCOURS));
    Piece fakePiece = new Piece("une fausse piece", monde);
    condition = new ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu.SUCCES, this.vivant, fakePiece, objets);
    assertThat(condition.verifierCondition(), equalTo(EtatDuJeu.ENCOURS));
    condition = new ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu.SUCCES, this.vivant, this.piece, objets);
    assertThat(condition.verifierCondition(), equalTo(condition.getEtatConditionVerifiee()));
	}

  @Test
  public void test_ConditionDeFinConjonctionConditionDeFin()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Objet[] objets = new Objet[1];
    objets[0] = new PiedDeBiche(monde);
    ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction c = new ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction(EtatDuJeu.SUCCES, this.vivant, this.piece, objets);
    assertThat(c.verifierCondition(), equalTo(EtatDuJeu.ENCOURS));
    this.vivant = new Vivant("un autre vivant", monde, 20, 20, this.piece, objets) {};
    c = new ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction(EtatDuJeu.SUCCES, this.vivant, this.piece, objets);
    assertThat(c.verifierCondition(), equalTo(c.getEtatConditionVerifiee()));
  }

}
