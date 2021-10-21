package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;

public class TestSerrure{

	public Monde monde;
  public Serrure serrure;

	@Before
	public void init() throws NomDEntiteDejaUtiliseDansLeMondeException {
    this.monde = new Monde("un monde");
    this.serrure = new Serrure("une serrure", monde);
  }

  @Test
  public void test_constructor_genererNom()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Serrure serrure0 = new Serrure(monde);
    Serrure serrure1 = new Serrure(monde);
    assertThat(serrure0.getNom().equals(serrure1.getNom()), is(false));
  }

  @Test
  public void test_creerClef()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Clef clef = this.serrure.creerClef();
    Serrure serrure2 = new Serrure("une autre serrure", monde);
    Clef clef2 = serrure2.creerClef();
    assertThat(clef.getNom().equals(clef2.getNom()), is(false));
  }

  @Test(expected=ActivationException.class)
  public void test_activer()
  throws ActivationException {
    this.serrure.activer();
  }

  @Test
  public void test_activableAvec()
  throws NomDEntiteDejaUtiliseDansLeMondeException {
    Clef clef = this.serrure.creerClef();
    Clef fake = new Clef("une autre cle", monde);
    assertThat(this.serrure.activableAvec(clef), is(true));
    assertThat(this.serrure.activableAvec(fake), is(false));
  }

  @Test
  public void test_activerAvec_getEtat()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleAvecObjetException {
    Clef clef = this.serrure.creerClef();
    assertThat(this.serrure.getEtat(), equalTo(Etat.VERROUILLE));
    this.serrure.activerAvec(clef);
    assertThat(this.serrure.getEtat(), equalTo(Etat.DEVEROUILLE));
  }

  @Test(expected=ActivationImpossibleAvecObjetException.class)
  public void test_activerAvec_expection()
  throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleAvecObjetException {
    Clef fake = new Clef("une autre clef", monde);
    this.serrure.activerAvec(fake);
  }

}
