package fr.insarouen.asi.prog.asiaventure.elements.objets;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Coffre;


public class TestCoffre{

	public Monde monde;
	public Coffre coffre;

	@Before
	public void init() throws Exception{
		this.monde = new Monde("un monde");
		this.coffre = new Coffre("un coffre", monde);
	}

	@Test
	public void test_activer_getEtat()
	throws ActivationException {
		assertThat(this.coffre.getEtat(), equalTo(Etat.FERME));
		this.coffre.activer();
		assertThat(this.coffre.getEtat(), equalTo(Etat.OUVERT));
	}

	@Test(expected=ActivationException.class)
	public void test_activerAvec()
	throws ActivationException, NomDEntiteDejaUtiliseDansLeMondeException {
		this.coffre.activerAvec(new PiedDeBiche(this.monde));
	}

	@Test
	public void test_activableAvec()
	throws NomDEntiteDejaUtiliseDansLeMondeException {
		assertThat(this.coffre.activableAvec(new PiedDeBiche(this.monde)), is(false));
	}

}
