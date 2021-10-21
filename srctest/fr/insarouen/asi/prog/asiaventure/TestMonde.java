package fr.insarouen.asi.prog.asiaventure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.Monde;

public class TestMonde {

	public Monde monde;
	public Entite ent;

	@Before
	public void init() throws Exception {
		monde = new Monde("MondeTest");
		ent = new Entite("Entite1", monde) {};
	}

	@Test
	public void test_getNom() {
		assertThat(monde.getNom(), equalTo("MondeTest"));
	}

	@Test
	public void test_getEntite() {
		assertThat(monde.getEntite("Entite1"), equalTo(ent));
		assertThat(monde.getEntite("Entite2"), equalTo(null));
	}

	@Test(expected=NomDEntiteDejaUtiliseDansLeMondeException.class)
	public void test_ajouterEntite()
	throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {
		monde.ajouter(ent);
	}

}
